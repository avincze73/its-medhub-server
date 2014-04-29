/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itssecurity;

import com.sun.appserv.security.AppservPasswordLoginModule;
import com.sun.enterprise.security.auth.realm.InvalidOperationException;
import com.sun.enterprise.security.auth.realm.NoSuchUserException;
import java.util.Enumeration;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;
import sun.security.acl.PrincipalImpl;

/**
 *
 * @author vincze.attila
 */
public class ITSLoginModule extends AppservPasswordLoginModule {



    @Override
    protected void authenticateUser() throws LoginException {
        _logger.log(Level.INFO, "ITSLoginModule : authenticateUser for {0}", _username);
        
        Enumeration userGroupsEnum = null;
        String[] userGroupsArray = null;
        ITSRealm tdsRealm;
        if (_currentRealm instanceof ITSRealm){
            tdsRealm = (ITSRealm) _currentRealm;
            _logger.log(Level.INFO, "ITSRealm");
        } else {
            throw  new LoginException();
        }
        if (tdsRealm.loginUser(_username, _password)) {
            try {
                userGroupsEnum = tdsRealm.getGroupNames(_username);
                Set principals = _subject.getPrincipals();
                principals.add(new PrincipalImpl(_username));
            } catch (InvalidOperationException ex) {
                Logger.getLogger(ITSLoginModule.class.getName()).log(Level.SEVERE, null, ex);
                throw new LoginException(ex.getMessage());
            } catch (NoSuchUserException ex) {
                Logger.getLogger(ITSLoginModule.class.getName()).log(Level.SEVERE, null, ex);
                throw new LoginException(ex.getMessage());
            }
            userGroupsArray = new String[2];
            int i = 0;
            while (userGroupsEnum.hasMoreElements()) {
                userGroupsArray[i++] = ((String) userGroupsEnum.nextElement());
            }
        } else {
            throw new LoginException();
        }
        commitUserAuthentication(userGroupsArray);
    }
}
