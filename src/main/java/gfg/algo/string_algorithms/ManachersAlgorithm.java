package gfg.algo.string_algorithms;

/**
 * Created By: Prashant Chaubey
 * Created On: 11-11-2018 22:51
 **/
public class ManachersAlgorithm {

    /**
     * T=O(n)
     *
     * @param text
     * @return
     */
    public static String longestPalindromicSubstring(String text) {
        if (text == null || text.length() == 0) {
            System.out.println("Input string is empty!");
            return "";
        }
        int n = 2 * text.length() + 1;//Position count
        int lps[] = new int[n]; //LPS Length Array
        lps[0] = 0;
        lps[1] = 1;
        int center = 1; //centerPosition
        int centerRight = 2; //centerRightPosition
        boolean expand = false;

        for (int currentRight = 2, currentLeft; currentRight < n; currentRight++) {
            //get currentLeftPosition  for currentRightPosition i
            currentLeft = 2 * center - currentRight;
            //Reset expand - means no expansion required
            expand = false;
            int diff = centerRight - currentRight;
            //If currentRightPosition is within centerRightPosition R
            if (diff > 0) {
                // left palindrome and right are contained in center palindrome.
                if (lps[currentLeft] < diff) // Case 1
                    lps[currentRight] = lps[currentLeft];
                    // left palindrome is prefix and right palindrome is suffix.
                else if (lps[currentLeft] == diff && currentRight == n - 1) // Case 2
                    lps[currentRight] = lps[currentLeft];
                    // left palindrome is prefix and right palindrome is suffix.
                else if (lps[currentLeft] == diff && currentRight < n - 1)  // Case 3
                {
                    lps[currentRight] = lps[currentLeft];
                    expand = true;  // expansion required
                    // left palindrome is not prefix.
                } else if (lps[currentLeft] > diff)  // Case 4
                {
                    lps[currentRight] = diff;
                    expand = false;  // expansion required
                }
            } else {
                lps[currentRight] = 0;
                expand = true;  // expansion required
            }

            if (expand) {
                //Attempt to expand palindrome centered at currentRightPosition i
                //Here for odd positions, we compare characters and
                //if match then increment LPS Length by ONE
                //If even position, we just increment LPS by ONE without
                //any character comparison
                while (true) {
//                    bound check
                    if ((currentRight + lps[currentRight]) < n && (currentRight - lps[currentRight]) >= 0) {
//                        we are calculating for the next position ignoring current therefore adding 1.
                        boolean evenPosition = ((currentRight + lps[currentRight] + 1) % 2 == 0);
                        int leftCharPosition = (currentRight - lps[currentRight] - 1) / 2;
                        int rightCharPosition = ((currentRight + lps[currentRight] + 1) / 2);
                        boolean oddPosition = rightCharPosition < text.length() && text.charAt(rightCharPosition) == text.charAt(leftCharPosition);
                        if (evenPosition || oddPosition) {
                            lps[currentRight]++;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }

            // If palindrome centered at currentRightPosition
            // expand beyond centerRightPosition,
            // adjust centerPosition based on expanded palindrome.
            if (currentRight + lps[currentRight] > centerRight) {
                center = currentRight;
                centerRight = currentRight + lps[currentRight];
            }
        }
        int maxPos = 0;
        for (int i = 0; i < lps.length; i++) {
            if (lps[maxPos] < lps[i]) {
                maxPos = i;
            }
        }
        int start = (maxPos - lps[maxPos]) / 2;
        return text.substring(start, start + lps[maxPos]);
    }
}