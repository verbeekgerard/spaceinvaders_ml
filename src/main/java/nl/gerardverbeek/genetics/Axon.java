package nl.gerardverbeek.genetics;

public class Axon {

    Neuron outputNeuron;

    public void process(Double value) {
            outputNeuron.process(value);
    }

    public void setOutputNeuron(Neuron outputNeuron) {
        this.outputNeuron = outputNeuron;
    }
}
