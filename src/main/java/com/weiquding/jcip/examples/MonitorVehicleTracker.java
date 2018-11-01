package com.weiquding.jcip.examples;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于监视器的车辆追踪器
 * 虽然类MutablePoint不是线程安全的，但追踪类是线程安全的。它所包含的Map对象和可变的Point对象都未曾发布。
 * 当需要返回车辆的位置时，通过MutablePoint拷贝构造函数或者deepCopy方法来复制正确的值，从而生成一个新的Map对象，
 * 并且该对象的值与原有Map对象的Key值和Value值都相同。
 * @author wubai
 * @date 2018/11/1 21:33
 */
@ThreadSafe
public class MonitorVehicleTracker {

    @GuardedBy("this")
    private final Map<String ,MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations){
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations(){
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id){
        MutablePoint loc = locations.get(id);
        return loc == null ? null : new MutablePoint(loc);
    }

    public synchronized void setLocation(String id, int x, int y){
        MutablePoint loc = locations.get(id);
        if(loc == null){
            throw new IllegalArgumentException("No such ID: " + id);
        }
        loc.x = x;
        loc.y = y;
    }



    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m){
        Map<String, MutablePoint> result = new HashMap<String, MutablePoint>();
        // 进行深copy
        for(String id : result.keySet()){
            result.put(id, new MutablePoint(m.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }
}
