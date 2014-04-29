/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import base.IRemoteService;
import javax.ejb.Remote;
import masterdatamodule.dto.RegLicQualTypeDTO;

/**
 *
 * @author root
 */
@Remote
public interface RegLicQualTypeServiceRemote extends IRemoteService<RegLicQualTypeDTO>, MasterDataService<RegLicQualTypeDTO> {
}
