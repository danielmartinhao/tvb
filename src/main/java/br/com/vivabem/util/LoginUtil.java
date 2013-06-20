package br.com.vivabem.util;

import java.security.MessageDigest;

public class LoginUtil {
	public static String getHashMD5(String password){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
		}
		byte[] passwordBytes = password.getBytes();
		byte[] hash = md.digest(passwordBytes);
		String passwordHash = org.jboss.security.Base64Utils.tob64(hash);
		return passwordHash;
	}
}