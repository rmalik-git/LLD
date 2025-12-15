package org.example;

import org.example.lruCache.LRUCache;

public class LRUCacheDemo {
    public static void main(String[] args) {
        LRUCache<String, Integer> lruCache = new LRUCache<>(3);
        lruCache.put("one", 1);
        lruCache.put("two", 2);
        lruCache.put("three", 3);
        System.out.println(lruCache.get("one"));
        lruCache.put("d", 4);
        System.out.println(lruCache.get("two"));

    }
}