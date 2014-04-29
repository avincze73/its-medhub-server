/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package radiologistmodule.entity;

import casemodule.entity.TDSCase;
import masterdatamodule.entity.WayOfClosing;
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
@Table(name = "WorkScrutiny")
@NamedQueries({
    @NamedQuery(name = "WorkScrutiny.findAll", query = "SELECT w FROM WorkScrutiny w"),
    @NamedQuery(name = "WorkScrutiny.findById", query = "SELECT w FROM WorkScrutiny w WHERE w.id = :id"),
    @NamedQuery(name = "WorkScrutiny.findByRequested", query = "SELECT w FROM WorkScrutiny w WHERE w.requested = :requested"),
    @NamedQuery(name = "WorkScrutiny.findByType", query = "SELECT w FROM WorkScrutiny w WHERE w.type = :type"),
    @NamedQuery(name = "WorkScrutiny.findByAccepted", query = "SELECT w FROM WorkScrutiny w WHERE w.accepted = :accepted"),
    @NamedQuery(name = "WorkScrutiny.findByConfirmed", query = "SELECT w FROM WorkScrutiny w WHERE w.confirmed = :confirmed"),
    @NamedQuery(name = "WorkScrutiny.findByOutcome", query = "SELECT w FROM WorkScrutiny w WHERE w.outcome = :outcome"),
    @NamedQuery(name = "WorkScrutiny.findByUnfinishedText", query = "SELECT w FROM WorkScrutiny w WHERE w.unfinishedText = :unfinishedText"),
    @NamedQuery(name = "WorkScrutiny.findByFinishedAndSignedText", query = "SELECT w FROM WorkScrutiny w WHERE w.finishedAndSignedText = :finishedAndSignedText"),
    @NamedQuery(name = "WorkScrutiny.findByOpened", query = "SELECT w FROM WorkScrutiny w WHERE w.opened = :opened")})
public class WorkScrutiny implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "requested")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requested;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Column(name = "accepted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accepted;
    @Column(name = "confirmed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date confirmed;
    @Column(name = "outcome")
    private String outcome;
    @Column(name = "unfinishedText")
    private String unfinishedText;
    @Column(name = "finishedAndSignedText")
    private String finishedAndSignedText;
    @Column(name = "opened")
    private Boolean opened;
    @JoinColumn(name = "wayOfClosingId", referencedColumnName = "id")
    @ManyToOne
    private WayOfClosing wayOfClosing;
    @JoinColumn(name = "caseId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TDSCase tDSCase;
    @JoinColumn(name = "tdsRadiologistId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ITSRadiologist tDSRadiologist;

    public WorkScrutiny() {
    }

    public WorkScrutiny(Long id) {
        this.id = id;
    }

    public WorkScrutiny(Long id, Date requested, String type) {
        this.id = id;
        this.requested = requested;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRequested() {
        return requested;
    }

    public void setRequested(Date requested) {
        this.requested = requested;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getAccepted() {
        return accepted;
    }

    public void setAccepted(Date accepted) {
        this.accepted = accepted;
    }

    public Date getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Date confirmed) {
        this.confirmed = confirmed;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getUnfinishedText() {
        return unfinishedText;
    }

    public void setUnfinishedText(String unfinishedText) {
        this.unfinishedText = unfinishedText;
    }

    public String getFinishedAndSignedText() {
        return finishedAndSignedText;
    }

    public void setFinishedAndSignedText(String finishedAndSignedText) {
        this.finishedAndSignedText = finishedAndSignedText;
    }

    public Boolean getOpened() {
        return opened;
    }

    public void setOpened(Boolean opened) {
        this.opened = opened;
    }

    public WayOfClosing getWayOfClosing() {
        return wayOfClosing;
    }

    public void setWayOfClosing(WayOfClosing wayOfClosing) {
        this.wayOfClosing = wayOfClosing;
    }

    public TDSCase getTDSCase() {
        return tDSCase;
    }

    public void setTDSCase(TDSCase tDSCase) {
        this.tDSCase = tDSCase;
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
        if (!(object instanceof WorkScrutiny)) {
            return false;
        }
        WorkScrutiny other = (WorkScrutiny) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.WorkScrutiny[id=" + id + "]";
    }

}
