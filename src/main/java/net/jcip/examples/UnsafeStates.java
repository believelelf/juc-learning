package net.jcip.examples;

/**
 * UnsafeStates
 * <p/>
 * Allowing internal mutable state to escape
 * 使内部可变状态逸出
 * 1、如果从非私有方法中返回一个引用，那么同样会发布返回的对象。
 * 2、因为任何调用者都可以修改这个数组的内部，非线程安全。
 * @author Brian Goetz and Tim Peierls
 */
class UnsafeStates {
    private String[] states = new String[]{
        "AK", "AL" /*...*/
    };

    public String[] getStates() {
        return states;
    }
}
