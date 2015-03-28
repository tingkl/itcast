package security;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class SecretKeyFromString {
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException, ClassNotFoundException, InvalidKeySpecException, InvalidAlgorithmParameterException {
		byte[] data = encrypt("aabb");
		decrypt(data);
	}

	private static void decrypt(byte[] results) throws NoSuchAlgorithmException,
			NoSuchPaddingException, FileNotFoundException, IOException,
			ClassNotFoundException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException {
		Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
		
		SecretKey key= SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(new PBEKeySpec("abcdefgh".toCharArray()));
		PBEParameterSpec pSpec = new PBEParameterSpec(new byte[]{ 1, 2, 3, 4, 5, 6, 7, 8}, 1000); 
		cipher.init(Cipher.DECRYPT_MODE, key, pSpec); 
		results = cipher.doFinal(results);
		System.out.println(new String(results));
	}

	private static byte[] encrypt(String data) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException,
			FileNotFoundException, IOException, InvalidKeySpecException, InvalidAlgorithmParameterException {
		Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
		SecretKey key= SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(new PBEKeySpec("abcdefgh".toCharArray()));
		PBEParameterSpec pSpec = new PBEParameterSpec(new byte[]{ 1, 2, 3, 4, 5, 6, 7, 8}, 1000); 
		cipher.init(Cipher.ENCRYPT_MODE, key, pSpec);
		/*cipher.update("aaa".getBytes());
		cipher.update("bbb".getBytes());*/
		byte[] results = cipher.doFinal(data.getBytes());
		System.out.println(new String(results));
		return results;
	}

}
