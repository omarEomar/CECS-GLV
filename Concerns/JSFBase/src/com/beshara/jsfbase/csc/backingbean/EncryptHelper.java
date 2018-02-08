package com.beshara.jsfbase.csc.backingbean;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;


public final class EncryptHelper {
    private static final String PASS_PHRASE = "besharakey";
    private static final String ALGORITHM = "DES";
    private static final String ENCODING = "UTF8";

    private EncryptHelper() {
    }

    public static String encrypt(String clearString) {

        try {
            Cipher cipherAlgorithm = getCipherAlgorithm(true);
            byte[] clearBytes = clearString.getBytes(ENCODING);
            byte[] cipherBytes = cipherAlgorithm.doFinal(clearBytes);
            char[] cipherHexChars = Hex.encodeHex(cipherBytes);
            return new String(cipherHexChars);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    public static String decrypt(String cipherHexString) {
        try {
            Cipher cipherAlgorithm = getCipherAlgorithm(false);
            char[] cipherHexChars = cipherHexString.toCharArray();
            byte[] cipherBytes = Hex.decodeHex(cipherHexChars);
            byte[] clearBytes = cipherAlgorithm.doFinal(cipherBytes);
            ByteBuffer bb = ByteBuffer.wrap(clearBytes);
            CharBuffer cb = Charset.forName(ENCODING).decode(bb);
            return new String(cb.array());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }


    private static Cipher getCipherAlgorithm(boolean encrypt) throws NoSuchAlgorithmException, 
                                                                     InvalidKeySpecException, 
                                                                     InvalidKeyException, 
                                                                     NoSuchPaddingException {
        SecretKeyFactory secretKeyFactory = 
            SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = secretKeyFactory.generateSecret(getKeySpec());
        Cipher cipherAlgorithm = Cipher.getInstance(secretKey.getAlgorithm());

        if (encrypt) {
            cipherAlgorithm.init(Cipher.ENCRYPT_MODE, secretKey);
        } else {
            cipherAlgorithm.init(Cipher.DECRYPT_MODE, secretKey);
        }

        return cipherAlgorithm;
    }

    private static KeySpec getKeySpec() throws InvalidKeyException {
        return new DESKeySpec(PASS_PHRASE.getBytes());
    }
}
