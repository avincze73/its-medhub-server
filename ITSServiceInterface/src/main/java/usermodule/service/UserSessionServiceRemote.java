/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package usermodule.service;

import common.dto.ClientIdentifierDTO;
import common.exception.UserAlreadyLoggedInException;
import javax.ejb.Remote;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface UserSessionServiceRemote {

    void login(ClientIdentifierDTO clientIdentifierDTO) throws UserAlreadyLoggedInException;
    void logout(String itsApplication);

    void login(byte[] clientIdentifierDTO) throws UserAlreadyLoggedInException;
    void logout(byte[] itsApplication);

    //String testMethod(String arg);
    
}
