package com.tw.demo.security;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

  
  
public class AES {  
      
    public static void main(String[] args) throws Exception {  
		String content = "艺龙有房";
        System.out.println("加密前：" + content);  
  
        String key = "123456";  
        System.out.println("加密密钥和解密密钥：" + key);  
          
        String encrypt = aesEncrypt(content, key);  
        System.out.println("加密后：" + encrypt);  
          
        String decrypt = aesDecrypt(encrypt, key);  
        System.out.println("解密后：" + decrypt);  
    }  
      
    /** 
     * AES加密 
     * @param content 待加密的内容 
     * @param encryptKey 加密密钥 
     * @return 加密后的byte[] 
     * @throws Exception 
     */  
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {  
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128, new SecureRandom(encryptKey.getBytes()));  
  
        Cipher cipher = Cipher.getInstance("AES");  
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));  
          
        return cipher.doFinal(content.getBytes("utf-8"));  
    }  
      
    /** 
     * AES加密为base 64 code 
     * @param content 待加密的内容 
     * @param encryptKey 加密密钥 
     * @return 加密后的base 64 code 
     * @throws Exception 
     */  
    public static String aesEncrypt(String content, String encryptKey) throws Exception {  
		return BASE64.base64Encode(aesEncryptToBytes(content, encryptKey));
    }  
      
    /** 
     * AES解密 
     * @param encryptBytes 待解密的byte[] 
     * @param decryptKey 解密密钥 
     * @return 解密后的String 
     * @throws Exception 
     */  
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {  
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128, new SecureRandom(decryptKey.getBytes()));  
          
        Cipher cipher = Cipher.getInstance("AES");  
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));  
        byte[] decryptBytes = cipher.doFinal(encryptBytes);  
          
        return new String(decryptBytes);  
    }  
      
    /** 
     * 将base 64 code AES解密 
     * @param encryptStr 待解密的base 64 code 
     * @param decryptKey 解密密钥 
     * @return 解密后的string 
     * @throws Exception 
     */  
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {  
		return (encryptStr == null || encryptStr.length() == 0) ? null : aesDecryptByBytes(BASE64.base64Decode(encryptStr), decryptKey);
    }  
      
}  