package gfg.ds.heap;

import java.util.*;

/** @noinspection WeakerAccess */
public class Applications {

  /** t=O(k+(n-k)*log k) We can also create a max heap and call extract max k times. O(k*log n) */
  public static int[] kLargestElements(int arr[], int k) {
    assert arr.length != 0 && k <= arr.length;

    // Min heap.
    PriorityQueue<Integer> heap = new PriorityQueue<>();
    // O(k)
    for (int i = 0; i < k; i++) {
      heap.add(arr[i]);
    }
    // O(n-k)
    for (int i = k; i < arr.length; i++) {
      if (arr[i] > heap.peek()) {
        heap.poll();
        // O(log k)
        heap.add(arr[i]);
      }
    }
    Integer[] temp = new Integer[k];
    temp = heap.toArray(temp);
    int[] kLargest = new int[k];
    for (int i = 0; i < k; i++) {
      kLargest[i] = temp[i];
    }
    return kLargest;
  }

  /**
   * t=O(k+(n-k)*log k) We can also use a BST but it need extra pointers for left, right and also
   * for maintaining min/max for O(1) getMin()
   */
  public static void sortNearlySortedArray(int arr[], int k) {
    assert k >= 1 && k < arr.length;

    PriorityQueue<Integer> heap = new PriorityQueue<>();
    // O(k)
    for (int i = 0; i <= k; i++) {
      heap.add(arr[i]);
    }
    // O((n-k)*log k)
    for (int i = k + 1; i < arr.length; i++) {
      // if k is 0 then it will fail here.
      arr[i - k - 1] = heap.poll();
      heap.add(arr[i]);
    }

    // O(k*logk)
    for (int i = arr.length - 1 - k - 1 + 1; i < arr.length; i++) {
      arr[i] = heap.poll();
    }
  }

  public static Map<String, Integer> kMostFrequentWords(List<String> words, int k) {
    class FrequencyInfo {
      private String word;
      private int frequency;

      private FrequencyInfo(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
      }

      @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FrequencyInfo frequencyInfo = (FrequencyInfo) o;

        if (frequency != frequencyInfo.frequency) return false;
        return word != null ? word.equals(frequencyInfo.word) : frequencyInfo.word == null;
      }

      @Override
      public int hashCode() {
        int result = word != null ? word.hashCode() : 0;
        result = 31 * result + frequency;
        return result;
      }

      @Override
      public String toString() {
        return "FrequencyInfo{" + "word='" + word + '\'' + ", frequency=" + frequency + '}';
      }
    }

    PriorityQueue<FrequencyInfo> minHeap =
        new PriorityQueue<>(Comparator.comparingInt(o -> o.frequency));
    Set<String> inHeap = new HashSet<>();
    Map<String, FrequencyInfo> dictionary = new HashMap<>(); // We can also use Trie

    for (String word : words) {
      FrequencyInfo frequencyInfo = dictionary.getOrDefault(word, new FrequencyInfo(word, 0));

      if (inHeap.contains(word)) {
        assert dictionary.containsKey(word);
        // If we have used our own implementation of heap we could just edit the frequency of the
        // node
        // corresponding to the word in heap by storing its index in the frequency info object and
        // then calling
        // heapify. Currently we are removing the node and then adding it that increases the time
        // complexity
        // from O(log n) to O(n). Keep in mind if using in future.
        minHeap.remove(frequencyInfo);
        frequencyInfo.frequency++;
        minHeap.add(frequencyInfo);
      } else if (inHeap.size() < k) {
        frequencyInfo.frequency++;
        minHeap.add(frequencyInfo);
        inHeap.add(word);
      } else {
        frequencyInfo.frequency++;
        FrequencyInfo minimum = minHeap.peek();
        assert minimum != null;

        if (minimum.frequency < frequencyInfo.frequency) {
          minHeap.poll();
          inHeap.remove(minimum.word);
          minHeap.add(frequencyInfo);
          inHeap.add(word);
        }
      }
      dictionary.put(word, frequencyInfo);
    }

    Map<String, Integer> kFrequentWords = new HashMap<>();
    for (FrequencyInfo frequencyInfo : minHeap) {
      kFrequentWords.put(frequencyInfo.word, frequencyInfo.frequency);
    }

    return kFrequentWords;
  }
}
