**Asymptotic Analysis**
* We calculate, how much the time (or space) taken by an algorithm increases with input size.

**Disadvantages**
1. We can't judge which one is better as we ignore constants in Asymptotic analysis.
2. It might be possible that those large inputs are never given to your software and an algorithm which is asymptotically
 slower, always performs better for your particular situation

**Notations**
* theta notation
    * drop lower order terms and ignore leading constants.
    * theta(g(n))={f(n):there exists positive constants c1,c2 and n0 such that 0<=c1*g(n)<=f(n)<=c2*g(n) for all n>=n0}
* big O notation 
  * O(g(n))={f(n):there exist positive constants c and n0 such that 0<=f(n)<=c*g(n) for all n>=n0}
* sigma notation
    * sigma(g(n)) = {f(n):there exist positive constants c and n0 such that 0<=c*g(n)<=f(n) for all n>=n0}
* little O notation
    * same as big O but loose bound
    * 0<=f(n)<c*g(n) limit(n->infinite) f(n)/g(n) = 0
* little sigma notation
    * same as sigma notation but loose bound
    * o<=c*g(n)<f(n) limit(n->infinite) f(n)/g(n) = infinite
    
**Analysis of loops**
* O(1) = Doesn't contain loop, recursion and call to any other non-constant time function.  A loop or recursion that runs
 a constant number of times is also considered as O(1).
* O(n) = loop variables is incremented/decremented by a constant amount.
* O(n^c) i
* O(log n) = loop variables is divided/multiplied by a constant amount.
* O(log log n) = loop variables is reduced/increased exponentially by a constant amount.

**Solving recurrences**
 1. Substitution method - we make a guess for the solution and then we use mathematical induction to prove to the guess 
 is correct or incorrect.
 
 ```cmd
T(n)=2T(n/2)+n
let T(n)=O(nlogn)
proof:
T(n)=2T(n/2)+n
<=cn/2log(n/2)+n
=cnlogn - cnlog2 + n
=cnlogn -cn + n
<=cnlogn
  
```
   
2. Recurrence tree method - we draw a recurrence tree and calculate the time taken by every level of tree. Finally, we 
sum the work done at all levels. The pattern is typically a arithmetic or geometric series.

3. Master method 

```cmd
T(n) = aT(n/b) + f(n) where a>=1 and b>1
f(n)=theta(n^c)
a.) c<logb(a) T(n)=theta(n^(logb(a))) <-last level dominates
b.) c=logb(a) T(n)=theta(n^c  log(n)) <-work in each level is same
c.) c>logb(a) T(n)=theta(f(n)) <-work at root dominate

      f(n) <-work at root
  /     |    \ <-a children
f(n/b)  f(n/b) f(n/b) <-work reduced by a factor of b
     ........
theta(1) theta(1) theta(1)

height=logb(n)
at last level
= a^height children of theta(1)
= theta(a^logb(n))
= theta(n^logb(a))

If f(n) = theta(n^c(logn)) k>=0 and c=logb(a)
then T(n)=theta(n^c(logn)^(k+1))

```

**Amortized Analysis** -> Used for algorithms where an occasional operation is very slow, but most of the other operations
are faster. We analyze a sequence of operations and guarantee a worst case average time which is lower than the worst 
case time of a particular expensive operation.

**Space complexity** -> auxiliary space + space used by input