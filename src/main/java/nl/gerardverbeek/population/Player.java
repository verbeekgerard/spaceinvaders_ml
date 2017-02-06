package nl.gerardverbeek.population;

import nl.gerardverbeek.layer.HiddenLayer;
import nl.gerardverbeek.layer.InputLayer;
import nl.gerardverbeek.layer.OutputLayer;
import nl.gerardverbeek.simulation.Game;
import nl.gerardverbeek.util.PlayerNames;

public class Player implements Cloneable  {

    private InputLayer inputLayer;
    private HiddenLayer hiddenLayer;
    private OutputLayer outputLayer;
    private Game game;
    private String name = PlayerNames.getRandomName();

    private volatile boolean shutdown;


    public void performAction(){
        inputLayer.processAllNeurons();
    }

    public void startPlayer() {
        Runnable playerTask = () -> {
            while (game.isGameRunning() && !shutdown) {
                performAction();
            }
        };

        Thread thread = new Thread(playerTask);
        thread.start();

        startPlayerGame();
    }

    private void startPlayerGame() {
        Runnable gameTask = () -> {
            game.startGameLoop();
        };

        Thread thread2 = new Thread(gameTask);
        thread2.start();
        thread2.interrupt();
    }

    public void mutate(){
    }



    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setShutdown(boolean shutdown) {this.shutdown = shutdown;}
    public void shutdown() {shutdown = true;}
    public boolean isDeath(){return game.isDeath();}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getFitness(){
        return game.getScore();
    }
    public void showFrame(){
        game.setFrameVisible();
    }
    public void hideFrame(){game.setFrameInVisible();}
    public Game getGame() {return game;}
    public void setGame(Game game) {this.game = game;}
    public void setInputLayer(InputLayer inputLayer) {this.inputLayer = inputLayer;}
    public void setHiddenLayer(HiddenLayer hiddenLayer) {this.hiddenLayer = hiddenLayer;}
    public void setOutputLayer(OutputLayer outputLayer) {this.outputLayer = outputLayer;}
    public InputLayer getInputLayer() {return inputLayer;}
    public HiddenLayer getHiddenLayer() {return hiddenLayer;}
    public OutputLayer getOutputLayer() {return outputLayer;}
}
