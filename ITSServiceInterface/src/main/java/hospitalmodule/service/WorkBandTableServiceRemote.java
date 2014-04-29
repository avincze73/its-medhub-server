/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmodule.service;

import hospitalmodule.dto.WorkBandTableDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface WorkBandTableServiceRemote {

    long save(WorkBandTableDTO item);

    void delete(long id);

    void save(long hospitalContractid, List<WorkBandTableDTO> dtos);

    List<WorkBandTableDTO> getByHospitalContract(long hospitalContractid);
}
