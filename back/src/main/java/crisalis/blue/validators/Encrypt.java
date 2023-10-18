package crisalis.blue.validators;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class Encrypt {
    //Algoritmo a utilizar ("AES", "DES", "RSA")
    /*
    private final static String alg = "AES";
    private static String iv = "0123456789ABCDEF"; //vector de inicialización

     */
    private static String key = "92AE31A79FEEB2A3"; //llave

    public static String encrypt(String text) throws Exception{
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);

        byte[] encrypted = cipher.doFinal(text.getBytes());

        return Base64.encodeBase64String(encrypted);
    }

    public static String decrypt(String encrypted) throws Exception {
        byte[] encryptedBytes=Base64.decodeBase64( encrypted.replace("\n", "") );

        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);

        String decrypted = new String(cipher.doFinal(encryptedBytes));

        return decrypted;
    }

}
