package hospitalmodule.dto;

import casemodule.dto.StudyDTO;
import base.BaseDTO;
import casemodule.dto.SeriesDTO;

/**
 *
 * @author vincze.attila
 */
public class StudyLevelInvoiceElementDTO extends BaseDTO {

    private StudyDTO study;
    private SeriesDTO series;
    private String fellIntoAvailability;
    private double sumValue;
    private CaseLevelInvoiceElementDTO caseLevelInvoiceElement;

    public StudyLevelInvoiceElementDTO() {
        super();
    }

    public StudyLevelInvoiceElementDTO(int id) {
        super(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof StudyLevelInvoiceElementDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public StudyDTO getStudy() {
        return study;
    }

    public void setStudy(StudyDTO study) {
        StudyDTO oldValue = this.study;
        this.study = study;
        propertyChangeSupport.firePropertyChange("study", oldValue, this.study);
    }

    public String getFellIntoAvailability() {
        return fellIntoAvailability;
    }

    public void setFellIntoAvailability(String fellIntoAvailability) {
        String oldValue = this.fellIntoAvailability;
        this.fellIntoAvailability = fellIntoAvailability;
        propertyChangeSupport.firePropertyChange("fellIntoAvailability", oldValue, this.fellIntoAvailability);
    }

    public double getSumValue() {
        return sumValue;
    }

    public void setSumValue(double sumValue) {
        double oldValue = this.sumValue;
        this.sumValue = sumValue;
        propertyChangeSupport.firePropertyChange("sumValue", oldValue, this.sumValue);
    }

    public CaseLevelInvoiceElementDTO getCaseLevelInvoiceElement() {
        return caseLevelInvoiceElement;
    }

    public void setCaseLevelInvoiceElement(CaseLevelInvoiceElementDTO caseLevelInvoiceElement) {
        CaseLevelInvoiceElementDTO oldValue = this.caseLevelInvoiceElement;
        this.caseLevelInvoiceElement = caseLevelInvoiceElement;
        propertyChangeSupport.firePropertyChange("caseLevelInvoiceElement", oldValue, this.caseLevelInvoiceElement);
    }

    public SeriesDTO getSeries() {
        return series;
    }

    public void setSeries(SeriesDTO series) {
        SeriesDTO oldValue = this.series;
        this.series = series;
        propertyChangeSupport.firePropertyChange("series", oldValue, this.series);
    }
}
