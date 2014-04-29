/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import usermodule.entity.ITSUser;

/**
 *
 * @author vincze.attila
 */
public class CryptographicUtil {

    private Cipher cipher;
    private SecretKey secretKey;
    private static final String preSharedKey = "b5f7564f7f555a1a668619bd75b4135f98fcdd98fad19da3dc29163709a0b6a5";
    private static final String salt = "b5ss";

    private CryptographicUtil() {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(256);
            //secretKey = generator.generateKey();
            //secretKey = new SecretKeySpec(preSharedKey.getBytes(), "AES");

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(preSharedKey.toCharArray(), salt.getBytes(), 1024, 256);
            SecretKey tmp = factory.generateSecret(spec);
            secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
            cipher = Cipher.getInstance("AES");
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(CryptographicUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(CryptographicUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CryptographicUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static CryptographicUtil create() {
        return new CryptographicUtil();
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
            Logger.getLogger(CryptographicUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public byte[] encrypt(Object object) {
        byte[] result = null;
        {
            ObjectOutputStream oos = null;
            try {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(baos);
                CipherOutputStream cos = new CipherOutputStream(bos, cipher);
                oos = new ObjectOutputStream(cos);
                oos.writeObject(object);
                oos.flush();
                oos.close();
                result = baos.toByteArray();
            } catch (IOException ex) {
                Logger.getLogger(CryptographicUtil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(CryptographicUtil.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    oos.close();
                } catch (IOException ex) {
                    Logger.getLogger(CryptographicUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    public Object decrypt(byte[] encryptedObject) {
        ObjectInputStream ois = null;
        Object result = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            BufferedInputStream bis =
                    new BufferedInputStream(new ByteArrayInputStream(encryptedObject));
            CipherInputStream cis = new CipherInputStream(bis, cipher);
            ois = new ObjectInputStream(cis);
            result = ois.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CryptographicUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(CryptographicUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CryptographicUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(CryptographicUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public static void main(String args[]) throws Exception {
        CryptographicUtil cu = CryptographicUtil.create();
        ITSUser user = new ITSUser();
        user.setName("vincze attila");
        System.out.println(user.getName());
        byte[] bt = cu.encrypt(user);
        System.out.println(bt);
        user = (ITSUser) cu.decrypt(bt);
        System.out.println(user.getName());

    }
}
