**Manhattan distance**
```cmd
=dist(P1,P2)
=|X1-X2|+|Y1-Y2|
=max(X1-X2,X2-X1)+max(Y1-Y2,Y2-Y1)
=max((X1-X2)-(Y1-Y2),(X1-X2)-(Y2-Y1),(X2-X1)-(Y1-Y2),(X2-X1)-(Y2-Y1)
=max((X1+Y1)-(X2+Y2),(X1-Y1)-(X2-Y2),(-X1+Y1)-(-X2+Y2),(-X1-Y1)-(-X2,-Y2)
=max(f1(P1)-f1(p2),f2(P1)-f2(P2),f3(P1)-f3(P2),f4(P1)-f4(p2)

f1(P)=X+Y
f2(P)=X-Y
f3(P)=-X+Y
f4(P)=-X-Y

```
We append a 0 to to the binary representation fo i to obtain the index of the left child and a 1 to obtain the index of 
the right child.

**Structure Property**
1. A binary heap is a complete binary tree
    * Each level(except possibly the bottom most level) is completely filled.
    * The bottom most level may be partially filled(from left to right)
2. Height of a complete binary tree with N elements is floor(log2N)

**Heap-Order Property** -> For every node X, key(parent(X))<= key(X) except root node, which has no parent(for MinHeap)

**Build Heap Analysis**
--------------------
```cmd
sum(height of all nodes)
[sum i=0-h]{(h-i)2^i} -> height  no of nodes with that height.
[sum i=0-h]{h2^i} - [sum i=0-h]{i2^i}
h[sum i=0-h]{2^i} - S
h(2^(h+1)-1) - S

S = sum i=0-h{i2^i}
S = 12 + 22^2 + ... + h2^h
2S = 12^2+32^3 + ... + h2^(h+1)
2S-S = S = 12^1 + 2^2 + ...+2^h + h2^(h+1)
S = -[2^(h+1)-1-1]+h2^(h+1)
S = (h-1)2^(h+1)-2

h(2^(h+1)-1)-(h-1)2^(h+1)-2
h2^(h+1) - h -h2^(h+1) + 2^(h+1) +2
2^(h+1) - h + 2
2^(logN+1) - logN +2 (h=logN)
2N-logN+2
O(N)
```


**Binomial Tree** -> A binomial tree of height of k is called Bk
1. It has 2^k nodes
2. The number of nodes at depth d = kCd

To check for n number of nodes what will be the binomial forest use the binary representation ex - 31 = 1 1 1 1 1 , it 
would be forest of B0, B1, B2, B3 and B4

Bi==B(i-1) + B(i-1)

**Binomial Heap**

**Structure Property** -> A forest of binomial trees as dictated by the binary representation of n.

**Heap-Order Property** -> Each binomial tree is a min-heap or a max-heap.

We add at the last index and replace the root with last index (during extractMin) to maintain the compact array, i.e,
a complete binary tree.

For update operation with log n we need NodeToHeap and HeapToNode array to find the node in the heap.