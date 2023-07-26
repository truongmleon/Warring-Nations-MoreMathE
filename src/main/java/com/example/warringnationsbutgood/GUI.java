package com.example.warringnationsbutgood;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class GUI extends Application {

    private static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("lobby.fxml"));
        scene = new Scene(fxmlLoader.load(), size.getWidth(), size.getHeight());
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
