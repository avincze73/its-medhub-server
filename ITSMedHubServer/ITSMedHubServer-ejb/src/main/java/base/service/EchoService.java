/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.service;

import its.gws.service.EchoServiceRemote;
import javax.ejb.Stateless;

/**
 *
 * @author itsadmin
 */
@Stateless
public class EchoService implements EchoServiceRemote {

    @Override
    public String echo() {
        return "hello vincze attila";
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
    
}
