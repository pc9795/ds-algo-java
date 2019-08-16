`10^8` loops per second for time complexity. \
`10^8` size of int array if global. \
`10^6` size of int array if local.

Java Int -> 2x10^9 \
Java Long -> 9x10^18

`LCM * GCD = a * b`

`i^=1` switch between 0 and 1

(a + b + c) % r = (a % r) + (b % r) + (c % r) -> remainder theorm

d%1==0 -> check a double is integer or not.

Avoid mixing algo and getting input, because if your algo breaks and input taking is not complete then it is a bug.

We can use arrays instead of map for getting counts.

For characters use bucket sort.

For pre-processing input sometimes we apply something for whole input size ex- sieve on all input range, instead of it
we can take all the inputs then try to do pre processing for ex- N<10^5 and we have to find prime numbers then instead
of applying sieve to 10^5 we can input everything and check the maximum number and then apply the sieve till that no.
only.

Bubble sort no of swaps - inversion count

**Probability**
* We can calculate the chances of two or more independent events by multiplying the chances
 
**WA**
* wrong answer 1st lines differ - expected: 'abc', found: '' means your answer is wrong; it is nothing to do with new 
line and all.[codeforces]
* trim your input
* use `long` in place of `int`.
* ans*=something%mod -> wa ; ans=ans*something%mod -> correct answer

**Manhattan distance**
* `d1(p,q)=sum(pi-qi) 1<=i<=n`
  
**Degenerate triangle** -> A-----B-----C

* Range of value <=1000 (max 9 bits) => max value of XOR = 1023