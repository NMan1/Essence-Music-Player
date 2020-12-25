package main.controllers;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import main.MediaPlayer;
import main.Track;

import java.io.IOException;


public class MainFrameController {

    private double xOffset = 0;

    private double yOffset = 0;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private Pane titlePane;

    @FXML
    private Rectangle seekBar;

    @FXML
    private Rectangle seekBarProgress;

    @FXML
    private Label trackCurrentPositionLabel;

    @FXML
    private Label trackLengthLabel;

    @FXML
    private ImageView albumCoverImage;

    @FXML
    private Label trackNameLabel;

    @FXML
    private Label trackArtistNameLabel;

    @FXML
    private Slider volumeBar;

    @FXML
    private ImageView playToggle;

    @FXML
    private Button libraryButton;

    @FXML
    private Button browseButton;

    @FXML
    private Button chartButton;

    @FXML
    private TextField searchBar;

    @FXML
    void initialize() throws IOException {
        titlePane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        titlePane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                titlePane.getScene().getWindow().setX(event.getScreenX() - xOffset);
                titlePane.getScene().getWindow().setY(event.getScreenY() - yOffset);
            }
        });

        MediaPlayer.setVolume((float) volumeBar.getValue());
        MediaPlayer.setLastVolume(MediaPlayer.getVolume());

        volumeBar.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.RIGHT) {
                MediaPlayer.setVolume((float) volumeBar.getValue());
                MediaPlayer.setLastVolume(MediaPlayer.getVolume());
                volumeBar.setStyle(String.format("-fx-background-color: linear-gradient(to right, #00d8ff %d%%, #222222 %d%%);", (int)(MediaPlayer.getVolume() * 100), (int)(MediaPlayer.getVolume() * 100)));
            }
        });

        volumeBar.setStyle(String.format("-fx-background-color: linear-gradient(to right, #00d8ff %d%%, #222222 %d%%);", (int)(MediaPlayer.getVolume() * 100), (int)(MediaPlayer.getVolume() * 100)));

        libraryButton.setStyle("-fx-border-color: #00d8ff;");
        browseButton.setStyle("-fx-border-color: #383838;");
        chartButton.setStyle("-fx-border-color: #383838;");
    }

    @FXML
    void handleClosePressed(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void handleCloseEntered(MouseEvent event) {
        ((ImageView)event.getSource()).setImage(new Image(getClass().getResource("../images/close_hovered.png").toString()));
    }

    @FXML
    void handleCloseExited(MouseEvent event) {
        ((ImageView)event.getSource()).setImage(new Image(getClass().getResource("../images/close.png").toString()));
    }

    @FXML
    void handleMinimizeEntered(MouseEvent event) {

    }

    @FXML
    void handleMinimizeExited(MouseEvent event) {

    }

    @FXML
    void handleMinimizePressed(MouseEvent event) {
        ((Stage)((ImageView)event.getSource()).getScene().getWindow()).setIconified(true);
    }

    @FXML
    void handleSeekBarPressed(MouseEvent event) {
        double clickX = event.getX();
        processSeekClick(event, clickX);
    }

    @FXML
    void handleSeekBarProgressPressed(MouseEvent event) {
        double clickX = event.getX();
        processSeekClick(event, clickX);
    }

    @FXML
    void handleVolumePressed(MouseEvent event) {
        MediaPlayer.setVolume((float) volumeBar.getValue());
        MediaPlayer.setLastVolume(MediaPlayer.getVolume());

        volumeBar.setStyle(String.format("-fx-background-color: linear-gradient(to right, #00d8ff %d%%, #222222 %d%%);", (int)(MediaPlayer.getVolume() * 100), (int)(MediaPlayer.getVolume() * 100)));
    }

    @FXML
    void handleVolumeIconPressed(MouseEvent event) {
        if (MediaPlayer.getVolume() == 0)
            MediaPlayer.setVolume(MediaPlayer.getLastVolume());
        else
            MediaPlayer.setVolume(0);
        volumeBar.setValue(MediaPlayer.getVolume());
        volumeBar.setStyle(String.format("-fx-background-color: linear-gradient(to right, #00d8ff %d%%, #222222 %d%%);", (int)(MediaPlayer.getVolume() * 100), (int)(MediaPlayer.getVolume() * 100)));
    }

    @FXML
    void handlePlayTogglePressed(MouseEvent event) {
        if (!MediaPlayer.getPaused()) {
            ((ImageView)event.getSource()).setImage(new Image(getClass().getResource("../images/pause.png").toString()));
            MediaPlayer.setPaused(true);
        }
        else {
            ((ImageView)event.getSource()).setImage(new Image(getClass().getResource("../images/play.png").toString()));
            MediaPlayer.setPaused(false);
        }
    }

    @FXML
    void handleSearchBarPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/SearchResults.fxml"));
            mainBorderPane.setCenter(loader.load());

            SearchResultsController controller = (SearchResultsController)loader.getController();
            controller.setSearchLabel(searchBar.getText());
            controller.makeSearch(searchBar.getText());
        }
    }

    @FXML
    void handleLibraryButtonPressed(MouseEvent event) {
        libraryButton.setStyle("-fx-border-color: #00d8ff;");
        browseButton.setStyle("-fx-border-color:  #383838;");
        chartButton.setStyle("-fx-border-color:  #383838;");
    }

    @FXML
    void handleBrowseButtonPressed(MouseEvent event) {
        libraryButton.setStyle("-fx-border-color: #383838;");
        browseButton.setStyle("-fx-border-color:  #00d8ff;");
        chartButton.setStyle("-fx-border-color:  #383838;");
    }

    @FXML
    void handleChartsButtonPressed(MouseEvent event) {
        libraryButton.setStyle("-fx-border-color: #383838;");
        browseButton.setStyle("-fx-border-color:  #383838;");
        chartButton.setStyle("-fx-border-color:  #00d8ff;");
    }

    private void processSeekClick(MouseEvent event, double clickX) {
        seekBarProgress.setWidth(clickX);

        double secondsPerPixel = MediaPlayer.getCurrentTrack().length / seekBar.getWidth();
        MediaPlayer.setCurrentPosition((int) (clickX * secondsPerPixel));

        int minutes = (MediaPlayer.getCurrentPosition() % 3600) / 60;
        int seconds = MediaPlayer.getCurrentPosition() % 60;

        trackCurrentPositionLabel.setText(String.format("%01d:%02d", minutes, seconds));
    }

    public void updatePlayer(Track track) {
        int minutes = (track.length % 3600) / 60;
        int seconds = track.length % 60;

        // Set track length and position
        trackCurrentPositionLabel.setText("0:00");
        trackLengthLabel.setText(String.format("%01d:%02d", minutes, seconds));

        // Set seek bar at 0
        seekBarProgress.setWidth(0);

        // Set track name and artist name
        trackNameLabel.setText(track.name);
        trackArtistNameLabel.setText(track.artist);
    }
}
