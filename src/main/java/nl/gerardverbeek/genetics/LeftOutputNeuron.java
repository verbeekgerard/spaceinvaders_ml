package nl.gerardverbeek.genetics;

import nl.gerardverbeek.simulation.Game;

public class LeftOutputNeuron implements Neuron {

    Game game;

    public LeftOutputNeuron(Game game){
        this.game = game;
    }

    @Override
    public void process(Double value) {
        game.setLeftPressed(true);
        try {
            Thread.sleep(400);
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
