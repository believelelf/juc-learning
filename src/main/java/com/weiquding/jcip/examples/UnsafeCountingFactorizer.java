package com.weiquding.jcip.examples;

import net.jcip.annotations.NotThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * 在没有同步的情况下统计已处理请求的数量的Servlet
 * 竞态条件：
 * 读取-修改-写入
 */
@NotThreadSafe
public class UnsafeCountingFactorizer extends GenericServlet implements Servlet {

    private long count = 0;

    public long getCount(){
        return count;
    }

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        count++;
        ecodeIntoResponse(res, factors);
    }

    private void ecodeIntoResponse(ServletResponse res, BigInteger[] factors) {

    }

    private BigInteger[] factor(BigInteger i) {
        return new BigInteger[]{};
    }

    private BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }


}
