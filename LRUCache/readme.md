LRU Cache should have following functionality:
1. put(key, value): **Inserts a key-value pair** into the cache. If the key already exists, update its value and mark it as recently used. 
If the cache exceeds its capacity, evict the least recently used item.
2. get(key): Returns the value associated with the key. if key exists in the cache, **move it in front** of cache and return its value. 
If the key does not exist, return -1.
3. Cache should have a **fixed capacity** defined at the time of its creation.
4. Cache should be **thread safe**, allowing concurrent access from multiple threads without data corruption.
5. Optimize for both time and space complexity, **aiming for O(1)** time complexity for both put and get operations.


---------******************----------

Problem Statement

Design and implement an LRU (Least Recently Used) Cache with a fixed capacity. The cache should support fast retrieval and insertion, and automatically evict the least recently used item when the capacity is exceeded.

Requirements

Fixed Capacity: The cache has a maximum size. When full, the least recently used item is evicted on insertion.
Fast Operations: Both get(key) and put(key, value) operations should be O(1).
Eviction Policy: The least recently used item is removed when the cache exceeds its capacity.
Extensibility: Easy to change the eviction policy or underlying data structures.
Core Entities

LRUCache: Main class implementing the cache logic, manages storage and eviction.
Node: Represents a doubly-linked list node for fast removal and insertion.
