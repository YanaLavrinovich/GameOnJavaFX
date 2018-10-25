package sample.model;

import sample.UserException;
import sample.model.pattern.Subject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DataBase extends Subject {
    private List<Player> data;

    public DataBase() {
        data = new ArrayList<>();
        readFromFile();
    }

    private void readFromFile() {
        Path path = Paths.get("resource/database.txt");
        try {
            List<String> list = Files.readAllLines(path);
            for (String current : list) {
                String[] player = current.split("\\s+");
                addInBase(new Player(player[0], Integer.valueOf(player[1])));
            }
        } catch (IOException e) {
            new UserException(e);
        }
    }

    public void writeToFile() {
        Path path = Paths.get("resource/database.txt");
        List<String> dataToString = new ArrayList();
        for (Player current : data) {
            dataToString.add(current.getName() + " " + String.valueOf(current.getTime()));
        }
        try {
            Files.write(path, dataToString);
        } catch (IOException e) {
            new UserException(e.fillInStackTrace());
        }
    }



    public void addInBase(Player player) {
        boolean founded = false;
        for (Player current : data) {
            if (current.getName().equals(player.getName())) {
                current.setTime(player.getTime());
                founded = true;
            }
        }
        if (!founded) {
            data.add(player);
        }

        setChanged(true);
        notifyAllObservers();
    }

    public List<Player> getData() {
        return data;
    }


}
