package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public static void main(String args[]) throws NoSuchAlgorithmException {
		//MD5是16位,SHA是20位（这是两种报文摘要的算法）
		//MessageDigest md= MessageDigest.getInstance("MD5");
		MessageDigest digest=MessageDigest.getInstance("SHA-1");
		digest.update("zxx".getBytes());
		byte [] results = digest.digest();
		System.out.println(toHex2(results));
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
