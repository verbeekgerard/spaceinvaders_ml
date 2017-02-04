package nl.gerardverbeek.genetics;

import nl.gerardverbeek.simulation.Game;

public class LeftOutputNeuron implements Neuron {

    Game game;
    OutputGene outputGene;

    public LeftOutputNeuron(Game game, OutputGene outputGene){
        this.outputGene = outputGene;
        this.game = game;
    }

    @Override
    public void process(Double value) {
        game.setLeftPressed(true);
        try {
            Thread.sleep(outputGene.getSleepTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game.setLeftPressed(false);
    }

    @Override
    public Axon getAxonByIndex(int i) {
        return null;
    }
}
