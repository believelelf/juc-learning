package net.jcip.examples;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * PersonSet
 * <p/>
 * Using confinement to ensure thread safety
 * 使用封闭机制来确保线程安全
 * PersonSet说明了如何通过封闭和加锁等机制使一个类成为线程安全的（即使这个类的状态变量不是线程安全的）。
 * PersonSet的状态是由HashSet来管理的，而HashSet并非线程安全的。但由于mySet是私有的并且不逸出，因此HashSet被封闭在PersonSet中。
 * 唯一能访问mySet的代码路径是addPerson与containsPerson,在执行它们时都要获得PersonSet上的锁，PersonSet的状态完全由它的内置锁来保护。
 * 因而PersonSet是一个线程安全的类。
 * @author Brian Goetz and Tim Peierls
 */

@ThreadSafe
public class PersonSet {
    @GuardedBy("this") private final Set<Person> mySet = new HashSet<Person>();

    public synchronized void addPerson(Person p) {
        mySet.add(p);
    }

    public synchronized boolean containsPerson(Person p) {
        return mySet.contains(p);
    }

    interface Person {
    }
}
