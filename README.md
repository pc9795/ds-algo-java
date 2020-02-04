**This Repo contains implementation of Data structures and Algorithms in Java**

I sometimes use Python also so you can find it here and there. The major content is what I learned in Geeks for Geeks but
I have also added notes form my study of CCDSAP.

I followed a style where in most of the places I used assertions in place of exceptions.

## Data Structures

Name|Type|Access|Insertion|Deletion|Search|Space|Special|
---|---|---|---|---|---|---|---|
Array|Linear|O(1)|O(n)|O(n)|<table><thead></thead><tbody><tr><td>O(n)</td><td>Sequential search</td></tr><tr><td>O(log n)</td><td>Binary search</td></tr></tbody></table>|O(n)|-|
Singly Linked List|Linear|O(n)|<table><thead></thead><tbody><tr><td>O(1)</td><td>Insertion at beginning</td></tr><tr><td>O(n)</td><td>Insertion in middle</td></tr><tr><td>O(n)</td><td>Insertion at end</td></tr></tbody></table>|<table><thead></thead><tbody><tr><td>O(1)</td><td>Deletion at beginning</td></tr><tr><td>O(n)</td><td>Deletion in middle</td></tr><tr><td>O(n)</td><td>Deletion at end</td></tr></tbody></table>|<table><thead></thead><tbody><tr><td>O(n)</td><td>Sequential search</td></tr></tbody></table>|O(n)|-|
Doubly Linked List|Linear|O(n)|<table><thead></thead><tbody><tr><td>O(1)</td><td>Insertion at beginning</td></tr><tr><td>O(n)</td><td>Insertion in middle</td></tr><tr><td>O(n)</td><td>Insertion at end</td></tr></tbody></table>|<table><thead></thead><tbody><tr><td>O(1)</td><td>Deletion at beginning</td></tr><tr><td>O(n)</td><td>Deletion in middle</td></tr><tr><td>O(n)</td><td>Deletion at end</td></tr></tbody></table>|<table><thead></thead><tbody><tr><td>O(n)</td><td>Sequential search</td></tr></tbody></table>|O(n)|-|
Circular Singly Linked List|Linear|O(n)|<table><thead></thead><tbody><tr><td>O(1)</td><td>Insertion at beginning</td></tr><tr><td>O(n)</td><td>Insertion in middle</td></tr><tr><td>O(1)</td><td>Insertion at end</td></tr></tbody></table>|<table><thead></thead><tbody><tr><td>O(1)</td><td>Deletion at beginning</td></tr><tr><td>O(n)</td><td>Deletion in middle</td></tr><tr><td>O(n)</td><td>Deletion at end</td></tr></tbody></table>|<table><thead></thead><tbody><tr><td>O(n)</td><td>Sequential search</td></tr></tbody></table>|O(n)|-|
Circular Doubly Linked List|Linear|O(n)|<table><thead></thead><tbody><tr><td>O(1)</td><td>Insertion at beginning</td></tr><tr><td>O(n)</td><td>Insertion in middle</td></tr><tr><td>O(1)</td><td>Insertion at end</td></tr></tbody></table>|<table><thead></thead><tbody><tr><td>O(1)</td><td>Deletion at beginning</td></tr><tr><td>O(n)</td><td>Deletion in middle</td></tr><tr><td>O(1)</td><td>Deletion at end</td></tr></tbody></table>|<table><thead></thead><tbody><tr><td>O(n)</td><td>Sequential search</td></tr></tbody></table>|O(n)|-|
Stack|Linear|O(n)|O(1)|O(1)|O(n)|-|
Queue|Linear|O(n)|O(1)|O(1)|O(n)|-|
Binary Tree|Tree|O(n)|-|O(n)|O(n)|O(n)|-|
Binary Search Tree|Tree|O(h)|O(h)|O(h)|O(h)|O(n)|-|
Balanced Binary Search Tree|Tree|O(log n)|O(log n)|O(log n)|O(log n)|O(n)|-|
Binary Heap|Tree|-|O(log n)|O(log n)|-|O(n)|<table><thead><tr><th>Get Min</th><th>Extract Min</th><th>Decrease Key</th></tr></thead><tbody><tr><td>O(1)</td><td>O(log n)</td><td>O(log n)</td></tr></tbody></table>|
Binomial Heap|Tree|-|O(1)|-|-|O(n)|<table><thead><tr><th>Get Min</th><th>Extract Min</th><th>Decrease Key</th><th>Union</th></tr></thead><tbody><tr><td>O(log n)</td><td>O(log n)</td><td>O(log n)</td><td>O(log n)</td></tr></tbody></table>|
Fibonacci Heap|Tree|-|O(1)|-|-|O(n)|<table><thead><tr><th>Get Min</th><th>Extract Min</th><th>Decrease Key</th><th>Union</th></tr></thead><tbody><tr><td>O(1)</td><td>O(log n)</td><td>O(log n)</td><td>O(1)</td></tr></tbody></table>|
Map|Map|O(1)|O(1)|O(1)|O(n)|O(1)|
Adjacency Matrix|Graph|-|-|-|-|O(V^2)|-|
Adjacency List|Graph|-|-|-|-|O(V+E)|-|
Trie|Tree|-|O(M)|O(M)|O(M)|O(ALPHABET_SIZE * M * N)|-|
Segment Tree|Tree|-|-|-|-|-|<table><thead><tr><th>Construction</th><th>Query</th><th>Update</th></tr></thead><tbody><tr><td>O(n)</td><td>O(log n)</td><td>O(log n)</td></tr></tbody></table>|
Suffix Tree|Tree|-|-|-|-|-|-|
