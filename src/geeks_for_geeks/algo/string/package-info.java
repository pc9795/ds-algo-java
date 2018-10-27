package geeks_for_geeks.algo.string;
/*
 * | a | b | a | b | a |  b  |  a  |
 * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14
 *
 * indexes = 0 - length-1
 * positions = N indexes + N-1 position between indexes + 2 far right and left.
 *           = 2*N+1
 *
 * L <- longest palindromic sequence
 * L[i]=d
 * substring from position i-d to i+d is a palindrome of length d.
 * substring from index (i-d)/2 to [(i+d)/2+1] is a palindrome of length d.
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
 *
 *
 * */