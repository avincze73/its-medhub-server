/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.dto;

import base.BaseDTO;
import java.util.Date;

/**
 *
 * @author vincze.attila
 */
public class CompanyAssignmentDTO extends BaseDTO {

    private Date started;
    private Date ended;
    private boolean active;
    private CompanyDTO company;
    private TDSRadiologistDTO tdsRadiologist;

    public CompanyAssignmentDTO(long id) {
        super(id);
    }

    public CompanyAssignmentDTO() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CompanyAssignmentDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public Date getEnded() {
        return ended;
    }

    public void setEnded(Date ended) {
        Date oldValue = this.ended;
        this.ended = ended;
        propertyChangeSupport.firePropertyChange("ended", oldValue, this.ended);
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        Date oldValue = this.started;
        this.started = started;
        propertyChangeSupport.firePropertyChange("started", oldValue, this.started);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        boolean oldValue = this.active;
        this.active = active;
        propertyChangeSupport.firePropertyChange("active", oldValue, this.active);
    }

    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        CompanyDTO oldValue = this.company;
        this.company = company;
        propertyChangeSupport.firePropertyChange("company", oldValue, this.company);
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
