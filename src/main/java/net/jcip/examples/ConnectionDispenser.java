package net.jcip.examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 使用ThreadLocal来维护线程的封闭性
 * ConnectionDispenser
 * <p/>
 * Using ThreadLocal to ensure thread confinement
 * ThreadLocal能使线程中的某个值与保存值的对象关联起来。
 * ThreadLocal提供了get与set方法等访问接口或方法，这些方法为每个使用该变量的线程都存有一份独立的副本。
 *
 * @author Brian Goetz and Tim Peierls
 */
public class ConnectionDispenser {
    static String DB_URL = "jdbc:mysql://localhost/mydatabase";

    private ThreadLocal<Connection> connectionHolder
            = new ThreadLocal<Connection>() {
                public Connection initialValue() {
                    try {
                        return DriverManager.getConnection(DB_URL);
                    } catch (SQLException e) {
                        throw new RuntimeException("Unable to acquire Connection, e");
                    }
                };
            };

    public Connection getConnection() {
        return connectionHolder.get();
    }
}
