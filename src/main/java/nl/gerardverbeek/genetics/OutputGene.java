package nl.gerardverbeek.genetics;

import nl.gerardverbeek.util.Options;

import java.util.concurrent.ThreadLocalRandom;

public class OutputGene implements Gene {

    private Long sleepTime;

    public OutputGene(long sleepTime){
        this.sleepTime = sleepTime;
    }


    @Override
    public double getTreshold() {
        return 0;
    }

    @Override
    public double getEnrichmentValue() {
        return 0;
    }


    @Override
    public long getSleepTime() {
        return sleepTime/Options.GAME_SPEED.getLongVal();
    }

    @Override
    public void setTreshold(double val) {

    }

    @Override
    public void setEnrichmentValue(double val) {

    }

    @Override
    public void setSleepTime(long val) {
        this.sleepTime = val;
    }

    @Override
    public void mutate(double val) {
        int randomInt = ThreadLocalRandom.current().nextInt(0, 2);
        if(randomInt > 0 ){
            sleepTime = (long) (sleepTime*(1+val));
        } else {
            sleepTime = (long) (sleepTime*(1-val));
        }
    }
}
