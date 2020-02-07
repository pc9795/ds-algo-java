**This Repo contains implementation of Data structures and Algorithms in Java**

I sometimes use Python also so you can find it here and there. The major content is what I learned in Geeks for Geeks but
I have also added notes form my study of CCDSAP.

I followed a style where in most of the places I used assertions in place of exceptions.

## Data Structures

**Common**

Name|Type|Insertion|Deletion|Search|
---|---|---|---|---|
Array|Linear|O(n)|O(n)|<table><thead></thead><tbody><tr><td>O(n)</td><td>Sequential</td></tr><tr><td>O(log n)</td><td>Binary</td></tr></tbody></table>|
Singly Linked List|Linear|<table><thead></thead><tbody><tr><td>O(1)</td><td>At Beginning</td></tr><tr><td>O(n)</td><td>In middle</td></tr><tr><td>O(n)</td><td>At end</td></tr></tbody></table>|<table><thead></thead><tbody><tr><td>O(1)</td><td>At beginning</td></tr><tr><td>O(n)</td><td>In middle</td></tr><tr><td>O(n)</td><td>At end</td></tr></tbody></table>|O(n)|
Circular Singly Linked List|Linear|<table><thead></thead><tbody><tr><td>O(1)</td><td>At beginning</td></tr><tr><td>O(n)</td><td>In middle</td></tr><tr><td>O(1)</td><td>At end</td></tr></tbody></table>|<table><thead></thead><tbody><tr><td>O(1)</td><td>At beginning</td></tr><tr><td>O(n)</td><td>In middle</td></tr><tr><td>O(n)</td><td>At end</td></tr></tbody></table>|O(n)|
Doubly Linked List|Linear|<table><thead></thead><tbody><tr><td>O(1)</td><td>At beginning</td></tr><tr><td>O(n)</td><td>In middle</td></tr><tr><td>O(n)</td><td>At end</td></tr></tbody></table>|<table><thead></thead><tbody><tr><td>O(1)</td><td>At beginning</td></tr><tr><td>O(n)</td><td>In middle</td></tr><tr><td>O(n)</td><td>At end</td></tr></tbody></table>|O(n)|
Circular Doubly Linked List|Linear|<table><thead></thead><tbody><tr><td>O(1)</td><td>At beginning</td></tr><tr><td>O(n)</td><td>In middle</td></tr><tr><td>O(1)</td><td>At end</td></tr></tbody></table>|<table><thead></thead><tbody><tr><td>O(1)</td><td>At beginning</td></tr><tr><td>O(n)</td><td>In middle</td></tr><tr><td>O(1)</td><td>At end</td></tr></tbody></table>|O(n)|
Binary Tree|Tree|-|O(n)|O(n)|
Binary Search Tree|Tree|O(h)|O(h)|O(h)|
Balanced Binary Search Tree|Tree|O(log n)|O(log n)|O(log n)|
Map|Map|O(1)|O(1)|O(1)|

**Insertion/Deletion**

Name|Type|Insertion|Deletion|
---|---|---|---|
Stack|Linear|O(1)|O(1)|
Queue|Linear|O(1)|O(1)|

**Heaps**

Name|Type|Insertion|Get Min|Extract Min|Decrease Key|Union|
---|---|---|---|---|---|---|
Binary Heap|Tree|O(log n)|O(1)|O(log n)|O(log n)|-|
Binomial Heap|Tree|O(1)|O(log n)|O(log n)|O(log n)|O(log n)|
Fibonacci Heap|Tree|O(1)|O(1)|O(log n)|O(log n)|O(1)|

**Graph**

Name|Type|Adding Vertex|Adding Edge|Querying Edge|Space|
---|---|---|---|---|---|
Adjacency Matrix|Graph|O(V^2)|O(1)|O(1)|O(V^2)|
Adjacency List|Graph|-|O(1)|-|O(V+E)|

**Tables to add**
* Union Find
* Segment Tree
* Suffix Tree
* Trie
* Difference Array
