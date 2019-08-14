1. It handles a simple "base case" without using recursion.
2. It avoids cycle - There are many ways to avoid infinite cycles, but making sure that we're dealing with progressively
 smaller or simpler problems is a good rule of thumb.
3. Each call of the function represents a complete handling of the given task.

In recursion we have to use a starter function that will initialize any data and get the parameters in a form that will 
be easy to work with. Once things are ready, the starter function calls the recursive function that will do the rest of 
the work.

**Direct and indirect** -> A function is called direct recursive if it calls the same function fun. A function is called
indirect recursive if it calls another function say fun_new and fun_new calls it directly or indirectly.

**Tailed and non-tailed recursion** -> A recursive function is tail recursive when recursive call is the last thing 
executed by the function.