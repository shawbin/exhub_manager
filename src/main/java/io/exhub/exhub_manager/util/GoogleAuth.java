package io.exhub.exhub_manager.util;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

/**
 * google验证
 * @date 2018/7/15 10:43
 * api https://github.com/wstrange/GoogleAuth
 */
public class GoogleAuth {

    /**
     * 生成 google key
     * @return
     */
    public static String getGoogleKey() {

        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        final GoogleAuthenticatorKey key = gAuth.createCredentials();
        return key.getKey();
    }

    /**
     * 验证google code
     * @param secretKey google key
     * @param password 输入的验证码
     * @return
     */
    public static boolean isCodeValid(String secretKey, int password) {

        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        return gAuth.authorize(secretKey, password);
    }

    /**
     * 拼接谷歌二维码链接
     * @param email
     * @param secretKey
     * @param issuer
     * @return
     */
    public static String getGoogleUrl(String email, String secretKey, String issuer) {

        return "otpauth://totp/" + email + "?secret=" + secretKey + "&issuer=" + issuer;
    }

    public static void main(String[] args) {

        System.out.println(getGoogleKey());
        boolean flag = isCodeValid(getGoogleKey(), 364913);
        System.out.println(flag);
    }
}
