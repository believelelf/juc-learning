package com.weiquding.jcip.examples;

import net.jcip.annotations.NotThreadSafe;

/**
 * 延迟初始化的竞态条件
 * ==》先检查后执行
 *
 */
@NotThreadSafe
public class LazyInitRace {

    private ExpensiveObject expensiveObject = null;

    public ExpensiveObject getInstance(){
        if(expensiveObject == null){
            expensiveObject = new ExpensiveObject();
        }
        return expensiveObject;
    }

}

class ExpensiveObject {

}