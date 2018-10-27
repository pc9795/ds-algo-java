package geeks_for_geeks.algo.searching_and_sorting;

/*
 * Jump search
 * ------------
 * We have an array of size n and block to be jumped size m. Then we search at the indices
 * arr[0], arr[m], arr[2m]...arr[km] and so on. Once we find the interval (arr[km]<x<arr[(k+1)m]),
 * we perform a linear search operation from the index km to find the element x.
 *
 * In worst case, we have to do n/m jumps and if the last checked value is greater than the
 * element to be searched for, we perform m-1 comparisons more of linear search. Therefore the
 * total number of comparisons in the worst case will be ((n/m)+m-1). The value of the function
 * will be minimum when m=sqrt(n). Therefore, the best step size id m=sqrt(n).
 *
 * T=O(sqrt n)
 *
 * Interpolation search
 * ---------------------
 * pos = lo+[(x-arr[lo])*(hi-lo)/(arr[hi]-arr[lo])]
 * It will return higher value of pos when element to be searched is closer to arr[hi]. And
 * smaller value when closer to arr[lo].
 *
 * T=O(log log n) (best case)<- values are uniformly distributed.
 *  =O(n) (worst case)
 *
 * Exponential search
 * -------------------
 * Start with subarray size 1, compare its last element with x, then try size 2, then 4 and
 * so on until last element of a subarray is not greater. Once we find and index i (after repeated
 * doubling of i), we know that the element must be present between i/2 and i. Do binary search.
 *
 * T=O(log n)
 *
 * Applications
 * ------------
 * 1. Useful for unbounded searches, where size of array is infinite.
 * 2. It works better than binary search for bounded arrays, and also when the element to be
 * searched is closer to the first element.
 *
 * Binary search vs ternary search
 * --------------------------------
 * T(n)=T(n/2)+2, T(1)=1 <-binary search
 * T(n)=T(n/3)+4, T(1)=1 <-ternary search
 *
 * Worst case
 * 2*c*log2(n) + O(1) <-binary search
 * 4*c*log3(n) + O(1) <-ternary search
 *
 * 2*c*log2(n) ? 4*c*log3(n)
 * log2(n) ? 2*log3(n)
 * log2(n) ? 2/(log2(3))*log2(n)
 *
 * 2/log2(3) > 1 therefore ternary search do more comparisons.
 *
 * Binary Insertion sort
 * ---------------------
 * We use binary search to reduce the number of comparisons in normal insertion sort. The algo
 * as whole still has a running worst case running time of O(n^2) because of the series of swaps
 * required for each insertion.
 *
 * Merge sort
 * ----------
 * T=O(logn) (all cases)
 * A=O(n)
 * stable
 * not in place
 *
 * Online algorithm
 * ----------------
 * one that can process its input piece-by-piece in a serial fashion, without having the entire input available from the
 * start.
 *
 * Heap sort
 * ----------
 * T=O(nlogn)
 * not stable
 * in place
 *
 * Quick Sort
 * ----------
 * T(n) = T(k) + T(n-k-1) + O(n)
 * T(n)=T(n-1)+O(n)=O(n^2) (worst case)
 * T(n)=2T(n/2)+O(n)=n*logn (best and average case)
 *
 * 3 way Quick sort
 * ----------------
 * arr[l...r] is divided into 3 parts.
 * a.)arr[l...i] elements less than pivot
 * b.)arr[i+1...j-1] elements equal to pivot
 * c.)arr[j+1...r] elements greater than pivot
 *
 * Quick vs Merge Sort
 * --------------------
 * for arrays (Quick sort)
 * Merge sort - O(N) extra space
 * Quick sort - cache friendly; tail recursive optimization can be applied.
 *
 * for linked lists (Merge sort)
 * Merge sort - no extra space.
 * Quick sort - can't access by index
 *
 * Radix Sort
 * -----------
 * O(d*(n+b))
 * d is the number of digits
 * b is the base of numbers
 *
 * if k is maximum value then d=logb(k)
 *
 * if k<=n^c (c is some constant)
 * O(logb(k)*(n+b))
 * O(logb(n)*(n+b))
 *
 * if b=n
 * T=O(n)
 *
 * range from 1 - n^c and values are in base n.
 *
 * Pigeonhole sort is similar to count sort but differs that it "moves item twice: once in the bucket array and again
 * to the final destination".
 *
 * Stability of sorting algorithms
 * --------------------------------
 * A sorting algorithm is said to be stable if two objects with equal keys appear in the same order
 * in sorted output as they appear in the input array to be sorted.
 *
 * When equal elements are indistinguishable, such as with integers, or more generally, any data where
 * the entire element is the key, stability iis not an issue. Stability is also not an issue if all keys
 * are different.
 *
 * Quick sort - during replacement of an element which is less than pivot it can happen the value can go back in the
 * array.
 *
 * Quick sort and heap sort can be made stable by extra space O(n).
 *
 * 
 *
 *
 *
 * */