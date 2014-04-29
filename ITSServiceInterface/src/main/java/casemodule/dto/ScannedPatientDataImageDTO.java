/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

import base.BaseDTO;

/**
 *
 * @author vincze.attila
 */
public class ScannedPatientDataImageDTO extends BaseDTO {

    private byte[] scannedImage;
    private ImagePatientDataDTO imagePatientData;

    public ScannedPatientDataImageDTO(long id) {
        super(id);
    }

    public ScannedPatientDataImageDTO() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ScannedPatientDataImageDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public byte[] getScannedImage() {
        return scannedImage;
    }

    public void setScannedImage(byte[] scannedImage) {
        byte[] oldValue = this.scannedImage;
        this.scannedImage = scannedImage;
        propertyChangeSupport.firePropertyChange("scannedImage", oldValue, this.scannedImage);
    }

    public ImagePatientDataDTO getImagePatientData() {
        return imagePatientData;
    }

    public void setImagePatientData(ImagePatientDataDTO imagePatientData) {
        ImagePatientDataDTO oldValue = this.imagePatientData;
        this.imagePatientData = imagePatientData;
        propertyChangeSupport.firePropertyChange("imagePatientData", oldValue, this.imagePatientData);
    }
}
