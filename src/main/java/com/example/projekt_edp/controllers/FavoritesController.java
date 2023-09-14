package com.example.projekt_edp.controllers;

import com.example.projekt_edp.DataBase;
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
        this.favoriteArtists = new ArrayList<>();
        this.favoriteSongs = new ArrayList<>();
        this.favoriteArtistsFromLyrics = new ArrayList<>();
    }
    public void setLyricsScene(Scene scene) {
        lyricsScene = scene;
    }

    public void setArtistsScene(Scene scene) {
        artistsScene = scene;
    }

    public void addToFavoritesArtists(String name) {
        if(!DataBase.readArtists().contains(name)) {
            DataBase.updateToFavoriteArtists(name);
            favoriteArtists = DataBase.readArtists();
        }
    }

    public void addToFavoritesSongsAndArtists(String name, String title) {
        if(!DataBase.readSongsAndArtists().get(0).contains(title) && !DataBase.readSongsAndArtists().get(1).contains(name)) {
            DataBase.updateToFavoriteSongs(name, title);
            favoriteSongs = DataBase.readSongsAndArtists().get(0);
favoriteArtistsFromLyrics = DataBase.readSongsAndArtists().get(1);
        }
    }

    public  void removeFromFavoritesArtists(String name) {
        DataBase.deleteFromFavoriteArtists(name);
        favoriteArtists=DataBase.readArtists();
        showFavoriteSongsAndArtists();
    }

    public  void removeFromFavoritesArtistsByIndex(int index) {
        removeFromFavoritesArtists(favoriteArtists.get(index));
    }

    public void removeFromFavoriteSongsAndArtists(String name, String title) {
        DataBase.deleteFromFavoriteSongs(name, title);
        favoriteSongs = DataBase.readSongsAndArtists().get(0);
        favoriteArtistsFromLyrics = DataBase.readSongsAndArtists().get(1);
        showFavoriteSongsAndArtists();
    }

    public  void removeFromFavoriteSongsAndArtistsByIndex(int index) {
        removeFromFavoriteSongsAndArtists(favoriteArtistsFromLyrics.get(index), favoriteSongs.get(index));
    }

    public void showFavoriteSongsAndArtists() {
String songAndArtists ="";
String artists = "";
favoriteSongs = DataBase.readSongsAndArtists().get(0);
favoriteArtists  = DataBase.readArtists();
favoriteArtistsFromLyrics = DataBase.readSongsAndArtists().get(1);

if(!favoriteSongs.isEmpty()) {
    for (int i = 0; i < favoriteSongs.size(); i++) {
        songAndArtists += i+1 + ". " + favoriteSongs.get(i) + " - " + favoriteArtistsFromLyrics.get(i) + "\n";
    }
}

        if(!favoriteArtists.isEmpty()) {
            for (int i = 0; i < favoriteArtists.size(); i++) {
                artists += i+1 + ". " + favoriteArtists.get(i) + "\n";
            }
        }
        favoriteSongsText.setText(songAndArtists);
        favoriteArtistsText.setText(artists);
    }

    public List<String> readFavoriteArtists() {
        return favoriteArtists;
    }

    public List<String> readFavoriteSongs() {
        return favoriteSongs;
    }

    public List<String> readFavoriteArtistsFromLyric() {
        return favoriteArtistsFromLyrics;
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
        System.out.println("artistNumber.getText() = " + songNumber.getText());
        int number = Integer.parseInt(songNumber.getText());
if(!(favoriteArtistsFromLyrics.isEmpty()) && favoriteArtistsFromLyrics.size() >= number && number> 0) {
    removeFromFavoriteSongsAndArtistsByIndex(number-1);
    showFavoriteSongsAndArtists();
}
    }

    @javafx.fxml.FXML
    public void onRemoveArtistClick(ActionEvent actionEvent) {
        int number = Integer.parseInt(artistNumber.getText());
        if(!(favoriteArtists.isEmpty()) && favoriteArtists.size() >= number && number> 0) {
            removeFromFavoritesArtistsByIndex(number-1);
            showFavoriteSongsAndArtists();
        }
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
