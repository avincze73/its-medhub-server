/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package casemodule.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "ReportTemplate")
@NamedQueries({
    @NamedQuery(name = "ReportTemplate.findAll", query = "SELECT r FROM ReportTemplate r"),
    @NamedQuery(name = "ReportTemplate.findById", query = "SELECT r FROM ReportTemplate r WHERE r.id = :id"),
    @NamedQuery(name = "ReportTemplate.findByName", query = "SELECT r FROM ReportTemplate r WHERE r.name = :name"),
    @NamedQuery(name = "ReportTemplate.findByDescription", query = "SELECT r FROM ReportTemplate r WHERE r.description = :description")})
public class ReportTemplate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    public ReportTemplate() {
    }

    public ReportTemplate(Long id) {
        this.id = id;
    }

    public ReportTemplate(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof ReportTemplate)) {
            return false;
        }
        ReportTemplate other = (ReportTemplate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ReportTemplate[id=" + id + "]";
    }

}
