package nl.gerardverbeek.layer;

import nl.gerardverbeek.genetics.Axon;
import nl.gerardverbeek.genetics.Neuron;

import java.util.List;
import java.util.stream.Collectors;

public class HiddenLayer implements Layer{

    List<Neuron> neurons;

    public HiddenLayer(List<Neuron> neurons){
        this.neurons = neurons;
    }

    @Override
    public void processAllNeurons() {
    }

    public List<Axon> getAxonsByIndex(int i){
        return neurons.stream().map(n->n.getAxonByIndex(i)).collect(Collectors.toList());
    }
}
