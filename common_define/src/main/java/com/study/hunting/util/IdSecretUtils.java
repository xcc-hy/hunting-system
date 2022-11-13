package com.study.hunting.util;

import javax.crypto.*;
import java.security.SecureRandom;
import java.util.Base64;

/*
 * 对数据的id进行加解密处理
 *
 */
public class IdSecretUtils {
    protected IdSecretUtils() {
    }

    private static final String ALGORITHM="AES";
    private static Cipher ENCRYPT_CIPHER;
    private static Cipher DECRYPT_CIPHER;
    static {
        try {
            KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
            //运用SHA1安全策略
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            //设置上密钥种子
            secureRandom.setSeed("xcc-id-secret".getBytes());
            //初始化基于SHA1的算法对象
            generator.init(secureRandom);
            SecretKey key = generator.generateKey();
            ENCRYPT_CIPHER = Cipher.getInstance(ALGORITHM);
            ENCRYPT_CIPHER.init(Cipher.ENCRYPT_MODE, key);
            DECRYPT_CIPHER = Cipher.getInstance(ALGORITHM);
            DECRYPT_CIPHER.init(Cipher.DECRYPT_MODE, key);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encode(String id) throws Exception {
        return new String(Base64.getUrlEncoder().encode(ENCRYPT_CIPHER.doFinal(id.getBytes())));
    }

    public static String decode(String id) throws IllegalBlockSizeException, BadPaddingException {
        //解密
        return new String(DECRYPT_CIPHER.doFinal(Base64.getUrlDecoder().decode(id)));
    }

//    public static void main(String[] args) throws Exception {
//        String encode = IdSecretUtils.encode("101");
//        System.out.println(encode);
//        System.out.println(IdSecretUtils.decode(encode));
//    }
}
