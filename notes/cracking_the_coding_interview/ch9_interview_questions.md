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

System design and Scalability
--
**Handling the questions**

* Communicate
* Go broad first
* Use the whiteboard
* Acknowledge interviewer concerns
* Be careful about assumptions
* State your assumptions explicitly
* Estimate when necessary
* Drive

**Design**

* Scope the problem -> Major features/use cases
* Make reasonable assumptions
* Draw the major components
* Identify the key issues
* Redesign for the key issues

**Algorithms that scale**

* Ask questions
* Make believe
* Get real 
* Solve problems

**Key concepts**

* Horizontal vs vertical scaling
* Load balancer -> redundant servers to cope up with the load.
* Database de-normalization and NoSQL -> Keep redundant data to avoid joins.
* Database partitioning(Sharding) -> Splitting the data across multiple machines while ensuring you have a way of
figuring out which data is on which machine.
    * Vertical partitioning -> partitioning by feature. If tables for a feature increase in size then again have to 
    partition.
    * Key-based(or Hash-based) partitioning -> allocate N servers and put the data on mod(key, n). One disadvantage is if
    you want to add an additional server you have to relocate the whole data.
    * Directory-based partitioning -> look table for the data. Easy to add additional servers. Disadvantages will be that
    the look up table is a single point of failure and constantly accessing the table will affect the performance.
* Cache
* Asynchronous processing & queues.
* Networking metrics
    * Bandwidth -> This is the maximum amount of data that can be transferred in a unit of time. It is typically measured
    in bits per second.
    * Throughput -> Actual amount of data transferred.
    * Latency -> How long it takes for data to go from one end to another.
* MapReduce
* Considerations
    * Failures
    * Availability and Reliability -> Availability is a function of the percentage of time the system is operational. 
    Reliability is a function of the probability that the system is operational for a certain amount of time.
    * Read-heavy vs. Write-heavy -> For write-heavy we can think of queuing up the writes. For read-heavy we can think 
    of caching the data.
    * Security

1. If a given task is repetitive we can think of pre-processing.

Testing
--

**Testing a real world object**

* Who will use it? And Why?
* What are the use cases?
* What are the bounds of use?
* What are the stress/failure conditions? -> When it's acceptable for the product to fail, and what failure should mean.
* How you would perform the testing? -> Fact check(Check for the properties of the object), Intended use(Check for it
working correctly on normal scenarios), Safety, Unintended uses.
 
**Testing a piece of software**

* Are we doing Black Box Testing or White Box Testing?
* Who will use it? And why?
* What are the use cases?
* What are bounds of use?
* What are the stress conditions/failure conditions?
* What are the test cases? How would perform the testing?

**Testing a function**

* Define the testcases
    * The normal cases
    * The extremes
    * Nulls and "illegal" input
    * Strange input -> ex- already sorted array to sort function.
* Define the expected result -> What is the output? Will input be mutated or not?
* Write test code

**Troubleshooting Questions**

* Understand the scenario
* Break down the problem
* Create specific, manageable tests

Java
--
* Overloading and overriding
* Collection framework
