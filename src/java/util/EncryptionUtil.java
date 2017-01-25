package util;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Base64;

public class EncryptionUtil {
	
	public static String hashPassword(String pwd) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.reset();
		md.update(pwd.getBytes());
		byte[] mdArr = md.digest();
		StringBuilder sb = new StringBuilder(mdArr.length*2);
		for(byte b : mdArr){
			int v = b & 0xff;
			if(v < 16)
				sb.append(0);
			sb.append(Integer.toHexString(v));
		}
		return sb.toString();
	}
	
	public static String getSalt(){
		Random r = new SecureRandom();
		byte[] saltBytes = new byte[128];
		r.nextBytes(saltBytes);
		return Base64.getEncoder().encodeToString(saltBytes);
	}
	
	public static String hashAndSaltPassword(String password) 
			throws NoSuchAlgorithmException{
		String salt = getSalt();
		System.out.println("salt for pwd ("+password+"): "
				+ salt);
		return hashPassword(password+salt);
	}
	
	public static void main(String[] args){
		try{
			System.out.println("pswd for pwd (suepass): "+hashAndSaltPassword("suepass"));
			System.out.println("suepass: "+hashAndSaltPassword("natashapass"));
			System.out.println("suepass: "+hashAndSaltPassword("chrispass"));
			System.out.println("suepass: "+hashAndSaltPassword("laurapass"));
			System.out.println("suepass: "+hashAndSaltPassword("davepass"));
		}
		catch(Exception e){
			
		}
	}
}
