package nl.gerardverbeek.genetics.sight;

import nl.gerardverbeek.genetics.Gene;

public class SightInputGene implements Gene {
    private double treshold = 0;

    public SightInputGene(double treshold) {
        this.treshold = treshold;
    }

    @Override
    public double getTreshold() {
        return treshold;
    }
    public void setTreshold(double treshold) {
        this.treshold = treshold;
    }


    @Override
    public double getEnrichmentValue() {return 0;}

    @Override
    public long getSleepTime() {
        return 0;
    }

}
