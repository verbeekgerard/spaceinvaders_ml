package nl.gerardverbeek.population;

import nl.gerardverbeek.genetics.*;
import nl.gerardverbeek.genetics.sight.SightInputNeuron;
import nl.gerardverbeek.genetics.sight.SightRange;
import nl.gerardverbeek.layer.HiddenLayer;
import nl.gerardverbeek.layer.InputLayer;
import nl.gerardverbeek.layer.OutputLayer;
import nl.gerardverbeek.simulation.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PopulationService {

    private List<SightRange> sightRanges = new ArrayList();
    private int hiddenLayerNodeAmount = 3;
    private int outputLayerAmount = 3;

    public PopulationService(){
        setSightRanges();
    }

    private void setSightRanges(){
        sightRanges.add(new SightRange(0,324));
        sightRanges.add(new SightRange(325, 651));

    }

    public Game getGame(){
        Game game = new Game();
        return game;
    }


    public Player getNewPlayer(Game game){
        Player player = new Player();

        player.setGame(game);

        InputLayer inputLayer = createInputLayer(game);
        HiddenLayer hiddenLayer = createHiddenLayer(inputLayer);
        OutputLayer outputLayer = createOutputLayer(hiddenLayer, game);

        player.setInputLayer(inputLayer);
        player.setHiddenLayer(hiddenLayer);
        player.setOutputLayer(outputLayer);

        return player;
    }

    private List<Axon> createAxons(int amount){
        List<Axon> axons = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            axons.add(createRandomAxonGene());
        }
        return axons;
    }

    private Axon createRandomAxonGene(){
        Axon axon = new Axon();
        return axon;
    }

    private OutputLayer createOutputLayer(HiddenLayer hiddenLayer, Game game){

        LeftOutputNeuron leftOutputNeuron = new LeftOutputNeuron(game);
        RightOutputNeuron rightOutputNeuron = new RightOutputNeuron(game);
        FireOutputNeuron fireOutputNeuron = new FireOutputNeuron(game);

        List<Neuron> outputNeurons = Arrays.asList(leftOutputNeuron, rightOutputNeuron, fireOutputNeuron);

        for (int i = 0; i < outputNeurons.size() ; i++) {
            Neuron outputNeuron = outputNeurons.get(i);
            hiddenLayer
                    .getAxonsByIndex(i)
                    .stream()
                    .forEach(a-> a.setOutputNeuron(outputNeuron));
        }

        return new OutputLayer(outputNeurons);

    }

    private HiddenLayer createHiddenLayer(InputLayer inputlayer){
        List<Neuron> hiddenNeurons = new ArrayList<>();
        for (int i = 0; i < hiddenLayerNodeAmount ; i++) {
            HiddenNeuron hiddenNeuron = new HiddenNeuron();
            inputlayer.getAxonsByIndex(i).stream().forEach(a->a.setOutputNeuron(hiddenNeuron));
            hiddenNeuron.setAxons(createAxons(outputLayerAmount));
            hiddenNeurons.add(hiddenNeuron);
        }
        return new HiddenLayer(hiddenNeurons);
    }

    private InputLayer createInputLayer(Game game){
        List<Neuron> inputNeurons = new ArrayList<>();
        for (int i = 0; i < sightRanges.size(); i++) {
            List<Axon> inputToHiddenAxons = createAxons(hiddenLayerNodeAmount);
            inputNeurons.add(new SightInputNeuron(game, sightRanges.get(i), inputToHiddenAxons));
        }
        return new InputLayer(inputNeurons);
    }
}
