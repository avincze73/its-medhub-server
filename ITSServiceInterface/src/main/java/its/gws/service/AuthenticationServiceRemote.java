/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package its.gws.service;

import common.dto.ClientIdentifierDTO;
import common.exception.UserAlreadyLoggedInException;
import javax.ejb.Remote;

/**
 *
 * @author itsadmin
 */
@Remote
public interface AuthenticationServiceRemote {

    String getPassword(String userName);

    void loginFailed(ClientIdentifierDTO dto);

    void loginSucceeded(ClientIdentifierDTO dto);
    
    void endITSSession(String sessionId, String endingType);
    
}
