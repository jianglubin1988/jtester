package com.bin.utils;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;

public class PropertiesUtils {

	private static final String RESOURCE = "/httpinfo.properties";
	private static final String PREFIX = "httpMap";
	private static Properties prop = new Properties();

	static {
		try {
			InputStream in = Object.class.getResourceAsStream(RESOURCE);
			prop.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object get(String key) throws Exception {
		return prop.get(key);
	}

	public static Integer getThreadNum() throws Exception {
		String threadNum = (String) prop.get("threadNum");
		if (StringUtils.isEmpty(threadNum)) {
			throw new Exception("线程数量(threadNum)为空！");
		}
		return Integer.parseInt(threadNum);
	}

	public static Integer getLoopCount() throws Exception {
		String loopCount = (String) prop.get("loopCount");
		if (StringUtils.isEmpty(loopCount)) {
			throw new Exception("线程数量(loopCount)为空！");
		}
		return Integer.parseInt(loopCount);
	}
	
	public static Integer getTimeout() throws Exception {
		String timeout = (String) prop.get("timeout");
		if (StringUtils.isEmpty(timeout)) {
			throw new Exception("线程数量(timeout)为空！");
		}
		return Integer.parseInt(timeout);
	}

	public static Set<Map<String, Object>> getAddrs() throws Exception {
		Set<Map<String, Object>> addrs = new HashSet<Map<String, Object>>();
		try {
			String prefix = prop.getProperty("keyPrefix").trim();
			if (StringUtils.isEmpty(prefix)) {
				prefix = PREFIX;
			}
			for (Object key : prop.keySet()) {
				if (!((String) key).startsWith(prefix)) {
					continue;
				}
				String value = prop.getProperty(key.toString()).trim();
				JSONObject json = JSONObject.fromObject(value);
				addrs.add((Map) json);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return addrs;
	}

	public static void main(String[] args) {
		try {
			Set<Map<String, Object>> sets = getAddrs();
			for (Map<String, Object> map : sets) {
				System.out.println("url: " + map.get("url"));
				System.out.println("params: " + map.get("params"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
