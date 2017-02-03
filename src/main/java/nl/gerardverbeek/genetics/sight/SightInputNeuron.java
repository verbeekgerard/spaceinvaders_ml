package nl.gerardverbeek.genetics.sight;

import nl.gerardverbeek.genetics.Axon;
import nl.gerardverbeek.genetics.Neuron;
import nl.gerardverbeek.simulation.AlienEntity;
import nl.gerardverbeek.simulation.Game;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SightInputNeuron implements Neuron {

    private Game game;
    private List<Axon> axons;
    private double aliens = 0;
    private int startPosistion;
    private int endPosition;
    private int treshold = 0;

    public SightInputNeuron(Game game, SightRange sightRange, List<Axon> axons){
        this.startPosistion = sightRange.getStart();
        this.endPosition = sightRange.getEnd();
        this.game = game;
        this.axons = axons;
        this.treshold = ThreadLocalRandom.current().nextInt(0, game.getAlienCount() + 1);
    }

    @Override
    public void process(Double value) {
        setAlienPositions();
        if(aliens > treshold){
            axons.stream().forEach(a -> a.process(aliens));
        }
    }

    @Override
    public Axon getAxonByIndex(int i) {
        if(i>axons.size()){
            return null;
        }
        return axons.get(i);
    }

    private void setAlienPositions(){
        aliens = 0;
        game.getEntities().forEach(e -> {
                    if (e instanceof AlienEntity) {
                        if (e.getX() > startPosistion || e.getX() < endPosition) {
                            aliens++;
                        }
                    }
                }
        );
    }

}