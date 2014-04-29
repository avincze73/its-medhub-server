/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package common.service;

import javax.ejb.Remote;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface PerformaceTestServiceRemote {

    void connect();
    
}
