package com.weiquding.jcip.examples;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * 缓存最近执行因数分解的数值及其计算结果的Servlet
 * 通过缩小同步代码块的范围来提高性能
 * 要判断同步代码块的合理大小；需要在各种设计需求之间进行权衡，包括安全性（这个需求必须得到满足）、简单性、性能。
 */
@ThreadSafe
public class CachedFactorizer extends GenericServlet implements Servlet {

    @GuardedBy("this") private BigInteger lastNumber;
    @GuardedBy("this") private BigInteger[] lastFactors;
    @GuardedBy("this") private long hits;
    @GuardedBy("this") private long cacheHits;

    public synchronized long getHits(){
        return hits;
    }
    public synchronized double getCachedHitRatio(){
        return (double)cacheHits/(double)hits;
    }


    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = null;
        synchronized (this){
            hits++;
            if(i.equals(lastNumber)){
                cacheHits++;
                factors = lastFactors.clone();
            }
        }
        if(factors == null){
            factors = factor(i);
            synchronized (this){
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }
        encodeIntoResponse(res, factors);

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
