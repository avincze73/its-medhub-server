/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmodule.dto;

import base.BaseDTO;
import common.exception.ConstraintException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import masterdatamodule.dto.ContractTypeDTO;
import masterdatamodule.dto.CurrencyDTO;

/**
 *
 * @author vincze.attila
 */
public class HospitalContractDTO extends BaseDTO {

    private String contractCode;
    private ContractTypeDTO contractType;
    private Date startDateTime;
    private Date stopDateTime;
    private String signersName;
    private Date signedDate;
    private Date intendedEndDateTime;
    private Date lastCaseAcceptanceDateTime;
    private int invoicePeriodDay; //hónap napja
    private int invoiceProductionDay; //számlakiállítás dátuma
    private Double normalWorkTimeDays;//ebből jön a határidő
    private double workTimeDaysWithConsultation;
    private double normalClosenessToDeadlineDaysHosp; //day
    private double closenessToDeadlineDaysWithConsHosp;//
    private double normalClosenessToDeadlineDaysRad; //day
    private double closenessToDeadlineDaysWithConsRad;//
    private double adminFeeForCheckingUselessCase;
    private double adminFeeForManuallyEnteringData;
    private double lateReportingMultiplier;//ez új
    private Integer credit;
    private boolean active;
    private AvailabilityPerWeekDTO minRequiredAvailabilityPerWeek;
    private AvailabilityPerWeekDTO maxRequiredAvailabilityPerWeek;
    private CurrencyDTO currency;
    private HospitalDTO hospital;
    private List<OptionAssignmentDTO> optionList;
    private List<ContactPersonAssignmentDTO> contactPersonAssignmentList;
    private List<MonthlyInvoiceDTO> invoiceList;
    //transient private ObservableList<OptionAssignmentDTO> obsOptionList;
    private List<WorkBandTableDTO> workBandTableList;
    private List<AvailabilityPerPeriodDTO> availabilityPerPeriodList;

    /**
     *
     * @author vincze.attila
     * attributes for displaying availabilityperweek
     */

    public static HospitalContractDTO createNullObject() {
        HospitalContractDTO ret = new HospitalContractDTO();
        ret.setNormalWorkTimeDays(null);
        ret.setCredit(null);
        return ret;
    }


    public HospitalContractDTO() {
        this(0);
    }

    public HospitalContractDTO(long id) {
        super(id);
        maxRequiredAvailabilityPerWeek = new AvailabilityPerPeriodDTO();
        minRequiredAvailabilityPerWeek = new AvailabilityPerPeriodDTO();
        optionList = new ArrayList<OptionAssignmentDTO>();
        contactPersonAssignmentList = new ArrayList<ContactPersonAssignmentDTO>();
        invoiceList = new ArrayList<MonthlyInvoiceDTO>();
        workBandTableList = new ArrayList<WorkBandTableDTO>();
        availabilityPerPeriodList = new ArrayList<AvailabilityPerPeriodDTO>();
        normalWorkTimeDays = 0.0;
        credit = 0;
        //obsOptionList = ObservableCollections.observableList(optionList);
    }

    @Override
    public HospitalContractDTO clone() throws CloneNotSupportedException {
        HospitalContractDTO result = (HospitalContractDTO) super.clone();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HospitalContractDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active)  {
        boolean oldValue = this.active;
        this.active = active;
        propertyChangeSupport.firePropertyChange("active", oldValue, this.active);
    }

    public double getAdminFeeForCheckingUselessCase() {
        return adminFeeForCheckingUselessCase;
    }

    public void setAdminFeeForCheckingUselessCase(double adminFeeForCheckingUselessCase) {
        double oldValue = this.adminFeeForCheckingUselessCase;
        this.adminFeeForCheckingUselessCase = adminFeeForCheckingUselessCase;
        propertyChangeSupport.firePropertyChange("adminFeeForCheckingUselessCase", oldValue, this.adminFeeForCheckingUselessCase);
    }

    public double getAdminFeeForManuallyEnteringData() {
        return adminFeeForManuallyEnteringData;
    }

    public void setAdminFeeForManuallyEnteringData(double adminFeeForManuallyEnteringData) {
        double oldValue = this.adminFeeForManuallyEnteringData;
        this.adminFeeForManuallyEnteringData = adminFeeForManuallyEnteringData;
        propertyChangeSupport.firePropertyChange("adminFeeForManuallyEnteringData", oldValue, this.adminFeeForManuallyEnteringData);
    }

    public double getClosenessToDeadlineDaysWithConsHosp() {
        return closenessToDeadlineDaysWithConsHosp;
    }

    public void setClosenessToDeadlineDaysWithConsHosp(double closenessToDeadlineDaysWithConsHosp) {
        double oldValue = this.closenessToDeadlineDaysWithConsHosp;
        this.closenessToDeadlineDaysWithConsHosp = closenessToDeadlineDaysWithConsHosp;
        propertyChangeSupport.firePropertyChange("closenessToDeadlineDaysWithConsHosp", oldValue, this.closenessToDeadlineDaysWithConsHosp);
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        String oldValue = this.contractCode;
        this.contractCode = contractCode;
        propertyChangeSupport.firePropertyChange("contractCode", oldValue, this.contractCode);
    }

    public ContractTypeDTO getContractType() {
        return contractType;
    }

    public void setContractType(ContractTypeDTO contractType) {
        ContractTypeDTO oldValue = this.contractType;
        this.contractType = contractType;
        propertyChangeSupport.firePropertyChange("contractType", oldValue, this.contractType);
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit)  {
        Integer oldValue = this.credit;
        this.credit = credit;
        propertyChangeSupport.firePropertyChange("credit", oldValue, this.credit);
    }

    public CurrencyDTO getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyDTO currency)  {
        CurrencyDTO oldValue = this.currency;
        this.currency = currency;
        propertyChangeSupport.firePropertyChange("currency", oldValue, this.currency);
    }

    public HospitalDTO getHospital() {
        return hospital;
    }

    public void setHospital(HospitalDTO hospital) {
        HospitalDTO oldValue = this.hospital;
        this.hospital = hospital;
        propertyChangeSupport.firePropertyChange("hospital", oldValue, this.hospital);
    }

    public Date getIntendedEndDateTime() {
        return intendedEndDateTime;
    }

    public void setIntendedEndDateTime(Date intendedEndDateTime)  {
        Date oldValue = this.intendedEndDateTime;
        this.intendedEndDateTime = intendedEndDateTime;
        propertyChangeSupport.firePropertyChange("intendedEndDateTime", oldValue, this.intendedEndDateTime);
    }

    public int getInvoicePeriodDay() {
        return invoicePeriodDay;
    }

    public void setInvoicePeriodDay(int invoicePeriodDay)  {
        int oldValue = this.invoicePeriodDay;
        this.invoicePeriodDay = invoicePeriodDay;
        propertyChangeSupport.firePropertyChange("invoicePeriodDay", oldValue, this.invoicePeriodDay);
    }

    public int getInvoiceProductionDay() {
        return invoiceProductionDay;
    }

    public void setInvoiceProductionDay(int invoiceProductionDay)  {
        int oldValue = this.invoiceProductionDay;
        this.invoiceProductionDay = invoiceProductionDay;
        propertyChangeSupport.firePropertyChange("invoiceProductionDay", oldValue, this.invoiceProductionDay);
    }

    public Date getLastCaseAcceptanceDateTime() {
        return lastCaseAcceptanceDateTime;
    }

    public void setLastCaseAcceptanceDateTime(Date lastCaseAcceptanceDateTime)  {
        Date oldValue = this.lastCaseAcceptanceDateTime;
        this.lastCaseAcceptanceDateTime = lastCaseAcceptanceDateTime;
        propertyChangeSupport.firePropertyChange("lastCaseAcceptanceDateTime", oldValue, this.lastCaseAcceptanceDateTime);
    }

    public AvailabilityPerWeekDTO getMaxRequiredAvailabilityPerWeek() {
        return maxRequiredAvailabilityPerWeek;
    }

    public void setMaxRequiredAvailabilityPerWeek(AvailabilityPerWeekDTO maxRequiredAvailabilityPerWeek)  {
        AvailabilityPerWeekDTO oldValue = this.maxRequiredAvailabilityPerWeek;
        this.maxRequiredAvailabilityPerWeek = maxRequiredAvailabilityPerWeek;
        propertyChangeSupport.firePropertyChange("maxRequiredAvailabilityPerWeek", oldValue, this.maxRequiredAvailabilityPerWeek);
    }

    public AvailabilityPerWeekDTO getMinRequiredAvailabilityPerWeek() {
        return minRequiredAvailabilityPerWeek;
    }

    public void setMinRequiredAvailabilityPerWeek(AvailabilityPerWeekDTO minRequiredAvailabilityPerWeek)  {
        AvailabilityPerWeekDTO oldValue = this.minRequiredAvailabilityPerWeek;
        this.minRequiredAvailabilityPerWeek = minRequiredAvailabilityPerWeek;
        propertyChangeSupport.firePropertyChange("minRequiredAvailabilityPerWeek", oldValue, this.minRequiredAvailabilityPerWeek);
    }

    public double getNormalClosenessToDeadlineDaysHosp() {
        return normalClosenessToDeadlineDaysHosp;
    }

    public void setNormalClosenessToDeadlineDaysHosp(double normalClosenessToDeadlineDaysHosp)  {
        double oldValue = this.normalClosenessToDeadlineDaysHosp;
        this.normalClosenessToDeadlineDaysHosp = normalClosenessToDeadlineDaysHosp;
        propertyChangeSupport.firePropertyChange("normalClosenessToDeadlineDaysHosp", oldValue, this.normalClosenessToDeadlineDaysHosp);
    }

    public Double getNormalWorkTimeDays() {
        return normalWorkTimeDays;
    }

    public void setNormalWorkTimeDays(Double normalWorkTimeDays)  {
        Double oldValue = this.normalWorkTimeDays;
        this.normalWorkTimeDays = normalWorkTimeDays;
        propertyChangeSupport.firePropertyChange("normalWorkTimeDays", oldValue, this.normalWorkTimeDays);
    }

    public Date getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(Date signedDate)  {
        Date oldValue = this.signedDate;
        this.signedDate = signedDate;
        propertyChangeSupport.firePropertyChange("signedDate", oldValue, this.signedDate);
    }

    public String getSignersName() {
        return signersName;
    }

    public void setSignersName(String signersName)  {
        String oldValue = this.signersName;
        this.signersName = signersName;
        propertyChangeSupport.firePropertyChange("signersName", oldValue, this.signersName);
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime)  {
        Date oldValue = this.startDateTime;
        this.startDateTime = startDateTime;
        propertyChangeSupport.firePropertyChange("startDateTime", oldValue, this.startDateTime);
    }

    public Date getStopDateTime() {
        return stopDateTime;
    }

    public void setStopDateTime(Date stopDateTime)  {
        Date oldValue = this.stopDateTime;
        this.stopDateTime = stopDateTime;
        propertyChangeSupport.firePropertyChange("stopDateTime", oldValue, this.stopDateTime);
    }

    public double getWorkTimeDaysWithConsultation() {
        return workTimeDaysWithConsultation;
    }

    public void setWorkTimeDaysWithConsultation(double workTimeDaysWithConsultation)  {
        double oldValue = this.workTimeDaysWithConsultation;
        this.workTimeDaysWithConsultation = workTimeDaysWithConsultation;
        propertyChangeSupport.firePropertyChange("workTimeDaysWithConsultation", oldValue, this.workTimeDaysWithConsultation);
    }

    public List<OptionAssignmentDTO> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<OptionAssignmentDTO> optionList) {
        List<OptionAssignmentDTO> oldValue = this.optionList;
        this.optionList = optionList;
        propertyChangeSupport.firePropertyChange("optionList", oldValue, this.optionList);
    }

    public List<ContactPersonAssignmentDTO> getContactPersonAssignmentList() {
        return contactPersonAssignmentList;
    }

    public void setContactPersonAssignmentList(List<ContactPersonAssignmentDTO> contactPersonAssignmentList) {
        List<ContactPersonAssignmentDTO> oldValue = this.contactPersonAssignmentList;
        this.contactPersonAssignmentList = contactPersonAssignmentList;
        propertyChangeSupport.firePropertyChange("contactPersonAssignmentList", oldValue, this.contactPersonAssignmentList);
    }

    public List<MonthlyInvoiceDTO> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<MonthlyInvoiceDTO> invoiceList) {
        List<MonthlyInvoiceDTO> oldValue = this.invoiceList;
        this.invoiceList = invoiceList;
        propertyChangeSupport.firePropertyChange("invoiceList", oldValue, this.invoiceList);
    }

    public List<WorkBandTableDTO> getWorkBandTableList() {
        return workBandTableList;
    }

    public void setWorkBandTable(List<WorkBandTableDTO> workBandTableList) {
        List<WorkBandTableDTO> oldValue = this.workBandTableList;
        this.workBandTableList = workBandTableList;
        propertyChangeSupport.firePropertyChange("workBandTableList", oldValue,
                this.workBandTableList);
    }

    public double getLateReportingMultiplier() {
        return lateReportingMultiplier;
    }

    public void setLateReportingMultiplier(double lateReportingMultiplier) {
        double oldValue = this.lateReportingMultiplier;
        this.lateReportingMultiplier = lateReportingMultiplier;
        propertyChangeSupport.firePropertyChange("lateReportingMultiplier", oldValue,
                this.lateReportingMultiplier);
    }

    public List<AvailabilityPerPeriodDTO> getAvailabilityPerPeriodList() {
        return availabilityPerPeriodList;
    }

    public void setAvailabilityPerPeriodList(List<AvailabilityPerPeriodDTO> availabilityPerPeriodList) {
        List<AvailabilityPerPeriodDTO> oldValue = this.availabilityPerPeriodList;
        this.availabilityPerPeriodList = availabilityPerPeriodList;
        propertyChangeSupport.firePropertyChange("availabilityPerPeriodList", oldValue,
                this.availabilityPerPeriodList);
    }

    public double getClosenessToDeadlineDaysWithConsRad() {
        return closenessToDeadlineDaysWithConsRad;
    }

    public void setClosenessToDeadlineDaysWithConsRad(double closenessToDeadlineDaysWithConsRad) {
        this.closenessToDeadlineDaysWithConsRad = closenessToDeadlineDaysWithConsRad;
    }

    public double getNormalClosenessToDeadlineDaysRad() {
        return normalClosenessToDeadlineDaysRad;
    }

    public void setNormalClosenessToDeadlineDaysRad(double normalClosenessToDeadlineDaysRad) {
        this.normalClosenessToDeadlineDaysRad = normalClosenessToDeadlineDaysRad;
    }

    @Override
    public void Validate(ResourceBundle bundle) throws ConstraintException {
        ArrayList<String> fields = new ArrayList<String>();
        if (contractCode == null || "".equals(contractCode)) {
            fields.add(bundle.getString("contractCode"));
        }
        if (startDateTime == null) {
            fields.add(bundle.getString("startDateTime"));
        }
        if (stopDateTime == null) {
            fields.add(bundle.getString("stopDateTime"));
        }
        if (signedDate == null) {
            fields.add(bundle.getString("signedDate"));
        }
        if (intendedEndDateTime == null) {
            fields.add(bundle.getString("intendedEndDateTime"));
        }
        if (lastCaseAcceptanceDateTime == null) {
            fields.add(bundle.getString("lastCaseAcceptanceDateTime"));
        }
        if (currency == null || "".equals(currency.getName())) {
            fields.add(bundle.getString("currency"));
        }
        if (contractType == null || "".equals(contractType.getEnglishName())) {
            fields.add(bundle.getString("contractType"));
        }
        if (fields.size() > 0) {
            throw new ConstraintException(
                    bundle.getString("mustBeFilled") + "\n" + fields.toString());
        }
    }

    public void update(HospitalContractDTO newItem) {
            setId(newItem.getId());
            setActive(newItem.isActive());
            setAdminFeeForCheckingUselessCase(newItem.getAdminFeeForCheckingUselessCase());
            setAdminFeeForManuallyEnteringData(newItem.getAdminFeeForManuallyEnteringData());
            setClosenessToDeadlineDaysWithConsHosp(newItem.getClosenessToDeadlineDaysWithConsHosp());
            setContractCode(newItem.getContractCode());
            setContractType(newItem.getContractType());
            setCredit(newItem.getCredit());
            setCurrency(newItem.getCurrency());
            setHospital(newItem.getHospital());
            setIntendedEndDateTime(newItem.getIntendedEndDateTime());
            setInvoicePeriodDay(newItem.getInvoicePeriodDay());
            setInvoiceProductionDay(newItem.getInvoiceProductionDay());
            setLastCaseAcceptanceDateTime(newItem.getLastCaseAcceptanceDateTime());
            setMaxRequiredAvailabilityPerWeek(newItem.getMaxRequiredAvailabilityPerWeek());
            setMinRequiredAvailabilityPerWeek(newItem.getMinRequiredAvailabilityPerWeek());
            setNormalClosenessToDeadlineDaysHosp(newItem.getNormalClosenessToDeadlineDaysHosp());
            setNormalWorkTimeDays(newItem.getNormalWorkTimeDays());
            setSignedDate(newItem.getSignedDate());
            setSignersName(newItem.getSignersName());
            setStartDateTime(newItem.getStartDateTime());
            setStopDateTime(newItem.getStopDateTime());
            setWorkTimeDaysWithConsultation(newItem.getWorkTimeDaysWithConsultation());
            setOptionList(newItem.getOptionList());
    }
}
