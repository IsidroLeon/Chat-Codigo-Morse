package com.ucab.javachat.Servidor.model;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64; 


/**
 * Esta clase tiene los metodos para encriptar y desencriptar la informacion importante del usuario.
 * 
 * Los metodos los consegui de: //http://www.qualityinfosolutions.com/metodos-para-encriptar-y-desencriptar-en-java/
 * 
 * @author qualitifuinfosolutions.
 *
 */



public class Criptologia {

   public String encriptar(String texto) {

       String secretKey = "=048!;|599;v_!!1:*y|FQ;w!a+x.93o8I7A48~Ba%i43*3r0:3B"; //llave para encriptar datos
       String base64EncryptedString = "";

       try {

           MessageDigest md = MessageDigest.getInstance("MD5");
           byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
           byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

           SecretKey key = new SecretKeySpec(keyBytes, "DESede");
           Cipher cipher = Cipher.getInstance("DESede");
           cipher.init(Cipher.ENCRYPT_MODE, key);

           byte[] plainTextBytes = texto.getBytes("utf-8");
           byte[] buf = cipher.doFinal(plainTextBytes);
           byte[] base64Bytes = Base64.encodeBase64(buf);
           base64EncryptedString = new String(base64Bytes);

       } catch (Exception ex) {
       }
       return base64EncryptedString;
   }

   public String desencriptar(String textoEncriptado) throws Exception {

       String secretKey = "=048!;|599;v_!!1:*y|FQ;w!a+x.93o8I7A48~Ba%i43*3r0:3B"; //llave para encriptar datos
       String base64EncryptedString = "";

       try {
           byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
           MessageDigest md = MessageDigest.getInstance("MD5");
           byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
           byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
           SecretKey key = new SecretKeySpec(keyBytes, "DESede");

           Cipher decipher = Cipher.getInstance("DESede");
           decipher.init(Cipher.DECRYPT_MODE, key);

           byte[] plainText = decipher.doFinal(message);

           base64EncryptedString = new String(plainText, "UTF-8");

       } catch (Exception ex) {
       }
       return base64EncryptedString;
   }
}