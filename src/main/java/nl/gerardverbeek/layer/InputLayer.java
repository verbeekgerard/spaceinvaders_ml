package nl.gerardverbeek.layer;

import nl.gerardverbeek.genetics.Axon;
import nl.gerardverbeek.genetics.Neuron;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InputLayer implements Layer {

    List<Neuron> neurons = new ArrayList<>();

    public InputLayer(List<Neuron> neurons){
        this.neurons = neurons;
    }

    @Override
    public void processAllNeurons() {
        neurons.stream().forEach(n-> n.process(null));
    }

    public List<Axon> getAxonsByIndex(int i){
        return neurons.stream().map(n->n.getAxonByIndex(i)).collect(Collectors.toList());
    }
}
