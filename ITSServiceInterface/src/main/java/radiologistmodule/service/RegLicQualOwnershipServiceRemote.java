/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package radiologistmodule.service;

import base.IRemoteService;
import java.util.List;
import javax.ejb.Remote;
import radiologistmodule.dto.RegLicQualOwnershipDTO;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface RegLicQualOwnershipServiceRemote extends IRemoteService<RegLicQualOwnershipDTO>{
    List<RegLicQualOwnershipDTO> findByRadiologist(int radiologistId);
}
