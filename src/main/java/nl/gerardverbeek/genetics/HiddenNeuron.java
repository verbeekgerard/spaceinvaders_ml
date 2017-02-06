package nl.gerardverbeek.genetics;

import nl.gerardverbeek.simulation.Game;

import java.util.List;

public class HiddenNeuron implements Neuron {

    private List<Axon> axons;
    private Gene hiddenGene;

    public HiddenNeuron(Gene hiddenGene){
        this.hiddenGene = hiddenGene;
    }

    @Override
    public void process(Double value) {
        if (value > hiddenGene.getTreshold()) {
            Double enrichtValue = value * (hiddenGene.getEnrichmentValue());
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

    @Override
    public Gene getGene() {
        return null;
    }

    @Override
    public void setGene(Gene gene) {
        this.hiddenGene = gene;
    }

    @Override
    public void setGame(Game game) {

    }
}
