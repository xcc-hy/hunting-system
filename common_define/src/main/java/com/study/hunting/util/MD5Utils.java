package com.study.hunting.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Utils {

	protected MD5Utils(){

	}

	private static final String SALT = "xcc";

	private static final String ALGORITHM_NAME = "md5";

	private static final int HASH_ITERATIONS = 2;

	public static String encrypt(String pwd) {
		return new SimpleHash(ALGORITHM_NAME, pwd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
	}

	public static String encrypt(String username, String pwd) {
		return new SimpleHash(ALGORITHM_NAME, pwd, ByteSource.Util.bytes(username.toLowerCase() + SALT),
				HASH_ITERATIONS).toHex();
	}

	public static void main(String[] args) {
		System.out.println(encrypt("xdx@163.com","123"));
	}

}
