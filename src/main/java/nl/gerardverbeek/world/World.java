package nl.gerardverbeek.world;

import nl.gerardverbeek.population.Population;
import nl.gerardverbeek.statistics.Statistics;
import nl.gerardverbeek.util.Options;

public class World {

    private static Population population = new Population();
    private static Statistics statistics = new Statistics(population);

    public static void main(String[] args){
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
}
