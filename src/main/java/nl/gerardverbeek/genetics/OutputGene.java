package nl.gerardverbeek.genetics;

public class OutputGene implements Gene {

    private long sleepTime;

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
        return sleepTime;
    }
}
