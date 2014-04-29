/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

import base.BaseDTO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import tdsdicominterface.DicomDataSet;

/**
 *
 * @author vincze.attila
 */
public class DicomImageDTO extends BaseDTO {

    private String dicomUniqueId;
    private String sopClassUid;
    private String instanceUid;
    private String timeZoneOffsetFromUtc;
    private String instanceNumber;
    private String sopInstanceStatus;
    private String sopAuthorizationDateTime;
    private String sopAuthorizationComment;
    private DicomDataSet dicomDataSet;
    private DicomDataSet dicomDataSetIcon;
    private SeriesDTO series;
    private boolean iconOnly;
    private ProcessedDicomImage processedDicomImage;
    private ProcessedDicomImage processedDicomImageIcon;

    public DicomImageDTO(long id) {
        super(id);
        iconOnly = true;
    }

    public DicomImageDTO() {
        super();
        iconOnly = true;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DicomImageDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public DicomDataSet getDicomDataSet() {
        return dicomDataSet;
    }

    public void setDicomDataSet(DicomDataSet dicomDataSet) {
        DicomDataSet oldValue = this.dicomDataSet;
        this.dicomDataSet = dicomDataSet;
        propertyChangeSupport.firePropertyChange("dicomDataSet", oldValue, this.dicomDataSet);
        //processedDicomImage = new ProcessedDicomImage(dicomDataSet);
    }

    public byte[] getDicomDataSetArray() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(dicomDataSet);
        } catch (IOException ex) {
            Logger.getLogger(DicomImageDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return baos.toByteArray();
    }

    public void setDicomDataSetArray(byte[] array) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(array));
            dicomDataSet = (DicomDataSet) ois.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DicomImageDTO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DicomImageDTO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(DicomImageDTO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getDicomUniqueId() {
        return dicomUniqueId;
    }

    public void setDicomUniqueId(String dicomUniqueId) {
        String oldValue = this.dicomUniqueId;
        this.dicomUniqueId = dicomUniqueId;
        propertyChangeSupport.firePropertyChange("dicomUniqueId", oldValue, this.dicomUniqueId);
    }

    public SeriesDTO getSeries() {
        return series;
    }

    public void setSeries(SeriesDTO series) {
        SeriesDTO oldValue = this.series;
        this.series = series;
        propertyChangeSupport.firePropertyChange("series", oldValue, this.series);
    }

    public String getInstanceNumber() {
        return instanceNumber;
    }

    public void setInstanceNumber(String instanceNumber) {
        String oldValue = this.instanceNumber;
        this.instanceNumber = instanceNumber;
        propertyChangeSupport.firePropertyChange("instanceNumber", oldValue, this.instanceNumber);
    }

    public String getSopAuthorizationComment() {
        return sopAuthorizationComment;
    }

    public void setSopAuthorizationComment(String sopAuthorizationComment) {
        String oldValue = this.sopAuthorizationComment;
        this.sopAuthorizationComment = sopAuthorizationComment;
        propertyChangeSupport.firePropertyChange("sopAuthorizationComment", oldValue, this.sopAuthorizationComment);
    }

    public String getSopAuthorizationDateTime() {
        return sopAuthorizationDateTime;
    }

    public void setSopAuthorizationDateTime(String sopAuthorizationDateTime) {
        String oldValue = this.sopAuthorizationDateTime;
        this.sopAuthorizationDateTime = sopAuthorizationDateTime;
        propertyChangeSupport.firePropertyChange("sopAuthorizationDateTime", oldValue, this.sopAuthorizationDateTime);
    }

    public String getSopClassUid() {
        return sopClassUid;
    }

    public void setSopClassUid(String sopClassUid) {
        String oldValue = this.sopClassUid;
        this.sopClassUid = sopClassUid;
        propertyChangeSupport.firePropertyChange("sopClassUid", oldValue, this.sopClassUid);
    }

    public String getSopInstanceStatus() {
        return sopInstanceStatus;
    }

    public void setSopInstanceStatus(String sopInstanceStatus) {
        String oldValue = this.sopInstanceStatus;
        this.sopInstanceStatus = sopInstanceStatus;
        propertyChangeSupport.firePropertyChange("sopInstanceStatus", oldValue, this.sopInstanceStatus);
    }

    public String getInstanceUid() {
        return instanceUid;
    }

    public void setInstanceUid(String instanceUid) {
        String oldValue = this.instanceUid;
        this.instanceUid = instanceUid;
        propertyChangeSupport.firePropertyChange("instanceUid", oldValue, this.instanceUid);
    }

    public String getTimeZoneOffsetFromUtc() {
        return timeZoneOffsetFromUtc;
    }

    public void setTimeZoneOffsetFromUtc(String timeZoneOffsetFromUtc) {
        String oldValue = this.timeZoneOffsetFromUtc;
        this.timeZoneOffsetFromUtc = timeZoneOffsetFromUtc;
        propertyChangeSupport.firePropertyChange("timeZoneOffsetFromUtc", oldValue, this.timeZoneOffsetFromUtc);
    }

    public DicomDataSet getDicomDataSetIcon() {
        return dicomDataSetIcon;
    }

    public void setDicomDataSetIcon(DicomDataSet dicomDataSetIcon) {
        DicomDataSet oldValue = this.dicomDataSetIcon;
        this.dicomDataSetIcon = dicomDataSetIcon;
        propertyChangeSupport.firePropertyChange("dicomDataSetIcon", oldValue, this.dicomDataSetIcon);
        //processedDicomImageIcon = new ProcessedDicomImage(dicomDataSet);
    }

    public byte[] getDicomDataSetIconArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(dicomDataSetIcon);
        return baos.toByteArray();
    }

    public void setDicomDataSetIconArray(byte[] array) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(array));
        dicomDataSetIcon = (DicomDataSet) ois.readObject();
    }

    public boolean isIconOnly() {
        return iconOnly;
    }

    public void setIconOnly(boolean iconOnly) {
        boolean oldValue = this.iconOnly;
        this.iconOnly = iconOnly;
        propertyChangeSupport.firePropertyChange("iconOnly", oldValue, this.iconOnly);
    }

    public ProcessedDicomImage getProcessedDicomImage() {
        //return processedDicomImage;
        return processedDicomImageIcon;
    }

    public void setProcessedDicomImage(ProcessedDicomImage processedDicomImage) {
        this.processedDicomImage = processedDicomImage;

    }

    public ProcessedDicomImage getProcessedDicomImageIcon() {
        return processedDicomImageIcon;
    }

    public void setProcessedDicomImageIcon(ProcessedDicomImage processedDicomImageIcon) {
        this.processedDicomImageIcon = processedDicomImageIcon;
    }

    
}
