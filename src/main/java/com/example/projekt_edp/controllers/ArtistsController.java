package com.example.projekt_edp.controllers;

import com.example.projekt_edp.fetchData.ArtistsInfo;
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
    private List<String> similarArtists;
    private String description;
    private String name;
    private String title;
    private List<List<String>> topAlbums;
    private  boolean isButtonClicked = false;
    private FavoritesController favoritesPaneController;
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

    public void setFavoritesPaneController(FavoritesController favoritesPaneController){
        this.favoritesPaneController = favoritesPaneController;
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
isButtonClicked = false;
name = artistName2.getText();
String firstLetter = name.substring(0,1).toUpperCase();
String restOfName = name.substring(1).toLowerCase();
name = firstLetter + restOfName;
        heartButton2.setStyle("-fx-shape: \"M 340.8 98.4 c 50.7 0 87.2 37.6 92.2 86.6 c 7 28 -10.9 49.8 -24 70 L 256 410 L 104 257 c -17 -16 -25.6 -39.1 -25 -66 c 0 -51 41.1 -92.3 91.9 -92.3 c 38.2 0 70.9 23.4 84.8 56.8 C 269.8 121.9 302.6 98.4 340.8 98.4 M 340.8 83 C 307 83 276 98.8 256 124.8 c -20 -26 -51 -41.8 -84.8 -41.8 C 112.1 83 64 131.3 64 190.7 c 0 27.9 9 57.3 28 76.3 L 245 420 l 10.9 11 l 10.9 -11 l 152.2 -152 c 20 -23 32.8 -47.9 31 -79 C 448 131.3 399.9 83 340.8 83 L 340.8 83 z\"");
if (!name.isBlank()) {
    if (favoritesPaneController.readFavoriteArtists().contains(name)) {
        String newStyle =  "-fx-shape: \"M 24 0 c -3.4 0 -6.3 2.7 -7.6 5.6 C 14.7 2.7 11.8 0 8 0 C 4 0 0 3.8 0 8.4 c 0 9.4 7 12.6 16 21.2 c 9 -8.6 16 -12.1 16 -21.2 C 32 3.8 28.2 0 24 0 z\";  -fx-background-color: #00b359;";
        heartButton2.setStyle(newStyle);
    }

    new Thread(() -> {
        Platform.runLater(() -> {
            try {
this.description = ArtistsInfo.fetchArtistInfo(this.name.replaceAll("\\s+", "%20"));
int startIndex = description.indexOf("<a");
int endIndex = description.indexOf("a>");
String deleteSpecialCharacter = description.substring(startIndex,endIndex+2);
String textWithoutSpecialCharacter = description.replace(deleteSpecialCharacter, "");
artistDescriptionText.setText(textWithoutSpecialCharacter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }).start();

    new Thread(() -> {
        Platform.runLater(() -> {
            try {
                this.topAlbums = ArtistsInfo.fetchTopAlbums(this.name.replaceAll("\\s+", "%20"));
                if(!this.topAlbums.isEmpty()) {
                    String top = "";
                    for (int i = 0; i < this.topAlbums.get(0).size(); i++) {
top += this.topAlbums.get(0).get(i) + "\n";
                    }
                    topAlbumText.setText(top);
                } else {
                    topAlbumText.setText("Album Not Found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }).start();

//    Thread thread1 = new Thread(() -> {
//        Platform.runLater(() -> {
//            try {
//                System.out.println("Sranie orzed");
//                System.out.println("Uruchomiono watek set image");
//                String adres = ArtistsInfo.fetchArtistPhoto(this.name.replaceAll("\\s+", "%20"));
//                System.out.println("Cos po funckji");
//                Image obraz = new Image (ArtistsInfo.fetchArtistPhoto(this.name.replaceAll("\\s+", "%20")));
//                //albumPhoto.setImage(new Image(ArtistsInfo.fetchArtistPhoto(this.name.replaceAll("\\s+", "%20"))));
//                albumPhoto.setImage(obraz);
//            } catch (Exception e) {
//                albumPhoto.setImage(new Image("https://www.publicdomainpictures.net/pictures/280000/nahled/not-found-image-15383864787lu.jpg"));
//                System.out.println("Sranie w catch");
//            }
//        });
//    });
//    thread1.start();
//    new Thread(() -> {
//        Platform.runLater(() -> {
//            try {
//                System.out.println("Uruchomiono watek set image");
//                albumPhoto.setImage(new Image(ArtistsInfo.fetchArtistPhoto(this.name.replaceAll("\\s+", "%20"))));
//            } catch (Exception e) {
//                albumPhoto.setImage(new Image("https://www.publicdomainpictures.net/pictures/280000/nahled/not-found-image-15383864787lu.jpg"));
//            }
//        });
//    }).start();

    new Thread(() -> {
        Platform.runLater(() -> {
            try {
                System.out.println("Watek od smilar artists");
                this.similarArtists = ArtistsInfo.fetchSimilarArtists(this.name.replaceAll("\\s+", "%20"));
                if(!this.similarArtists.isEmpty()) {
                    String top = "";
                    for (int i = 0; i < this.similarArtists.size(); i++) {
                        top += this.similarArtists.get(i) + "\n";
                    }
                    similarArtistsText.setText(top);
                } else {
                    similarArtistsText.setText("Similar artists not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }).start();
}
    }

    @FXML
    public void onAHeartButtonClick2(ActionEvent actionEvent) {
        isButtonClicked = !isButtonClicked;
        if (isButtonClicked) {
            String newStyle =  "-fx-shape: \"M 24 0 c -3.4 0 -6.3 2.7 -7.6 5.6 C 14.7 2.7 11.8 0 8 0 C 4 0 0 3.8 0 8.4 c 0 9.4 7 12.6 16 21.2 c 9 -8.6 16 -12.1 16 -21.2 C 32 3.8 28.2 0 24 0 z\";  -fx-background-color: #00b359;";
            heartButton2.setStyle(newStyle);
            favoritesPaneController.addToFavoritesSongsAndArtists(name, title);
            favoritesPaneController.showFavoriteSongsAndArtists();
        }
        else {
            String newStyle =  "-fx-shape: \"M 340.8 98.4 c 50.7 0 87.2 37.6 92.2 86.6 c 7 28 -10.9 49.8 -24 70 L 256 410 L 104 257 c -17 -16 -25.6 -39.1 -25 -66 c 0 -51 41.1 -92.3 91.9 -92.3 c 38.2 0 70.9 23.4 84.8 56.8 C 269.8 121.9 302.6 98.4 340.8 98.4 M 340.8 83 C 307 83 276 98.8 256 124.8 c -20 -26 -51 -41.8 -84.8 -41.8 C 112.1 83 64 131.3 64 190.7 c 0 27.9 9 57.3 28 76.3 L 245 420 l 10.9 11 l 10.9 -11 l 152.2 -152 c 20 -23 32.8 -47.9 31 -79 C 448 131.3 399.9 83 340.8 83 L 340.8 83 z\"; -fx-base: transparent; -fx-background-color: white;";
            heartButton2.setStyle(newStyle);
            favoritesPaneController.removeFromFavoriteSongsAndArtists(name, title);
            favoritesPaneController.showFavoriteSongsAndArtists();
        }
    }
}
