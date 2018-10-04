package com.weiquding.juc.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author wubai
 * @date 2018/10/4 10:45
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Long start= System.currentTimeMillis();
        final List<Integer> l=new ArrayList<Integer>();
        final Random random=new Random();
        for(int i=0;i<10000;i++){
            Thread thread=new Thread(){
                @Override
                public void run(){
                    l.add(random.nextInt());
                }
            };
            thread.start();
            thread.join();
        }
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(l.size());

    }
}

/*    Connected to the target VM, address: '127.0.0.1:60305', transport: 'socket'
        6583
        10000
        Disconnected from the target VM, address: '127.0.0.1:60305', transport: 'socket'

        Process finished with exit code 0*/
