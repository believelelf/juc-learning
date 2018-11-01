package com.weiquding.jcip.examples;

/**
 * 如果内置锁不是可重入的，那么这段代码将发生死锁
 */
public class Widget {

    public synchronized void doSomething(){

    }

    public class LoggingWidget extends Widget{

        public synchronized void doSomething(){
            System.out.println(toString() + ": calling doSomething");
            // 锁的重入，基于同一内置锁
            // 重入意味着锁的操作粒度是基于线程，而不是"调用”
            // 重入的一种实现方法是，为每个锁关联一个获取计数值和一个所有者线程
            super.doSomething();
        }
    }


}
