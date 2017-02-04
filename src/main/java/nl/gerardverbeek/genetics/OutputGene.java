package nl.gerardverbeek.genetics;

public class OutputGene implements Gene {

    private long sleepTime;

    public OutputGene(long sleepTime){
        this.sleepTime = sleepTime;
    }

    public long getSleepTime() {
        return sleepTime;
    }
}
