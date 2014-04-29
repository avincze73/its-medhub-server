/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import base.IRemoteService;
import javax.ejb.Remote;
import masterdatamodule.dto.WayOfClosingDTO;

/**
 *
 * @author root
 */
@Remote
public interface WayOfClosingServiceRemote extends IRemoteService<WayOfClosingDTO>, MasterDataService<WayOfClosingDTO> {

    WayOfClosingDTO getSingleByName(String name);
}
