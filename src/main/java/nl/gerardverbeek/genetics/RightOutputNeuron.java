package nl.gerardverbeek.genetics;

import nl.gerardverbeek.simulation.Game;

public class RightOutputNeuron implements Neuron {

    Game game;

    public RightOutputNeuron(Game game){
        this.game = game;
    }

    @Override
    public void process(Double value) {
        game.setRightPressed(true);
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game.setRightPressed(false);

    }

    @Override
    public Axon getAxonByIndex(int i) {
        return null;
    }
}
