/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmodule.dto;

import base.BaseDTO;
import masterdatamodule.dto.BodyRegionDTO;

/**
 *
 * @author vincze.attila
 */
public class BodyRegionWithBandDTO extends BaseDTO {

    private WorkBandTableDTO workBandTable;
    private BodyRegionDTO bodyRegion;
    private String bandAssignment;

    public BodyRegionWithBandDTO(long id) {
        super(id);
    }

    public BodyRegionWithBandDTO() {
        super();
    }

    public BodyRegionWithBandDTO(long id, BodyRegionDTO bodyRegion, String bandAssignment) {
        super(id);
        this.bodyRegion = bodyRegion;
        this.bandAssignment = bandAssignment;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BodyRegionWithBandDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public String getBandAssignment() {
        return bandAssignment;
    }

    public void setBandAssignment(String bandAssignment) {
        String oldValue = this.bandAssignment;
        this.bandAssignment = bandAssignment;
        propertyChangeSupport.firePropertyChange("bandAssignment", oldValue, this.bandAssignment);
    }

    public BodyRegionDTO getBodyRegion() {
        return bodyRegion;
    }

    public void setBodyRegion(BodyRegionDTO bodyRegion) {
        BodyRegionDTO oldValue = this.bodyRegion;
        this.bodyRegion = bodyRegion;
        propertyChangeSupport.firePropertyChange("bodyRegion", oldValue, this.bodyRegion);
    }

    public WorkBandTableDTO getWorkBandTable() {
        return workBandTable;
    }

    public void setWorkBandTable(WorkBandTableDTO workBandTable) {
        WorkBandTableDTO oldValue = this.workBandTable;
        this.workBandTable = workBandTable;
        propertyChangeSupport.firePropertyChange("workBandTable", oldValue, this.workBandTable);
    }
}
