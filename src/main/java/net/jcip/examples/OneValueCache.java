package net.jcip.examples;

import net.jcip.annotations.Immutable;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 对于数值及其因数分解结果进行缓存的不可变容器类
 * OneValueCache
 * <p/>
 * Immutable holder for caching a number and its factors
 * 对于在访问和更新多个相关变量出现的竞态条件问题，可以通过将这些变量全部保存在一个不变对象中来消除。
 * @author Brian Goetz and Tim Peierls
 */
@Immutable
public class OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger i,
                         BigInteger[] factors) {
        // BigInteger是不可变对象。Immutable arbitrary-precision integers.
        lastNumber = i;
        // 复制原数组，防止引用传递
        lastFactors = Arrays.copyOf(factors, factors.length);
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null || !lastNumber.equals(i))
            return null;
        else
            // 复制原数组，防止引用传递
            return Arrays.copyOf(lastFactors, lastFactors.length);
    }
}
