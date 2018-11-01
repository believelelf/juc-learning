package com.weiquding.jcip.examples;

import net.jcip.annotations.NotThreadSafe;

/**
 * 与Java.awt.Point类似的可变Point类
 * @author wubai
 * @date 2018/11/1 21:29
 */
@NotThreadSafe
public class MutablePoint {

    // 状态逸出
    public int x, y;

    public MutablePoint(){
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint point){
        this.x = point.x;
        this.y = point.y;
    }

}
