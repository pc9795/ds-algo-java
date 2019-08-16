C(n)=(1/n)*(worst-case total cost of sequence of n operations)

**Aggregate Analysis** -> One assumes that there is no need to distinguish between the different operations on the data 
structure. Determines the upper bound T(n) on the total cost of a sequence of n operations, then calculates the amortized
cost to ben T(n)/n.

**Accounting method** ->  Early operations have an amortized cost higher than their actual cost, which accumulates a 
saved "credit" that pays for later operations having an amortized cost lower than their actual cost.

`sum 1 to n( C_amortized)>=sum 1 to n(C)`

ex-for geeks_for_geeks.stack with size |S|

Operation|Actual cost(C)|Amortized Cost(C_amortized)
---|---|---
Push|1|2
Pop|1|0
MultiPop(k)|min{abs(S),k}|0

When an object is pushed to the geeks_for_geeks.stack, it comes endowed with enough credit to pay not only for the operation of pushing 
it onto the geeks_for_geeks.stack, but also for whatever operation will eventually remove it from geeks_for_geeks.stack, be that a POP, a Multipop, or 
no operation at all.

**Potential method** -> The strategy is to define a potential function PHI which maps a state D to a scalar-valued 
potential PHI(D). Given a sequence of n operations with actual costs c1,...,cn, which transform the data structure from 
its initial state D0 through states D1,...,Dn, we define heuristic costs

`C_amortized = C + PHI(Di) - PHI(Di-1)`

`sum 1 to n(C_amortized)=sum 1 to n(C) + PHI(Dn) - PHI(D0)`

ex-Stack
```cmd
PHI(S)=|S| (geeks_for_geeks.stack length)
PHI(0)=0

Push-> C_amortized_push = C_push + PHI(D_new) - PHI(D_old)=1 + (|S_old| + 1) - |S_old| = 2
Pop-> C_amortized_pop = C_pop + PHI(D_new) - PHI(D_old) = 1 + (|S_old| - 1) - |S_old| = 0
MultiPop-> C_amortized_mulitpop = C_mulitpop + PHI(D_new) - PHI(D_old)= min{|S|, k} + (|S_new| - |S_old|) = 0

```

Online algorithms commonly use amortized analysis.