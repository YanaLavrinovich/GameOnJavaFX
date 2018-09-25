package sample.model;

public enum Entity {
    HERO(1),
    INDESTRUCTIBLE_BLOCK(2),
    DESTRUCTIBLE_BLOCK(3),
    GRASS(0),
    BOMB(4);

    private int code;

    Entity(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
