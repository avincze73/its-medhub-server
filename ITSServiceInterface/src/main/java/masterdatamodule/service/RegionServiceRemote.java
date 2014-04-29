/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import base.IRemoteService;
import javax.ejb.Remote;
import masterdatamodule.dto.RegionDTO;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface RegionServiceRemote extends IRemoteService<RegionDTO>, MasterDataService<RegionDTO> {
}
