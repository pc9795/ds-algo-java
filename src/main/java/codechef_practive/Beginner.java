package codechef_practive;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created By: Prashant Chaubey
 * Created On: 07-04-2019 12:34
 * Purpose: TODO:
 **/
public class Beginner {

    public static void candy123() {
        Scanner in = null;
        in = new Scanner(System.in);

        int t = in.nextInt();
        in.nextLine();
        int[] limck = new int[1000];
        int[] bob = new int[1000];
        limck[0] = 1;
        bob[0] = 2;
        for (int i = 1; i < 1000; i++) {
            limck[i] = limck[i - 1] + 2 * i + 1;
            bob[i] = bob[i - 1] + 2 * (i + 1);
        }
        for (int _t = 0; _t < t; _t++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int maxLimck = Arrays.binarySearch(limck, a);
            int maxBob = Arrays.binarySearch(bob, b);
            maxLimck = maxLimck < 0 ? -(maxLimck + 1) - 1 : maxLimck;
            maxBob = maxBob < 0 ? -(maxBob + 1) - 1 : maxBob;
            System.out.println(maxLimck > maxBob ? "Limak" : "Bob");

        }
        in.close();
    }

    public static void chrl2() {
        Scanner in = null;
        in = new Scanner(System.in);

        HashMap<Character, Integer> map = new HashMap<>();
        map.put('C', 0);
        map.put('H', 0);
        map.put('E', 0);
        map.put('F', 0);
        HashMap<Character, Integer> seqCharToPos = new HashMap<>();
        seqCharToPos.put('C', 0);
        seqCharToPos.put('H', 1);
        seqCharToPos.put('E', 2);
        seqCharToPos.put('F', 3);
        HashMap<Integer, Character> posToSeqChar = new HashMap<>();
        posToSeqChar.put(0, 'C');
        posToSeqChar.put(1, 'H');
        posToSeqChar.put(2, 'E');
        posToSeqChar.put(3, 'F');
        String text = in.nextLine();
        for (char c : text.toCharArray()) {
            int pos = seqCharToPos.get(c);
            if (pos == 0) {
                map.put(c, map.get(c) + 1);
                continue;
            }
            map.put(c, Math.min(map.get(posToSeqChar.get(pos - 1)), map.get(c) + 1));
        }
        System.out.println(map.values().stream().reduce(Math::min).get());
    }

    public static void trisq() {
        Scanner in = new Scanner(System.in);
        int[] squares = new int[10001];
        squares[0] = squares[1] = 0;
        for (int i = 2; i < squares.length; i++) {
            squares[i] = (i / 2) - 1 + squares[i - 2];
        }
        int t = in.nextInt();
        for (int _t = 0; _t < t; _t++) {
            int size = in.nextInt();
            System.out.println(squares[size]);
        }
        in.close();
    }

    public static void muffins3() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int _t = 0; _t < t; _t++) {
            int n = in.nextInt();
            System.out.println(n / 2 + 1);
        }
        in.close();
    }
}


