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
public class CountryDTO extends BaseDTO {

    private String englishName;
    private String hungarianName;
    private CurrencyDTO currency;
    //error
    private boolean englishNameError;
    private boolean hungarianNameError;
    private boolean currencyError;
    //error
    private boolean englishNameOptional;
    private boolean hungarianNameOptional;
    private boolean currencyOptional;

    public CountryDTO() {
        this(0, null, null, null);
    }

    public CountryDTO(long id, String englishName, String hungarianName, CurrencyDTO currency) {
        super(id);
        this.englishName = englishName;
        this.hungarianName = hungarianName;
        this.currency = currency;
    }

    public CountryDTO(String englishName, String hungarianName, CurrencyDTO currency) {
        this(0, englishName, hungarianName, currency);
    }

    @Override
    public CountryDTO clone() throws CloneNotSupportedException {
        CountryDTO result = (CountryDTO) super.clone();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CountryDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public CurrencyDTO getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyDTO currency) {
        CurrencyDTO oldValue = this.currency;
        this.currency = currency;
        propertyChangeSupport.firePropertyChange("currency", oldValue, this.currency);
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

    public boolean isCurrencyError() {
        return currencyError;
    }

    public void setCurrencyError(boolean currencyError) {
        boolean oldValue = this.currencyError;
        this.currencyError = currencyError;
        propertyChangeSupport.firePropertyChange("currencyError", oldValue, this.currencyError);
    }

    public boolean isCurrencyOptional() {
        return currencyOptional;
    }

    public void setCurrencyOptional(boolean currencyOptional) {
        boolean oldValue = this.currencyOptional;
        this.currencyOptional = currencyOptional;
        propertyChangeSupport.firePropertyChange("currencyOptional", oldValue, this.currencyOptional);
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
