package com.zachary.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author zhangcheng
 * @desc 加密和解密
 *
 */
public class EmsAuthEncryptUtil {

	public static final String CHARSET = "UTF-8";

	public static final String SECURITY_KEY = "iGwrYVbBtW6nlJJ0IaPsMmeAPXQZVFrDS+tQh69OyT8lsnPdC5hXbXtfQPTao9dq5tXcLItTl6FvHO9485+QAcsSpIxrVMme";

	public static String encrypt(String dataSource) {
		if (StringUtils.isEmpty(SECURITY_KEY)) {
			return dataSource;
		}
		if (StringUtils.isEmpty(dataSource))
			return "";
		try {
			byte[] bytes = encryptDes(dataSource.getBytes(CHARSET),
					SECURITY_KEY, CHARSET);
			if (bytes == null) {
				return "";
			}
			bytes = Base64.encodeBase64(bytes);
			return new String(bytes, CHARSET);
		} catch (UnsupportedEncodingException e) {
		} catch (Exception e) {
		}
		return "";
	}

	public static String decrypt(String dataSource) {
		if (StringUtils.isEmpty(SECURITY_KEY)) {
			return dataSource;
		}
		if (StringUtils.isEmpty(dataSource))
			return "";
		try {
			byte[] bytes = Base64.decodeBase64(dataSource.getBytes(CHARSET));
			bytes = decryptDes(bytes, SECURITY_KEY, CHARSET);
			if (bytes == null) {
				return "";
			}
			return new String(bytes, CHARSET);
		} catch (UnsupportedEncodingException e) {
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * DES 锟斤拷锟斤拷
	 * 
	 * @param dataSource
	 * @param securityKey
	 *            锟斤拷锟斤拷KEY
	 * @return
	 */
	private static byte[] encryptDes(byte[] dataSource, String securityKey,
			String charset) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(securityKey.getBytes(charset));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			return cipher.doFinal(dataSource);
		} catch (UnsupportedEncodingException e) {
		} catch (InvalidKeyException e) {
		} catch (NoSuchAlgorithmException e) {
		} catch (InvalidKeySpecException e) {
		} catch (NoSuchPaddingException e) {
		} catch (IllegalBlockSizeException e) {
		} catch (BadPaddingException e) {
		} catch (Exception e) {
		}
		return null;
	}

	private static byte[] decryptDes(byte[] src, String securityKey,
			String charset) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(securityKey.getBytes(charset));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, securekey, random);
			return cipher.doFinal(src);
		} catch (InvalidKeyException e) {
		} catch (UnsupportedEncodingException e) {
		} catch (NoSuchAlgorithmException e) {
		} catch (InvalidKeySpecException e) {
		} catch (NoSuchPaddingException e) {
		} catch (Exception e) {
		}
		return null;

	}

	public static String decryptBillCode(String billCode) {
		try {
			billCode = billCode.replaceAll(" ", "+");
			billCode = EmsAuthEncryptUtil.decrypt(billCode);
			billCode = billCode.toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return billCode;
	}

	public static void main(String[] args) {
		/*long start = System.currentTimeMillis();
		String result = encrypt("625512234567");
		String str = decrypt(result);
		System.out.println(result);
		System.out.println(str);
		long end = System.currentTimeMillis();
		System.out.println("desc: " + (end - start) + ":ms");*/
		String a=null;
		//System.out.println(StringUtils.contains(a, "2h"));
		
		try
		{
			a.compareTo("ss");
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}

}