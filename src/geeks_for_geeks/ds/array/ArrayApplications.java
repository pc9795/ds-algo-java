package geeks_for_geeks.ds.array;

import java.util.ArrayList;

/**
 * Created By: Prashant Chaubey
 * Created On: 04-10-2018 02:09
 **/
public class ArrayApplications {
    public static void findLeaders(int arr[]) {
        if (arr.length == 0) {
            System.out.println("Leaders:{}");
            return;
        }
        ArrayList<Integer> leaders = new ArrayList<>();
        leaders.add(arr[arr.length - 1]);
        int highFromEnd = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > highFromEnd) {
                leaders.add(arr[i]);
                highFromEnd = arr[i];
            }
        }
        System.out.println("Leaders:" + leaders);
    }

    public static void main(String[] args) {
        findLeaders(new int[]{16, 17, 4, 3, 5, 2});
    }
}
