/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.entity;

import base.ITSEntity;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "Modality")
public class Modality extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "name")
    @Size(min = 1, message = "*")
    @NotNull(message = "*")
    private String name;

    public Modality() {
        this(null, null);
    }

    public Modality(Long id) {
        this(id, null);
    }

    public Modality(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Modality(String name) {
        this(null, name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Modality clone() throws CloneNotSupportedException {
        Modality result = (Modality) super.clone();
        return result;
    }
//    public Collection<Series> getSeriesCollection() {
//        return seriesCollection;
//    }
//
//    public void setSeriesCollection(Collection<Series> seriesCollection) {
//        this.seriesCollection = seriesCollection;
//    }
//
//    public Collection<WorkBandTable> getWorkBandTableCollection() {
//        return workBandTableCollection;
//    }
//
//    public void setWorkBandTableCollection(Collection<WorkBandTable> workBandTableCollection) {
//        this.workBandTableCollection = workBandTableCollection;
//    }
//
//    public Collection<RadCompetence> getRadCompetenceCollection() {
//        return radCompetenceCollection;
//    }
//
//    public void setRadCompetenceCollection(Collection<RadCompetence> radCompetenceCollection) {
//        this.radCompetenceCollection = radCompetenceCollection;
//    }
}
