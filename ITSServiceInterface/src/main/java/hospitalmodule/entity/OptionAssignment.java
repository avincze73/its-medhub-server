/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hospitalmodule.entity;

import hospitalmodule.entity.HospitalContract;
import masterdatamodule.entity.HospContrOption;
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
@Table(name = "OptionAssignment")
@NamedQueries({
    @NamedQuery(name = "OptionAssignment.findAll", query = "SELECT o FROM OptionAssignment o"),
    @NamedQuery(name = "OptionAssignment.findById", query = "SELECT o FROM OptionAssignment o WHERE o.id = :id"),
    @NamedQuery(name = "OptionAssignment.findByValidStart", query = "SELECT o FROM OptionAssignment o WHERE o.validStart = :validStart"),
    @NamedQuery(name = "OptionAssignment.findByInactivation", query = "SELECT o FROM OptionAssignment o WHERE o.inactivation = :inactivation")})
public class OptionAssignment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "validStart")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validStart;
    @Column(name = "inactivation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inactivation;
    @JoinColumn(name = "hospitalContractId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HospitalContract hospitalContract;
    @JoinColumn(name = "hospContrOptionId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HospContrOption hospContrOption;

    public OptionAssignment() {
    }

    public OptionAssignment(Long id) {
        this.id = id;
    }

    public OptionAssignment(Long id, Date validStart) {
        this.id = id;
        this.validStart = validStart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getValidStart() {
        return validStart;
    }

    public void setValidStart(Date validStart) {
        this.validStart = validStart;
    }

    public Date getInactivation() {
        return inactivation;
    }

    public void setInactivation(Date inactivation) {
        this.inactivation = inactivation;
    }

    public HospitalContract getHospitalContract() {
        return hospitalContract;
    }

    public void setHospitalContract(HospitalContract hospitalContract) {
        this.hospitalContract = hospitalContract;
    }

    public HospContrOption getHospContrOption() {
        return hospContrOption;
    }

    public void setHospContrOption(HospContrOption hospContrOption) {
        this.hospContrOption = hospContrOption;
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
        if (!(object instanceof OptionAssignment)) {
            return false;
        }
        OptionAssignment other = (OptionAssignment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.OptionAssignment[id=" + id + "]";
    }

}
