package nl.gerardverbeek.genetics;

import nl.gerardverbeek.simulation.Game;

public interface Neuron {
    public void process(Double value);
    public Axon getAxonByIndex(int i);
    public Gene getGene();
    public void setGene(Gene gene);
    public void setGame(Game game);
}
