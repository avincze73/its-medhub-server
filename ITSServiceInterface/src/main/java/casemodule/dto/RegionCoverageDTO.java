/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

import base.BaseDTO;
import java.util.Date;
import masterdatamodule.dto.BodyRegionDTO;

/**
 *
 * @author vincze.attila
 */
public class RegionCoverageDTO extends BaseDTO {

    private SeriesDTO series;
    private BodyRegionDTO bodyRegion;
    private boolean updated;
    private Date updating;
    private Date denied;
    private Date confirmed;

    public RegionCoverageDTO(int id) {
        super(id);
    }

    public RegionCoverageDTO() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RegionCoverageDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public BodyRegionDTO getBodyRegion() {
        return bodyRegion;
    }

    public void setBodyRegion(BodyRegionDTO bodyRegion) {
        BodyRegionDTO oldValue = this.bodyRegion;
        this.bodyRegion = bodyRegion;
        propertyChangeSupport.firePropertyChange("bodyRegion", oldValue, this.bodyRegion);
    }

    public Date getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Date confirmed) {
        Date oldValue = this.confirmed;
        this.confirmed = confirmed;
        propertyChangeSupport.firePropertyChange("confirmed", oldValue, this.confirmed);
    }

    public Date getDenied() {
        return denied;
    }

    public void setDenied(Date denied) {
        Date oldValue = this.denied;
        this.denied = denied;
        propertyChangeSupport.firePropertyChange("denied", oldValue, this.denied);
    }

    public SeriesDTO getSeries() {
        return series;
    }

    public void setSeries(SeriesDTO series) {
        SeriesDTO oldValue = this.series;
        this.series = series;
        propertyChangeSupport.firePropertyChange("series", oldValue, this.series);
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        boolean oldValue = this.updated;
        this.updated = updated;
        propertyChangeSupport.firePropertyChange("updated", oldValue, this.updated);
    }

    public Date getUpdating() {
        return updating;
    }

    public void setUpdating(Date updating) {
        Date oldValue = this.updating;
        this.updating = updating;
        propertyChangeSupport.firePropertyChange("updating", oldValue, this.updating);
    }
}
