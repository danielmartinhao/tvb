package br.com.vivabem;

import java.security.MessageDigest;

public class HashPassword {
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