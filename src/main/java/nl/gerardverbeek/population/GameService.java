package nl.gerardverbeek.population;

public class GameService {

    public void startGame(Player player){
        player.startPlayer();
        player.getGame().startGameLoop();
    }

}
