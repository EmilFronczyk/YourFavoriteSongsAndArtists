package com.example.projekt_edp;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static Connection con;
    private static final String url = "jdbc:postgresql://localhost:5432/projekt_edp";
    private static final String usrName = "postgres";
    private static final String passwd = "Postgresql123!";

    public static void createTable() {
        System.out.println("Started to create a table");

        try {
            con = DriverManager.getConnection(url,usrName,passwd);
            Statement statement = con.createStatement();
            DatabaseMetaData dbm = con.getMetaData();
            ResultSet tableFavoriteSongs = dbm.getTables(null, null, "FavoriteSongs", null);
            ResultSet tableFavoriteArtists = dbm.getTables(null, null, "FavoriteArtists", null);
            if (!tableFavoriteSongs.next()) { //Table Favorite Songs does not exists
                System.out.println("Table Favorite Songs doesn't exists");
                System.out.println("tableFavoriteSongs.next() = " + tableFavoriteSongs.next());
                String sqlFavoriteSongs = "CREATE TABLE FavoriteSongs " +
                        "(Title VARCHAR(100) NOT NULL, " +
                        " ArtistName VARCHAR(100) NOT NULL, " +
                        " PRIMARY KEY ( Title, ArtistName ));";

                statement.executeUpdate(sqlFavoriteSongs);
            }
            if (!tableFavoriteArtists.next()) { //Table Favorite Artists does not exists
                System.out.println("Table Favorite Artists doesn't exists");
                String sqlFavoriteArtists = "CREATE TABLE FavoriteArtists " +
                        "(ArtistName VARCHAR(100) NOT NULL, " +
                        " PRIMARY KEY ( ArtistName ));";

                statement.executeUpdate(sqlFavoriteArtists);
            }
            con.close();

        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Tabela istnieje");
        }
    }
    public static List<String> readArtists (){
        List<String> artistList = new ArrayList<>();
        try {
            con = DriverManager.getConnection(url,usrName,passwd);
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("select * from FavoriteArtists");
            ResultSetMetaData resultMetaData = result.getMetaData();
            while (result.next()) {
artistList.add(result.getString(1));
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artistList;
    }
    public static List<List<String>> readSongsAndArtists() {
        List<List<String>> songsAndArtistsList = new ArrayList<>();
        List<String> artistList = new ArrayList<>();
        List<String> songsList = new ArrayList<>();

        try {
            con = DriverManager.getConnection(url,usrName,passwd);
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("select * from FavoriteSongs");
            ResultSetMetaData resultMetaData = result.getMetaData();

            while(result.next()) {
                songsList.add(result.getString(1));
                artistList.add(result.getString(2));
            }

            songsAndArtistsList.add(songsList);
            songsAndArtistsList.add(artistList);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songsAndArtistsList;
    }

    public static void updateToFavoriteSongs(String name, String title) {
        try {
            con = DriverManager.getConnection(url,usrName,passwd);
            Statement statement = con.createStatement();
            statement.executeUpdate("INSERT INTO FavoriteSongs (Title,ArtistName) VALUES ('"+title+"','"+name+"')");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateToFavoriteArtists(String name) {
        try {
            con = DriverManager.getConnection(url,usrName,passwd);
            Statement statement = con.createStatement();
            statement.executeUpdate("INSERT INTO FavoriteArtists (ArtistName) VALUES ('"+name+"')");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFromFavoriteSongs(String name, String title) {
        try {
            con = DriverManager.getConnection(url,usrName,passwd);
            Statement statement = con.createStatement();
            statement.executeUpdate("delete from FavoriteSongs where Title=" + "'"+ title +"'" + " and ArtistName=" + "'"+ name +"'");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFromFavoriteArtists(String name) {
        try {
            con = DriverManager.getConnection(url,usrName,passwd);
            Statement statement = con.createStatement();
            statement.executeUpdate("delete from FavoriteArtists where ArtistName=" + "'"+ name +"'");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
