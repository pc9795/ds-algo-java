package gfg.algo.greedy_algorithms;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HuffmanCoding {

  static class Data {
    String c;
    int freq;
    Data left;
    Data right;

    public Data(String c, int freq) {
      this.c = c;
      this.freq = freq;
    }

    @Override
    public String toString() {
      return "Data{" + "c=" + c + ", freq=" + freq + '}';
    }
  }

  // t=nlogn
  public static void huffmanCoding(Data[] arr) {
    PriorityQueue<Data> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.freq));
    heap.addAll(Arrays.asList(arr));

    while (heap.size() != 1) {
      Data first = heap.poll();
      Data second = heap.poll();
      Data combined = new Data(first.c + second.c, first.freq + second.freq);
      combined.left = first;
      combined.right = second;
      heap.add(combined);
    }
    printHuffmanCodes(heap.peek(), "");
  }

  private static void printHuffmanCodes(Data root, String code) {
    if (root == null) {
      return;
    }
    if (root.right == null && root.left == null) {
      System.out.println("huffman code of " + root.c + ":" + code);
    } else {
      printHuffmanCodes(root.left, code + "0");
      printHuffmanCodes(root.right, code + "1");
    }
  }

  // t=n
  public static void huffmanCoding2(Data[] arr) {
    Arrays.sort(arr, Comparator.comparingInt(o -> o.freq));
    ArrayDeque<Data> first = new ArrayDeque<>(Arrays.asList(arr));
    ArrayDeque<Data> second = new ArrayDeque<>();
    while (!(first.isEmpty() && second.size() == 1)) {
      Data min1 = findMin(first, second);
      Data min2 = findMin(first, second);
      Data combined = new Data(min1.c + min2.c, min1.freq + min2.freq);
      combined.left = min1;
      combined.right = min2;
      second.add(combined);
    }
    printHuffmanCodes(second.peek(), "");
  }

  private static Data findMin(ArrayDeque<Data> first, ArrayDeque<Data> second) {
    if(first.isEmpty()&&second.isEmpty()){
      throw new RuntimeException("Both can't be empty");
    }
    if (first.isEmpty()) {
      return second.poll();
    }
    if (second.isEmpty()) {
      return first.poll();
    }
    if (first.peek().freq < second.peek().freq) {
      return first.poll();
    } else {
      return second.poll();
    }
  }
}
