package sample.model;

public enum Entity {
    GRASS(0),
    HERO(1),
    INDESTRUCTIBLE_BLOCK(2),
    DESTRUCTIBLE_BLOCK(3),
    BOMB(4),
    KEY(5),
    FIRE(6);

    private int code;

    Entity(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
