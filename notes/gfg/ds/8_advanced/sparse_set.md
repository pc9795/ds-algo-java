How to do the following operations efficiently if there are large number of queries for them.
* Insertion
* Deletion
* Searching
* Clearing/Removing all the elements.

Sparse Set outperforms all BST, Hashing and bit vector. We assume that we are given range of data (or maximum value an 
element can have) and maximum number of elements that can be stored in set.

A common use of this data structure is with register allocation algorithms in compilers, which have a fixed universe(the 
number of registers in the machine) and are updated and cleared frequently during a single processing run.