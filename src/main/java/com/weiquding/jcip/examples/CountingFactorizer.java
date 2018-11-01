package com.weiquding.jcip.examples;

import net.jcip.annotations.ThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 使用AtomicLong类型的变量来统计已处理请求的数量
 * 原子类，涉及个单个变量的不变性条件
 */
@ThreadSafe
public class CountingFactorizer extends GenericServlet implements Servlet {

    private AtomicLong count = new AtomicLong(0);

    public long getCount(){
        return count.get();
    }

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        count.incrementAndGet();
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
