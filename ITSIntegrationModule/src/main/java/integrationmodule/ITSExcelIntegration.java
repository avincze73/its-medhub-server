/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationmodule;

import casemodule.dto.CaseDataDTO;
import casemodule.dto.ElectronicPatientDataDTO;
import casemodule.dto.ElectronicReferralDTO;
import casemodule.dto.ScannedPatientDataImageDTO;
import casemodule.dto.ScannedReferralImageDTO;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.FileUtil;

/**
 *
 * @author vincze.attila
 */
public class ITSExcelIntegration implements ITDSIntegration {

    private String DRIVER_NAME = "sun.jdbc.odbc.JdbcOdbcDriver";
    private String DATABASE_URL = "jdbc:odbc:TDSCaseData";
    private Properties prop;

    public ITSExcelIntegration() {
        prop = new java.util.Properties();
        prop.put("charSet", "cp1250");
    }

    public CaseDataDTO createCaseDataDTO() {
        CaseDataDTO caseDataDTO = new CaseDataDTO();
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();

            //case
            ResultSet rs = st.executeQuery("Select * from [Case$]");
            rs.next();
            int modeOfAcquisitionId = rs.getInt(1);
            int senderHospitalStaffId = rs.getInt(2);
            String nonDicomCaseIdInHospital = rs.getString(3);

            //caseDataDTO.setModeOfAcquisitionId(modeOfAcquisitionId);
            //caseDataDTO.setSenderHospitalStaffId(senderHospitalStaffId);
            caseDataDTO.setNonDicomCaseIdInHospital(nonDicomCaseIdInHospital);


            //electronicpatientdata
            rs = st.executeQuery("Select * from [ElectronicPatientData$]");
            rs.next();
            ElectronicPatientDataDTO electronicPatientData = new ElectronicPatientDataDTO(
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5),
                    rs.getDate(6), rs.getString(7),
                    rs.getString(8), rs.getString(9), rs.getString(10),
                    rs.getString(11), rs.getString(12));
            caseDataDTO.setElectronicPatientData(electronicPatientData);

            //electronicreferral
            rs = st.executeQuery("Select * from [ElectronicReferral$]");
            rs.next();
            ElectronicReferralDTO electronicReferral = new ElectronicReferralDTO(
                    rs.getString(1), rs.getDate(2), rs.getString(3),
                    rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7),
                    rs.getString(8), rs.getString(9), rs.getString(10),
                    rs.getString(11), rs.getString(12),
                    rs.getString(13), rs.getString(14), rs.getString(15),
                    rs.getString(16), rs.getString(17), rs.getString(18),
                    rs.getString(19), rs.getString(20), rs.getString(21),
                    rs.getString(22), rs.getString(23));

            caseDataDTO.setElectronicReferral(electronicReferral);

            //ScannedPatientDataImage
            rs = st.executeQuery("Select * from [ScannedPatientDataImage$]");
            while (rs.next()) {
                File file = new File(rs.getString(1));
                ScannedPatientDataImageDTO spdi = new ScannedPatientDataImageDTO();
                try {
                    spdi.setScannedImage(FileUtil.getBytesFromFile(file));
                } catch (IOException ex) {
                    Logger.getLogger(ITSExcelIntegration.class.getName()).log(Level.SEVERE, null, ex);
                }
                //caseDataDTO.getScannedPatientDataImageList().add(spdi);
            }

            //ScannedReferralImage
            rs = st.executeQuery("Select * from [ScannedReferralImage$]");
            while (rs.next()) {
                ScannedReferralImageDTO sri = new ScannedReferralImageDTO();
                String filePath = rs.getString(1);
                File file = new File(filePath);
                //sri.setFileName(filePath);
                try {
                    sri.setScannedImage(FileUtil.getBytesFromFile(file));
                } catch (IOException ex) {
                    Logger.getLogger(ITSExcelIntegration.class.getName()).log(Level.SEVERE, null, ex);
                }
                //caseDataDTO.getScannedReferralImageList().add(sri);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ITSExcelIntegration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ITSExcelIntegration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ITSExcelIntegration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return caseDataDTO;
    }

//    public String[] getDicomFilePaths() {
//        Connection con = null;
//        Set<String> ret = new HashSet<String>();
//        try {
//            Class.forName(DRIVER_NAME);
//            con = DriverManager.getConnection(DATABASE_URL, prop);
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery("Select * from [DicomFile$]");
//            while (rs.next()) {
//                ret.add(rs.getString(1));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(TDSExcelIntegration.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(TDSExcelIntegration.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                con.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(TDSExcelIntegration.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return ret.toArray(new String[0]);
//    }


    public File[] getScannedPatientDataPaths() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public File[] getScannedReferralPaths() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getCaseSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public File[] getDicomFilePaths() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
