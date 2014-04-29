/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmodule.service;

import base.IRemoteService;
import hospitalmodule.dto.ContactPersonAssignmentDTO;
import hospitalmodule.dto.ContactPersonDTO;
import hospitalmodule.dto.HospitalContractDTO;
import javax.ejb.Remote;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface HospitalContractServiceRemote extends IRemoteService<HospitalContractDTO> {

    long saveContactPersonAssignment(ContactPersonAssignmentDTO dto);
    long saveContactPerson(ContactPersonDTO dto);
}
