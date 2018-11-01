package net.jcip.examples;

/**
 * 在没有足够同步的情况下发布对象，非线程安全
 * StuffIntoPublic
 * <p/>
 * Unsafe publication
 * 将对象引用保存到公有域中，由于存在可见性问题，其它线程看到的Holder对象将处于不一致的状态。
 * 即便在该对象的构造函数中已经正确地构建了不变性条件。这种不正确的发布导致其他线程看到尚未创建完成的对象。
 *
 * @author Brian Goetz and Tim Peierls
 */
public class StuffIntoPublic {
    // 可能为null,可以已经初始化，由于不保证可见性，会有失效值问题。
    public Holder holder;

    public void initialize() {
        holder = new Holder(42);
    }
}
