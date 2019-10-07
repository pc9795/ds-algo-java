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

Linked Lists
--
Recursive algorithms take at least O(n) space, where n is the depth of the recursive call. All recursive algorithm can 
be implemented iteratively, although they may be much complex. 


Stacks and Queues
--
* A stack can be used to implement a recursive algorithm iteratively.
* Queue is used in breadth-first search or in implementing a cache. 

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

Bit Manipulation
--
```cmd
x ^ 0s = x
x ^ 1s = ~x
x ^ x = 0

x & 0s = 0
x & 1x = x
x & x = x

x | 0s = x
x | 1s = 1s
X | x = x
```

Two's complement -> In other words, the binary representation of -K (negative K) as a N-bit number is concat
(1, 2**(N-1) - K). Another way to look at this is that we invert the bits in the positive representation and then add 1.

**Math and Logic Puzzles**
* P(A and B) = P(B given A)P(A) = P(A given B)P(B)
* P(A given B) = P(B given A)(P(A)/P(B)) -> Bayes theorm
* P(A or B) = P(A) + P(B) - P(A and B)
    * For independent events -> P(A and B) = P(A)*P(B) because P(B given A) is P(B) since A indicates nothing about B.
    * For mutually exclusive events -> P(A and B) = 0
    * mutual exclusivity means that if one happens then the other cannot. Independence, however, says that one event 
    happening means absolutely nothing about the other event. Thus, as long as two events have non-zero probabilities, 
    they will never be both mutually exclusive and independent.
    
* You add probabilities when the events you are thinking about are alternatives [Reading score 0 goals or 1 goal or 2 
goals in their match] - you are looking for "mutually exclusive" events - things which could not happen at the same time
 (in the same match).
  
* You multiply probabilities when you want two or more different things to happen "at the same time" or "consecutively" 
[Reading score 1 and Leeds score 1 and Arsenal score 2]. The key thing here is that the events are independent - they do
 not affect each other, or the second does not affect the first (etc).
 
* If the two jug sizes are relatively prime, you can measure any value between one and the sum of the jug sizes. 

* x(x+1)=num of floors -> egg drop puzzle.

Object-Oriented Design
--
* Handle Ambiguity - six w's: who, what, where, when, how, why
* Define the core objects.
* Analyze relationships(between core objects)
* Investigate actions

Singleton pattern is often called as an anti pattern because it interfere with unit testing.

Recursion and Dynamic Programming
--
* Bottom up -> from base to original
* Top down -> from original to base
* Half and half