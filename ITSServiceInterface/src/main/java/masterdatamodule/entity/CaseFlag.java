/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package masterdatamodule.entity;

import casemodule.entity.FlagAssignment;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "CaseFlag")
@NamedQueries({
    @NamedQuery(name = "CaseFlag.findAll", query = "SELECT c FROM CaseFlag c"),
    @NamedQuery(name = "CaseFlag.findById", query = "SELECT c FROM CaseFlag c WHERE c.id = :id"),
    @NamedQuery(name = "CaseFlag.findByEnglishName", query = "SELECT c FROM CaseFlag c WHERE c.englishName = :englishName"),
    @NamedQuery(name = "CaseFlag.findByHungarianName", query = "SELECT c FROM CaseFlag c WHERE c.hungarianName = :hungarianName")})
public class CaseFlag implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "englishName")
    private String englishName;
    @Basic(optional = false)
    @Column(name = "hungarianName")
    private String hungarianName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caseFlag")
    private Collection<FlagAssignment> flagAssignmentCollection;

    public CaseFlag() {
    }

    public CaseFlag(Long id) {
        this.id = id;
    }

    public CaseFlag(Long id, String englishName, String hungarianName) {
        this.id = id;
        this.englishName = englishName;
        this.hungarianName = hungarianName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getHungarianName() {
        return hungarianName;
    }

    public void setHungarianName(String hungarianName) {
        this.hungarianName = hungarianName;
    }

    public Collection<FlagAssignment> getFlagAssignmentCollection() {
        return flagAssignmentCollection;
    }

    public void setFlagAssignmentCollection(Collection<FlagAssignment> flagAssignmentCollection) {
        this.flagAssignmentCollection = flagAssignmentCollection;
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
        if (!(object instanceof CaseFlag)) {
            return false;
        }
        CaseFlag other = (CaseFlag) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CaseFlag[id=" + id + "]";
    }

}
