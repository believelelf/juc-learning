package com.weiquding.jcp.examples;

/**
 * Volatile可变量：保证可见性
 * 检查某个状态标记以判断是否退出循环
 * @author wubai
 * @date 2018/10/31 22:03
 */
public class CountingSheep {

    volatile boolean asleep;


    void tryToSleep() {
        while (!asleep){
            countSomeSheep();
        }
    }

    void countSomeSheep() {
        // One, two, three...
    }
}
