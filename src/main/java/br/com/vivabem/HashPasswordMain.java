package br.com.vivabem;

import java.security.MessageDigest;

public class HashPasswordMain {
	public static void main(String[] args) {
		for (String password: args){
			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (Exception e) {
				e.printStackTrace();
			}
			byte[] passwordBytes = password.getBytes();
			byte[] hash = md.digest(passwordBytes);
			String passwordHash = org.jboss.security.Base64Utils.tob64(hash);
			System.out.println("password hash: " + password + "\t-> " + passwordHash);
		}
	}
}