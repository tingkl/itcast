package security;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class MD5WithSecret {
	public static void main(String args[]) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {
		//MD5是16位,SHA是20位（这是两种报文摘要的算法）
		//MessageDigest md= MessageDigest.getInstance("MD5");
		//根据数据以及密码算摘要，防止摘要和数据同时被篡改
		//基于mac（消息验证码）的数字摘要
		PBEKeySpec keySpec = new PBEKeySpec("abcde".toCharArray());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		SecretKey key = keyFactory.generateSecret(keySpec);
		
		Mac mac = Mac.getInstance("HmacMD5");
		mac.init(key);
		
		byte[] dest = mac.doFinal("zxx".getBytes());
		System.out.println(toHex2(dest));
	}
	
	private static String toHex2(byte[] buf) {
		StringBuilder sBuilder = new StringBuilder();
		
	    for (int i = 0; i < buf.length; i++) {
	    	//高四位移动到低四位,高位是1则高四位补1，高位是0则高四位补0,故还需要&00001111
	    	int hi = (buf[i]>>4)&0x0f;
	    	int lo = buf[i]& 0x0f;
	    	sBuilder.append(Integer.toHexString(hi));
	    	sBuilder.append(Integer.toHexString(lo));
	    }
	    return sBuilder.toString();
	}
	//3f72eafdae5955ef4daadad655abb5fedeaf646c
	//转16进制的字符串形式
	private static String toHex(byte[] buf) {
		StringBuilder sBuilder = new StringBuilder();
		System.out.println(buf.length);
	    for (int i = 0; i < buf.length; i++) {
	    	//高四位移动到低四位,高位是1则高四位补1，高位是0则高四位补0,故还需要&00001111
	    	int hi = (buf[i]>>4)&0x0f;
	    	int lo = buf[i]& 0x0f;
	    	sBuilder.append(hi>9?(char)(hi - 10 +'a'):(char)(hi + '0'));
	    	sBuilder.append(lo>9?(char)(lo - 10 +'a'):(char)(lo + '0'));
	    }
	    return sBuilder.toString();
	}
	

}
