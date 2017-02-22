package nl.gerardverbeek.ui;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import nl.gerardverbeek.population.Player;
import nl.gerardverbeek.statistics.Statistics;
import nl.gerardverbeek.util.Option;
import nl.gerardverbeek.util.Options;
import nl.gerardverbeek.world.World;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainDashboardController implements Initializable{
    private static World world = new World();

    private Player bestPlayer;

    @FXML
    private Label averageFitnessValueLabel;

    @FXML
    private Slider gameSpeedSlider;

    @FXML
    private Label gameSpeedLabelValue;

    private static Stage bestPlayerStage;


    @FXML
    private void startApp(ActionEvent e){
        world.start();
        System.out.println("world started!");
    }

    public static World getWorld(){
        return world;
    }

    @FXML
    private void showStatistics(ActionEvent e){
        Statistics statistics = world.getStatistics();

        Runnable task = () -> {
            while (true) {
                try {
                    Thread.sleep(Options.STATISTICS_REFRESH_RATE.getLongVal());
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                System.out.println("AllPlayersDeath: " + statistics.allPlayersDeath());

                Platform.runLater(
                        () -> {
                            averageFitnessValueLabel.setText("" + statistics.getAverageFitness());
                        }
                );
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    public void showAllPlayers(){
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("/ui/playerStats.fxml"));
            Stage stage = new Stage();
            stage.setTitle("All players");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBestPlayer(){
        Platform.runLater(() -> {
            bestPlayerStage = new Stage();
            bestPlayerStage.setTitle("BestPlayer");
            Scene scene = World.getBestPlayer().getGame().getScene();
            bestPlayerStage.setScene(scene);
            bestPlayerStage.show();
        });

    }

    public void hideBestPlayer(){
        Platform.runLater(() -> {
            if (bestPlayerStage != null) {
                bestPlayerStage.close();
            } else {
                System.out.println("Nothing to hide!");
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        gameSpeedLabelValue.setText(""+Options.GAME_SPEED.getLongVal());

        gameSpeedSlider.setMin(1);
        gameSpeedSlider.setMax(15);
        gameSpeedSlider.setValue(Options.GAME_SPEED.getLongVal());
        gameSpeedSlider.setShowTickMarks(true);
        gameSpeedSlider.setMajorTickUnit(1);
        gameSpeedSlider.setMinorTickCount(1);
        gameSpeedSlider.setBlockIncrement(2);

        gameSpeedSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                gameSpeedLabelValue.setText(String.format("%.2f", new_val));
                Options.GAME_SPEED = new Option(new_val.longValue());
            }
        });

    }


    public static void closeBestPlayerStage(){
        Platform.runLater(() -> {
            if (bestPlayerStage != null) {
                bestPlayerStage.close();
            }
        });
    }

    public static Stage getBestPlayerStage(){
        return bestPlayerStage;
    }


}
