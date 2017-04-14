/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author DaoMinhThien
 */
public class Encode {
    public static String encryptPass(String pass){
        String p = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(pass.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "2" + hashtext;
            }
            p = hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return p;
    }
}
