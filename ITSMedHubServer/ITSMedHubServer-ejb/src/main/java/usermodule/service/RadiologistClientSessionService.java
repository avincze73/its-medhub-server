/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package usermodule.service;

import common.exception.UserAlreadyLoggedInException;
import javax.ejb.Stateful;
import common.dto.ClientIdentifierDTO;

/**
 *
 * @author vincze.attila
 */
@Stateful
public class RadiologistClientSessionService implements RadiologistClientSessionServiceRemote {

    @Override
    public void login(ClientIdentifierDTO clientIdentifierDTO) throws UserAlreadyLoggedInException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void logout(String tdsApplication) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void login(byte[] clientIdentifierDTO) throws UserAlreadyLoggedInException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void logout(byte[] itsApplication) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
