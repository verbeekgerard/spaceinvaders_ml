package nl.gerardverbeek.ui;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import nl.gerardverbeek.population.Player;
import nl.gerardverbeek.statistics.Statistics;
import nl.gerardverbeek.util.Option;
import nl.gerardverbeek.util.Options;
import nl.gerardverbeek.world.World;

import java.net.URL;
import java.util.ResourceBundle;

public class MainDashboardController implements Initializable{
    private World world = new World();

    private Player bestPlayer;

    @FXML
    private Label averageFitnessValueLabel;

    @FXML
    private Slider gameSpeedSlider;

    @FXML
    private Label gameSpeedLabelValue;

    @FXML
    private void startApp(ActionEvent e){
        world.start();
        System.out.println("world started!");
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

    public void showBestPlayer(){
        bestPlayer = world.getBestPlayer();
        bestPlayer.showFrame();
    }

    public void hideBestPlayer(){
        if(bestPlayer!=null)
            bestPlayer.hideFrame();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        gameSpeedLabelValue.setText(""+Options.GAME_SPEED.getLongVal());

        gameSpeedSlider.setMin(1);
        gameSpeedSlider.setMax(100);
        gameSpeedSlider.setValue(Options.GAME_SPEED.getLongVal());
        gameSpeedSlider.setShowTickLabels(true);
        gameSpeedSlider.setShowTickMarks(true);
        gameSpeedSlider.setMajorTickUnit(50);
        gameSpeedSlider.setMinorTickCount(5);
        gameSpeedSlider.setBlockIncrement(10);

        gameSpeedSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                gameSpeedLabelValue.setText(String.format("%.2f", new_val));
                Options.GAME_SPEED = new Option(new_val.longValue());
            }
        });

    }



}
