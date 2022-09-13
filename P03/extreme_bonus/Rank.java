public class Rank { 
    public static final int MIN = 0;
    public static final int MAX = 9;
    public final int rank;
    
    public Rank (int rank) {
        this.rank = rank;
    }

    public String toString() {
        return "" + rank;
    }
}
    