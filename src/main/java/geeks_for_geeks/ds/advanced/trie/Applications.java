package geeks_for_geeks.ds.advanced.trie;

import geeks_for_geeks.ds.nodes.TrieNode;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Set;

/**
 * Created By: Prashant Chaubey
 * Created On: 07-02-2020 22:50
 **/
public class Applications {
    public static String longestPrefixMatching(Set<String> dictionary, String input) {
        Trie trie = new Trie();
        input = input.toLowerCase();
        for (String word : dictionary) {
            trie.insert(word);
        }
        ArrayDeque<TrieNode> stack = new ArrayDeque<>();
        TrieNode curr = trie.root;
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
