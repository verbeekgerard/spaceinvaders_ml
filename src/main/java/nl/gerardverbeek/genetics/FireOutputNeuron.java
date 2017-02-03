package nl.gerardverbeek.genetics;

import nl.gerardverbeek.simulation.Game;

public class FireOutputNeuron implements Neuron {

    Game game;

    public FireOutputNeuron(Game game){
        this.game = game;
    }

    @Override
    public void process(Double value) {
        game.setFirePressed(true);
        try {
            Thread.sleep(100);
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
