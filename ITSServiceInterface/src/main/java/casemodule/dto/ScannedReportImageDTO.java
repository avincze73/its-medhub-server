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
public class ScannedReportImageDTO extends BaseDTO {

    private byte[] scannedImage;
    private ImageReportDTO imageReport;

    public ScannedReportImageDTO(long id) {
        super(id);
    }

    public ScannedReportImageDTO() {
        this(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ScannedReportImageDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public ImageReportDTO getImageReport() {
        return imageReport;
    }

    public void setImageReport(ImageReportDTO imageReport) {
        ImageReportDTO oldValue = this.imageReport;
        this.imageReport = imageReport;
        propertyChangeSupport.firePropertyChange("imageReport", oldValue, this.imageReport);
    }

    public byte[] getScannedImage() {
        return scannedImage;
    }

    public void setScannedImage(byte[] scannedImage) {
        byte[] oldValue = this.scannedImage;
        this.scannedImage = scannedImage;
        propertyChangeSupport.firePropertyChange("scannedImage", oldValue, this.scannedImage);
    }
}
