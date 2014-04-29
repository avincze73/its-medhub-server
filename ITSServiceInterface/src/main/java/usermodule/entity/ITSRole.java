/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.entity;

import base.ITSEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author vincze.attila
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "canHave", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("role")
@Table(name = "ITSRole")
public class ITSRole extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "hungarianPublicAlias")
    private String hungarianPublicAlias;
    @Column(name = "englishPublicAlias")
    private String englishPublicAlias;
    @Column(name = "abbreviation")
    private String abbreviation;
    @Column(name = "description")
    private String description;
    @Column(name = "roleBoundaries")
    private String roleBoundaries;
    @Column(name = "canHave", insertable = false, updatable = false)
    private String canHave;
    //
    @JoinTable(name = "RolePrerequisites", joinColumns = {
        @JoinColumn(name = "itsRoleId", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "prerequisiteId", referencedColumnName = "id")})
    @ManyToMany
    private List<ITSRole> prerequisiteList;
    //
    @JoinTable(name = "RoleWhoCanAssignThisRoleToUser", joinColumns = {
        @JoinColumn(name = "itsRoleId", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "whoCanAssignThisRoleToUserId", referencedColumnName = "id")})
    @ManyToMany
    private List<ITSRole> whoCanAssignList;

    public ITSRole() {
        super();
    }

    public ITSRole(Long id) {
        super(id);
    }

    public ITSRole(Long id, String name) {
        super(id);
        this.name = name;
    }

    public ITSRole(String name, String hungarianPublicAlias,
            String englishPublicAlias, String abbreviation, String canHave,
            String description, String roleBoundaries) {
        super();
        this.name = name;
        this.hungarianPublicAlias = hungarianPublicAlias;
        this.englishPublicAlias = englishPublicAlias;
        this.abbreviation = abbreviation;
        this.canHave = canHave;
        this.description = description;
        this.roleBoundaries = roleBoundaries;
        whoCanAssignList = new ArrayList<ITSRole>();
        prerequisiteList = new ArrayList<ITSRole>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHungarianPublicAlias() {
        return hungarianPublicAlias;
    }

    public void setHungarianPublicAlias(String hungarianPublicAlias) {
        this.hungarianPublicAlias = hungarianPublicAlias;
    }

    public String getEnglishPublicAlias() {
        return englishPublicAlias;
    }

    public void setEnglishPublicAlias(String englishPublicAlias) {
        this.englishPublicAlias = englishPublicAlias;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoleBoundaries() {
        return roleBoundaries;
    }

    public void setRoleBoundaries(String roleBoundaries) {
        this.roleBoundaries = roleBoundaries;
    }

    public String getCanHave() {
        return canHave;
    }

    public void setCanHave(String canHave) {
        this.canHave = canHave;
    }

    public List<ITSRole> getPrerequisiteList() {
        return prerequisiteList;
    }

    public void setPrerequisiteList(List<ITSRole> prerequisiteList) {
        this.prerequisiteList = prerequisiteList;
    }

    public List<ITSRole> getWhoCanAssignList() {
        return whoCanAssignList;
    }

    public void setWhoCanAssignList(List<ITSRole> whoCanAssignList) {
        this.whoCanAssignList = whoCanAssignList;
    }
}
