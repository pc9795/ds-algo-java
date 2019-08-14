Dynamic programming amounts to breaking down an optimization problem into simpler sub-problems, and storing the solution
to each sub-problem so that each sub-problem is only solved once.

* First try to find the recurrence.
* Then the dimensions of the memoization table will come from the variables on which our recursion function depends.

```cmd
|0 1|^n  |1| = |Fn-1|
|1 1|     |0|   |Fn  |
matrix multiplication can be done in logN using divide and conquer.

G(n)=a.G(n-1)+b.G(n-2)+c.G(n-3) if this type of recursion is found then it can be done with the help of matrix.

G(n+1)= aG(n) + bG(n-1) + cG(n-2)
    =(a^2+b)G(n-1) + (ab+c)G(n-2) + acG(n-3)

|0 1 0||G(n-3)|=|G(n-2)|
|0 0 1| |G(n-2)| |G(n-1)|
|c b a| |G(n-1)| |G(n)  |

for G(n+1)
|0 1 0||0 1 0|=|0  0    1    |
|0 0 1| |0 0 1| |c  b    a    |
|c b a| |c b a| |ac c+ab b+a^2|

```

DP is recursion without repetition.

**MIT Videos**

* Optimization problem with careful brute force(exhaustive search)
* Topological sort of sub-problems dependency DAG; So the problem must be DAG.
* You can save space in bottom-up
* For graphs take example of DAG and cyclic graphs.

1. Define sub-problems
2. Guess(part of solution)
3. relate sub-problem solutions
4. recurse & memoize or build DP table bootum-up
5. solve the original problem

T=no of sub-problemstime/sub-problem

Parent pointer for tracing back the results.

**DP on strings**

* suffixes n
* prefixes n
* substrings n^2

**2 kinds of guessing**
1. guessing which sub-problem to use to solve bigger sub problems
2. add more sub-problems to guess/remember more(in knapsack adding weight as a dimension)

Advanced
--

**DP over multidimensional arrays**

Each state is represented as one dimension. We progress using a order of this dimensions.

```cmd
for (int i1 = 0; i1<N1; i1++)
for (int i2 = 0; i2<N1; i2++)
 ...
    for (int ik = 0; ik<Nk; ik++) {
      //get some states (j1, j2, j3, ..., jk) -> jres by performing transitions
      //and handle them
    }

```

**DP over subsets (N<=20)**

State domain = (s,a) s is the subset and a is additional parameters; We progress by adding values to these subsets.

Hamiltonian path -> Same as Euler path but for vertices.

**DP over strings**
State domain = (L, R, a) L and R, left and right borders of a given substring. We progress in a non-decreasing length.

**DP over trees**
State domain = (v,a) v is a root of subtree; The easiest way to iterate through states in correct order is to launch DFS
from the root of tree. When DFS exists from a vertex, its result must be finally computed and stored in global memory.

**DP over layers**
State domain = (k,p) k is number of fully processed layers and p is so-called profile of solution. Profile is the 
necessary information about solution in layers that are not fully processed yet.


**DP problems**
1. Optimization (Max, Min etc.)
2. Combinatoric (No of ways, Sum, Count etc.)

**Optimization**
* It is often useful to fill the DP results array with neutral values before calculating anything.

**Recovering the solution**
* DP from final state (Use when low on memory, but complex and time consuming)
* Back links

**DP optimization**
1. Merge equivalent states; ex- you are considering a permutation of some problem as a state, but if the order does't matter then it can be
to combinations which has lower value than permutations.
2. Prune impossible states
    * Explicit parameter dependence ex- state domain is (A,B) and if B=F(A) we can convert this state domain to only 
    (A) because whenever we want B we can calculate it using A.
    *  Implicit parameter dependence; ex- state domain is (A,B) and f(A,B)=0 or there are more other equations. We can 
    convert this to explict parameter dependence
    *  Inequalities on parameters; ex- state domain is (i,j) and i<j then we can write for(i=0;i<N;i++) 
    for(j=i+1;j<N;j++), eliminating impossible states
    *  No-thinking ways
        - Discard possible states and do not process them. ex- if(res[i][j]==0) continue
        - Use recursive search with memoization.
        - Store results in a map. This way impossible states do not eat memory and time at all.
3. Store results only for two layers of DP state domain. (But you can't reconstruct the solution)
4. Precalculate.
5. Rotate the optimization problem.
6. Calculate matrix power by squaring.
7. Use complex data structures and algorithms.