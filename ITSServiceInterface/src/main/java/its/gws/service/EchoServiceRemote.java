/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package its.gws.service;

import javax.ejb.Remote;

/**
 *
 * @author itsadmin
 */
@Remote
public interface EchoServiceRemote {

    String echo();
    
}
