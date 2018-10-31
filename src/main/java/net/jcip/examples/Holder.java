package net.jcip.examples;

/**
 * 由于未被正确地发布，因此这个类可能出现故障。（多线程中另一个线程在调用assertSanity时可能抛出AssertionError）
 * 抛出异常的原因不存在Holder类本身，而在于Holder类未被正确地发布。然而如果将n声明为final类型，那么Holder类将不可变，从而避免出现不正确的发布问题。
 * Holder
 * <p/>
 * Class at risk of failure if not properly published
 * 由于没有使用同步来确保对其他线程可见，因此将Holder称为“未被正确发布”。
 * 在未被正确发布的对象中存在两个问题==》
 * 1、除了发布对象的线程外，其他线程可以看到的Holder域是一个失效值，因此将看到一个空引用或者之前的旧值
 * 2、线程看到的Holder引用是最新的，但Holder状态的值却是失效的。
 *      ==》尽管在构造函数中设置的域值似乎是第一次向这些域中写入的值，因此不会有“更旧的”值被视为失效值。但Object的构造函数会在子类构造函数运行之前先将默认值写入所有域。因此，某个域的默认值可能被视为失效值。
 * @author Brian Goetz and Tim Peierls
 */
public class Holder {
    // n是可变的状态。可以声明为final类型，final类型或不可变，在java内存模型中，final域能确保初始化过程的安全。
    // 非final 在Object初始化时n为0,
    private int n;

    public Holder(int n) {
        this.n = n;
    }

    public void assertSanity() {
        // n的两次读取不一致。
        // 第一次读取到失效值，再次读取到更新值。
        if (n != n){
            throw new AssertionError("This statement is false.");
        }
    }
}
