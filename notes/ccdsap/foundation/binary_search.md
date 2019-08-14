In binary search we assume that we have random access to the sequence. Trying to use on a container such as a linked list
makes little sense and it is better use a plain linear search instead.

if we see arr[i]==mid as f(i)==mid then we can conclude that we can apply binary function on some monotonically increasing
functions also.

Always test you code on a two element set where the predicate is false for the first element and true for the second.

When applying binary search on real numbers 100 iterations will reduce search space to 10^-30 of it's initial size. \
```cmd
In one search n/2
In 100th search n/(2^100)
2^100=10^x
take log10
100log10(2)=x
x=~30
```
