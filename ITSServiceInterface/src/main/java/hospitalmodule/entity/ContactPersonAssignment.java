/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hospitalmodule.entity;

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
@Table(name = "ContactPersonAssignment")
@NamedQueries({
    @NamedQuery(name = "ContactPersonAssignment.findAll", query = "SELECT c FROM ContactPersonAssignment c"),
    @NamedQuery(name = "ContactPersonAssignment.findById", query = "SELECT c FROM ContactPersonAssignment c WHERE c.id = :id"),
    @NamedQuery(name = "ContactPersonAssignment.findByContactPosition", query = "SELECT c FROM ContactPersonAssignment c WHERE c.contactPosition = :contactPosition"),
    @NamedQuery(name = "ContactPersonAssignment.findByContactType", query = "SELECT c FROM ContactPersonAssignment c WHERE c.contactType = :contactType"),
    @NamedQuery(name = "ContactPersonAssignment.findByStarted", query = "SELECT c FROM ContactPersonAssignment c WHERE c.started = :started"),
    @NamedQuery(name = "ContactPersonAssignment.findByFinished", query = "SELECT c FROM ContactPersonAssignment c WHERE c.finished = :finished"),
    @NamedQuery(name = "ContactPersonAssignment.findByInactivation", query = "SELECT c FROM ContactPersonAssignment c WHERE c.inactivation = :inactivation")})
public class ContactPersonAssignment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "contactPosition")
    private String contactPosition;
    @Basic(optional = false)
    @Column(name = "contactType")
    private String contactType;
    @Basic(optional = false)
    @Column(name = "started")
    @Temporal(TemporalType.TIMESTAMP)
    private Date started;
    @Basic(optional = false)
    @Column(name = "finished")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finished;
    @Column(name = "inactivation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inactivation;
    @JoinColumn(name = "hospitalContractId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HospitalContract hospitalContract;
    @JoinColumn(name = "contactPersonId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ContactPerson contactPerson;

    public ContactPersonAssignment() {
    }

    public ContactPersonAssignment(Long id) {
        this.id = id;
    }

    public ContactPersonAssignment(Long id, String contactPosition, String contactType, Date started, Date finished) {
        this.id = id;
        this.contactPosition = contactPosition;
        this.contactType = contactType;
        this.started = started;
        this.finished = finished;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public Date getFinished() {
        return finished;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
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

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
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
        if (!(object instanceof ContactPersonAssignment)) {
            return false;
        }
        ContactPersonAssignment other = (ContactPersonAssignment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ContactPersonAssignment[id=" + id + "]";
    }

}
