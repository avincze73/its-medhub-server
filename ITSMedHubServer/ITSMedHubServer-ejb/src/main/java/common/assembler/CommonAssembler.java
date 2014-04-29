/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common.assembler;

import common.dto.TDSServiceDTO;
import java.util.ArrayList;
import systemmodule.entity.TDSService;
import usermodule.assembler.UserAssembler;
import usermodule.dto.RoleDTO;
import usermodule.entity.ITSRole;

/**
 *
 * @author vincze.attila
 */
public class CommonAssembler {

    public static TDSServiceDTO toDTO(TDSService entity) {
        TDSServiceDTO dto = new TDSServiceDTO(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setTdsApplication(entity.getTdsApplication());
        for (ITSRole role : entity.getTDSRoleCollection()) {
            dto.getRoleList().add(UserAssembler.toDTO(role));
        }
        return dto;
    }

    public static TDSService toEntity(TDSServiceDTO dto) {
        TDSService entity = new TDSService();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setTdsApplication(dto.getTdsApplication());
        entity.setTDSRoleCollection(new ArrayList<ITSRole>());
        for (RoleDTO role : dto.getRoleList()) {
            entity.getTDSRoleCollection().add(UserAssembler.toEntity(role));
        }
        return entity;
    }
}
