/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.dto;

import base.BaseDTO;
import common.exception.ConstraintException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import reportingmodule.dto.CommentDTO;
import reportingmodule.dto.ConsultationDTO;
import reportingmodule.dto.ReportingDTO;
import reportingmodule.dto.WorkScrutinyDTO;
import usermodule.dto.SessionLogEntryDTO;
import usermodule.dto.TDSRadiologistRoleAssignmentDTO;
import usermodule.dto.UserDTO;

/**
 *
 * @author vincze.attila
 */
public class TDSRadiologistDTO extends BaseDTO {

    private String accountNumber;
    private String reportSendingCode;
    private Date creditValidity;
    private Date lastCheck;
    private Integer creditPoints;
    private Integer defaultNormalAvailabilityHrsPerWeekDay;
    private Integer defaultMaxAvailabilityHrsPerWeekDay;
    private Integer defaultNormalAvailabilityHrsPerWeekendDay;
    private Integer defaultMaxAvailabilityHrsPerWeekendDay;
    private Integer workDemandPriority;
    private Integer englishLanguageCompetence;
    private Integer hungarianLanguageCompetence;
    private Integer radInvoiceClosingDay;
    private Integer radPaymentDay;
    private Double hungarianSalaryRate;
    private Double englishSalaryRate;
    private boolean englishAllowed;
    private boolean inProbation;
    private boolean canReport;///ez új
    private UserDTO userInfo;
    private List<CompanyAssignmentDTO> companyAssignmentList;
    private List<TDSRadiologistRoleAssignmentDTO> roleAssignmentList;
    private List<RadCompetenceDTO> radCompetenceList;
    private List<RegLicQualOwnershipDTO> regLicQualOwnershipList;
    private List<ReportingDTO> reportingList;
    private List<ConsultationDTO> consultationList;
    private List<CommentDTO> commentList;
    private List<WorkScrutinyDTO> workScrutinyList;
    private List<WorkPlaceDTO> workPlaceList;
    private List<SuperVisionDTO> supervisedRadList;
    private List<RadAvailabilityDTO> availabilityList;

    public static TDSRadiologistDTO createNullObject() {
        TDSRadiologistDTO ret = new TDSRadiologistDTO();
        ret.getUserInfo().setTimeZoneGmt(null);
        ret.setCreditPoints(null);
        ret.setDefaultNormalAvailabilityHrsPerWeekDay(null);
        ret.setDefaultMaxAvailabilityHrsPerWeekDay(null);
        ret.setDefaultNormalAvailabilityHrsPerWeekendDay(null);
        ret.setDefaultMaxAvailabilityHrsPerWeekendDay(null);
        ret.setWorkDemandPriority(null);
        ret.setEnglishLanguageCompetence(null);
        ret.setHungarianLanguageCompetence(null);
        ret.setHungarianSalaryRate(null);
        ret.setEnglishSalaryRate(null);
        ret.setRadInvoiceClosingDay(null);
        ret.setRadPaymentDay(null);
        return ret;
    }

    public TDSRadiologistDTO(long id) {
        super(id);
        radCompetenceList = new ArrayList<RadCompetenceDTO>();
        regLicQualOwnershipList = new ArrayList<RegLicQualOwnershipDTO>();
        reportingList = new ArrayList<ReportingDTO>();
        consultationList = new ArrayList<ConsultationDTO>();
        commentList = new ArrayList<CommentDTO>();
        workScrutinyList = new ArrayList<WorkScrutinyDTO>();
        workPlaceList = new ArrayList<WorkPlaceDTO>();
        supervisedRadList = new ArrayList<SuperVisionDTO>();
        availabilityList = new ArrayList<RadAvailabilityDTO>();
        roleAssignmentList = new ArrayList<TDSRadiologistRoleAssignmentDTO>();
        companyAssignmentList = new ArrayList<CompanyAssignmentDTO>();
        userInfo = new UserDTO();
        canReport = true;
        creditPoints = 0;
        defaultNormalAvailabilityHrsPerWeekDay = 0;
        defaultMaxAvailabilityHrsPerWeekDay = 0;
        defaultNormalAvailabilityHrsPerWeekendDay = 0;
        defaultMaxAvailabilityHrsPerWeekendDay = 0;
        workDemandPriority = 0;
        englishLanguageCompetence = 0;
        hungarianLanguageCompetence = 0;
        hungarianSalaryRate = 0.0;
        englishSalaryRate = 0.0;
        radInvoiceClosingDay = 0;
        radPaymentDay = 0;
    }

    public TDSRadiologistDTO() {
        this(0);
    }

    @Override
    public TDSRadiologistDTO clone() throws CloneNotSupportedException {
        TDSRadiologistDTO result = (TDSRadiologistDTO) super.clone();
        result.setUserInfo((UserDTO) userInfo.clone());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TDSRadiologistDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public List<SessionLogEntryDTO> getModifications(BaseDTO original) {
        List<SessionLogEntryDTO> ret = new ArrayList<SessionLogEntryDTO>();
        if (original != null && ((TDSRadiologistDTO) original).getUserInfo() != null) {
            List<SessionLogEntryDTO> retUserInfo = userInfo.getModifications(((TDSRadiologistDTO) original).getUserInfo());
            ret.addAll(retUserInfo);
        } else {
            List<SessionLogEntryDTO> retUserInfo = userInfo.getModifications(null);
            ret.addAll(retUserInfo);
        }
        return ret;
    }

    @Override
    public void validate() throws ConstraintException {
        super.validate();
        userInfo.validate();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        String oldValue = this.accountNumber;
        this.accountNumber = accountNumber;
        propertyChangeSupport.firePropertyChange("accountNumber", oldValue, this.accountNumber);
    }

    public Integer getCreditPoints() {
        return creditPoints;
    }

    public void setCreditPoints(Integer creditPoints) {
        Integer oldValue = this.creditPoints;
        this.creditPoints = creditPoints;
        propertyChangeSupport.firePropertyChange("creditPoints", oldValue, this.creditPoints);
    }

    public Date getCreditValidity() {
        return creditValidity;
    }

    public void setCreditValidity(Date creditValidity) {
        Date oldValue = this.creditValidity;
        this.creditValidity = creditValidity;
        propertyChangeSupport.firePropertyChange("creditValidity", oldValue, this.creditValidity);
    }

    public Integer getDefaultMaxAvailabilityHrsPerWeekDay() {
        return defaultMaxAvailabilityHrsPerWeekDay;
    }

    public void setDefaultMaxAvailabilityHrsPerWeekDay(Integer defaultMaxAvailabilityHrsPerWeekDay) {
        Integer oldValue = this.defaultMaxAvailabilityHrsPerWeekDay;
        this.defaultMaxAvailabilityHrsPerWeekDay = defaultMaxAvailabilityHrsPerWeekDay;
        propertyChangeSupport.firePropertyChange("defaultMaxAvailabilityHrsPerWeekDay", oldValue, this.defaultMaxAvailabilityHrsPerWeekDay);
    }

    public Integer getDefaultMaxAvailabilityHrsPerWeekendDay() {
        return defaultMaxAvailabilityHrsPerWeekendDay;
    }

    public void setDefaultMaxAvailabilityHrsPerWeekendDay(Integer defaultMaxAvailabilityHrsPerWeekendDay) {
        Integer oldValue = this.defaultMaxAvailabilityHrsPerWeekendDay;
        this.defaultMaxAvailabilityHrsPerWeekendDay = defaultMaxAvailabilityHrsPerWeekendDay;
        propertyChangeSupport.firePropertyChange("defaultMaxAvailabilityHrsPerWeekendDay", oldValue, this.defaultMaxAvailabilityHrsPerWeekendDay);
    }

    public Integer getDefaultNormalAvailabilityHrsPerWeekDay() {
        return defaultNormalAvailabilityHrsPerWeekDay;
    }

    public void setDefaultNormalAvailabilityHrsPerWeekDay(Integer defaultNormalAvailabilityHrsPerWeekDay) {
        Integer oldValue = this.defaultNormalAvailabilityHrsPerWeekDay;
        this.defaultNormalAvailabilityHrsPerWeekDay = defaultNormalAvailabilityHrsPerWeekDay;
        propertyChangeSupport.firePropertyChange("defaultMaxAvailabilityHrsPerWeekendDay", oldValue, this.defaultNormalAvailabilityHrsPerWeekDay);
    }

    public Integer getDefaultNormalAvailabilityHrsPerWeekendDay() {
        return defaultNormalAvailabilityHrsPerWeekendDay;
    }

    public void setDefaultNormalAvailabilityHrsPerWeekendDay(Integer defaultNormalAvailabilityHrsPerWeekendDay) {
        Integer oldValue = this.defaultNormalAvailabilityHrsPerWeekendDay;
        this.defaultNormalAvailabilityHrsPerWeekendDay = defaultNormalAvailabilityHrsPerWeekendDay;
        propertyChangeSupport.firePropertyChange("defaultNormalAvailabilityHrsPerWeekendDay", oldValue, this.defaultNormalAvailabilityHrsPerWeekendDay);
    }

    public boolean isEnglishAllowed() {
        return englishAllowed;
    }

    public void setEnglishAllowed(boolean englishAllowed) {
        boolean oldValue = this.englishAllowed;
        this.englishAllowed = englishAllowed;
        propertyChangeSupport.firePropertyChange("englishAllowed", oldValue, this.englishAllowed);
    }

    public Integer getEnglishLanguageCompetence() {
        return englishLanguageCompetence;
    }

    public void setEnglishLanguageCompetence(Integer englishLanguageCompetence) {
        Integer oldValue = this.englishLanguageCompetence;
        this.englishLanguageCompetence = englishLanguageCompetence;
        propertyChangeSupport.firePropertyChange("englishLanguageCompetence", oldValue, this.englishLanguageCompetence);
    }

    public Double getEnglishSalaryRate() {
        return englishSalaryRate;
    }

    public void setEnglishSalaryRate(Double englishSalaryRate) {
        Double oldValue = this.englishSalaryRate;
        this.englishSalaryRate = englishSalaryRate;
        propertyChangeSupport.firePropertyChange("englishSalaryRate", oldValue, this.englishSalaryRate);
    }

    public Integer getHungarianLanguageCompetence() {
        return hungarianLanguageCompetence;
    }

    public void setHungarianLanguageCompetence(Integer hungarianLanguageCompetence) {
        Integer oldValue = this.hungarianLanguageCompetence;
        this.hungarianLanguageCompetence = hungarianLanguageCompetence;
        propertyChangeSupport.firePropertyChange("hungarianLanguageCompetence", oldValue, this.hungarianLanguageCompetence);
    }

    public Double getHungarianSalaryRate() {
        return hungarianSalaryRate;
    }

    public void setHungarianSalaryRate(Double hungarianSalaryRate) {
        Double oldValue = this.hungarianSalaryRate;
        this.hungarianSalaryRate = hungarianSalaryRate;
        propertyChangeSupport.firePropertyChange("hungarianSalaryRate", oldValue, this.hungarianSalaryRate);
    }

    public boolean isInProbation() {
        return inProbation;
    }

    public void setInProbation(boolean inProbation) {
        boolean oldValue = this.inProbation;
        this.inProbation = inProbation;
        propertyChangeSupport.firePropertyChange("inProbation", oldValue, this.inProbation);
    }

    public Date getLastCheck() {
        return lastCheck;
    }

    public void setLastCheck(Date lastCheck) {
        Date oldValue = this.lastCheck;
        this.lastCheck = lastCheck;
        propertyChangeSupport.firePropertyChange("lastCheck", oldValue, this.lastCheck);
    }

    public Integer getRadInvoiceClosingDay() {
        return radInvoiceClosingDay;
    }

    public void setRadInvoiceClosingDay(Integer radInvoiceClosingDay) {
        Integer oldValue = this.radInvoiceClosingDay;
        this.radInvoiceClosingDay = radInvoiceClosingDay;
        propertyChangeSupport.firePropertyChange("radInvoiceClosingDay", oldValue, this.radInvoiceClosingDay);
    }

    public Integer getRadPaymentDay() {
        return radPaymentDay;
    }

    public void setRadPaymentDay(Integer radPaymentDay) {
        Integer oldValue = this.radPaymentDay;
        this.radPaymentDay = radPaymentDay;
        propertyChangeSupport.firePropertyChange("radPaymentDay", oldValue, this.radPaymentDay);
    }

    public String getReportSendingCode() {
        return reportSendingCode;
    }

    public void setReportSendingCode(String reportSendingCode) {
        String oldValue = this.reportSendingCode;
        this.reportSendingCode = reportSendingCode;
        propertyChangeSupport.firePropertyChange("reportSendingCode", oldValue, this.reportSendingCode);
    }

    public Integer getWorkDemandPriority() {
        return workDemandPriority;
    }

    public void setWorkDemandPriority(Integer workDemandPriority) {
        Integer oldValue = this.workDemandPriority;
        this.workDemandPriority = workDemandPriority;
        propertyChangeSupport.firePropertyChange("workDemandPriority", oldValue, this.workDemandPriority);
    }

    public List<RadCompetenceDTO> getRadCompetenceList() {
        return radCompetenceList;
    }

    public void setRadCompetenceList(List<RadCompetenceDTO> radCompetenceList) {
        List<RadCompetenceDTO> oldValue = this.radCompetenceList;
        this.radCompetenceList = radCompetenceList;
        propertyChangeSupport.firePropertyChange("competenceList", oldValue, this.radCompetenceList);
    }

    public List<RegLicQualOwnershipDTO> getRegLicQualOwnershipList() {
        return regLicQualOwnershipList;
    }

    public void setRegLicQualOwnershipList(List<RegLicQualOwnershipDTO> regLicQualOwnershipList) {
        List<RegLicQualOwnershipDTO> oldValue = this.regLicQualOwnershipList;
        this.regLicQualOwnershipList = regLicQualOwnershipList;
        propertyChangeSupport.firePropertyChange("regLicQualList", oldValue, this.regLicQualOwnershipList);
    }

    public UserDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserDTO userInfo) {
        UserDTO oldValue = this.userInfo;
        this.userInfo = userInfo;
        propertyChangeSupport.firePropertyChange("userInfo", oldValue, this.userInfo);
    }

    public List<ReportingDTO> getReportingList() {
        return reportingList;
    }

    public void setReportingList(List<ReportingDTO> reportingList) {
        List<ReportingDTO> oldValue = this.reportingList;
        this.reportingList = reportingList;
        propertyChangeSupport.firePropertyChange("reportingList", oldValue, this.reportingList);
    }

    public List<CommentDTO> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentDTO> commentList) {
        List<CommentDTO> oldValue = this.commentList;
        this.commentList = commentList;
        propertyChangeSupport.firePropertyChange("commentList", oldValue, this.commentList);
    }

    public List<ConsultationDTO> getConsultationList() {
        return consultationList;
    }

    public void setConsultationList(List<ConsultationDTO> consultationList) {
        List<ConsultationDTO> oldValue = this.consultationList;
        this.consultationList = consultationList;
        propertyChangeSupport.firePropertyChange("consultationList", oldValue, this.consultationList);
    }

    public List<WorkScrutinyDTO> getWorkScrutinyList() {
        return workScrutinyList;
    }

    public void setWorkScrutinyList(List<WorkScrutinyDTO> workScrutinyList) {
        List<WorkScrutinyDTO> oldValue = this.workScrutinyList;
        this.workScrutinyList = workScrutinyList;
        propertyChangeSupport.firePropertyChange("workScrutinyList", oldValue, this.workScrutinyList);
    }

    public List<TDSRadiologistRoleAssignmentDTO> getRoleAssignmentList() {
        return roleAssignmentList;
    }

    public void setRoleAssignmentList(List<TDSRadiologistRoleAssignmentDTO> roleAssignmentList) {
        List<TDSRadiologistRoleAssignmentDTO> oldValue = this.roleAssignmentList;
        this.roleAssignmentList = roleAssignmentList;
        propertyChangeSupport.firePropertyChange("roleAssignmentList", oldValue, this.roleAssignmentList);
    }

    public List<WorkPlaceDTO> getWorkPlaceList() {
        return workPlaceList;
    }

    public void setWorkPlaceList(List<WorkPlaceDTO> workPlaceList) {
        List<WorkPlaceDTO> oldValue = this.workPlaceList;
        this.workPlaceList = workPlaceList;
        propertyChangeSupport.firePropertyChange("workPlaceList", oldValue, this.workPlaceList);
    }

    public List<CompanyAssignmentDTO> getCompanyAssignmentList() {
        return companyAssignmentList;
    }

    public void setCompanyAssignmentList(List<CompanyAssignmentDTO> companyAssignmentList) {
        List<CompanyAssignmentDTO> oldValue = this.companyAssignmentList;
        this.companyAssignmentList = companyAssignmentList;
        propertyChangeSupport.firePropertyChange("companyAssignmentList", oldValue, this.companyAssignmentList);
    }

    public List<SuperVisionDTO> getSupervisedRadList() {
        return supervisedRadList;
    }

    public void setSupervisedRadList(List<SuperVisionDTO> supervisedRadList) {
        List<SuperVisionDTO> oldValue = this.supervisedRadList;
        this.supervisedRadList = supervisedRadList;
        propertyChangeSupport.firePropertyChange("supervisedRadList", oldValue, this.supervisedRadList);
    }

    public List<RadAvailabilityDTO> getAvailabilityList() {
        return availabilityList;
    }

    public void setAvailabilityList(List<RadAvailabilityDTO> availabilityList) {
        List<RadAvailabilityDTO> oldValue = this.availabilityList;
        this.availabilityList = availabilityList;
        propertyChangeSupport.firePropertyChange("availabilityList", oldValue, this.availabilityList);
    }

    public boolean isCanReport() {
        return canReport;
    }

    public void setCanReport(boolean canReport) {
        boolean oldValue = this.canReport;
        this.canReport = canReport;
        propertyChangeSupport.firePropertyChange("canReport", oldValue, this.canReport);
    }

    public void update(TDSRadiologistDTO newItem) {
        radCompetenceList.clear();
        regLicQualOwnershipList.clear();
        if (newItem.getUserInfo() == null) {
            setUserInfo(new UserDTO());
        } else {
            userInfo.setId(newItem.getUserInfo().getId());
            userInfo.setName(newItem.getUserInfo().getName());
            userInfo.setLoginName(newItem.getUserInfo().getLoginName());
            userInfo.setPassword(newItem.getUserInfo().getPassword());
            userInfo.setSex(newItem.getUserInfo().getSex());
            userInfo.setTdsEmail(newItem.getUserInfo().getTdsEmail());
            userInfo.setWorkEmail(newItem.getUserInfo().getWorkEmail());
            userInfo.setWorkTel(newItem.getUserInfo().getWorkTel());
            userInfo.setWorkFax(newItem.getUserInfo().getWorkFax());
            userInfo.setPlaceOfFax(newItem.getUserInfo().getPlaceOfFax());
            userInfo.setSkype(newItem.getUserInfo().getSkype());
            userInfo.setMsn(newItem.getUserInfo().getMsn());
            userInfo.setAddingDateTime(newItem.getUserInfo().getAddingDateTime());
            userInfo.setActive(newItem.getUserInfo().isActive());
            userInfo.setGetsSystemEmails(newItem.getUserInfo().isGetsSystemEmails());
        }

        setId(newItem.getId());
        setAccountNumber(newItem.getAccountNumber());
        setCreditPoints(newItem.getCreditPoints());
        setCreditValidity(newItem.getCreditValidity());
        setDefaultMaxAvailabilityHrsPerWeekDay(newItem.getDefaultMaxAvailabilityHrsPerWeekDay());
        setDefaultMaxAvailabilityHrsPerWeekendDay(newItem.getDefaultMaxAvailabilityHrsPerWeekendDay());
        setDefaultNormalAvailabilityHrsPerWeekDay(newItem.getDefaultNormalAvailabilityHrsPerWeekDay());
        setDefaultNormalAvailabilityHrsPerWeekendDay(newItem.getDefaultNormalAvailabilityHrsPerWeekendDay());
        setEnglishAllowed(newItem.isEnglishAllowed());
        setEnglishLanguageCompetence(newItem.getEnglishLanguageCompetence());
        setEnglishSalaryRate(newItem.getEnglishSalaryRate());
        setHungarianLanguageCompetence(newItem.getHungarianLanguageCompetence());
        setHungarianSalaryRate(newItem.getHungarianSalaryRate());
        setInProbation(newItem.isInProbation());
        setLastCheck(newItem.getLastCheck());
        setRadInvoiceClosingDay(newItem.getRadInvoiceClosingDay());
        setRadPaymentDay(newItem.getRadPaymentDay());
        setReportSendingCode(newItem.getReportSendingCode());
        setWorkDemandPriority(newItem.getWorkDemandPriority());

        for (RadCompetenceDTO radCompetenceDTO : newItem.getRadCompetenceList()) {
            radCompetenceList.add(radCompetenceDTO);
        }

        for (RegLicQualOwnershipDTO regLicQualOwnershipDTO : newItem.getRegLicQualOwnershipList()) {
            regLicQualOwnershipList.add(regLicQualOwnershipDTO);
        }
    }
}
