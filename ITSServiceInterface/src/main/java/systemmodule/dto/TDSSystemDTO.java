/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systemmodule.dto;

import base.BaseDTO;

/**
 *
 * @author vincze.attila
 */
public class TDSSystemDTO extends BaseDTO {

    private int timeZoneGmt;
    private String radiologistAssignmentMode;

    public TDSSystemDTO(int id) {
        super(id);
    }

    public TDSSystemDTO() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TDSSystemDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public String getRadiologistAssignmentMode() {
        return radiologistAssignmentMode;
    }

    public void setRadiologistAssignmentMode(String radiologistAssignmentMode) {
        String oldValue = this.radiologistAssignmentMode;
        this.radiologistAssignmentMode = radiologistAssignmentMode;
        propertyChangeSupport.firePropertyChange("radiologistAssignmentMode", oldValue, this.radiologistAssignmentMode);
    }

    public int getTimeZoneGmt() {
        return timeZoneGmt;
    }

    public void setTimeZoneGmt(int timeZoneGmt) {
        int oldValue = this.timeZoneGmt;
        this.timeZoneGmt = timeZoneGmt;
        propertyChangeSupport.firePropertyChange("timeZoneGmt", oldValue, this.timeZoneGmt);
    }


}
