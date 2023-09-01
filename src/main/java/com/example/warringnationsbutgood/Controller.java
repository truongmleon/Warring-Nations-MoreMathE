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
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Controller {
    //Reusable components
    private Parent newRoot;
    private Stage newStage;
    private Scene newScene;

    //Results
    private int playersCount, startingHitpoints;
    private int round = 0;
    private String level;

    //Names and Emails
    private String[] names, emails;
    private final String[] colors = {"#00FFFF", "#FFFF00", "#ADFF2F", "#FF0000", "#FF00FF"};
    private final ArrayList<String> stageNames = new ArrayList<>(Arrays.asList("Arithmetic", "Geometry", "Algebruh", "Calculus", "Abstract"));
    private ArrayList<TextField> inputs;

    private ArrayList<Player> playersInfo;

    GridPane menu = new GridPane();
    GridPane actions = new GridPane();
    @FXML
    private VBox gameTitle;

    //Values from dropdown
    @FXML
    private ComboBox<String> players, stage, hitpoints;

    @FXML
    private Button lobby, back, go;

    @FXML
    private GridPane playerInfo, gameMenu, actionMenu, settingsMenu, stagesMenu;

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
            setUp();
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

    private String createEmailBody(int id) {
        Player currentPlayer = playersInfo.get(id);
        currentPlayer.generate();
        return "===========================%0A"
                + "WARRING%20NATIONS%0A"
                + "===========================%0A%0A"
                + "Stats%20this%20round:%0A"
                + "Attack:%20" + currentPlayer.getAttack() + "%0A"
                + "Defense:%20" + currentPlayer.getDefense() + "%0A"
                + "Mana:%20" + currentPlayer.getMana() + "%0A%0A"
                + "===========================%0A%0A"
                + "You%20have%20" + currentPlayer.getHealth() + "%20HP%20left%0A"
                + "You%20have%20used%20" + currentPlayer.getTotalMana() + "%20mana%0A"
                + "You%20are%20in%20the%20" + currentPlayer.getStage() + "%20stage%0A%0A"
                + "===========================%0A%0A"
                + "Reply%20with%20attack%20[team],%20defend,%20or%20mana%20(manaing%20twice%20is%20not%20allowed).";
    }

    private void generate(ActionEvent event) {
        round++;
        //Making Email
        for (int i = 0; i < emails.length; i++) {
            Desktop desktop = Desktop.getDesktop();
            String subject = "?subject=WarringNationsRound_" + round;

            String message = "mailto:" + emails[i] + subject + "&body=" + createEmailBody(i);
            URI uri = URI.create(message);
            try {
                desktop.mail(uri);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void getPlayers() {
        //Scrollable menu to get players email and name
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

        inputs = new ArrayList<>();
        playersInfo = new ArrayList<>();

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

    private void setUp() {
        //Things that will not be changed after setting them up once
        menu.setPrefSize(750, 50 + 60 * 8);
        menu.setVgap(10);
        menu.setHgap(50);
        menu.setId("gamePane");
        menu.setAlignment(Pos.CENTER);

        actions.setPrefSize(240, 100);
        actions.setVgap(10);
        actions.setHgap(50);
        actions.setAlignment(Pos.CENTER);

        final Text[] titles = {new Text("Name"), new Text("Hitpoints"), new Text("Stage"), new Text("Status")};

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
    }

    private void collectInfo() {
        final Button[] buttonTitles = {new Button("Generate"), new Button("Calculate"), new Button("Mines")};
        gameMenu.getChildren().clear();

        for (int j = 0; j < 8; j++) {
            Button button = new Button();
            Text health = new Text(Integer.toString(startingHitpoints));
            Text currentLevel = new Text(level);
            Text status = new Text("SAFE");

            try {
                Player p1 = new Player(startingHitpoints, level, "SAFE", names[j]);
                playersInfo.add(p1);
                button.setText(j + 1 + " - " + names[j]);
            } catch (Exception e) {
                button.setText(j + 1 + " - ");
            }

            button.setDisable(true);

            button.getStyleClass().add("nameButtons");
            health.getStyleClass().add("stats");
            currentLevel.getStyleClass().add("stats");
            status.getStyleClass().add("stats");

            int index = stageNames.indexOf(level);
            currentLevel.setStyle("-fx-fill: " + colors[index] + ";");
            status.setStyle("-fx-fill: #00FFFF;");

            menu.add(button, 0, j + 1);
            menu.add(health, 1, j + 1);
            menu.add(currentLevel, 2, j + 1);
            menu.add(status, 3, j + 1);
        }

        for (int l = 0; l < 3; l++) {
            buttonTitles[l].getStyleClass().add("actions");

            if (l == 0) {
                buttonTitles[l].setOnAction(this::generate);
            }
            if (l != 0) {
                buttonTitles[l].setDisable(true);
            }

            actions.add(buttonTitles[l], 0, l);
        }

        gameMenu.setVisible(true);
        gameMenu.getChildren().add(menu);

        actionMenu.setVisible(true);
        actionMenu.getChildren().add(actions);

        stagesMenu.setVisible(true);
    }

}
