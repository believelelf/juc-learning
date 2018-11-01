package com.weiquding.jcip.examples;

import net.jcip.annotations.NotThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 该Servlet在没有足够原子性保证的情况下对其最近计算的结果进行缓存
 * 不变性条件：在lastFactors中缓存的因数之积应该等于在lastNumber中缓存的数值。
 * 当在不变性条件中涉及多个变量时，各个变量之间并不彼此独立的，而是某个变量的值会对其变量的值产生约束。因此，当更新某个变量时，需要在同一个原子操作中对其他变量同时进行更新。
 */
@NotThreadSafe
public class UnsafeCachingFactorizer extends GenericServlet implements Servlet {

    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<BigInteger>();

    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<BigInteger[]>();

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        if(i.equals(lastNumber.get())){
            encodeIntoResponse(res, lastFactors.get());
        }else {
            BigInteger[] factors = factor(i);
            lastNumber.set(i);
            lastFactors.set(factors);
            encodeIntoResponse(res, factors);
        }
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[] { i };
    }

}
