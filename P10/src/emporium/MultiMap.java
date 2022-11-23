package emporium;

import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.ArrayList;

public class MultiMap<K,V> {
    public void put(K key, V value){
        if (map.get(key) == null) {
            map.put(key, new HashSet<V>());
        }
        ((HashSet<V>)map.get(key)).add(value);
    }
    public Object[] get(K key){
        if (map.get(key) == null) {
            Object[] tmp = null;
            return tmp;
        }
        //System.out.print("" + map.get(key).toArray());
        return map.get(key).toArray();
    }

    private HashMap<K, HashSet<V>> map = new HashMap<>();
}