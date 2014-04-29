/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.downloading;

import casemodule.dto.CaseDTO;
//import casemodule.dto.DicomImageDataSetDTO;
//import casemodule.dto.DicomImageDataSetDTO;
//import casemodule.dto.DicomImageDataSetDTO;
import casemodule.dto.DicomImageDTO;
import casemodule.dto.FlagAssignmentDTO;
import casemodule.dto.ProcessedDicomImage;
import casemodule.dto.ScenarioInstanceDTO;
import casemodule.dto.SeriesDTO;
import casemodule.dto.StudyDTO;
import casemodule.service.CaseServiceRemote;
import casemodule.service.RcCaseServiceRemote;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import dicommodule.processing.DicomOptionsException;
import dicommodule.processing.NecessaryPictureAttributeMissingException;
import event.DicomDataSetDownloadedEvent;
import event.ITSEventManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.SwingWorker;
import masterdatamodule.dto.CaseFlagDTO;
import masterdatamodule.dto.ScenarioDTO;
import masterdatamodule.service.CaseFlagServiceRemote;
import masterdatamodule.service.ScenarioServiceRemote;
import pm.CaseFlagPM;
import reportingmodule.dto.CommentDTO;
import reportingmodule.service.ReportingServiceRemote;
import tdsdicominterface.exception.DIException;

/**
 * @author vincze.attila
 * Szingleton osztály, ezen keresztül lehet elérni a letöltött eseteket.
 * A tdsCase tartalmazza az esetlistán megnyitott esetet.
 * A tdsCaseReference1 és tdsCaseReference2 a megnyitott eset mellé letöltött
 * referencia eseteket tartalmazza.
 * A tdsCaseNext egy következő esetet tartalmazhat, amennyiben a felhasználói
 * felületen kezdeményezik a letöltésést.
 * Az osztály egyetlen példányát a getInstance() metóduson keresztül lehet elérni.
 * Ezen osztály által kiváltott események kezelése megfigyelhető a TDSRadiologistClient
 * view.CaseDetailView osztályában.
 */
public class RemoteCaseDownloader extends CaseDownloader {

    private volatile static RemoteCaseDownloader instance;

    protected RemoteCaseDownloader() {
        //loadCase("2");
    }

    public static synchronized RemoteCaseDownloader getInstance() {
        if (null == instance) {
            instance = new RemoteCaseDownloader();
        }
        return instance;
    }

    /**
     * @param caseId Ezen azonosítójú esetet tölti le ez objektum.
     */
    @Override
    public final CaseDTO loadCase(String caseId, boolean dataOnly) {
        try {
            //System.setProperty("org.omg.CORBA.ORBInitialHost", "diag-1.site");
            CaseServiceRemote service =
                    (CaseServiceRemote) new InitialContext().lookup("casemodule.service.CaseServiceRemote");
            tdsMainCase = service.getCase(Integer.parseInt(caseId));
            if (!dataOnly) {
                downloadDicomDataSetIcons(String.valueOf(tdsMainCase.getId()), DownloaderCaseType.main);
            }
        } catch (NamingException ex) {
            Logger.getLogger(RemoteCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tdsMainCase;
    }

    private void addDicomDataSet(DicomImageDTO dicomImage, CaseDTO caseDTO, boolean isIcon) throws DIException, NecessaryPictureAttributeMissingException, DicomOptionsException {
        for (StudyDTO studyDTO : caseDTO.getReferralInfoList().get(0).getStudyList()) {
            for (SeriesDTO seriesDTO : studyDTO.getSeriesList()) {
                for (DicomImageDTO dicomImageDTO : seriesDTO.getDicomImageList()) {
                    if (dicomImageDTO.getId() == dicomImage.getId()) {
                        if (isIcon) {
                            dicomImageDTO.setDicomDataSetIcon(dicomImage.getDicomDataSetIcon());
                            dicomImageDTO.setProcessedDicomImageIcon(new ProcessedDicomImage(dicomImage.getDicomDataSetIcon()));
                        } else {
                            dicomImageDTO.setIconOnly(false);
                            dicomImageDTO.setDicomDataSet(dicomImage.getDicomDataSet());
                        }
                    }
                }
            }
        }
    }

    /**
     * Új szál elindításával elkezdi letölteni a paraméterben megadott esethez
     * tartozó dicomDataSetIcon-okat.
     * @param caseType enum, amely megmutatja, hogy hova melyik attribútumba
     * tölti le a dicomDataSet-eket.
     * Amennyiben az aktuális dicomdataseticon letöltése befejeződött, kiváltódik
     * egy esemény, amire fel kell iratkozni, ha értesítést kér valamely objektum
     * erről.
     */
    private void downloadDicomDataSetIcons(final String caseId, final DownloaderCaseType caseType) {
        SwingWorker workerFileDownload = new SwingWorker() {

            @Override
            protected void done() {
                try {
                    get();
                } catch (InterruptedException ex) {
                    Logger.getLogger(RemoteCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(RemoteCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            protected Object doInBackground() throws Exception {
                CaseServiceRemote service =
                        (CaseServiceRemote) new InitialContext().lookup("casemodule.service.CaseServiceRemote");
                for (Long id : tdsMainCase.getDicomImageIds()) {
                    DicomImageDTO dicomImage = service.getDicomDataSetIcon(id);
                    switch (caseType) {
                        case main:
                            addDicomDataSet(dicomImage, tdsMainCase, true);
                            break;
                    }
                    ITSEventManager.fireDicomDataSetDownloadedEvent(new DicomDataSetDownloadedEvent(id));
                    System.out.println("transfering icon: " + dicomImage.getDicomUniqueId());
                }
                ITSEventManager.fireDicomDataSetDownloadedEvent(new DicomDataSetDownloadedEvent("Finished"));
                downloadDicomDataSets(caseId, DownloaderCaseType.main);
                return null;
            }
        };
        workerFileDownload.execute();
    }

    /**
     * Új szál elindításával elkezdi letölteni a paraméterben megadott esethez
     * tartozó dicomDataSet-eket.
     * @param caseType enum, amely megmutatja, hogy hova melyik attribútumba
     * tölti le a dicomDataSet-eket.
     * Amennyiben az aktuális dicomdataseticon letöltése befejeződött, kiváltódik
     * egy esemény, amire fel kell iratkozni, ha értesítést kér valamely objektum
     * erről.
     */
    private void downloadDicomDataSets(String caseId, final DownloaderCaseType caseType) {
        SwingWorker workerFileDownload = new SwingWorker() {

            @Override
            protected void done() {
                try {
                    get();
                } catch (InterruptedException ex) {
                    Logger.getLogger(RemoteCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(RemoteCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            protected Object doInBackground() throws Exception {
                CaseServiceRemote service =
                        (CaseServiceRemote) new InitialContext().lookup("casemodule.service.CaseServiceRemote");
                for (Long id : tdsMainCase.getDicomImageIds()) {
                    DicomImageDTO dicomImage = service.getDicomDataSet(id);
                    switch (caseType) {
                        case main:
                            addDicomDataSet(dicomImage, tdsMainCase, false);
                            break;
                    }
                    ITSEventManager.fireDicomDataSetDownloadedEvent(new DicomDataSetDownloadedEvent(dicomImage));
                    System.out.println("transfering: " + dicomImage.getDicomUniqueId());
                }
                return null;
            }
        };
        workerFileDownload.execute();
    }

    private List<DicomImageDTO> getDicomImages() {
        List<DicomImageDTO> ret = new ArrayList<DicomImageDTO>();
        for (StudyDTO studyDTO : tdsMainCase.getReferralInfoList().get(0).getStudyList()) {
            for (SeriesDTO seriesDTO : studyDTO.getSeriesList()) {
                for (DicomImageDTO dicomImageDTO : seriesDTO.getDicomImageList()) {
                    ret.add(dicomImageDTO);
                }
            }
        }
        return ret;
    }

    @Override
    public List<ScenarioDTO> getScenarioList() {
        List<ScenarioDTO> result = new ArrayList<ScenarioDTO>();

        try {
            ScenarioServiceRemote service =
                    (ScenarioServiceRemote) new InitialContext().lookup("masterdatamodule.service.ScenarioServiceRemote");
            result.addAll(service.getAllForRadiologist());
        } catch (NamingException ex) {
            Logger.getLogger(RemoteCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (ScenarioInstanceDTO scenarioInstanceDTO : tdsMainCase.getScenarioInstanceList()) {
            ScenarioDTO scenarioDTO = result.get(result.indexOf(scenarioInstanceDTO.getScenario()));
            scenarioDTO.setNote(scenarioInstanceDTO.getNote());
            scenarioDTO.setStarted(true);
        }


        return result;
    }

    @Override
    public void startScenario(ScenarioInstanceDTO dto) {
        try {
            dto.setTdsCase(new CaseDTO(tdsMainCase.getId()));
            tdsMainCase.getScenarioInstanceList().add(dto);
            CaseServiceRemote service =
                    (CaseServiceRemote) new InitialContext().lookup("casemodule.service.CaseServiceRemote");
            long id = service.saveScenarioInstance(dto);
            dto.setId(id);
        } catch (NamingException ex) {
            Logger.getLogger(RemoteCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deactivateScenario(ScenarioInstanceDTO dto) {
        for (ScenarioInstanceDTO scenarioInstanceDTO : tdsMainCase.getScenarioInstanceList()) {
            if (scenarioInstanceDTO.getScenario().getId() == dto.getScenario().getId() && scenarioInstanceDTO.getDeactivated() == null) {
                scenarioInstanceDTO.setDeactivated(dto.getDeactivated());
                scenarioInstanceDTO.setNote(dto.getNote());
                try {
                    dto.setTdsCase(new CaseDTO(tdsMainCase.getId()));
                    CaseServiceRemote service =
                            (CaseServiceRemote) new InitialContext().lookup("casemodule.service.CaseServiceRemote");
                    service.saveScenarioInstance(scenarioInstanceDTO);
                } catch (NamingException ex) {
                    Logger.getLogger(RemoteCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void saveReport() {
        try {
            //tdsMainCase.getReportingList().get(0).setUnfinishedText(reportText);
            ReportingServiceRemote service =
                    (ReportingServiceRemote) new InitialContext().lookup("reportingmodule.service.ReportingServiceRemote");
            service.save(tdsMainCase.getReportingList().get(0));
        } catch (NamingException ex) {
            Logger.getLogger(RemoteCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void doneReport() {
        try {
            tdsMainCase.getReportingList().get(0).setDone(new Date());
            ReportingServiceRemote service =
                    (ReportingServiceRemote) new InitialContext().lookup("reportingmodule.service.ReportingServiceRemote");
            service.done(tdsMainCase.getReportingList().get(0));
        } catch (NamingException ex) {
            Logger.getLogger(RemoteCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getReportText() {
        return tdsMainCase.getReportingList().get(0).getUnfinishedText();
    }

    @Override
    public void confirmReport(List<SeriesDTO> seriesList, String note) {
        try {
            tdsMainCase.getReportingList().get(0).setDoneConfirmed(new Date());
            ReportingServiceRemote service =
                    (ReportingServiceRemote) new InitialContext().lookup("reportingmodule.service.ReportingServiceRemote");
            service.confirm(tdsMainCase.getReportingList().get(0), seriesList, note);
        } catch (NamingException ex) {
            Logger.getLogger(RemoteCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<CaseFlagPM> getCaseFlagPMList() {
        List<CaseFlagPM> result = new ArrayList<CaseFlagPM>();
        try {
            CaseFlagServiceRemote service =
                    (CaseFlagServiceRemote) new InitialContext().lookup("masterdatamodule.service.CaseFlagServiceRemote");
            List<CaseFlagDTO> flagList = service.findAll();
            for (CaseFlagDTO caseFlagDTO : flagList) {
                boolean checked = false;
                for (FlagAssignmentDTO flagAssignmentDTO : tdsMainCase.getFlagAssignmentList()) {
                    if (flagAssignmentDTO.getFlag().equals(caseFlagDTO)) {
                        checked = true;
                        break;
                    }
                }
                result.add(new CaseFlagPM(caseFlagDTO, checked));
            }
        } catch (TooManyResultsException ex) {
            Logger.getLogger(RemoteCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ZeroResultException ex) {
            Logger.getLogger(RemoteCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(RemoteCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public void closeCase(String note) {
        try {
            RcCaseServiceRemote service = (RcCaseServiceRemote) new InitialContext().lookup("casemodule.service.RcCaseServiceRemote");
            service.closeCase(tdsMainCase.getId(), note);
        } catch (NamingException ex) {
            Logger.getLogger(RemoteCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void loadCase() {
        downloadDicomDataSetIcons(String.valueOf(tdsMainCase.getId()), DownloaderCaseType.main);
    }

    @Override
    public void saveComment(CommentDTO dto) {
        try {
            ReportingServiceRemote service =
                    (ReportingServiceRemote) new InitialContext().lookup("reportingmodule.service.ReportingServiceRemote");
            service.save(dto);
        } catch (NamingException ex) {
            Logger.getLogger(RemoteCaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
