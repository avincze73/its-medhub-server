/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmodule.dto;

import base.BaseDTO;
import casemodule.dto.CaseDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vincze.attila
 */
public class CaseLevelInvoiceElementDTO extends BaseDTO {

    private CaseDTO caseItBelongsTo;
    private double sumValue;
    private Date caseReceived;
    private Date reportSent;//reportSent
    private Date reportBecameAvailable;//reportSent
    private int consulted;
    private double workloadOnDayBeforeThis;
    private double normalWorkload;
    private double paygWorkload;
    private double extraWorkload;
    private double estimatedWorkTime;
    private double workTime;
    private String workloadCategory;
    private double allowedReportingTime;
    private double reportingTime;
    private List<StudyLevelInvoiceElementDTO> studyLevelInvoiceElementList;
    private MonthlyInvoiceDTO monthlyInvoice;

    public CaseLevelInvoiceElementDTO(int id) {
        super(id);
        studyLevelInvoiceElementList = new ArrayList<StudyLevelInvoiceElementDTO>();
    }

    public CaseLevelInvoiceElementDTO() {
        super();
        studyLevelInvoiceElementList = new ArrayList<StudyLevelInvoiceElementDTO>();
    }

    public CaseLevelInvoiceElementDTO(CaseDTO caseItBelongsTo, double sumValue,
            Date caseReceived, Date caseSent, int consulted) {
        super();
        this.caseItBelongsTo = caseItBelongsTo;
        this.sumValue = sumValue;
        this.caseReceived = caseReceived;
        this.reportSent = caseSent;
        this.consulted = consulted;
        studyLevelInvoiceElementList = new ArrayList<StudyLevelInvoiceElementDTO>();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CaseLevelInvoiceElementDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public CaseDTO getCaseItBelongsTo() {
        return caseItBelongsTo;
    }

    public void setCaseItBelongsTo(CaseDTO caseItBelongsTo) {
        CaseDTO oldValue = this.caseItBelongsTo;
        this.caseItBelongsTo = caseItBelongsTo;
        propertyChangeSupport.firePropertyChange("caseItBelongsTo", oldValue, this.caseItBelongsTo);
    }

    public Date getCaseReceived() {
        return caseReceived;
    }

    public void setCaseReceived(Date caseReceived) {
        Date oldValue = this.caseReceived;
        this.caseReceived = caseReceived;
        propertyChangeSupport.firePropertyChange("caseReceived", oldValue, this.caseReceived);
    }

    public Date getReportSent() {
        return reportSent;
    }

    public void setReportSent(Date caseSent) {
        Date oldValue = this.reportSent;
        this.reportSent = caseSent;
        propertyChangeSupport.firePropertyChange("caseSent", oldValue, this.reportSent);
    }

    public List<StudyLevelInvoiceElementDTO> getStudyLevelInvoiceElementList() {
        return studyLevelInvoiceElementList;
    }

    public void setStudyLevelInvoiceElementList(List<StudyLevelInvoiceElementDTO> studyLevelInvoiceElementList) {
        List<StudyLevelInvoiceElementDTO> oldValue = this.studyLevelInvoiceElementList;
        this.studyLevelInvoiceElementList = studyLevelInvoiceElementList;
        propertyChangeSupport.firePropertyChange("studyLevelInvoiceElementList", oldValue, this.studyLevelInvoiceElementList);
    }

    public double getSumValue() {
        return sumValue;
    }

    public void setSumValue(double sumValue) {
        double oldValue = this.sumValue;
        this.sumValue = sumValue;
        propertyChangeSupport.firePropertyChange("sumValue", oldValue, this.sumValue);
    }

    public int getConsulted() {
        return consulted;
    }

    public void setConsulted(int consulted) {
        double oldValue = this.consulted;
        this.consulted = consulted;
        propertyChangeSupport.firePropertyChange("consulted", oldValue, this.consulted);
    }

    public MonthlyInvoiceDTO getMonthlyInvoice() {
        return monthlyInvoice;
    }

    public void setMonthlyInvoice(MonthlyInvoiceDTO monthlyInvoice) {
        MonthlyInvoiceDTO oldValue = this.monthlyInvoice;
        this.monthlyInvoice = monthlyInvoice;
        propertyChangeSupport.firePropertyChange("monthlyInvoice", oldValue, this.monthlyInvoice);
    }

    public double getAllowedReportingTime() {
        return allowedReportingTime;
    }

    public void setAllowedReportingTime(double allowedReportingTime) {
        double oldValue = this.allowedReportingTime;
        this.allowedReportingTime = allowedReportingTime;
        propertyChangeSupport.firePropertyChange("allowedReportingTime", oldValue, this.allowedReportingTime);
    }

    public double getEstimatedWorkTime() {
        return estimatedWorkTime;
    }

    public void setEstimatedWorkTime(double estimatedWorkTime) {
        double oldValue = this.estimatedWorkTime;
        this.estimatedWorkTime = estimatedWorkTime;
        propertyChangeSupport.firePropertyChange("estimatedWorkTime", oldValue, this.estimatedWorkTime);
    }

    public double getExtraWorkload() {
        return extraWorkload;
    }

    public void setExtraWorkload(double extraWorkload) {
        double oldValue = this.extraWorkload;
        this.extraWorkload = extraWorkload;
        propertyChangeSupport.firePropertyChange("extraWorkload", oldValue, this.extraWorkload);
    }

    public double getNormalWorkload() {
        return normalWorkload;
    }

    public void setNormalWorkload(double normalWorkload) {
        double oldValue = this.normalWorkload;
        this.normalWorkload = normalWorkload;
        propertyChangeSupport.firePropertyChange("normalWorkload", oldValue, this.normalWorkload);
    }

    public double getPaygWorkload() {
        return paygWorkload;
    }

    public void setPaygWorkload(double paygWorkload) {
        double oldValue = this.paygWorkload;
        this.paygWorkload = paygWorkload;
        propertyChangeSupport.firePropertyChange("paygWorkload", oldValue, this.paygWorkload);
    }

    public Date getReportBecameAvailable() {
        return reportBecameAvailable;
    }

    public void setReportBecameAvailable(Date reportBecameAvailable) {
        Date oldValue = this.reportBecameAvailable;
        this.reportBecameAvailable = reportBecameAvailable;
        propertyChangeSupport.firePropertyChange("reportBecameAvailable", oldValue, this.reportBecameAvailable);
    }

    public double getReportingTime() {
        return reportingTime;
    }

    public void setReportingTime(double reportingTime) {
        double oldValue = this.reportingTime;
        this.reportingTime = reportingTime;
        propertyChangeSupport.firePropertyChange("reportingTime", oldValue, this.reportingTime);
    }

    public double getWorkTime() {
        return workTime;
    }

    public void setWorkTime(double workTime) {
        double oldValue = this.workTime;
        this.workTime = workTime;
        propertyChangeSupport.firePropertyChange("workTime", oldValue, this.workTime);
    }

    public String getWorkloadCategory() {
        return workloadCategory;
    }

    public void setWorkloadCategory(String workloadCategory) {
        String oldValue = this.workloadCategory;
        this.workloadCategory = workloadCategory;
        propertyChangeSupport.firePropertyChange("workloadCategory", oldValue, this.workloadCategory);
    }

    public double getWorkloadOnDayBeforeThis() {
        return workloadOnDayBeforeThis;
    }

    public void setWorkloadOnDayBeforeThis(double workloadOnDayBeforeThis) {
        double oldValue = this.workloadOnDayBeforeThis;
        this.workloadOnDayBeforeThis = workloadOnDayBeforeThis;
        propertyChangeSupport.firePropertyChange("workloadOnDayBeforeThis", oldValue, this.workloadOnDayBeforeThis);
    }
}
