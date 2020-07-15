package gfg.ds.advanced.trie;

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
    void testSearch() {
        Trie trie = new Trie();
        trie.insert("ab");

        assert !trie.search("c");
        assert !trie.search("a");
        assert !trie.search("ac");
        assert trie.search("ab");
    }

    @Test
    void testDeleteKeysNotPresent() {
        Trie trie = new Trie();
        trie.insert("abcdef");
        trie.insert("pras");

        trie.delete("ad");
        trie.delete("abd");

        assert trie.search("abcdef");
        assert trie.search("pras");
    }

    // In a unique key no part of key contains another key (prefix), nor the key itself is prefix of another key in trie)
    @Test
    void testDeleteUniqueKey() {
        Trie trie = new Trie();
        trie.insert("abcdef");
        trie.insert("pras");

        trie.delete("pras");
        assert !trie.search("pras");
        assert trie.search("abcdef");
    }

    @Test
    void testDeletePrefixKey() {
        Trie trie = new Trie();
        trie.insert("abcdef");
        trie.insert("abc");

        trie.delete("abc");

        assert !trie.search("abc");
        assert trie.search("abcdef");
    }

    @Test
    void testDeleteKeyContainingOtherKey(){
        Trie trie = new Trie();
        trie.insert("abcdef");
        trie.insert("abcdefgh");

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
