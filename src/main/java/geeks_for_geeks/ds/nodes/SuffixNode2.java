package geeks_for_geeks.ds.nodes;

/**
 * Created By: Prashant Chaubey
 * Created On: 09-02-2020 11:29
 **/
public class SuffixNode2 implements Comparable<SuffixNode2> {
    public int index;
    public int rank;
    public int nextRank;

    public SuffixNode2(int index, int rank) {
        this.index = index;
        this.rank = rank;
    }

    @Override
    public int compareTo(SuffixNode2 o) {
        return (rank == o.rank) ? nextRank - o.nextRank : rank - o.rank;
    }

    @Override
    public String toString() {
        return "SuffixNode2{" +
                "index=" + index +
                ", rank=" + rank +
                ", nextRank=" + nextRank +
                '}';
    }
}
