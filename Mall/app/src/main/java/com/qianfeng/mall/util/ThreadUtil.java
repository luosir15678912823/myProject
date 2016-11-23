package com.qianfeng.mall.util;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by qf on 2016/10/8.
 */
public class ThreadUtil {

        public static void doHttpByExecutePool(Runnable task){
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            executorService.submit(task);

        }

}
