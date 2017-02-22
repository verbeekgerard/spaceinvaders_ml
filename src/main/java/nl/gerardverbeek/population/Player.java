package nl.gerardverbeek.population;

import nl.gerardverbeek.genetics.Neuron;
import nl.gerardverbeek.layer.HiddenLayer;
import nl.gerardverbeek.layer.InputLayer;
import nl.gerardverbeek.layer.OutputLayer;
import nl.gerardverbeek.simulation.Game;
import nl.gerardverbeek.util.Options;
import nl.gerardverbeek.util.PlayerNames;

public class Player implements Cloneable  {

    private InputLayer inputLayer;
    private HiddenLayer hiddenLayer;
    private OutputLayer outputLayer;
    private Game game;
    private String name = PlayerNames.getRandomName();

    private boolean shutdown;

    public void performAction(){
        inputLayer.processAllNeurons();
    }

    private Thread gameThread;
    private Thread playerThread;

    /**
     * Start player and refresh the game
     */
    public void restartPlayer(){
        game = new Game();

        this.shutdown = false;
        System.out.println(this.name +" restarting");
        Runnable playerTask = () -> {
            while (!shutdown) {
                performAction();
            }
        };

        playerThread = new Thread(playerTask);
        playerThread.start();
        startPlayerGame();
    }

    public void startPlayer() {
        System.out.println(this.name +" starting");

        Runnable playerTask = () -> {
            while (!shutdown) {
                performAction();
            }
        };

        playerThread = new Thread(playerTask);
        playerThread.start();

        startPlayerGame();
    }

    public void startPlayerGame() {
        Runnable gameTask = () -> {
            game.startGameLoop();
        };

        gameThread = new Thread(gameTask);
        gameThread.start();

    }

    public void mutate(){
        double mutationRate = Options.MUTATION_RATE.getVal();
        hiddenLayer.getNeurons()
                .stream()
                .map(Neuron::getGene)
                .forEach(g-> g.mutate(mutationRate));

        inputLayer.getNeurons()
                .stream()
                .map(Neuron::getGene)
                .forEach(g -> g.mutate(mutationRate));

        outputLayer.getNeurons()
                .stream()
                .map(Neuron::getGene)
                .forEach(g -> g.mutate(mutationRate));
    }



    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void start(){
        System.out.println(this.name +" starting..");
        shutdown = false;
        game.start();
    }
    public void setShutdown(boolean shutdown) {this.shutdown = shutdown;}
    public void shutdown() {
        System.out.println(this.name +" shutting down");
        shutdown = true;
        game.stop();
        game = null;
    }
    public boolean isDeath(){return game.isDeath();}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getFitness(){
        if(game != null) {
            return game.getScore();
        }
        return 0;
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
