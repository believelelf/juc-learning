package com.weiquding.juc.thread;

import java.util.concurrent.TimeUnit;

/**
 *  查看线程状态
 * jps -l 查看java虚拟机进程id
 * jstack VMID 查看线程状态
 * @author wubai
 * @date 2018/10/3 22:50
 */
public class ThreadState {

    public static void main(String[] args){
        // "TimeWaitingThread" #15 prio=5 os_prio=0 tid=0x0000020cbfe3a800 nid=0xcb4 waiting on condition[0x000000b5522ff000]
        //java.lang.Thread.State: TIMED_WAITING (sleeping)
        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
        //"WaitingThead" #16 prio=5 os_prio=0 tid=0x0000020cbfe3d800 nid=0x1bb4 in Object.wait()  [0x000000b5523fe000]
        //java.lang.Thread.State: WAITING (on object monitor)
        new Thread(new Waiting(), "WaitingThead").start();
        //"BlockedThread-1" #17 prio=5 os_prio=0 tid=0x0000020cbfe3e000 nid=0x245c waiting on condition  [0x000000b5524fe000]
        //java.lang.Thread.State: TIMED_WAITING (sleeping)
        new Thread(new Blocked(), "BlockedThread-1").start();
        // "BlockedThread-2" #18 prio=5 os_prio=0 tid=0x0000020cbfe3f000 nid=0x1e90 waiting for monitor entry  [0x000000b5525ff000]
        // java.lang.Thread.State: BLOCKED (on object monitor)
        new Thread(new Blocked(), "BlockedThread-2").start();
    }


    static class Waiting implements Runnable{

        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }


    static class TimeWaiting implements Runnable{

        @Override
        public void run() {
            while (true){
                second(100);
            }
        }

    }

    static class Blocked implements Runnable{

        @Override
        public void run() {
            synchronized (Blocked.class){
                while (true){
                    second(100);
                }
            }
        }
    }


    private static void second(int timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
F:\java\idea_workspaces\juc-learning>jps -l
        10656 org.jetbrains.jps.cmdline.Launcher
        12176 sun.tools.jps.Jps
        1140
        16020 org.jetbrains.idea.maven.server.RemoteMavenServer
        6392 com.weiquding.juc.thread.ThreadState
*/

/*
F:\java\idea_workspaces\juc-learning>jstack 6392
        2018-10-03 23:02:09
        Full thread dump Java HotSpot(TM) 64-Bit Server VM (10.0.2+13 mixed mode):

        Threads class SMR info:
        _java_thread_list=0x0000020cbfe39b40, length=18, elements={
        0x0000020cbf9ef000, 0x0000020cbf9f1800, 0x0000020cbfa49000, 0x0000020cbfa4a800,
        0x0000020cbfa4d800, 0x0000020cbfa50000, 0x0000020cbfabc800, 0x0000020cbfac4800,
        0x0000020cbfdcf800, 0x0000020cbfda2800, 0x0000020cbfcf6000, 0x0000020cbfdf0800,
        0x0000020cbfe0d800, 0x0000020cbfe3a800, 0x0000020cbfe3d800, 0x0000020cbfe3e000,
        0x0000020cbfe3f000, 0x0000020c98f9b000
        }

        "Reference Handler" #2 daemon prio=10 os_prio=2 tid=0x0000020cbf9ef000 nid=0x26b0 waiting on con
        dition  [0x000000b5514fe000]
        java.lang.Thread.State: RUNNABLE
        at java.lang.ref.Reference.waitForReferencePendingList(java.base@10.0.2/Native Method)
        at java.lang.ref.Reference.processPendingReferences(java.base@10.0.2/Reference.java:174)

        at java.lang.ref.Reference.access$000(java.base@10.0.2/Reference.java:44)
        at java.lang.ref.Reference$ReferenceHandler.run(java.base@10.0.2/Reference.java:138)

        "Finalizer" #3 daemon prio=8 os_prio=1 tid=0x0000020cbf9f1800 nid=0x2f24 in Object.wait()  [0x00
        0000b5515ff000]
        java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(java.base@10.0.2/Native Method)
        - waiting on <0x00000006d0909410> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@10.0.2/ReferenceQueue.java:151)
        - waiting to re-lock in wait() <0x00000006d0909410> (a java.lang.ref.ReferenceQueue$Lock
        )
        at java.lang.ref.ReferenceQueue.remove(java.base@10.0.2/ReferenceQueue.java:172)
        at java.lang.ref.Finalizer$FinalizerThread.run(java.base@10.0.2/Finalizer.java:216)

        "Signal Dispatcher" #4 daemon prio=9 os_prio=2 tid=0x0000020cbfa49000 nid=0x3ed4 runnable  [0x00
        00000000000000]
        java.lang.Thread.State: RUNNABLE

        "Attach Listener" #5 daemon prio=5 os_prio=2 tid=0x0000020cbfa4a800 nid=0x18c8 waiting on condit
        ion  [0x0000000000000000]
        java.lang.Thread.State: RUNNABLE

        "C2 CompilerThread0" #6 daemon prio=9 os_prio=2 tid=0x0000020cbfa4d800 nid=0x12fc waiting on con
        dition  [0x0000000000000000]
        java.lang.Thread.State: RUNNABLE
        No compile task

        "C2 CompilerThread1" #7 daemon prio=9 os_prio=2 tid=0x0000020cbfa50000 nid=0x3da4 waiting on con
        dition  [0x0000000000000000]
        java.lang.Thread.State: RUNNABLE
        No compile task

        "C1 CompilerThread2" #8 daemon prio=9 os_prio=2 tid=0x0000020cbfabc800 nid=0x2f8c waiting on con
        dition  [0x0000000000000000]
        java.lang.Thread.State: RUNNABLE
        No compile task

        "Sweeper thread" #9 daemon prio=9 os_prio=2 tid=0x0000020cbfac4800 nid=0x3b58 runnable  [0x00000
        00000000000]
        java.lang.Thread.State: RUNNABLE

        "JDWP Transport Listener: dt_socket" #10 daemon prio=10 os_prio=0 tid=0x0000020cbfdcf800 nid=0x3
        594 runnable  [0x0000000000000000]
        java.lang.Thread.State: RUNNABLE

        "JDWP Event Helper Thread" #11 daemon prio=10 os_prio=0 tid=0x0000020cbfda2800 nid=0x19bc runnab
        le  [0x0000000000000000]
        java.lang.Thread.State: RUNNABLE

        "JDWP Command Reader" #12 daemon prio=10 os_prio=0 tid=0x0000020cbfcf6000 nid=0xfe8 runnable  [0
        x0000000000000000]
        java.lang.Thread.State: RUNNABLE

        "Common-Cleaner" #13 daemon prio=8 os_prio=1 tid=0x0000020cbfdf0800 nid=0x1d2c in Object.wait()
        [0x000000b551ffe000]
        java.lang.Thread.State: TIMED_WAITING (on object monitor)
        at java.lang.Object.wait(java.base@10.0.2/Native Method)
        - waiting on <0x00000006d070f7c8> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@10.0.2/ReferenceQueue.java:151)
        - waiting to re-lock in wait() <0x00000006d070f7c8> (a java.lang.ref.ReferenceQueue$Lock
        )
        at jdk.internal.ref.CleanerImpl.run(java.base@10.0.2/CleanerImpl.java:148)
        at java.lang.Thread.run(java.base@10.0.2/Thread.java:844)
        at jdk.internal.misc.InnocuousThread.run(java.base@10.0.2/InnocuousThread.java:134)

        "Service Thread" #14 daemon prio=9 os_prio=0 tid=0x0000020cbfe0d800 nid=0x28a0 runnable  [0x0000
        000000000000]
        java.lang.Thread.State: RUNNABLE

        "TimeWaitingThread" #15 prio=5 os_prio=0 tid=0x0000020cbfe3a800 nid=0xcb4 waiting on condition
        [0x000000b5522ff000]
        java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(java.base@10.0.2/Native Method)
        at java.lang.Thread.sleep(java.base@10.0.2/Thread.java:340)
        at java.util.concurrent.TimeUnit.sleep(java.base@10.0.2/TimeUnit.java:403)
        at com.weiquding.juc.thread.ThreadState.second(ThreadState.java:64)
        at com.weiquding.juc.thread.ThreadState.access$000(ThreadState.java:10)
        at com.weiquding.juc.thread.ThreadState$TimeWaiting.run(ThreadState.java:43)
        at java.lang.Thread.run(java.base@10.0.2/Thread.java:844)

        "WaitingThead" #16 prio=5 os_prio=0 tid=0x0000020cbfe3d800 nid=0x1bb4 in Object.wait()  [0x00000
        0b5523fe000]
        java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(java.base@10.0.2/Native Method)
        - waiting on <0x00000006d0793288> (a java.lang.Class for com.weiquding.juc.thread.Thread
        State$Waiting)
        at java.lang.Object.wait(java.base@10.0.2/Object.java:328)
        at com.weiquding.juc.thread.ThreadState$Waiting.run(ThreadState.java:27)
        - waiting to re-lock in wait() <0x00000006d0793288> (a java.lang.Class for com.weiquding
        .juc.thread.ThreadState$Waiting)
        at java.lang.Thread.run(java.base@10.0.2/Thread.java:844)

        "BlockedThread-1" #17 prio=5 os_prio=0 tid=0x0000020cbfe3e000 nid=0x245c waiting on condition  [
        0x000000b5524fe000]
        java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(java.base@10.0.2/Native Method)
        at java.lang.Thread.sleep(java.base@10.0.2/Thread.java:340)
        at java.util.concurrent.TimeUnit.sleep(java.base@10.0.2/TimeUnit.java:403)
        at com.weiquding.juc.thread.ThreadState.second(ThreadState.java:64)
        at com.weiquding.juc.thread.ThreadState.access$000(ThreadState.java:10)
        at com.weiquding.juc.thread.ThreadState$Blocked.run(ThreadState.java:55)
        - locked <0x00000006d0794740> (a java.lang.Class for com.weiquding.juc.thread.ThreadStat
        e$Blocked)
        at java.lang.Thread.run(java.base@10.0.2/Thread.java:844)

        "BlockedThread-2" #18 prio=5 os_prio=0 tid=0x0000020cbfe3f000 nid=0x1e90 waiting for monitor ent
        ry  [0x000000b5525ff000]
        java.lang.Thread.State: BLOCKED (on object monitor)
        at com.weiquding.juc.thread.ThreadState$Blocked.run(ThreadState.java:55)
        - waiting to lock <0x00000006d0794740> (a java.lang.Class for com.weiquding.juc.thread.T
        hreadState$Blocked)
        at java.lang.Thread.run(java.base@10.0.2/Thread.java:844)

        "DestroyJavaVM" #19 prio=5 os_prio=0 tid=0x0000020c98f9b000 nid=0x1678 waiting on condition  [0x
        0000000000000000]
        java.lang.Thread.State: RUNNABLE

        "VM Thread" os_prio=2 tid=0x0000020cbf9d8000 nid=0x3b84 runnable

        "GC Thread#0" os_prio=2 tid=0x0000020c98fb3800 nid=0x3b90 runnable

        "GC Thread#1" os_prio=2 tid=0x0000020c98fb4800 nid=0x36b4 runnable

        "GC Thread#2" os_prio=2 tid=0x0000020c98fb5800 nid=0x1814 runnable

        "GC Thread#3" os_prio=2 tid=0x0000020c98fb7800 nid=0x3ae8 runnable

        "G1 Main Marker" os_prio=2 tid=0x0000020c99001000 nid=0x2648 runnable

        "G1 Conc#0" os_prio=2 tid=0x0000020c99004000 nid=0x2638 runnable

        "G1 Refine#0" os_prio=2 tid=0x0000020cbcc0d800 nid=0x2900 runnable

        "G1 Refine#1" os_prio=2 tid=0x0000020cbcc0e800 nid=0x1578 runnable

        "G1 Refine#2" os_prio=2 tid=0x0000020cbcc0f800 nid=0x37cc runnable

        "G1 Refine#3" os_prio=2 tid=0x0000020cbcc10000 nid=0xf9c runnable

        "G1 Young RemSet Sampling" os_prio=2 tid=0x0000020cbcc14000 nid=0x38ac runnable
        "VM Periodic Task Thread" os_prio=2 tid=0x0000020cbfd8f000 nid=0x32c8 waiting on condition

        JNI global references: 1375


        F:\java\idea_workspaces\juc-learning>
*/
