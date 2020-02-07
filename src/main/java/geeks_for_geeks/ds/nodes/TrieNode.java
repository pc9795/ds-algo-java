package geeks_for_geeks.ds.nodes;

import sun.reflect.generics.tree.Tree;

/**
 * Created By: Prashant Chaubey
 * Created On: 07-02-2020 20:15
 **/
public class TrieNode {
    private TrieNode[] children;
    public boolean isEndOfWord;

    public TrieNode(final int ALPHABET_SIZE) {
        children = new TrieNode[ALPHABET_SIZE];
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

    public void deleteChild(int index) {
        children[index] = null;
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
