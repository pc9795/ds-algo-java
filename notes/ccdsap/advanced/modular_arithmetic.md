n^(exp)%M; we simply use modulus on all the intermediate multiplications and take modulus at the end.

**Lucas' Theorem**
```cmd
nCr%M = n0Cr0%M  n1Cr1%M ... nkCrk%M
nk nk-1 ... n0 is the base M representation of n
rk rk-1 ... r0 is the base M representation of r
M should be prime (power of prime)

```
If AB%M = 1, then A and B are modular multiplicative inverse of each other.

**Chinese Remainder Theorem**

We are given two array num[0...k-1] and rem[0...k-1]. In num[0...k-1], every pair is co-prime. We need to find
minimum positive number x such that:
```cmd
x%num[0]=rem[0]
x%num[1]=rem[1]
...
x%num[k-1]=rem[k-1]
We get such an x using this theorem.
```



`nCr = n-1Cr + n-1Cr-1 ; to calculate nCr%m if m<=5000 and r<=n<=5000`

`[n!/(r!(n-r)!]%m = nn-1...n-r+1r^-1(r-1)^-1...1 where r is small.`

```cmd
nCr % M where M is not prime then use chinese remainder theorem.
ex- M = 142857 = 3^3  11  13  37
then we can find result of nCr % m for each m = 27,11,13,37 and reconstruct the answer modulo 142857 using chinese 
remainder theorem.

```
