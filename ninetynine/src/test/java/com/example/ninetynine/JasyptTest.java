package com.example.ninetynine;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.junit.jupiter.api.Test;

public class JasyptTest {

    @Test
    public void jasyptTest() {
        String password = "";  // Djasypt.password에 있는 값 넣어주기
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(4);
        encryptor.setPassword(password);
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
        String content = "and0LXRva2VuLXNlY3JldA==";    // 암호화 할 내용
        String encryptedContent = encryptor.encrypt(content); // 암호화
        String decryptedContent = encryptor.decrypt(encryptedContent); // 복호화
        System.out.println("Enc : " + encryptedContent + ", Dec: " + decryptedContent);
    }
}
