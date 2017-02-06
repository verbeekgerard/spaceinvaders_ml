package nl.gerardverbeek.evolution;

import nl.gerardverbeek.genetics.Neuron;
import nl.gerardverbeek.population.Player;
import nl.gerardverbeek.simulation.Game;
import nl.gerardverbeek.util.Options;
import nl.gerardverbeek.util.PlayerNames;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class EvolutionService {

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
        setNewGame(newPlayer);
        return newPlayer;
    }

    private void setNewGame(Player player){
        Game newGame = new Game();
        player.setGame(newGame);
        player.getInputLayer().getNeurons().stream().forEach(n -> n.setGame(newGame));
        player.getOutputLayer().getNeurons().stream().forEach(n-> n.setGame(newGame));

    }


    /**
     * Pick genes from both parents and combine them to one new player
     * @param parents
     * @return
     */
    private Player mate(List<Player> parents){
        Player parent1 = parents.get(0);
        Player parent2 = parents.get(1);

        Player child = null;
        try {
            child = (Player) parent1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        setChildInputLayerWithGenesFormParent(child, parent2);
        return child;
    }


    private void setChildInputLayerWithGenesFormParent(Player child, Player parent){
        int maxNeurons = child.getInputLayer().getNeurons().size();
        int neuronsToChangeAmount = maxNeurons * (Options.GENE_REPLACEMENT_PERCENTAGE.getIntVal()/100);

        for (int i = 0; i < neuronsToChangeAmount ; i++) {
            //pick random neuron
            int randomNeuronIndex = ThreadLocalRandom.current().nextInt(0, maxNeurons + 1);
            Neuron childNeuron = child.getInputLayer().getNeurons().get(randomNeuronIndex);
            childNeuron.setGene(parent.getInputLayer().getNeurons().get(randomNeuronIndex).getGene());
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

        while(player==null) {
            int randomFitness = ThreadLocalRandom.current().nextInt(0, maxFitness + 1);
            int randomPlayerIndex = ThreadLocalRandom.current().nextInt(0, players.size());
            Player randomPlayer = players.get(randomPlayerIndex);
            if (randomPlayer.getFitness() > randomFitness) {
                player = randomPlayer;
            }
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
