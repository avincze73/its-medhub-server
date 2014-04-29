/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationmodule;

import casemodule.dto.CaseDataDTO;
import casemodule.dto.ScannedPatientDataImageDTO;
import casemodule.dto.ScannedReferralImageDTO;
import casemodule.dto.ScannedReportImageDTO;
import casemodule.service.CaseTransferServiceRemote;
import dicommodule.oldretiredandtesting.DicomIdentifier;
import event.FileUploadingEvent;
import event.ITSEventManager;
import integrationmodule.dto.HistoryStudyUploadPackageDTO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.commons.io.FileUtils;
import tdsdicominterface.DicomDataSet;
import tdsdicominterface.DicomManager;
import tdsdicominterface.exception.DIException;
import util.FileUtil;
import util.ImageUtil;

/**
 *
 * @author vincze.attila
 */
public class CaseTransferClientProcess {

    public static CaseTransferClientProcess create() {
        return new CaseTransferClientProcess();
    }

    public void start(CaseDataDTO caseDataDTO) {
        try {
            CaseTransferServiceRemote caseTransferServiceRemote = (CaseTransferServiceRemote) new InitialContext().lookup("casemodule.service.CaseTransferServiceRemote");
            Set<String> uids = new HashSet<String>();
            Set<DicomDataSet> dicomDataSets = new HashSet<DicomDataSet>();
            long caseSize = 0;
            for (String path : caseDataDTO.getDicomImageList()) {
                caseSize += FileUtils.sizeOf(new File(path));
            }
            for (String path : caseDataDTO.getScannedReferralImageList()) {
                caseSize += FileUtils.sizeOf(new File(path));
            }
            for (String path : caseDataDTO.getScannedPatientDataImageList()) {
                caseSize += FileUtils.sizeOf(new File(path));
            }
            ITSEventManager.fireEvent(new FileUploadingEvent("start:" + caseSize));
            for (String path : caseDataDTO.getDicomImageList()) {
                File file = new File(path);
                try {
                    DicomDataSet dicomDataSet = DicomManager.importFile(file);
                    DicomIdentifier dicomIdentifier = new DicomIdentifier(dicomDataSet);
                    uids.add(dicomIdentifier.getUniqueId());
                    dicomDataSets.add(dicomDataSet);
                } catch (DIException ex) {
                    Logger.getLogger(CaseTransferClientProcess.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CaseTransferClientProcess.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            caseDataDTO.setDicomUniqueIds(uids.toArray(new String[0]));
            caseTransferServiceRemote.startCaseTransfer(caseDataDTO);
            for (String path : caseDataDTO.getScannedReferralImageList()) {
                File file = new File(path);
                if (!"jpeg".equals(ImageUtil.getFormatInFile(file).toLowerCase()) && !"png".equals(ImageUtil.getFormatInFile(file).toLowerCase())) {
                    continue;
                }
                ScannedReferralImageDTO sri = new ScannedReferralImageDTO();
                //sri.setFileName(file.getAbsolutePath());
                try {
                    sri.setScannedImage(FileUtil.getBytesFromFile(file));
                } catch (IOException ex) {
                    Logger.getLogger(CaseTransferClientProcess.class.getName()).log(Level.SEVERE, null, ex);
                }
                ITSEventManager.fireEvent(new FileUploadingEvent(file.length()));
                System.out.println("Transfering: " + file.getAbsolutePath());
                caseTransferServiceRemote.saveScannedReferralImage(sri);
            }
            for (String path : caseDataDTO.getScannedPatientDataImageList()) {
                File file = new File(path);
                if (!"jpeg".equals(ImageUtil.getFormatInFile(file).toLowerCase()) && !"png".equals(ImageUtil.getFormatInFile(file).toLowerCase())) {
                    continue;
                }
                ScannedPatientDataImageDTO spdi = new ScannedPatientDataImageDTO();
                try {
                    spdi.setScannedImage(FileUtil.getBytesFromFile(file));
                } catch (IOException ex) {
                    Logger.getLogger(CaseTransferClientProcess.class.getName()).log(Level.SEVERE, null, ex);
                }
                ITSEventManager.fireEvent(new FileUploadingEvent(file.length()));
                System.out.println("Transfering: " + file.getAbsolutePath());
                caseTransferServiceRemote.saveScannedPatientDataImage(spdi);
            }
            for (String path : caseDataDTO.getDicomImageList()) {
                File file = new File(path);
                try {
                    DicomDataSet dicomDataSet = DicomManager.importFile(file);
                    System.out.println("Transfering: " + new DicomIdentifier(dicomDataSet).getUniqueId());
                    ITSEventManager.fireEvent(new FileUploadingEvent(file.length()));
                    caseTransferServiceRemote.saveDicomDataSet(dicomDataSet);
                } catch (DIException ex) {
                    Logger.getLogger(CaseTransferClientProcess.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CaseTransferClientProcess.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            HistoryStudyUploadPackageDTO historyStudyUploadPackage = new HistoryStudyUploadPackageDTO();
            historyStudyUploadPackage.setReferralText("hello");
            historyStudyUploadPackage.setReportText("attila");
            //referralimage
            historyStudyUploadPackage.setReferralImageList(new ArrayList<ScannedReferralImageDTO>());
            ScannedReferralImageDTO scannedReferralImageDTO = new ScannedReferralImageDTO();
            scannedReferralImageDTO.setScannedImage("vincze".getBytes());
            historyStudyUploadPackage.getReferralImageList().add(scannedReferralImageDTO);
            //reportimage
            historyStudyUploadPackage.setReportImageList(new ArrayList<ScannedReportImageDTO>());
            ScannedReportImageDTO scannedReportImageDTO = new ScannedReportImageDTO();
            scannedReportImageDTO.setScannedImage("attila".getBytes());
            historyStudyUploadPackage.getReportImageList().add(scannedReportImageDTO);
            caseTransferServiceRemote.saveHistoryStudy(historyStudyUploadPackage);
            File file = new File(caseDataDTO.getDicomImageList().get(0));
            try {
                DicomDataSet dicomDataSet = DicomManager.importFile(file);
                caseTransferServiceRemote.saveDicomDataSet(dicomDataSet);
            } catch (DIException ex) {
                Logger.getLogger(CaseTransferClientProcess.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CaseTransferClientProcess.class.getName()).log(Level.SEVERE, null, ex);
            }

            caseTransferServiceRemote.commitCaseTransfer(uids.toArray(new String[0]));
            ITSEventManager.fireEvent(new FileUploadingEvent("stop"));
        } catch (NamingException ex) {
            Logger.getLogger(CaseTransferClientProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start() {
        try {
            CaseTransferServiceRemote caseTransferServiceRemote =
                    (CaseTransferServiceRemote) new InitialContext().lookup("casemodule.service.CaseTransferServiceRemote");
            Set<String> uids = new HashSet<String>();
            Set<DicomDataSet> dicomDataSets = new HashSet<DicomDataSet>();
            //ITDSIntegration integration = new TDSExcelIntegration();
            ITDSIntegration integration = new ITSFileSystemIntegration();
            CaseDataDTO caseDataDTO = integration.createCaseDataDTO();
            //String[] dicomFilePaths = integration.getDicomFilePaths();
            ITSEventManager.fireEvent(new FileUploadingEvent("start:" + integration.getCaseSize()));



            for (File file : integration.getDicomFilePaths()) {
                //File file = new File(filePath);
                try {
                    DicomDataSet dicomDataSet = DicomManager.importFile(file);
                    DicomIdentifier dicomIdentifier = new DicomIdentifier(dicomDataSet);
                    uids.add(dicomIdentifier.getUniqueId());
                    dicomDataSets.add(dicomDataSet);



                } catch (DIException ex) {
                    Logger.getLogger(CaseTransferClientProcess.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CaseTransferClientProcess.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
            caseDataDTO.setDicomUniqueIds(uids.toArray(new String[0]));
            caseTransferServiceRemote.startCaseTransfer(caseDataDTO);



            for (File file : integration.getScannedReferralPaths()) {
                if (!"jpeg".equals(ImageUtil.getFormatInFile(file).toLowerCase())
                        && !"png".equals(ImageUtil.getFormatInFile(file).toLowerCase())) {
                    continue;


                }
                ScannedReferralImageDTO sri = new ScannedReferralImageDTO();
                //sri.setFileName(file.getAbsolutePath());


                try {
                    sri.setScannedImage(FileUtil.getBytesFromFile(file));



                } catch (IOException ex) {
                    Logger.getLogger(CaseTransferClientProcess.class.getName()).log(Level.SEVERE, null, ex);
                }
                ITSEventManager.fireEvent(new FileUploadingEvent(file.length()));
                System.out.println("Transfering: " + file.getAbsolutePath());
                caseTransferServiceRemote.saveScannedReferralImage(sri);


            }

            for (File file : integration.getScannedPatientDataPaths()) {
                if (!"jpeg".equals(ImageUtil.getFormatInFile(file).toLowerCase())
                        && !"png".equals(ImageUtil.getFormatInFile(file).toLowerCase())) {
                    continue;


                }
                ScannedPatientDataImageDTO spdi = new ScannedPatientDataImageDTO();


                try {
                    spdi.setScannedImage(FileUtil.getBytesFromFile(file));



                } catch (IOException ex) {
                    Logger.getLogger(CaseTransferClientProcess.class.getName()).log(Level.SEVERE, null, ex);
                }
                ITSEventManager.fireEvent(new FileUploadingEvent(file.length()));
                System.out.println("Transfering: " + file.getAbsolutePath());
                caseTransferServiceRemote.saveScannedPatientDataImage(spdi);


            }

            for (File file : integration.getDicomFilePaths()) {
                try {
                    DicomDataSet dicomDataSet = DicomManager.importFile(file);
                    System.out.println("Transfering: " + new DicomIdentifier(dicomDataSet).getUniqueId());
                    ITSEventManager.fireEvent(new FileUploadingEvent(file.length()));
                    caseTransferServiceRemote.saveDicomDataSet(dicomDataSet);



                } catch (DIException ex) {
                    Logger.getLogger(CaseTransferClientProcess.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CaseTransferClientProcess.class.getName()).log(Level.SEVERE, null, ex);
                }


            }

//            for (DicomDataSet dicomDataSet : dicomDataSets) {
//                System.out.println("Transfering: " + new DicomIdentifier(dicomDataSet).getUniqueId());
//                caseTransferServiceRemote.saveDicomDataSet(dicomDataSet);
//            }
            caseTransferServiceRemote.commitCaseTransfer(uids.toArray(new String[0]));
            ITSEventManager.fireEvent(new FileUploadingEvent("stop"));




        } catch (NamingException ex) {
            Logger.getLogger(CaseTransferClientProcess.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
