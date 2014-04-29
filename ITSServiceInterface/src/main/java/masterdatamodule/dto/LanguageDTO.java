/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.dto;

import base.BaseDTO;

/**
 *
 * @author vincze.attila
 */
public class LanguageDTO extends BaseDTO {

    private String englishName;
    private String hungarianName;
    //error
    private boolean englishNameError;
    private boolean hungarianNameError;
    //optional
    private boolean englishNameOptional;
    private boolean hungarianNameOptional;

    public LanguageDTO() {
        this(0, null, null);
    }

    public LanguageDTO(String englishName, String hungarianName) {
        this(0, englishName, hungarianName);
    }

    public LanguageDTO(long id, String englishName, String hungarianName) {
        super(id);
        this.englishName = englishName;
        this.hungarianName = hungarianName;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LanguageDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public LanguageDTO clone() throws CloneNotSupportedException {
        LanguageDTO result = (LanguageDTO) super.clone();
        return result;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        String oldValue = this.englishName;
        this.englishName = englishName;
        propertyChangeSupport.firePropertyChange("englishName", oldValue, this.englishName);
    }

    public String getHungarianName() {
        return hungarianName;
    }

    public void setHungarianName(String hungarianName) {
        String oldValue = this.hungarianName;
        this.hungarianName = hungarianName;
        propertyChangeSupport.firePropertyChange("hungarianName", oldValue, this.hungarianName);
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
}
