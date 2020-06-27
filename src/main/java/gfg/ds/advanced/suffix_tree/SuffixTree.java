package gfg.ds.advanced.suffix_tree;

import gfg.ds.advanced.trie.Trie;

/**
 * Created By: Prashant Chaubey
 * Created On: 09-02-2020 13:08
 **/
public class SuffixTree {
    //Ideally it should be a compressed trie.
    private Trie trie;

    public SuffixTree(String input) {
        trie = new Trie();
        for (int i = 0; i < input.length(); i++) {
            trie.insert(input.substring(i));
        }
    }


    /**
     * t=O(m)
     *
     * @param pattern text to search
     * @return true if pattern is found
     */
    public boolean search(String pattern) {
        return trie.prefixSearch(pattern);
    }
}
