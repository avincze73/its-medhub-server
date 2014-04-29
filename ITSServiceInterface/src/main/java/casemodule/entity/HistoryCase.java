/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.entity;

import casemodule.entity.TDSCase;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tds
 */
@Entity
@Table(name = "HistoryCase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoryCase.findAll", query = "SELECT h FROM HistoryCase h"),
    @NamedQuery(name = "HistoryCase.findById", query = "SELECT h FROM HistoryCase h WHERE h.id = :id")})
public class HistoryCase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    //@NotNull
    @Column(name = "id")
    private Long id;
    @Lob
    @Column(name = "finalizedReport")
    private byte[] finalizedReport;
    @JoinColumn(name = "imageReportId", referencedColumnName = "id")
    @ManyToOne
    private ImageReport imageReport;
    @JoinColumn(name = "caseId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TDSCase tDSCase;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "historyCase")
    private List<ReferralInfo> referralInfoList;
    @JoinColumn(name = "dicomPatientDataId", referencedColumnName = "id")
    @ManyToOne
    private DicomPatientData dicomPatientData;

    public HistoryCase() {
    }

    public HistoryCase(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFinalizedReport() {
        return finalizedReport;
    }

    public void setFinalizedReport(byte[] finalizedReport) {
        this.finalizedReport = finalizedReport;
    }

    public ImageReport getImageReport() {
        return imageReport;
    }

    public void setImageReport(ImageReport imageReport) {
        this.imageReport = imageReport;
    }

    public TDSCase gettDSCase() {
        return tDSCase;
    }

    public void settDSCase(TDSCase tDSCase) {
        this.tDSCase = tDSCase;
    }

    public List<ReferralInfo> getReferralInfoList() {
        return referralInfoList;
    }

    public void setReferralInfoList(List<ReferralInfo> referralInfoList) {
        this.referralInfoList = referralInfoList;
    }

    public DicomPatientData getDicomPatientData() {
        return dicomPatientData;
    }

    public void setDicomPatientData(DicomPatientData dicomPatientData) {
        this.dicomPatientData = dicomPatientData;
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
        if (!(object instanceof HistoryCase)) {
            return false;
        }
        HistoryCase other = (HistoryCase) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HistoryCase[ id=" + id + " ]";
    }
}
