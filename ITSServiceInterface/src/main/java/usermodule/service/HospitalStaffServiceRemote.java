/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package usermodule.service;

import base.IRemoteService;
import javax.ejb.Remote;
import usermodule.dto.HospitalStaffDTO;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface HospitalStaffServiceRemote extends IRemoteService<HospitalStaffDTO> {
    
}
