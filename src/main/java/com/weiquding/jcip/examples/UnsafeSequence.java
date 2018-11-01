package com.weiquding.jcip.examples;

import net.jcip.annotations.NotThreadSafe;

/**
 * 非线程安全的数值序列生成器
 */
@NotThreadSafe
public class UnsafeSequence {

    private int value;

    /**
     * 竞态条件：读取-修改-写入
     * 包含三个操作：读取value;将value加1;并将计算结果写入value
     * @return
     */
    public int getNext(){
        return value++;
    }
}
