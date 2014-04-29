/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itssecurity;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vincze.attila
 */
public class SecurityUtil {

    private SecurityUtil() {
    }

    public static SecurityUtil create() {
        return new SecurityUtil();
    }

    public String getPasswordHash(String password) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            synchronized (md) {
                md.update(password.getBytes(), 0, password.length());
            }
            result = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SecurityUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
