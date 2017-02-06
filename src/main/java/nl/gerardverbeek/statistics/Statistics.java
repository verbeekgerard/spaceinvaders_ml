package nl.gerardverbeek.statistics;

import nl.gerardverbeek.population.Player;
import nl.gerardverbeek.population.Population;

import java.util.List;

public class Statistics {

    private static Population population;

    public Statistics(Population population){
        this.population = population;
    }

    public void logStatistics(){
        List<Player> players = population.getPlayers();
        players.stream().forEach(p->printStatsForPlayer(p));
    }

    private void printStatsForPlayer(Player player){
        System.out.println("Player: " + player.getName());
        System.out.println("Fitness: " + player.getFitness());
    }

    public double getAverageFitness(){
        List<Player> players = population.getPlayers();
        return players.stream()
                .map(p-> p.getFitness())
                .mapToInt(i->i)
                .average()
                .orElse(0);
    }


    public boolean allPlayersDeath(){
        return population.allPlayersDeath();
    }
}
