package cn.mrmj.utils;

import java.util.*;
import java.util.Map.Entry;


/**
 * create by: mrmj
 * description: collection集合工具类，咱也不知道怎么用
 * create time: 2019/11/12 18:46
 */
public class CollectionUtil {
    
    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> merge(Map<String, T>... maps) {
        int length = 0;
        if (null == maps || 0 == (length = maps.length)) {
            return new HashMap<String, T>();
        }
        Map<String, T> base = maps[0];
        for (int i = 1; i < length; i++) {
            Map<String, T> other = maps[i];
            if (null == other) {
                continue;
            }
            for (Entry<String, T> entry : other.entrySet()) {
                base.put(entry.getKey(), entry.getValue());
            }
        }
        return base;
    }
    
    public static <T> Map<String, T> merge(Collection<Map<String, T>> maps) {
        if (null == maps || maps.isEmpty()) {
            return new HashMap<String, T>();
        }
        Iterator<Map<String, T>> mapsIt = maps.iterator();
        Map<String, T> base = null;
        while (mapsIt.hasNext()) {
            Map<String, T> map = mapsIt.next();
            if (null == map) {
                continue;
            }
            if (null == base) {
                base = map;
            } else {
                for (Entry<String, T> entry : map.entrySet()) {
                    base.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return base;
    }
    
    public static Map<String, Object> merge(Map<String, Object> base, Properties prop) {
        if (null == base) {
            base = new HashMap<String, Object>();
        }
        if (null != prop && !prop.isEmpty()) {
            for (Entry<Object, Object> entry : prop.entrySet()) {
                base.put((String) entry.getKey(), entry.getValue());
            }
        }
        return base;
    }
    
    public static <T> Map<String, T> subMap(Map<String, T> sourceMap, String... keys) {
        Map<String, T> subMap = new HashMap<String, T>();
        if (null == sourceMap || sourceMap.isEmpty()) {
            return subMap;
        }
        if (null == keys || 0 == keys.length) {
            subMap.putAll(sourceMap);
        } else {
            for (String key : keys) {
                if (sourceMap.containsKey(key)) {
                    subMap.put(key, sourceMap.get(key));
                }
            }
        }
        return subMap;
    }
    
    public static <T> void remove(Map<String, T> map, String... keys) {
        if (null != keys && 0 < keys.length) {
            for (String key : keys) {
                map.remove(key);
            }
        }
    }
    
    /**
     * 判断item是否存在与collection中
     * */
    public static <T> boolean inCollection(T item, T[] collection) {
        for (T collectionItem : collection) {
            if (item.equals(collectionItem)) {
                return true;
            }
        }
        return false;
    }
    
    public static Map<String, Object> transKey(Map<String, Object> map, String preKey, String afterKey) {
        if (null == map || map.isEmpty()) {
            return null;
        }
        if (map.containsKey(preKey) && !map.containsKey(afterKey)) {
            map.put(afterKey, map.get(preKey));
        }
        return map;
    }
    
    public static void merge(Map<String, Object> dest, Map<String, Object> src, boolean cover) {
        if (null == dest) {
            dest = new HashMap<>();
        }
        if (null == src) {
            src = new HashMap<>();
        }
        for (Entry<String, Object> entry : src.entrySet()) {
            if (!dest.containsKey(entry.getKey()) || cover) {
                dest.put(entry.getKey(), entry.getValue());
            }
        }
    }
}
