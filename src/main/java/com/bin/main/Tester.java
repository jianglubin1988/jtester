package com.bin.main;

import com.bin.thread.HttpThread;
import com.bin.utils.PropertiesUtils;
import com.bin.utils.ThreadPoolUtils;
import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;

public class Tester {

	private final static Logger logger = Logger.getLogger(Tester.class);

	public static void main(String[] args) {
		logger.info("============================== Tester running " + Calendar.getInstance().getTime() + " ==============================");
		ExecutorService pool = null;
		try {
			int i = 0;
			int num = PropertiesUtils.getThreadNum();
			pool = ThreadPoolUtils.getInstance(num);

			while (i < num) {
				Thread thread = new HttpThread();
				pool.submit(thread);
				i++;
			}
		} catch (Exception e) {
			logger.error("异常信息捕获： " + e.getMessage());
		} finally {
			if(pool != null){
				pool.shutdown();
			}
		}

	}
}
