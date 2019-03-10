package com.sly.accountmanager.common.utils;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * rsa加密工具类
 * 
 * @author sly
 * @time 2018年12月12日
 */
public class RsaUtils {
	public static final String KEY_ALGORITHM = "RSA";
	/** 貌似默认是RSA/NONE/PKCS1Padding，未验证 */
	public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
	public static final String PUBLIC_KEY = "B38A579FFE8711E88737000C29048FA8";
	public static final String PRIVATE_KEY = "BCB81751FE8711E88737000C29048FA8";

	/** RSA密钥长度必须是64的倍数，在512~65536之间。默认是1024 */
	public static final int KEY_SIZE = 2048;

	/**
	 * _生成密钥对。注意这里是生成密钥对KeyPair，再由密钥对获取公私钥
	 * 
	 * @return
	 * @author sly
	 * @time 2019年3月9日
	 */
	public static Map<String, byte[]> generateKeyBytes() {

		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
			keyPairGenerator.initialize(KEY_SIZE);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

			Map<String, byte[]> keyMap = new HashMap<String, byte[]>(16);
			keyMap.put(PUBLIC_KEY, publicKey.getEncoded());
			// System.out.println("PUBLIC_KEY:" +
			// Base64.encodeBase64String(publicKey.getEncoded()));
			keyMap.put(PRIVATE_KEY, privateKey.getEncoded());
			// System.out.println("PRIVATE_KEY:"
			// +Base64.encodeBase64String(privateKey.getEncoded()));
			return keyMap;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * _还原公钥，X509EncodedKeySpec 用于构建公钥的规范
	 * 
	 * @param keyBytes
	 * @return
	 * @author sly
	 * @time 2019年3月9日
	 */
	public static PublicKey restorePublicKey(byte[] keyBytes) {
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);

		try {
			KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
			PublicKey publicKey = factory.generatePublic(x509EncodedKeySpec);
			return publicKey;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * _还原私钥，PKCS8EncodedKeySpec 用于构建私钥的规范
	 * 
	 * @param keyBytes
	 * @return
	 * @author sly
	 * @time 2019年3月9日
	 */
	public static PrivateKey restorePrivateKey(byte[] keyBytes) {
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
		try {
			KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
			PrivateKey privateKey = factory.generatePrivate(pkcs8EncodedKeySpec);
			return privateKey;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * _加密
	 * 
	 * @param key
	 * @param plainText
	 * @return
	 * @author sly
	 * @time 2019年3月9日
	 */
	public static byte[] rsaEncode(PublicKey key, byte[] plainText) {

		try {
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return cipher.doFinal(plainText);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * _解密
	 * 
	 * @param key
	 * @param encodedText
	 * @return
	 * @author sly
	 * @time 2019年3月9日
	 */
	public static String rsaDecode(PrivateKey key, byte[] encodedText) {

		try {
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			return new String(cipher.doFinal(encodedText));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
		}
		return null;

	}
}
