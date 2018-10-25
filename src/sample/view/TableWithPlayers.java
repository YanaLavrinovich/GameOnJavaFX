package sample.view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import sample.controller.Controller;
import sample.model.DataBase;
import sample.model.Player;
import sample.model.pattern.Observer;

import java.util.*;

public class TableWithPlayers extends Observer {
    private TableView<Player> table;
    private BorderPane paneBox;
    private List<Player> topList;


    public TableWithPlayers(Controller controller) {
        topList = new ArrayList<>();
        this.subject = controller.getDataBase();
        this.subject.addObserver(this);

        paneBox = new BorderPane();
        table = new TableView<>();

        TableColumn<Player, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setMinWidth(190);
        TableColumn<Player, Integer> timeColumn = new TableColumn<>("Time");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        timeColumn.setMinWidth(80);
        table.getColumns().addAll(nameColumn, timeColumn);
        paneBox.setCenter(table);
        BorderPane.setMargin(table, new Insets(30));
        topList = controller.getDataBase().getData();
        setList(topList);
    }

    public BorderPane getPaneBox() {
        return paneBox;
    }

    @Override
    public void update() {
        DataBase dataBase = (DataBase) this.subject;
        topList = dataBase.getData();
        Comparator<Player> comparator =
                Comparator.comparing(Player::getTime);
        topList.sort(comparator);
        setList(topList);
    }

    public void setList(List<Player> topList) {
        table.setItems(FXCollections.observableArrayList(topList));
    }
}
