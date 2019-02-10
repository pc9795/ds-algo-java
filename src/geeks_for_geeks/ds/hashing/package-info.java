package geeks_for_geeks.ds.hashing;

/*
 * Collision
 * ---------
 *
 * Chaining
 * --------
 * Advantages
 * -----------
 * 1. Hash table never fills up
 *
 * Disadvantages
 * --------------
 * 1. Cache performance of chaining is not good as keys are stored using linked list
 * 2. Wastage of space (some parts of hash table are never used)
 * 3. If the chain becomes long, the search time can become O(n) in worst case.
 * 4. Uses extra space for links.
 *
 * Performance
 * ------------
 * m=number of slots in hash table
 * n=number of keys to be inserted in hash table
 *
 * load factor a=n/m
 *
 * expected time to search/insert/delete =O(1+a)
 *
 * Open Addressing
 * ----------------
 * Insert(k)-keep probing until an empty slot is found. Once an empty slot is found, insert k
 *
 * Search(k)-keep probing until slot's key doesn't become equal to k or an empty slot is
 *           reached
 *
 * Delete(k)-If we simply delete a key, then search may fail. So slots of deleted keys are
 *           marked specially as "deleted". Insert can insert an item in a deleted slot, but the
 *           search doesn't stop at a deleted slot.
 *
 * Ways to implement Open addressing
 * ----------------------------------
 *
 * Linear probing
 * ---------------
 * if slot hash(x)%S is full, then we try (hash(x)+1)%S
 * if (hash(x)+1)%S is also full, then we try (hash(x)+2)%S
 *
 * Quadratic probing
 * ------------------
 * if slot hash(x)%S is full, then we try (hash(x)+1*1)%S
 * if ((hash(x)+1*1)%S is also full, then we try (hash(x)+2*2)%S
 *
 * Double Hashing
 * ---------------
 * if slot hash(x)%S is full, then we try (hash(x)+1*hash2(x))%S
 * if (hash(x)+1*hash2(x))%S is also full, then we try (hash(x)+2*hash2(x))%S
 *
 *
 *                          Advantages                    Disadvantages
 *                         ------------                   -------------
 * Linear Probing           1. best cache performance     1. clustering
 *                          2. easy to compute
 * Quadratic Probing        -                             -
 * Double Hashing           1. no clustering              1. poor cache performance
 *                                                        2. computation time
 *
 *
 * Separate Chaining                         Open Addressing
 * ------------------                        ----------------
 * Chaining is Simpler to implement.         Open Addressing requires more computation.
 *
 * In chaining, Hash table never fills up,   In open addressing, table may become full.
 * we can always add more elements to chain.
 *
 * Chaining is Less sensitive to the hash    Open addressing requires extra care for
 * function or load factors.                 to avoid clustering and load factor.
 *
 * Chaining is mostly used when it is        Open addressing is used when the frequency
 * unknown how many and how frequently keys  and number of keys is known. [Because we have to
 * may be inserted or deleted.               store everything in the same table].
 *
 * Cache performance of chaining is not      Open addressing provides better cache
 * good as keys are stored using linked      performance as everything is stored in the
 * list.                                     same table.
 *
 * Wastage of Space (Some Parts of hash      In Open addressing, a slot can be used even
 * table in chaining are never used).        if an input doesn’t map to it.
 *
 * Chaining uses extra space for links.      No links in Open addressing
 *
 * Performance
 * ------------
 * load factor a<1
 * expected time to search/insert/delete <1/(1-a)
 *
 *
 *
 *
 * */