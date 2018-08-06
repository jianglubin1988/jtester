package com.bin.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtils {

    private static ExecutorService pool;

    public static ExecutorService getInstance(){
        try {
            if(pool == null){
                pool = getInstance(PropertiesUtils.getThreadNum());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return pool;
    }

    public static ExecutorService getInstance(int threadNum){
        try {
            if(pool == null){
                pool = Executors.newFixedThreadPool(threadNum);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return pool;
    }
}
