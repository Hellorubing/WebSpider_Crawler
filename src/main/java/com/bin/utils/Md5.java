package com.bin.utils;

import java.security.NoSuchAlgorithmException;

public class Md5 {
	
	public static String getMD5(byte[] source) {
		String s = null;
		char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
				'a','b','c','d','e','f'}; //用来将字节转换成十六进制表示的字符
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest();//MD5的计算结果是一个128位的长整数，用字节表示就是16个字节
			char str[] = new char[16*2];//每个字节用十六进制表示的话，使用两个字符。所以表示成十六进制需要32个字符
			int k = 0;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}	
		return null;
	}
}