package nl.gerardverbeek.genetics;

public class HiddenGene {

    private double treshold = 0;
    private double enrichmentValue = 0.00;


    public HiddenGene(double treshold, double enrichmentValue) {
        this.treshold = treshold;
        this.enrichmentValue = enrichmentValue;
    }


    public double getTreshold() {
        return treshold;
    }

    public void setTreshold(double treshold) {
        this.treshold = treshold;
    }

    public double getEnrichmentValue() {
        return enrichmentValue;
    }

    public void setEnrichmentValue(double enrichmentValue) {
        this.enrichmentValue = enrichmentValue;
    }
}
