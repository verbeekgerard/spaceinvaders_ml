package nl.gerardverbeek.genetics;

import nl.gerardverbeek.simulation.Game;

public class RightOutputNeuron implements Neuron {

    Game game;
    Gene outputGene;

    public RightOutputNeuron(Game game, OutputGene outputGene){
        this.outputGene = outputGene;
        this.game = game;
    }

    @Override
    public void process(Double value) {
        game.setRightPressed(true);
        try {
            Thread.sleep(outputGene.getSleepTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game.setRightPressed(false);

    }

    @Override
    public Axon getAxonByIndex(int i) {
        return null;
    }

    @Override
    public Gene getGene() {
        return outputGene;
    }

    @Override
    public void setGene(Gene gene) {
        this.outputGene = gene;
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }
}
