/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.service;

import java.util.List;
import javax.ejb.Remote;
import usermodule.dto.RoleDTO;
import usermodule.dto.TDSManagerRoleAssignmentDTO;
import usermodule.dto.TDSManagerRoleDTO;
import usermodule.dto.TDSRadiologistRoleDTO;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface RoleServiceRemote {

    List<RoleDTO> findAll();

    RoleDTO find(long id);

    long save(RoleDTO dto);

    void delete(long id);

    List<TDSRadiologistRoleDTO> getTDSRadiologistRoles();

    List<TDSManagerRoleDTO> getTDSManagerRoles();

    long saveTDSManagerRoleAssignment(TDSManagerRoleAssignmentDTO dto);
}
