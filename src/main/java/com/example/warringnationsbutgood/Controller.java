package com.example.warringnationsbutgood;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class Controller {
    //Reusable components
    private Parent newRoot;
    private Stage newStage;
    private Scene newScene;

    //Values from dropdown
    @FXML
    private ComboBox<String> players;
    @FXML
    private ComboBox<String> stage;
    @FXML
    private ComboBox<String> hitpoints;

    @FXML
    protected void hostButtonClick(ActionEvent event) throws IOException {
        newRoot = FXMLLoader.load(Objects.requireNonNull(Controller.class.getResource("host.fxml")));
        newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        newScene = new Scene(newRoot, GUI.getWidth(), GUI.getHeight());
        newStage.setScene(newScene);
        newStage.setFullScreen(true);
        newStage.show();
    }

    @FXML
    protected void rules(ActionEvent event) throws IOException {
        newRoot = FXMLLoader.load(Objects.requireNonNull(Controller.class.getResource("rules.fxml")));
        newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        newScene = new Scene(newRoot, GUI.getWidth(), GUI.getHeight());
        newStage.setScene(newScene);
        newStage.setFullScreen(true);
        newStage.show();
    }

    @FXML
    protected void lobbyClick(ActionEvent event) throws IOException {
        newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        newStage.setScene(GUI.getScene());
        newStage.setFullScreen(true);
        newStage.show();
    }

    @FXML
    protected void go(ActionEvent event) throws IOException {
        int playersCount = Integer.parseInt(players.getValue());
        String level = stage.getValue();
        int startingHitpoints = Integer.parseInt(hitpoints.getValue());
    }

    @FXML
    protected void reset(ActionEvent event) throws IOException {
        newRoot = FXMLLoader.load(Objects.requireNonNull(Controller.class.getResource("rules.fxml")));
        newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        newScene = new Scene(newRoot, GUI.getWidth(), GUI.getHeight());
        newStage.setScene(newScene);
        newStage.setFullScreen(true);
        newStage.show();
    }

}
