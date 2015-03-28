package security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class SecretKeyTest {
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException, ClassNotFoundException {
		byte[] data = encrypt("aabb");
		decrypt(data);
	}

	private static void decrypt(byte[] results) throws NoSuchAlgorithmException,
			NoSuchPaddingException, FileNotFoundException, IOException,
			ClassNotFoundException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("AES");
		FileInputStream fisKey = new FileInputStream("xxx_secrete.key");
		ObjectInputStream ois = new ObjectInputStream(fisKey);
		SecretKey key= (SecretKey) ois.readObject();
		ois.close();
		fisKey.close();
		cipher.init(Cipher.DECRYPT_MODE, key); 
		results = cipher.doFinal(results);
		System.out.println(new String(results));
	}

	private static byte[] encrypt(String data) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException,
			FileNotFoundException, IOException {
		Cipher cipher = Cipher.getInstance("AES");
		SecretKey key = KeyGenerator.getInstance("AES").generateKey();
		cipher.init(Cipher.ENCRYPT_MODE, key);
		/*cipher.update("aaa".getBytes());
		cipher.update("bbb".getBytes());*/
		byte[] results = cipher.doFinal(data.getBytes());
		System.out.println(new String(results));
	    
		FileOutputStream fosKey = new FileOutputStream("xxx_secrete.key");
		ObjectOutputStream oos = new ObjectOutputStream(fosKey);
		oos.writeObject(key);
		oos.close();
		fosKey.close();
		return results;
	}

}
