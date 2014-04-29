/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.service;

import casemodule.assembler.CaseAssembler;
import casemodule.dto.CaseAgreementTest;
import casemodule.dto.CaseDataDTO;
import casemodule.dto.CaseUrgency;
import casemodule.dto.DataProcLogEntryType;
import casemodule.dto.ScannedPatientDataImageDTO;
import casemodule.dto.ScannedReferralImageDTO;
import casemodule.dto.ScannedReportImageDTO;
import casemodule.entity.DataProcLog;
import casemodule.entity.DataProcLogEntry;
import casemodule.entity.DicomImage;
import casemodule.entity.DicomPatientData;
import casemodule.entity.ElectronicPatientData;
import casemodule.entity.ElectronicReferral;
import casemodule.entity.HistoryCase;
import casemodule.entity.ImagePatientData;
import casemodule.entity.ImageReferral;
import casemodule.entity.ImageReport;
import casemodule.entity.ReferralInfo;
import casemodule.entity.ScannedPatientDataImage;
import casemodule.entity.ScannedReferralImage;
import casemodule.entity.ScannedReportImage;
import casemodule.entity.Series;
import casemodule.entity.TDSCase;
import casemodule.entity.TDSStudy;
import common.service.CaseAssignmentService;
import dicommodule.tdsdicomadapter.DicomAdapter;
import dicommodule.tdsdicomadapter.DicomInterfaceDBSide;
import hospitalmodule.repository.HospitalContractRepository;
import integrationmodule.dto.HistoryStudyUploadPackageDTO;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ApplicationException;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import masterdatamodule.entity.BodyRegion;
import masterdatamodule.entity.CaseStatus;
import masterdatamodule.entity.Modality;
import masterdatamodule.entity.ModeOfAcquisition;
import masterdatamodule.repository.CaseStatusRepository;
import tdsdicomimage.exception.DImgException;
import tdsdicominterface.DicomDataSet;
import tdsdicominterface.exception.DIException;
import usermodule.repository.HospitalStaffRepository;

/**
 *
 * @author vincze.attila
 */
@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 60)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CaseTransferService implements CaseTransferServiceRemote {

    @EJB(name = "systemMessageService")
    private CaseAssignmentService caseAssignmentService;
    @EJB(name = "caseStatusRepository")
    private CaseStatusRepository caseStatusRepository;
    @EJB(name = "dataProcLogService")
    private DataProcLogService dataProcLogService;
    @EJB(name = "hospitalContractRepository")
    private HospitalContractRepository hospitalContractRepository;
    @EJB(name = "HospitalStaffRepository")
    private HospitalStaffRepository hospitalStaffRepository;
    @Resource
    private SessionContext ctx;
    @PersistenceContext(unitName = "ITSMedHubServerPU", type = PersistenceContextType.EXTENDED)
    private EntityManager em;
    private Set<String> dicomUniqueIds;
    private String[] dicomUniquIdsArray;
    private TDSCase tdsCase;
    private HistoryCase historyCase;
    private int studyNumberInList;
    private int seriesNumberInList;

    public CaseTransferService() {
        dicomUniqueIds = new HashSet<String>();
        studyNumberInList = 0;
        seriesNumberInList = 0;
    }

    @Override
    public void startCaseTransfer(CaseDataDTO dto) {
        //dicomUniquIdsArray = dto.getDicomUniqueIds();
        ElectronicPatientData electronicPatientData = null;
        ElectronicReferral electronicReferral = null;
        //for (String s : dto.getDicomUniqueIds()) {
        //    System.out.println(s);
        //}
        tdsCase = new TDSCase();
        tdsCase.setHospitalCaseHistoryList(new ArrayList<HistoryCase>());
        tdsCase.setHospitalStaff(hospitalStaffRepository.findByLoginName(ctx.getCallerPrincipal().getName()));
        tdsCase.setCaseStatus(caseStatusRepository.findByEnglishName("received"));
        String strJQL = "select moa from ModeOfAcquisition moa "
                + "where moa.name like :name";
        tdsCase.setModeOfAcquisition(em.createQuery(strJQL, ModeOfAcquisition.class).setParameter("name", "All manual upload from file sys").getSingleResult());
        Long hospitalContractId = getHospitalContractId(tdsCase.getHospitalStaff().getId());
        tdsCase.setHospitalContract(hospitalContractRepository.find(hospitalContractId));
        double normalWorkTimeDays = tdsCase.getHospitalContract().getNormalWorkTimeDays();
        Calendar deadLine = Calendar.getInstance();
        deadLine.add(Calendar.HOUR, (int) (normalWorkTimeDays * 24));
        tdsCase.setDeadLine(deadLine.getTime());
        //tdsCase.setNonDicomCaseIdInHospital(dto.getNonDicomCaseIdInHospital());
        tdsCase.setNonDicomCaseIdInHospital1Name(dto.getNonDicomCaseIdInHospital1Name());
        tdsCase.setNonDicomCaseIdInHospital1ShortName(dto.getNonDicomCaseIdInHospital1ShortName());
        tdsCase.setNonDicomCaseIdInHospital1Value(dto.getNonDicomCaseIdInHospital1Value());
        tdsCase.setNonDicomCaseIdInHospital2Name(dto.getNonDicomCaseIdInHospital2Name());
        tdsCase.setNonDicomCaseIdInHospital2ShortName(dto.getNonDicomCaseIdInHospital2ShortName());
        tdsCase.setNonDicomCaseIdInHospital2Value(dto.getNonDicomCaseIdInHospital2Value());
        tdsCase.setTransferStarted(new Date());
        tdsCase.setAgreementTest(CaseAgreementTest.ok.name());
        tdsCase.setAgreementTestDateTime(new Date());
        tdsCase.setTransferredToServer(new Date());
        tdsCase.setUrgency(CaseUrgency.normal.name());
        tdsCase.setTdsCaseUniqueId(
                tdsCase.getHospitalContract().getHospital().getAbbrevName()
                + new SimpleDateFormat("yyyy.MM.dd.hhmmssSSS").format(tdsCase.getTransferredToServer()));
        tdsCase.setReferralInfoCollection(new ArrayList<ReferralInfo>());
        tdsCase.getReferralInfoCollection().add(new ReferralInfo());
        tdsCase.getReferralInfoCollection().get(0).setTDSCase(tdsCase);
        tdsCase.getReferralInfoCollection().get(0).settDSStudyCollection(new ArrayList<TDSStudy>());

        System.out.println("checking getElectronicPatientData");
        if (!"".equals(dto.getElectronicPatientData().getPatientIdInHospital())) {
            electronicPatientData = CaseAssembler.toEntity(dto.getElectronicPatientData());
            System.out.println("getElectronicPatientData");
            em.persist(electronicPatientData);
            tdsCase.setElectronicPatientData(electronicPatientData);
        }
        System.out.println("checking getElectronicReferral");
        if (!"".equals(dto.getElectronicReferral().getReferralText())) {
            electronicReferral = CaseAssembler.toEntity(dto.getElectronicReferral());
            System.out.println("getElectronicReferral");
            em.persist(electronicReferral);
            tdsCase.getReferralInfoCollection().get(0).setElectronicReferral(electronicReferral);
        }
        //saveImageReferral(dto.getScannedReferralImageList());
        //saveImagePatientData(dto.getScannedPatientDataImageList());

        DataProcLog dataProcLog = new DataProcLog();
        dataProcLog.setStarted(new Date());
        dataProcLog.setTDSCase(tdsCase);
        tdsCase.setDataProcLog(dataProcLog);
        em.persist(dataProcLog);
        em.persist(tdsCase);
        //em.flush();

        //dataProcLogService.saveEntry(tdsCase.getDataProcLog(), DataProcLogEntryType.InsertedToDatabase, "TDSCase [id]: " + tdsCase.getTdsCaseUniqueId());

        //em.persist(tdsCase);
        //dataProcLogService.saveEntry(tdsCase.getDataProcLog(), DataProcLogEntryType.InsertedToDatabase,
        //        "TDSCase", null, tdsCase.getTdsCaseUniqueId(), null, null);
        DataProcLogEntry dataProcLogEntry = new DataProcLogEntry();
        dataProcLogEntry.setEntryGeneration(new Date());
        dataProcLogEntry.setEntryType(DataProcLogEntryType.InsertedToDatabase.name());
        dataProcLogEntry.setDataProcLog(tdsCase.getDataProcLog());
        dataProcLogEntry.setSender(ctx.getCallerPrincipal().getName());
        dataProcLogEntry.setTableName("TDSCase");
        dataProcLogEntry.setRecordUid(tdsCase.getTdsCaseUniqueId());
        em.persist(dataProcLogEntry);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Remove
    public void commitCaseTransfer(String[] ids) {
        System.out.println("commitCaseTransfer");
        tdsCase.setTransferredToServer(new Date());
        calculateSeriesMinAndMaxRawValue();
        doAgreementTest();
        assignCaseToRadiologistRandomly();
        em.merge(tdsCase);
    }

    private void calculateSeriesMinAndMaxRawValue(){
        for (TDSStudy tDSStudy : tdsCase.getReferralInfoCollection().get(0).gettDSStudyCollection()) {

        }
    }
    @Override
    public void saveScannedReferralImage(ScannedReferralImageDTO dto) {
        System.out.println("saveImageReferral");
        if (tdsCase.getReferralInfoCollection().get(0).getImageReferral() == null) {
            ImageReferral imageReferral = new ImageReferral();
            em.persist(imageReferral);
            tdsCase.getReferralInfoCollection().get(0).setImageReferral(imageReferral);
        }
        ScannedReferralImage scannedReferralImage = CaseAssembler.toEntity(dto);
        //scannedReferralImage.setImageReferral(tdsCase.getReferralInfoCollection().get(0).getImageReferral());
        em.persist(scannedReferralImage);
    }

    @Override
    public void saveScannedPatientDataImage(ScannedPatientDataImageDTO dto) {
        System.out.println("saveImagePatientData");
        if (tdsCase.getImagePatientData() == null) {
            ImagePatientData imagePatientData = new ImagePatientData();
            em.persist(imagePatientData);
            tdsCase.setImagePatientData(imagePatientData);
        }
        ScannedPatientDataImage scannedPatientDataImage = CaseAssembler.toEntity(dto);
        //scannedPatientDataImage.setImagePatientData(tdsCase.getImagePatientData());
        em.persist(scannedPatientDataImage);
    }

    private Long getHospitalContractId(long senderHospitalStaffId) {
        System.out.println("getHospitalContractId");
        String strJQL = "select hc.id from HospitalContract hc, Hospital h, HospitalStaff hs "
                + "where hc.hospital.id = h.id and hs.hospital.id = h.id "
                + "and hc.active = 1 and hs.id = :id";
        Long hospitalContractId = null;
        try {
            hospitalContractId =
                    em.createQuery(strJQL, Long.class).setParameter("id", senderHospitalStaffId).getSingleResult();
        } catch (NoResultException ex) {
            //Logger.getLogger(CaseTransferService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hospitalContractId;
    }

    private Modality getModality(String dicomName) {
        System.out.println("getModality");
        String strJQL = "select m from Modality m where m.name = :dicomName";
        Modality modality =
                em.createQuery(strJQL, Modality.class).setParameter("dicomName", dicomName).getSingleResult();
        return modality;
    }

    private BodyRegion getBodyRegion(String dicomName) {
        System.out.println("getBodyRegion");
        String strJQL = "select br from BodyRegion br where br.dicomName = :dicomName";
        BodyRegion bodyRegion = em.createNamedQuery("BodyRegion.findById", BodyRegion.class).setParameter("id", (long) 2).getSingleResult();
        try {
            bodyRegion =
                    em.createQuery(strJQL, BodyRegion.class).setParameter("dicomName", dicomName).getSingleResult();
        } catch (NoResultException ex) {
            //Logger.getLogger(CaseTransferService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bodyRegion;
    }

    private CaseStatus getCaseStatusStatusByName(String name) {
        String strJQL = "select cs from CaseStatus cs "
                + "where cs.englishName = :name";
        CaseStatus caseStatus = em.createQuery(strJQL, CaseStatus.class).setParameter("name", name).getSingleResult();
        return caseStatus;
    }

    @Override
    public void saveDicomDataSet(DicomDataSet dicomDataSet) {
        //initializing variables
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        DateFormat timeFormat = new SimpleDateFormat("hhmmss");
        TDSStudy tdsStudy = null;
        Series series = null;
        //tdsdicomimage.DicomImage tdsDicomImage = new tdsdicomimage.DicomImage();
        DicomInterfaceDBSide dicomIntf = new DicomAdapter(dicomDataSet);
        String dicomDataSetInstanceUid = dicomIntf.getUniqueId();
        System.out.println(dicomDataSetInstanceUid);
        dicomUniqueIds.add(dicomDataSetInstanceUid);
        try {
            String tdsStudyInstanceUid = dicomIntf.getStudyInstanceUID();
            String seriesInstanceUid = dicomIntf.getSeriesInstanceUID();
            //saving dicompatientdata
            if (historyCase == null) {
                if (tdsCase.getDicomPatientData() == null) {
                    System.out.println("saving dicompatientdata");
                    DicomPatientData dicomPatientData = new DicomPatientData();
                    dicomPatientData = new DicomPatientData();
                    dicomPatientData.setComments(dicomIntf.getPatientComments());
                    if (dicomIntf.getPatientDoBYYYYMMDD() != null) {
                        dicomPatientData.setDob(dateFormat.parse(dicomIntf.getPatientDoBYYYYMMDD()));
                    }
                    dicomPatientData.setIssuerOfPatientId(dicomIntf.getIssuerOfPatientID());
                    dicomPatientData.setMothersBirthName(dicomIntf.getPatientsMothersBirthName());
                    dicomPatientData.setOtherPatientId(dicomIntf.getOtherPatientIDs());
                    dicomPatientData.setOtherPatientIdRecords(CaseAssembler.toByteArray(dicomIntf.getOtherPatientIDRecords()));
                    dicomPatientData.setOtherPatientNames(dicomIntf.getOtherDICOMPatientNames());
                    dicomPatientData.setPatientId(dicomIntf.getDICOMPatientID());
                    dicomPatientData.setPatientName(dicomIntf.getDICOMPatientName());
                    dicomPatientData.setSex(dicomIntf.getPatientSex());
                    dicomPatientData.setTypeOfPatientId(dicomIntf.getTypeOfPatientID());
                    tdsCase.setDicomPatientData(dicomPatientData);
                    em.persist(dicomPatientData);
                }
            } else {
                if (historyCase.getDicomPatientData() == null) {
                    System.out.println("saving dicompatientdata");
                    DicomPatientData dicomPatientData = new DicomPatientData();
                    dicomPatientData = new DicomPatientData();
                    dicomPatientData.setComments(dicomIntf.getPatientComments());
                    if (dicomIntf.getPatientDoBYYYYMMDD() != null) {
                        dicomPatientData.setDob(dateFormat.parse(dicomIntf.getPatientDoBYYYYMMDD()));
                    }
                    dicomPatientData.setIssuerOfPatientId(dicomIntf.getIssuerOfPatientID());
                    dicomPatientData.setMothersBirthName(dicomIntf.getPatientsMothersBirthName());
                    dicomPatientData.setOtherPatientId(dicomIntf.getOtherPatientIDs());
                    dicomPatientData.setOtherPatientIdRecords(CaseAssembler.toByteArray(dicomIntf.getOtherPatientIDRecords()));
                    dicomPatientData.setOtherPatientNames(dicomIntf.getOtherDICOMPatientNames());
                    dicomPatientData.setPatientId(dicomIntf.getDICOMPatientID());
                    dicomPatientData.setPatientName(dicomIntf.getDICOMPatientName());
                    dicomPatientData.setSex(dicomIntf.getPatientSex());
                    dicomPatientData.setTypeOfPatientId(dicomIntf.getTypeOfPatientID());
                    historyCase.setDicomPatientData(dicomPatientData);
                    em.persist(dicomPatientData);
                }
            }

            //saving study
            if (historyCase == null) {
                for (TDSStudy tDSStudy : tdsCase.getReferralInfoCollection().get(0).gettDSStudyCollection()) {
                    System.out.println(tDSStudy.getInstanceUid());
                    if (tdsStudyInstanceUid.equals(tDSStudy.getInstanceUid())) {
                        tdsStudy = tDSStudy;
                        break;
                    }
                }
            } else {
                for (TDSStudy tDSStudy : historyCase.getReferralInfoList().get(0).gettDSStudyCollection()) {
                    System.out.println(tDSStudy.getInstanceUid());
                    if (tdsStudyInstanceUid.equals(tDSStudy.getInstanceUid())) {
                        tdsStudy = tDSStudy;
                        break;
                    }
                }
            }
            if (tdsStudy == null) {
                tdsStudy = new TDSStudy();
                tdsStudy.setNumberInList(++studyNumberInList);
                tdsStudy.setSeriesCollection(new ArrayList<Series>());
                if (historyCase == null) {
                    tdsStudy.setReferralInfo(tdsCase.getReferralInfoCollection().get(0));
                } else {
                    tdsStudy.setReferralInfo(historyCase.getReferralInfoList().get(0));
                }
                tdsStudy.setAdditionalPatientHistory(dicomIntf.getAdditionalPatientHistory());
                tdsStudy.setAdmittingDiagnoseDescription(dicomIntf.getAdditionalPatientHistory());
                tdsStudy.setDescription(dicomIntf.getStudyDescription());
                tdsStudy.setInstanceUid(dicomIntf.getStudyInstanceUID());
                tdsStudy.setPatientMedicalAlerts(dicomIntf.getPatientMedicalAlerts());
                tdsStudy.setReferringPhysyciansDicomName(dicomIntf.getReferringPhysyciansDICOMName());
                tdsStudy.setSmokingStatus(dicomIntf.getSmokingStatus());
                tdsStudy.setAccessionNumber(dicomIntf.getAccessionNumber());
                if (dicomIntf.getStudyDateYYYYMMDD() != null) {
                    tdsStudy.setStudyDate(dateFormat.parse(dicomIntf.getStudyDateYYYYMMDD()));
                }
                tdsStudy.setStudyId(dicomIntf.getStudyID());
                if (dicomIntf.getStudyTimeHHMMSS() != null) {
                    tdsStudy.setStudyTime(timeFormat.parse(dicomIntf.getStudyTimeHHMMSS()));
                }
                System.out.println("saving study");
                em.persist(tdsStudy);
                if (historyCase == null) {
                    tdsCase.getReferralInfoCollection().get(0).gettDSStudyCollection().add(tdsStudy);
                } else {
                    historyCase.getReferralInfoList().get(0).gettDSStudyCollection().add(tdsStudy);
                }

            }

            //saving series
            for (Series ser : tdsStudy.getSeriesCollection()) {
                if (seriesInstanceUid.equals(ser.getInstanceUid())) {
                    series = ser;
                    break;
                }
            }
            if (series == null) {
                series = new Series();
                series.setNumberInList(++seriesNumberInList);
                series.setDicomImageCollection(new ArrayList<DicomImage>());
                series.setTDSStudy(tdsStudy);
                series.setModality(getModality(dicomIntf.getModality()));
                series.setBodyRegion1(getBodyRegion(dicomIntf.getBodyPartExamined()));
                series.setDescription(dicomIntf.getSeriesDescription());
                series.setInstanceUid(dicomIntf.getSeriesInstanceUID());
                series.setEquipmentDeviceSerialNumber(dicomIntf.getEquipmentDeviceSerialNumber());
                series.setBodyPartExamined(dicomIntf.getBodyPartExamined());
                series.setClinicalTrialSeriesDescription(dicomIntf.getClinicalTrialSeriesDescription());
                series.setClinicalTrialSeriesId(dicomIntf.getClinicalTrialSeriesID());
                if (dicomIntf.getEquipmentDateOfLastCalibrationYYYYMMDD() != null) {
                    series.setEquipmentDateOfLastCalibration(dateFormat.parse(dicomIntf.getEquipmentDateOfLastCalibrationYYYYMMDD()));
                }
                series.setEquipmentDeviceSerialNumber(dicomIntf.getEquipmentDeviceSerialNumber());
                series.setEquipmentInstitutionalDepartmentName(dicomIntf.getEquipmentInstitutionalDepartmentName());
                series.setEquipmentLocationInstitutionName(dicomIntf.getEquipmentLocationInstitutionName());
                series.setEquipmentManufacturer(dicomIntf.getEquipmentManufacturer());
                series.setEquipmentManufacturersModelName(dicomIntf.getEquipmentManufacturersModelName());
                series.setEquipmentStationName(dicomIntf.getEquipmentStationName());
                if (dicomIntf.getEquipmentTimeOfLastCalibrationHHMMSS() != null) {
                    series.setEquipmentTimeOfLastCalibration(timeFormat.parse(dicomIntf.getEquipmentTimeOfLastCalibrationHHMMSS()));
                }
                series.setFrameOfReferenceUid(dicomIntf.getFrameOfReferenceUID());
                series.setLaterality(dicomIntf.getLaterality());
                series.setOperatorsDicomName(dicomIntf.getOperatorsDICOMName());
                series.setPerformingPhysiciansDicomName(dicomIntf.getPerformingPhysiciansDICOMName());
                series.setProtocolName(dicomIntf.getProtocolName());
                if (dicomIntf.getSeriesDateYYYYMMDD() != null) {
                    series.setSeriesDate(dateFormat.parse(dicomIntf.getSeriesDateYYYYMMDD()));
                }
                series.setSeriesNumberState(dicomIntf.getSeriesNumber().isEmpty());
                series.setSeriesNumberValue(dicomIntf.getSeriesNumber().value);
                series.setSeriesMinRawValue(0.0);
                series.setSeriesMaxRawValue(0.0);
                if (dicomIntf.getSeriesTimeHHMMSS() != null) {
                    series.setSeriesTime(timeFormat.parse(dicomIntf.getSeriesTimeHHMMSS()));
                }
                System.out.println("saving series");
                em.persist(series);
                tdsStudy.getSeriesCollection().add(series);
            }

            //saving dicomimagedataset
            DicomImage dicomImage = new DicomImage();
            dicomImage.setDicomUniqueId(dicomDataSetInstanceUid);
            dicomImage.setSeries(series);
            dicomImage.setDicomDataSet(CaseAssembler.toByteArray(dicomDataSet));
            dicomImage.setDicomDataSetIcon(CaseAssembler.toByteArray(dicomDataSet));
            tdsdicomimage.DicomImage dImage = new tdsdicomimage.DicomImage();
//            try {
//                DicomDataSet icon = tdsDicomImage.repackImageToNewDataSet(dicomDataSet, tdsdicomimage.DicomImage.Format_JPEG2000, null, null, null, null, null);
//                dicomImage.setDicomDataSetIcon(CaseAssembler.toByteArray(icon));
//            } catch (DImgException ex) {
//                Logger.getLogger(CaseTransferService.class.getName()).log(Level.SEVERE, null, ex);
//            }
            dicomImage.setInstanceNumber(dicomIntf.getInstanceNumber());
            dicomImage.setSopAuthorizationComment(dicomIntf.getSOPAuthorizationComment());
            dicomImage.setSopAuthorizationDateTime(dicomIntf.getSOPAuthorizationDateTimeYYYYMMDDHHMMSSFFFFFFplusOptionalSZZZZ());
            dicomImage.setSopClassUid(dicomIntf.getSOPClassUID());
            dicomImage.setSopInstanceStatus(dicomIntf.getSOPInstanceStatus());
            dicomImage.setInstanceUid(dicomIntf.getSOPInstanceUID());
            dicomImage.setTimeZoneOffsetFromUtc(dicomIntf.getTimezoneOffsetFromUTC());
            System.out.println("saving dicomimagedataset");
            em.persist(dicomImage);
            float minRaw = 0;
            float maxRaw = 0;
            try {
                dImage.loadImage(dicomDataSet);
                minRaw = dImage.getMinValue();
                maxRaw = dImage.getMaxValue();
            } catch (DImgException ex) {
                Logger.getLogger(CaseTransferService.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (maxRaw > series.getSeriesMaxRawValue()){
                series.setSeriesMaxRawValue(new Double(maxRaw));
            }
            if (minRaw < series.getSeriesMinRawValue()){
                series.setSeriesMinRawValue(new Double(minRaw));
            }
            em.merge(series);
            series.getDicomImageCollection().add(dicomImage);
        } catch (IOException ex) {
            Logger.getLogger(CaseTransferService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CaseTransferService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DIException ex) {
            Logger.getLogger(CaseTransferService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void doAgreementTest() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        boolean isOk = true;
        DicomInterfaceDBSide dicomIntf;
        String message = null;
        for (TDSStudy tDSStudy : tdsCase.getReferralInfoCollection().get(0).gettDSStudyCollection()) {
            if (!isOk) {
                break;
            }
            for (Series series : tDSStudy.getSeriesCollection()) {
                if (!isOk) {
                    break;
                }
                for (DicomImage dicomImage : series.getDicomImageCollection()) {
                    try {
                        dicomIntf = new DicomAdapter(CaseAssembler.toDicomDataSet(dicomImage.getDicomDataSet()));

                        //dicompatientdata level
                        if (dicomIntf.getIssuerOfPatientID() == null ? tdsCase.getDicomPatientData().getIssuerOfPatientId() != null : !dicomIntf.getIssuerOfPatientID().equals(tdsCase.getDicomPatientData().getIssuerOfPatientId())) {
                            isOk = false;
                            message = "DicomPatientData level problem: getIssuerOfPatientID";
                            break;
                        } else if (dicomIntf.getPatientsMothersBirthName() == null ? tdsCase.getDicomPatientData().getMothersBirthName() != null : !dicomIntf.getPatientsMothersBirthName().equals(tdsCase.getDicomPatientData().getMothersBirthName())) {
                            isOk = false;
                            message = "DicomPatientData level problem: getPatientsMothersBirthName";
                            break;
                        } else if (dicomIntf.getOtherPatientIDs() == null ? tdsCase.getDicomPatientData().getOtherPatientId() != null : !dicomIntf.getOtherPatientIDs().equals(tdsCase.getDicomPatientData().getOtherPatientId())) {
                            isOk = false;
                            message = "DicomPatientData level problem: getOtherPatientIDs";
                            break;
                        } else if (dicomIntf.getOtherPatientIDRecords() == null ? tdsCase.getDicomPatientData().getOtherPatientIdRecords() != null
                                : dicomIntf.getOtherPatientIDRecords().length
                                != CaseAssembler.toStringArray(tdsCase.getDicomPatientData().getOtherPatientIdRecords()).length) {
                            isOk = false;
                            message = "DicomPatientData level problem: getOtherPatientIDRecords";
                            break;
                        } else if (dicomIntf.getOtherDICOMPatientNames() == null ? tdsCase.getDicomPatientData().getOtherPatientNames() != null : !dicomIntf.getOtherDICOMPatientNames().equals(tdsCase.getDicomPatientData().getOtherPatientNames())) {
                            isOk = false;
                            message = "DicomPatientData level problem: getOtherDICOMPatientNames";
                            break;
                        } else if (dicomIntf.getDICOMPatientID() == null ? tdsCase.getDicomPatientData().getPatientId() != null : !dicomIntf.getDICOMPatientID().equals(tdsCase.getDicomPatientData().getPatientId())) {
                            isOk = false;
                            message = "DicomPatientData level problem: getDICOMPatientID";
                            break;
                        } else if (dicomIntf.getDICOMPatientName() == null ? tdsCase.getDicomPatientData().getPatientName() != null : !dicomIntf.getDICOMPatientName().equals(tdsCase.getDicomPatientData().getPatientName())) {
                            isOk = false;
                            message = "DicomPatientData level problem: getDICOMPatientName";
                            break;
                        } else if (dicomIntf.getPatientSex() == null ? tdsCase.getDicomPatientData().getSex() != null : !dicomIntf.getPatientSex().equals(tdsCase.getDicomPatientData().getSex())) {
                            isOk = false;
                            message = "DicomPatientData level problem: getPatientSex";
                            break;
                        } else if (dicomIntf.getTypeOfPatientID() == null ? tdsCase.getDicomPatientData().getTypeOfPatientId() != null : !dicomIntf.getTypeOfPatientID().equals(tdsCase.getDicomPatientData().getTypeOfPatientId())) {
                            isOk = false;
                            message = "DicomPatientData level problem: getTypeOfPatientID";
                            break;
                        } else if (dicomIntf.getPatientDoBYYYYMMDD() == null ? tdsCase.getDicomPatientData().getDob() != null : dateFormat.parse(dicomIntf.getPatientDoBYYYYMMDD()) != tdsCase.getDicomPatientData().getDob()) {
                            isOk = false;
                            message = "DicomPatientData level problem: getPatientDoBYYYYMMDD";
                            break;
                        } else if (dicomIntf.getPatientComments() == null ? tdsCase.getDicomPatientData().getComments() != null : !dicomIntf.getPatientComments().equals(tdsCase.getDicomPatientData().getComments())) {
                            isOk = false;
                            message = "DicomPatientData level problem: getPatientComments";
                            break;
                        }

                        //study level
                        if (tDSStudy.getInstanceUid() == null ? dicomIntf.getStudyInstanceUID() != null
                                : !tDSStudy.getInstanceUid().equals(dicomIntf.getStudyInstanceUID())) {
                            isOk = false;
                            message = "Study level problem";
                            break;
                        }

                        //series level
                        if (series.getInstanceUid() == null ? dicomIntf.getSeriesInstanceUID() != null
                                : !series.getInstanceUid().equals(dicomIntf.getSeriesInstanceUID())) {
                            isOk = false;
                            message = "Series level problem";
                            break;
                        }


                    } catch (ParseException ex) {
                        Logger.getLogger(CaseTransferService.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DIException ex) {
                        Logger.getLogger(CaseTransferService.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(CaseTransferService.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(CaseTransferService.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }

        if (!isOk) {
            tdsCase.setAgreementTest(CaseAgreementTest.mismatch.name());
            tdsCase.setAgreementTestNotes(message);
        }
    }

    private void assignCaseToRadiologistRandomly() {
        if (!caseAssignmentService.assignCaseToRadiologistRandomly(tdsCase)) {

            dataProcLogService.saveEntry(
                    tdsCase.getDataProcLog(),
                    DataProcLogEntryType.AutomaticModification,
                    "TDSCase", "caseStatus", tdsCase.getTdsCaseUniqueId(), tdsCase.getCaseStatus().getEnglishName(),
                    "waiting - normal");
            tdsCase.setCaseStatus(caseStatusRepository.findByEnglishName("waiting - normal"));
        }
    }

    @Override
    public void saveHistoryStudy(HistoryStudyUploadPackageDTO dto) {
        historyCase = new HistoryCase();
        historyCase.settDSCase(tdsCase);
        tdsCase.getHospitalCaseHistoryList().add(historyCase);
        historyCase.setReferralInfoList(new ArrayList<ReferralInfo>());
        historyCase.getReferralInfoList().add(new ReferralInfo());
        historyCase.getReferralInfoList().get(0).setHistoryCase(historyCase);
        historyCase.getReferralInfoList().get(0).settDSStudyCollection(new ArrayList<TDSStudy>());
        if (dto.getReportText() == null ? "" != null : !dto.getReportText().equals("")) {
            historyCase.setFinalizedReport(dto.getReportText().getBytes());
        }
        if (dto.getReferralText() == null ? "" != null : !dto.getReferralText().equals("")) {

            ElectronicReferral electronicReferral = new ElectronicReferral();
            electronicReferral.setReferralText(dto.getReferralText());
            em.persist(electronicReferral);
            historyCase.getReferralInfoList().get(0).setElectronicReferral(electronicReferral);
        }
        if (dto.getReportImageList() != null && !dto.getReportImageList().isEmpty()) {
            ImageReport imageReport = new ImageReport();
            em.persist(imageReport);
            for (ScannedReportImageDTO scannedReportImageDTO : dto.getReportImageList()) {
                ScannedReportImage scannedReportImage = new ScannedReportImage();
                scannedReportImage.setScannedImage(scannedReportImageDTO.getScannedImage());
                scannedReportImage.setImageReport(imageReport);
                em.persist(scannedReportImage);
            }
            historyCase.setImageReport(imageReport);
        }
        if (dto.getReferralImageList() != null && !dto.getReferralImageList().isEmpty()) {
            ImageReferral imageReferral = new ImageReferral();
            em.persist(imageReferral);
            for (ScannedReferralImageDTO scannedReferralImageDTO : dto.getReferralImageList()) {
                ScannedReferralImage scannedReferralImage = new ScannedReferralImage();
                scannedReferralImage.setScannedImage(scannedReferralImageDTO.getScannedImage());
                //scannedReferralImage.setImageReferral(imageReferral);
                em.persist(scannedReferralImage);
            }
            historyCase.getReferralInfoList().get(0).setImageReferral(imageReferral);
        }

        em.persist(historyCase);
        em.merge(tdsCase);
//        System.out.println("checking getElectronicReferral");
//        if (!"".equals(dto.getElectronicReferral().getReferralText())) {
//            electronicReferral = CaseAssembler.toEntity(dto.getElectronicReferral());
//            System.out.println("getElectronicReferral");
//            em.persist(electronicReferral);
//            tdsCase.getReferralInfoCollection().get(0).setElectronicReferral(electronicReferral);
//        }
        //saveImageReferral(dto.getScannedReferralImageList());
        //saveImagePatientData(dto.getScannedPatientDataImageList());

    }
}
