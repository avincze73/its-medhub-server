/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.dto;

import base.BaseDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vincze.attila
 */
public class RoleDTO extends BaseDTO {

    private String name;
    private String hungarianPublicAlias;
    private String englishPublicAlias;
    private String abbreviation;
    private String canHave;
    private String description;
    private String roleBoundaries;
    private List<RoleDTO> whoCanAssignThisRoleToUser;
    private List<RoleDTO> prerequisites;

    public RoleDTO(long id) {
        super(id);
        whoCanAssignThisRoleToUser = new ArrayList<RoleDTO>();
        prerequisites = new ArrayList<RoleDTO>();
    }

    public RoleDTO() {
        super();
        whoCanAssignThisRoleToUser = new ArrayList<RoleDTO>();
        prerequisites = new ArrayList<RoleDTO>();
    }

    public RoleDTO(String name, String hungarianPublicAlias,
            String englishPublicAlias, String abbreviation,
            String description, String roleBoundaries, String canHave) {
        super();
        this.name = name;
        this.hungarianPublicAlias = hungarianPublicAlias;
        this.englishPublicAlias = englishPublicAlias;
        this.abbreviation = abbreviation;
        this.canHave = canHave;
        this.description = description;
        this.roleBoundaries = roleBoundaries;
        whoCanAssignThisRoleToUser = new ArrayList<RoleDTO>();
        prerequisites = new ArrayList<RoleDTO>();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RoleDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public List<RoleDTO> getWhoCanAssignThisRoleToUser() {
        return whoCanAssignThisRoleToUser;
    }

    public void setWhoCanAssignThisRoleToUser(List<RoleDTO> whoCanAssignThisRoleToUser) {
        List<RoleDTO> oldValue = this.whoCanAssignThisRoleToUser;
        this.whoCanAssignThisRoleToUser = whoCanAssignThisRoleToUser;
        propertyChangeSupport.firePropertyChange("whoCanAssignThisRoleToUser", oldValue, this.whoCanAssignThisRoleToUser);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        String oldValue = this.description;
        this.description = description;
        propertyChangeSupport.firePropertyChange("description", oldValue, this.description);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", oldValue, this.name);
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        String oldValue = this.abbreviation;
        this.abbreviation = abbreviation;
        propertyChangeSupport.firePropertyChange("abbreviation", oldValue, this.abbreviation);
    }

    public String getEnglishPublicAlias() {
        return englishPublicAlias;
    }

    public void setEnglishPublicAlias(String englishPublicAlias) {
        String oldValue = this.englishPublicAlias;
        this.englishPublicAlias = englishPublicAlias;
        propertyChangeSupport.firePropertyChange("englishPublicAlias", oldValue, this.englishPublicAlias);
    }

    public String getHungarianPublicAlias() {
        return hungarianPublicAlias;
    }

    public void setHungarianPublicAlias(String hungarianPublicAlias) {
        String oldValue = this.hungarianPublicAlias;
        this.hungarianPublicAlias = hungarianPublicAlias;
        propertyChangeSupport.firePropertyChange("hungarianPublicAlias", oldValue, this.hungarianPublicAlias);
    }

    public String getRoleBoundaries() {
        return roleBoundaries;
    }

    public void setRoleBoundaries(String roleBoundaries) {
        String oldValue = this.roleBoundaries;
        this.roleBoundaries = roleBoundaries;
        propertyChangeSupport.firePropertyChange("roleBoundaries", oldValue, this.roleBoundaries);
    }

    public List<RoleDTO> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<RoleDTO> prerequisites) {
        List<RoleDTO> oldValue = this.prerequisites;
        this.prerequisites = prerequisites;
        propertyChangeSupport.firePropertyChange("prerequisites", oldValue, this.prerequisites);
    }

    public String getCanHave() {
        return canHave;
    }

    public void setCanHave(String canHave) {
        String oldValue = this.canHave;
        this.canHave = canHave;
        propertyChangeSupport.firePropertyChange("canHave", oldValue, this.canHave);
    }
}
