Go till sqrt(n) for finding prime numbers.

If a number N has a prime factor larger than sqrt(N), then it surely has a prime factor smaller than sqrt(N). So it's 
sufficient to search for prime factors in the range [1,sqrt(N)], and then use them in order to compute the prime factors
in the range [sqrt(N),N]. If no prime factors exist in the range [1,sqrt(N)], then N itself is prime and there is no need
to continue searching beyond that range.
