package gfg.algo.pattern_searching;

import org.junit.jupiter.api.Test;

public class BoyerMooreTest {
    @Test
    public void testSearch(){
        BoyerMoore.search("GCAATGCCTATGTGACC", "TATGTG");
    }
}
