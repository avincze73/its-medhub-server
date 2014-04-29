/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.entity;

import base.ITSEntity;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "SequenceCategory")
public class SequenceCategory extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "categorisation")
    private String categorisation;
    @Basic(optional = false)
    @Column(name = "bounds")
    private String bounds;
    @Basic(optional = false)
    @Column(name = "borders")
    private String borders;
    @Basic(optional = false)
    @Column(name = "multipliers")
    private String multipliers;

    public SequenceCategory() {
        this(null, null, null, null, null);
    }

    public SequenceCategory(Long id) {
        this(id, null, null, null, null);
    }

    public SequenceCategory(Long id, String categorisation, String bounds, String borders, String multipliers) {
        super(id);
        this.categorisation = categorisation;
        this.bounds = bounds;
        this.borders = borders;
        this.multipliers = multipliers;
    }

    public SequenceCategory(String categorisation, String bounds, 
            String borders, String multipliers) {
        this(null, categorisation, bounds, borders, multipliers);
    }


    public String getCategorisation() {
        return categorisation;
    }

    public void setCategorisation(String categorisation) {
        this.categorisation = categorisation;
    }

    public String getBounds() {
        return bounds;
    }

    public void setBounds(String bounds) {
        this.bounds = bounds;
    }

    public String getBorders() {
        return borders;
    }

    public void setBorders(String borders) {
        this.borders = borders;
    }

    public String getMultipliers() {
        return multipliers;
    }

    public void setMultipliers(String multipliers) {
        this.multipliers = multipliers;
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
        if (!(object instanceof SequenceCategory)) {
            return false;
        }
        SequenceCategory other = (SequenceCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SequenceCategory[id=" + id + "]";
    }
}
