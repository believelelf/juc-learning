package com.weiquding.jcip.examples;

import net.jcip.annotations.Immutable;

/**
 * Point是一个不可变对象。
 * 1、所有域都是final的
 * 2、对象一经构建后，状态不会发生改变。
 * 3、this没有构建过程中逸出
 * @author wubai
 * @date 2018/11/1 21:59
 */
@Immutable
public class Point {

    public final int x, y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
