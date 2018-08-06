package com.bin.thread;

import com.bin.utils.HttpRequestUtils;
import com.bin.utils.PropertiesUtils;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Set;

public class HttpThread extends Thread {

    private static final Logger logger = Logger.getLogger(HttpThread.class);

    public void run() {
        try {
            Set<Map<String, Object>> sets = PropertiesUtils.getAddrs();
            Long temp = 0l;
            String maxLog = "";
            Long errCount = 0l;
            Long total = 0l;
            int i = 0;
            int count = PropertiesUtils.getLoopCount();
            while (i < count) {
                for (Map<String, Object> map : sets) {
                    Long start = System.currentTimeMillis();
                    JSONObject param = map.get("params") == null ? null : (JSONObject) map.get("params");
                    String url = (String) map.get("url");
                    String result = HttpRequestUtils.httpPost(url, param);
                    Long end = System.currentTimeMillis();
                    Long interval = end - start;
                    String normLog = "\n线程：" + Thread.currentThread().getName()
                            + "\n接口：" + url
                            + "\n耗时：" + interval + "ms"
                            + "\n请求报文：" + param
                            + "\n返回报文：" + result
                            + "\n";
                    if (interval > temp) {
                        temp = interval;
                        maxLog = normLog;
                    }
                    total += interval;
                    if (interval > PropertiesUtils.getTimeout()) {
                        errCount++;
                        logger.info(normLog);
                    } else {
                        logger.info("返回报文：" + result + "，耗时：" + interval + "ms");
                    }

                }
                i++;
            }
//			Long num = count * sets.size() * 1l;
//			Long average = total / num;
//			Long max = temp;
//			logger.info("\n================线程【" + Thread.currentThread().getName() + "】信息开始================"
//					+ "\n总耗时: " + total + "ms"
//					+ "\n平均耗时： " + average + "ms"
//					+ "\n最大耗时：" + max + "ms"
//					+ "\n请求数量： " + num
//					+ "\n超时数量： " + errCount
//					+ "\n----最大耗时信息----"
//					+ maxLog
//					+ "\n================线程【" + Thread.currentThread().getName() + "】信息结束================"
//					+ "\n\n"
//			);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("调用接口失败！异常信息：" + e.getMessage());
        }
    }
}
