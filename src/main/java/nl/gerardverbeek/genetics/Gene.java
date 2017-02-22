package nl.gerardverbeek.genetics;

public interface Gene {
    public double getTreshold();
    public double getEnrichmentValue();
    public long getSleepTime();

    public void setTreshold(double val);
    public void setEnrichmentValue(double val);
    public void setSleepTime(long val);



    public void mutate(double val);

}
