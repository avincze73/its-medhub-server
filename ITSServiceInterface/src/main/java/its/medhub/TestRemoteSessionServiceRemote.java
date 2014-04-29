/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package its.medhub;

import javax.ejb.Remote;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface TestRemoteSessionServiceRemote {

    void businessMethod();
    
}
