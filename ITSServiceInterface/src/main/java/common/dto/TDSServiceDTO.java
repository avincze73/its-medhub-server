/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common.dto;

import base.BaseDTO;
import java.util.ArrayList;
import java.util.List;
import usermodule.dto.RoleDTO;

/**
 *
 * @author vincze.attila
 */
public class TDSServiceDTO extends BaseDTO {

    private String name;
    private String description;
    private String tdsApplication;
    private List<RoleDTO> roleList;

    public TDSServiceDTO(long id) {
        super(id);
        roleList = new ArrayList<RoleDTO>();
    }

    public TDSServiceDTO() {
        super();
        roleList = new ArrayList<RoleDTO>();
    }

    public TDSServiceDTO(String name) {
        super();
        this.name = name;
        roleList = new ArrayList<RoleDTO>();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TDSServiceDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", oldValue, this.name);
    }

    public List<RoleDTO> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleDTO> roleList) {
        List<RoleDTO> oldValue = this.roleList;
        this.roleList = roleList;
        propertyChangeSupport.firePropertyChange("roleList", oldValue, this.roleList);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        String oldValue = this.description;
        this.description = description;
        propertyChangeSupport.firePropertyChange("description", oldValue, this.description);
    }

    public String getTdsApplication() {
        return tdsApplication;
    }

    public void setTdsApplication(String tdsApplication) {
        String oldValue = this.description;
        this.tdsApplication = tdsApplication;
        propertyChangeSupport.firePropertyChange("description", oldValue, this.description);
    }


}
