package cn.vivian.demojwt.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 *
 */
public class RSACryptographyUtils {

    private static KeyPair keyPair;

    public static KeyPair genKeyPair(int keyLength) throws NoSuchAlgorithmException {
        if (keyPair == null) {
            synchronized (RSACryptographyUtils.class) {
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
                keyPairGenerator.initialize(keyLength);
                keyPair = keyPairGenerator.generateKeyPair();
            }
        }
        return keyPair;
    }

}
