package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.controllers.MainFrameController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Init title and style
        primaryStage.setTitle("Essence Music Player");
        primaryStage.initStyle(StageStyle.UNDECORATED);

        // Set property
        System.setProperty("prism.lcdtext", "false");

        // Create root and loader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/MainFrame.fxml"));
        Parent root = loader.load();

        // Create default scene
        Scene scene = new Scene(root, 1100, 650, Color.rgb(34,34,34));
        scene.getStylesheets().add(getClass().getResource("css/styles.css").toString());

        // Set scene
        primaryStage.setScene(scene);
        primaryStage.show();

        // Initialize media player
        MediaPlayer.setMainController((MainFrameController) loader.getController());
        MediaPlayer.setTrack(new Track("Send it up", "Yeezus", "Kanye West", 250));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
