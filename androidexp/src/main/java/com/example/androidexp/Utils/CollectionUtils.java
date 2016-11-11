package com.example.androidexp.Utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by POST on 2016/11/11.
 */
public class CollectionUtils {

    /**
     * is null or its size is 0
     * @param c
     * @param <V>
     * @return
     */
    public static <V> boolean isEmpty(Collection<V> c) {
        return (c == null || c.size() == 0);
    }

    public static <V> boolean isEmpty(List<V> list) {
        return (list == null || list.size() == 0);
    }

    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return (map == null || map.size() == 0);
    }

    /**
     * join map
     *
     * @param map
     * @return
     */
    public static String mapToJson(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }

        StringBuilder paras = new StringBuilder();
        paras.append("{");
        Iterator<Map.Entry<String, String>> ite = map.entrySet().iterator();
        while (ite.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) ite.next();
            paras.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\"");
            if (ite.hasNext()) {
                paras.append(",");
            }
        }
        paras.append("}");
        return paras.toString();
    }
}
