package gfg.ds.advanced.trie;

// only works for 'a'-'z'
public class Trie {
  private static final int ALPHABET_SIZE = 26;
  public TrieNode root;

  public Trie() {
    this.root = new TrieNode(ALPHABET_SIZE);
  }

  public int toIndex(char ch) {
    return ch - 'a';
  }

  // t=M; M is the key length
  // s=M*n; n is no of keys in the trie
  public void insert(String key) {
    key = key.toLowerCase();
    TrieNode curr = root;
    for (char ch : key.toCharArray()) {
      curr = curr.getOrCreateChild(toIndex(ch));
    }
    curr.isEndOfWord = true;
  }

  // t=M; M is the key length
  public boolean search(String key) {
    key = key.toLowerCase();
    TrieNode curr = root;
    for (char ch : key.toCharArray()) {
      curr = curr.getChild(toIndex(ch));
      if (curr == null) {
        return false;
      }
    }
    return curr != null && curr.isEndOfWord;
  }

  public boolean prefixSearch(String prefix) {
    prefix = prefix.toLowerCase();
    TrieNode curr = root;
    for (char ch : prefix.toCharArray()) {
      curr = curr.getChild(toIndex(ch));
      if (curr == null) {
        return false;
      }
    }
    return curr != null;
  }

  // t=M; M is the key length
  public void delete(String key) {
    key = key.toLowerCase();
    deleteUtil(key, 0, root);
  }

  private TrieNode deleteUtil(String key, int index, TrieNode curr) {
    if (curr == null) {
      return null;
    }
    if (index == key.length()) {
      curr.isEndOfWord = false;
      return curr.isEmpty() ? null : curr;
    }
    int childIndex = toIndex(key.charAt(index));
    curr.updateChild(childIndex, deleteUtil(key, index + 1, curr.getChild(childIndex)));
    if (curr.isEmpty() && !curr.isEndOfWord) {
      return null;
    }
    return curr;
  }

  public static class TrieNode {
    private final TrieNode[] children;
    public boolean isEndOfWord;

    public TrieNode(int alphabetSize) {
      children = new TrieNode[alphabetSize];
    }

    public TrieNode getChild(int index) {
      return children[index];
    }

    public TrieNode getOrCreateChild(int index) {
      if (children[index] == null) {
        children[index] = new TrieNode(children.length);
      }
      return children[index];
    }

    public void updateChild(int index, TrieNode child) {
      children[index] = child;
    }

    public boolean isEmpty() {
      for (TrieNode child : children) {
        if (child != null) {
          return false;
        }
      }
      return false;
    }
  }
}
