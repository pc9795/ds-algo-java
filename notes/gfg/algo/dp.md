Metrics with subset then need dp else greedy will work.

**Overlapping sub-problems** -> Solutions of same sub-problems are needed again and again.

1. Memoization(Top Down)-lookup table with recursion.
2. Tabulation(Bottom Up)-Build a table in bottom up fashion and returns the last entry from the table.

**Optimal Substructure** -> Optimal solution of the given problem can be obtained by using optimal solutions of its 
sub-problems.

Recursions
--

**LIS**
* L is lis array and arr is input array.
* L(i)=1+max(L(j)) where 0<j<i and arr[j]<arr[i] or
* L(i)=1 if no such j exists.

**LCS**
* if X[0..m-1]==Y[0..n-1] then L(X[0..m-1],Y[0..n-1])=1+L(X[0..m-2],Y[0..n-2])
* else L(X[0..m-1,Y[0..n-1])=MAX(L(X[0..m-2],Y[0..n-1),L(X[0..m-1],Y[0..n-2]))

**Boyer Moore**

Combination of two approaches
1. Bad Character Heuristic
2. Good Suffix Heuristic.

If we shift all edge weights in such a way that their are no negative weights then we can use Dijkstra instead of
Bellman ford (lower complexity).
