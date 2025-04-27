package shoppingMall.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encoder {

    // 평문 비밀번호를 SHA-256으로 암호화
    public static String encode(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString(); // 16진수 문자열로 반환
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
