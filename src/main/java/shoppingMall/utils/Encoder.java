package shoppingMall.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encoder {

    public static String encode(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            String encrypted = sb.toString();

            if (encrypted.length() > 64) {
                encrypted = encrypted.substring(0, 64);
            }

            return encrypted;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}