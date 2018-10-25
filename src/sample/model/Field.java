package sample.model;


import sample.UserException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Field {
    private int[][] field;
    private int keyI = 3;
    private int keyJ = 3;

    public final static int HIGH = 21;
    public final static int WEIGHT = 21;


    public Field() {
        field = new int[HIGH][WEIGHT];
    }

    public int[][] getField() {
        return field;
    }

    public void addFirstLevel() throws UserException {
        Path firstLevel = Paths.get("resource/firstLevel.txt");
        List<String> level = new ArrayList<>();
        try {
            level = Files.lines(firstLevel).collect(Collectors.toList());
        } catch (IOException e) {
            throw new UserException("The file wasn't found!", e.fillInStackTrace());
        }
        field = moveToArray(level);
    }

    private int[][] moveToArray(List<String> level) {
        int[][] result = new int[HIGH][WEIGHT];
        int i = 0;
        for (String current : level) {
            String[] string = current.split("\\s+");
            for (int j = 0; j < string.length; j++) {
                result[i][j] = Integer.parseInt(string[j]);
            }
            i++;
        }
        return result;
    }

    public int getKeyI() {
        return keyI;
    }

    public int getKeyJ() {
        return keyJ;
    }
}
