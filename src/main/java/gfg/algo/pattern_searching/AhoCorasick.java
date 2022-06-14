package gfg.algo.pattern_searching;

import java.util.*;

public class AhoCorasick {
  private static final int MAX_CHARS = 26;
  private final int[] out;
  private final int[] failureFn;
  private final int[][] gotoFn; // it is a trie

  private AhoCorasick() {
    // should be equal to sum of the length of all keywords
    int MAX_STATES = 10;
    this.out = new int[MAX_STATES];
    this.failureFn = new int[MAX_STATES];
    Arrays.fill(failureFn, -1);
    this.gotoFn = new int[MAX_STATES][MAX_CHARS];
    for (int i = 0; i < MAX_STATES; i++) {
      Arrays.fill(gotoFn[i], -1);
    }
  }

  // t = n+m+z; n is the text length, m is total characters in all words, z is total number of
  // occurrences of words in text.
  public static Map<String, List<Integer>> search(String text, List<String> words) {
    Map<String, List<Integer>> occurences = new HashMap<>();
    AhoCorasick algo = new AhoCorasick();
    algo.build(words);
    int currentState = 0;
    for (int i = 0; i < text.length(); i++) {
      currentState = algo.nextState(currentState, text.charAt(i));
      if (algo.out[currentState] == 0) continue;
      for (int j = 0; j < words.size(); j++) {
        String word = words.get(j);
        // bitmap which have non-zero bit if word on that index is found in the current state.
        if ((algo.out[currentState] & (1 << j)) > 0) {
          if (!occurences.containsKey(word)) {
            occurences.put(word, new ArrayList<>());
          }
          occurences.get(word).add(i - word.length() + 1);
        }
      }
    }
    return occurences;
  }

  // https://www.youtube.com/watch?v=O7_w001f58c&ab_channel=NiemaMoshiri
  private void build(List<String> words) {
    buildTrie(words);
    updateStateOfCharsWithNoEdgeFromRoot();
    buildFailureFn();
  }

  private void buildTrie(List<String> words) {
    int states = 1;
    for (int i = 0; i < words.size(); i++) {
      String word = words.get(i);
      int currentState = 0;
      for (int j = 0; j < word.length(); j++) {
        int ch = word.charAt(j) - 'a';
        // create new state if node for ch doesn't exist
        if (gotoFn[currentState][ch] == -1) {
          gotoFn[currentState][ch] = states++;
        }
        currentState = gotoFn[currentState][ch];
      }
      out[currentState] |= (1 << i);
    }
  }

  private void updateStateOfCharsWithNoEdgeFromRoot() {
    for (int ch = 0; ch < MAX_CHARS; ch++) {
      if (gotoFn[0][ch] == -1) {
        gotoFn[0][ch] = 0;
      }
    }
  }

  private void buildFailureFn() {
    ArrayDeque<Integer> q = new ArrayDeque<>();
    // initialize q for bfs. it is a multi source bfs
    // all node of depth 1 have failure function value as 0;
    for (int ch = 0; ch < MAX_CHARS; ch++) {
      int state = gotoFn[0][ch];
      if (state != 0) {
        failureFn[state] = 0;
        q.add(state);
      }
    }

    while (!q.isEmpty()) {
      int state = q.poll();
      for (int ch = 0; ch < MAX_CHARS; ch++) {
        int nextState = gotoFn[state][ch];
        if (nextState == -1) continue;
        int failure = failureFn[state];
        // deepest node labeled by proper suffix of string from root to current state.
        while (gotoFn[failure][ch] == -1) {
          failure = failureFn[failure];
        }
        failure = gotoFn[failure][ch];
        failureFn[nextState] = failure;
        out[nextState] |= out[failure];
        q.add(nextState);
      }
    }
  }

  private int nextState(int state, char ch) {
    int nextState = state;
    while (gotoFn[nextState][ch - 'a'] == -1) {
      nextState = failureFn[nextState];
    }
    return gotoFn[nextState][ch - 'a'];
  }
}
