/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.entity;

import base.ITSEntity;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import usermodule.entity.HospitalStaff;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "ITSLanguage")
public class ITSLanguage extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "englishName")
    @Size(min=1, message="*")
    @NotNull(message="*")
    private String englishName;
    @Basic(optional = false)
    @Column(name = "hungarianName")
    @Size(min=1, message="*")
    @NotNull(message="*")
    private String hungarianName;
    @JoinTable(name = "HospitalStaffLanguages", joinColumns = {
        @JoinColumn(name = "itsLanguageId", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "hospitalStaffId", referencedColumnName = "id")})
    @ManyToMany
    private List<HospitalStaff> hospitalStaffList;

    public ITSLanguage() {
        this(null, null, null);
    }

    public ITSLanguage(Long id) {
        this(id, null, null);
    }

    public ITSLanguage(Long id, String englishName, String hungarianName) {
        super(id);
        this.englishName = englishName;
        this.hungarianName = hungarianName;
    }

    public ITSLanguage(String englishName, String hungarianName) {
        this(null, englishName, hungarianName);
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

    @Override
    public ITSLanguage clone() throws CloneNotSupportedException {
        ITSLanguage result = (ITSLanguage) super.clone();
        return result;
    }
    public List<HospitalStaff> getHospitalStaffList() {
        return hospitalStaffList;
    }

    public void setHospitalStaffList(List<HospitalStaff> hospitalStaffList) {
        this.hospitalStaffList = hospitalStaffList;
    }
}
