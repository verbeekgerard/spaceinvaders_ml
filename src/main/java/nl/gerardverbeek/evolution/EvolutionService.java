package nl.gerardverbeek.evolution;

import nl.gerardverbeek.genetics.Neuron;
import nl.gerardverbeek.population.Player;
import nl.gerardverbeek.population.PopulationService;
import nl.gerardverbeek.simulation.Game;
import nl.gerardverbeek.util.Options;
import nl.gerardverbeek.util.PlayerNames;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class EvolutionService {

    private PopulationService populationService = new PopulationService();


    public List<Player> createNewPopulation(List<Player> oldPlayers){

        int maxFitness = getHighestFitness(oldPlayers);
        List<Player> newPlayers = new ArrayList<>();

        while(newPlayers.size() < oldPlayers.size()){
            newPlayers.add(getNewPlayer(oldPlayers, maxFitness));
        }

        return newPlayers;
    }

    private Player getNewPlayer(List<Player> players, int maxFitness){
        Player newPlayer;

        List<Player> parents = getParentsForReproduction(players,maxFitness);
        newPlayer = mate(parents);

        newPlayer.setName(PlayerNames.getRandomName());
        newPlayer.setShutdown(false);
        newPlayer.mutate();
//        setNewGame(newPlayer);
        return newPlayer;
    }

    private void setNewGame(Player player){
        Game newGame = new Game();
        player.setGame(newGame);
        player.getInputLayer().getNeurons().stream().forEach(n -> n.setGame(newGame));
        player.getOutputLayer().getNeurons().stream().forEach(n -> n.setGame(newGame));

    }


    /**
     * Pick genes from both parents and combine them to one new player
     * @param parents
     * @return
     */
    private Player mate(List<Player> parents){
        Player parent1 = parents.get(0);
        Player parent2 = parents.get(1);

        Player child = populationService.getNewPlayer(new Game());

        //Set all genes from parent1
        setChildInputLayerWithAllGenesFromParent(child, parent1);
        setChildHiddenLayerWithAllGenesFromParent(child, parent1);
        setChildOutputLayerWithAllGenesFromParent(child, parent1);

        //replace some random genes in the child from parent2
        setChildInputLayerWithGenesFromParent(child, parent2);
        setChildHiddenLayerWithGenesFromParent(child, parent2);
        setChildOutputLayerWithGenesFromParent(child, parent2);

        child.mutate();
        return child;
    }


    private void setChildOutputLayerWithGenesFromParent(Player child, Player parent){
        int maxNeurons = child.getOutputLayer().getNeurons().size();
        int neuronsToChangeAmount = maxNeurons * (Options.GENE_REPLACEMENT_PERCENTAGE.getIntVal()/100);

        for (int i = 0; i < neuronsToChangeAmount ; i++) {
            int randomNeuronIndex = ThreadLocalRandom.current().nextInt(0, maxNeurons + 1);
            Neuron childNeuron = child.getOutputLayer().getNeurons().get(randomNeuronIndex);
            childNeuron.setGene(parent.getOutputLayer().getNeurons().get(randomNeuronIndex).getGene());
        }
    }

    private void setChildOutputLayerWithAllGenesFromParent(Player child, Player parent){
        int maxNeurons = child.getOutputLayer().getNeurons().size();
        for (int i = 0; i < maxNeurons ; i++) {
            Neuron childNeuron = child.getOutputLayer().getNeurons().get(i);
            childNeuron.setGene(parent.getOutputLayer().getNeurons().get(i).getGene());
        }
    }

    private void setChildHiddenLayerWithGenesFromParent(Player child, Player parent){
        int maxNeurons = child.getHiddenLayer().getNeurons().size();
        int neuronsToChangeAmount = maxNeurons * (Options.GENE_REPLACEMENT_PERCENTAGE.getIntVal()/100);
        for (int i = 0; i < neuronsToChangeAmount ; i++) {
            int randomNeuronIndex = ThreadLocalRandom.current().nextInt(0, maxNeurons + 1);
            Neuron childNeuron = child.getHiddenLayer().getNeurons().get(randomNeuronIndex);
            childNeuron.setGene(parent.getHiddenLayer().getNeurons().get(randomNeuronIndex).getGene());
        }
    }

    private void setChildHiddenLayerWithAllGenesFromParent(Player child, Player parent){
        int maxNeurons = child.getHiddenLayer().getNeurons().size();
        for (int i = 0; i < maxNeurons ; i++) {
            Neuron childNeuron = child.getHiddenLayer().getNeurons().get(i);
            childNeuron.setGene(parent.getHiddenLayer().getNeurons().get(i).getGene());
        }
    }

    private void setChildInputLayerWithGenesFromParent(Player child, Player parent){
        int maxNeurons = child.getInputLayer().getNeurons().size();
        int neuronsToChangeAmount = maxNeurons * (Options.GENE_REPLACEMENT_PERCENTAGE.getIntVal()/100);
        for (int i = 0; i < neuronsToChangeAmount ; i++) {
            int randomNeuronIndex = ThreadLocalRandom.current().nextInt(0, maxNeurons + 1);
            Neuron childNeuron = child.getInputLayer().getNeurons().get(randomNeuronIndex);
            childNeuron.setGene(parent.getInputLayer().getNeurons().get(randomNeuronIndex).getGene());
        }
    }

    private void setChildInputLayerWithAllGenesFromParent(Player child, Player parent){
        int maxNeurons = child.getInputLayer().getNeurons().size();
        for (int i = 0; i < maxNeurons ; i++) {
            Neuron childNeuron = child.getInputLayer().getNeurons().get(i);
            childNeuron.setGene(parent.getInputLayer().getNeurons().get(i).getGene());
        }
    }

    private List<Player> getParentsForReproduction(List<Player> players, int maxFitness){
        List<Player> playersForReproduction = new ArrayList<>();
        playersForReproduction.add(getParentFromPopulation(players, maxFitness));
        playersForReproduction.add(getParentFromPopulation(players, maxFitness));

        return playersForReproduction;
    }


    private Player getParentFromPopulation(List<Player> players, int maxFitness){
        Player player = null;
        int counter = 0;

        while(player==null && counter < 1000) {
            int randomFitness = ThreadLocalRandom.current().nextInt(0, maxFitness + 1);
            int randomPlayerIndex = ThreadLocalRandom.current().nextInt(0, players.size());
            Player randomPlayer = players.get(randomPlayerIndex);
            if (randomPlayer.getFitness() > randomFitness) {
                player = randomPlayer;
            }
            counter++;
        }
        if(player == null){
            player = players.get(ThreadLocalRandom.current().nextInt(0, players.size()));
        }

        return player;
    }


    /**
     *
     * @param players
     * @return
     */
    private int getHighestFitness(List<Player> players){
        return players.stream()
                .mapToInt(p -> p.getFitness())
                .max()
                .getAsInt();
    }



}
