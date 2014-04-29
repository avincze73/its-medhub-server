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
public class CaseStatusDTO extends BaseDTO {

    private String englishName;
    private String hungarianName;
    private String tdsManagerSeesEng;
    private String tdsRadiologistSeesEng;
    private String hospitalSeesEng1;
    private String hospitalSeesEng2;
    //optional
    private boolean englishNameOptional;
    private boolean hungarianNameOptional;
    private boolean tdsManagerSeesEngOptional;
    private boolean tdsRadiologistSeesEngOptional;
    private boolean hospitalSeesEng1Optional;
    private boolean hospitalSeesEng2Optional;
    //error
    private boolean englishNameError;
    private boolean hungarianNameError;
    private boolean tdsManagerSeesEngError;
    private boolean tdsRadiologistSeesEngError;
    private boolean hospitalSeesEng1Error;
    private boolean hospitalSeesEng2Error;

    public CaseStatusDTO() {
        this(0, null, null, null, null, null, null);
    }

    public CaseStatusDTO(String englishName, String hungarianName) {
        this(0, englishName, hungarianName, null, null, null, null);
    }

    public CaseStatusDTO(long id, String englishName, String hungarianName) {
        this(0, englishName, hungarianName, null, null, null, null);
    }

    public CaseStatusDTO(String englishName, String hungarianName, String tdsManagerSeesEng,
            String tdsRadiologistSeesEng, String hospitalSeesEng1, String hospitalSeesEng2) {
        this(0, englishName, hungarianName, tdsManagerSeesEng, tdsRadiologistSeesEng, hospitalSeesEng1, hospitalSeesEng2);
    }

    public CaseStatusDTO(long id, String englishName, String hungarianName,
            String tdsManagerSeesEng, String tdsRadiologistSeesEng,
            String hospitalSeesEng1, String hospitalSeesEng2) {
        super(id);
        this.englishName = englishName;
        this.hungarianName = hungarianName;
        this.tdsManagerSeesEng = tdsManagerSeesEng;
        this.tdsRadiologistSeesEng = tdsRadiologistSeesEng;
        this.hospitalSeesEng1 = hospitalSeesEng1;
        this.hospitalSeesEng2 = hospitalSeesEng2;
        this.tdsManagerSeesEngOptional = true;
        this.tdsRadiologistSeesEngOptional = true;
        this.hospitalSeesEng1Optional = true;
        this.hospitalSeesEng2Optional = true;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CaseStatusDTO)) {
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

    public String getHospitalSeesEng1() {
        return hospitalSeesEng1;
    }

    public void setHospitalSeesEng1(String hospitalSeesEng1) {
        String oldValue = this.hospitalSeesEng1;
        this.hospitalSeesEng1 = hospitalSeesEng1;
        propertyChangeSupport.firePropertyChange("hospitalSeesEng1", oldValue, this.hospitalSeesEng1);
    }

    public String getHospitalSeesEng2() {
        return hospitalSeesEng2;
    }

    public void setHospitalSeesEng2(String hospitalSeesEng2) {
        String oldValue = this.hospitalSeesEng2;
        this.hospitalSeesEng2 = hospitalSeesEng2;
        propertyChangeSupport.firePropertyChange("hospitalSeesEng2", oldValue, this.hospitalSeesEng2);
    }

    public String getTdsManagerSeesEng() {
        return tdsManagerSeesEng;
    }

    public void setTdsManagerSeesEng(String tdsManagerSeesEng) {
        String oldValue = this.tdsManagerSeesEng;
        this.tdsManagerSeesEng = tdsManagerSeesEng;
        propertyChangeSupport.firePropertyChange("tdsManagerSeesEng", oldValue, this.tdsManagerSeesEng);
    }

    public String getTdsRadiologistSeesEng() {
        return tdsRadiologistSeesEng;
    }

    public void setTdsRadiologistSeesEng(String tdsRadiologistSeesEng) {
        String oldValue = this.tdsRadiologistSeesEng;
        this.tdsRadiologistSeesEng = tdsRadiologistSeesEng;
        propertyChangeSupport.firePropertyChange("tdsRadiologistSeesEng", oldValue, this.tdsRadiologistSeesEng);
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

    public boolean isHospitalSeesEng1Error() {
        return hospitalSeesEng1Error;
    }

    public void setHospitalSeesEng1Error(boolean hospitalSeesEng1Error) {
        boolean oldValue = this.hospitalSeesEng1Error;
        this.hospitalSeesEng1Error = hospitalSeesEng1Error;
        propertyChangeSupport.firePropertyChange("hospitalSeesEng1Error", oldValue, this.hospitalSeesEng1Error);
    }

    public boolean isHospitalSeesEng1Optional() {
        return hospitalSeesEng1Optional;
    }

    public void setHospitalSeesEng1Optional(boolean hospitalSeesEng1Optional) {
        boolean oldValue = this.hospitalSeesEng1Optional;
        this.hospitalSeesEng1Optional = hospitalSeesEng1Optional;
        propertyChangeSupport.firePropertyChange("hospitalSeesEng1Optional", oldValue, this.hospitalSeesEng1Optional);
    }

    public boolean isHospitalSeesEng2Error() {
        return hospitalSeesEng2Error;
    }

    public void setHospitalSeesEng2Error(boolean hospitalSeesEng2Error) {
        boolean oldValue = this.hospitalSeesEng2Error;
        this.hospitalSeesEng2Error = hospitalSeesEng2Error;
        propertyChangeSupport.firePropertyChange("hospitalSeesEng2Error", oldValue, this.hospitalSeesEng2Error);
    }

    public boolean isHospitalSeesEng2Optional() {
        return hospitalSeesEng2Optional;
    }

    public void setHospitalSeesEng2Optional(boolean hospitalSeesEng2Optional) {
        boolean oldValue = this.hospitalSeesEng2Optional;
        this.hospitalSeesEng2Optional = hospitalSeesEng2Optional;
        propertyChangeSupport.firePropertyChange("hospitalSeesEng2Optional", oldValue, this.hospitalSeesEng2Optional);
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

    public boolean isTdsManagerSeesEngError() {
        return tdsManagerSeesEngError;
    }

    public void setTdsManagerSeesEngError(boolean tdsManagerSeesEngError) {
        boolean oldValue = this.hungarianNameOptional;
        this.tdsManagerSeesEngError = tdsManagerSeesEngError;
        propertyChangeSupport.firePropertyChange("hungarianNameOptional", oldValue, this.hungarianNameOptional);
    }

    public boolean isTdsManagerSeesEngOptional() {
        return tdsManagerSeesEngOptional;
    }

    public void setTdsManagerSeesEngOptional(boolean tdsManagerSeesEngOptional) {
        boolean oldValue = this.tdsManagerSeesEngOptional;
        this.tdsManagerSeesEngOptional = tdsManagerSeesEngOptional;
        propertyChangeSupport.firePropertyChange("tdsManagerSeesEngOptional", oldValue, this.tdsManagerSeesEngOptional);
    }

    public boolean isTdsRadiologistSeesEngError() {
        return tdsRadiologistSeesEngError;
    }

    public void setTdsRadiologistSeesEngError(boolean tdsRadiologistSeesEngError) {
        boolean oldValue = this.tdsRadiologistSeesEngError;
        this.tdsRadiologistSeesEngError = tdsRadiologistSeesEngError;
        propertyChangeSupport.firePropertyChange("tdsRadiologistSeesEngError", oldValue, this.tdsRadiologistSeesEngError);
    }

    public boolean isTdsRadiologistSeesEngOptional() {
        return tdsRadiologistSeesEngOptional;
    }

    public void setTdsRadiologistSeesEngOptional(boolean tdsRadiologistSeesEngOptional) {
        boolean oldValue = this.tdsRadiologistSeesEngOptional;
        this.tdsRadiologistSeesEngOptional = tdsRadiologistSeesEngOptional;
        propertyChangeSupport.firePropertyChange("tdsRadiologistSeesEngOptional", oldValue, this.tdsRadiologistSeesEngOptional);
    }

    @Override
    public CaseStatusDTO clone() throws CloneNotSupportedException {
        CaseStatusDTO result = (CaseStatusDTO) super.clone();
        return result;
    }
}
