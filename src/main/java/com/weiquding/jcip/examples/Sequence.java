package com.weiquding.jcip.examples;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * 线程安全的数值序列生成器
 */
@ThreadSafe
public class Sequence {

    @GuardedBy("this")
    private int value;

    /**
     * 同步代码块，使用内置锁this
     * 同步代码块包括两个部分：
     * 一个作为锁的对象引用，一个作为由这个锁保护的代码块
     * @return
     */
    public synchronized int getValue(){
        return value++;
    }


}
