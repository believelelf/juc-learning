package net.jcip.examples;

import net.jcip.annotations.Immutable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 * 在可变对象基础上构建的不可变类
 * 1、stooges是一个可变的状态，但其在构造完成后无法对其进行修改。
 * 2、stooges是一个final域，无法重新赋值。
 * 3、ThreeStooges实例构造过程中this没有逸出。
 * 对象不变性三条件满足。
 * ThreeStooges
 * <p/>
 * Immutable class built out of mutable underlying objects,
 * demonstration of candidate for lock elision
 *
 * @author Brian Goetz and Tim Peierls
 */
@Immutable
 public final class ThreeStooges {
    private final Set<String> stooges = new HashSet<String>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }

    public String getStoogeNames() {
        List<String> stooges = new Vector<String>();
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
        return stooges.toString();
    }
}
