package nl.gerardverbeek.population;

import nl.gerardverbeek.layer.HiddenLayer;
import nl.gerardverbeek.layer.InputLayer;
import nl.gerardverbeek.layer.OutputLayer;
import nl.gerardverbeek.simulation.Game;
import nl.gerardverbeek.util.PlayerNames;

public class Player {
    private InputLayer inputLayer;
    private HiddenLayer hiddenLayer;
    private OutputLayer outputLayer;
    private Game game;
    private String name = PlayerNames.getRandomName();

    public void performAction(){
        inputLayer.processAllNeurons();
    }

    public void startPlayer() {
        System.out.println("startPlayer..");

        Runnable playerTask = () -> {
            while (game.isGameRunning()) {
//                System.out.println("Score: " + game.getScore());
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
    }

    public Player mate(Player player){
        Player child = null;
        return child;
    }

    public void mutate(){
    }

    public boolean isDeath(){
        return game.isDeath();
    }

    public String getName() {return name;}
    public int getFitness(){
        return game.getScore();
    }
    public void showFrame(){
        game.setFrameVisible();
    }
    public Game getGame() {return game;}
    public void setGame(Game game) {this.game = game;}
    public void setInputLayer(InputLayer inputLayer) {this.inputLayer = inputLayer;}
    public void setHiddenLayer(HiddenLayer hiddenLayer) {this.hiddenLayer = hiddenLayer;}
    public void setOutputLayer(OutputLayer outputLayer) {
        this.outputLayer = outputLayer;
    }
}
