/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package masterdatamodule.service;

import base.IRemoteService;
import javax.ejb.Remote;
import masterdatamodule.dto.HospContrOptionDTO;

/**
 *
 * @author root
 */
@Remote
public interface HospContrOptionServiceRemote extends IRemoteService<HospContrOptionDTO>, MasterDataService<HospContrOptionDTO> {
    
}
