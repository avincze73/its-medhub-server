/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

import base.BaseDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vincze.attila
 */
public class ImagePatientDataDTO extends BaseDTO {

    private List<ScannedPatientDataImageDTO> imageList;

    public ImagePatientDataDTO() {
        super();
        imageList = new ArrayList<ScannedPatientDataImageDTO>();
    }

    public ImagePatientDataDTO(long id) {
        super(id);
        imageList = new ArrayList<ScannedPatientDataImageDTO>();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ImagePatientDataDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public List<ScannedPatientDataImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<ScannedPatientDataImageDTO> imageList) {
        List<ScannedPatientDataImageDTO> oldValue = this.imageList;
        this.imageList = imageList;
        propertyChangeSupport.firePropertyChange("imageList", oldValue, this.imageList);
    }


}
