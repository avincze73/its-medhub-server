/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gwsmodule.service;

import common.RemoteMessage;
import common.exception.UserAlreadyLoggedInException;
import common.exception.UserNotDefinedException;
import javax.ejb.Remote;

/**
 *
 * @author itsadmin
 */
@Remote
public interface GatewayServerSessionServiceRemote {

    RemoteMessage getPassword(RemoteMessage input) throws UserNotDefinedException, UserAlreadyLoggedInException;

    void startITSSession();

    void endITSSession(String sessionId, Object endingType);

    void loginSucceeded(RemoteMessage input);

    void loginFailed(RemoteMessage input);
    
}
