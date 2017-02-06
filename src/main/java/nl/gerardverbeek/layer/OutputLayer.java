package nl.gerardverbeek.layer;

import nl.gerardverbeek.genetics.Neuron;

import java.util.List;

public class OutputLayer implements Layer {

    List<Neuron> outputNeurons;

    public OutputLayer(List<Neuron> outputNeurons){
        this.outputNeurons = outputNeurons;
    }

    @Override
    public void processAllNeurons() {

    }

    public List<Neuron> getNeurons() {
        return outputNeurons;
    }

}
