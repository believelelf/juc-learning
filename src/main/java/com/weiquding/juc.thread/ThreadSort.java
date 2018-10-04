package com.weiquding.juc.thread;

/**
 * 线程运行顺序保证
 * Thread.join()
 * @author wubai
 * @date 2018/10/3 23:08
 */
public class ThreadSort {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread1();
        thread1.start();
        // Thread.join() Waits for this thread to die.
        // 阻塞调用者
        thread1.join();
        System.out.println("--------Thread1 die------------");

        Thread thread2 = new Thread2();
        thread2.start();
        thread2.join();
        System.out.println("--------Thread2 die------------");

        Thread thread3 = new Thread3();
        thread3.start();
        thread3.join();
        System.out.println("--------Thread3 die------------");


    }

/*
示例1
==>thread 1
--------Thread1 die------------
==>Thread 2
--------Thread2 die------------
==>Thread 3
--------Thread3 die------------
*/


/*
示例2
==>thread 1
====>thread 1 sleep end
--------Thread1 die------------
==>Thread 2
--------Thread2 die------------
==>Thread 3
--------Thread3 die------------

jstack VMID
主线程main,等待Thread1结束
"main" #1 prio=5 os_prio=0 tid=0x000001dad10c5800 nid=0x18c8 in Object.wait()  [0x00000035835ff000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(java.base@10.0.2/Native Method)
        - waiting on <0x00000006d07fcff0> (a com.weiquding.juc.thread.ThreadSort$Thread1)
        at java.lang.Thread.join(java.base@10.0.2/Thread.java:1353)
        - waiting to re-lock in wait() <0x00000006d07fcff0> (a com.weiquding.juc.thread.ThreadSort$Thread1)
        at java.lang.Thread.join(java.base@10.0.2/Thread.java:1427)
        at com.weiquding.juc.thread.ThreadSort.main(ThreadSort.java:16)

"Thread-0" #15 prio=5 os_prio=0 tid=0x000001dafa2b9000 nid=0x2064 waiting on condition  [0x00000035850ff000]
    java.lang.Thread.State: TIMED_WAITING (sleeping)
    at java.lang.Thread.sleep(java.base@10.0.2/Native Method)
    at com.weiquding.juc.thread.ThreadSort$Thread1.run(ThreadSort.java:49)
*/



    static class Thread1 extends Thread{

        @Override
        public void run() {
            System.out.println("==>thread 1");
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("====>thread 1 sleep end");
        }
    }

    static class Thread2 extends Thread{
        @Override
        public void run() {
            System.out.println("==>Thread 2");
        }
    }

    static class Thread3 extends Thread{
        @Override
        public void run() {
            System.out.println("==>Thread 3");
        }
    }
}
