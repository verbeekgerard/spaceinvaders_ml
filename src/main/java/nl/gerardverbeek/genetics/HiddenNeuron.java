package nl.gerardverbeek.genetics;

import nl.gerardverbeek.simulation.Game;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class HiddenNeuron implements Neuron {

    private List<Axon> axons;

    private double treshold = 0;
    private double enrichmentValue = 0.00;

    public HiddenNeuron(){
        treshold = ThreadLocalRandom.current().nextDouble(0, Game.getMaxAliens() + 1);
        enrichmentValue = ThreadLocalRandom.current().nextDouble(1, 2);
    }

    @Override
    public void process(Double value) {
        if (value > treshold) {
            Double enrichtValue = value * (enrichmentValue);
            axons.stream().forEach(a-> a.process(enrichtValue));
        }
    }

    public void setAxons(List<Axon> axons) {
        this.axons = axons;
    }

    @Override
    public Axon getAxonByIndex(int i) {
        if(i>axons.size()){
            return null;
        }
        return axons.get(i);
    }
}
