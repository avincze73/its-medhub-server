/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.service;

import java.util.List;
import javax.ejb.Remote;
import radiologistmodule.dto.RadAvailabilityDTO;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface RadAvailabilityServiceRemote {

    RadAvailabilityDTO find(long id);

    long save(RadAvailabilityDTO dto);

    void delete(long id);

    List<RadAvailabilityDTO> findByRadiologist(int radiologistId);
}
