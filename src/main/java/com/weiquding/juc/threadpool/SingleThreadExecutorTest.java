package com.weiquding.juc.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wubai
 * @date 2018/10/4 10:01
 */
public class SingleThreadExecutorTest {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final List<Integer> list = new ArrayList<>();
        ExecutorService executorService = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        new Thread(new MonitorThreadPoolUtil((ThreadPoolExecutor) executorService, 1)).start();
        final Random random = new Random();
        for(int i = 0; i < 10000; i++){
            int finalI = i;
            executorService.execute(new Runnable(){
               @Override
               public void run() {
                 /*  try {
                       Thread.sleep(3);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }*/
                   list.add(finalI);
               }
           });
        }
        System.out.println("===>main thread to be shutdown");
        /**
         *      * Initiates an orderly shutdown in which previously submitted
         *      * tasks are executed, but no new tasks will be accepted.
         *      * Invocation has no additional effect if already shut down.
         *      *
         *      * <p>This method does not wait for previously submitted tasks to
         *      * complete execution.  Use {@link #awaitTermination awaitTermination}
         *      * to do that.
         */
        executorService.shutdown();
        System.out.println("===>main thread called shutdown method");
        /**
         *      * Blocks until all tasks have completed execution after a shutdown
         *      * request, or the timeout occurs, or the current thread is
         *      * interrupted, whichever happens first.
         */
        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println(list.size());
        System.out.println("use time:" + (System.currentTimeMillis() - start));
    }
}

/*
示例1：调用shutdown及awaitTermination
===>main thread to be shutdown
        ===>main thread called shutdown method
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:0,任务数:512,线程结束没:false,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:175,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:415,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:675,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:935,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:1198,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:1464,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:1725,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:1978,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:2240,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:2501,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:2762,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:3026,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:3281,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:3543,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:3807,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:4067,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:4327,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:0,完成数:4571,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:4827,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:5082,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:5341,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:5605,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:5861,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:6119,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:6380,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:6643,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:6907,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:7167,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:7414,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:7673,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:7939,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:8203,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:8465,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:8714,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:8976,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:9237,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:9499,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:9761,任务数:10000,线程结束没:true,任务结束没:false
        10000
        Disconnected from the target VM, address: '127.0.0.1:59735', transport: 'socket'
        任务执行完成*/



/*
示例2：调用shutdown,不调用awaitTermination,不会阻塞main线程
===>main thread to be shutdown
        ===>main thread called shutdown method
        0
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:0,任务数:2968,线程结束没:false,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:224,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:490,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:754,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:1016,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:1276,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:1525,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:1783,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:2042,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:2300,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:2562,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:2816,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:3078,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:3340,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:3603,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:3864,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:4124,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:4381,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:4643,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:4906,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:5168,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:5427,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:5682,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:5944,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:6205,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:6468,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:6731,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:6982,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:7242,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:7502,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:7764,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:8021,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:8272,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:8534,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:8801,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:9060,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:9320,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:9578,任务数:10000,线程结束没:true,任务结束没:false
        [monitor] 池大小:1,核心数:1,活跃数:1,完成数:9833,任务数:10000,线程结束没:true,任务结束没:false
        任务执行完成
        Disconnected from the target VM, address: '127.0.0.1:60023', transport: 'socket'

        Process finished with exit code 0*/
/*
示例3：
    线程内部不休眠，计算执行时间
===>main thread to be shutdown
        ===>main thread called shutdown method
        10000
        [monitor] 池大小:0,核心数:1,活跃数:0,完成数:0,任务数:1,线程结束没:false,任务结束没:false
        use time:45
        任务执行完成
        Disconnected from the target VM, address: '127.0.0.1:60324', transport: 'socket'

        Process finished with exit code 0*/
