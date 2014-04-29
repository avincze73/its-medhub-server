/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.dto;

import base.BaseDTO;
import masterdatamodule.dto.CountryDTO;

/**
 *
 * @author vincze.attila
 */
public class CompanyDTO extends BaseDTO {

    private String name;
    private CountryDTO country;
    private String address;
    private String taxAuthorityNumber;
    private String accountNumber;

    public CompanyDTO(long id) {
        super(id);
        //country = new CountryDTO();
    }

    public CompanyDTO() {
        super();
        //country = new CountryDTO();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CompanyDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        String oldValue = this.accountNumber;
        this.accountNumber = accountNumber;
        propertyChangeSupport.firePropertyChange("accountNumber", oldValue, this.accountNumber);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        String oldValue = this.address;
        this.address = address;
        propertyChangeSupport.firePropertyChange("address", oldValue, this.address);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", oldValue, this.name);
    }

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        CountryDTO oldValue = this.country;
        this.country = country;
        propertyChangeSupport.firePropertyChange("country", oldValue, this.country);
    }

    public String getTaxAuthorityNumber() {
        return taxAuthorityNumber;
    }

    public void setTaxAuthorityNumber(String taxAuthorityNumber) {
        String oldValue = this.taxAuthorityNumber;
        this.taxAuthorityNumber = taxAuthorityNumber;
        propertyChangeSupport.firePropertyChange("taxAuthorityNumber", oldValue, this.taxAuthorityNumber);
    }
}
