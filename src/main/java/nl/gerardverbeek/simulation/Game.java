package nl.gerardverbeek.simulation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import nl.gerardverbeek.ui.MainDashboardController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 4B 2014/2015 R.Casimiri, Gualdo Tadino (PG)
 */
public class Game implements Measures{
	
	public int record = 0;
	Bullet bulletc;
    MediaPlayer mp;
    Pane pane = new Pane();

    ImageView back;
    ImageView ship;
    Image enemiesV;

    Rectangle pointer = new Rectangle();
    String statusMP;

    CopyOnWriteArrayList<ImageView> enemies = new CopyOnWriteArrayList<>(); //new ImageView[Measures.ENEMY_COLUMN * Measures.ENEMY_ROW];

    Timeline tl;
    public int MOV = 0;

    boolean rightEnemy = true;
    boolean bulletIsAlive = false;
    boolean newLevel = true;

    int score = 0;
    int updateTime = 28;
    Text punt = new Text("Score: " + score);

    String sRecord = "";
    String n;
    long previousShotTime = System.currentTimeMillis();
    private Scene scene;



    public Game(){
        Image backV = new Image("/back.jpg");
        back = new ImageView(backV);

        Image shipV = new Image("/ship.png");
        ship = new ImageView(shipV);
        enemiesV = new Image("/invasore1.gif");
    }
    public List<ImageView> getEntities(){
        return enemies;
    }

    public void again() {
        for (int j = 0; j < Measures.ENEMY_ROW; j++) {
            for (int i = 0; i < Measures.ENEMY_COLUMN; i++) {

                ImageView enemy =new ImageView(enemiesV);

                enemy.setPreserveRatio(true);
                enemy.setX(i * 50);
                enemy.setY(j * 50);
                enemy.setFitWidth(Measures.ENEMY_EDGE);

                pane.getChildren().add(enemy);

                enemies.add(enemy);

                if (i == Measures.ENEMY_COLUMN - 1 && j == 0) {
                    pointer.setWidth(Measures.ENEMY_EDGE);
                    pointer.setHeight(Measures.ENEMY_EDGE);
                    pointer.setFill(Color.TRANSPARENT);
                    pointer.setX(enemies.get(i).getX() + Measures.ENEMY_EDGE);
                }
            }
        }
        updateTime-=3;
    }

//    @Override
    public void start() {

        ship.setPreserveRatio(true);
        ship.setFitWidth(80);
        ship.setX(100);
        ship.setY(680);

        pane.getChildren().add(back);
        pane.getChildren().add(ship);
        pane.getChildren().add(punt);

        punt.setFont(Font.font("Verdana", 20));
        punt.setFill(Color.YELLOW);

        again();

        back.setX(0);
        back.setY(0);

        punt.setX(10);
        punt.setY(20);

        Duration dI = new Duration(updateTime);
        KeyFrame f = new KeyFrame(dI, e -> movementCore());
        tl = new Timeline(f);
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
        scene = new Scene(pane, Measures.SCREEN_WIDTH, Measures.SCREEN_HEIGHT);
        scene.setOnKeyPressed(e -> keyboardManage(e));

    }

    public void keyboardManage(KeyEvent ke) {
        if (ke.getCode() == KeyCode.D) {
            pressRight();
        } else if (ke.getCode() == KeyCode.A) {
            pressLeft();
        } else if (ke.getCode() == KeyCode.SPACE) { // shoot
            shoot();
        }
    }

    public void shoot() {
        Platform.runLater(() -> {
            if ((previousShotTime + 500) < System.currentTimeMillis()) {
                bulletc = new Bullet(10, 50, ship.getX(), enemies, pane);
                previousShotTime = System.currentTimeMillis();
            }
        });
    }

    public void pressLeft() {
        Platform.runLater(() -> {
            double x = ship.getX();
            x -= 10;
            ship.setX(x);
        });
    }

    public void pressRight() {
        Platform.runLater(() -> {
            double x = ship.getX();
                    x += 10;
                    ship.setX(x);
                });
    }

    public void movementCore() {
        if (rightEnemy) { //check if the enemy is going toward right
            if (pointer.getX() + Measures.ENEMY_EDGE >= Measures.SCREEN_WIDTH) { //check collision on right edge
                rightEnemy = false;
                for (int i = 0; i < enemies.size(); i++) {
                    if (enemies.get(i) != null) {
                        enemies.get(i).setY(enemies.get(i).getY() + 50);
                    }
                }
            }
            for (int i = 0; i < enemies.size(); i++) {
                if (enemies.get(i) != null) {
                    enemies.get(i).setX(enemies.get(i).getX() + Measures.SPEED); //move the enemy
                }
            }
            pointer.setX(pointer.getX() + Measures.SPEED); //move the pointer
        } else {
            if (pointer.getX() - ((Measures.ENEMY_EDGE * (Measures.ENEMY_COLUMN + 2))) <= 0) {
                rightEnemy = true;
                for (int i = 0; i < enemies.size(); i++) {
                    if (enemies.get(i) != null) {
                        enemies.get(i).setY(enemies.get(i).getY() + 50);
                    }
                }
            }
            for (int i = 0; i < enemies.size(); i++) {
                if (enemies.get(i) != null) {
                    enemies.get(i).setX(enemies.get(i).getX() - Measures.SPEED);
                }
            }
            pointer.setX(pointer.getX() - Measures.SPEED);
        }
        MOV++; //animation block 

        if (bulletc != null) {
            score += bulletc.getScore();
            punt.setText("Score: " + score);
        }
    }

    private boolean isGameOver(){
        List<ImageView> localEnemies = new ArrayList(enemies);
        try {
            return localEnemies.stream()
                    .filter(e -> e.getY() >= ship.getY())
                    .findAny()
                    .isPresent();
        } catch(NullPointerException e){
            return false;
        }
    }
    
    public void stop(){
    	 try {
             tl.stop();
             MainDashboardController.closeBestPlayerStage();


         } catch (Exception e) {
             record = 0;
         }
    }

    public int getAlienCount(){
        return enemies.size();
    }

    public static int getMaxAliens(){
        return Measures.ENEMY_COLUMN * Measures.ENEMY_COLUMN;
    }
    public boolean isDeath(){
        return isGameOver();
    }

    public int getScore(){
        return score;
    }

    public void setFrameVisible(){

    }

    public void setFrameInVisible(){

    }

    public void startGameLoop(){
        Platform.runLater(() -> {
            start();
        });
    }

    public Pane getPane(){
        return this.pane;
    }

    public Scene getScene(){
        return this.scene;
    }

    public Rectangle getPointer() {
        return pointer;
    }

}