package com.shopmanager.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.Arrays;
import java.util.Objects;

public class EncodingUtils {
    public static String Encoding(String toEncodingStr){
        Objects.requireNonNull(toEncodingStr);
        String encodingStr=null;
        try {
            MessageDigest md=MessageDigest.getInstance("MD5");
            md.update(toEncodingStr.getBytes());
            byte[] digest = md.digest();
            BigInteger bigInteger=new BigInteger(1,digest);
            encodingStr = bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return encodingStr;
    }

}
