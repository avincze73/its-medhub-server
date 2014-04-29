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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "Scenario")
public class Scenario extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "englishName")
    @Size(min = 1, message = "*")
    @NotNull(message = "*")
    private String englishName;
    @Basic(optional = false)
    @Column(name = "hungarianName")
    @Size(min = 1, message = "*")
    @NotNull(message = "*")
    private String hungarianName;
    @Basic(optional = false)
    @Column(name = "radiologistStarts")
    private boolean radiologistStarts;
    @Basic(optional = false)
    @Column(name = "category")
    private String category;
    @Basic(optional = false)
    @Column(name = "behaviour")
    private int behaviour;

    public Scenario() {
        this(null, null, null, false, null, 0);
    }

    public Scenario(Long id) {
        this(id, null, null, false, null, 0);
    }

    public Scenario(Long id, String englishName, String hungarianName, boolean radiologistStarts, String category, int behaviour) {
        super(id);
        this.englishName = englishName;
        this.hungarianName = hungarianName;
        this.radiologistStarts = radiologistStarts;
        this.category = category;
        this.behaviour = behaviour;
    }

    public Scenario(String englishName, String hungarianName, boolean radiologistStarts, String category, int behaviour) {
        this(null, englishName, hungarianName, radiologistStarts, category, behaviour);
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

    public boolean getRadiologistStarts() {
        return radiologistStarts;
    }

    public void setRadiologistStarts(boolean radiologistStarts) {
        this.radiologistStarts = radiologistStarts;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(int behaviour) {
        this.behaviour = behaviour;
    }

    @Override
    public Scenario clone() throws CloneNotSupportedException {
        Scenario result = (Scenario) super.clone();
        return result;
    }
}
