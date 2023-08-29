package com.example.projekt_edp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.projekt_edp.DataBase;

import java.net.URL;
import java.sql.JDBCType;
import java.util.ResourceBundle;

public class LyricsController {
    private Scene artistsScene;
    private Scene favoritesScene;
    private  boolean isButtonClicked = false;
    private FavoritesController favoritesPaneController;
    @javafx.fxml.FXML
    private Button lyricsButton;
    @javafx.fxml.FXML
    private Button artistsButton;
    @javafx.fxml.FXML
    private Button favoritesButton;
    @javafx.fxml.FXML
    private Button searchButton;
    @javafx.fxml.FXML
    private TextField songTitle;
    @javafx.fxml.FXML
    private TextField artistName;
    @javafx.fxml.FXML
    private Button heartButton;
    @javafx.fxml.FXML
    private Label lyricsText;
    @javafx.fxml.FXML
    private Label artistText;
    @javafx.fxml.FXML
    private Label songInfoText;
    @javafx.fxml.FXML
    private Label similarText;


    public LyricsController(){
        try {
            DataBase.createTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setArtistsScene(Scene scene) {
        this.artistsScene = scene;
    }

    public void setFavoritesScene(Scene scene) {
        this.favoritesScene = scene;
    }
    public void openArtistsScene(ActionEvent actionEvent) {
        Stage lyricsStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        lyricsStage.setScene(this.artistsScene);
    }

    public void openFavoritesScene(ActionEvent actionEvent) {
        Stage lyricsStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        lyricsStage.setScene(this.favoritesScene);
    }

    public void setFavoritesPaneController (FavoritesController favoritesPaneController) {
        this.favoritesPaneController = favoritesPaneController;
    }

    @javafx.fxml.FXML
    public void onArtistsButtonClick(ActionEvent actionEvent) {
        this.openArtistsScene(actionEvent);
    }

    @javafx.fxml.FXML
    public void onFavoritesButtonClick(ActionEvent actionEvent) {
        //favoritesPaneController.showFavoriteSongsAndArtists();
         this.openFavoritesScene(actionEvent);
    }

    @javafx.fxml.FXML
    public void onSearchButtonClick(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void onAHeartButtonClick(ActionEvent actionEvent) {
        isButtonClicked = !isButtonClicked;
        if (isButtonClicked) {
            String newStyle =  "-fx-shape: \"M 24 0 c -3.4 0 -6.3 2.7 -7.6 5.6 C 14.7 2.7 11.8 0 8 0 C 4 0 0 3.8 0 8.4 c 0 9.4 7 12.6 16 21.2 c 9 -8.6 16 -12.1 16 -21.2 C 32 3.8 28.2 0 24 0 z\";  -fx-background-color: #00b359;";
            heartButton.setStyle(newStyle);
        }
        else {
            String newStyle =  "-fx-shape: \"M 340.8 98.4 c 50.7 0 87.2 37.6 92.2 86.6 c 7 28 -10.9 49.8 -24 70 L 256 410 L 104 257 c -17 -16 -25.6 -39.1 -25 -66 c 0 -51 41.1 -92.3 91.9 -92.3 c 38.2 0 70.9 23.4 84.8 56.8 C 269.8 121.9 302.6 98.4 340.8 98.4 M 340.8 83 C 307 83 276 98.8 256 124.8 c -20 -26 -51 -41.8 -84.8 -41.8 C 112.1 83 64 131.3 64 190.7 c 0 27.9 9 57.3 28 76.3 L 245 420 l 10.9 11 l 10.9 -11 l 152.2 -152 c 20 -23 32.8 -47.9 31 -79 C 448 131.3 399.9 83 340.8 83 L 340.8 83 z\"; -fx-base: transparent; -fx-background-color: white;";
            heartButton.setStyle(newStyle);
        }

    }

}
