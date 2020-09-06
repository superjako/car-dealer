package com.sg.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: RSA
 * @Description: RSA签名验签
 * @author: xz
 * @date 2019年8月15日 上午10:54:06
 */
public class RSA {

	private static final String SIGN_TYPE_RSA = "RSA";

	private static final String SIGN_TYPE_RSA2 = "RSA2";

	private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	private static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";

	private static final int DEFAULT_BUFFER_SIZE = 8192;

	private static final String CHARSET = "UTF-8";

	/**
	 * 生成公钥私钥对
	 * @throws NoSuchAlgorithmException
	 */
	public static Map<String, String> genKeyPair() throws NoSuchAlgorithmException {
		Map<String, String> keyMap = new HashMap<String, String>();
		// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		// 初始化密钥对生成器，密钥大小为96-1024位
		keyPairGen.initialize(1024,new SecureRandom());
		// 生成一个密钥对，保存在keyPair中
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
		String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
		// 得到私钥字符串
		String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
		// 将公钥和私钥保存到Map
		keyMap.put("publicKey", publicKeyString);  //公钥
		keyMap.put("privateKey", privateKeyString);  //私钥
		return keyMap;
	}

	/**
	 * RSA/RSA2签名
	 * @param signType 签名类型RSA/RSA2
	 * @param content 签名内容
	 * @param privateKey 私钥
	 * @param charset 字符集
	 * @return 签名串
	 * @throws Exception
	 */
	public static String rsaSign(String signType, String content, String privateKey, String charset) throws Exception {
		PrivateKey priKey = null;
		Signature signature = null;
		if (SIGN_TYPE_RSA.equals(signType)) {
			priKey = getPrivateKeyFromPKCS8(SIGN_TYPE_RSA, new ByteArrayInputStream(privateKey.getBytes()));
			signature = Signature.getInstance(SIGN_ALGORITHMS);
		} else if (SIGN_TYPE_RSA2.equals(signType)) {
			priKey = getPrivateKeyFromPKCS8(SIGN_TYPE_RSA, new ByteArrayInputStream(privateKey.getBytes()));
			signature = Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);
		} else {
			throw new Exception("不是支持的签名类型 : : signType=" + signType);
		}
		signature.initSign(priKey);
		if (StringUtils.isEmpty(charset)) {
			signature.update(content.getBytes());
		} else {
			signature.update(content.getBytes(charset));
		}
		byte[] signed = signature.sign();
		return new String(Base64.encodeBase64(signed));
	}

	/**
	 * RSA/RSA2验签
	 * @param signType 签名类型
	 * @param content 签名内容
	 * @param sign 签名串
	 * @param publicKey 公钥
	 * @param charset 字符集
	 * @return 验签结果
	 * @throws Exception
	 */
	public static boolean rsaCheck(String signType, String content, String sign, String publicKey, String charset) throws Exception {
		Signature signature = null;
		PublicKey pubKey = getPublicKeyFromX509("RSA", new ByteArrayInputStream(publicKey.getBytes()));
		if (SIGN_TYPE_RSA.equals(signType)) {
			signature = Signature.getInstance(SIGN_ALGORITHMS);
		} else if (SIGN_TYPE_RSA2.equals(signType)) {
			signature = Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);
		} else {
			throw new Exception("不是支持的签名类型 : signType=" + signType);
		}
		signature.initVerify(pubKey);
		if (StringUtils.isEmpty(charset)) {
			signature.update(content.getBytes());
		} else {
			signature.update(content.getBytes(charset));
		}
		return signature.verify(Base64.decodeBase64(sign.getBytes()));
	}

	/**
	 * 私钥字符串生成私钥key
	 * @param algorithm
	 * @param ins
	 * @return
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKeyFromPKCS8(String algorithm, InputStream ins) throws Exception {
		if (ins == null || StringUtils.isEmpty(algorithm)) {
			return null;
		}
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		byte[] encodedKey = readText(ins).getBytes();
		encodedKey = Base64.decodeBase64(encodedKey);
		return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
	}

	/**
	 * 公钥字符串生成公钥key
	 * @param algorithm
	 * @param ins
	 * @return
	 * @throws Exception
	 */
	public static PublicKey getPublicKeyFromX509(String algorithm, InputStream ins) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		StringWriter writer = new StringWriter();
		io(new InputStreamReader(ins), writer, -1);
		byte[] encodedKey = writer.toString().getBytes();
		encodedKey = Base64.decodeBase64(encodedKey);
		return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
	}


	private static String readText(InputStream ins) throws IOException {
		Reader reader = new InputStreamReader(ins);
		StringWriter writer = new StringWriter();
		io(reader, writer, -1);
		return writer.toString();
	}

	private static void io(Reader in, Writer out, int bufferSize) throws IOException {
		if (bufferSize == -1) {
			bufferSize = DEFAULT_BUFFER_SIZE >> 1;
		}
		char[] buffer = new char[bufferSize];
		int amount;
		while ((amount = in.read(buffer)) >= 0) {
			out.write(buffer, 0, amount);
		}
	}

	public static void main(String[] args) throws Exception {
		//生成公钥和私钥
		Map<String, String> keyMap = genKeyPair();
		System.out.println("随机生成的公钥为：" + keyMap.get("publicKey"));
		System.out.println("随机生成的私钥为：" + keyMap.get("privateKey"));
		//加密内容
		String content = "农交网";
		System.out.println("加密内容为：" + content);
		String sign = rsaSign(SIGN_TYPE_RSA2, content, keyMap.get("privateKey"), CHARSET);
		System.out.println("加密串为：" + sign);
		boolean result = rsaCheck(SIGN_TYPE_RSA2, content, sign, keyMap.get("publicKey"), CHARSET);
		System.out.println("验签结果为：" + result);
	}

}
