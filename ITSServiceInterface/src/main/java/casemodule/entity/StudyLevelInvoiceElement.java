/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package casemodule.entity;

import radiologistmodule.entity.CaseLevelInvoiceElement;
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
@Table(name = "StudyLevelInvoiceElement")
@NamedQueries({
    @NamedQuery(name = "StudyLevelInvoiceElement.findAll", query = "SELECT s FROM StudyLevelInvoiceElement s"),
    @NamedQuery(name = "StudyLevelInvoiceElement.findById", query = "SELECT s FROM StudyLevelInvoiceElement s WHERE s.id = :id"),
    @NamedQuery(name = "StudyLevelInvoiceElement.findByFellIntoAvailability", query = "SELECT s FROM StudyLevelInvoiceElement s WHERE s.fellIntoAvailability = :fellIntoAvailability")})
public class StudyLevelInvoiceElement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "fellIntoAvailability")
    private String fellIntoAvailability;
    @Basic(optional = false)
    @Column(name = "sumValue")
    private double sumValue;
    @JoinColumn(name = "studyId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TDSStudy tDSStudy;
    @JoinColumn(name = "seriesId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Series series;
    @JoinColumn(name = "caseLevelInvoiceElementId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CaseLevelInvoiceElement caseLevelInvoiceElement;

    public StudyLevelInvoiceElement() {
    }

    public StudyLevelInvoiceElement(Long id) {
        this.id = id;
    }

    public StudyLevelInvoiceElement(Long id, String fellIntoAvailability, double sumValue) {
        this.id = id;
        this.fellIntoAvailability = fellIntoAvailability;
        this.sumValue    = sumValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFellIntoAvailability() {
        return fellIntoAvailability;
    }

    public void setFellIntoAvailability(String fellIntoAvailability) {
        this.fellIntoAvailability = fellIntoAvailability;
    }

    public double getSumValue() {
        return sumValue;
    }

    public void setSumValue(double sumValue) {
        this.sumValue = sumValue;
    }

    public TDSStudy getTDSStudy() {
        return tDSStudy;
    }

    public void setTDSStudy(TDSStudy tDSStudy) {
        this.tDSStudy = tDSStudy;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public CaseLevelInvoiceElement getCaseLevelInvoiceElement() {
        return caseLevelInvoiceElement;
    }

    public void setCaseLevelInvoiceElement(CaseLevelInvoiceElement caseLevelInvoiceElement) {
        this.caseLevelInvoiceElement = caseLevelInvoiceElement;
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
        if (!(object instanceof StudyLevelInvoiceElement)) {
            return false;
        }
        StudyLevelInvoiceElement other = (StudyLevelInvoiceElement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generatedentity.StudyLevelInvoiceElement[id=" + id + "]";
    }

}
