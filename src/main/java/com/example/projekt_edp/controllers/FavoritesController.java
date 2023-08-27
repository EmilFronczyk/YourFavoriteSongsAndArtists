package com.example.projekt_edp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class FavoritesController {
    private Scene lyricsScene;
    private Scene artistsScene;
    private List<String> favoriteArtists;
    private List<String> favoriteSongs;
    private List<String> favoriteArtistsFromLyrics;
    @javafx.fxml.FXML
    private Button removeSongButton;
    @javafx.fxml.FXML
    private TextField songNumber;
    @javafx.fxml.FXML
    private Button removeArtistButton;
    @javafx.fxml.FXML
    private TextField artistNumber;
    @javafx.fxml.FXML
    private Button lyricsButton;
    @javafx.fxml.FXML
    private Button artistsButton;
    @javafx.fxml.FXML
    private Button favoritesButton;
    @javafx.fxml.FXML
    private Label favoriteSongsText;
    @javafx.fxml.FXML
    private Label favoriteArtistsText;
    @javafx.fxml.FXML
    private Label favoriteArtistsText2;
    @javafx.fxml.FXML
    private Label favoriteSongsText2;

    public FavoritesController() {
        favoriteArtists = new ArrayList<>();
        favoriteSongs = new ArrayList<>();
        favoriteArtistsFromLyrics = new ArrayList<>();
    }
    public void setLyricsScene(Scene scene) {
        lyricsScene = scene;
    }

    public void setArtistsScene(Scene scene) {
        artistsScene = scene;
    }
    @FXML
    public void openLyricsScene(ActionEvent actionEvent) {
        Stage favoritesStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        favoritesStage.setScene(lyricsScene);
    }

    @FXML
    public void openArtistsScene(ActionEvent actionEvent) {
        Stage favoritesStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        favoritesStage.setScene(artistsScene);
    }

    @javafx.fxml.FXML
    public void onRemoveSongClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onRemoveArtistClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onLyricsButtonClick(ActionEvent actionEvent) {
        this.openLyricsScene(actionEvent);
    }

    @javafx.fxml.FXML
    public void onArtistsButtonClick(ActionEvent actionEvent) {
        this.openArtistsScene(actionEvent);
    }
}
