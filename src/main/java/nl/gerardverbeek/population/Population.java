package nl.gerardverbeek.population;

import nl.gerardverbeek.evolution.EvolutionService;
import nl.gerardverbeek.simulation.Game;
import nl.gerardverbeek.util.Options;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

    public void restartPopulation(){
//        players.stream().forEach(p -> p.hideFrame());
        players.stream().forEach(p -> p.restartPlayer());
//        players.stream().forEach(p -> p.showFrame());


    }

    public void startPopulation(){
        players.stream().forEach(p -> p.startPlayer());
    }

    public void startEvolution(){
        Runnable task = () -> {
            while (true) {
                if(allPlayersDeath()){
                    Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
                    System.out.println("threads: " + threadSet.size());

//                    TODO. When createNewPopulation is called memory leak 


//
                    //create new population
                    List<Player> newPlayers = evolutionService.createNewPopulation(players);
                    //Stop and remove old players
                    stopOldPlayers();
                    //start new ones
                    players = newPlayers;
                    startPopulation();
                    System.out.println("new population created");
                }
            }
        };

        Thread thread = new Thread(task);
        thread.start();

    }

    private void stopOldPlayers(){
        players.stream().forEach(Player::shutdown);
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
