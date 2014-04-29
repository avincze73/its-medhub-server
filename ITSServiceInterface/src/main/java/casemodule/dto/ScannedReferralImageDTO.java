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
public class ScannedReferralImageDTO extends BaseDTO {

    //private String fileName;
    private byte[] scannedImage;
    //private int numInList;
    private ImageReferralDTO imageReferral;

    public ScannedReferralImageDTO(long id) {
        super(id);
    }

    public ScannedReferralImageDTO() {
        super();
    }

//    public ScannedReferralImageDTO(String fileName, int numInList) {
//        super();
//        this.fileName = fileName;
//        this.numInList = numInList;
//    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ScannedReferralImageDTO)) {
            return false;
        }
        return super.equals(obj);
    }

//    public String getFileName() {
//        return fileName;
//    }
//
//    public void setFileName(String fileName) {
//        String oldValue = this.fileName;
//        this.fileName = fileName;
//        propertyChangeSupport.firePropertyChange("fileName", oldValue, this.fileName);
//    }

    public byte[] getScannedImage() {
        return scannedImage;
    }

    public void setScannedImage(byte[] scannedImage) {
        byte[] oldValue = this.scannedImage;
        this.scannedImage = scannedImage;
        propertyChangeSupport.firePropertyChange("scannedImage", oldValue, this.scannedImage);
    }

    public ImageReferralDTO getImageReferral() {
        return imageReferral;
    }

    public void setImageReferral(ImageReferralDTO imageReferral) {
        ImageReferralDTO oldValue = this.imageReferral;
        this.imageReferral = imageReferral;
        propertyChangeSupport.firePropertyChange("imageReferral", oldValue, this.imageReferral);
    }

//    public int getNumInList() {
//        return numInList;
//    }
//
//    public void setNumInList(int numInList) {
//        int oldValue = this.numInList;
//        this.numInList = numInList;
//        propertyChangeSupport.firePropertyChange("numInList", oldValue, this.numInList);
//    }
}
