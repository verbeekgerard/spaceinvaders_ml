package nl.gerardverbeek.population;

import nl.gerardverbeek.evolution.EvolutionService;
import nl.gerardverbeek.simulation.Game;
import nl.gerardverbeek.util.Options;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Population {

    private PopulationService populationService = new PopulationService();
    private EvolutionService evolutionService = new EvolutionService();

    List<Player> players = new ArrayList<>();


    public void createPopulation(){

        for (int i = 0; i < Options.POPULATION_SIZE.getVal() ; i++) {
            Game game = populationService.getGame();
            Player player = populationService.getNewPlayer(game);
            players.add(player);
        }

    }

    public void startPopulation(){
        players.stream().forEach(p -> p.startPlayer());
    }

    public void startEvolution(){
        Runnable playerTask = () -> {
            while (true) {
                if(allPlayersDeath()){
                    //create new population
                    List<Player> newPlayers = evolutionService.createNewPopulation(players);
                    //Stop and remove old players
                    stopOldPlayers();
                    //start new ones
                    players = newPlayers;
                    startPopulation();
                }
            }
        };

        Thread thread = new Thread(playerTask);
        thread.start();

    }

    private void stopOldPlayers(){
        players.stream().forEach(Player::shutdown);
        players.clear();
    }


    public boolean allPlayersDeath(){
        List<Player> players = getPlayers();
        return !players.stream()
                .map(Player::isDeath)
                .collect(Collectors.toList())
                .contains(false);
    }

    public List<Player> getPlayers(){
        return players;
    }
}
