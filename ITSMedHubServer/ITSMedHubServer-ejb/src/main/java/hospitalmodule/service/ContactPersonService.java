/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmodule.service;

import hospitalmodule.assembler.HospitalAssembler;
import hospitalmodule.dto.ContactPersonDTO;
import hospitalmodule.entity.ContactPerson;
import hospitalmodule.repository.ContactPersonRepository;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class ContactPersonService implements ContactPersonServiceRemote {

    @EJB(name = "repository")
    private ContactPersonRepository repository;

    @Override
    public long save(ContactPersonDTO item) {
        ContactPerson entity = HospitalAssembler.toEntity(item);
        if (entity.getId() == 0) {
            repository.create(entity);
        } else {
            repository.edit(entity);
        }
        return entity.getId();
    }

    @Override
    public void delete(long id) {
        ContactPerson entity = repository.find(id);
        repository.remove(entity);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
