/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itsdatabasecreation;

import casemodule.dto.CaseDTO;
import casemodule.dto.DataProcLogDTO;
import casemodule.dto.ElectronicPatientDataDTO;
import casemodule.dto.ElectronicReferralDTO;
import casemodule.dto.PatientAndReferralInfoDTO;
import casemodule.dto.SystemMessageTypeClass;
import casemodule.dto.SystemMessageTypeDTO;
import masterdatamodule.dto.TDSConstantDTO;
import common.dto.TDSServiceDTO;
import hospitalmodule.dto.AvailabilityPerPeriodDTO;
import hospitalmodule.dto.AvailabilityPerWeekDTO;
import hospitalmodule.dto.ContactPersonAssignmentDTO;
import hospitalmodule.dto.ContactPersonDTO;
import hospitalmodule.dto.ContactPersonType;
import hospitalmodule.dto.HospitalContractDTO;
import hospitalmodule.dto.HospitalDTO;
import hospitalmodule.dto.OptionAssignmentDTO;
import hospitalmodule.dto.WorkBandTableDTO;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import masterdatamodule.dto.BodyRegionDTO;
import masterdatamodule.dto.CaseFlagDTO;
import masterdatamodule.dto.CaseStatusDTO;
import masterdatamodule.dto.ContractTypeDTO;
import masterdatamodule.dto.CountryDTO;
import masterdatamodule.dto.CurrencyDTO;
import masterdatamodule.dto.GoverningAuthorityDTO;
import masterdatamodule.dto.HospContrOptionDTO;
import masterdatamodule.dto.LanguageDTO;
import masterdatamodule.dto.ModalityDTO;
import masterdatamodule.dto.ModeOfAcquisitionDTO;
import masterdatamodule.dto.RegLicQualDTO;
import masterdatamodule.dto.RegLicQualTypeDTO;
import masterdatamodule.dto.RegionDTO;
import masterdatamodule.dto.ScenarioDTO;
import masterdatamodule.dto.ScenarioPolicyDTO;
import masterdatamodule.dto.SequenceCategoryDTO;
import masterdatamodule.dto.WayOfClosingDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import radiologistmodule.dto.CompanyAssignmentDTO;
import radiologistmodule.dto.CompanyDTO;
import radiologistmodule.dto.RadAvailabilityDTO;
import radiologistmodule.dto.RadCompetenceDTO;
import radiologistmodule.dto.RegLicQualOwnershipDTO;
import radiologistmodule.dto.TDSRadiologistDTO;
import radiologistmodule.dto.WorkPlaceDTO;
import reportingmodule.dto.AutoFunctionTypeClass;
import reportingmodule.dto.AutoFunctionTypeDTO;
import usermodule.dto.HospitalStaffDTO;
import usermodule.dto.HospitalStaffRoleDTO;
import usermodule.dto.RoleAssignmentDTO;
import usermodule.dto.RoleDTO;
import usermodule.dto.TDSManagerDTO;
import usermodule.dto.TDSManagerRoleAssignmentDTO;
import usermodule.dto.TDSManagerRoleDTO;
import usermodule.dto.TDSRadiologistRoleDTO;
import usermodule.dto.UserDTO;
import usermodule.entity.UserSex;
import usermodule.entity.UserType;

/**
 *
 * @author vincze.attila
 */
public class MasterDataMigration {

    private String DRIVER_NAME =
            "sun.jdbc.odbc.JdbcOdbcDriver";
    private String DATABASE_URL = "jdbc:odbc:TDSMasterData";
    private SessionFactory factory;
    private Session session;
    private Properties prop;

    public static MasterDataMigration Create() {
        return new MasterDataMigration();
    }

    public MasterDataMigration() {
        //factory = ITSHibernateUtil.getSessionFactory();
        prop = new java.util.Properties();
        prop.put("charSet", "cp1250");
    }

    public void serialize() {
        FileOutputStream fos = null;
        {
            ObjectOutputStream oos = null;
            try {
                session = factory.openSession();
                CaseDTO dto = (CaseDTO) session.get(CaseDTO.class, 2);
                fos = new FileOutputStream("f:\\DiagDance\\a.out");
                oos = new ObjectOutputStream(fos);
                oos.writeObject(dto);
                oos.flush();
                oos.close();
                fos.close();


                FileInputStream fileIn =
                        new FileInputStream("f:\\DiagDance\\a.out");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                CaseDTO dto2 = (CaseDTO) in.readObject();
                in.close();
                fileIn.close();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fos.close();
                } catch (IOException ex) {
                    Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    oos.close();
                } catch (IOException ex) {
                    Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void Load() {
        session = factory.openSession();
        Transaction tx = session.beginTransaction();

        loadAutoFunctionTypes();
        loadSystemMessageType();
        loadModalities();
        loadRoles();
        loadRoleCanAssignToUser();
        loadRolePrerequisites();
        loadBodyRegions();
        loadSequenceCategories();
        loadLanguages();
        loadRegLicQualTypes();
        loadCaseFlags();
        loadModeOfAcquisitions();
        loadCaseStatuses();
        loadWayOfClosings();
        loadScenarios();
        loadScenarioPolicies();
        loadCurrencies();
        loadCoutries();
        loadRegions();
        loadCompanies();
        loadRegLicQuals();
        loadHospContrOptions();
        loadTDSConstans();
        loadGoverningAuthorities();
        loadContractTypes();
        loadTestHospital();
        loadTestRadiologists();
        loadTestHospitalContract();
        loadTestHospitalStaff();
        loadTestTDSManager();
        //loadTestTDSCase();
        getTestHospitalContract();
        loadTestRoles();
        loadTestTDSServices();
        tx.commit();
        session.close();
    }

    private void loadCompanies() {
        System.out.println("loading companies...");
        CompanyDTO dto = new CompanyDTO();
        dto.setName("First company");
        dto.setAccountNumber("11111");
        dto.setTaxAuthorityNumber("22222");
        dto.setAddress("miskolc");
        dto.setCountry((CountryDTO) session.get(CountryDTO.class, (long) 1));
        session.save(dto);
    }

    private void loadSystemMessageType() {
        System.out.println("loading system message type...");
        SystemMessageTypeDTO systemMessageTypeDTO = new SystemMessageTypeDTO();
        systemMessageTypeDTO.setMessageClass(SystemMessageTypeClass.IT.name());
        systemMessageTypeDTO.setMessageType("SystemMessageType1");
        systemMessageTypeDTO.setPriority(1);
        session.save(systemMessageTypeDTO);
    }

    private void loadTDSConstans() {
        System.out.println("loading TDS constants...");
        session.save(new TDSConstantDTO("Max record number", 135));
        session.save(new TDSConstantDTO("A kórház nem veszi át a leletet", 1));
        session.save(new TDSConstantDTO("Munkalista frissítési periodusideje", 30));
    }

    private void loadGoverningAuthorities() {
        System.out.println("loading governing authorities...");
        session.save(new GoverningAuthorityDTO("Authority 1"));
        session.save(new GoverningAuthorityDTO("Authority 2"));
        session.save(new GoverningAuthorityDTO("Authority 3"));
        session.save(new GoverningAuthorityDTO("Authority 4"));
        session.save(new GoverningAuthorityDTO("Authority 5"));
        session.save(new GoverningAuthorityDTO("Authority 6"));
    }

    private void loadContractTypes() {
        System.out.println("loading contract types...");
        session.save(new ContractTypeDTO("Contract type 1", "Szerződés típus 1"));
        session.save(new ContractTypeDTO("Contract type 2", "Szerződés típus 2"));
        session.save(new ContractTypeDTO("Contract type 3", "Szerződés típus 3"));
        session.save(new ContractTypeDTO("Contract type 4", "Szerződés típus 4"));
        session.save(new ContractTypeDTO("Contract type 5", "Szerződés típus 5"));
        session.save(new ContractTypeDTO("Contract type 6", "Szerződés típus 6"));
    }

    private void loadAutoFunctionTypes() {
        System.out.println("loading autofunction types...");
        session.save(new AutoFunctionTypeDTO("Archiving", AutoFunctionTypeClass.archiving.name()));
        session.save(new AutoFunctionTypeDTO("Basic", AutoFunctionTypeClass.basic.name()));
        session.save(new AutoFunctionTypeDTO("Scenario", AutoFunctionTypeClass.scenario.name()));
    }

    private void loadLanguages() {
        System.out.println("loading languages...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [Language$]");
            while (rs.next()) {
                session.save(new LanguageDTO(rs.getString(1), rs.getString(2)));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void loadModalities() {
        System.out.println("loading modalities...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [Modality$]");
            while (rs.next()) {
                session.save(new ModalityDTO(rs.getString(1)));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadRoles() {
        System.out.println("loading roles...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [Role$]");
            while (rs.next()) {
                String canHave = rs.getString(5);
                if ("TDSManager".equals(canHave)) {
                    session.save(
                            new TDSManagerRoleDTO(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getString(4),
                            canHave, rs.getString(6), rs.getString(7)));
                } else if ("TDSRadiologist".equals(canHave)) {
                    session.save(
                            new TDSRadiologistRoleDTO(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getString(4),
                            canHave, rs.getString(6), rs.getString(7)));
                } else {
                    session.save(
                            new HospitalStaffRoleDTO(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getString(4),
                            canHave, rs.getString(6), rs.getString(7)));
                }

            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadRoleCanAssignToUser() {
        System.out.println("loading roleCanAssignToUser...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [Role$]");
            long i = 0;
            while (rs.next()) {
                i++;
                String canAssignToUser = rs.getString(8);
                String[] array = canAssignToUser.split(",");
                RoleDTO role = (RoleDTO) session.get(RoleDTO.class, i);
                for (int j = 0; j < array.length; j++) {
                    String string = array[j].trim();
                    List<RoleDTO> rList = session.createCriteria(RoleDTO.class).add(Expression.like("abbreviation", string)).list();
                    if (rList.size() > 0) {
                        role.getWhoCanAssignThisRoleToUser().add(rList.get(0));
                    }
                }
                session.saveOrUpdate(role);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadRolePrerequisites() {
        System.out.println("loading rolePrerequisites...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [Role$]");
            long i = 0;
            while (rs.next()) {
                i++;
                String prerequiste = rs.getString(9);
                if ("-".equals(prerequiste)) {
                    continue;
                }
                String[] array = prerequiste.split(",");
                RoleDTO role = (RoleDTO) session.get(RoleDTO.class, i);
                for (int j = 0; j < array.length; j++) {
                    String string = array[j].trim();
                    List<RoleDTO> rList = session.createCriteria(RoleDTO.class).add(Expression.like("abbreviation", string)).list();
                    if (rList.size() > 0) {
                        role.getPrerequisites().add(rList.get(0));
                    }
                }
                session.saveOrUpdate(role);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadBodyRegions() {
        System.out.println("loading body regions...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [BodyRegion$]");
            while (rs.next()) {
                session.save(new BodyRegionDTO(rs.getString(2), rs.getString(1), rs.getString(3), rs.getString(4)));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadSequenceCategories() {
        System.out.println("loading sequence categories...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [SequenceCategory$]");
            while (rs.next()) {
                session.save(new SequenceCategoryDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadHospContrOptions() {
        System.out.println("loading hospital contract options...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [HospContrOption$]");
            while (rs.next()) {
                session.save(new HospContrOptionDTO(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            rs.close();
            st.close();
            long i = 3;
            HospContrOptionDTO hospContrOptionDTO =
                    new HospContrOptionDTO("standardHungarianRLQRequirements", "RegLicQual", "-");
            hospContrOptionDTO.setLongParameter(((RegLicQualDTO) session.get(RegLicQualDTO.class, i)).getId());
            session.save(hospContrOptionDTO);

            i = 4;
            hospContrOptionDTO =
                    new HospContrOptionDTO("standardEnglishRLQRequirements", "RegLicQual", "-");
            hospContrOptionDTO.setLongParameter(((RegLicQualDTO) session.get(RegLicQualDTO.class, i)).getId());
            session.save(hospContrOptionDTO);

        } catch (SQLException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadRegLicQualTypes() {
        System.out.println("loading reglicqualtypes...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [RegLicQualType$]");
            while (rs.next()) {
                session.save(new RegLicQualTypeDTO(rs.getString(1)));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadCaseFlags() {
        System.out.println("loading case flags...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [CaseFlag$]");
            while (rs.next()) {
                session.save(new CaseFlagDTO(rs.getString(1), rs.getString(2)));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadModeOfAcquisitions() {
        System.out.println("loading mode of acquisitions...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [ModeOfAcquisition$]");
            while (rs.next()) {
                session.save(new ModeOfAcquisitionDTO(rs.getString(1), rs.getString(2)));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void loadCaseStatuses() {
        System.out.println("loading case statuses...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [CaseStatus$]");
            while (rs.next()) {
                CaseStatusDTO caseStatusDTO = new CaseStatusDTO(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6));
                session.save(caseStatusDTO);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadWayOfClosings() {
        System.out.println("loading way of closings...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [WayOfClosing$]");
            while (rs.next()) {
                session.save(new WayOfClosingDTO(rs.getString(1)));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadScenarios() {
        System.out.println("loading scenarios...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [Scenario$]");
            while (rs.next()) {
                session.save(new ScenarioDTO(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getString(4), rs.getInt(8)));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void loadScenarioPolicies() {
        System.out.println("loading scenario policies...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [ScenarioPolicy$]");
            while (rs.next()) {
                session.save(new ScenarioPolicyDTO(
                        (ScenarioDTO) session.createCriteria(ScenarioDTO.class).add(Expression.eq("englishName", rs.getString(1))).uniqueResult(),
                        rs.getString(2), new Date()));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadCurrencies() {
        System.out.println("loading currencies...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [Currency$]");
            while (rs.next()) {
                session.save(new CurrencyDTO(rs.getString(1)));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadCoutries() {
        System.out.println("loading countries...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [Country$]");
            while (rs.next()) {
                session.save(new CountryDTO(rs.getString(1), rs.getString(2),
                        (CurrencyDTO) session.createCriteria(CurrencyDTO.class).add(Expression.eq("name", rs.getString(3))).uniqueResult()));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadRegLicQuals() {
        System.out.println("loading reglicquals...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [RegLicQual$]");
            while (rs.next()) {
                session.save(new RegLicQualDTO(
                        rs.getString(1),
                        (RegionDTO) session.createCriteria(RegionDTO.class).add(Expression.eq("name", rs.getString(3))).uniqueResult(),
                        (RegLicQualTypeDTO) session.createCriteria(RegLicQualTypeDTO.class).add(Expression.eq("name", rs.getString(4))).uniqueResult()));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadRegions() {
        System.out.println("loading regions...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [Region$]");
            while (rs.next()) {
                session.save(new RegionDTO(rs.getString(1),
                        (CountryDTO) session.createCriteria(CountryDTO.class).add(Expression.eq("englishName", rs.getString(2))).uniqueResult()));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MasterDataMigration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadTestRadiologists() {
        System.out.println("loading test radiologists...");
        //radiologist 1
        TDSRadiologistDTO radiologist = new TDSRadiologistDTO();
        UserDTO user = new UserDTO();
        user.setName("Radiologist Name1");
        user.setLoginName("tds.radiologist");
        user.setPassword(SecurityUtil.create().getPasswordHash("tds1"));
        user.setSex(UserSex.female.name());
        user.setTdsEmail("tds@hss-med.eu");
        user.setWorkEmail("workemail");
        user.setWorkTel("worktel");
        user.setAddingDateTime(new Date());
        user.setActive(true);
        user.setGetsSystemEmails(true);
        user.setUserType(UserType.itsRadiologist.name());

        radiologist.setUserInfo(user);
        radiologist.setAccountNumber("accountnumber");
        radiologist.setCreditPoints(1);
        radiologist.setCreditValidity(new Date());
        radiologist.setDefaultMaxAvailabilityHrsPerWeekDay(2);
        radiologist.setDefaultMaxAvailabilityHrsPerWeekendDay(3);
        radiologist.setDefaultNormalAvailabilityHrsPerWeekDay(4);
        radiologist.setDefaultNormalAvailabilityHrsPerWeekendDay(5);
        radiologist.setEnglishAllowed(true);
        radiologist.setEnglishLanguageCompetence(3);
        radiologist.setEnglishSalaryRate(123.0);
        radiologist.setHungarianLanguageCompetence(5);
        radiologist.setHungarianSalaryRate(456.0);
        radiologist.setInProbation(true);
        radiologist.setLastCheck(new Date());
        radiologist.setRadInvoiceClosingDay(7);
        radiologist.setRadPaymentDay(7);
        radiologist.setReportSendingCode("reportsendingcode");
        radiologist.setWorkDemandPriority(9);

        RadCompetenceDTO competence = new RadCompetenceDTO();
        competence.setAddingDateTime(new Date());
        long i = 5;
        competence.setBodyRegion((BodyRegionDTO) session.get(BodyRegionDTO.class, i));
        i = 1;
        competence.setModality((ModalityDTO) session.get(ModalityDTO.class, i));
        competence.setCompetenceLevel(12);
        competence.setValid(true);
        competence.setTdsRadiologist(radiologist);
        radiologist.getRadCompetenceList().add(competence);

        RegLicQualOwnershipDTO reg = new RegLicQualOwnershipDTO();
        reg.setActive(true);
        reg.setCertificateNumber("certificatenumber");
        reg.setRegLicQual((RegLicQualDTO) session.get(RegLicQualDTO.class, (long) 1));
        reg.setValidBegin(new Date());
        reg.setValidEnd(new Date());
        reg.setTdsRadiologist(radiologist);
        radiologist.getRegLicQualOwnershipList().add(reg);

        CompanyAssignmentDTO ca = new CompanyAssignmentDTO();
        ca.setTdsRadiologist(radiologist);
        ca.setStarted(new Date());
        ca.setEnded(new Date());
        ca.setActive(true);
        ca.setCompany((CompanyDTO) session.get(CompanyDTO.class, (long) 1));
        radiologist.getCompanyAssignmentList().add(ca);

        WorkPlaceDTO wp = new WorkPlaceDTO();
        wp.setTdsRadiologist(radiologist);
        wp.setHospital((HospitalDTO) session.get(HospitalDTO.class, (long) 1));
        wp.setAdded(new Date());
        wp.setFromDate(new Date());
        wp.setToDate(new Date());
        radiologist.getWorkPlaceList().add(wp);

        RadAvailabilityDTO ra = new RadAvailabilityDTO();
        ra.setTdsRadiologist(radiologist);
        ra.setAdded(new Date());
        ra.setConfirmedByPM(false);
        ra.setConfirmedByRad(false);
        ra.setValid(false);
        ra.setFromDate(new Date());
        ra.setToDate(new Date());
        ra.setNormalAvailabilityPerWeek(new AvailabilityPerWeekDTO(1, 1, 1, 1, 1, 1, 1));
        ra.setMaxAvailabilityPerWeek(new AvailabilityPerWeekDTO(1, 1, 1, 1, 1, 1, 1));
        radiologist.getAvailabilityList().add(ra);
        session.save(radiologist);

        //radiologist 2
        radiologist = new TDSRadiologistDTO();

        user = new UserDTO();
        user.setName("Radiologist Name2");
        user.setLoginName("tds.radiologist2");
        user.setPassword(SecurityUtil.create().getPasswordHash("tds1"));
        user.setSex(UserSex.female.name());
        user.setTdsEmail("tds@hss-med.eu");
        user.setWorkEmail("workemail");
        user.setWorkTel("worktel");
        user.setAddingDateTime(new Date());
        user.setActive(true);
        user.setGetsSystemEmails(true);
        user.setUserType(UserType.itsRadiologist.name());

        radiologist.setUserInfo(user);
        radiologist.setAccountNumber("accountnumber");
        radiologist.setCreditPoints(1);
        radiologist.setCreditValidity(new Date());
        radiologist.setDefaultMaxAvailabilityHrsPerWeekDay(2);
        radiologist.setDefaultMaxAvailabilityHrsPerWeekendDay(3);
        radiologist.setDefaultNormalAvailabilityHrsPerWeekDay(4);
        radiologist.setDefaultNormalAvailabilityHrsPerWeekendDay(5);
        radiologist.setEnglishAllowed(true);
        radiologist.setEnglishLanguageCompetence(3);
        radiologist.setEnglishSalaryRate(123.0);
        radiologist.setHungarianLanguageCompetence(5);
        radiologist.setHungarianSalaryRate(456.0);
        radiologist.setInProbation(true);
        radiologist.setLastCheck(new Date());
        radiologist.setRadInvoiceClosingDay(7);
        radiologist.setRadPaymentDay(7);
        radiologist.setReportSendingCode("reportsendingcode");
        radiologist.setWorkDemandPriority(9);

        competence = new RadCompetenceDTO();
        competence.setAddingDateTime(new Date());
        competence.setBodyRegion((BodyRegionDTO) session.get(BodyRegionDTO.class, (long) 6));
        competence.setModality((ModalityDTO) session.get(ModalityDTO.class, (long) 2));
        competence.setCompetenceLevel(12);
        competence.setValid(true);
        competence.setTdsRadiologist(radiologist);
        radiologist.getRadCompetenceList().add(competence);

        reg = new RegLicQualOwnershipDTO();
        reg.setActive(true);
        reg.setCertificateNumber("certificatenumber");
        reg.setRegLicQual((RegLicQualDTO) session.get(RegLicQualDTO.class, (long) 3));
        reg.setValidBegin(new Date());
        reg.setValidEnd(new Date());
        reg.setTdsRadiologist(radiologist);
        radiologist.getRegLicQualOwnershipList().add(reg);

        ca = new CompanyAssignmentDTO();
        ca.setTdsRadiologist(radiologist);
        ca.setStarted(new Date());
        ca.setEnded(new Date());
        ca.setActive(true);
        ca.setCompany((CompanyDTO) session.get(CompanyDTO.class, (long) 1));
        radiologist.getCompanyAssignmentList().add(ca);

        wp = new WorkPlaceDTO();
        wp.setTdsRadiologist(radiologist);
        wp.setHospital((HospitalDTO) session.get(HospitalDTO.class, (long) 1));
        wp.setAdded(new Date());
        wp.setFromDate(new Date());
        wp.setToDate(new Date());
        radiologist.getWorkPlaceList().add(wp);

        ra = new RadAvailabilityDTO();
        ra.setTdsRadiologist(radiologist);
        ra.setAdded(new Date());
        ra.setConfirmedByPM(false);
        ra.setConfirmedByRad(false);
        ra.setValid(false);
        ra.setFromDate(new Date());
        ra.setToDate(new Date());
        ra.setNormalAvailabilityPerWeek(new AvailabilityPerWeekDTO(1, 1, 1, 1, 1, 1, 1));
        ra.setMaxAvailabilityPerWeek(new AvailabilityPerWeekDTO(1, 1, 1, 1, 1, 1, 1));
        radiologist.getAvailabilityList().add(ra);
        session.save(radiologist);


        //radiologist 3
        radiologist = new TDSRadiologistDTO();

        user = new UserDTO();
        user.setName("Radiologist Name3");
        user.setLoginName("tds.radiologist3");
        user.setPassword(SecurityUtil.create().getPasswordHash("tds1"));
        user.setSex(UserSex.female.name());
        user.setTdsEmail("tds@hss-med.eu");
        user.setWorkEmail("workemail");
        user.setWorkTel("worktel");
        user.setAddingDateTime(new Date());
        user.setActive(true);
        user.setGetsSystemEmails(true);
        user.setUserType(UserType.itsRadiologist.name());

        radiologist.setUserInfo(user);
        radiologist.setAccountNumber("accountnumber");
        radiologist.setCreditPoints(1);
        radiologist.setCreditValidity(new Date());
        radiologist.setDefaultMaxAvailabilityHrsPerWeekDay(2);
        radiologist.setDefaultMaxAvailabilityHrsPerWeekendDay(3);
        radiologist.setDefaultNormalAvailabilityHrsPerWeekDay(4);
        radiologist.setDefaultNormalAvailabilityHrsPerWeekendDay(5);
        radiologist.setEnglishAllowed(true);
        radiologist.setEnglishLanguageCompetence(3);
        radiologist.setEnglishSalaryRate(123.0);
        radiologist.setHungarianLanguageCompetence(5);
        radiologist.setHungarianSalaryRate(456.0);
        radiologist.setInProbation(true);
        radiologist.setLastCheck(new Date());
        radiologist.setRadInvoiceClosingDay(7);
        radiologist.setRadPaymentDay(7);
        radiologist.setReportSendingCode("reportsendingcode");
        radiologist.setWorkDemandPriority(9);

        competence = new RadCompetenceDTO();
        competence.setAddingDateTime(new Date());
        competence.setBodyRegion((BodyRegionDTO) session.get(BodyRegionDTO.class, (long) 6));
        competence.setModality((ModalityDTO) session.get(ModalityDTO.class, (long) 2));
        competence.setCompetenceLevel(12);
        competence.setValid(true);
        competence.setTdsRadiologist(radiologist);
        radiologist.getRadCompetenceList().add(competence);

        reg = new RegLicQualOwnershipDTO();
        reg.setActive(true);
        reg.setCertificateNumber("certificatenumber");
        reg.setRegLicQual((RegLicQualDTO) session.get(RegLicQualDTO.class, (long) 3));
        reg.setValidBegin(new Date());
        reg.setValidEnd(new Date());
        reg.setTdsRadiologist(radiologist);
        radiologist.getRegLicQualOwnershipList().add(reg);

        ca = new CompanyAssignmentDTO();
        ca.setTdsRadiologist(radiologist);
        ca.setStarted(new Date());
        ca.setEnded(new Date());
        ca.setActive(true);
        ca.setCompany((CompanyDTO) session.get(CompanyDTO.class, (long) 1));
        radiologist.getCompanyAssignmentList().add(ca);

        wp = new WorkPlaceDTO();
        wp.setTdsRadiologist(radiologist);
        wp.setHospital((HospitalDTO) session.get(HospitalDTO.class, (long) 1));
        wp.setAdded(new Date());
        wp.setFromDate(new Date());
        wp.setToDate(new Date());
        radiologist.getWorkPlaceList().add(wp);

        ra = new RadAvailabilityDTO();
        ra.setTdsRadiologist(radiologist);
        ra.setAdded(new Date());
        ra.setConfirmedByPM(false);
        ra.setConfirmedByRad(false);
        ra.setValid(false);
        ra.setFromDate(new Date());
        ra.setToDate(new Date());
        ra.setNormalAvailabilityPerWeek(new AvailabilityPerWeekDTO(1, 1, 1, 1, 1, 1, 1));
        ra.setMaxAvailabilityPerWeek(new AvailabilityPerWeekDTO(1, 1, 1, 1, 1, 1, 1));
        radiologist.getAvailabilityList().add(ra);
        session.save(radiologist);


        //radiologist 3
        radiologist = new TDSRadiologistDTO();

        user = new UserDTO();
        user.setName("Radiologist Name4");
        user.setLoginName("tds.radiologist4");
        user.setPassword(SecurityUtil.create().getPasswordHash("tds1"));
        user.setSex(UserSex.female.name());
        user.setTdsEmail("tds@hss-med.eu");
        user.setWorkEmail("workemail");
        user.setWorkTel("worktel");
        user.setAddingDateTime(new Date());
        user.setActive(true);
        user.setGetsSystemEmails(true);
        user.setUserType(UserType.itsRadiologist.name());

        radiologist.setUserInfo(user);
        radiologist.setAccountNumber("accountnumber");
        radiologist.setCreditPoints(1);
        radiologist.setCreditValidity(new Date());
        radiologist.setDefaultMaxAvailabilityHrsPerWeekDay(2);
        radiologist.setDefaultMaxAvailabilityHrsPerWeekendDay(3);
        radiologist.setDefaultNormalAvailabilityHrsPerWeekDay(4);
        radiologist.setDefaultNormalAvailabilityHrsPerWeekendDay(5);
        radiologist.setEnglishAllowed(true);
        radiologist.setEnglishLanguageCompetence(3);
        radiologist.setEnglishSalaryRate(123.0);
        radiologist.setHungarianLanguageCompetence(5);
        radiologist.setHungarianSalaryRate(456.0);
        radiologist.setInProbation(true);
        radiologist.setLastCheck(new Date());
        radiologist.setRadInvoiceClosingDay(7);
        radiologist.setRadPaymentDay(7);
        radiologist.setReportSendingCode("reportsendingcode");
        radiologist.setWorkDemandPriority(9);

        competence = new RadCompetenceDTO();
        competence.setAddingDateTime(new Date());
        competence.setBodyRegion((BodyRegionDTO) session.get(BodyRegionDTO.class, (long) 6));
        competence.setModality((ModalityDTO) session.get(ModalityDTO.class, (long) 2));
        competence.setCompetenceLevel(12);
        competence.setValid(true);
        competence.setTdsRadiologist(radiologist);
        radiologist.getRadCompetenceList().add(competence);

        reg = new RegLicQualOwnershipDTO();
        reg.setActive(true);
        reg.setCertificateNumber("certificatenumber");
        reg.setRegLicQual((RegLicQualDTO) session.get(RegLicQualDTO.class, (long) 3));
        reg.setValidBegin(new Date());
        reg.setValidEnd(new Date());
        reg.setTdsRadiologist(radiologist);
        radiologist.getRegLicQualOwnershipList().add(reg);

        ca = new CompanyAssignmentDTO();
        ca.setTdsRadiologist(radiologist);
        ca.setStarted(new Date());
        ca.setEnded(new Date());
        ca.setActive(true);
        ca.setCompany((CompanyDTO) session.get(CompanyDTO.class, (long) 1));
        radiologist.getCompanyAssignmentList().add(ca);

        wp = new WorkPlaceDTO();
        wp.setTdsRadiologist(radiologist);
        wp.setHospital((HospitalDTO) session.get(HospitalDTO.class, (long) 1));
        wp.setAdded(new Date());
        wp.setFromDate(new Date());
        wp.setToDate(new Date());
        radiologist.getWorkPlaceList().add(wp);

        ra = new RadAvailabilityDTO();
        ra.setTdsRadiologist(radiologist);
        ra.setAdded(new Date());
        ra.setConfirmedByPM(false);
        ra.setConfirmedByRad(false);
        ra.setValid(false);
        ra.setFromDate(new Date());
        ra.setToDate(new Date());
        ra.setNormalAvailabilityPerWeek(new AvailabilityPerWeekDTO(1, 1, 1, 1, 1, 1, 1));
        ra.setMaxAvailabilityPerWeek(new AvailabilityPerWeekDTO(1, 1, 1, 1, 1, 1, 1));
        radiologist.getAvailabilityList().add(ra);
        session.save(radiologist);

    }

    private void loadTestRoles() {
//        RoleDTO role = new RoleDTO();
//        role.setName("Manager");
//        role.setCanHave(RoleCanHave.HospitalStaff.name());
//        session.save(role);
//
//        role = new RoleDTO();
//        role.setName("Guest");
//        role.setCanHave(RoleCanHave.TDSManager.name());
//        session.save(role);
//
//        role = new RoleDTO();
//        role.setName("IT");
//        role.setCanHave(RoleCanHave.TDSRadiologist.name());
//        session.save(role);

        RoleAssignmentDTO ra = new RoleAssignmentDTO();
        ra.setAdded(new Date());
        //ra.setStarted(new Date());
        ra.setActive(true);
        //ra.setTdsUser((UserDTO) session.get(UserDTO.class, 1));
        //ra.setTdsRole((RoleDTO) session.get(RoleDTO.class, 1));
        //session.save(ra);
    }

    private void loadTestHospital() {
            System.out.println("loading test hospital...");
            HospitalDTO hospital = new HospitalDTO();
            hospital.setAbbrevName("abbrev name");
            hospital.setActive(true);
            hospital.setAddingDate(new Date());
            hospital.setAddress("address");
            hospital.setGovAuthority((GoverningAuthorityDTO) session.get(GoverningAuthorityDTO.class, (long) 1));
            hospital.setGovBody("gov body");
            hospital.setOfficialName("official name");
            hospital.setQualityRequirements(1);
            hospital.setShortName("short name");
            hospital.setRegion((RegionDTO) session.get(RegionDTO.class, (long) 1));

            ContactPersonDTO cp = new ContactPersonDTO();
            cp.setHospital(hospital);
            cp.setCorrespAddress("address");
            cp.setDepartment("dep");
            cp.setEmail("a@a.a");
            cp.setFax("ember fax");
            cp.setName("ember neve");
            cp.setPlaceOfFax("fax helye");
            cp.setPositionInDept("pozíció");
            cp.setSkype("skype név");
            cp.setTel("telefonszám");
            hospital.getContactPersonList().add(cp);
            session.save(hospital);
    }

    private void loadTestHospitalStaff() {
        System.out.println("loading test hospitalStaff...");
        //1
        HospitalStaffDTO hospitalStaff = new HospitalStaffDTO();
        UserDTO user = new UserDTO();
        user.setName("HospitalStaff Name");
        user.setLoginName("hospital.staff");
        user.setPassword(SecurityUtil.create().getPasswordHash("tds1"));
        user.setSex(UserSex.male.name());
        user.setTdsEmail("tds@hss-med.eu");
        user.setWorkEmail("workemail");
        user.setWorkTel("worktel");
        user.setAddingDateTime(new Date());
        user.setActive(true);
        user.setGetsSystemEmails(true);
        user.setUserType(UserType.hospitalStaff.name());
        hospitalStaff.setUserInfo(user);
        hospitalStaff.setHospital((HospitalDTO) session.get(HospitalDTO.class, (long) 1));
        hospitalStaff.setPositionInHosp("position in hosp");
        hospitalStaff.setRoleWithTDS("role with tds");
        hospitalStaff.getLanguageList().add((LanguageDTO) session.get(LanguageDTO.class, (long) 1));
        hospitalStaff.getLanguageList().add((LanguageDTO) session.get(LanguageDTO.class, (long) 2));
        session.save(hospitalStaff);

        //2
        hospitalStaff = new HospitalStaffDTO();
        user = new UserDTO();
        user.setName("HospitalStaff Name2");
        user.setLoginName("hospital.staff2");
        user.setPassword(SecurityUtil.create().getPasswordHash("tds1"));
        user.setSex(UserSex.male.name());
        user.setTdsEmail("tds@hss-med.eu");
        user.setWorkEmail("workemail");
        user.setWorkTel("worktel");
        user.setAddingDateTime(new Date());
        user.setActive(true);
        user.setGetsSystemEmails(true);
        user.setUserType(UserType.hospitalStaff.name());
        hospitalStaff.setUserInfo(user);
        hospitalStaff.setHospital((HospitalDTO) session.get(HospitalDTO.class, (long) 1));
        hospitalStaff.setPositionInHosp("position in hosp");
        hospitalStaff.setRoleWithTDS("role with tds");
        hospitalStaff.getLanguageList().add((LanguageDTO) session.get(LanguageDTO.class, (long) 1));
        hospitalStaff.getLanguageList().add((LanguageDTO) session.get(LanguageDTO.class, (long) 2));
        session.save(hospitalStaff);

        //3
        hospitalStaff = new HospitalStaffDTO();
        user = new UserDTO();
        user.setName("HospitalStaff Name3");
        user.setLoginName("hospital.staff3");
        user.setPassword(SecurityUtil.create().getPasswordHash("tds1"));
        user.setSex(UserSex.male.name());
        user.setTdsEmail("tds@hss-med.eu");
        user.setWorkEmail("workemail");
        user.setWorkTel("worktel");
        user.setAddingDateTime(new Date());
        user.setActive(true);
        user.setGetsSystemEmails(true);
        user.setUserType(UserType.hospitalStaff.name());
        hospitalStaff.setUserInfo(user);
        hospitalStaff.setHospital((HospitalDTO) session.get(HospitalDTO.class, (long) 1));
        hospitalStaff.setPositionInHosp("position in hosp");
        hospitalStaff.setRoleWithTDS("role with tds");
        hospitalStaff.getLanguageList().add((LanguageDTO) session.get(LanguageDTO.class, (long) 1));
        hospitalStaff.getLanguageList().add((LanguageDTO) session.get(LanguageDTO.class, (long) 2));
        session.save(hospitalStaff);


    }

    private void loadTestTDSManager() {
        System.out.println("loading test tdsmanager...");
        //1
        TDSManagerDTO tdsManager = new TDSManagerDTO();
        UserDTO user = new UserDTO();
        user.setName("TDSManager Name");
        user.setLoginName("tds.manager");
        user.setPassword(SecurityUtil.create().getPasswordHash("tds1"));
        user.setSex(UserSex.male.name());
        user.setTdsEmail("tds@hss-med.eu");
        user.setWorkEmail("workemail");
        user.setWorkTel("worktel");
        user.setAddingDateTime(new Date());
        user.setActive(true);
        user.setUserType(UserType.itsManager.name());
        user.setGetsSystemEmails(true);
        tdsManager.setUserInfo(user);

        TDSManagerRoleAssignmentDTO assignmentDTO = new TDSManagerRoleAssignmentDTO();
        assignmentDTO.setAdded(new Date());
        assignmentDTO.setAssignmentStart(new Date());
        assignmentDTO.setPlannedEnd(new Date());
        assignmentDTO.setTdsManager(tdsManager);
        assignmentDTO.setTdsManagerRole((TDSManagerRoleDTO) session.get(TDSManagerRoleDTO.class, (long) 1));
        tdsManager.getRoleAssignmentList().add(assignmentDTO);

        assignmentDTO = new TDSManagerRoleAssignmentDTO();
        assignmentDTO.setAdded(new Date());
        assignmentDTO.setAssignmentStart(new Date());
        assignmentDTO.setPlannedEnd(new Date());
        assignmentDTO.setTdsManager(tdsManager);
        assignmentDTO.setTdsManagerRole((TDSManagerRoleDTO) session.get(TDSManagerRoleDTO.class, (long) 3));
        tdsManager.getRoleAssignmentList().add(assignmentDTO);

        session.save(tdsManager);

        //2
        tdsManager = new TDSManagerDTO();
        user = new UserDTO();
        user.setName("TDSManager Name2");
        user.setLoginName("tds.manager2");
        user.setPassword(SecurityUtil.create().getPasswordHash("tds1"));
        user.setSex(UserSex.male.name());
        user.setTdsEmail("tds@hss-med.eu");
        user.setWorkEmail("workemail");
        user.setWorkTel("worktel");
        user.setAddingDateTime(new Date());
        user.setActive(true);
        user.setUserType(UserType.itsManager.name());
        user.setGetsSystemEmails(true);
        tdsManager.setUserInfo(user);

        assignmentDTO = new TDSManagerRoleAssignmentDTO();
        assignmentDTO.setAdded(new Date());
        assignmentDTO.setAssignmentStart(new Date());
        assignmentDTO.setPlannedEnd(new Date());
        assignmentDTO.setTdsManager(tdsManager);
        assignmentDTO.setTdsManagerRole((TDSManagerRoleDTO) session.get(TDSManagerRoleDTO.class, (long) 1));
        tdsManager.getRoleAssignmentList().add(assignmentDTO);

        assignmentDTO = new TDSManagerRoleAssignmentDTO();
        assignmentDTO.setAdded(new Date());
        assignmentDTO.setAssignmentStart(new Date());
        assignmentDTO.setPlannedEnd(new Date());
        assignmentDTO.setTdsManager(tdsManager);
        assignmentDTO.setTdsManagerRole((TDSManagerRoleDTO) session.get(TDSManagerRoleDTO.class, (long) 3));
        tdsManager.getRoleAssignmentList().add(assignmentDTO);

        session.save(tdsManager);


        //3
        tdsManager = new TDSManagerDTO();
        user = new UserDTO();
        user.setName("TDSManager Name3");
        user.setLoginName("tds.manager3");
        user.setPassword(SecurityUtil.create().getPasswordHash("tds1"));
        user.setSex(UserSex.male.name());
        user.setTdsEmail("tds@hss-med.eu");
        user.setWorkEmail("workemail");
        user.setWorkTel("worktel");
        user.setAddingDateTime(new Date());
        user.setActive(true);
        user.setUserType(UserType.itsManager.name());
        user.setGetsSystemEmails(true);
        tdsManager.setUserInfo(user);

        assignmentDTO = new TDSManagerRoleAssignmentDTO();
        assignmentDTO.setAdded(new Date());
        assignmentDTO.setAssignmentStart(new Date());
        assignmentDTO.setPlannedEnd(new Date());
        assignmentDTO.setTdsManager(tdsManager);
        assignmentDTO.setTdsManagerRole((TDSManagerRoleDTO) session.get(TDSManagerRoleDTO.class, (long) 1));
        tdsManager.getRoleAssignmentList().add(assignmentDTO);

        assignmentDTO = new TDSManagerRoleAssignmentDTO();
        assignmentDTO.setAdded(new Date());
        assignmentDTO.setAssignmentStart(new Date());
        assignmentDTO.setPlannedEnd(new Date());
        assignmentDTO.setTdsManager(tdsManager);
        assignmentDTO.setTdsManagerRole((TDSManagerRoleDTO) session.get(TDSManagerRoleDTO.class, (long) 3));
        tdsManager.getRoleAssignmentList().add(assignmentDTO);

        session.save(tdsManager);

    }

    private void loadTestHospitalContract() {
            System.out.println("loading test hospital contract...");
            HospitalContractDTO hospitalContract = new HospitalContractDTO();
            hospitalContract.setActive(true);
            hospitalContract.setAdminFeeForCheckingUselessCase(1d);
            hospitalContract.setAdminFeeForManuallyEnteringData(1d);
            hospitalContract.setClosenessToDeadlineDaysWithConsHosp(6d);
            hospitalContract.setContractCode("contract code");
            hospitalContract.setContractType((ContractTypeDTO) session.get(ContractTypeDTO.class, (long) 1));
            hospitalContract.setCredit(1);
            hospitalContract.setCurrency((CurrencyDTO) session.get(CurrencyDTO.class, (long) 1));
            hospitalContract.setHospital((HospitalDTO) session.get(HospitalDTO.class, (long) 1));
            hospitalContract.setIntendedEndDateTime(new Date());
            hospitalContract.setInvoicePeriodDay(1);
            hospitalContract.setInvoiceProductionDay(1);
            hospitalContract.setLastCaseAcceptanceDateTime(new Date());
            hospitalContract.setMaxRequiredAvailabilityPerWeek(new AvailabilityPerWeekDTO(1, 1, 1, 1, 1, 1, 1));
            hospitalContract.setMinRequiredAvailabilityPerWeek(new AvailabilityPerWeekDTO(1, 1, 1, 1, 1, 1, 1));
            hospitalContract.setNormalClosenessToDeadlineDaysHosp(3d);
            hospitalContract.setNormalWorkTimeDays(5d);
            hospitalContract.setSignedDate(new Date());
            hospitalContract.setSignersName("signers name");
            hospitalContract.setStartDateTime(new Date());
            hospitalContract.setStopDateTime(new Date());
            hospitalContract.setWorkTimeDaysWithConsultation(6d);

            OptionAssignmentDTO optionAssigment = new OptionAssignmentDTO();
            optionAssigment.setHospitalContract(hospitalContract);
            optionAssigment.setValidStart(new Date());
            optionAssigment.setOption((HospContrOptionDTO) session.get(HospContrOptionDTO.class, (long) 1));
            hospitalContract.getOptionList().add(optionAssigment);

            optionAssigment = new OptionAssignmentDTO();
            optionAssigment.setHospitalContract(hospitalContract);
            optionAssigment.setValidStart(new Date());
            optionAssigment.setOption((HospContrOptionDTO) session.get(HospContrOptionDTO.class, (long) 2));
            hospitalContract.getOptionList().add(optionAssigment);


            optionAssigment = new OptionAssignmentDTO();
            optionAssigment.setHospitalContract(hospitalContract);
            optionAssigment.setValidStart(new Date());
            optionAssigment.setOption((HospContrOptionDTO) session.get(HospContrOptionDTO.class, (long) 3));
            hospitalContract.getOptionList().add(optionAssigment);

            AvailabilityPerPeriodDTO app = new AvailabilityPerPeriodDTO(1, 1, 1, 1, 1, 1, 1);
            app.setStarted(new Date());
            app.setEnded(new Date());
            app.setHospitalContract(hospitalContract);
            hospitalContract.getAvailabilityPerPeriodList().add(app);

            app = new AvailabilityPerPeriodDTO(2, 2, 2, 2, 2, 2, 2);
            app.setStarted(new Date());
            app.setEnded(new Date());
            app.setHospitalContract(hospitalContract);
            hospitalContract.getAvailabilityPerPeriodList().add(app);

            ContactPersonAssignmentDTO cpa = new ContactPersonAssignmentDTO();
            cpa.setHospitalContract(hospitalContract);
            cpa.setContactPerson((ContactPersonDTO) session.get(ContactPersonDTO.class, (long) 1));
            cpa.setContactPosition("string");
            cpa.setContactType(ContactPersonType.emergency.name());
            cpa.setFinished(new Date());
            cpa.setStarted(new Date());
            cpa.setInactivation(new Date());
            hospitalContract.getContactPersonAssignmentList().add(cpa);
            session.save(hospitalContract);



            WorkBandTableDTO wbt = new WorkBandTableDTO();
            wbt.setHospitalContract(hospitalContract);


            //session.save(wbt);

    }

    private void getTestHospitalContract() {
        WorkBandTableDTO wbt = (WorkBandTableDTO) session.get(WorkBandTableDTO.class, (long) 1);
    }

    private void loadTestTDSServices() {
        System.out.println("loading test tdsServices...");
        TDSServiceDTO tDSServiceDTO = new TDSServiceDTO("getCaseList");
        tDSServiceDTO.getRoleList().add((RoleDTO) session.get(RoleDTO.class, (long) 1));
        tDSServiceDTO.getRoleList().add((RoleDTO) session.get(RoleDTO.class, (long) 2));
        tDSServiceDTO.getRoleList().add((RoleDTO) session.get(RoleDTO.class, (long) 3));
        session.save(tDSServiceDTO);
    }

    private void loadTestTDSCase() {
        System.out.println("loading test tdsCase...");
        CaseDTO tdsCase = new CaseDTO();
        tdsCase.setAcceptedAndAssigned(new Date());
        tdsCase.setCaseStatus((CaseStatusDTO) session.get(CaseStatusDTO.class, 1));
        tdsCase.setConfirmedDone(new Date());
        tdsCase.setHospTakes(new Date());
        tdsCase.setTransferStarted(new Date());
        tdsCase.setHospitalContract((HospitalContractDTO) session.get(HospitalContractDTO.class, (long) 1));
        tdsCase.setModeOfAcquisition((ModeOfAcquisitionDTO) session.get(ModeOfAcquisitionDTO.class, (long) 1));
        tdsCase.setRejectedByTDS(new Date());
        tdsCase.setTransferredToServer(new Date());
        tdsCase.setReceiverHospitalStaff((HospitalStaffDTO) session.get(HospitalStaffDTO.class, (long) 1));
        tdsCase.setSenderHospitalStaff((HospitalStaffDTO) session.get(HospitalStaffDTO.class, (long) 1));

        PatientAndReferralInfoDTO pafi = new PatientAndReferralInfoDTO();
//        pafi.setDicomPatientData(
//                new DicomPatientDataDTO(
//                "rs.getString(1)", "rs.getString(2)", "rs.getString(3)",
//                "rs.getString(4)", "rs.getString(5)",
//                new Date(), "rs.getString(7)",
//                "rs.getString(8)", "rs.getString(9)", "rs.getString(10)",
//                "rs.getString(11)", "rs.getString(12)"));
        pafi.setElectronicPatientData(
                new ElectronicPatientDataDTO(
                "rs.getString(1)", "rs.getString(2)", "rs.getString(3)",
                "rs.getString(4)", "rs.getString(5)",
                new Date(), "rs.getString(7)",
                "rs.getString(8)", "rs.getString(9)", "rs.getString(10)",
                "rs.getString(11)", "rs.getString(12)"));
        pafi.setElectronicReferral(
                new ElectronicReferralDTO(
                "rs.getString(1)", new Date(), "rs.getString(3)",
                "rs.getString(4)", "rs.getString(5)",
                "rs.getString(6)", "rs.getString(7)",
                "rs.getString(8)", "rs.getString(9)", "rs.getString(10)",
                "rs.getString(11)", "rs.getString(12)",
                "rs.getString(13)", "rs.getString(14)", "rs.getString(15)",
                "rs.getString(16)", "rs.getString(17)", "rs.getString(18)",
                "rs.getString(19)", "rs.getString(20)", "rs.getString(21)",
                "rs.getString(22)", "rs.getString(23)"));
        pafi.setAgreementTest("agreement test");
        pafi.setAgreementTestDateTime(new Date());
        pafi.setCaseItBelongsTo(tdsCase);
        tdsCase.setDataProcLog(new DataProcLogDTO());
        tdsCase.getDataProcLog().setCaseItBelongsTo(tdsCase);
        tdsCase.getDataProcLog().setStarted(new Date());

        Format formatter = new SimpleDateFormat("yyyy.MM.dd.hhmmssSSS");
        tdsCase.setTdsCaseUniqueId(
                tdsCase.getHospitalContract().getHospital().getAbbrevName()
                + formatter.format(tdsCase.getTransferredToServer()));

        Calendar c = Calendar.getInstance();
        c.setTime(tdsCase.getTransferredToServer());
        c.add(Calendar.DATE, 1);
        tdsCase.setDeadLine(c.getTime());

        session.save(tdsCase);
    }
}
