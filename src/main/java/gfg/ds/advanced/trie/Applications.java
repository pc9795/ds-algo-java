package gfg.ds.advanced.trie;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Set;

/** @noinspection WeakerAccess */
public class Applications {
  public static String longestPrefixMatching(Set<String> dictionary, String input) {
    Trie trie = new Trie();
    input = input.toLowerCase();
    for (String word : dictionary) {
      trie.insert(word);
    }
    ArrayDeque<Trie.TrieNode> stack = new ArrayDeque<>();
    Trie.TrieNode curr = trie.root;
    for (char ch : input.toCharArray()) {
      curr = curr.getChild(trie.toIndex(ch));
      if (curr == null) {
        break;
      }
      stack.push(curr);
    }
    while (!stack.isEmpty() && !Objects.requireNonNull(stack.peek()).isEndOfWord) {
      stack.pop();
    }
    return input.substring(0, stack.size());
  }
}
