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
@Table(name = "Consultation")
@NamedQueries({
    @NamedQuery(name = "Consultation.findAll", query = "SELECT c FROM Consultation c"),
    @NamedQuery(name = "Consultation.findById", query = "SELECT c FROM Consultation c WHERE c.id = :id"),
    @NamedQuery(name = "Consultation.findByRequested", query = "SELECT c FROM Consultation c WHERE c.requested = :requested"),
    @NamedQuery(name = "Consultation.findByRequestText", query = "SELECT c FROM Consultation c WHERE c.requestText = :requestText"),
    @NamedQuery(name = "Consultation.findByAccepted", query = "SELECT c FROM Consultation c WHERE c.accepted = :accepted"),
    @NamedQuery(name = "Consultation.findByDone", query = "SELECT c FROM Consultation c WHERE c.done = :done"),
    @NamedQuery(name = "Consultation.findByDoneConfirmed", query = "SELECT c FROM Consultation c WHERE c.doneConfirmed = :doneConfirmed"),
    @NamedQuery(name = "Consultation.findByUnfinishedText", query = "SELECT c FROM Consultation c WHERE c.unfinishedText = :unfinishedText"),
    @NamedQuery(name = "Consultation.findByFinishedAndSignedText", query = "SELECT c FROM Consultation c WHERE c.finishedAndSignedText = :finishedAndSignedText"),
    @NamedQuery(name = "Consultation.findByOpened", query = "SELECT c FROM Consultation c WHERE c.opened = :opened")})
public class Consultation implements Serializable {
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
    @Column(name = "requestText")
    private String requestText;
    @Column(name = "accepted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accepted;
    @Column(name = "done")
    @Temporal(TemporalType.TIMESTAMP)
    private Date done;
    @Column(name = "doneConfirmed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date doneConfirmed;
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

    public Consultation() {
    }

    public Consultation(Long id) {
        this.id = id;
    }

    public Consultation(Long id, Date requested, String requestText) {
        this.id = id;
        this.requested = requested;
        this.requestText = requestText;
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

    public String getRequestText() {
        return requestText;
    }

    public void setRequestText(String requestText) {
        this.requestText = requestText;
    }

    public Date getAccepted() {
        return accepted;
    }

    public void setAccepted(Date accepted) {
        this.accepted = accepted;
    }

    public Date getDone() {
        return done;
    }

    public void setDone(Date done) {
        this.done = done;
    }

    public Date getDoneConfirmed() {
        return doneConfirmed;
    }

    public void setDoneConfirmed(Date doneConfirmed) {
        this.doneConfirmed = doneConfirmed;
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
        if (!(object instanceof Consultation)) {
            return false;
        }
        Consultation other = (Consultation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Consultation[id=" + id + "]";
    }

}
