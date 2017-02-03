package nl.gerardverbeek.statistics;

import nl.gerardverbeek.population.Player;
import nl.gerardverbeek.population.Population;

import java.util.List;

public class Statistics {

    private static Population population;

    public Statistics(Population population){
        this.population = population;
    }

    public static void logStatistics(){
        List<Player> players = population.getPlayers();
        players.stream().forEach(p->printStatsForPlayer(p));
    }

    private static void printStatsForPlayer(Player player){
        System.out.println("Player: " + player.getName());
        System.out.println("Fitness: " + player.getFitness());
    }
}
