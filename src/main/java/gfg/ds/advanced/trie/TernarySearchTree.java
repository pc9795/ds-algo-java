package gfg.ds.advanced.trie;

import java.util.HashSet;
import java.util.Set;

/** @noinspection WeakerAccess */
public class TernarySearchTree {
  private TernaryTreeNode root;

  /**
   * t=O(M); M is the key length The comparisons to find the next position for a character in string
   * will be constant and bounded by the ALPHABET_SIZE
   */
  public TernarySearchTree insert(String key) {
    TernaryTreeNode prev = null;
    char prevCh = '0';
    TernaryTreeNode curr = root;
    int length = key.length();

    for (int i = 0; i < length; ) {
      char ch = key.charAt(i);
      boolean isEndOfWord = i == length - 1;

      if (curr == null) {
        TernaryTreeNode newNode = new TernaryTreeNode(ch, isEndOfWord);
        if (prev == null) {
          curr = root = newNode;
        } else if (prevCh == prev.data) {
          curr = prev.equal = newNode;
        } else if (prevCh < prev.data) {
          curr = prev.left = newNode;
        } else {
          curr = prev.right = newNode;
        }
      }

      if (ch == curr.data) {
        if (isEndOfWord) {
          curr.isEndOfWord = true;
        }
        prev = curr;
        curr = curr.equal;
        i++;
      } else if (ch < curr.data) {
        prev = curr;
        curr = curr.left;
      } else {
        prev = curr;
        curr = curr.right;
      }
      prevCh = ch;
    }

    return this;
  }

  public Set<String> keys() {
    Set<String> keys = new HashSet<>();
    keysUtil(root, new StringBuilder(), keys);

    return keys;
  }

  private void keysUtil(TernaryTreeNode root, StringBuilder partialKey, Set<String> keys) {
    if (root == null) {
      return;
    }

    keysUtil(root.left, new StringBuilder(), keys);

    partialKey.append(root.data);
    if (root.isEndOfWord) {
      keys.add(partialKey.toString());
    }

    keysUtil(root.equal, partialKey, keys);

    keysUtil(root.right, new StringBuilder(), keys);
  }

  /**
   * t=O(M); M is the key length. The comparisons to find the next position for a character in
   * string will be constant and bounded by the ALPHABET_SIZE
   */
  public boolean search(String key) {
    if (isEmpty() || key.isEmpty()) {
      return false;
    }

    TernaryTreeNode prev = null;
    TernaryTreeNode curr = root;
    int length = key.length();

    for (int i = 0; i < length; ) {
      if (curr == null) {
        return false;
      }

      char ch = key.charAt(i);

      if (ch < curr.data) {
        prev = curr;
        curr = curr.left;
      } else if (ch > curr.data) {
        prev = curr;
        curr = curr.right;
      } else {
        prev = curr;
        curr = curr.equal;
        i++;
      }
    }

    return prev.isEndOfWord;
  }

  public boolean isEmpty() {
    return root == null;
  }

  public static class TernaryTreeNode {
    private TernaryTreeNode left;
    private TernaryTreeNode right;
    private TernaryTreeNode equal;
    private boolean isEndOfWord;
    private char data;

    public TernaryTreeNode(char data, boolean isEndOfWord) {
      this.data = data;
      this.isEndOfWord = isEndOfWord;
    }
  }
}
