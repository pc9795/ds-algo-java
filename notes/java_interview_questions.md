1. In terms of inheritance, what is the effect  of keeping a constructor private?
* Tha class can be inherited, but only by its own or its parent's inner classes.

General
--
* If you remove an element via Iterator `remove()` method, exception will not be thrown. However, in case of removing 
via a particular collection `remove()` method, `ConcurrentModificationException` will be thrown.
* `HashTable` doesn't allow null values.
* `ConcurrentHashMap` by default stores data in segments. So that multiple threads can work on different segments. 16.

ArrayList
--
* `null` is allowed.

LinkedList
--
* `null` is allowed.

HashMap
--
* backed by an array.
* `null` key is allowed. It is stored in bucket[0] because we can't get its hash using `hashcode` as it will throw 
`NullPointerException`
* Use chaining for collisions. Till `TREEIFY_THRESHOLD` which is currently 8 linked list is used to store chains. After 
that a BST is used. A BST node has more space requirements so there is a threshold based scheme. `UNTREEIFY_THRESHOLD` 
is 6.
*  multiple `null` values allowed.
* `add`, `remove`, and `contains` is O(1)
* A load factor is a number which controls the resizing of HashMap when a number of elements in the HashMap cross the 
load factor. Default is 0.75.
* `size` returns int so it is erroneous for large maps so we can use `mappingCount` method which returns long.
* the capacity is the number of buckets in the hash table. 16.
* If key is mutable then make sure its state change dosen't change the hash code.
* fail-fast iterator.
* `clone` returns shallow copy.


TreeMap
--
* `null` key is not allowed.
*  multiple `null` values allowed.
* Internally use red black tree.
* elements are in natural order or by using a `Comparator`.
* `add`, `remove`, and `contains` is O(1)
* fail-fast iterator.
* `clone` returns shallow copy.
* no "initial capacity" constructor because the size of the TreeMap dynamically increases if needed , without shuffling 
the internals.
* can be synchronized using `Collections.synchronizedSortedMap` method.

LinkedHashMap
--
* `null` key is allowed.
*  multiple `null` values allowed.
* Maintains the insertion order with the help of maintaining a doubly linked list. It has two fields `head` and `tail`.
* Ordering is not affected by duplicate keys.
* `add`, `remove`, and `contains` is O(1)
* fail-fast iterator.
* `clone` returns shallow copy.

HashSet
--
* `clone` returns shallow copy.
* can be synchronized using `Collections.synchronizedSet` method.
* fail-fast iterator.
* Contain a single object to put in place of "value" in internal map.

LinkedHashSet
--
* `clone` returns shallow copy.
* can be synchronized using `Collections.synchronizedSet` method.
* fail-fast iterator.
* Contain a single object to put in place of "value" in internal map.

TreeSet
--
* `clone` returns shallow copy.
* can be synchronized using `Collections.synchronizedSet` method.
* fail-fast iterator.
* Contain a single object to put in place of "value" in internal map.
* `headSet(int)` to get all the elements less than the input. `tailSet()` and `subSet()`. Returned sets are views means
they will change as the original set changes.

 

ArrayDeque
--
* Faster than stack `Stack` for stack and `LinkedList` for queue.
* `null` not allowed.
* When the internal array of the ArrayDeque is full, as it has to double its size and copy all the data.  
* `getFirst()`/`getLast()` methods will throw an exception when invoked on an empty deque. However, the `peekFirst()`/
`peekLast()` methods will return null if the deque is empty
* `removeFirst()`/`removeLast()` will throw an exception if deque is empty. However, the `pollFirst()`/`pollLast()` 
methods will return null for an empty deque 

PriorityQueue
--
* space = O(n), peek = O(1), enqueue = O(log n), dequeue = O(log n)
* `null` not allowed.