package nl.gerardverbeek.world;

import nl.gerardverbeek.population.Player;
import nl.gerardverbeek.population.Population;
import nl.gerardverbeek.statistics.Statistics;
import nl.gerardverbeek.util.Options;

import java.util.Comparator;

public class World {

    private static Population population = new Population();

    private static Statistics statistics = new Statistics(population);


    public static void main(String [] args){
        start();
    }


    public static void start(){
        population.createPopulation();
        population.startPopulation();
        population.startEvolution();

//        startStatistics();
    }

    private static void startStatistics(){
        Runnable task = () -> {
            while (true) {
                try {
                    Thread.sleep(Options.STATISTICS_REFRESH_RATE.getLongVal());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                statistics.logStatistics();
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

//    public Player getBestPlayer(){
//        return population.getPlayers().stream().max(Comparator.comparing(Player::getFitness)).get();
//    }

    public static Player getBestPlayer(){
        return population.getPlayers().stream().max(Comparator.comparing(Player::getFitness)).get();
    }

    public Population getPopulation(){
        return population;
    }


    public static Statistics getStatistics() {
        return statistics;
    }
}
