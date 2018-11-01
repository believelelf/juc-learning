package com.weiquding.jcip.examples;

import net.jcip.annotations.NotThreadSafe;

/**
 * 可变见-失效数据
 * 非线程安全的可变整数类
 * MutableInteger类中的get与set都是在没有同步的情况下访问value的。
 * 与其它问题相比，失效值的问题更容易出现。
 * @author wubai
 * @date 2018/10/31 21:49
 */
@NotThreadSafe
public class MutableInteger {

    private int value;

    public int get(){
        return this.value;
    }
    public void set(int value){
        this.value = value;
    }

}
