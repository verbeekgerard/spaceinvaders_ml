package nl.gerardverbeek.ui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nl.gerardverbeek.population.Player;
import nl.gerardverbeek.world.World;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PlayerStatsController implements Initializable{
    private World world;

    private List<PlayerStats> playerStats = new ArrayList();

    private List<Label> labels = new ArrayList<>();

    @FXML
    private Pane masterPane;

    @FXML
    private Pane playerNamePane;

    @FXML
    private Pane playerStatsPane;

    @FXML
    private Pane showGamePane;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        world = MainDashboardController.getWorld();
        createPlayerStats();
        updatePlayers();
    }

    private void updatePlayers(){
        Runnable task = () -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                Platform.runLater(() -> {
                        List<Player> players = world.getPopulation().getPlayers();
                        for (int i = 0; i <players.size(); i++) {
                            labels.get(i).setText(""+players.get(i).getFitness());
                        }
                });
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    private Button createPlayerButton(Player player){
        Button button = new Button("Show game");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.showFrame();
            }
        });

        return button;
    }

    private void createPlayerStats(){
        List<Player> players = world.getPopulation().getPlayers();
//        VBox vbox = new VBox(10);
        VBox vbox2 = new VBox(10);
//        VBox vbox3 = new VBox(10);
        for (Player player : players){
//            Label nameLabel = new Label(player.getName());
            Label fitnessLabel = new Label(""+player.getFitness());
//            Button button = createPlayerButton(player);
            labels.add(fitnessLabel);
//            playerStats.add(new PlayerStats(null, fitnessLabel, player, null));

//            vbox.getChildren().add(nameLabel);
            vbox2.getChildren().add(fitnessLabel);
//            vbox3.getChildren().add(button);
        }

//        playerNamePane.getChildren().add(vbox);
        playerStatsPane.getChildren().add(vbox2);
//        showGamePane.getChildren().add(vbox3);
    }



}


class PlayerStats{
    Label nameLabel;
    Label fitnessLabel;
    Button button;
    Player player;

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public PlayerStats(Label nameLabel, Label fitnessLabel, Player player, Button button) {
        this.nameLabel = nameLabel;
        this.fitnessLabel = fitnessLabel;
        this.player = player;
        this.button = button;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public void setName(String name) {
        this.nameLabel.setText(name);
    }

    public Label getFitnessLabel() {
        return fitnessLabel;
    }

    public void setFitness(String fitness) {
        this.fitnessLabel.setText(fitness);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
