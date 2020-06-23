package geeks_for_geeks.ds.advanced;

import geeks_for_geeks.ds.advanced.trie.Applications;
import geeks_for_geeks.ds.advanced.trie.Trie;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created By: Prashant Chaubey
 * Created On: 07-02-2020 20:33
 **/
class TestTrie {
    @Test
    void testTrieOperations() {
        Trie trie = new Trie();
        trie.insert("ab");
        assert !trie.search("c");
        assert !trie.search("a");
        assert trie.search("ab");
        assert !trie.search("ac");
    }

    @Test
    void testTrieDelete() {
        Trie trie = new Trie();
        trie.insert("abcdef");
        trie.insert("pras");

        //Case 1: key may not be there in trie. Delete operation should not modify trie.
        trie.delete("ad");
        trie.delete("abd");

        //Case 2: key y present as unique key (no part of key contains another key (prefix), nor the key itself is
        // prefix of another key in trie). Delete all the nodes.
        assert trie.search("pras");
        trie.delete("pras");
        assert !trie.search("pras");

        //Case 3: Key is prefix key of another long key in trie. Unmark the leaf node.
        trie.insert("abc");
        assert trie.search("abc");
        trie.delete("abc");
        assert !trie.search("abc");

        //Case 4: Key present in trie, having atleast one other key as prefix key. Delete nodes from end of key until
        // first leaf node of longest prefix key.
        trie.insert("abcdefgh");
        assert trie.search("abcdefgh");
        trie.delete("abcdefgh");
        assert !trie.search("abcdefgh");
        assert trie.search("abcdef");

    }

    @Test
    void testLongestPrefixMatching() {
        Set<String> dict = new HashSet<>(Arrays.asList("are", "area", "base", "cat", "cater", "children", "basement"));
        assert Applications.longestPrefixMatching(dict, "caterer").equals("cater");
        assert Applications.longestPrefixMatching(dict, "basemexy").equals("base");
        assert Applications.longestPrefixMatching(dict, "child").isEmpty();
    }
}
