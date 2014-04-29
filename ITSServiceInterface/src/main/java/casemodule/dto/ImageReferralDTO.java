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
public class ImageReferralDTO extends BaseDTO {

    private List<ScannedReferralImageDTO> imageList;
    
    public ImageReferralDTO() {
        super();
        imageList = new ArrayList<ScannedReferralImageDTO>();
    }

    public ImageReferralDTO(int id) {
        super(id);
        imageList = new ArrayList<ScannedReferralImageDTO>();
    }

    public ImageReferralDTO(long id) {
        super(id);
        imageList = new ArrayList<ScannedReferralImageDTO>();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ImageReferralDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public List<ScannedReferralImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<ScannedReferralImageDTO> imageList) {
        List<ScannedReferralImageDTO> oldValue = this.imageList;
        this.imageList = imageList;
        propertyChangeSupport.firePropertyChange("imageList", oldValue, this.imageList);
    }

    public void addScannedImage(ScannedReferralImageDTO scannedImage){
        scannedImage.setImageReferral(this);
        imageList.add(scannedImage);
    }

}
