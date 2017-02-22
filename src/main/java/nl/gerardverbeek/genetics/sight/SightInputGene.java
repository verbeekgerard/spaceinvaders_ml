package nl.gerardverbeek.genetics.sight;

import nl.gerardverbeek.genetics.Gene;

import java.util.concurrent.ThreadLocalRandom;

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
    public void setEnrichmentValue(double val) {

    }

    @Override
    public void setSleepTime(long val) {

    }


    @Override
    public double getEnrichmentValue() {return 0;}

    @Override
    public long getSleepTime() {
        return 0;
    }

    @Override
    public void mutate(double val) {
        int randomInt = ThreadLocalRandom.current().nextInt(0, 2);
        if(randomInt > 0 ){
            treshold = treshold*(1+val);
        } else {
            treshold = treshold*(1-val);
        }
    }

}
