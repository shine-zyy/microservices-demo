package com.zyy.springcloud.common.util;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


/**
 * AES加密和解密算法
 */
public class AESUtil {
	
	private static final String PASSWORD = "^Zyy!O_O";
	
	/**
	 * 获取解密后的字符串
	 * @param content
	 * @return
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static String revertAESCode(String content) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		byte[] decryptFrom = parseHexStr2Byte(content);
		byte[] decryptResult = decrypt(decryptFrom, PASSWORD);
		String decryptString = new String(decryptResult);
		return decryptString;
	}
	
	/**
	 * 获取解密后的字符串
	 * @param content
	 * @param passcode
	 * @return
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static String revertAESCode(String content,String passcode) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		byte[] decryptFrom = parseHexStr2Byte(content);
		byte[] decryptResult = decrypt(decryptFrom, passcode);
		String decryptString = new String(decryptResult);
		return decryptString;
	}
	
	/**
	 * 获取加密后的字符串
	 * @param content
	 * @return
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws UnsupportedEncodingException 
	 * @throws InvalidKeyException 
	 */
	public static String getAESCode(String content) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException{
		byte[] encryptResult = encrypt(content, PASSWORD);
		String encryptResultStr = parseByte2HexStr(encryptResult);
		return encryptResultStr;
	}
	
	/**
	 * 获取加密后的字符串
	 * @param content
	 * @param passcode
	 * @return
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws UnsupportedEncodingException 
	 * @throws InvalidKeyException 
	 */
	public static String getAESCode(String content,String passcode) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException{
		byte[] encryptResult = encrypt(content, passcode);
		String encryptResultStr = parseByte2HexStr(encryptResult);
		return encryptResultStr;
	}
	
	

	/**
	 * 加密
	 * @param content
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws NoSuchPaddingException 
	 * @throws UnsupportedEncodingException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	private static byte[] encrypt(String content, String password) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			//防止linux下 随机生成key 
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );  
            secureRandom.setSeed(password.getBytes());  
			kgen.init(128,secureRandom);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat,"AES");
			/**创建密码器**/
			Cipher cipher = Cipher.getInstance("AES");
			byte[] byteContent = content.getBytes("UTF-8");
			/**初始化密码器**/
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(byteContent);
			return result;
	}

	/**
	 * 解密
	 * @param content
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	private static byte[] decrypt(byte[] content, String password) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
			KeyGenerator kgen = KeyGenerator.
			getInstance("AES");
			//防止linux下 随机生成key 
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );  
            secureRandom.setSeed(password.getBytes());  
			kgen.init(128,secureRandom);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat,"AES");
			/**创建密码器**/
			Cipher cipher = Cipher.getInstance("AES");
			/**初始化密码器**/
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] result = cipher.doFinal(content);
			return result;
	}

	/**
	 * 将二进制转换成十六进制
	 * @param buf
	 * @return
	 */
	private static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if(hex.length() == 1) {
				hex = '0'+ hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将十六进制转换为二进制
	 * @param hexStr
	 * @return
	 */
	private static byte[] parseHexStr2Byte(String hexStr) {
		if(hexStr.length() < 1) {
			return null ;
		}else{
			byte[] result =new byte[hexStr.length() / 2];
			for(int i = 0; i < hexStr.length() / 2; i++) {
				int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
				int low = Integer.parseInt(hexStr.substring(i * 2 + 1,i * 2 + 2),16);
				result[i] = (byte) (high * 16 + low);
			}
			return result;
		}
	}

	public static void main(String[] args) throws Exception {
		String content = "TEST";
		System.out.println(getAESCode(content));
	}
}