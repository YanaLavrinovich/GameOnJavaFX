package sample.model;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Bot {
    private int i;
    private int j;
    private Integer lastStep;
    private Thread thread;
    private boolean wantBomb;

    public static final Integer NOT_MOVED = 4;

    public Bot(int i, int j) {
        this.i = i;
        this.j = j;
        lastStep = NOT_MOVED;

        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(10),
                        ae -> {
                            setWantBomb(true);
                        }
                )
        );
        timeline.setCycleCount(-1);
        timeline.play();
    }

    public void setWantBomb(boolean wantBomb) {
        this.wantBomb = wantBomb;
    }

    public boolean isWantBomb() {
        return wantBomb;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public Thread getThread() {
        return thread;
    }

    public Integer getLastStep() {
        return lastStep;
    }

    public void setLastStep(Integer lastStep) {
        this.lastStep = lastStep;
    }
}
