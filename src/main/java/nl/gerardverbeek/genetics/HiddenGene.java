package nl.gerardverbeek.genetics;

import java.util.concurrent.ThreadLocalRandom;

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

    @Override
    public void mutate(double val) {
        int randomInt = ThreadLocalRandom.current().nextInt(0, 2);
        if(randomInt > 0 ){
            treshold = treshold*(1+val);
            enrichmentValue = enrichmentValue*(1+val);
        } else {
            treshold = treshold*(1-val);
            enrichmentValue = enrichmentValue*(1-val);
        }
    }

    public void setEnrichmentValue(double enrichmentValue) {
        this.enrichmentValue = enrichmentValue;
    }

    @Override
    public void setSleepTime(long val) {
    }
}
