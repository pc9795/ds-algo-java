package companies.hubspot;

import javafx.util.Pair;

import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 02-11-2019 03:50
 * Purpose: TODO:
 **/
public class Main {

    private boolean checkSorted(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        int value = list.get(0);
        for (int i = 1; i < list.size() - 1; i++) {
            if (value > list.get(i)) {
                return false;
            }
            value = list.get(i);
        }
        return true;
    }

    public List<Integer> mergeSorted(List<Integer> list1, List<Integer> list2, int limit) {
        assert list1 != null && list2 != null;
        assert checkSorted(list1) && checkSorted(list2);
        List<Integer> result = new LinkedList<>();
        int i, j;
        int counter = 1;
        for (i = 0, j = 0; i < list1.size() || j < list2.size(); ) {
            if (list1.get(i) < list2.get(j)) {
                result.add(list1.get(i++));
            } else {
                result.add(list2.get(j++));
            }
            if (counter++ == limit) {
                return result;
            }
        }
        for (; i < list1.size(); i++) {
            result.add(list1.get(i));
            if (counter++ == limit) {
                return result;
            }
        }
        for (; j < list2.size(); j++) {
            result.add(list2.get(j));
            if (counter++ == limit) {
                return result;
            }
        }
        return result;
    }

    /**
     * t=O(n*length)
     *
     * @param str
     * @param n
     * @return
     */
    public static String mostCommonN_Gram(String str, int n) {
        assert str != null;

        //O(length)
        String[] words = str.split(" ");
        Map<String, Integer> ngramCounts = new TreeMap<>();

        for (String word : words) {
            if (word.length() < n) {
                continue;
            }
            if (word.length() == n) {
                ngramCounts.put(word, ngramCounts.getOrDefault(word, 0) + 1);
                continue;
            }

            // O(n*length + n)
            for (int i = 0; i <= word.length() - n; i++) {
                String ngram = word.substring(i, i + n);
                ngramCounts.put(ngram, ngramCounts.getOrDefault(ngram, 0) + 1);
            }
        }
        String ans = null;
        int max = Integer.MIN_VALUE;
        // bounded by O(length)
        for (String key : ngramCounts.keySet()) {
            if (ngramCounts.get(key) > max) {
                ans = key;
                max = ngramCounts.get(key);
            }
        }
        return ans;
    }

    private static boolean isPalindrome(Map<Character, Integer> charCount) {
        int odd = 0;
        for (Character ch : charCount.keySet()) {
            if (charCount.get(ch) % 2 != 0) {
                odd++;
            }
        }
        return odd == 0 || odd == 1;
    }

    private static String getPalindrome(Map<Character, Integer> charCount, int n) {
        char[] str = new char[n];
        char odd = ' ';
        int i = 0;
        for (Character ch : charCount.keySet()) {
            int count = charCount.get(ch);
            if (count % 2 != 0) {
                odd = ch;
                continue;
            }
            for (int j = 0; j < count / 2; j++) {
                str[i++] = ch;
            }
        }
        if (odd != ' ') {
            for (int j = 0; j < charCount.get(odd); j++) {
                str[i++] = odd;
            }
        }
        for (; i < n; i++) {
            str[i] = str[n - i - 1];
        }
        return new String(str);
    }

    private static Map<Character, Integer> getCharMap(String str) {
        Map<Character, Integer> charMap = new HashMap<>();
        for (Character ch : str.toCharArray()) {
            charMap.put(ch, charMap.getOrDefault(ch, 0) + 1);
        }
        return charMap;
    }

    public static List<String> convertToPalindromes(List<String> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map<Character, Integer> charMap = getCharMap(list.get(i));
            if (!isPalindrome(charMap)) {
                return Collections.singletonList("-1");
            }
            result.add(getPalindrome(charMap, list.get(i).length()));

        }
        return result;
    }

    public static Pair<Integer, Integer> getPairWithSumN(int[] arr, int sum) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        Set<Integer> set = new HashSet<>();
        Arrays.stream(arr).forEach(set::add);
        for (int i = 0; i < arr.length; i++) {
            if (set.contains(sum - arr[i])) {
                return new Pair<>(arr[i], sum - arr[i]);
            }
        }
        return null;
    }

    public static Pair<Integer, Integer> getPairWithSumN2(int[] arr, int sum) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        Arrays.sort(arr);
        for (int i = 0, j = arr.length - 1; i < j; ) {
            int temp = arr[i] + arr[j];
            if (temp == sum) {
                return new Pair<>(arr[i], arr[j]);
            }
            if (temp < sum) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }
}
