package com.weiquding.jcip.examples;

/**
 * 使用工厂方法来防止this引用在构造过程中逸出。
 * 私有构造方法，公有静态工厂方法
 * @author wubai
 * @date 2018/10/31 22:25
 */
public class SafeListener {

    private final EventListener listener;

    private SafeListener(){
        listener = new EventListener() {
            @Override
            public void onEvent(Event e) {
                doSomething(e);
            }
        };
    }

    public static SafeListener newInstance(EventSource source){
        SafeListener safe = new SafeListener();
        //  SafeListener的发布构造完成后才发布。
        source.registerListener(safe.listener);
        return safe;
    }


    void doSomething(Event e) {
    }


    interface EventSource {
        void registerListener(EventListener e);
    }

    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {
    }
}
