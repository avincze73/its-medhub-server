/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

import base.BaseDTO;
import java.util.Date;
import masterdatamodule.dto.CaseFlagDTO;

/**
 *
 * @author vincze.attila
 */
public class FlagAssignmentDTO extends BaseDTO {

    private CaseDTO caseItBelongsTo;
    private CaseFlagDTO flag;
    private Date added;
    private boolean active;

    public FlagAssignmentDTO(long id) {
        super(id);
    }

    public FlagAssignmentDTO() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FlagAssignmentDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        boolean oldValue = this.active;
        this.active = active;
        propertyChangeSupport.firePropertyChange("active", oldValue, this.active);
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        Date oldValue = this.added;
        this.added = added;
        propertyChangeSupport.firePropertyChange("added", oldValue, this.added);
    }

    public CaseDTO getCaseItBelongsTo() {
        return caseItBelongsTo;
    }

    public void setCaseItBelongsTo(CaseDTO caseItBelongsTo) {
        CaseDTO oldValue = this.caseItBelongsTo;
        this.caseItBelongsTo = caseItBelongsTo;
        propertyChangeSupport.firePropertyChange("caseItBelongsTo", oldValue, this.caseItBelongsTo);
    }

    public CaseFlagDTO getFlag() {
        return flag;
    }

    public void setFlag(CaseFlagDTO flag) {
        CaseFlagDTO oldValue = this.flag;
        this.flag = flag;
        propertyChangeSupport.firePropertyChange("flag", oldValue, this.flag);
    }
}
