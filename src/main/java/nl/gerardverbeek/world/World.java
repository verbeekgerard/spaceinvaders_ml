package nl.gerardverbeek.world;

import nl.gerardverbeek.population.Player;
import nl.gerardverbeek.population.Population;
import nl.gerardverbeek.statistics.Statistics;
import nl.gerardverbeek.ui.UIApplication;
import nl.gerardverbeek.util.Options;

import java.util.Comparator;

public class World {

    private static Population population = new Population();


    private static Statistics statistics = new Statistics(population);

    private static UIApplication uiApplication = new UIApplication();

//    public static void main(String[] args){
//        population.createPopulation();
//        population.startPopulation();
//
//        startStatistics();
//    }


    public void start(){
        population.createPopulation();
        population.startPopulation();

        startStatistics();
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

    public Player getBestPlayer(){
        return population.getPlayers().stream().max(Comparator.comparing(Player::getFitness)).get();
    }


    public static Statistics getStatistics() {
        return statistics;
    }
}
