package com.fz.admin.core;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Map;

public class EncryptCore {
    /** 安全密钥 */
    private static final String keyData = "C056E9E07D9C6CC9E0";
    /**
     * DES加密 默认密钥
     * @param encryptData
     * @return
     */
    public static String encrypt(String encryptData)
    {
        return encrypt(encryptData,keyData);
    }
    /**
     * DES解密 默认密钥
     * @param sourceData
     * @return
     */
    public static String dencrypt(String sourceData)
    {
        try {
            return dencrypt(sourceData,keyData);
        }
        catch (Exception e) {
            return sourceData;
        }

    }
    /**
     * DES加密 指定密钥
     * @param encryptData
     * @param key
     * @return
     */
    public static String encrypt(String encryptData,String key)
    {
        try{
            byte[] datasource=encryptData.getBytes();
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            return cipher.doFinal(datasource).toString();
        }
        catch(Throwable e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * DES解密 指定密钥
     * @param sourceData
     * @param key
     * @return
     */
    public static String dencrypt(String sourceData,String key) throws Exception
    {
        byte[] src=sourceData.getBytes();
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(key.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(src).toString();
    }

    public static String MD5(String sourceData)throws IOException
    {
        return byte2Hex(encryptMD5(sourceData));
    }
    public static byte[] encryptMD5(String data) throws IOException {
        return encryptMD5(data.getBytes("UTF-8"));
    }

    public static byte[] encryptMD5(byte[] data) throws IOException {
        Object var1 = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(data);
            return bytes;
        } catch (GeneralSecurityException var3) {
            throw new IOException(var3.toString());
        }
    }
    private static String byte2Hex(byte[] bytes) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int j = bytes.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (byte byte0 :bytes) {
            str[k++] =hexDigits[byte0 >>> 4 & 0xf];
            str[k++] =hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }
    private static byte[] encryptHMAC(String data, String secret) throws IOException {
        byte[] bytes = null;
        try {
            SecretKey secretKey = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacMD5");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data.getBytes("UTF-8"));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }
}
