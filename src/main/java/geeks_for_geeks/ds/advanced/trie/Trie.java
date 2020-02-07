package geeks_for_geeks.ds.advanced.trie;

import geeks_for_geeks.ds.nodes.TrieNode;

/**
 * Created By: Prashant Chaubey
 * Created On: 07-02-2020 20:19
 * It only works for lower case characters.
 **/
public class Trie {
    private static final int ALPHABET_SIZE = 26;
    public TrieNode root;

    public Trie() {
        this.root = new TrieNode(ALPHABET_SIZE);
    }

    public int toIndex(char ch) {
        return ch - 'a';
    }

    /**
     * t=O(M); M is the key length
     * s=O(ALPHABET_SIZE*M*n); n is the no of keys in the trie.
     *
     * @param key key to insert
     */
    public void insert(String key) {
        key = key.toLowerCase();
        TrieNode curr = root;
        for (char ch : key.toCharArray()) {
            curr = curr.getOrCreateChild(toIndex(ch));
        }
        curr.isEndOfWord = true;
    }

    /**
     * t=O(M); M is the key length.
     * In BST this will take O(M*log n) where n is the number of keys.
     *
     * @param key key to search
     * @return true if key is found.
     */
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

    /**
     * t=O(M); M is the key length
     *
     * @param key key to delete
     */
    public void delete(String key) {
        key = key.toLowerCase();
        deleteUtil(key, 0, root);
    }

    private TrieNode deleteUtil(String key, int index, TrieNode curr) {
        //Key doesn't exist.
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
}
