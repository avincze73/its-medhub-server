/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package its.gws.service;

import java.util.List;
import javax.ejb.Remote;
import usermodule.entity.ITSManager;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface UserServiceProxyRemote {

    List<ITSManager> getManagers();

    ITSManager getManager(long id);
    
}
