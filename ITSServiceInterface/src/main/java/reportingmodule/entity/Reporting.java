/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportingmodule.entity;

import masterdatamodule.entity.WayOfClosing;
import casemodule.entity.TDSCase;
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
@Table(name = "Reporting")
@NamedQueries({
    @NamedQuery(name = "Reporting.findAll", query = "SELECT r FROM Reporting r"),
    @NamedQuery(name = "Reporting.findById", query = "SELECT r FROM Reporting r WHERE r.id = :id"),
    @NamedQuery(name = "Reporting.findByAssigned", query = "SELECT r FROM Reporting r WHERE r.assigned = :assigned"),
    @NamedQuery(name = "Reporting.findByActive", query = "SELECT r FROM Reporting r WHERE r.active = :active"),
    @NamedQuery(name = "Reporting.findByDone", query = "SELECT r FROM Reporting r WHERE r.done = :done"),
    @NamedQuery(name = "Reporting.findByDoneConfirmed", query = "SELECT r FROM Reporting r WHERE r.doneConfirmed = :doneConfirmed"),
    @NamedQuery(name = "Reporting.findByUnfinishedText", query = "SELECT r FROM Reporting r WHERE r.unfinishedText = :unfinishedText"),
    @NamedQuery(name = "Reporting.findByFinishedAndSignedText", query = "SELECT r FROM Reporting r WHERE r.finishedAndSignedText = :finishedAndSignedText"),
    @NamedQuery(name = "Reporting.findByOpened", query = "SELECT r FROM Reporting r WHERE r.opened = :opened")})
public class Reporting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "assigned")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assigned;
    @Basic(optional = false)
    @Column(name = "active")
    private boolean active;
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
    @Column(name = "ready")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ready;
    @Column(name = "technicalDetails")
    private String technicalDetails;
    @Column(name = "medicalHistory")
    private String medicalHistory;
    @Column(name = "description")
    private String description;
    @Column(name = "conclusion")
    private String conclusion;
    @JoinColumn(name = "wayOfClosingId", referencedColumnName = "id")
    @ManyToOne
    private WayOfClosing wayOfClosing;
    @JoinColumn(name = "caseId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TDSCase tDSCase;
    @JoinColumn(name = "tdsRadiologistId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ITSRadiologist tDSRadiologist;

    public Reporting() {
    }

    public Reporting(Long id) {
        this.id = id;
    }

    public Reporting(Long id, Date assigned, boolean active) {
        this.id = id;
        this.assigned = assigned;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAssigned() {
        return assigned;
    }

    public void setAssigned(Date assigned) {
        this.assigned = assigned;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public Date getReady() {
        return ready;
    }

    public void setReady(Date ready) {
        this.ready = ready;
    }

    public String getTechnicalDetails() {
        return technicalDetails;
    }

    public void setTechnicalDetails(String technicalDetails) {
        this.technicalDetails = technicalDetails;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reporting)) {
            return false;
        }
        Reporting other = (Reporting) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Reporting[id=" + id + "]";
    }
}
