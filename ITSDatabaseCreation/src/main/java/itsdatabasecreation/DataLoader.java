/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itsdatabasecreation;

import casemodule.dto.SystemMessageTypeClass;
import casemodule.entity.AutoFunctionType;
import casemodule.entity.ScenarioPolicy;
import hospitalmodule.dto.AvailabilityPerPeriodDTO;
import hospitalmodule.dto.AvailabilityPerWeekDTO;
import hospitalmodule.dto.ContactPersonAssignmentDTO;
import hospitalmodule.dto.ContactPersonDTO;
import hospitalmodule.dto.ContactPersonType;
import hospitalmodule.dto.HospitalContractDTO;
import hospitalmodule.dto.HospitalDTO;
import hospitalmodule.dto.OptionAssignmentDTO;
import hospitalmodule.dto.WorkBandTableDTO;
import hospitalmodule.entity.ContractType;
import hospitalmodule.entity.GoverningAuthority;
import hospitalmodule.entity.Hospital;
import hospitalmodule.entity.HospitalContract;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import masterdatamodule.dto.ContractTypeDTO;
import masterdatamodule.dto.CurrencyDTO;
import masterdatamodule.dto.HospContrOptionDTO;
import masterdatamodule.entity.BodyRegion;
import masterdatamodule.entity.CaseStatus;
import masterdatamodule.entity.Country;
import masterdatamodule.entity.Currency;
import masterdatamodule.entity.HospContrOption;
import masterdatamodule.entity.ITSConstant;
import masterdatamodule.entity.ITSLanguage;
import masterdatamodule.entity.Modality;
import masterdatamodule.entity.ModeOfAcquisition;
import masterdatamodule.entity.RegLicQual;
import masterdatamodule.entity.RegLicQualType;
import masterdatamodule.entity.Region;
import masterdatamodule.entity.Scenario;
import masterdatamodule.entity.SequenceCategory;
import masterdatamodule.entity.WayOfClosing;
import radiologistmodule.entity.AvailabilityPerWeek;
import radiologistmodule.entity.ITSRadiologist;
import reportingmodule.dto.AutoFunctionTypeClass;
import systemmodule.entity.SystemMessageType;
import usermodule.entity.HospitalStaff;
import usermodule.entity.HospitalStaffRole;
import usermodule.entity.ITSManager;
import usermodule.entity.ITSManagerRole;
import usermodule.entity.ITSManagerRoleAssignment;
import usermodule.entity.ITSRadiologistRole;
import usermodule.entity.ITSRole;
import usermodule.entity.ITSUser;
import usermodule.entity.UserSex;
import usermodule.entity.UserType;

/**
 *
 * @author vincze.attila
 */
public class DataLoader {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DataLoader.class);
    private EntityManager em;
    private EntityManager emGWS;
    private String DRIVER_NAME =
            "sun.jdbc.odbc.JdbcOdbcDriver";
    private String DATABASE_URL = "jdbc:odbc:ITSMasterData";
    private Properties prop;

    public static DataLoader create() {
        return new DataLoader();
    }

    public DataLoader() {
        prop = new java.util.Properties();
        prop.put("charSet", "cp1250");
        em = DataUtil.factory.createEntityManager();
        emGWS = DataUtil.factoryGWS.createEntityManager();
    }

    public void loadGWS() {
        emGWS.getTransaction().begin();
        emGWS.getTransaction().commit();
    }

    public void load() {
        em.getTransaction().begin();
        loadSystemMessageType();
        loadITSConstants();
        loadGoverningAuthorities();
        loadContractTypes();
        loadAutoFunctionTypes();
        loadLanguages();
        loadModalities();
        loadRoles();
        loadRoleCanAssignToUser();
        loadRolePrerequisites();
        loadBodyRegions();
        loadSequenceCategories();
        loadHospContrOptions();
        loadRegLicQualTypes();
        loadModeOfAcquisitions();
        loadCaseStatuses();
        loadWayOfClosings();
        loadScenarios();
        loadScenarioPolicies();
        loadCurrencies();
        loadCoutries();
        loadRegions();
        loadRegLicQuals();
        loadTestITSManager();
        loadTestHospital();
        loadTestHospitalStaff();
        loadTestHospitalContract();
        loadTestITSRadiologist();
        loadGWSUser();
        em.getTransaction().commit();


        loadGWS();
    }

    private void loadTestHospitalContract() {
        log.info("loading test hospital contract...");
        HospitalContract hospitalContract = new HospitalContract();
        hospitalContract.setActive(true);
        hospitalContract.setAdminFeeForCheckingUselessCase(1d);
        hospitalContract.setAdminFeeForManuallyEnteringData(1d);
        hospitalContract.setClosenessToDeadlineDaysWithConsHosp(6d);
        hospitalContract.setContractCode("contract code");
        hospitalContract.setContractType(em.find(ContractType.class, Long.valueOf(1)));
        hospitalContract.setCredit(1);
        hospitalContract.setCurrency(em.find(Currency.class, Long.valueOf(1)));
        hospitalContract.setHospital(em.find(Hospital.class, Long.valueOf(1)));
        hospitalContract.setIntendedEndDateTime(new Date());
        hospitalContract.setInvoicePeriodDay(1);
        hospitalContract.setInvoiceProductionDay(1);
        hospitalContract.setLastCaseAcceptanceDateTime(new Date());
        hospitalContract.setNormalClosenessToDeadlineDaysHosp(3d);
        hospitalContract.setNormalWorkTimeDays(5d);
        hospitalContract.setSignedDate(new Date());
        hospitalContract.setSignersName("signers name");
        hospitalContract.setStartDateTime(new Date());
        hospitalContract.setStopDateTime(new Date());
        hospitalContract.setWorkTimeDaysWithConsultation(6d);
        em.persist(hospitalContract);


    }

    private void loadSystemMessageType() {
        log.info("loading system message type...");
        SystemMessageType systemMessageType = new SystemMessageType();
        systemMessageType.setMessageClass(SystemMessageTypeClass.IT.name());
        systemMessageType.setMessageType("SystemMessageType1");
        systemMessageType.setPriority(1);
        em.persist(systemMessageType);
    }

    private void loadITSConstants() {
        log.info("loading ITS constants...");
        em.persist(new ITSConstant("Max record number", 135));
        em.persist(new ITSConstant("A kórház nem veszi át a leletet", 1));
        em.persist(new ITSConstant("Munkalista frissítési periodusideje", 30));
    }

    private void loadGoverningAuthorities() {
        log.info("loading governing authorities...");
        em.persist(new GoverningAuthority("Authority 1"));
        em.persist(new GoverningAuthority("Authority 2"));
        em.persist(new GoverningAuthority("Authority 3"));
        em.persist(new GoverningAuthority("Authority 4"));
        em.persist(new GoverningAuthority("Authority 5"));
        em.persist(new GoverningAuthority("Authority 6"));
    }

    private void loadContractTypes() {
        log.info("loading contract types...");
        em.persist(new ContractType("Contract type 1", "Szerződés típus 1"));
        em.persist(new ContractType("Contract type 2", "Szerződés típus 2"));
        em.persist(new ContractType("Contract type 3", "Szerződés típus 3"));
        em.persist(new ContractType("Contract type 4", "Szerződés típus 4"));
        em.persist(new ContractType("Contract type 5", "Szerződés típus 5"));
        em.persist(new ContractType("Contract type 6", "Szerződés típus 6"));
    }

    private void loadAutoFunctionTypes() {
        log.info("loading autofunction types...");
        em.persist(new AutoFunctionType("Archiving", AutoFunctionTypeClass.archiving.name()));
        em.persist(new AutoFunctionType("Basic", AutoFunctionTypeClass.basic.name()));
        em.persist(new AutoFunctionType("Scenario", AutoFunctionTypeClass.scenario.name()));
    }

    private void loadLanguages() {
        log.info("loading languages...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [Language$]");
            while (rs.next()) {
                em.persist(new ITSLanguage(rs.getString(1), rs.getString(2)));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            log.error(ex);
        } catch (ClassNotFoundException ex) {
            log.error(ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                log.error(ex);
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
                em.persist(new Modality(rs.getString(1)));
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
        log.info("loading roles...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [Role$]");
            while (rs.next()) {
                String canHave = rs.getString(5);
                if ("ITSManager".equals(canHave)) {
                    em.persist(
                            new ITSManagerRole(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getString(4),
                            canHave, rs.getString(6), rs.getString(7)));
                } else if ("ITSRadiologist".equals(canHave)) {
                    em.persist(
                            new ITSRadiologistRole(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getString(4),
                            canHave, rs.getString(6), rs.getString(7)));
                } else {
                    em.persist(
                            new HospitalStaffRole(rs.getString(1), rs.getString(2),
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
        log.info("loading roleCanAssignToUser...");
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
                //ITSRole role = (ITSRole) em.find(ITSRole.class, i);
                ITSRole role =
                        em.createQuery("select r from ITSRole r where r.abbreviation = :abbreviation", ITSRole.class).setParameter("abbreviation", rs.getString(4)).getSingleResult();
                for (int j = 0; j < array.length; j++) {
                    String string = array[j].trim();
                    try {
                        ITSRole r =
                                em.createQuery("select r from ITSRole r where r.abbreviation = :abbreviation", ITSRole.class).setParameter("abbreviation", string).getSingleResult();
                        role.getWhoCanAssignList().add(r);
                        em.merge(role);
                    } catch (NonUniqueResultException e) {
                        log.error(e);
                    }
                }
                em.merge(role);
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
        log.info("loading rolePrerequisites...");
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
                //ITSRole role = (ITSRole) em.find(ITSRole.class, i);
                ITSRole role =
                        em.createQuery("select r from ITSRole r where r.abbreviation = :abbreviation", ITSRole.class).setParameter("abbreviation", rs.getString(4)).getSingleResult();

                for (int j = 0; j < array.length; j++) {
                    String string = array[j].trim();
                    try {
                        ITSRole r =
                                em.createQuery("select r from ITSRole r where r.abbreviation like :abbreviation", ITSRole.class).setParameter("abbreviation", string).getSingleResult();
                        role.getPrerequisiteList().add(r);
                        em.merge(role);
                    } catch (NonUniqueResultException e) {
                        log.error(e);
                    }
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

    private void loadBodyRegions() {
        log.info("loading body regions...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [BodyRegion$]");
            while (rs.next()) {
                em.persist(new BodyRegion(rs.getString(2), rs.getString(1), rs.getString(3), rs.getString(4)));
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
        log.info("loading sequence categories...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [SequenceCategory$]");
            while (rs.next()) {
                em.persist(new SequenceCategory(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
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
        log.info("loading hospital contract options...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [HospContrOption$]");
            while (rs.next()) {
                em.persist(new HospContrOption(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            rs.close();
            st.close();
            long i = 3;
            HospContrOption hospContrOption =
                    new HospContrOption("standardHungarianRLQRequirements", "RegLicQual", "-");
            hospContrOption.setLongParameter(3L);
            em.persist(hospContrOption);

            i = 4;
            hospContrOption =
                    new HospContrOption("standardEnglishRLQRequirements", "RegLicQual", "-");
            hospContrOption.setLongParameter(4L);
            em.persist(hospContrOption);
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
        log.info("loading reglicqualtypes...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [RegLicQualType$]");
            while (rs.next()) {
                em.persist(new RegLicQualType(rs.getString(1)));
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
        log.info("loading mode of acquisitions...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [ModeOfAcquisition$]");
            while (rs.next()) {
                em.persist(new ModeOfAcquisition(rs.getString(1), rs.getString(2)));
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
        log.info("loading case statuses...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [CaseStatus$]");
            while (rs.next()) {
                CaseStatus caseStatus = new CaseStatus(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6));
                em.persist(caseStatus);
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
        log.info("loading way of closings...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [WayOfClosing$]");
            while (rs.next()) {
                em.persist(new WayOfClosing(rs.getString(1)));
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
        log.info("loading scenarios...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [Scenario$]");
            while (rs.next()) {
                em.persist(new Scenario(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getString(4), rs.getInt(8)));
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
        log.info("loading scenario policies...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [ScenarioPolicy$]");
            while (rs.next()) {
                em.persist(new ScenarioPolicy(
                        em.createQuery("select s from Scenario s where s.englishName = :englishName", Scenario.class).setParameter("englishName", rs.getString(1)).getSingleResult(),
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
        log.info("loading currencies...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [Currency$]");
            while (rs.next()) {
                em.persist(new Currency(rs.getString(1)));
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
        log.info("loading countries...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [Country$]");
            while (rs.next()) {
                em.persist(new Country(rs.getString(1), rs.getString(2),
                        em.createQuery("select c from Currency c where c.name = :name", Currency.class).setParameter("name", rs.getString(3)).getSingleResult()));
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
        log.info("loading regions...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [Region$]");
            while (rs.next()) {
                em.persist(new Region(rs.getString(1),
                        em.createQuery("select c from Country c where c.englishName = :englishName", Country.class).setParameter("englishName", rs.getString(2)).getSingleResult()));
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
        log.info("loading reglicquals...");
        Connection con = null;
        try {
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(DATABASE_URL, prop);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from [RegLicQual$]");
            while (rs.next()) {
                em.persist(new RegLicQual(
                        rs.getString(1),
                        "-".equals(rs.getString(3)) ? null : em.createQuery("select r from Region r where r.name = :name", Region.class).setParameter("name", rs.getString(3)).getSingleResult(),
                        "-".equals(rs.getString(4)) ? null : em.createQuery("select r from RegLicQualType r where r.name = :name", RegLicQualType.class).setParameter("name", rs.getString(4)).getSingleResult()));
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

    private void loadTestITSRadiologist() {
        log.info("loading test itsradiologists...");
        ITSRadiologist radiologist = new ITSRadiologist();
        ITSUser user = new ITSUser();
        user.setName("ITSRadiologist Name");
        user.setLoginName("its.radiologist");
        user.setPassword(SecurityUtil.create().getPasswordHash("tds1"));
        user.setSex(UserSex.male.name());
        user.setItsEmail("tds@hss-med.eu");
        user.setWorkEmail("workemail");
        user.setWorkTel("worktel");
        user.setAddingDateTime(new Date());
        user.setActive(true);
        user.setUserType(UserType.itsManager.name());
        user.setGetsSystemEmails(true);
        radiologist.setItsUser(user);

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

        em.persist(radiologist);
    }

    private void loadTestITSManager() {
        log.info("loading test itsmanagers...");
        //1
        ITSManager manager = new ITSManager();
        ITSUser user = new ITSUser();
        user.setName("TDSManager Name");
        user.setLoginName("tds.manager");
        user.setPassword(SecurityUtil.create().getPasswordHash("tds1"));
        user.setSex(UserSex.male.name());
        user.setItsEmail("tds@hss-med.eu");
        user.setWorkEmail("workemail");
        user.setWorkTel("worktel");
        user.setAddingDateTime(new Date());
        user.setActive(true);
        user.setUserType(UserType.itsManager.name());
        user.setGetsSystemEmails(true);
        manager.setItsUser(user);

        ITSManagerRoleAssignment assignment = new ITSManagerRoleAssignment();
        assignment.setAdded(new Date());
        assignment.setAssignmentStart(new Date());
        assignment.setPlannedEnd(new Date());
        assignment.setItsManager(manager);
        assignment.setItsManagerRole(
                em.createQuery("select r from ITSManagerRole r", ITSManagerRole.class).getResultList().get(0));
        manager.getItsManagerRoleAssignmentList().add(assignment);

        assignment = new ITSManagerRoleAssignment();
        assignment.setAdded(new Date());
        assignment.setAssignmentStart(new Date());
        assignment.setPlannedEnd(new Date());
        assignment.setItsManager(manager);
        assignment.setItsManagerRole(
                em.createQuery("select r from ITSManagerRole r", ITSManagerRole.class).getResultList().get(2));
        manager.getItsManagerRoleAssignmentList().add(assignment);

        em.persist(manager);

        //2.
        manager = new ITSManager();
        user = new ITSUser();
        user.setName("TDSManager Name1");
        user.setLoginName("tds.manager1");
        user.setPassword(SecurityUtil.create().getPasswordHash("tds1"));
        user.setSex(UserSex.male.name());
        user.setItsEmail("tds@hss-med.eu");
        user.setWorkEmail("workemail");
        user.setWorkTel("worktel");
        user.setAddingDateTime(new Date());
        user.setActive(true);
        user.setUserType(UserType.itsManager.name());
        user.setGetsSystemEmails(true);
        manager.setItsUser(user);
        em.persist(manager);

        //3.
        manager = new ITSManager();
        user = new ITSUser();
        user.setName("TDSManager Name2");
        user.setLoginName("tds.manager2");
        user.setPassword(SecurityUtil.create().getPasswordHash("tds1"));
        user.setSex(UserSex.male.name());
        user.setItsEmail("tds@hss-med.eu");
        user.setWorkEmail("workemail");
        user.setWorkTel("worktel");
        user.setAddingDateTime(new Date());
        user.setActive(true);
        user.setUserType(UserType.itsManager.name());
        user.setGetsSystemEmails(true);
        manager.setItsUser(user);
        em.persist(manager);

        //4.
        manager = new ITSManager();
        user = new ITSUser();
        user.setName("TDSManager Name3");
        user.setLoginName("tds.manager3");
        user.setPassword(SecurityUtil.create().getPasswordHash("tds1"));
        user.setSex(UserSex.male.name());
        user.setItsEmail("tds@hss-med.eu");
        user.setWorkEmail("workemail");
        user.setWorkTel("worktel");
        user.setAddingDateTime(new Date());
        user.setActive(true);
        user.setUserType(UserType.itsManager.name());
        user.setGetsSystemEmails(true);
        manager.setItsUser(user);
        em.persist(manager);

    }

    private void loadGWSUser() {
        log.info("loading gateway server user...");
        ITSUser user = new ITSUser();
        user.setName("Gateway Server");
        user.setLoginName("gateway.server");
        user.setPassword(SecurityUtil.create().getPasswordHash("tds1"));
        user.setSex(UserSex.male.name());
        user.setItsEmail("tds@hss-med.eu");
        user.setWorkEmail("workemail");
        user.setWorkTel("worktel");
        user.setAddingDateTime(new Date());
        user.setActive(true);
        user.setUserType(UserType.itsManager.name());
        user.setGetsSystemEmails(true);
        em.persist(user);
    }

    private void loadTestHospital() {
        log.info("loading test hospital...");
        Hospital hospital = new Hospital();
        hospital.setAbbrevName("abbrev name");
        hospital.setActive(true);
        hospital.setAddingDate(new Date());
        hospital.setAddress("address");
        hospital.setGoverningAuthority(
                em.createQuery("select g from GoverningAuthority g", GoverningAuthority.class).getResultList().get(0));
        hospital.setGovBody("gov body");
        hospital.setOfficialName("official name");
        hospital.setQualityRequirements(1);
        hospital.setShortName("short name");
        hospital.setRegion(
                em.createQuery("select r from Region r", Region.class).getResultList().get(0));
        em.persist(hospital);
    }

    private void loadTestHospitalStaff() {
        log.info("loading test hospitalStaff...");
        //1
        HospitalStaff hospitalStaff = new HospitalStaff();
        ITSUser user = new ITSUser();
        user.setName("HospitalStaff Name");
        user.setLoginName("hospital.staff");
        user.setPassword(SecurityUtil.create().getPasswordHash("tds1"));
        user.setSex(UserSex.male.name());
        user.setItsEmail("tds@hss-med.eu");
        user.setWorkEmail("workemail");
        user.setWorkTel("worktel");
        user.setAddingDateTime(new Date());
        user.setActive(true);
        user.setGetsSystemEmails(true);
        user.setUserType(UserType.hospitalStaff.name());
        hospitalStaff.setItsUser(user);
        hospitalStaff.setHospital(
                em.createQuery("select h from Hospital h", Hospital.class).getResultList().get(0));
        hospitalStaff.setPositionInHosp("position in hosp");
        hospitalStaff.setRoleWithITS("role with tds");
        hospitalStaff.getLanguageList().add(em.createQuery("select l from ITSLanguage l", ITSLanguage.class).getResultList().get(0));
        hospitalStaff.getLanguageList().add(em.createQuery("select l from ITSLanguage l", ITSLanguage.class).getResultList().get(1));
        em.persist(hospitalStaff);
    }
}
