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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Locale;
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
    private final String[] stages = {"Arithmetic", "Geometry", "Algebruh", "Calculus", "Abstract"};

    private Button[] playerButtons, adminButtons;
    private ArrayList<TextField> inputs;
    private ArrayList<Player> playersInfo;
    private GridPane menu = new GridPane();
    private GridPane actions = new GridPane();
    @FXML
    private VBox gameTitle;

    //Values from dropdown
    @FXML
    private ComboBox<String> players, stage, hitpoints;

    @FXML
    private Button lobby, back, go;

    @FXML
    private GridPane playerInfo, gameMenu, actionMenu, settingsMenu, stagesMenu, player;

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

    private String getProblem(String current) {
        // https://mathmaker.vercel.app/
        String problem = "https://mathmaker.vercel.app/";

        for (int i = 0; i < stages.length; i++) {
            if (Objects.equals(current, stages[i])) {
                return problem + stages[i].toLowerCase() + ".html";
            }
        }

        return problem;
    }
    private String createEmailBody(int id) {
        Player currentPlayer = playersInfo.get(id);
        String problem = getProblem(currentPlayer.getStage());
        currentPlayer.generate();

        return "===========================%0A"
                + "WARRING%20NATIONS%0A"
                + "===========================%0A%0A"
                + "Stats:%0A"
                + "Attack:%20" + currentPlayer.getAttack() + "%0A"
                + "Defense:%20" + currentPlayer.getDefense() + "%0A"
                + "Mana:%20" + currentPlayer.getMana() + "%0A%0A"
                + "===========================%0A%0A"
                + "You%20have%20" + currentPlayer.getHealth() + "%20HP%20left%0A"
                + "You%20have%20used%20" + currentPlayer.getTotalMana() + "%20mana%0A"
                + "You%20are%20in%20the%20" + currentPlayer.getStage() + "%20stage%0A%0A"
                + "===========================%0A%0A"
                + "Problem:%20" + problem + "%0A"
                + "For%20Wolfman%20problems%20(Algebruh%20and%20Calculus),%20you%20get%203%20tries.%0A"
                + "For%20Geometry%20problems,%20just%20do%201%20Khan%20Academy%20one.%0A"
                + "Reply%20with%20Question[right/wrong],attack%20[team],%20defend,%20or%20mana%20(manaing%20twice%20is%20not%20allowed).%0A"
                + "";
    }

    private void generate(ActionEvent event) {
        round++;
        //Making Email
        for (int i = 0; i < emails.length; i++) {
            playerButtons[i].setDisable(false);
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

        adminButtons[0].setDisable(true);
        adminButtons[1].setDisable(false);
        adminButtons[2].setDisable(false);
    }

    private void calculate(ActionEvent event) {
        //Really just a refresh function. Calculations are done throughout
    }

    private void mines(ActionEvent event) {

    }

    public void exit() {
        player.setVisible(false);
    }

    private void playerActions(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        Player user;

        for (int i = 0; i < playerButtons.length; i++) {
            if (playerButtons[i].equals((sourceButton))) {
                user = playersInfo.get(i);
                break;
            }
        }



        player.setVisible(true);
    }

    private void createPlayerActions() {
        GridPane menu = new GridPane();
        menu.setPrefSize(200, 150);
        menu.setVgap(10);
        menu.setHgap(20);

        player.getChildren().add(menu);
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
        menu.setPrefSize(780, 50 + 60 * 8);
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
        final Image[] actionIcons = {
                new Image("https://raw.githubusercontent.com/truongmleon/Warring-Nations-MoreMathEdition/master/src/main/resources/com/example/warringnationsbutgood/assets/icons/buttons/Dove.png", 40, 40, true, false),
                new Image("https://raw.githubusercontent.com/truongmleon/Warring-Nations-MoreMathEdition/master/src/main/resources/com/example/warringnationsbutgood/assets/icons/buttons/Blahaj.png", 40, 40, true, false),
                new Image("https://raw.githubusercontent.com/truongmleon/Warring-Nations-MoreMathEdition/master/src/main/resources/com/example/warringnationsbutgood/assets/icons/buttons/Gem.png", 40, 40, true, false)
        };

        final ImageView[] actionView = {new ImageView(actionIcons[0]), new ImageView(actionIcons[1]), new ImageView(actionIcons[2])};
        playerButtons = new Button[8];
        adminButtons = new Button[3];

        for (int j = 0; j < 8; j++) {
            Button button = new Button();
            Player p1;
            Text health;
            Text currentLevel;
            Text status;

            try {
                p1 = new Player(startingHitpoints, level, names[j]);
                playersInfo.add(p1);
                button.setText(j + 1 + " - " + names[j]);
                health = new Text(Integer.toString(p1.getHealth()));
                currentLevel = new Text(p1.getStage());
                status = new Text(p1.getStatus());
                currentLevel.setStyle("-fx-fill: " + p1.getCurrentStageColor() + ";");
                status.setStyle("-fx-fill:" + p1.getCurrentStatusColor() + ";");
            } catch (Exception e) {
                button.setText(j + 1 + " - Mr. Harp");
                health = new Text("GOD");
                currentLevel = new Text("Calculus");
                status = new Text("ALIVE");
            }

            button.setDisable(true);
            button.getStyleClass().add("nameButtons");
            health.getStyleClass().add("stats");
            button.setOnAction(this::playerActions);
            currentLevel.getStyleClass().add("stats");
            status.getStyleClass().add("stats");

            playerButtons[j] = button;
            menu.add(button, 0, j + 1);
            menu.add(health, 1, j + 1);
            menu.add(currentLevel, 2, j + 1);
            menu.add(status, 3, j + 1);
        }

        for (int l = 0; l < 3; l++) {
            buttonTitles[l].getStyleClass().add("actions");

            if (l == 0) {
                buttonTitles[l].setOnAction(this::generate);
            } else if (l == 1) {
                buttonTitles[l].setOnAction(this::calculate);
            } else {
                buttonTitles[l].setOnAction(this::mines);
            }

            if (l != 0) {
                buttonTitles[l].setDisable(true);
            }
            buttonTitles[l].setGraphic(actionView[l]);
            adminButtons[l] = buttonTitles[l];
            actions.add(buttonTitles[l], 0, l);
        }

        gameMenu.setVisible(true);
        gameMenu.getChildren().add(menu);

        actionMenu.setVisible(true);
        actionMenu.getChildren().add(actions);

        stagesMenu.setVisible(true);
    }
}
