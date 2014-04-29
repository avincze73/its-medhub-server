/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package usermodule.service;

import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import java.util.Date;
import usermodule.entity.HospitalStaff;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import usermodule.assembler.UserAssembler;
import usermodule.dto.HospitalStaffDTO;
import usermodule.entity.ITSUser;
import usermodule.repository.HospitalStaffRepository;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class HospitalStaffService implements HospitalStaffServiceRemote {
    @EJB(name = "repository")
    private HospitalStaffRepository repository;


    @Override
    public List<HospitalStaffDTO> findAll() throws TooManyResultsException, ZeroResultException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<HospitalStaffDTO> findByName(String name) throws TooManyResultsException, ZeroResultException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public HospitalStaffDTO find(long id) {
        HospitalStaff item = repository.find(id);
        return UserAssembler.toDTO(item);
    }

    @Override
    public long save(HospitalStaffDTO dto) {
        long res = dto.getId();
        HospitalStaff oldHospitalStaff;
        if (dto.getId() == 0) {
            oldHospitalStaff = new HospitalStaff();
            oldHospitalStaff.setItsUser(new ITSUser());
            dto.getUserInfo().setAddingDateTime(new Date());
            //oldHospitalStaff.getTDSUser().setAddingDateTime(new Date());
        } else {
            oldHospitalStaff = repository.find(dto.getId());
        }
        HospitalStaff item = UserAssembler.updateEntity(dto, oldHospitalStaff);
        if (dto.getId() == 0) {
            repository.create(item);
            res = item.getId();
        } else {
            repository.edit(item);
        }
        return res;
    }

    @Override
    public void delete(long id) {
        HospitalStaff item = repository.find(id);
        repository.remove(item);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
}
