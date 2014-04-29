/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package masterdatamodule.service;

import base.IRemoteService;
import javax.ejb.Remote;
import masterdatamodule.dto.ModalityDTO;

/**
 *
 * @author root
 */
@Remote
public interface ModalityServiceRemote extends IRemoteService<ModalityDTO>, MasterDataService<ModalityDTO> {
    
}
