/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.dto;

import base.BaseDTO;
import hospitalmodule.dto.AvailabilityPerWeekDTO;
import java.util.ArrayList;
import java.util.Date;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

/**
 *
 * @author vincze.attila
 */
public class RadAvailabilityDTO extends BaseDTO {

    private Date fromDate;
    private Date toDate;
    private Date added;
    private boolean valid;
    private boolean confirmedByPM;
    private boolean confirmedByRad;
    private AvailabilityPerWeekDTO normalAvailabilityPerWeek;
    private AvailabilityPerWeekDTO maxAvailabilityPerWeek;
    private TDSRadiologistDTO tdsRadiologist;
    private transient ObservableList<AvailabilityPerWeekDTO> normalAvailabilityList;
    private transient ObservableList<AvailabilityPerWeekDTO> maxAvailabilityList;

    public RadAvailabilityDTO(long id) {
        super(id);
        normalAvailabilityPerWeek = new AvailabilityPerWeekDTO();
        maxAvailabilityPerWeek = new AvailabilityPerWeekDTO();
        normalAvailabilityList = ObservableCollections.observableList(new ArrayList<AvailabilityPerWeekDTO>());
        maxAvailabilityList = ObservableCollections.observableList(new ArrayList<AvailabilityPerWeekDTO>());
    }

    public RadAvailabilityDTO() {
        super();
        normalAvailabilityPerWeek = new AvailabilityPerWeekDTO();
        maxAvailabilityPerWeek = new AvailabilityPerWeekDTO();
        normalAvailabilityList = ObservableCollections.observableList(new ArrayList<AvailabilityPerWeekDTO>());
        maxAvailabilityList = ObservableCollections.observableList(new ArrayList<AvailabilityPerWeekDTO>());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RadAvailabilityDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        Date oldValue = this.added;
        this.added = added;
        propertyChangeSupport.firePropertyChange("added", oldValue, this.added);
    }

    public boolean isConfirmedByPM() {
        return confirmedByPM;
    }

    public void setConfirmedByPM(boolean confirmedByPM) {
        boolean oldValue = this.confirmedByPM;
        this.confirmedByPM = confirmedByPM;
        propertyChangeSupport.firePropertyChange("confirmedByPM", oldValue, this.confirmedByPM);
    }

    public boolean isConfirmedByRad() {
        return confirmedByRad;
    }

    public void setConfirmedByRad(boolean confirmedByRad) {
        boolean oldValue = this.confirmedByRad;
        this.confirmedByRad = confirmedByRad;
        propertyChangeSupport.firePropertyChange("confirmedByRad", oldValue, this.confirmedByRad);
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        Date oldValue = this.fromDate;
        this.fromDate = fromDate;
        propertyChangeSupport.firePropertyChange("fromDate", oldValue, this.fromDate);
    }

    public AvailabilityPerWeekDTO getMaxAvailabilityPerWeek() {
        return maxAvailabilityPerWeek;
    }

    public void setMaxAvailabilityPerWeek(AvailabilityPerWeekDTO maxAvailabilityPerWeek) {
        AvailabilityPerWeekDTO oldValue = this.maxAvailabilityPerWeek;
        this.maxAvailabilityPerWeek = maxAvailabilityPerWeek;
        propertyChangeSupport.firePropertyChange("maxAvailabilityPerWeek", oldValue, this.maxAvailabilityPerWeek);
        maxAvailabilityList.clear();
        maxAvailabilityList.add(this.maxAvailabilityPerWeek);
    }

    public AvailabilityPerWeekDTO getNormalAvailabilityPerWeek() {
        return normalAvailabilityPerWeek;
    }

    public void setNormalAvailabilityPerWeek(AvailabilityPerWeekDTO normalAvailabilityPerWeek) {
        AvailabilityPerWeekDTO oldValue = this.normalAvailabilityPerWeek;
        this.normalAvailabilityPerWeek = normalAvailabilityPerWeek;
        propertyChangeSupport.firePropertyChange("normalAvailabilityPerWeek", oldValue, this.normalAvailabilityPerWeek);
        normalAvailabilityList.clear();
        normalAvailabilityList.add(this.normalAvailabilityPerWeek);
    }

    public TDSRadiologistDTO getTdsRadiologist() {
        return tdsRadiologist;
    }

    public void setTdsRadiologist(TDSRadiologistDTO tdsRadiologist) {
        TDSRadiologistDTO oldValue = this.tdsRadiologist;
        this.tdsRadiologist = tdsRadiologist;
        propertyChangeSupport.firePropertyChange("tdsRadiologist", oldValue, this.tdsRadiologist);
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        Date oldValue = this.toDate;
        this.toDate = toDate;
        propertyChangeSupport.firePropertyChange("toDate", oldValue, this.toDate);
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        boolean oldValue = this.valid;
        this.valid = valid;
        propertyChangeSupport.firePropertyChange("valid", oldValue, this.valid);
    }

    public ObservableList<AvailabilityPerWeekDTO> getMaxAvailabilityList() {
        return maxAvailabilityList;
    }

    public void setMaxAvailabilityList(ObservableList<AvailabilityPerWeekDTO> maxAvailabilityList) {
        ObservableList<AvailabilityPerWeekDTO> oldValue = this.maxAvailabilityList;
        this.maxAvailabilityList = maxAvailabilityList;
        propertyChangeSupport.firePropertyChange("maxAvailabilityList", oldValue, this.maxAvailabilityList);
    }

    public ObservableList<AvailabilityPerWeekDTO> getNormalAvailabilityList() {
        return normalAvailabilityList;
    }

    public void setNormalAvailabilityList(ObservableList<AvailabilityPerWeekDTO> normalAvailabilityList) {
        ObservableList<AvailabilityPerWeekDTO> oldValue = this.normalAvailabilityList;
        this.normalAvailabilityList = normalAvailabilityList;
        propertyChangeSupport.firePropertyChange("normalAvailabilityList", oldValue, this.normalAvailabilityList);
    }
}
