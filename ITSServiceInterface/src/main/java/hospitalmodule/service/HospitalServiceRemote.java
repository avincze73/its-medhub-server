/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmodule.service;

import base.IRemoteService;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import hospitalmodule.dto.ContactPersonDTO;
import hospitalmodule.dto.HospitalContractDTO;
import hospitalmodule.dto.HospitalDTO;
import hospitalmodule.dto.OptionAssignmentDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author root
 */
@Remote
public interface HospitalServiceRemote extends IRemoteService<HospitalDTO> {

    List<ContactPersonDTO> getContactPersonListByHospital(long hospitalId);

    void activate(long hospitalId, boolean active);

    List<HospitalDTO> getHospitalList() throws TooManyResultsException, ZeroResultException;

    List<HospitalDTO> getHospitalList(String abbrevName) throws TooManyResultsException, ZeroResultException;

    List<HospitalDTO> getHospitalNameList();

    HospitalDTO getHospital(long id);

    HospitalDTO saveHospital(HospitalDTO dto);

    HospitalContractDTO getHospitalContract(long id);

    HospitalContractDTO saveHospitalContract(HospitalContractDTO dto);

    OptionAssignmentDTO saveOptionAssignment(OptionAssignmentDTO dto);

}
