package nl.gerardverbeek.genetics.sight;

import javafx.scene.image.ImageView;
import nl.gerardverbeek.genetics.Axon;
import nl.gerardverbeek.genetics.Gene;
import nl.gerardverbeek.genetics.Neuron;
import nl.gerardverbeek.simulation.Game;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SightInputNeuron implements Neuron {

    private Game game;
    private List<Axon> axons;
    private double aliens = 0;
    private int startPosistion;
    private int endPosition;
    private Gene sightInputGene;

    public SightInputNeuron(Game game, SightRange sightRange, List<Axon> axons){
        this.startPosistion = sightRange.getStart();
        this.endPosition = sightRange.getEnd();
        this.game = game;
        this.axons = axons;
        sightInputGene = new SightInputGene(ThreadLocalRandom.current().nextInt(0, game.getAlienCount() + 1));
    }

    @Override
    public void process(Double value) {
        setAlienPositions();
        if(aliens > sightInputGene.getTreshold()){
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

    @Override
    public Gene getGene() {
        return sightInputGene;
    }

    @Override
    public void setGene(Gene gene) {
        this.sightInputGene.setTreshold(gene.getTreshold());
        this.sightInputGene.setEnrichmentValue(gene.getEnrichmentValue());
        this.sightInputGene.setSleepTime(gene.getSleepTime());
    }

    private void setAlienPositions(){
        aliens = 0;
        List<ImageView> entities = (game.getEntities());
        entities.stream().forEach(e -> {
                    if (e.getX() > startPosistion && e.getX() < endPosition) {
                        aliens++;
                    }
                }
        );
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

}
