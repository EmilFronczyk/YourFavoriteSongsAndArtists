package com.example.projekt_edp;

import com.example.projekt_edp.controllers.ArtistsController;
import com.example.projekt_edp.controllers.FavoritesController;
import com.example.projekt_edp.controllers.LyricsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // lyrics scene
        FXMLLoader lyricsLoader = new FXMLLoader(HelloApplication.class.getResource("lyrics.fxml"));
        Scene lyricsScene = new Scene(lyricsLoader.load(), 1026, 735);

        // artists scene
        FXMLLoader artistsLoader = new FXMLLoader(HelloApplication.class.getResource("artists.fxml"));
        Scene artistsScene = new Scene(artistsLoader.load(), 1026, 735);

        //favorites scene
        FXMLLoader favoritesLoader = new FXMLLoader(HelloApplication.class.getResource("favorites.fxml"));
        Scene favoritesScene = new Scene(favoritesLoader.load(), 1026, 735);

        // injecting artists scene into the controller of the lyrics scene
        LyricsController lyricsPaneController = lyricsLoader.getController();
        lyricsPaneController.setArtistsScene(artistsScene);
        // injecting favorites scene into the controller of the lyrics scene
        lyricsPaneController.setFavoritesScene(favoritesScene);

        // injecting lyrics scene into the controller of the artists scene
        ArtistsController artistsPaneController = artistsLoader.getController();
        artistsPaneController.setLyricsScene(lyricsScene);
        // injecting favorites scene into the controller of the artists scene
        artistsPaneController.setFavoritesScene(favoritesScene);

        // injecting lyrics scene into the controller of the favorites scene
        FavoritesController favoritesPaneController = favoritesLoader.getController();
        favoritesPaneController.setLyricsScene(lyricsScene);
        // injecting artists scene into the controller of the favorites scene
        favoritesPaneController.setArtistsScene(artistsScene);

        stage.setTitle("YoursFavSongs&Artists!");
        stage.setScene(lyricsScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}