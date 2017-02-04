package nl.gerardverbeek.genetics;

import nl.gerardverbeek.simulation.Game;

public class FireOutputNeuron implements Neuron {

    Game game;
    OutputGene outputGene;

    public FireOutputNeuron(Game game, OutputGene outputGene){
        this.outputGene = outputGene;
        this.game = game;
    }

    @Override
    public void process(Double value){
        game.setFirePressed(true);
        try {
            Thread.sleep(outputGene.getSleepTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game.setFirePressed(false);
    }

    @Override
    public Axon getAxonByIndex(int i) {
        return null;
    }
}
