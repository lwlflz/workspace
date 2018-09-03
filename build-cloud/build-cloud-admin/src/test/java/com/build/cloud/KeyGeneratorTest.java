package com.build.cloud;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.codec.Base64;

public class KeyGeneratorTest {
	public static void main(String[] args) {
		try {
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			SecretKey deskey = keygen.generateKey();
			System.out.println(Base64.encodeToString(deskey.getEncoded()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String salt = RandomStringUtils.randomAlphanumeric(20);
		System.out.println(salt);
	}
}
