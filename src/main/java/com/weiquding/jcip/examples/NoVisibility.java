package com.weiquding.jcip.examples;

/**
 * 可见性
 * 在没有同步的情况下共享变量
 * 主线程与读线程将访问共享变量ready和number。
 * 由于没有使用足够的同步机制，因此无法保证主线程写入的ready值和number值对于读线程来说是可见的。
 * @author wubai
 * @date 2018/10/28 10:09
 */
public class NoVisibility {

    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread{

        /**
         * 对于读线程
         * 1、可能永远看不到ready值，死循环。
         * 2、可能读到ready的值，不进行循环
         * 3、可能输出number=0,因为读取到ready，但没有读取number.==》没有同步的情况下，会发生重排序。
         *      当主线程首先写入number，然后在没有同步的情况下写入ready,那么读线程看到的顺序可能与写入的顺序完全相反。
         * 4、可能输出number=42,两个值读取到
         * 5、
         */
        @Override
        public void run(){
            while (!ready){
                // ==> true 重排序
                Thread.yield();
                System.out.println(number);
            }
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready =  true;
    }
}
