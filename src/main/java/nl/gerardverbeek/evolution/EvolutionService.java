package nl.gerardverbeek.evolution;

import nl.gerardverbeek.population.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class EvolutionService {

    public void evolvePopultion(List<Player> players){

    }

    private List<Player> createNewPopulation(List<Player> oldPlayers){

        double maxFitness = getHighestFitness(oldPlayers);
        List<Player> newPlayers = new ArrayList<>();

        int i = 0;
        while(newPlayers.size() < oldPlayers.size()){

            //get player with random number between 0 and max fitness
            //pick player where fitness is between 0 and the random number
            //the higher the fitness is from the player the more likely the chance it is picked for reproduction

        }

        return newPlayers;
    }

    private Player getNewPlayer(List<Player> players, int maxFitness){
        Player newPlayer;

        List<Player> parents = getParentsForReproduction(players,maxFitness);
        newPlayer = createPlayer(parents);

        return newPlayer;
    }

    private Player createPlayer(List<Player> parents){
        //pick genes from both parents and combine them to one new player




        return null;
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
            int randomPlayerIndex = ThreadLocalRandom.current().nextInt(0, players.size() + 1);
            Player randomPlayer = players.get(randomPlayerIndex);
            if (player.getFitness() > randomFitness) {
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
