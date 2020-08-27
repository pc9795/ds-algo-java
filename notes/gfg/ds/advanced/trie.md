**Advantages of Trie over Hashing**

1. Trie is more powerful in case of prefix search. 
2. Time complexity of hashing is O(1) in average case, it may have collisions.

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

In Trie navigation to the next position in a search string is `O(1)` as we just use the children corresponding the 
character in that given position. However, in Ternary search trees this logic can took up `O(ALPHABET_SIZE)` due to 
expected comparisons in worst case. (My conclusion; have to check) 

#

**Applications of Tries/Ternary Search trees**:
1. Prefix search
2. Nearest neighbour word search
3. Dictionary for words
4. Alphabetical order printing

If we want to support suggestions, like google shows “did you mean …”, then we need to find the closest word in 
dictionary. The closest word can be defined as the word that can be obtained with minimum number of character 
transformations (add, delete, replace). A Naive way is to take the given word and generate all words which are 1 
edit distance  away and one by one look them in dictionary. If nothing found, then look for all words which are 2 
distant and so on.