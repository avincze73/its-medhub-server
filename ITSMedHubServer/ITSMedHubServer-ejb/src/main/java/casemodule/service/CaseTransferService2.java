/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.service;

import casemodule.assembler.CaseAssembler;
import casemodule.dto.CaseAgreementTest;
import casemodule.dto.CaseUrgency;
import casemodule.dto.DataProcLogEntryType;
import casemodule.dto.ScannedPatientDataImageDTO;
import casemodule.dto.ScannedReferralImageDTO;
import casemodule.entity.DataProcLog;
import casemodule.entity.DataProcLogEntry;
import casemodule.entity.DicomImage;
import casemodule.entity.DicomPatientData;
import casemodule.entity.ElectronicPatientData;
import casemodule.entity.ElectronicReferral;
import casemodule.entity.HistoryCase;
import casemodule.entity.ImagePatientData;
import casemodule.entity.ImageReferral;
import casemodule.entity.ReferralInfo;
import casemodule.entity.ScannedPatientDataImage;
import casemodule.entity.ScannedReferralImage;
import casemodule.entity.Series;
import casemodule.entity.TDSCase;
import casemodule.entity.TDSStudy;
import common.service.CaseAssignmentService;
import dicommodule.tdsdicomadapter.DicomAdapter;
import dicommodule.tdsdicomadapter.DicomInterfaceDBSide;
import hospitalmodule.entity.HospitalContract;
import hospitalmodule.repository.HospitalContractRepository;
import integrationmodule.entity.ITSCasePackage;
import integrationmodule.entity.PatientDataFile;
import integrationmodule.entity.ReferralFile;
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
import masterdatamodule.entity.Modality;
import masterdatamodule.entity.ModeOfAcquisition;
import masterdatamodule.repository.CaseStatusRepository;
import tdsdicomimage.exception.DImgException;
import tdsdicominterface.DicomDataSet;
import tdsdicominterface.exception.DIException;
import usermodule.entity.HospitalStaff;
import usermodule.repository.HospitalStaffRepository;

/**
 *
 * @author vincze.attila
 */
@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 60)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CaseTransferService2 implements CaseTransferService2Remote {

    protected org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass().getName());
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
    private TDSCase itsCase;
    private HistoryCase historyCase;
    private int studyNumberInList;
    private int seriesNumberInList;

    public CaseTransferService2() {
        dicomUniqueIds = new HashSet<String>();
        studyNumberInList = 0;
        seriesNumberInList = 0;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public void startCaseTransfer(ITSCasePackage itsCasePackage) {
        //dicomUniquIdsArray = itsCasePackage.getDicomUniqueIds();
        ElectronicPatientData electronicPatientData = null;
        ElectronicReferral electronicReferral = null;
        //for (String s : itsCasePackage.getDicomUniqueIds()) {
        //    System.out.println(s);
        //}
        itsCase = new TDSCase();
        itsCase.setHospitalCaseHistoryList(new ArrayList<HistoryCase>());
        //itsCase.setHospitalStaff(hospitalStaffRepository.findByLoginName(ctx.getCallerPrincipal().getName()));
        itsCase.setHospitalStaff(new HospitalStaff(Long.valueOf(1)));
        itsCase.setCaseStatus(caseStatusRepository.findByEnglishName("received"));
        String strJQL = "select moa from ModeOfAcquisition moa "
                + "where moa.name like :name";
        itsCase.setModeOfAcquisition(em.createQuery(strJQL, ModeOfAcquisition.class).setParameter("name", "All manual upload from file sys").getSingleResult());
        //Long hospitalContractId = getHospitalContractId(itsCase.getHospitalStaff().getId());
        //itsCase.setHospitalContract(hospitalContractRepository.find(hospitalContractId));
        itsCase.setHospitalContract(new HospitalContract(Long.valueOf(1)));
        double normalWorkTimeDays = itsCase.getHospitalContract().getNormalWorkTimeDays();
        Calendar deadLine = Calendar.getInstance();
        deadLine.add(Calendar.HOUR, (int) (normalWorkTimeDays * 24));
        itsCase.setDeadLine(deadLine.getTime());
        //itsCase.setNonDicomCaseIdInHospital(itsCasePackage.getNonDicomCaseIdInHospital());
        itsCase.setNonDicomCaseIdInHospital1Name(itsCasePackage.getNonDicomCaseIdInHospital1Name());
        itsCase.setNonDicomCaseIdInHospital1ShortName(itsCasePackage.getNonDicomCaseIdInHospital1ShortName());
        itsCase.setNonDicomCaseIdInHospital1Value(itsCasePackage.getNonDicomCaseIdInHospital1Value());
        itsCase.setNonDicomCaseIdInHospital2Name(itsCasePackage.getNonDicomCaseIdInHospital2Name());
        itsCase.setNonDicomCaseIdInHospital2ShortName(itsCasePackage.getNonDicomCaseIdInHospital2ShortName());
        itsCase.setNonDicomCaseIdInHospital2Value(itsCasePackage.getNonDicomCaseIdInHospital2Value());
        itsCase.setTransferStarted(new Date());
        itsCase.setAgreementTest(CaseAgreementTest.ok.name());
        itsCase.setAgreementTestDateTime(new Date());
        itsCase.setTransferredToServer(new Date());
        itsCase.setUrgency(CaseUrgency.normal.name());
        //itsCase.setTdsCaseUniqueId(
        //        itsCase.getHospitalContract().getHospital().getAbbrevName()
        //        + new SimpleDateFormat("yyyy.MM.dd.hhmmssSSS").format(itsCase.getTransferredToServer()));
        itsCase.setTdsCaseUniqueId("its case unique id" + new Date());
        itsCase.setReferralInfoCollection(new ArrayList<ReferralInfo>());
        itsCase.getReferralInfoCollection().add(new ReferralInfo());
        itsCase.getReferralInfoCollection().get(0).setTDSCase(itsCase);
        itsCase.getReferralInfoCollection().get(0).settDSStudyCollection(new ArrayList<TDSStudy>());

        System.out.println("checking getElectronicPatientData");
        if (!"".equals(itsCasePackage.getElectronicPatientDataPackage().getPatientIdInHospital())) {
            electronicPatientData = CaseAssembler.toEntity(itsCasePackage.getElectronicPatientDataPackage());
            System.out.println("getElectronicPatientData");
            em.persist(electronicPatientData);
            itsCase.setElectronicPatientData(electronicPatientData);
        }
        System.out.println("checking getElectronicReferral");
        if (!"".equals(itsCasePackage.getElectronicReferralPackage().getReferralText())) {
            electronicReferral = CaseAssembler.toEntity(itsCasePackage.getElectronicReferralPackage());
            System.out.println("getElectronicReferral");
            em.persist(electronicReferral);
            itsCase.getReferralInfoCollection().get(0).setElectronicReferral(electronicReferral);
        }
        //saveImageReferral(itsCasePackage.getScannedReferralImageList());
        //saveImagePatientData(itsCasePackage.getScannedPatientDataImageList());

        DataProcLog dataProcLog = new DataProcLog();
        dataProcLog.setStarted(new Date());
        dataProcLog.setTDSCase(itsCase);
       itsCase.setDataProcLog(dataProcLog);
       em.persist(dataProcLog);
       em.persist(itsCase);
       //em.flush();

        //dataProcLogService.saveEntry(itsCase.getDataProcLog(), DataProcLogEntryType.InsertedToDatabase, "TDSCase [id]: " + itsCase.getTdsCaseUniqueId());

        //em.persist(itsCase);
        //dataProcLogService.saveEntry(itsCase.getDataProcLog(), DataProcLogEntryType.InsertedToDatabase,
        //        "TDSCase", null, itsCase.getTdsCaseUniqueId(), null, null);
        DataProcLogEntry dataProcLogEntry = new DataProcLogEntry();
        dataProcLogEntry.setEntryGeneration(new Date());
        dataProcLogEntry.setEntryType(DataProcLogEntryType.InsertedToDatabase.name());
        dataProcLogEntry.setDataProcLog(itsCase.getDataProcLog());
        dataProcLogEntry.setSender(ctx.getCallerPrincipal().getName());
        dataProcLogEntry.setTableName("TDSCase");
        dataProcLogEntry.setRecordUid(itsCase.getTdsCaseUniqueId());
       em.persist(dataProcLogEntry);
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

    
    @Override
    
    //@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void init() {
        log.info(new Date());
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Remove
    public void commitCaseTransfer() {
        System.out.println("commitCaseTransfer");
        itsCase.setTransferredToServer(new Date());
        //calculateSeriesMinAndMaxRawValue();
        //doAgreementTest();
        assignCaseToRadiologistRandomly();
        em.merge(itsCase);
        
    }
    
    private void assignCaseToRadiologistRandomly() {
        if (!caseAssignmentService.assignCaseToRadiologistRandomly(itsCase)) {

            dataProcLogService.saveEntry(
                    itsCase.getDataProcLog(),
                    DataProcLogEntryType.AutomaticModification,
                    "TDSCase", "caseStatus", itsCase.getTdsCaseUniqueId(), itsCase.getCaseStatus().getEnglishName(),
                    "waiting - normal");
            itsCase.setCaseStatus(caseStatusRepository.findByEnglishName("waiting - normal"));
        }
    }
    
    @Override
    public void saveScannedReferralImage(ReferralFile image) {
        System.out.println("saveImageReferral");
        if (itsCase.getImageReferral() == null) {
            ImageReferral imageReferral = new ImageReferral();
            imageReferral.setScannedReferralImageList(new ArrayList<ScannedReferralImage>());
            itsCase.setImageReferral(imageReferral);
            //em.persist(imageReferral);
        }
        ScannedReferralImage scannedReferralImage = CaseAssembler.toEntity(image);
        itsCase.getImageReferral().getScannedReferralImageList().add(scannedReferralImage);
        //scannedReferralImage.setImageReferral(itsCase.getReferralInfoCollection().get(0).getImageReferral());
        //em.persist(scannedReferralImage);
    }
    
    @Override
    public void saveScannedPatientDataImage(PatientDataFile image) {
        System.out.println("saveImagePatientData");
        if (itsCase.getImagePatientData() == null) {
            ImagePatientData imagePatientData = new ImagePatientData();
            imagePatientData.setScannedPatientDataImageList(new ArrayList<ScannedPatientDataImage>());
            itsCase.setImagePatientData(imagePatientData);
            
            //em.persist(imagePatientData);
            //itsCase.setImagePatientData(imagePatientData);
        }
        ScannedPatientDataImage scannedPatientDataImage = CaseAssembler.toEntity(image);
        itsCase.getImagePatientData().getScannedPatientDataImageList().add(scannedPatientDataImage);
        //scannedPatientDataImage.setImagePatientData(itsCase.getImagePatientData());
        //em.persist(scannedPatientDataImage);
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
        BodyRegion bodyRegion = em.createQuery("SELECT br from BodyRegion br where br.id = :id", BodyRegion.class).setParameter("id", (long) 2).getSingleResult();
        try {
            bodyRegion =
                    em.createQuery(strJQL, BodyRegion.class).setParameter("dicomName", dicomName).getSingleResult();
        } catch (NoResultException ex) {
            //Logger.getLogger(CaseTransferService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bodyRegion;
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
                if (itsCase.getDicomPatientData() == null) {
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
                    itsCase.setDicomPatientData(dicomPatientData);
                    //em.persist(dicomPatientData);
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
                    //em.persist(dicomPatientData);
                }
            }

            //saving study
            if (historyCase == null) {
                for (TDSStudy tDSStudy : itsCase.getReferralInfoCollection().get(0).gettDSStudyCollection()) {
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
                    tdsStudy.setReferralInfo(itsCase.getReferralInfoCollection().get(0));
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
                    itsCase.getReferralInfoCollection().get(0).gettDSStudyCollection().add(tdsStudy);
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
    
}
