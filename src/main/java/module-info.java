module com.example.warringnationsbutgood {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires javafx.media;

    opens com.example.warringnationsbutgood to javafx.fxml;
    exports com.example.warringnationsbutgood;
}