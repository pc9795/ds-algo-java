package geeks_for_geeks.algo.randomized_algorithms;

/*
 * E(R) = r1*p1 + r2*p2 + ..... + rk*pk
 * Expected value of random variable = possible value * probability( possible value)
 *
 * Linearity of expectation
 * ------------------------
 * E(R1 + R2) = E(R1) + E(R2) <- for both dependent and independent events.
 * E(R1 * R2) = E(R1) * E(R2) <- for independent events.
 *
 * Random Variable
 * ----------------
 * A function which maps from the set of sample space to set of real numbers.
 *
 * eg.,
 * X -> no of heads if a two coins are tossed
 * 0(TT), 1(HT|TH), 2(HH)
 * S -> {HH, HT, TH, TT} <- sample space
 *
 * X -> outcome of a dice throw
 * 1, 2, 3, 4, 5, 6
 *
 * Discrete Random Variable
 * ------------------------
 * takes on finite number of values.
 * 1. 0<=pi<=1
 * 2. sum(pi)=1
 *
 * Continuous Random Variable
 * ---------------------------
 * takes on infinite number of values
 * 1. 0<=f(x)<=1
 * 2. integration(f(x)dx)=1
 *
 * If probability of success is p in every trial, then expected number of trials until success
 * is 1/p.
 *
 * Proof
 * ------
 * E[R]=1*p+2*(1-p)*p+3(1-p)^2(p)+.....
 * no of trial*probability
 *
 * E[R]=p[1+2*(1-p)+3(1-p)^2+....  -(1)
 * (1-p)E[R]=p[1(1-p)+2*(1-p)^2+3*(1-p)^3+.....  -(2)
 *
 * (2)-(1)
 * E[R]=[1+(1-p)+(1-p)^2+.....
 *
 * Infinite GP with ratio (1-p)
 * E[R]=1/[1-(1-p)]
 * E[R]=1/p
 *
 *
 *
 *
 *
 *
 * */