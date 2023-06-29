package com.example.warringnationsbutgood;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class Controller {

    private Parent rulesRoot;
    private Stage rulesStage;
    private Scene rulesScene;

    @FXML
    protected void hostButtonClick(ActionEvent event) throws IOException {
        rulesRoot = FXMLLoader.load(Objects.requireNonNull(Controller.class.getResource("host.fxml")));
        rulesStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        rulesScene = new Scene(rulesRoot, GUI.getWidth(), GUI.getHeight());
        rulesStage.setScene(rulesScene);
        rulesStage.show();
    }

    @FXML
    protected void rules(ActionEvent event) throws IOException {
        rulesRoot = FXMLLoader.load(Objects.requireNonNull(Controller.class.getResource("rules.fxml")));
        rulesStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        rulesScene = new Scene(rulesRoot, GUI.getWidth(), GUI.getHeight());
        rulesStage.setScene(rulesScene);
        rulesStage.show();
    }

    @FXML
    protected void lobbyClick(ActionEvent event) throws IOException {
        rulesStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        rulesStage.setScene(GUI.getScene());
        rulesStage.show();
    }

}
