/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.dto;

import base.BaseDTO;
import java.util.Date;
import masterdatamodule.dto.BodyRegionDTO;
import masterdatamodule.dto.ModalityDTO;

/**
 *
 * @author vincze.attila
 */
public class RadCompetenceDTO extends BaseDTO {

    private ModalityDTO modality;
    private BodyRegionDTO bodyRegion;
    private int competenceLevel;
    private Date addingDateTime;
    private boolean valid;
    private TDSRadiologistDTO tdsRadiologist;

    public RadCompetenceDTO(long id) {
        super(id);
    }

    public RadCompetenceDTO() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RadCompetenceDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public Date getAddingDateTime() {
        return addingDateTime;
    }

    public void setAddingDateTime(Date addingDateTime) {
        Date oldValue = this.addingDateTime;
        this.addingDateTime = addingDateTime;
        propertyChangeSupport.firePropertyChange("addingDateTime", oldValue, this.addingDateTime);
    }

    public BodyRegionDTO getBodyRegion() {
        return bodyRegion;
    }

    public void setBodyRegion(BodyRegionDTO bodyRegion) {
        BodyRegionDTO oldValue = this.bodyRegion;
        this.bodyRegion = bodyRegion;
        propertyChangeSupport.firePropertyChange("bodyRegion", oldValue, this.bodyRegion);
    }

    public int getCompetenceLevel() {
        return competenceLevel;
    }

    public void setCompetenceLevel(int competenceLevel) {
        int oldValue = this.competenceLevel;
        this.competenceLevel = competenceLevel;
        propertyChangeSupport.firePropertyChange("competenceLevel", oldValue, this.competenceLevel);
    }

    public ModalityDTO getModality() {
        return modality;
    }

    public void setModality(ModalityDTO modality) {
        ModalityDTO oldValue = this.modality;
        this.modality = modality;
        propertyChangeSupport.firePropertyChange("modality", oldValue, this.modality);
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        boolean oldValue = this.valid;
        this.valid = valid;
        propertyChangeSupport.firePropertyChange("valid", oldValue, this.valid);
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
