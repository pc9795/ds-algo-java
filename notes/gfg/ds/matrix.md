**In place matrix transpose**
```cmd
ol = old location
nl = new location

R X C will turn to C X R

ol = or * C + oc 
nl = nr * R + nc 

nr = nc
nc = or
N = R * C

nl = (ol * R) - (or * (N - 1)) (combining all the above facts)

nl mod (N - 1) = (ol * R) mod (N - 1) (second term is disappeared because divisible)
nl = (ol * R) mod (N - 1) (because nl is always smaller than N - 1)
```