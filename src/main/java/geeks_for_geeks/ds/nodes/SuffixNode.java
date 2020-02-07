package geeks_for_geeks.ds.nodes;

public class SuffixNode implements Comparable<SuffixNode> {
    public int index;
    private String suffix;

    public SuffixNode(int index, String suffix) {
        this.index = index;
        this.suffix = suffix;
    }

    @Override
    public int compareTo(SuffixNode o) {
        return this.suffix.compareTo(o.suffix);
    }
}