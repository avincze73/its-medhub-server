/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package its.medhub;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import util.CryptographicUtil;

/**
 *
 * @author vincze.attila
 */
public class MyUserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException, DataAccessException {
        System.out.println(string);
        User user = new User();
        user.setUsername("hospital.staff");
        user.setPassword(CryptographicUtil.create().getPasswordHash("tds1"));
//        MessageDigest m;
//        try {
//            m = MessageDigest.getInstance("MD5");
//            m.update("tds1".getBytes(), 0, "tds1".length());
//            user.setPassword(new BigInteger(1, m.digest()).toString(16));
//            System.out.println("MD5: " + new BigInteger(1, m.digest()).toString(16));
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(MyUserService.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        return user;
    }
}
