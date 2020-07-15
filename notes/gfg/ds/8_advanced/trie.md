**Trie vs Hashing**

Trie is more powerful in case of prefix search. Moreover, time complexity of hashing is O(1) in average case, it may 
have collisions.

**Compressed Trie**

Obtained from standard Trie by joining chains of single nodes.

### Ternary Search Tree

Unlike trie where each node contains 26 pointers for its children, each node in a ternary search tree contains only 3 
pointers:
1. The left pointer points to the node whose value is less than the value in the current node.
2. The equal pointer points to the node whose value is equal to the value in the current node.
3. The right pointer points to the node whose value is greater than the value in the current node.

**Advantages over Trie**
* More space efficient (involve only three pointers per node as compared to 26 in standard tries)

In Tries navigation to next position in a string is `O(1)` as we just use the children corresponding to the index
representing the character in that given position. However, in Ternary search trees this logic can took up `O(ALPHABET_SIZE)`
due to expected comparisons in worst case. 

Applications of ternary search trees:
1. Ternary search trees are efficient for queries like “Given a word, find the next word in dictionary(near-neighbor 
lookups)” or “Find all telephone numbers starting with 9342 or “typing few starting characters in a web browser displays 
all website names with this prefix”(Auto complete feature)”.

2. Used in spell checks: Ternary search trees can be used as a dictionary to store all the words. Once the word is typed 
in an editor, the word can be parallely searched in the ternary search tree to check for correct spelling.
