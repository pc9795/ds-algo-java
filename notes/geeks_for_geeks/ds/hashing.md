Hashing is an improvement over Direct Access Table. The idea is to use hash function that converts a given key to a 
smaller number and uses the small number as index in a table called hash table.

**Properties of a good hash function**
* Efficiently computable
* Should uniformly distribute the keys.

Collision
--
The situation where a newly inserted key maps to an already occupied slot in the hash table is called collision.

**Chaining**
Each cell of hash table point to a linked list of records that have same hash function value.
* Advantages
    1. Hash table never fills up
    2. Simple to implement
    3. Less sensitive to the hash function or load factors
    4. It is mostly used when it is unknown how many and how frequently keys may be inserted/deleted.
* Disadvantages
    1. Cache performance of chaining is not good as keys are stored using linked list
    2. Wastage of space (some parts of hash table are never used)
    3. If the chain becomes long, the search time can become O(n) in worst case.
    4. Uses extra space for links.

**Performance**
```cmd
Assumption that each key is equally likely to be hashed to any slot of table.

m = number of slots in hash table
n = number of keys to be inserted in hash table

load factor, a = n/m

expected time to search/insert/delete =O(1+a)

```

**Open Addressing**
* Insert(k) - keep probing until an empty slot is found. Once an empty slot is found, insert k
* Search(k) - keep probing until slot's key doesn't become equal to k or an empty slot is reached
* Delete(k) - If we simply delete a key, then search may fail. So slots of deleted keys are marked specially as "deleted".
 Insert can insert an item in a deleted slot, but the search doesn't stop at a deleted slot.

**Ways to implement Open addressing**

**Linear probing**
```cmd
S is the table size

if slot hash(x)%S is full, then we try (hash(x)+1)%S
if (hash(x)+1)%S is also full, then we try (hash(x)+2)%S
...
```

**Clustering** Many consecutive elements form groups and it starts taking time to find a free slot or to search an element.

**Quadratic probing**
```cmd
S is the table size

if slot hash(x)%S is full, then we try (hash(x)+1*1)%S
if ((hash(x)+1*1)%S is also full, then we try (hash(x)+2*2)%S
...
```

**Double Hashing**
```cmd
S is the table size

if slot hash(x)%S is full, then we try (hash(x)+1*hash2(x))%S
if (hash(x)+1*hash2(x))%S is also full, then we try (hash(x)+2*hash2(x))%S
...
```

Technique|Advantages|Disadvantages
---|---|---
Linear Probing|1. best cache performance; 2. easy to compute|1. clustering
Quadratic Probing|-|-
Double Hashing|1. no clustering|1. poor cache performance; 2. computation time


Separate Chaining|Open Addressing
---|---
Chaining is Simpler to implement.|Open Addressing requires more computation.
In chaining, Hash table never fills up, we can always add more elements to chain.|In open addressing, table may become full.
Chaining is Less sensitive to the hash function or load factors.|Open addressing requires extra care for to avoid clustering and load factor.
Chaining is mostly used when it is unknown how many and how frequently keys may be inserted or deleted.|Open addressing is used when the frequency and number of keys is known. [Because we have to store everything in the same table].
Cache performance of chaining is not good as keys are stored using linked list.|Open addressing provides better cache performance as everything is stored in the same table.
Wastage of Space (Some Parts of hash table in chaining are never used).|In Open addressing, a slot can be used even if an input doesn’t map to it.
Chaining uses extra space for links|No links in Open addressing

**Performance**
```cmd
Assumption that each key is equally likely to be hashed to any slot of table.

m = number of slots in hash table
n = number of keys to be inserted in hash table

load factor, a = n/m (< 1)

expected time to search/insert/delete < 1/(1 - a)

```