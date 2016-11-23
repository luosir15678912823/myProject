package com.nightly.lovetravel.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Nightly on 2016/10/8.
 */

public class ThreadUtil {
    private static ExecutorService executorService;

    public static void execute(Runnable task){
        if(executorService==null) {
            executorService = Executors.newFixedThreadPool(10);
        }
        executorService.execute(task);
    }
}
