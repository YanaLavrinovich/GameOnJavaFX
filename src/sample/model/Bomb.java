package sample.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.Duration;


public class Bomb {
    //добавляем таймер при установке бомбы
    //спустя три сееунды, она взрывается на три клеточки в разные стороны
    //после чего исезает и умирает
    private BooleanProperty isAlive;
    private int i;
    private int j;


    public Bomb(int i, int j) {
        this.i = i;
        this.j = j;
        isAlive = new SimpleBooleanProperty(true);
        isAlive.addListener((observable, oldValue, newValue) -> System.out.println("changed " + oldValue + "->" + newValue));
        Timeline timeline = new Timeline(
          new KeyFrame(
                  Duration.seconds(2),
                  ae -> {
                  isAlive.set(false);}
          )
        );
        timeline.setCycleCount(1);
        timeline.play();
    }

    public boolean isAlive() {
        return isAlive.get();
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
