package sample.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import sample.UserException;
import sample.controller.Controller;

import java.util.ArrayList;
import java.util.List;

public class World {
    private Player player;
    private Field field;
    private List<Bomb> bombs;
    private List<Bot> bots;
    private BotMoving botMoving;
    private Controller controller;

    public World(String nameHero, Controller controller) throws UserException {
        this.controller = controller;
        player = new Player(nameHero);
        bombs = new ArrayList<>();
        bots = new ArrayList<>();
        field = new Field();
        botMoving = new BotMoving(this);
        field.addFirstLevel();
        createBot();
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(1),
                        ae -> {
                            checkStatusBomb();
                            checkBombBot();
                        }
                )
        );
        timeline.setCycleCount(-1);
        timeline.play();
    }

    private void checkBombBot() {
        for(Bot currentBot : bots) {
            if(currentBot.isWantBomb()) {
                putBombBot(currentBot);
            }
        }
    }

    private void putBombBot(Bot bot) {
        field.getField()[bot.getI()][bot.getJ()] = Entity.BOMB.getCode();
        bombs.add(new Bomb(bot.getI(), bot.getJ()));
        bot.setWantBomb(false);
    }

    private void createBot() {
        for (int i = 0; i < Field.HIGH; i++) {
            for (int j = 0; j < Field.WEIGHT; j++) {
                if (field.getField()[i][j] == Entity.BOT.getCode()) {
                    Bot bot = new Bot(i, j);
                    bots.add(bot);
                    startBot(bot);
                }
            }
        }
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
                if (field.getField()[i][j] != Entity.BOMB.getCode()) {
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
                if (field.getField()[i][j] != Entity.BOMB.getCode()) {
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
                if (field.getField()[i][j] != Entity.BOMB.getCode()) {
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
                if (field.getField()[i][j] != Entity.BOMB.getCode()) {
                    field.getField()[i][j] = 0;
                }
            }
        }
    }

    public void setPlayer() {
        field.getField()[player.getI()][player.getJ()] = Entity.HERO.getCode();
    }

    public void setBot(Bot bot) {
        field.getField()[bot.getI()][bot.getJ()] = Entity.BOT.getCode();
    }

    public void putBomb() {
        field.getField()[player.getI()][player.getJ()] = Entity.BOMB.getCode();
        bombs.add(new Bomb(player.getI(), player.getJ()));
    }

    private void checkStatusBomb() {
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (!bomb.isAlive()) {
                field.getField()[bomb.getI()][bomb.getJ()] = Entity.FIRE.getCode();
                if (field.getField()[bomb.getI() + 1][bomb.getJ()] != Entity.INDESTRUCTIBLE_BLOCK.getCode()) {
                    field.getField()[bomb.getI() + 1][bomb.getJ()] = Entity.FIRE.getCode();
                }
                if (field.getField()[bomb.getI() - 1][bomb.getJ()] != Entity.INDESTRUCTIBLE_BLOCK.getCode()) {
                    field.getField()[bomb.getI() - 1][bomb.getJ()] = Entity.FIRE.getCode();
                }
                if (field.getField()[bomb.getI()][bomb.getJ() + 1] != Entity.INDESTRUCTIBLE_BLOCK.getCode()) {
                    field.getField()[bomb.getI()][bomb.getJ() + 1] = Entity.FIRE.getCode();
                }
                if (field.getField()[bomb.getI()][bomb.getJ() - 1] != Entity.INDESTRUCTIBLE_BLOCK.getCode()) {
                    field.getField()[bomb.getI()][bomb.getJ() - 1] = Entity.FIRE.getCode();
                }

                Timeline timeline = new Timeline(
                        new KeyFrame(
                                Duration.seconds(1),
                                ae -> {
                                    removeBombFromField();
                                }
                        )
                );
                timeline.setCycleCount(1);
                timeline.play();
                bombs.remove(bomb);
            }
        }
    }

    public Field getField() {
        return field;
    }

    private void removeBombFromField() {
        int keyI = field.getKeyI();
        int keyJ = field.getKeyJ();
        for (int i = 0; i < Field.HIGH; i++) {
            for (int j = 0; j < Field.WEIGHT; j++) {
                if (field.getField()[i][j] == Entity.FIRE.getCode()) {
                        List<Bot> founded = new ArrayList<>();
                        for (Bot currentBot : bots) {
                            if (currentBot.getI() == i && currentBot.getJ() == j) {
                                stopBot(currentBot);
                                founded.add(currentBot);
                            }
                        }
                        if(!founded.isEmpty()) {
                            bots.removeAll(founded);
                        }
                        if(bots.isEmpty()) {
                            controller.gameOverWithWin();
                        }
                    if (i == keyI && j == keyJ) {
                        field.getField()[i][j] = Entity.KEY_OPEN.getCode();
                    } else if (i == player.getI() && j == player.getJ()) {
                        if (player.getLive() > 1) {
                            player.setLive(player.getLive() - 1);

                            field.getField()[i][j] = Entity.HERO.getCode();
                        } else if(player.getLive() == 1){
                            player.setLive(player.getLive() - 1);
                            controller.gameOver();
                        }
                    } else {
                        field.getField()[i][j] = Entity.GRASS.getCode();
                    }
                }
            }
        }
    }

    public void moveBotUp(Bot bot) {
        int i = bot.getI();
        int j = bot.getJ();
        int newI = i - 1;
        if (newI >= 0 && newI < 20) {
            if (field.getField()[newI][j] == Entity.GRASS.getCode()) {
                bot.setI(newI);
                setBot(bot);
                if (field.getField()[i][j] != Entity.BOMB.getCode()) {
                    field.getField()[i][j] = 0;
                }
            }
        }
    }

    public void moveBotDown(Bot bot) {
        int i = bot.getI();
        int j = bot.getJ();
        int newI = i + 1;
        if (newI >= 0 && newI < 20) {
            if (field.getField()[newI][j] == 0) {
                bot.setI(newI);
                setBot(bot);
                if (field.getField()[i][j] != Entity.BOMB.getCode()) {
                    field.getField()[i][j] = 0;
                }
            }
        }

    }

    public void moveBotRight(Bot bot) {
        int i = bot.getI();
        int j = bot.getJ();
        int newJ = j + 1;
        if (newJ >= 0 && newJ < 20) {
            if (field.getField()[i][newJ] == 0) {
                bot.setJ(newJ);
                setBot(bot);
                if (field.getField()[i][j] != Entity.BOMB.getCode()) {
                    field.getField()[i][j] = 0;
                }
            }
        }

    }

    public void moveBotLeft(Bot bot) {
        int i = bot.getI();
        int j = bot.getJ();
        int newJ = j - 1;
        if (newJ >= 0 && newJ < 20) {
            if (field.getField()[i][newJ] == Entity.GRASS.getCode()) {
                bot.setJ(newJ);
                setBot(bot);
                if (field.getField()[i][j] != Entity.BOMB.getCode()) {
                    field.getField()[i][j] = Entity.GRASS.getCode();
                }
            }
        }

    }

    private void startBot(Bot bot) {
        Runnable r = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    botMoving.chooseDirection(bot);
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    System.out.println("Big ass");
                    Thread.currentThread().interrupted();
                }
            }
        };
        Thread thread = new Thread(r);
        bot.setThread(thread);
        thread.start();
    }

    private void stopBot(Bot bot) {
        bot.getThread().stop();
    }

    public Player getPlayer() {
        return player;
    }
}