package com.weiquding.juc.thread;

/**
 * @author wubai
 * @date 2018/10/3 22:37
 */
public class Runnable1 implements Runnable{

    @Override
    public void run() {
        System.out.println("===>Thread:Runable implement start....");
    }

    public static void main(String args[]){
        new Thread(new Runnable1()).start();
    }
}
