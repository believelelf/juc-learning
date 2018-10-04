package com.weiquding.juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 线程创建的方式之一：Callable
 * @author wubai
 * @date 2018/10/3 22:40
 */
public class Callable1 implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("===>Thread:Callable implement start....");
        return "callable";
    }

    public static void main(String[] args){
        new Thread(new FutureTask<String>(new Callable1())).start();
    }
}
