package main.controllers;

import be.ceau.itunesapi.response.Response;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import main.SearchApi;
import main.SearchResponse;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.List;

public class SearchResultsController {

    @FXML
    private Label showingResultsLabel;

    @FXML
    private HBox topResultsHBox;

    @FXML
    void initialize() {

    }

    public void setSearchLabel(String searchText) {
        showingResultsLabel.setText("Showing results for \"" + searchText + "\"");
    }

    public void makeContainers(List<SearchResponse> topResults) {
        for (var results : topResults) {
            HBox outerBox = new HBox();
            VBox innerBox = new VBox();

            outerBox.setPadding(new Insets(25, 10, 20, 20));
            innerBox.setPadding(new Insets(5, 5, 5, 10));
            innerBox.setSpacing(5);

            ImageView artistPfp = new ImageView(results.getMediaUrl());

            if (results.getMediaType().equals("Album")) {
                Label albumName = new Label(results.getAlbumName());
                albumName.setFont(new Font("Helvetica", 16));
                albumName.setStyle("-fx-text-fill: #ffffff;");
                innerBox.getChildren().add(albumName);
            }
            else if (results.getMediaType().equals("Song")) {
                Label songName = new Label(results.getSongName());
                songName.setFont(new Font("Helvetica", 16));
                songName.setStyle("-fx-text-fill: #ffffff;");
                innerBox.getChildren().add(songName);
            }
            else {
                innerBox.setSpacing(20);
            }

            Label artistName = new Label(results.getArtistName());
            artistName.setFont(new Font("Helvetica", 16));
            artistName.setStyle("-fx-text-fill: #ffffff;");

            Label type = new Label(results.getMediaType());
            type.setFont(new Font("Helvetica", 16));
            type.setStyle("-fx-text-fill: #ffffff;");

            innerBox.getChildren().addAll(artistName, type);
            outerBox.getChildren().addAll(artistPfp, innerBox);

            topResultsHBox.getChildren().add(outerBox);
        }

        topResultsHBox.setSpacing(120);
    }

    public void makeSearch(String searchText) throws IOException {
        makeContainers(SearchApi.getTopResults(searchText));
    }
}