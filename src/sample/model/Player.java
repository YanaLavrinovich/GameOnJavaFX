package sample.model;


public class Player{
    private String name;
    private int time;
    private Integer live;
    private int i;
    private int j;

    private final static int MAX_AMOUNT_LIVES = 3;

    public Player(String name) {
        this.i = 1;
        this.j = 1;
        this.name = name;
        this.time = 0;
        this.live = MAX_AMOUNT_LIVES;
    }
    public Player(String name, int time) {
        this.i = 1;
        this.j = 1;
        this.name = name;
        this.time = time;
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

    public int getLive() {
        return live;
    }

    public void setLive(int live) {
        this.live = live;
    }

    public String getName() {
        return name;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }
}
