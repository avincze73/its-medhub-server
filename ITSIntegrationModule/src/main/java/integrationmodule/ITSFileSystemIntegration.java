/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationmodule;

import casemodule.dto.CaseDataDTO;
import casemodule.dto.ElectronicPatientDataDTO;
import casemodule.dto.ElectronicReferralDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author vincze.attila
 */
public class ITSFileSystemIntegration implements ITDSIntegration {

    private String selectedDirectory;

    private String getSelectedDirectory() {
        String result = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("choosertitle");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            result = chooser.getSelectedFile().getAbsolutePath();
            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile().getAbsolutePath());
        } else {
            System.out.println("No Selection ");
        }
        return result;
    }

    public CaseDataDTO createCaseDataDTO() {
        CaseDataDTO caseDataDTO = new CaseDataDTO();
        selectedDirectory = getSelectedDirectory();
        if (selectedDirectory == null) {
            return null;
        }
        try {
            Properties caseProperties = new Properties();
            caseProperties.load(new FileInputStream(new File(selectedDirectory + "\\tdscase.properties")));

            //caseDataDTO.setModeOfAcquisitionId(Integer.parseInt(caseProperties.getProperty("modeOfAcquisitionId")));
            //caseDataDTO.setSenderHospitalStaffId(Integer.parseInt(caseProperties.getProperty("senderHospitalStaffId")));
            caseDataDTO.setNonDicomCaseIdInHospital(caseProperties.getProperty("nonDicomCaseIdInHospital"));

            ElectronicPatientDataDTO electronicPatientData = new ElectronicPatientDataDTO(
                    caseProperties.getProperty("patientIdInHospital"),
                    caseProperties.getProperty("patientName"),
                    caseProperties.getProperty("gender"),
                    caseProperties.getProperty("idOrSimilar"),
                    caseProperties.getProperty("tBorNiOrSimilar"),
                    new SimpleDateFormat("yyyy.MM.dd.").parse(caseProperties.getProperty("dob")),
                    caseProperties.getProperty("address"),
                    caseProperties.getProperty("contactInfo"),
                    caseProperties.getProperty("contactTel"),
                    caseProperties.getProperty("mothersName"),
                    caseProperties.getProperty("nationality"),
                    caseProperties.getProperty("other"));
            caseDataDTO.setElectronicPatientData(electronicPatientData);

            ElectronicReferralDTO electronicReferral = new ElectronicReferralDTO(
                    caseProperties.getProperty("placeOfReferral"),
                    new SimpleDateFormat("yyyy.MM.dd.").parse(caseProperties.getProperty("dateOfReferral")),
                    caseProperties.getProperty("referralText"),
                    caseProperties.getProperty("referringInstitutionName"),
                    caseProperties.getProperty("referringInstitutionAddress"),
                    caseProperties.getProperty("referringInstitutionCode"),
                    caseProperties.getProperty("referringInstitutionContacts"),
                    caseProperties.getProperty("referringInstitutionTel"),
                    caseProperties.getProperty("referringInstitutionFax"),
                    caseProperties.getProperty("referringUnitName"),
                    caseProperties.getProperty("referringUnitAddress"),
                    caseProperties.getProperty("referringUnitCode"),
                    caseProperties.getProperty("referringUnitContacts"),
                    caseProperties.getProperty("referringUnitTel"),
                    caseProperties.getProperty("referringUnitFax"),
                    caseProperties.getProperty("referringDoctorName"),
                    caseProperties.getProperty("referringDoctorCode"),
                    caseProperties.getProperty("referringDoctorContacts"),
                    caseProperties.getProperty("referringDoctorTel"),
                    caseProperties.getProperty("referringDoctorFax"),
                    caseProperties.getProperty("referralCodeAndDescriptionList"),
                    caseProperties.getProperty("referralCodeList"),
                    caseProperties.getProperty("referralDescriptionList"));
            caseDataDTO.setElectronicReferral(electronicReferral);

            //File[] scannedReferrals = (new File(selectedDirectory + "\\scannedReferral")).listFiles();
            //File[] scannedPatientDatas = (new File(selectedDirectory + "\\scannedPatientData")).listFiles();

//            for (int i = 0; i < scannedReferrals.length; i++) {
//                File file = scannedReferrals[i];
//                ScannedReferralImageDTO sri = new ScannedReferralImageDTO();
//                sri.setFileName(file.getAbsolutePath());
//                try {
//                    sri.setScannedImage(FileUtil.getBytesFromFile(file));
//                } catch (IOException ex) {
//                    Logger.getLogger(TDSExcelIntegration.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                caseDataDTO.getScannedReferralImageList().add(sri);
//            }
//
//            for (int i = 0; i < scannedPatientDatas.length; i++) {
//                File file = scannedPatientDatas[i];
//                ScannedPatientDataImageDTO spdi = new ScannedPatientDataImageDTO();
//                try {
//                    spdi.setScannedImage(FileUtil.getBytesFromFile(file));
//                } catch (IOException ex) {
//                    Logger.getLogger(TDSExcelIntegration.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                caseDataDTO.getScannedPatientDataImageList().add(spdi);
//            }


        } catch (ParseException ex) {
            Logger.getLogger(ITSFileSystemIntegration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ITSFileSystemIntegration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return caseDataDTO;
    }

    public File[] getDicomFilePaths() {
        return new File(selectedDirectory + "\\dicom").listFiles();
    }

    public File[] getScannedReferralPaths() {
        return new File(selectedDirectory + "\\scannedReferral").listFiles();
    }

    public File[] getScannedPatientDataPaths() {
        return new File(selectedDirectory + "\\scannedPatientData").listFiles();
    }

    public long getCaseSize() {
        if (selectedDirectory == null) {
            return 0;
        } else {
            return FileUtils.sizeOfDirectory(new File(selectedDirectory + "\\dicom"))
                    + FileUtils.sizeOfDirectory(new File(selectedDirectory + "\\scannedReferral"))
                    + FileUtils.sizeOfDirectory(new File(selectedDirectory + "\\scannedPatientData"));
        }
    }
}
