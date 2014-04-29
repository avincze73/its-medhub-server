/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.dto;

import base.BaseDTO;
import java.beans.PropertyVetoException;

/**
 *
 * @author vincze.attila
 */
public class BodyRegionDTO extends BaseDTO {

    private String englishName;
    private String hungarianName;
    private String dicomName;
    private String snomedCode;
    private Integer groupNumber;
    //error
    private boolean englishNameError;
    private boolean hungarianNameError;
    private boolean dicomNameError;
    private boolean snomedCodeError;
    private boolean groupNumberError;
    //optional
    private boolean englishNameOptional;
    private boolean hungarianNameOptional;
    private boolean dicomNameOptional;
    private boolean snomedCodeOptional;
    private boolean groupNumberOptional;

    public BodyRegionDTO() {
        this(0, null, null, null, null, 0);
    }

    public BodyRegionDTO(String englishName, String hungarianName, String dicomName, Integer groupNumber) {
        this(0, englishName, hungarianName, dicomName, null, groupNumber);
    }

    public BodyRegionDTO(long id, String englishName, String hungarianName, String dicomName, Integer groupNumber) {
        this(id, englishName, hungarianName, dicomName, null, groupNumber);
    }

    public BodyRegionDTO(String englishName, String hungarianName, String dicomName, String snomedCode, Integer groupNumber) {
        this(0, englishName, hungarianName, dicomName, snomedCode, groupNumber);
    }

    public BodyRegionDTO(String englishName, String hungarianName, String dicomName, String snomedCode) {
        this(0, englishName, hungarianName, dicomName, snomedCode, 0);
    }

    public BodyRegionDTO(long id, String englishName, String hungarianName,
            String dicomName, String snomedCode, Integer groupNumber) {
        super(id);
        this.englishName = englishName;
        this.hungarianName = hungarianName;
        this.dicomName = dicomName;
        this.snomedCode = snomedCode;
        this.groupNumber = groupNumber;
        this.dicomNameError = true;
        this.snomedCodeError = true;
    }

    @Override
    public BodyRegionDTO clone() throws CloneNotSupportedException {
        BodyRegionDTO result = (BodyRegionDTO) super.clone();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BodyRegionDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) throws PropertyVetoException {
        String oldValue = this.englishName;
        vetoableChangeSupport.fireVetoableChange("englishName", oldValue, englishName);
        this.englishName = englishName;
        propertyChangeSupport.firePropertyChange("englishName", oldValue, this.englishName);
    }

    public String getHungarianName() {
        return hungarianName;
    }

    public void setHungarianName(String hungarianName) throws PropertyVetoException {
        String oldValue = this.hungarianName;
        vetoableChangeSupport.fireVetoableChange("hungarianName", oldValue, hungarianName);
        this.hungarianName = hungarianName;
        propertyChangeSupport.firePropertyChange("hungarianName", oldValue, this.hungarianName);
    }

    public String getDicomName() {
        return dicomName;
    }

    public void setDicomName(String dicomName) throws PropertyVetoException {
        String oldValue = this.dicomName;
        vetoableChangeSupport.fireVetoableChange("dicomName", oldValue, dicomName);
        this.dicomName = dicomName;
        propertyChangeSupport.firePropertyChange("dicomName", oldValue, this.dicomName);
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) throws PropertyVetoException {
        Integer oldValue = this.groupNumber;
        vetoableChangeSupport.fireVetoableChange("groupNumber", oldValue, groupNumber);
        this.groupNumber = groupNumber;
        propertyChangeSupport.firePropertyChange("groupNumber", oldValue, this.groupNumber);
    }

    public String getSnomedCode() {
        return snomedCode;
    }

    public void setSnomedCode(String snomedCode) {
        String oldValue = this.snomedCode;
        this.snomedCode = snomedCode;
        propertyChangeSupport.firePropertyChange("snomedCode", oldValue, this.snomedCode);
    }

    public boolean isDicomNameError() {
        return dicomNameError;
    }

    public void setDicomNameError(boolean dicomNameError) {
        boolean oldValue = this.dicomNameError;
        this.dicomNameError = dicomNameError;
        propertyChangeSupport.firePropertyChange("dicomNameError", oldValue, this.dicomNameError);
    }

    public boolean isDicomNameOptional() {
        return dicomNameOptional;
    }

    public void setDicomNameOptional(boolean dicomNameOptional) {
        boolean oldValue = this.dicomNameOptional;
        this.dicomNameOptional = dicomNameOptional;
        propertyChangeSupport.firePropertyChange("dicomNameOptional", oldValue, this.dicomNameOptional);
    }

    public boolean isEnglishNameError() {
        return englishNameError;
    }

    public void setEnglishNameError(boolean englishNameError) {
        boolean oldValue = this.englishNameError;
        this.englishNameError = englishNameError;
        propertyChangeSupport.firePropertyChange("englishNameError", oldValue, this.englishNameError);
    }

    public boolean isEnglishNameOptional() {
        return englishNameOptional;
    }

    public void setEnglishNameOptional(boolean englishNameOptional) {
        boolean oldValue = this.englishNameOptional;
        this.englishNameOptional = englishNameOptional;
        propertyChangeSupport.firePropertyChange("englishNameOptional", oldValue, this.englishNameOptional);
    }

    public boolean isGroupNumberError() {
        return groupNumberError;
    }

    public void setGroupNumberError(boolean groupNumberError) {
        boolean oldValue = this.groupNumberError;
        this.groupNumberError = groupNumberError;
        propertyChangeSupport.firePropertyChange("groupNumberError", oldValue, this.groupNumberError);
    }

    public boolean isGroupNumberOptional() {
        return groupNumberOptional;
    }

    public void setGroupNumberOptional(boolean groupNumberOptional) {
        boolean oldValue = this.groupNumberOptional;
        this.groupNumberOptional = groupNumberOptional;
        propertyChangeSupport.firePropertyChange("groupNumberOptional", oldValue, this.groupNumberOptional);
    }

    public boolean isHungarianNameError() {
        return hungarianNameError;
    }

    public void setHungarianNameError(boolean hungarianNameError) {
        boolean oldValue = this.hungarianNameError;
        this.hungarianNameError = hungarianNameError;
        propertyChangeSupport.firePropertyChange("hungarianNameError", oldValue, this.hungarianNameError);
    }

    public boolean isHungarianNameOptional() {
        return hungarianNameOptional;
    }

    public void setHungarianNameOptional(boolean hungarianNameOptional) {
        boolean oldValue = this.hungarianNameOptional;
        this.hungarianNameOptional = hungarianNameOptional;
        propertyChangeSupport.firePropertyChange("hungarianNameOptional", oldValue, this.hungarianNameOptional);
    }

    public boolean isSnomedCodeError() {
        return snomedCodeError;
    }

    public void setSnomedCodeError(boolean snomedCodeError) {
        boolean oldValue = this.snomedCodeError;
        this.snomedCodeError = snomedCodeError;
        propertyChangeSupport.firePropertyChange("snomedCodeError", oldValue, this.snomedCodeError);
    }

    public boolean isSnomedCodeOptional() {
        return snomedCodeOptional;
    }

    public void setSnomedCodeOptional(boolean snomedCodeOptional) {
        boolean oldValue = this.snomedCodeOptional;
        this.snomedCodeOptional = snomedCodeOptional;
        propertyChangeSupport.firePropertyChange("snomedCodeOptional", oldValue, this.snomedCodeOptional);
    }
}
