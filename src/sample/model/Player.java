package sample.model;

public class Player {
    private String name;
    private long time;
    private int live;
    private int i;
    private int j;

    private final static int MAX_AMOUNT_LIVES = 3;

    public Player(String name) {
        this.i = 0;
        this.j = 0;
        this.name = name;
        this.time = 0;
        this.live = MAX_AMOUNT_LIVES;
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
}
