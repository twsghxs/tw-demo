package com.tw.demo.security;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class BASE64 {

	/**
	 * base 64 encode
	 * 
	 * @param bytes
	 *            待编码的byte[]
	 * @return 编码后的base 64 code
	 */

	public static String base64Encode(byte[] bytes) {
		return new BASE64Encoder().encode(bytes);
	}

	/**
	 * base 64 decode
	 * 
	 * @param base64Code
	 *            待解码的base 64 code
	 * @return 解码后的byte[]
	 * @throws Exception
	 */
	public static byte[] base64Decode(String base64Code) throws Exception {
		return (base64Code == null || base64Code.length() == 0) ? null : new BASE64Decoder().decodeBuffer(base64Code);
	}
}
