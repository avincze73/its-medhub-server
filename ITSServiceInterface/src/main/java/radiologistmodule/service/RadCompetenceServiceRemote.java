/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package radiologistmodule.service;

import base.IRemoteService;
import java.util.List;
import javax.ejb.Remote;
import radiologistmodule.dto.RadCompetenceDTO;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface RadCompetenceServiceRemote extends IRemoteService<RadCompetenceDTO> {
    List<RadCompetenceDTO> findByRadiologist(int radiologistId);
}
