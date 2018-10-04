package com.weiquding.juc.threadpool;

import java.util.concurrent.ThreadPoolExecutor;

/**
 *  线程池监控工具
 *  用法：new Thread(new MonitorThreadPoolUtil(ThreadPoolExecutor executor, int seconds)).start()
 *  说明：传入线程池对象，每seconds秒打印一次线程池的状态数据
 *
 * @author wubai
 * @date 2018/10/3 23:34
 */
public class MonitorThreadPoolUtil implements Runnable {

    private ThreadPoolExecutor executor;
    private int delay;
    private volatile boolean stop;

    public MonitorThreadPoolUtil(ThreadPoolExecutor executor, int delay){
        this.executor = executor;
        this.delay = delay;
    }

    public void shutdown(){
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop){
            if(this.executor.isTerminated()){
                System.out.println("任务执行完成");
                break;
            }
            String str = String.format("[monitor] 池大小:%d,核心数:%d,活跃数:%d,完成数:%d,任务数:%d,线程结束没:%s,任务结束没:%s",
                    this.executor.getPoolSize(),
                    this.executor.getCorePoolSize(),
                    this.executor.getActiveCount(),
                    this.executor.getCompletedTaskCount(),
                    this.executor.getTaskCount(),
                    this.executor.isShutdown(),
                    this.executor.isTerminated()
            );
            System.out.println(str);
            try {
                Thread.sleep(delay * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
