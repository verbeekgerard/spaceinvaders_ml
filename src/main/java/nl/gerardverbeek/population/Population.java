package nl.gerardverbeek.population;

import nl.gerardverbeek.simulation.Game;

import java.util.ArrayList;
import java.util.List;

public class Population {

    PopulationService populationService = new PopulationService();
    List<Player> players = new ArrayList<>();

    public void createPopulation(){

        for (int i = 0; i < 2 ; i++) {
            Game game = populationService.getGame();
            Player player = populationService.getNewPlayer(game);
            players.add(player);
        }

    }

    public void startPopulation(){
        players.stream().forEach(p->p.startPlayer());
    }

//    public void startPlayer(Player player) {
//        System.out.println("startPlayer..");
//
//        Runnable task = () -> {
//            while (!player.isDeath()) {
//                player.performAction();
//            }
//        };
//
//        Thread thread = new Thread(task);
//        thread.start();
//
//        player.getGame().startGameLoop();
//    }

    private void removeBadPlayers(){

    }

    public List<Player> getPlayers(){
        return players;
    }
}
