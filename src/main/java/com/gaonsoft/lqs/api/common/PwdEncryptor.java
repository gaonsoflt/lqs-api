package com.gaonsoft.lqs.api.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PwdEncryptor implements PasswordEncoder {
	@Override
	public String encode(CharSequence rawPassword) {
		String SHA = "";

		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(rawPassword.toString().getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			SHA = null;
		}
		return SHA;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encodedPassword.equals(encode(rawPassword));
	}
}