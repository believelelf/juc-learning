package com.weiquding.jcip.examples;

import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 将线程安全委托给ConcurrentHashMap
 * DelegatingVehicleTracker没有使用显示的同步，所有对状态的访问都是由ConcurrentHashMap来管理，而且Map所有的键与值都是不可变的。
 * @author wubai
 * @date 2018/11/1 22:02
 */
@ThreadSafe
public class DelegatingVehicleTracker {

    private final ConcurrentMap<String, Point> locations;
    private final Map<String,Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        this.locations = new ConcurrentHashMap<String, Point>(points);
        this.unmodifiableMap = Collections.unmodifiableMap(points);
    }

    /**
     * 返回一个不可修改但却实时的车辆位置视图。
     * （unmodifiableMap内维护了原map的引用，原map是可以修改的，an unmodifiable view of the specified map.）
     * 如果线程A调用getLocations,而线程B在随后调用setLocation修改了某些点的位置，那么在返回给线程A的Map中将反映出这些变化。
     * @return
     */
    public Map<String, Point> getLocations(){
        return unmodifiableMap;
    }

    /**
     * 如果需要一个不发生变化的视图，那么getLocations可以返回对locations这个Map对象的一个㳀拷贝（Shallow Copy，key,value仍维护对原对象的引用)
     * 由于Map内容（key只读，及value不可变）是不可变的，因此只需复制Map的结构，而不用复制它的内容。
     * @return
     */
    public Map<String, Point> getCopyLocations(){
        return Collections.unmodifiableMap(new HashMap<String, Point>(locations));
    }

    public Point getLocation(String id){
        return locations.get(id);
    }

    /**
     * 由并发容器保证线程安全
     * @param id
     * @param x
     * @param y
     */
    public void setLocation(String id, int x, int y){
        if(locations.replace(id, new Point(x, y)) == null){
            throw new IllegalStateException("invalid vehicle name: " + id);
        }
    }
}
