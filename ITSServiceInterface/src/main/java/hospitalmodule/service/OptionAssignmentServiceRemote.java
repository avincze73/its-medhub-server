/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hospitalmodule.service;

import base.IRemoteService;
import hospitalmodule.dto.OptionAssignmentDTO;
import javax.ejb.Remote;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface OptionAssignmentServiceRemote extends IRemoteService<OptionAssignmentDTO>{
    
}
