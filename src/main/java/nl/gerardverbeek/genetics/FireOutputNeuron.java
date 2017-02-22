package nl.gerardverbeek.genetics;

import nl.gerardverbeek.simulation.Game;

public class FireOutputNeuron implements Neuron {

    Game game;
    Gene outputGene;

    public FireOutputNeuron(Game game, OutputGene outputGene){
        this.outputGene = outputGene;
        this.game = game;
    }

    @Override
    public void process(Double value){
        game.shoot();
        try {
            Thread.sleep(outputGene.getSleepTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Axon getAxonByIndex(int i) {
        return null;
    }

    @Override
    public Gene getGene() {
        return outputGene;
    }

    @Override
    public void setGene(Gene gene) {
        this.outputGene.setTreshold(gene.getTreshold());
        this.outputGene.setEnrichmentValue(gene.getEnrichmentValue());
        this.outputGene.setSleepTime(gene.getSleepTime());
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }
}
