Arrays and Strings
--

**Hash table**
* We can use array of linked lists to implement it.
* Compute the hash of the key. It could be int/long.
* Map the hash to array index. `hash % len(arr)`
* T=O(N) (WC), O(1) (BC) 
* In worst case all keys are mapped to the same index.

`StringBuilder`, `HashTable` and `ArrayList`.

Wherever we use HashMap for characters we can use an integer array or bit set.

Trees and Graphs
--
**Trees**
* Confirm that it is tree or binary tree.
* Confirm that equality is allowed in BST or not? If yes, how?

**Tries**
* While a hash table can quickly look up whether a string is a valid word, it cannot tell us if a string is a prefix of 
any valid words. A trie can do this very quickly.
* A trie can check if a string is a valid prefix in 0( K) time, where K is the length of the string. This is actually the
same runtime as a hash table will take. Although we often refer to hash table lookups as being 0(1) time, this isn't 
entirely true. A hash table must read through all the characters in the input, which takes O ( K) time in the case of a 
word lookup.
