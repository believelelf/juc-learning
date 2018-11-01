package net.jcip.examples;

import net.jcip.annotations.*;

/**
 * Counter
 * <p/>
 * Simple thread-safe counter using the Java monitor pattern
 * 使用java监视器模式的线程安全计数器
 * 要分析对象的状态，首先从对象的域开始。如果对象中所有的域都是基本类型的变量，那么这些域将构成对象的全部状态。
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public final class Counter {
    @GuardedBy("this") private long value = 0;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment() {
        if (value == Long.MAX_VALUE){
            throw new IllegalStateException("counter overflow");
        }
        return ++value;
    }
}
