/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.downloading;

import casemodule.dto.CaseDTO;
import casemodule.dto.DicomImageDTO;
import casemodule.dto.DicomPatientDataDTO;
import casemodule.dto.ProcessedDicomImage;
import casemodule.dto.ProcessedDicomSeries;
import casemodule.dto.ReferralInfoDTO;
import casemodule.dto.ScenarioInstanceDTO;
import casemodule.dto.SeriesDTO;
import casemodule.dto.StudyDTO;
import dicommodule.processing.DicomOptionsException;
import dicommodule.processing.NecessaryPictureAttributeMissingException;
import dicommodule.tdsdicomadapter.DicomAdapter;
import dicommodule.tdsdicomadapter.DicomInterfaceDBSide;
import event.DicomDataSetDownloadedEvent;
import event.ITSEventManager;
import hospitalmodule.dto.HospitalContractDTO;
import hospitalmodule.dto.HospitalDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import masterdatamodule.dto.BodyRegionDTO;
import masterdatamodule.dto.CaseStatusDTO;
import masterdatamodule.dto.ModalityDTO;
import masterdatamodule.dto.ModeOfAcquisitionDTO;
import masterdatamodule.dto.ScenarioDTO;
import pm.CaseFlagPM;
import reportingmodule.dto.CommentDTO;
import reportingmodule.dto.ReportingDTO;
import tdsdicominterface.DicomDataSet;
import tdsdicominterface.DicomManager;
import tdsdicominterface.exception.DIException;
import usermodule.dto.HospitalStaffDTO;

/**
 *
 * @author vincze.attila
 *
 */
public final class LocalCaseDownloader extends CaseDownloader {

    private volatile static LocalCaseDownloader instance;
    private File[] dicomFiles;
    private File[] iconFiles;
    private Properties initProperties;
    private Properties caseProperties;

    /**
     * A konstruktor inicializálja a változókat.
     * 
     */
    protected LocalCaseDownloader() {
        try {
            initProperties = new Properties();
            //initProperties.load(new FileInputStream((new File("f:\\DiagDance\\TDS\\TDSCaseDownloadingModule\\init.properties")).getAbsoluteFile()));
            initProperties.load(new FileInputStream((new File("f:\\DiagDance\\ITS\\sources\\ITSCaseDownloadingModule\\src\\main\\resources\\init.properties")).getAbsoluteFile()));
            //initProperties.load(new FileInputStream((new File("H:\\Personal\\Biznisz\\Rendszer\\Aktualis Rendszer\\TDSCaseDownloadingModule\\init.properties")).getAbsoluteFile()));
            File folder = new File(initProperties.getProperty("dicomdir"));
            System.out.println(folder.getAbsolutePath());

            caseProperties = new Properties();
            caseProperties.load(new FileInputStream(new File(folder.getAbsolutePath() + "\\tdscase.properties")));
            dicomFiles = (new File(folder.getAbsolutePath() + "\\dicom")).listFiles();
            iconFiles = (new File(folder.getAbsolutePath() + "\\icon")).listFiles();
            createMainCaseDTO();
            downloadDicomDataSets();
        } catch (IOException ex) {
            Logger.getLogger(LocalCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static synchronized LocalCaseDownloader getInstance() {
        if (null == instance) {
            instance = new LocalCaseDownloader();
        }
        return instance;
    }

    @Override
    public CaseDTO loadCase(String caseId, boolean dataOnly) {
        return tdsMainCase;
    }

    private void downloadDicomDataSets() {

        SwingWorker workerFileDownload = new SwingWorker() {

            @Override
            protected void done() {
                try {
                    get();
                } catch (InterruptedException ex) {
                    Logger.getLogger(LocalCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(LocalCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            protected Object doInBackground() throws Exception {
                for (int i = 0; i < dicomFiles.length; i++) {
                    Thread.sleep(500);
                    File file = dicomFiles[i];
                    DicomDataSet dicomDataSet = DicomManager.importFile(file);
                    addDicomDataSet(dicomDataSet);
                }
                Thread.sleep(1000);
                ITSEventManager.fireDicomDataSetDownloadedEvent(new DicomDataSetDownloadedEvent("Finished"));
                return null;
            }
        };
        workerFileDownload.execute();
    }

    private void addDicomDataSet(DicomDataSet dicomDataSet) throws DIException, NecessaryPictureAttributeMissingException, DicomOptionsException {
        for (StudyDTO studyDTO : tdsMainCase.getReferralInfoList().get(0).getStudyList()) {
            for (SeriesDTO seriesDTO : studyDTO.getSeriesList()) {
                for (DicomImageDTO dicomImageDTO : seriesDTO.getDicomImageList()) {
                    DicomInterfaceDBSide dicomIntf = new DicomAdapter(dicomDataSet);
                    if (dicomImageDTO.getInstanceUid().equals(dicomIntf.getUniqueId())) {
                        dicomImageDTO.setIconOnly(false);
                        dicomImageDTO.setDicomDataSet(dicomDataSet);
                        dicomImageDTO.setProcessedDicomImage(new ProcessedDicomImage(dicomDataSet));
                        System.out.println("downloaded: " + dicomImageDTO.getInstanceUid());
                        ITSEventManager.fireDicomDataSetDownloadedEvent(new DicomDataSetDownloadedEvent(dicomImageDTO));
                    }
                }
            }
        }
    }

    private void createMainCaseDTO() {
        tdsMainCase = new CaseDTO();
        tdsMainCase.setCaseStatus(new CaseStatusDTO(1, null, null));
        tdsMainCase.setModeOfAcquisition(new ModeOfAcquisitionDTO(1));
        tdsMainCase.setHospitalContract(new HospitalContractDTO(1));
        tdsMainCase.getHospitalContract().setHospital(new HospitalDTO(1));
        tdsMainCase.getHospitalContract().getHospital().setAbbrevName("HospAbbrevName");
        tdsMainCase.getHospitalContract().getHospital().setShortName("HospShortName");
        tdsMainCase.setSenderHospitalStaff(new HospitalStaffDTO(1));
        tdsMainCase.setTransferStarted(new Date());
        tdsMainCase.setTransferredToServer(new Date());
        Format formatter = new SimpleDateFormat("yyyy.MM.dd.hhmmssSSS");
        //System.out.println(formatter.format(new Date()));
        tdsMainCase.setTdsCaseUniqueId(
                tdsMainCase.getHospitalContract().getHospital().getAbbrevName()
                + formatter.format(tdsMainCase.getTransferredToServer()));
        tdsMainCase.getReferralInfoList().add(new ReferralInfoDTO());
        tdsMainCase.getReferralInfoList().get(0).setStudyList(new ArrayList<StudyDTO>());
        tdsMainCase.getReferralInfoList().get(0).setCaseItBelongsTo(tdsMainCase);
        //tdsMainCase.setStudyList(new ArrayList<StudyDTO>());
//        tdsMainCase.setPatientAndReferralInfo(new PatientAndReferralInfoDTO("nondicomid"));
//        tdsMainCase.getPatientAndReferralInfo().setAgreementTest(CaseAgreementTest.ok.name());
//        tdsMainCase.getPatientAndReferralInfo().setAgreementTestDateTime(new Date());
        Calendar c = Calendar.getInstance();
        c.setTime(tdsMainCase.getTransferredToServer());
        c.add(Calendar.DATE, 1);
        tdsMainCase.setDeadLine(c.getTime());

        ScenarioInstanceDTO scenarioInstanceDTO = new ScenarioInstanceDTO(1);
        try {
            scenarioInstanceDTO.setAdded(new SimpleDateFormat("yyyy.MM.dd. hh:mm:ss").parse("2010.12.08. 11:11:11"));
            scenarioInstanceDTO.setScenario(new ScenarioDTO(1, "e1", "h1"));
            scenarioInstanceDTO.setTdsCase(tdsMainCase);
            scenarioInstanceDTO.setNote("note1");
        } catch (ParseException ex) {
            Logger.getLogger(LocalCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(scenarioInstanceDTO.getAdded());
        tdsMainCase.getScenarioInstanceList().add(scenarioInstanceDTO);

        scenarioInstanceDTO = new ScenarioInstanceDTO(2);
        try {
            scenarioInstanceDTO.setAdded(new SimpleDateFormat("yyyy.MM.dd. hh:mm:ss").parse("2010.12.07. 11:11:11"));
            scenarioInstanceDTO.setScenario(new ScenarioDTO(3, "e3", "h3"));
            scenarioInstanceDTO.setTdsCase(tdsMainCase);
            scenarioInstanceDTO.setNote("note3");
        } catch (ParseException ex) {
            Logger.getLogger(LocalCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(scenarioInstanceDTO.getAdded());
        tdsMainCase.getScenarioInstanceList().add(scenarioInstanceDTO);

        ReportingDTO reportingDTO = new ReportingDTO();
        reportingDTO.setCaseItBelongsTo(tdsMainCase);
        reportingDTO.setActive(true);
        reportingDTO.setUnfinishedText("Ez itt a diagnózis első verziója");
        tdsMainCase.getReportingList().add(reportingDTO);

//        scenarioInstanceDTO = new ScenarioInstanceDTO(1);
//        try {
//            scenarioInstanceDTO.setAdded(new SimpleDateFormat("yyyy.MM.dd.").parse("2010.12.05."));
//            scenarioInstanceDTO.setScenario(new ScenarioDTO(2));
//            scenarioInstanceDTO.setTdsCase(tdsMainCase);
//            scenarioInstanceDTO.setNote("note2");
//        } catch (ParseException ex) {
//            Logger.getLogger(LocalCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        tdsMainCase.getScenarioInstanceList().add(scenarioInstanceDTO);


        tdsMainCase.setDicomImageIds(new Long[dicomFiles.length]);
        for (int i = 0; i < dicomFiles.length; i++) {
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                DateFormat timeFormat = new SimpleDateFormat("hhmmss");
                StudyDTO study = null;
                SeriesDTO series = null;
                DicomImageDTO dicomImage = null;
                File file = dicomFiles[i];
                DicomDataSet dicomDataSet = DicomManager.importFile(file);
                DicomInterfaceDBSide dicomIntf = new DicomAdapter(dicomDataSet);
                System.out.println("Processing: " + dicomIntf.getUniqueId());

                //saving dicompatientdata
                if (tdsMainCase.getDicomPatientData() == null) {
                    System.out.println("saving dicompatientdata");
                    DicomPatientDataDTO dicomPatientData = new DicomPatientDataDTO(1);
                    dicomPatientData.setComments(dicomIntf.getPatientComments());
                    if (dicomIntf.getPatientDoBYYYYMMDD() != null) {
                        try {
                            dicomPatientData.setDob(dateFormat.parse(dicomIntf.getPatientDoBYYYYMMDD()));
                        } catch (ParseException ex) {
                            Logger.getLogger(LocalCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    dicomPatientData.setIssuerOfPatientId(dicomIntf.getIssuerOfPatientID());
                    dicomPatientData.setMothersBirthName(dicomIntf.getPatientsMothersBirthName());
                    dicomPatientData.setOtherPatientId(dicomIntf.getOtherPatientIDs());
                    dicomPatientData.setOtherPatientIdRecords(dicomIntf.getOtherPatientIDRecords());
                    dicomPatientData.setOtherPatientNames(dicomIntf.getOtherDICOMPatientNames());
                    dicomPatientData.setPatientId(dicomIntf.getDICOMPatientID());
                    dicomPatientData.setPatientName(dicomIntf.getDICOMPatientName());
                    dicomPatientData.setSex(dicomIntf.getPatientSex());
                    dicomPatientData.setTypeOfPatientId(dicomIntf.getTypeOfPatientID());
                    tdsMainCase.setDicomPatientData(dicomPatientData);
                }

                //saving study
                String tdsStudyInstanceUid = dicomIntf.getStudyInstanceUID();
                for (StudyDTO studyDTO : tdsMainCase.getReferralInfoList().get(0).getStudyList()) {
                    if (tdsStudyInstanceUid.equals(studyDTO.getInstanceUid())) {
                        study = studyDTO;
                        //break;
                    }
                }
                if (study == null) {
                    System.out.println("saving study");
                    study = new StudyDTO();
                    study.setSeriesList(new ArrayList<SeriesDTO>());
                    study.setReferralInfo(tdsMainCase.getReferralInfoList().get(0));
                    study.setAdditionalPatientHistory(dicomIntf.getAdditionalPatientHistory());
                    study.setAdmittingDiagnoseDescription(dicomIntf.getAdditionalPatientHistory());
                    study.setDescription(dicomIntf.getStudyDescription());
                    study.setInstanceUid(dicomIntf.getStudyInstanceUID());
                    study.setPatientMedicalAlerts(dicomIntf.getPatientMedicalAlerts());
                    study.setReferringPhysyciansDicomName(dicomIntf.getReferringPhysyciansDICOMName());
                    study.setSmokingStatus(dicomIntf.getSmokingStatus());
                    if (dicomIntf.getStudyDateYYYYMMDD() != null) {
                        try {
                            study.setStudyDate(dateFormat.parse(dicomIntf.getStudyDateYYYYMMDD()));
                        } catch (ParseException ex) {
                            Logger.getLogger(LocalCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    study.setStudyId(dicomIntf.getStudyID());
                    if (dicomIntf.getStudyTimeHHMMSS() != null) {
                        try {
                            study.setStudyTime(timeFormat.parse(dicomIntf.getStudyTimeHHMMSS()));
                        } catch (ParseException ex) {
                            Logger.getLogger(LocalCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    tdsMainCase.getReferralInfoList().get(0).getStudyList().add(study);
                }

                //saving series
                String seriesInstanceUid = dicomIntf.getSeriesInstanceUID();
                for (SeriesDTO ser : study.getSeriesList()) {
                    if (seriesInstanceUid.equals(ser.getInstanceUid())) {
                        series = ser;
                        //break;
                    }
                }
                if (series == null) {
                    System.out.println("saving series");
                    series = new SeriesDTO();
                    series.setDicomImageList(new ArrayList<DicomImageDTO>());
                    series.setStudy(study);
                    series.setModality(new ModalityDTO(1, dicomIntf.getModality()));
                    series.setHospitalBodyRegion(new BodyRegionDTO(1, dicomIntf.getBodyPartExamined(), "", "", 1));
                    series.setDescription(dicomIntf.getSeriesDescription());
                    series.setInstanceUid(dicomIntf.getSeriesInstanceUID());
                    series.setEquipmentDeviceSerialNumber(dicomIntf.getEquipmentDeviceSerialNumber());
                    series.setBodyPartExamined(dicomIntf.getBodyPartExamined());
                    series.setClinicalTrialSeriesDescription(dicomIntf.getClinicalTrialSeriesDescription());
                    series.setClinicalTrialSeriesId(dicomIntf.getClinicalTrialSeriesID());
                    if (dicomIntf.getEquipmentDateOfLastCalibrationYYYYMMDD() != null) {
                        try {
                            series.setEquipmentDateOfLastCalibration(dateFormat.parse(dicomIntf.getEquipmentDateOfLastCalibrationYYYYMMDD()));
                        } catch (ParseException ex) {
                            Logger.getLogger(LocalCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    series.setEquipmentDeviceSerialNumber(dicomIntf.getEquipmentDeviceSerialNumber());
                    series.setEquipmentInstitutionalDepartmentName(dicomIntf.getEquipmentInstitutionalDepartmentName());
                    series.setEquipmentLocationInstitutionName(dicomIntf.getEquipmentLocationInstitutionName());
                    series.setEquipmentManufacturer(dicomIntf.getEquipmentManufacturer());
                    series.setEquipmentManufacturersModelName(dicomIntf.getEquipmentManufacturersModelName());
                    series.setEquipmentStationName(dicomIntf.getEquipmentStationName());
                    if (dicomIntf.getEquipmentTimeOfLastCalibrationHHMMSS() != null) {
                        try {
                            series.setEquipmentTimeOfLastCalibration(timeFormat.parse(dicomIntf.getEquipmentTimeOfLastCalibrationHHMMSS()));
                        } catch (ParseException ex) {
                            Logger.getLogger(LocalCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    series.setFrameOfReferenceUid(dicomIntf.getFrameOfReferenceUID());
                    series.setLaterality(dicomIntf.getLaterality());
                    series.setOperatorsDicomName(dicomIntf.getOperatorsDICOMName());
                    series.setPerformingPhysiciansDicomName(dicomIntf.getPerformingPhysiciansDICOMName());
                    series.setProtocolName(dicomIntf.getProtocolName());
                    if (dicomIntf.getSeriesDateYYYYMMDD() != null) {
                        try {
                            series.setSeriesDate(dateFormat.parse(dicomIntf.getSeriesDateYYYYMMDD()));
                        } catch (ParseException ex) {
                            Logger.getLogger(LocalCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    series.setSeriesNumberState(dicomIntf.getSeriesNumber().isEmpty());
                    series.setSeriesNumberValue(dicomIntf.getSeriesNumber().value);
                    if (dicomIntf.getSeriesTimeHHMMSS() != null) {
                        try {
                            series.setSeriesTime(timeFormat.parse(dicomIntf.getSeriesTimeHHMMSS()));
                        } catch (ParseException ex) {
                            Logger.getLogger(LocalCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    study.getSeriesList().add(series);
                }

                //saving dicomimage
                System.out.println("saving dicomimagedataset");
                String dicomDataSetInstanceUid = dicomIntf.getUniqueId();
                dicomImage = new DicomImageDTO();
                dicomImage.setDicomUniqueId(dicomDataSetInstanceUid);
                dicomImage.setSeries(series);
                dicomImage.setDicomDataSetIcon(dicomDataSet);
                dicomImage.setProcessedDicomImageIcon(new ProcessedDicomImage(dicomDataSet));
                dicomImage.setIconOnly(true);
                dicomImage.setInstanceNumber(dicomIntf.getInstanceNumber());
                dicomImage.setSopAuthorizationComment(dicomIntf.getSOPAuthorizationComment());
                dicomImage.setSopAuthorizationDateTime(dicomIntf.getSOPAuthorizationDateTimeYYYYMMDDHHMMSSFFFFFFplusOptionalSZZZZ());
                dicomImage.setSopClassUid(dicomIntf.getSOPClassUID());
                dicomImage.setSopInstanceStatus(dicomIntf.getSOPInstanceStatus());
                dicomImage.setInstanceUid(dicomIntf.getSOPInstanceUID());
                dicomImage.setTimeZoneOffsetFromUtc(dicomIntf.getTimezoneOffsetFromUTC());
                series.getDicomImageList().add(dicomImage);
            } catch (DIException ex) {
                Logger.getLogger(LocalCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LocalCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NecessaryPictureAttributeMissingException e) {
                Logger.getLogger(LocalCaseDownloader.class.getName()).log(Level.SEVERE, null, e);
            } catch (DicomOptionsException e) {
                Logger.getLogger(LocalCaseDownloader.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        for (StudyDTO studyDTO : tdsMainCase.getReferralInfoList().get(0).getStudyList()) {
            for (SeriesDTO seriesDTO : studyDTO.getSeriesList()) {
                List<DicomDataSet> dicomDataSetList = new ArrayList<DicomDataSet>();
                for (DicomImageDTO dicomImageDTO : seriesDTO.getDicomImageList()) {
                    dicomDataSetList.add(dicomImageDTO.getDicomDataSetIcon());
                }
                seriesDTO.setProcessedDicomSeries(new ProcessedDicomSeries(dicomDataSetList));
            }
        }
    }

    @Override
    public void startScenario(ScenarioInstanceDTO dto) {
        dto.setId(tdsMainCase.getScenarioInstanceList().size() + 1);
        dto.setTdsCase(tdsMainCase);
        tdsMainCase.getScenarioInstanceList().add(dto);
    }

    @Override
    public void deactivateScenario(ScenarioInstanceDTO dto) {
        for (ScenarioInstanceDTO scenarioInstanceDTO : tdsMainCase.getScenarioInstanceList()) {
            if (scenarioInstanceDTO.getScenario().getId() == dto.getScenario().getId() && scenarioInstanceDTO.getDeactivated() == null) {
                scenarioInstanceDTO.setDeactivated(dto.getDeactivated());
            }
        }
    }

    @Override
    public List<ScenarioDTO> getScenarioList() {
        List<ScenarioDTO> result = new ArrayList<ScenarioDTO>();
        result.add(new ScenarioDTO(1, "e1", "h1", true, "c1", 1));
        result.add(new ScenarioDTO(2, "e2", "h2", true, "c2", 1));
        result.add(new ScenarioDTO(3, "e3", "h3", true, "c3", 2));
        result.add(new ScenarioDTO(4, "e4", "h4", true, "c4", 2));

        for (ScenarioInstanceDTO scenarioInstanceDTO : tdsMainCase.getScenarioInstanceList()) {
            ScenarioDTO scenarioDTO = result.get(result.indexOf(scenarioInstanceDTO.getScenario()));
            scenarioDTO.setNote(scenarioInstanceDTO.getNote());
            scenarioDTO.setStarted(true);
        }
        return result;
    }

    @Override
    public void saveReport() {
        //tdsMainCase.getReportingList().get(0).setUnfinishedText(reportText);
    }

    @Override
    public void doneReport() {
        tdsMainCase.getReportingList().get(0).setDone(new Date());
    }

    @Override
    public String getReportText() {
        return tdsMainCase.getReportingList().get(0).getUnfinishedText();
    }

    @Override
    public void confirmReport(List<SeriesDTO> seriesList, String note) {
        tdsMainCase.getReportingList().get(0).setDoneConfirmed(new Date());
    }

    @Override
    public List<CaseFlagPM> getCaseFlagPMList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void closeCase(String note) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void loadCase() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveComment(CommentDTO dto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
