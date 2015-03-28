package security;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSA {
	public static void main(String[] args) throws NoSuchAlgorithmException,
			InvalidKeyException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, IOException {
		//可以公钥加密私钥解密，也可以私钥加密公钥解密,不够私钥加密公钥解密没啥用处，公钥大家都知道
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		KeyPair keyPair = generator.generateKeyPair();
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();

		byte[] results = publicEncrypt("传智播客", publicKey);
		privateDecrypt(results, privateKey);
 
		privateEncryptStream("传智播客", privateKey);
		publicDecryptStream(publicKey);
	}

	private static void publicDecryptStream(PublicKey publicKey)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IOException {
		// TODO Auto-generated method stub
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		File file = new File("rsa.stream");
		FileInputStream fis = new FileInputStream(file);
		CipherInputStream cis = new CipherInputStream(fis, cipher);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int c = cis.read();// 读取bis流中的下一个字节
		while (c != -1) {
			baos.write(c);
			c = cis.read();
		}
		cis.close();
		byte retArr[] = baos.toByteArray();
		System.out.println(new String(retArr));
	}

	private static void privateEncryptStream(String data, PrivateKey privateKey)
			throws IOException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		File file = new File("rsa.stream");
		file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		CipherOutputStream cos = new CipherOutputStream(fos, cipher);
		cos.write(data.getBytes());
		cos.flush();
		cos.close();
	}

	private static void privateDecrypt(byte[] results, PrivateKey privateKey)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] result = cipher.doFinal(results);
		System.out.println(new String(result, "gbk"));
	}

	private static byte[] publicEncrypt(String data, PublicKey publicKey)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, UnsupportedEncodingException {
		// TODO Auto-generated method stubC
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] result = cipher.doFinal(data.getBytes("gbk"));
		return result;
	}

}
