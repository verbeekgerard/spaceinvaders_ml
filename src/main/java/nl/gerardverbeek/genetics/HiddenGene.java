package nl.gerardverbeek.genetics;

public class HiddenGene implements Gene {

    private double treshold = 0;
    private double enrichmentValue = 0.00;


    public HiddenGene(double treshold, double enrichmentValue) {
        this.treshold = treshold;
        this.enrichmentValue = enrichmentValue;
    }


    @Override
    public double getTreshold() {
        return treshold;
    }

    public void setTreshold(double treshold) {
        this.treshold = treshold;
    }

    @Override
    public double getEnrichmentValue() {
        return enrichmentValue;
    }

    @Override
    public long getSleepTime() {
        return 0;
    }

    public void setEnrichmentValue(double enrichmentValue) {
        this.enrichmentValue = enrichmentValue;
    }
}
