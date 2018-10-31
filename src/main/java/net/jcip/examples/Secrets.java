package net.jcip.examples;

import java.util.*;

/**
 * Secrets
 *
 * Publishing an object
 * 1、发布对象的最简单的是将对象的引用保存到一个静态变量中。
 * 2、当发布某个对象时，可能会间接地发布其他对象。如果将一个Secret对象添加到集合knownSecrets中，那么同样会发布这个对象。
 *
 * @author Brian Goetz and Tim Peierls
 */
class Secrets {

    public static Set<Secret> knownSecrets;

    public void initialize() {
        knownSecrets = new HashSet<Secret>();
    }
}


class Secret {
}
