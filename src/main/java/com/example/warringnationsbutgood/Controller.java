package com.example.warringnationsbutgood;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Controller {
    //Reusable components
    private Parent newRoot;
    private Stage newStage;
    private Scene newScene;

    //Results
    private int playersCount;
    private int startingHitpoints;
    private String level;

    //Names and Emails
    private String[] names;
    private String[] emails;

    private ArrayList<TextField> inputs;

    @FXML
    private VBox gameTitle;

    //Values from dropdown
    @FXML
    private ComboBox<String> players;
    @FXML
    private ComboBox<String> stage;
    @FXML
    private ComboBox<String> hitpoints;

    @FXML
    private GridPane settingsMenu;

    @FXML
    private Button lobby;

    @FXML
    private Button back;

    @FXML
    private Button go;

    @FXML
    private GridPane playerInfo;

    @FXML
    private GridPane gameMenu;

    @FXML
    protected void hostButtonClick(ActionEvent event) throws IOException {
        newRoot = FXMLLoader.load(Objects.requireNonNull(Controller.class.getResource("host.fxml")));
        newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        newScene = new Scene(newRoot, GUI.getWidth(), GUI.getHeight());
        newStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        newStage.setScene(newScene);
        newStage.setFullScreen(true);
        newStage.show();
    }

    @FXML
    protected void rules(ActionEvent event) throws IOException {
        newRoot = FXMLLoader.load(Objects.requireNonNull(Controller.class.getResource("rules.fxml")));
        newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        newScene = new Scene(newRoot, GUI.getWidth(), GUI.getHeight());
        newStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        newStage.setScene(newScene);
        newStage.setFullScreen(true);
        newStage.show();
    }

    @FXML
    protected void lobbyClick(ActionEvent event) throws IOException {
        newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        newStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        newStage.setScene(GUI.getScene());
        newStage.setFullScreen(true);
        newStage.show();
    }

    @FXML
    protected void go(ActionEvent event) throws IOException {
        if (playerInfo.isVisible()) {
            playerInfo.setVisible(false);
            back.setVisible(false);
            go.setVisible(false);
            gameTitle.setVisible(false);
            collectInfo();
        } else {
            playersCount = Integer.parseInt(players.getValue());
            startingHitpoints = Integer.parseInt(hitpoints.getValue());
            level = stage.getValue();

            settingsMenu.setVisible(false);
            lobby.setVisible(false);
            back.setVisible(true);

            playerInfo.setVisible(true);
            getPlayers();
        }
    }

    @FXML
    protected void back(ActionEvent event) throws IOException {
        settingsMenu.setVisible(true);
        lobby.setVisible(true);
        back.setVisible(false);
        playerInfo.setVisible(false);
    }

    private void getPlayers() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(700, 250);
        scrollPane.setId("-fx-background-color: transparent;");

        GridPane menu = new GridPane();
        menu.setPrefSize(600, 50 + 60 * playersCount);
        menu.setVgap(10);
        menu.setHgap(20);

        Text name = new Text("Name");
        Text email = new Text("Email");

        name.getStyleClass().add("info");
        email.getStyleClass().add("info");

        menu.add(name, 1, 1);
        menu.add(email, 2, 1);

        inputs = new ArrayList<TextField>();

        for (int i = 0; i < playersCount; i++) {
            TextField enterName = new TextField();
            TextField enterEmail = new TextField();

            enterName.setPrefWidth(300);
            enterEmail.setPrefWidth(300);

            enterName.getStyleClass().add("textField");
            enterEmail.getStyleClass().add("textField");

            enterEmail.setText("sn3@kent.k12.wa.us");

            inputs.add(enterName);
            inputs.add(enterEmail);
            menu.add(enterName, 1, 2 + i);
            menu.add(enterEmail, 2, 2 + i);
        }

        scrollPane.setContent(menu);
        playerInfo.getChildren().add(scrollPane);
    }

    private void collectInfo() {
        GridPane menu = new GridPane();
        menu.setPrefSize(750, 50 + 60 * 8);
        menu.setVgap(10);
        menu.setHgap(50);
        menu.setId("gamePane");
        menu.setAlignment(Pos.CENTER);

        Text[] titles = {new Text("Name"), new Text("Hitpoints"), new Text("Used Mana"), new Text("Status")};

        for (int k = 0; k < titles.length; k++) {
            titles[k].getStyleClass().add("titleGame");
            menu.add(titles[k], k, 0);
        }

        names = new String[playersCount];
        emails = new String[playersCount];

        for (int i = 0; i < inputs.size(); i++) {
            String data = inputs.get(i).getText();

            if (i % 2 == 0) {
                //Name
                names[i / 2] = data;
            } else {
                //Email
                emails[i / 2] = data;
            }
        }

        for (int j = 0; j < 8; j++) {
            Button button = new Button();
            Text health = new Text(Integer.toString(startingHitpoints));
            Text mana = new Text("0");
            Text status = new Text("SAFE");

            try {
                Player p1 = new Player(j + 1, startingHitpoints, level, "SAFE", names[j], emails[j]);
                button.setText(j + 1 + " - " + names[j]);
                button.getStyleClass().add("nameButtons");
            } catch (Exception e) {
                button.setText(j + 1 + " - ");
                button.getStyleClass().add("nameButtons");
                button.setDisable(true);
            }

            health.getStyleClass().add("stats");
            mana.getStyleClass().add("stats");
            status.getStyleClass().add("stats");

            menu.add(button, 0, j + 1);
            menu.add(health, 1, j + 1);
            menu.add(mana, 2, j + 1);
            menu.add(status, 3, j + 1);
        }

        gameMenu.setVisible(true);
        gameMenu.getChildren().add(menu);
    }

}
