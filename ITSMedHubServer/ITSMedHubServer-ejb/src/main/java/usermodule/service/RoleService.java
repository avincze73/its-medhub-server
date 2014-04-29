/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import usermodule.assembler.UserAssembler;
import usermodule.dto.RoleDTO;
import usermodule.dto.TDSManagerRoleAssignmentDTO;
import usermodule.dto.TDSManagerRoleDTO;
import usermodule.dto.TDSRadiologistRoleDTO;
import usermodule.entity.ITSManagerRole;
import usermodule.entity.ITSManagerRoleAssignment;
import usermodule.entity.ITSRadiologistRole;
import usermodule.entity.ITSRole;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class RoleService implements RoleServiceRemote {

    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    public List<RoleDTO> findAll() {
        List<RoleDTO> dtos = new ArrayList<RoleDTO>();
        List<ITSRole> items = em.createNamedQuery("TDSRole.findAll", ITSRole.class).getResultList();
        for (ITSRole item : items) {
            dtos.add(UserAssembler.toDTO(item));
        }
        return dtos;
    }

    @Override
    public RoleDTO find(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public long save(RoleDTO dto) {
        ITSRole item = UserAssembler.toEntity(dto);
        long res = dto.getId();
        if (dto.getId() == 0) {
            em.persist(item);
            em.flush();
            res = item.getId();
        } else {
            em.merge(item);
        }
        return res;
    }

    @Override
    public void delete(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TDSRadiologistRoleDTO> getTDSRadiologistRoles() {
        List<TDSRadiologistRoleDTO> dtos = new ArrayList<TDSRadiologistRoleDTO>();
        List<ITSRadiologistRole> items = em.createQuery("SELECT t FROM TDSRadiologistRole t", ITSRadiologistRole.class).getResultList();
        for (ITSRadiologistRole item : items) {
            dtos.add(UserAssembler.toDTO(item));
        }
        return dtos;
    }

    @Override
    public List<TDSManagerRoleDTO> getTDSManagerRoles() {
        List<TDSManagerRoleDTO> dtos = new ArrayList<TDSManagerRoleDTO>();
        List<ITSManagerRole> items = em.createQuery("SELECT t FROM TDSManagerRole t", ITSManagerRole.class).getResultList();
        for (ITSManagerRole item : items) {
            dtos.add(UserAssembler.toDTO(item));
        }
        return dtos;
    }

    @Override
    public long saveTDSManagerRoleAssignment(TDSManagerRoleAssignmentDTO dto) {
        ITSManagerRoleAssignment item = UserAssembler.toEntity(dto);
        long res = dto.getId();
        if (dto.getId() == 0) {
            em.persist(item);
            em.flush();
            res = item.getId();
        } else {
            em.merge(item);
        }
        System.out.println(item.getItsManagerRole().getId());
        return res;
    }


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
