package com.example.warringnationsbutgood;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;

public class GUI extends Application {

    private static final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("lobby.fxml"));
        final Image img = new Image("https://raw.githubusercontent.com/truongmleon/Warring-Nations-MoreMathEdition/master/src/main/resources/com/example/warringnationsbutgood/assets/icons/cow.png");
        scene = new Scene(fxmlLoader.load(), size.getWidth(), size.getHeight());
        primaryStage.getIcons().add(img);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Warring Nations");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }


    public static double getWidth() { return size.getWidth(); }

    public static double getHeight() { return  size.getHeight(); }

    public static Scene getScene() { return scene; }

    public static void main(String[] args) { launch(); }
}
