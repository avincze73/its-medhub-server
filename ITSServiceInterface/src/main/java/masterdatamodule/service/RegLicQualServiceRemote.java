/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import base.IRemoteService;
import javax.ejb.Remote;
import masterdatamodule.dto.RegLicQualDTO;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface RegLicQualServiceRemote extends IRemoteService<RegLicQualDTO>, MasterDataService<RegLicQualDTO> {
}
