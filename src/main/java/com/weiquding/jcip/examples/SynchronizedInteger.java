package com.weiquding.jcip.examples;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * 可见性-失效数据示例
 * 通过对get与set方法进行同步，可以使MutableInteger完成一个线程安全类。
 * 注意：仅对set方法进行同步是不够的，调用get的线程仍会看见失效值。
 * @author wubai
 * @date 2018/10/31 21:54
 */
@ThreadSafe
public class SynchronizedInteger {

    @GuardedBy("this")
    private int value;

    public synchronized void set(int value){
        this.value = value;
    }

    public synchronized int get(){
        return this.value;
    }

}
