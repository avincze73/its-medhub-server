/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package radiologistmodule.entity;

import masterdatamodule.entity.Country;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "Company")
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
    @NamedQuery(name = "Company.findById", query = "SELECT c FROM Company c WHERE c.id = :id"),
    @NamedQuery(name = "Company.findByName", query = "SELECT c FROM Company c WHERE c.name = :name"),
    @NamedQuery(name = "Company.findByAddress", query = "SELECT c FROM Company c WHERE c.address = :address"),
    @NamedQuery(name = "Company.findByTaxAuthorityNumber", query = "SELECT c FROM Company c WHERE c.taxAuthorityNumber = :taxAuthorityNumber"),
    @NamedQuery(name = "Company.findByAccountNumber", query = "SELECT c FROM Company c WHERE c.accountNumber = :accountNumber")})
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @Column(name = "taxAuthorityNumber")
    private String taxAuthorityNumber;
    @Basic(optional = false)
    @Column(name = "accountNumber")
    private String accountNumber;
    @JoinColumn(name = "countryId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Country country;

    public Company() {
    }

    public Company(Long id) {
        this.id = id;
    }

    public Company(Long id, String name, String address, String taxAuthorityNumber, String accountNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.taxAuthorityNumber = taxAuthorityNumber;
        this.accountNumber = accountNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTaxAuthorityNumber() {
        return taxAuthorityNumber;
    }

    public void setTaxAuthorityNumber(String taxAuthorityNumber) {
        this.taxAuthorityNumber = taxAuthorityNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Company[id=" + id + "]";
    }

}
