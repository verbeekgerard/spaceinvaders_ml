package nl.gerardverbeek.genetics;

public interface Neuron {
    public void process(Double value);
    public Axon getAxonByIndex(int i);
}
