package sample.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BotMoving {
    private Field field;
    private World world;

    private final static Integer LEFT = 0;
    private final static Integer RIGHT = 1;
    private final static Integer UP = 2;
    private final static Integer DOWN = 3;
    private final static Integer HAVE_NOT_DIRECTION = 5;

    public BotMoving(World world) {
        this.world = world;
        this.field = world.getField();
    }

    public void chooseDirection(Bot bot) {
        List<Integer> directionList = new ArrayList<>();
        directionList = fullList(directionList, bot);
        int direction = getRandomDirection(directionList);

        if(direction != HAVE_NOT_DIRECTION) {
            if (direction == LEFT) {
                world.moveBotLeft(bot);
            } else if (direction == RIGHT) {
                world.moveBotRight(bot);
            } else if (direction == UP) {
                world.moveBotUp(bot);
            } else if (direction == DOWN) {
                world.moveBotDown(bot);
            }
        }
    }

    private int getRandomDirection(List<Integer> directionList) {
        Collections.shuffle(directionList);
        int result;
        if(!directionList.isEmpty()) {
            result = directionList.get(0);
        } else {
            result = HAVE_NOT_DIRECTION;
        }
        return result;
    }

    private List<Integer> fullList(List<Integer> directionList, Bot bot) {
        int[][] table = field.getField();
        int i = bot.getI();
        int j = bot.getJ();

        if (table[i - 1][j] == Entity.GRASS.getCode()) {
            directionList.add(UP);
        }
        if (table[i + 1][j] == Entity.GRASS.getCode()) {
            directionList.add(DOWN);
        }
        if (table[i][j - 1] == Entity.GRASS.getCode()) {
            directionList.add(LEFT);
        }
        if (table[i][j + 1] == Entity.GRASS.getCode()) {
            directionList.add(RIGHT);
        }
        if(!bot.getLastStep().equals(Bot.NOT_MOVED)) {
            directionList.remove(bot.getLastStep());
        }
        return directionList;
    }
}
