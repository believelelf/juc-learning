package com.weiquding.jcip.examples;

/**
 * 隐式地使this引用逸出
 * 1、当ThisEscape发布EventListener时，也隐含地发布了ThisEscape实例本身。因为内部类实例中包含了外部实例ThisEscape实例的隐含引用
 * 2、当且仅当构造函数返回时，对象才处于可预测的和一致性的状态。因此，当从构造函数中发布对象时，只是发布了一个尚未构造完成的对象。
 *
 * @author wubai
 * @date 2018/10/31 22:14
 */
public class ThisEscape {

    public ThisEscape(EventSource source){
        source.registerListener(new EventListener(){
            @Override
            public void onEvent(Event event){
                doSomething(event);
            }
        });
    }

    private void doSomething(Event event) {
        // do something
    }

    interface EventSource{
        void registerListener(EventListener eventListener);
    }
    interface EventListener{
       void onEvent(Event event);
    }
    interface Event{

    }

}
