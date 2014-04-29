/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.dto;

import base.BaseDTO;
import hospitalmodule.dto.HospitalDTO;
import java.util.Date;

/**
 *
 * @author vincze.attila
 */
public class WorkPlaceDTO extends BaseDTO {

    private Date fromDate;
    private Date toDate;
    private Date added;
    private HospitalDTO hospital;
    private TDSRadiologistDTO tdsRadiologist;

    public WorkPlaceDTO(long id) {
        super(id);
    }

    public WorkPlaceDTO() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof WorkPlaceDTO)) {
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

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        Date oldValue = this.fromDate;
        this.fromDate = fromDate;
        propertyChangeSupport.firePropertyChange("fromDate", oldValue, this.fromDate);
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        Date oldValue = this.toDate;
        this.toDate = toDate;
        propertyChangeSupport.firePropertyChange("toDate", oldValue, this.toDate);
    }

    public HospitalDTO getHospital() {
        return hospital;
    }

    public void setHospital(HospitalDTO hospital) {
        HospitalDTO oldValue = this.hospital;
        this.hospital = hospital;
        propertyChangeSupport.firePropertyChange("hospital", oldValue, this.hospital);
    }

    public TDSRadiologistDTO getTdsRadiologist() {
        return tdsRadiologist;
    }

    public void setTdsRadiologist(TDSRadiologistDTO tdsRadiologist) {
        TDSRadiologistDTO oldValue = this.tdsRadiologist;
        this.tdsRadiologist = tdsRadiologist;
        propertyChangeSupport.firePropertyChange("tdsRadiologist", oldValue, this.tdsRadiologist);
    }
}
