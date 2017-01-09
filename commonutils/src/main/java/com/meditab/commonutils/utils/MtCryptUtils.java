/******************************************************************************
 * <p/>
 * Copyright © Meditab Software Inc. 2014
 * <p/>
 * <p>
 * <p>
 * Class:
 * 1. MtCryptUtils.java
 * <p>
 * <p>
 * <p/>
 * Description:
 * This is the Global class for AES encryption/Decryption
 * <p/>
 * <p>
 * <p>
 * Version History:
 * 1.0     December 22, 2014.     New Class      Rushil Shah     Purpose:
 * This class is for AES encryption/decryption of data that needs to be protected
 * for example: data to be stored in sqlite files.
 ***************************************************************************/

package com.meditab.commonutils.utils;

/**
 * Created by rushils on 12/22/2014.
 */

import android.util.Log;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class MtCryptUtils {

    private String iv = "fedcba9876543210";//Dummy iv (CHANGE IT as required!)
    private IvParameterSpec ivspec;
    private SecretKeySpec keyspec;
    private Cipher cipher;
    private String SecretKey = "0123456789abcdef";//Dummy secretKey (CHANGE IT as required!)


    /**
     * Constructor of the class
     */
    public MtCryptUtils() {
        ivspec = new IvParameterSpec(iv.getBytes());

        keyspec = new SecretKeySpec(SecretKey.getBytes(), "AES");

        try {
            cipher = Cipher.getInstance("AES/CBC/NoPadding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {

            e.printStackTrace();
        }
    }

    /**
     * Constructor of the class
     */
    public MtCryptUtils(String secretKey) {

        SecretKey = secretKey;

        ivspec = new IvParameterSpec(iv.getBytes());

        keyspec = new SecretKeySpec(SecretKey.getBytes(), "AES");

        try {
            cipher = Cipher.getInstance("AES/CBC/NoPadding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {

            e.printStackTrace();
        }
    }


    /**
     * Method for encrypting string
     *
     * @param text : Text to be encrypted
     * @return : encrypted byte
     */
    public byte[] encrypt(String text) {
        if (text == null || text.length() == 0) {
            Log.d("Empty string", "");
        }

        byte[] encrypted = null;

        try {
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);

            encrypted = cipher.doFinal(padString(text).getBytes());
        } catch (Exception e) {
            Log.d("Encrypted Message", e.getMessage());
        }

        return encrypted;
    }


    /**
     * Method for decrypting the string
     *
     * @param code : string to be decrypted
     * @return : decrypted byte
     */
    public byte[] decrypt(String code) {
        if (code == null || code.length() == 0)
            Log.d("Empty string", "");

        byte[] decrypted = null;

        try {
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            decrypted = cipher.doFinal(hexToBytes(code));
        } catch (Exception e) {
            Log.d("Decrypted Message", e.getMessage());
        }
        return decrypted;
    }


    /**
     * Method which converts bytes to hexadecimal
     *
     * @param : byte array
     * @return : String object
     */
    public static String bytesToHex(byte[] b) {
        StringBuffer buf = new StringBuffer();

        for (byte aB : b) {
            buf.append(byteToHex(aB));
        }
        return buf.toString();
    }


    /**
     * Converts a single byte to Hexadecimal
     *
     * @param b : byte
     * @return : String object
     */
    public static String byteToHex(byte b) {
        char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] a = {hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f]};
        return new String(a);
    }


    /**
     * Converts Hexadecimal to bytes
     *
     * @param str :string object
     * @return : byte array
     */

    public static byte[] hexToBytes(String str) {
        if (str == null) {
            return null;
        } else if (str.length() < 2) {
            return null;
        } else {
            int len = str.length() / 2;
            byte[] buffer = new byte[len];
            for (int i = 0; i < len; i++) {
                buffer[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
            }
            return buffer;
        }
    }


    /**
     * Used for providing pad to string
     *
     * @param source: Source String
     * @return : Padded String
     */
    private static String padString(String source) {
        char paddingChar = ' ';
        int size = 16;
        int x = source.length() % size;
        int padLength = size - x;

        for (int i = 0; i < padLength; i++) {
            source += paddingChar;
        }

        return source;
    }
}


