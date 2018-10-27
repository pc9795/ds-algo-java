package geeks_for_geeks.algo.asymptotic_analysis;

/*
 * Asymptotic Analysis
 * --------------------
 * We calculate, how much the time (or space) taken by an algorithm increases with input size.
 *
 * Disadvantages
 * -------------
 * 1. We can't judge which one is better as we ignore constants in Asymptotic analysis.
 * 2. It might be possible that those large inputs are never given to your software and an
 * algorithm which is asymptotically slower, always performs better for your particular
 * situation
 *
 * Notations
 * ----------
 * theta notation - drop lower order terms and ignore leading constants.
 * theta(g(n))={f(n):there exists positive constants c1,c2 and n0 such that 0<=c1*g(n)
 * <=f(n)<=c2*g(n) for all n>=n0}
 *
 * big O notation -
 * O(g(n))={f(n):there exist positive constants c and n0 such that 0<=f(n)<=c*g(n) for
 * all n>=n0}
 *
 * sigma notation-
 * sigma(g(n)) = {f(n):there exist positive constants c and n0 such that 0<=c*g(n)<=
 * f(n) for all n>=n0}
 *
 * little O notation - same as big O but loose bound
 * 0<=f(n)<c*g(n)
 * limit(n->infinite) f(n)/g(n) = 0
 *
 * little sigma notation - same as sigma notation but loose bound
 * o<=c*g(n)<f(n)
 * limit(n->infinite) f(n)/g(n) = infinite
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * */