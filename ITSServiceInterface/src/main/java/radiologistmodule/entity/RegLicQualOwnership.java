/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package radiologistmodule.entity;

import masterdatamodule.entity.RegLicQual;
import radiologistmodule.entity.ITSRadiologist;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "RegLicQualOwnership")
@NamedQueries({
    @NamedQuery(name = "RegLicQualOwnership.findAll", query = "SELECT r FROM RegLicQualOwnership r"),
    @NamedQuery(name = "RegLicQualOwnership.findById", query = "SELECT r FROM RegLicQualOwnership r WHERE r.id = :id"),
    @NamedQuery(name = "RegLicQualOwnership.findByValidBegin", query = "SELECT r FROM RegLicQualOwnership r WHERE r.validBegin = :validBegin"),
    @NamedQuery(name = "RegLicQualOwnership.findByValidEnd", query = "SELECT r FROM RegLicQualOwnership r WHERE r.validEnd = :validEnd"),
    @NamedQuery(name = "RegLicQualOwnership.findByCertificateNumber", query = "SELECT r FROM RegLicQualOwnership r WHERE r.certificateNumber = :certificateNumber"),
    @NamedQuery(name = "RegLicQualOwnership.findByActive", query = "SELECT r FROM RegLicQualOwnership r WHERE r.active = :active")})
public class RegLicQualOwnership implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "validBegin")
    @Temporal(TemporalType.DATE)
    private Date validBegin;
    @Basic(optional = false)
    @Column(name = "validEnd")
    @Temporal(TemporalType.DATE)
    private Date validEnd;
    @Basic(optional = false)
    @Column(name = "certificateNumber")
    private String certificateNumber;
    @Basic(optional = false)
    @Column(name = "active")
    private boolean active;
    @JoinColumn(name = "regLicQualId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RegLicQual regLicQual;
    @JoinColumn(name = "tdsRadiologistId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ITSRadiologist tDSRadiologist;

    public RegLicQualOwnership() {
    }

    public RegLicQualOwnership(Long id) {
        this.id = id;
    }

    public RegLicQualOwnership(Long id, Date validBegin, Date validEnd, String certificateNumber, boolean active) {
        this.id = id;
        this.validBegin = validBegin;
        this.validEnd = validEnd;
        this.certificateNumber = certificateNumber;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getValidBegin() {
        return validBegin;
    }

    public void setValidBegin(Date validBegin) {
        this.validBegin = validBegin;
    }

    public Date getValidEnd() {
        return validEnd;
    }

    public void setValidEnd(Date validEnd) {
        this.validEnd = validEnd;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public RegLicQual getRegLicQual() {
        return regLicQual;
    }

    public void setRegLicQual(RegLicQual regLicQual) {
        this.regLicQual = regLicQual;
    }

    public ITSRadiologist getTDSRadiologist() {
        return tDSRadiologist;
    }

    public void setTDSRadiologist(ITSRadiologist tDSRadiologist) {
        this.tDSRadiologist = tDSRadiologist;
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
        if (!(object instanceof RegLicQualOwnership)) {
            return false;
        }
        RegLicQualOwnership other = (RegLicQualOwnership) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RegLicQualOwnership[id=" + id + "]";
    }

}
