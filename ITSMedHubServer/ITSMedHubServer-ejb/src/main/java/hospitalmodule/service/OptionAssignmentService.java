/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hospitalmodule.service;

import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import hospitalmodule.assembler.HospitalAssembler;
import hospitalmodule.dto.OptionAssignmentDTO;
import hospitalmodule.entity.OptionAssignment;
import hospitalmodule.repository.OptionAssignmentRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class OptionAssignmentService implements OptionAssignmentServiceRemote {
    @EJB(name = "repository")
    private OptionAssignmentRepository repository;

    @Override
    public List<OptionAssignmentDTO> findAll() throws TooManyResultsException, ZeroResultException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<OptionAssignmentDTO> findByName(String name) throws TooManyResultsException, ZeroResultException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public OptionAssignmentDTO find(long id) {
        OptionAssignment item = repository.find(id);
        return HospitalAssembler.toDTO(item);
    }

    @Override
    public long save(OptionAssignmentDTO dto) {
        OptionAssignment item = HospitalAssembler.toEntity(dto);
        long res = dto.getId();
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
        OptionAssignment item = repository.find(id);
        repository.remove(item);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
}
