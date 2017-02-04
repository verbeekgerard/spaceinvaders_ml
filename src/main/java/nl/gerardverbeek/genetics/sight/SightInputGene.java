package nl.gerardverbeek.genetics.sight;

public class SightInputGene {
    private int treshold = 0;

    public SightInputGene(int treshold) {
        this.treshold = treshold;
    }

    public int getTreshold() {
        return treshold;
    }

    public void setTreshold(int treshold) {
        this.treshold = treshold;
    }
}
