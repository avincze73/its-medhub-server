/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author vincze.attila
 */
public class AesEncryptionTool {
//    private Log log = LogFactory.getLog(AesEncryptionTool.class);
//
//    private String key;
//
//    /**
//     * Gets a message containing some sample 128, 192, and 256 bit AES keys you can use, and indicates which levels of
//     * AES encryption may not be supported.
//     *
//     * @return
//     * @throws NoSuchAlgorithmException
//     */
//    private String getSampleKeys() throws NoSuchAlgorithmException {
//        StringBuffer sb = new StringBuffer();
//        KeyGenerator kgen = KeyGenerator.getInstance("AES");
//        appendSampleKey(kgen, 128, sb);
//        sb.append("\n\n");
//        appendSampleKey(kgen, 192, sb);
//        sb.append("\n\n");
//        appendSampleKey(kgen, 256, sb);
//
//        return sb.toString();
//    }
//
//    private void appendSampleKey(KeyGenerator kgen, int numBits, StringBuffer sb) {
//        try {
//            kgen.init(numBits);
//            SecretKey skey = kgen.generateKey();
//            String exampleKey = HexUtil.asHex(skey.getEncoded());
//            sb.append("Example of ");
//            sb.append(numBits);
//            sb.append("-bit AES key: ");
//            sb.append(exampleKey);
//        }
//        catch (Throwable t) {
//            sb.append(numBits);
//            sb.append("-bit AES ENCRYPTION NOT SUPPORTED");
//            log.error("Problem checking " + numBits + "-bit AES encryption (maybe that level of encryption is not supported by this JVM)", t);
//        }
//    }
//
//    public String encrypt(String plaintext) throws Exception {
//        String result = null;
//        if (key == null) {
//            String sampleKeys = getSampleKeys();
//            throw new Exception("Encrypt failed. Must set key property of AesEncryptionToolImpl in spring config.\n\n" + sampleKeys);
//        }
//
//        byte[] keyBytes = HexUtil.lenientHexToBytes(key);
//        if (keyBytes == null || keyBytes.length < 16) {
//            String sampleKeys = getSampleKeys();
//            throw new Exception("Encrypt failed. Property 'key' of AesEncryptionToolImpl in spring config was invalid (for example, use a 16, 24, or 40 byte hex string for 128-bit, 192-bit, or 256-bit AES key, respectively).\n\n" + sampleKeys);
//        }
//
//        SecretKeySpec skeySpec = new SecretKeySpec(keyBytes, "AES");
//        Cipher cipher = Cipher.getInstance("AES");
//        try {
//            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
//            byte[] encBytes = cipher.doFinal(plaintext.getBytes());
//            result = new String(new Base64().encode(encBytes));
//        }
//        catch (InvalidKeyException ike) {
//            String sampleKeys = getSampleKeys();
//            log.error("Encrypt failed. Property 'key' was invalid in AesEncryptionToolImpl in spring config.\n\n" + sampleKeys, ike);
//        }
//
//        return result;
//    }
//
//    public String decrypt(String encryptedtext) throws Exception {
//        String result = null;
//        if (key == null) {
//            String sampleKeys = getSampleKeys();
//            throw new Exception("Decrypt failed. Must set key property of AesEncryptionToolImpl in spring config.\n\n" + sampleKeys);
//        }
//
//        byte[] keyBytes = HexUtil.lenientHexToBytes(key);
//        if (keyBytes == null || keyBytes.length < 16) {
//            String sampleKeys = getSampleKeys();
//            throw new Exception("Encrypt failed. Property 'key' of AesEncryptionToolImpl in spring config was invalid (for example, use a 16, 24, or 40 byte hex string for 128-bit, 192-bit, or 256-bit AES key, respectively).\n\n" + sampleKeys);
//        }
//
//        SecretKeySpec skeySpec = new SecretKeySpec(keyBytes, "AES");
//        Cipher cipher = Cipher.getInstance("AES");
//        try {
//            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
//            byte[] dataBytes = new Base64().decode(encryptedtext.getBytes("ASCII"));
//            byte[] decrypted = cipher.doFinal(dataBytes);
//            result = new String(decrypted);
//        }
//        catch (InvalidKeyException ike) {
//            String sampleKeys = getSampleKeys();
//            log.error("Decrypt failed. Property 'key' was invalid in AesEncryptionToolImpl in spring config.\n\n" + sampleKeys, ike);
//        }
//
//        return result;
//    }
//
//    public String getKey() {
//        return key;
//    }
//
//    public void setKey(String key) {
//        this.key = key;
//    }
}
