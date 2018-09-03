package com.build.cloud;

import java.security.NoSuchAlgorithmException;

import com.sunsine.common.util.encrypt.MD5Util;


public class PaTest {
	public static void main(String[] args) {
		try {
			System.out.println(MD5Util.encode("admin"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
