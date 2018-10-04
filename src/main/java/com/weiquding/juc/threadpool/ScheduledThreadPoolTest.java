package com.weiquding.juc.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author wubai
 * @date 2018/10/4 10:50
 */
public class ScheduledThreadPoolTest {

    public static void main(String[] args){
        ScheduledExecutorService scheduledExecutorService= Executors.newScheduledThreadPool(10);

        /**
         *      * Submits a Runnable task for execution and returns a Future
         *      * representing that task. The Future's {@code get} method will
         *      * return {@code null} upon <em>successful</em> completion.
         */
        /*
            scheduledExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("submit task");
            }
        });*/

        // Submits a one-shot task that becomes enabled after the given delay.
/*        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("schedule task");
            }
        }, 5, TimeUnit.SECONDS);*/

        /**
         *      * Submits a periodic action that becomes enabled first after the
         *      * given initial delay, and subsequently with the given period;
         *      * that is, executions will commence after
         *      * {@code initialDelay}, then {@code initialDelay + period}, then
         *      * {@code initialDelay + 2 * period}, and so on.
         *      *
         *      * <p>The sequence of task executions continues indefinitely until
         *      * one of the following exceptional completions occur:
         *      * <ul>
         *      * <li>The task is {@linkplain Future#cancel explicitly cancelled}
         *      * via the returned future.
         *      * <li>The executor terminates, also resulting in task cancellation.
         *      * <li>An execution of the task throws an exception.  In this case
         *      * calling {@link Future#get() get} on the returned future will throw
         *      * {@link ExecutionException}, holding the exception as its cause.
         *      * </ul>
         *      * Subsequent executions are suppressed.  Subsequent calls to
         *      * {@link Future#isDone isDone()} on the returned future will
         *      * return {@code true}.
         *      *
         *      * <p>If any execution of this task takes longer than its period, then
         *      * subsequent executions may start late, but will not concurrently
         *      * execute.
         *
         */
        //固定频率
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("scheduleAtFixedRate task");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1, 5, TimeUnit.SECONDS);

        /**
         *      * Submits a periodic action that becomes enabled first after the
         *      * given initial delay, and subsequently with the given delay
         *      * between the termination of one execution and the commencement of
         *      * the next.
         *      *
         *      * <p>The sequence of task executions continues indefinitely until
         *      * one of the following exceptional completions occur:
         *      * <ul>
         *      * <li>The task is {@linkplain Future#cancel explicitly cancelled}
         *      * via the returned future.
         *      * <li>The executor terminates, also resulting in task cancellation.
         *      * <li>An execution of the task throws an exception.  In this case
         *      * calling {@link Future#get() get} on the returned future will throw
         *      * {@link ExecutionException}, holding the exception as its cause.
         *      * </ul>
         *      * Subsequent executions are suppressed.  Subsequent calls to
         *      * {@link Future#isDone isDone()} on the returned future will
         *      * return {@code true}.
         */
        // 固定时延
 /*       scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("scheduleWithFixedDelay task");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },1, 5, TimeUnit.SECONDS);*/


    }
}
