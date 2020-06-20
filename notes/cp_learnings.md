* https://www.codechef.com/COOK110B/problems/EXERCISE -> If a question have some kind of symbolic sequence we can try to
 reduce it
  
* https://www.codechef.com/problems/FIBEASY -> For any integer n, the sequence of Fibonacci numbers Fi taken modulo n is
periodic. The **Pisano** period, denoted π(n), is the length of the period of this sequence.

* https://www.codechef.com/problems/GDSUB -> 
```
For input: 
7 4 
2 2 3 3 5 5 7
dp[i][j] = d[i][j-1] + dp[i-1][j-1]*count(i)
j is sub sequence size and i is the distinct values.
ans is sum of last row.
+===+===+====+====+====+====+
|   | 0 | 1  | 2  | 2  | 4  |
+===+===+====+====+====+====+
|-1 | 1 |  0 |  0 | 0  |  0 |
+---+---+----+----+----+----+
| 2 | 1 |  2 |  0 |  0 |  0 |
+---+---+----+----+----+----+
| 3 | 1 |  4 |  4 |  0 |  0 |
+---+---+----+----+----+----+
| 5 | 1 | 6  |  2 |  8 | 0  |
+---+---+----+----+----+----+
| 7 | 1 |  7 | 18 | 20 |  8 |
+---+---+----+----+----+----+
```

* If you are getting TLE in Java output then buffer it in `StringBuilder`.