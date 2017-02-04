package nl.gerardverbeek.genetics;

import java.util.List;

public class HiddenNeuron implements Neuron {

    private List<Axon> axons;
    private HiddenGene hiddenGene;

    public HiddenNeuron(HiddenGene hiddenGene){
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
}
