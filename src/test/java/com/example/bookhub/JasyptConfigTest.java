package com.example.bookhub;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JasyptConfigTest {

    @Test
    void stringEncryptor() {
        String url = "db_url";
        String username = "admin";
        String password = "!Zxcv1234!!";

        System.out.println(jasyptEncoding(url));
        System.out.println(":::::   username : "+jasyptEncoding(username));
        System.out.println(":::::   password : "+jasyptEncoding(password));
    }

    public String jasyptEncoding(String value) {

        String key = "han";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(key);
        return pbeEnc.encrypt(value);
    }


/*###################################################################################*/

    @Test
    @DisplayName("패스워드를 jasypt로 암호화")
    public void jasyptEncryptorPassword() {
        String key = "han";   // 키를 이용하여 암호화 예정

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(8);   // 코어 수
        encryptor.setPassword(key);
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");  // 암호화 알고리즘

        String str = "rltjs9694@gmail.com";
        String encryptStr = encryptor.encrypt(str);
        String decryptStr = encryptor.decrypt(encryptStr);

        System.out.println("암호화 된 문자열 : " + encryptStr);
        System.out.println("복호화 된 문자열 : " + decryptStr);
    }
}
