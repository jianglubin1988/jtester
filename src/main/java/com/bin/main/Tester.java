package com.bin.main;

import org.apache.log4j.Logger;

import com.bin.thread.HttpThread;
import com.bin.utils.PropertiesUtils;

public class Tester {

	private final static Logger logger = Logger.getLogger(Tester.class);

	public static void main(String[] args) {
		try {
			int i = 0;
			int num = PropertiesUtils.getThreadNum();
			while (i < num) {
				Thread thread = new HttpThread();
				thread.start();
				i++;
			}
		} catch (Exception e) {
			logger.error("异常信息捕获： " + e.getMessage());
		}

	}
}
