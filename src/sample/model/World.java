package sample.model;

public class World {
    private Player player;
    private Field field;

    public World(String nameHero) {
        player = new Player(nameHero);
        field = new Field();
        field.addFirstLevel();
        field.repaint();
    }

    private void findHero(Player player) {
        int i = 0;
        do {
            int j = 0;
            do {
                if (field.getField()[i][j] == Entity.HERO.getCode()) {
                    player.setI(i);
                    player.setJ(j);
                    break;
                }
                j++;
            } while (j < Field.WEIGHT);
            i++;
        } while (i < Field.HIGH);
    }

    public void moveHeroUp() {
        findHero(player);
        int i = player.getI();
        int j = player.getJ();
        int newI = i - 1;
        if (newI >= 0 && newI < 20) {
            if (field.getField()[newI][j] == 0) {
                player.setI(newI);
                if(field.getField()[i][j] != Entity.BOMB.getCode()) {
                    field.getField()[i][j] = 0;
                }
            }
        }
    }

    public void moveHeroDown() {
        findHero(player);
        int i = player.getI();
        int j = player.getJ();
        int newI = i + 1;
        if (newI >= 0 && newI < 20) {
            if (field.getField()[newI][j] == 0) {
                player.setI(newI);
                if(field.getField()[i][j] != Entity.BOMB.getCode()) {
                    field.getField()[i][j] = 0;
                }
            }
        }
    }

    public void moveHeroRight() {
        findHero(player);
        int i = player.getI();
        int j = player.getJ();
        int newJ = j + 1;
        if (newJ >= 0 && newJ < 20) {
            if (field.getField()[i][newJ] == 0) {
                player.setJ(newJ);
                if(field.getField()[i][j] != Entity.BOMB.getCode()) {
                    field.getField()[i][j] = 0;
                }
            }
        }
    }

    public void moveHeroLeft() {
        findHero(player);
        int i = player.getI();
        int j = player.getJ();
        int newJ = j - 1;
        if (newJ >= 0 && newJ < 20) {
            if (field.getField()[i][newJ] == 0) {
                player.setJ(newJ);
                if(field.getField()[i][j] != Entity.BOMB.getCode()) {
                    field.getField()[i][j] = 0;
                }
            }
        }
    }

    public void setPlayer() {
        field.getField()[player.getI()][player.getJ()] = Entity.HERO.getCode();
    }

    public void putBomb() {
        field.getField()[player.getI()][player.getJ()] = Entity.BOMB.getCode();
    }

    public Field getField() {
        return field;
    }
}