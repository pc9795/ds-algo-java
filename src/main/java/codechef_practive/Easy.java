package codechef_practive;

import java.util.Scanner;

/**
 * Created By: Prashant Chaubey
 * Created On: 12-06-2019 03:08
 * Purpose: TODO:
 **/
public class Easy {

    public static void numgame2() {
        Scanner in = new Scanner(System.in);

//        9-A, 8-B, 7-B, 6-B, 5-A, 4-B, 3-B, 2-B, 1-A
        int t = in.nextInt();
        in.nextLine();
        for (int _t = 0; _t < t; _t++) {
            int n = in.nextInt();
            System.out.println(n % 4 == 1 ? "ALICE" : "BOB");
        }
        in.close();
    }


}