**Isolating the last bit**

```cmd
1 is last set bit therefore b is (0...0)
(a1b)`+ 1 = a`0b`+ 1 = a`0(0...0)`+ 1=a`0(1...1) + 1=a`1(0...0)=a`1b
now a1b & a`1b =  last set bit.
```
