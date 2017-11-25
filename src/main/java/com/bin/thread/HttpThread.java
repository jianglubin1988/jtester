package com.bin.thread;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.bin.utils.HttpRequestUtils;
import com.bin.utils.PropertiesUtils;

import net.sf.json.JSONObject;

public class HttpThread extends Thread {

	private static final Logger logger = Logger.getLogger(HttpThread.class);

	public void run() {
		try {
			Set<Map<String, Object>> sets = PropertiesUtils.getAddrs();
			Long errCount = 0l;
			Long total = 0l;
			int i = 0;
			int count = PropertiesUtils.getLoopCount();
			while (i < count) {
				for (Map<String, Object> map : sets) {
					Long start = System.currentTimeMillis();
					JSONObject param = map.get("param") == null ? null : (JSONObject) map.get("param");
					HttpRequestUtils.httpPost((String) map.get("url"), param);
					Long end = System.currentTimeMillis();
					Long interval = end - start;
					total += interval;
					if (interval > PropertiesUtils.getTimeout()) {
						errCount++;
						logger.info("线程" + Thread.currentThread().getName() + "调用Http接口耗时：" + interval + "ms");
					}
				}
				i++;
			}
			Long num = count * sets.size() * 1l;
			Long average = total / num;
			logger.info("线程名称：" + Thread.currentThread().getName() + "，Http请求数量： " + num + "，请求超时数量： " + errCount
					+ "，总耗时: " + total + "ms，平均耗时： " + average + "ms");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("调用接口失败！异常信息：" + e.getMessage());
		}
	}
}
