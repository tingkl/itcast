package security;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public class SingnatureTest {
	public static void main(String args[]) throws Exception {
		
		//非对称，用私钥加密，将加密的数据发送给你，并将原始数据发送给你，你用公钥解密得到的数据，如果解密后的数据等于收到的数据，你可以证明这句话就是他说的
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		KeyPair keyPair = generator.generateKeyPair();
		//公钥是要公安局备案的
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
		
		byte[] signed = sign(privateKey);
		verify(signed, publicKey);
		
	}

	private static void verify(byte[] signed, PublicKey publicKey) throws SignatureException, Exception {
		// TODO Auto-generated method stub
		Signature signature = Signature.getInstance("SHA1withRSA");
		signature.initVerify(publicKey);
		signature.update("给你一百万，绝不反悔!".getBytes());
		if (signature.verify(signed)) {
			System.out.println("给你一百万，绝不反悔!就是你说的");
		}
	}

	private static byte[] sign(PrivateKey privateKey)
			throws NoSuchAlgorithmException, InvalidKeyException,
			SignatureException {
		Signature signature = Signature.getInstance("SHA1withRSA");
		signature.initSign(privateKey);
		signature.update("给你一百万，绝不反悔!".getBytes());
		byte[] signed = signature.sign();
		return signed;
	}

}
