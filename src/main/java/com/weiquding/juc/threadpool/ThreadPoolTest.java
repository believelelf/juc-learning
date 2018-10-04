package com.weiquding.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wubai
 * @date 2018/10/4 0:02
 */
public class ThreadPoolTest {

    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();
        new Thread(new MonitorThreadPoolUtil((ThreadPoolExecutor) executorService,1)).start();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println("===>test");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.shutdown();
    }
}

/*
pool-1-thread-1
        ===>test
        [monitor] 池大小:1,核心数:0,活跃数:1,完成数:0,任务数:1,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:0,活跃数:1,完成数:0,任务数:1,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:0,活跃数:1,完成数:0,任务数:1,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:0,活跃数:1,完成数:0,任务数:1,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:0,活跃数:1,完成数:0,任务数:1,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:0,活跃数:1,完成数:0,任务数:1,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:0,活跃数:1,完成数:0,任务数:1,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:0,活跃数:1,完成数:0,任务数:1,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:0,活跃数:1,完成数:0,任务数:1,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:0,活跃数:1,完成数:0,任务数:1,线程结束没:true,任务结束没:false
        任务执行完成*/
