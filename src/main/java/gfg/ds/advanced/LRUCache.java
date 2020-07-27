package gfg.ds.advanced;

import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 16-07-2020 00:35
 **/
public class LRUCache {
    private int capacity;
    private Set<Integer> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        //The use of linked has set maintains the insertion order and help in determining the least recently used.
        this.cache = new LinkedHashSet<>();
    }

    public void refer(int key) {
        cache.remove(key);
        if (cache.size() == capacity) {
            int leastRecentlyUsed = cache.iterator().next();
            cache.remove(leastRecentlyUsed);
        }
        cache.add(key);
    }

    public List<Integer> getContents() {
        return new ArrayList<>(cache);
    }
}
