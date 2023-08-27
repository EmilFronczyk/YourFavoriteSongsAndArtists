package com.example.projekt_edp.controllers;

import javafx.scene.Scene;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.List;
import java.util.Objects;

public class ArtistsController {
    private Scene lyricsScene;
    private Scene favoritesScene;
    @FXML
    private Label similarArtistsText;
    @FXML
    private Button lyricsButton;
    @FXML
    private Button favoriteArtistButton;
    @FXML
    private Button searchArtistButton;
    @FXML
    private TextField artistName2;
    @FXML
    private Button heartButton2;
    @FXML
    private Label artistDescriptionText;
    @FXML
    private Label topAlbumText;
    @FXML
    private Label topAlbumText2;
    @FXML
    private ImageView albumPhoto;

    public ArtistsController() {
    }
    public void setLyricsScene(Scene scene) {
        lyricsScene = scene;
    }

    public void setFavoritesScene(Scene scene) {
        favoritesScene = scene;
    }

    public void openLyricsScene(ActionEvent actionEvent) {
        Stage artistsStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        artistsStage.setScene(lyricsScene);
    }

    public void openFavoritesScene(ActionEvent actionEvent) {
        Stage artistsStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        artistsStage.setScene(favoritesScene);
    }

    @FXML
    public void onLyricsButtonClick(ActionEvent actionEvent) {
        this.openLyricsScene(actionEvent);
    }

    @FXML
    public void onFavoritesButtonClick(ActionEvent actionEvent) {
        this.openFavoritesScene(actionEvent);
    }

    @FXML
    public void onSearchArtistButtonClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onAHeartButtonClick2(ActionEvent actionEvent) {
    }
}
