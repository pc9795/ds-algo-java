package ds.advancedDs;

public class Trie {

	public Trie[] characters;
	public boolean isLeaf;

	public static void main(String[] args) {
		Trie trie = new Trie();
		String[] keys = { "she", "sells", "sea", "shore", "the", "by", "sheer" };
		for (int i = 0; i < keys.length; i++) {
			trie.insert(trie, keys[i]);
		}
		String delete = "sheer";
		System.out.println(trie.delete(trie, delete, 0, delete.length()));
		System.out.println("search:" + trie.search(trie, delete));
		System.out.println("search:" + trie.search(trie, "she"));
	}

	public Trie() {
		characters = new Trie[26];
	}

	public String toString() {
		if (this.isLeaf) {
			return "xL";
		} else {
			return "x";
		}
	}

	public void insert(Trie root, String key) {
		Trie temp = root;
		for (int i = 0; i < key.length(); i++) {
			int index = (int) key.charAt(i) - 97;
			if (temp.characters[index] == null) {
				temp.characters[index] = new Trie();
			}
			temp = temp.characters[index];
		}
		temp.isLeaf = true;
	}

	public boolean search(Trie root, String key) {
		Trie temp = root;
		for (int i = 0; i < key.length(); i++) {
			int index = (int) key.charAt(i) - 97;
			if (temp.characters[index] == null) {
				return false;
			}
			temp = temp.characters[index];
		}
		return (temp != null && temp.isLeaf);
	}

	public boolean isFree(Trie root) {
		for (int i = 0; i < root.characters.length; i++) {
			if (root.characters[i] != null) {
				return false;
			}
		}
		return true;
	}

	public int index(char ch) {
		return (int) ch - 97;
	}

	public boolean delete(Trie root, String key, int level, int len) {
		System.out.println("for " + ((level > 0) ? key.charAt(level - 1) : "start"));
		System.out.println((root == null) ? "empty" : "present");
		if (root != null) {
			if (level == len) {
				root.isLeaf = false;
				if (isFree(root)) {
					return true;
				}
				return false;
			} else {
				int index = index(key.charAt(level));
				if (delete(root.characters[index], key, level + 1, len)) {
					root.characters[index] = null;
					return !root.isLeaf && isFree(root);
				}

			}
		}
		return false;
	}
}
