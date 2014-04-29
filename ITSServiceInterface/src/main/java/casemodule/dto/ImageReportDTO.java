/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

import base.BaseDTO;
import java.util.List;

/**
 *
 * @author vincze.attila
 */
public class ImageReportDTO extends BaseDTO {

    private List<ScannedReportImageDTO> imageList;

    public ImageReportDTO(long id) {
        super(id);
    }

    public ImageReportDTO() {
        this(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ImageReportDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public List<ScannedReportImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<ScannedReportImageDTO> imageList) {
        List<ScannedReportImageDTO> oldValue = this.imageList;
        this.imageList = imageList;
        propertyChangeSupport.firePropertyChange("imageList", oldValue, this.imageList);
    }
}
