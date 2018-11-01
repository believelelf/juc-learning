package net.jcip.examples;

import net.jcip.annotations.*;

/**
 * PrivateLock
 * <p/>
 * Guarding state with a private lock
 * 通过一个私有锁来保护状态
 * 使用私有的锁对象而不是内置的锁（或任何其他可通过公有方法访问的锁），有许多的优点，私有的锁可以将对象封装起来。使客户代码无法得到锁。
 * 但客户代码可以通过公有方法来访问锁。
 *
 * @author Brian Goetz and Tim Peierls
 */
public class PrivateLock {
    private final Object myLock = new Object();
    @GuardedBy("myLock") Widget widget;

    void someMethod() {
        synchronized (myLock) {
            // Access or modify the state of widget
        }
    }
}
