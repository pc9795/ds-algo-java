**Ukkonen Suffix Tree**

At any time, Ukkonen's algorithm builds the suffix tree for characters seen so far and so it has **online** property

We loop for all prefixes(phases) and then to suffixes for each phase(extensions). Suffix extension is all about adding the
next character into the suffix tree built so far. In extension j of phase i+1, algorithm finds the end of S[j..i], which 
is already in the tree due to previous phase i, and then it extends S[j..i] to be sure the suffix S[j..i+1] is in the tree.

Rules to add extensions.
1. If the path from the root labelled S[j..i] ends at leaf edge(i.e. S[i] is last character on leaf edge) then character
S[i+1] is just added to the end of the label on the leaf edge.
2. If the path from the root labelled S[j..i] ends at non-leaf edge(i.e. there are more characters after S[i] on path)
and next character is not S[i+1], then a new leaf edge with label S[i+1] and number j is created starting from character
S[i+1]. A new internal node will also be created if S[1..i] ends inside(in-between) a non-leaf edge.
3. If the path from the root labelled S[j..i] ends at non-leaf edge(i.e, there are more characters after S[i] on path)and
next character is S[i+1] (already in tree), do nothing.

```cmd
text = xabxac

phases(prefixes)  extensions(suffixes)
x       x
xa      xa      a
xab     xab     ab      b
xabx    xabx    abx     bx      x
xabxa   xabxa   abxa    bxa     xa      a
xabxac  xabxac  abxac   bxac    xac     ac      c

$ is for leaf node 

phase 1 extension 1
o   
|x
$

phase 2 extension 1; matched x; rule 1 
o   
|xa
$

phase 2 extension 2
  o   
|a  \xa
$    $

phase 3 extension 1; matched xa; rule 1
  o   
|a   \xab
$     $

phase 3 extension 2; matched a; rule 1
   o   
|ab   \xab
$      $

phase 3 extension 3;
     o    
 /b   |ab   \xab
$     $      $

phase 4 extension 1; matched xab; rule 1
       o    
 /b   |ab   \xabx
$     $      $

phase 4 extension 2; matched ab; rule 1
     o    
 /b   |abx   \xabx
$     $       $

phase 4 extension 3; matched b; rule 1
        o    
 /bx   |abx   \xabx
$      $       $

phase 4 extension 4; matched x; rule 3
        o    
 /bx   |abx   \xabx
$      $      $

phase 5 
extension 1;  matched xabx; rule 1
extension 2;  matched abx; rule 1
extension 3;  matched bx; rule 1
extension 4;  matched xa; rule 3
extension 5;  matched a; rule 3
        o    
 /bxa   |abxa   \xabxa
$       $        $

phase 6 
extension 1;  matched xabxa; rule 1
extension 2;  matched abxa; rule 1
extension 3;  matched bxa; rule 1
extension 4;  matched xa; rule 2
extension 5;  matched a; rule 3
extension 6;  
            o    
 /c     /bxac   |a      \xa
$      $        o        o
             /c  \bxac /c  \bxac
            $    $    $     $
```

**Suffix links**

For an internal node v with path-label xA, where x is a single character and A is a substring(can be empty), if there is
another node s(v) with path-label A, then a pointer from v to s(v) is called a suffix link.  If A is empty string, suffix
link from internal node will go to root node. There will not be any suffix link from root node(As it's not considered as 
internal node)

```cmd
            o    
 /c   /bxac     |a      \xa
$    $      ^---o<-------o
             /c  \bxac /c  \bxac
            $    $    $     $
            
suffix link from xa to a
suffix link from a to root
```

**How suffix link speeds up?**

Take previous example for phase 6 extension 1(xabxac). Now for extension 2(abxac) we just have to move one node up and 
use the suffix link to find the matching characters from root.

**Skip/Count Trick**

When walking down from node s(v) to leaf, instead of matching path character by character as we travel, we can directly 
skip to the next node if number of characters on the edge is less than number of characters we need to travel. If number
of characters on the edge is more that the number of characters we need to travel, we directly skip to the last character
on that edge. If implementation is such a way that number of characters on any edge, character at a given position in 
string S should be obtained in constant time, then skip/count trick will do that walk down in proportional to the number
of nodes on it rather than the number of characters on it.

**Edge-label compression**

So far, path labels are represented as characters in string. Such a suffix tree will take O(m^2) space to store the path
labels. To avoid this, we can use two pair of indices(start, end) on each edge for path labels, instead of substring itself.
The indices start and end tells the path label start and end position in  string S. With this, suffix tree needs O(m) space.

**Observations**
* When rule 3 applies in any extension j of phase i+1(i.e. path labelled S[j..i] continues with character S[i+1]), then 
it will also apply in all further extensions of same phase(i.e. extensions j+1 to i+1 in phase i+1). That's because if path
labelled S[j.i] continues with character S[i+1], then path labelled S[j+1..i], S[j+2..i], S[j+3..i],...,S[i..i] will also 
continue with character S[i+1]
* 
 