/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmodule.dto;

import base.BaseDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vincze.attila
 */
public class MonthlyInvoiceDTO extends BaseDTO {

    private Date started;
    private Date ended;
    private Date calculation;
    private Date sentToHospital;
    private Date paid;
    private double invoiceClosingSum; //double legyen
    private String statement;
    private String detailedPreview;//
    private String briefPreview;//
    private List<CaseLevelInvoiceElementDTO> caseLevelInvoiceElementList;
    private HospitalContractDTO hospitalContract;

    public MonthlyInvoiceDTO(int id) {
        super(id);
        caseLevelInvoiceElementList = new ArrayList<CaseLevelInvoiceElementDTO>();
    }

    public MonthlyInvoiceDTO() {
        super();
        caseLevelInvoiceElementList = new ArrayList<CaseLevelInvoiceElementDTO>();
    }

    public MonthlyInvoiceDTO(Date started, Date ended) {
        super();
        this.started = started;
        this.ended = ended;
        caseLevelInvoiceElementList = new ArrayList<CaseLevelInvoiceElementDTO>();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MonthlyInvoiceDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public Date getCalculation() {
        return calculation;
    }

    public void setCalculation(Date calculation) {
        Date oldValue = this.calculation;
        this.calculation = calculation;
        propertyChangeSupport.firePropertyChange("calculation", oldValue, this.calculation);
    }

    public List<CaseLevelInvoiceElementDTO> getCaseLevelInvoiceElementList() {
        return caseLevelInvoiceElementList;
    }

    public void setCaseLevelInvoiceElementList(List<CaseLevelInvoiceElementDTO> caseLevelInvoiceElementList) {
        List<CaseLevelInvoiceElementDTO> oldValue = this.caseLevelInvoiceElementList;
        this.caseLevelInvoiceElementList = caseLevelInvoiceElementList;
        propertyChangeSupport.firePropertyChange("caseLevelInvoiceElementList", oldValue, this.caseLevelInvoiceElementList);
    }

    public Date getEnded() {
        return ended;
    }

    public void setEnded(Date ended) {
        Date oldValue = this.ended;
        this.ended = ended;
        propertyChangeSupport.firePropertyChange("ended", oldValue, this.ended);
    }

    public double getInvoiceClosingSum() {
        return invoiceClosingSum;
    }

    public void setInvoiceClosingSum(double invoiceClosingSum) {
        double oldValue = this.invoiceClosingSum;
        this.invoiceClosingSum = invoiceClosingSum;
        propertyChangeSupport.firePropertyChange("invoiceClosingSum", oldValue, this.invoiceClosingSum);
    }

    public Date getPaid() {
        return paid;
    }

    public void setPaid(Date paid) {
        Date oldValue = this.paid;
        this.paid = paid;
        propertyChangeSupport.firePropertyChange("paid", oldValue, this.paid);
    }

    public Date getSentToHospital() {
        return sentToHospital;
    }

    public void setSentToHospital(Date sentToHospital) {
        Date oldValue = this.sentToHospital;
        this.sentToHospital = sentToHospital;
        propertyChangeSupport.firePropertyChange("sentToHospital", oldValue, this.sentToHospital);
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        Date oldValue = this.started;
        this.started = started;
        propertyChangeSupport.firePropertyChange("started", oldValue, this.started);
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        String oldValue = this.statement;
        this.statement = statement;
        propertyChangeSupport.firePropertyChange("statement", oldValue, this.statement);
    }

    public HospitalContractDTO getHospitalContract() {
        return hospitalContract;
    }

    public void setHospitalContract(HospitalContractDTO hospitalContract) {
        HospitalContractDTO oldValue = this.hospitalContract;
        this.hospitalContract = hospitalContract;
        propertyChangeSupport.firePropertyChange("hospitalContract", oldValue, this.hospitalContract);
    }

    public String getBriefPreview() {
        return briefPreview;
    }

    public void setBriefPreview(String briefPreview) {
        String oldValue = this.briefPreview;
        this.briefPreview = briefPreview;
        propertyChangeSupport.firePropertyChange("briefPreview", oldValue, this.briefPreview);
    }

    public String getDetailedPreview() {
        return detailedPreview;
    }

    public void setDetailedPreview(String detailedPreview) {
        String oldValue = this.detailedPreview;
        this.detailedPreview = detailedPreview;
        propertyChangeSupport.firePropertyChange("detailedPreview", oldValue, this.detailedPreview);
    }


}
