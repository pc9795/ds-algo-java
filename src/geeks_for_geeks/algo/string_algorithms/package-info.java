package geeks_for_geeks.algo.string_algorithms;
/*
 * | a | b | a | b | a |  b  |  a  |
 * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14
 *
 * indexes = 0 - length-1
 * positions = N indexes + N-1 position between indexes + 2 far right and left.
 *           = 2*N+1
 *
 * even positions are not representing characters.
 *
 * L <- longest palindromic sequence
 * L[i]=d
 * substring from position i-d to i+d is a palindrome of length d.
 * substring from index (i-d)/2 to [(i+d)/2+1] is a palindrome of length d.
 *
 * Manachers Algo
 * --------------
 * 1. centerPosition=position for which LPS length is calculated; let LPS length be d.
 * 2. centerRightPosition=centerPosition+d
 * 3. centerLeftPosition=centerPosition-d
 * 4. currentRightPosition-position which is right of the centerPosition for which LPS length
 * is not yet known and has to be calculated.
 * 5. currentLeftPosition-position on the left side of centerPosition which corresponds to
 * the currentRightPosition.
 * centerPosition-currentLeftPosition=currentRightPosition-centerPosition
 * currentLeftPosition=2*centerPosition-currentRightPosition
 * 6. i-left palindrome= palindrome at currentLeftPosition
 * 7. i-right palindrome=palindrome at currentRightPosition
 * 8. center palindrome=palindrome at centerPosition
 *
 * Case 1: [No new comparisons needed]
 * -------
 * if L[currentLeftPosition]<centerRightPosition-currentRightPosition.
 * the palindrome at currentLeftPosition is contained in center palindrome.
 * the palindrome at currentRightPosition is contained in center palindrome.
 * L[currentRightPosition]=L[currentLeftPosition]
 *
 *
 * Case 2: [No new comparisons needed]
 * -------
 * if L[currentLeftPosition]=centerRightPosition-currentLeftPosition
 * the palindrome at currentLeftPosition is prefix of center palindrome.
 * the palindrome at currentRightPosition is suffix of center palindrome.
 * L[currentRightPosition]=L[currentLeftPosition]
 *
 * Case 3: [New comparisons needed]
 * -------
 * the palindrome at currentLeftPosition is prefix of center palindrome.
 * the palindrome at currentRightPosition is not suffix of center palindrome.
 * L[currentRightPosition]>L[currentLeftPosition]
 *
 * Case 4: [New comparisons needed]
 * --------
 * the palindrome at currentLeftPosition is not prefix of center palindrome.
 * the palindrome at currentRightPosition is not suffix of center palindrome.
 * L[currentRightPosition]>L[currentLeftPosition]
 *
 * We change centerPosition to currentRightPosition if palindrome centered at currentRight
 * position expands beyond centerRightPosition.
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