package nl.gerardverbeek.simulation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.List;

public class Bullet {

    boolean bulletIsAlive = true;
    Rectangle r = null;
    Pane p;
    int incr;
//    Rectangle rect = Game.getPointer();

    public Bullet(double x, double y, double pos, List<ImageView> v, Pane p) {
        if (r == null) {
            this.p = p;
            Timeline tlB;
            r = new Rectangle(x, y);
            p.getChildren().add(r);
            r.setX(pos + 35);
            r.setY(640);
            Duration dB = new Duration(5);
            KeyFrame fB = new KeyFrame(dB, e -> {
                if (r != null) {
                    r.setY(r.getY() - 5);
                    collisioncheck(v);
                }
            });
            tlB = new Timeline(fB);
            tlB.setCycleCount(Animation.INDEFINITE);
            tlB.play();
        }
    }

    private boolean collisioncheck(List<ImageView> enemies) {
        for (int i = 0; i < enemies.size(); i++) {
            if (r != null && enemies.get(i) != null) {
                if ((r != null && r.getX() < enemies.get(i).getX() + enemies.get(i).getFitWidth()
                        && r.getX() + r.getWidth() > enemies.get(i).getX()
                        && r.getY() < enemies.get(i).getY() + enemies.get(i).getFitHeight()
                        && r.getHeight() + r.getY() > enemies.get(i).getY())) {
                    enemies.get(i).setVisible(false);
                    enemies.remove(i);
                    removeBullet();
                    bulletIsAlive = false;
                    incr += 1;
                }
            }
        }
        if (r != null) {
                if (r.getY()<0-r.getHeight()-1) {
                    removeBullet();
            }
        }
        return bulletIsAlive;
    }

    public int getScore() {
        int x = incr;
        incr = 0;
        return x;
    }
    
//    public void getRectangle(Rectangle rect){
//        this.rect = rect;
//    }
//
    public void removeBullet() {
        p.getChildren().remove(r);
        r = null;
    }
}