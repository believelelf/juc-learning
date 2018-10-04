package com.weiquding.juc.thread;

/**
 * 线程创建的方式之一
 * @author wubai
 * @date 2018/10/3 22:31
 */
public class Thread1 extends Thread{

    @Override
    public void run(){
        System.out.println("===>thread run....");
    }

    public static void main(String[] args){
        new Thread1().start();
    }
}
