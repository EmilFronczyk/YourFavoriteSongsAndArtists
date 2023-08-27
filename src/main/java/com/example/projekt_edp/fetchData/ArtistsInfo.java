package com.example.projekt_edp.fetchData;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ArtistsInfo {
    public ArtistsInfo() {}

    private static StringBuffer fetchArtistData(String name) throws IOException {
        String url = "https://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist=" + name + "&api_key=ab8c6024c18d8a16dffed9a8cfcc3476&format=json";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response;
    }

    private static StringBuffer fetchAlbumsData(String name) throws IOException {
        String url = "https://ws.audioscrobbler.com/2.0/?method=artist.gettopalbums&artist=" + name + "&api_key=ab8c6024c18d8a16dffed9a8cfcc3476&format=json";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response;
    }

    private static StringBuffer fetchArtistPhotoData(String name) throws IOException {
        String url = "https://www.theaudiodb.com/api/v1/json/2/search.php?s=" + name;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response;
    }

    public static String fetchArtistPhoto(String name) throws Exception{
        StringBuffer response = fetchArtistPhotoData(name);
        JSONObject res = new JSONObject(response.toString());
        JSONArray artistsArray;
        try {
            artistsArray = res.getJSONArray("artists");
        } catch(Exception e) {
            return "not found";
        }

        String result = artistsArray.getJSONObject(0).getString("strArtistThumb");
        return result;
    }

    public static String fetchArtistInfo(String name) throws Exception{
        StringBuffer response = fetchArtistData(name);
        JSONObject res = new JSONObject(response.toString());
        String result;
        if(!res.has("artist")) {
            return "Not found";
        }
        JSONObject artists = new JSONObject(res.getString("artist"));
        JSONObject info = new JSONObject(artists.getString("bio"));
        result = info.getString("summary");
        return result;
    }

    public static List<String> fetchSimilarArtists(String name) throws Exception{
        StringBuffer response = fetchArtistData(name);
        List<String> artistsList = new ArrayList<String>();
        JSONObject res = new JSONObject(response.toString());
        if(!res.has("artist")) {
            return artistsList;
        }
        JSONObject artist = new JSONObject(res.getString("artist"));
        JSONObject similar = new JSONObject(artist.getString("similar"));

        JSONArray artistsArray = similar.getJSONArray("artist");
        for(int i = 0 ; i < artistsArray.length() ; i++){
            artistsList.add(artistsArray.getJSONObject(i).getString("name"));
        }
        return artistsList;
    }

    public static List<List<String>> fetchTopAlbums(String name) throws Exception{

        StringBuffer response = fetchAlbumsData(name);
        List<List<String>> result = new ArrayList<>();
        JSONObject res = new JSONObject(response.toString());
        if(!res.has("topalbums")) {
            return result;
        }
        JSONObject topAlbums = new JSONObject(res.getString("topalbums"));

        JSONArray albums = topAlbums.getJSONArray("album");
        List<String> albumTitleList = new ArrayList<String>();
        List<String> albumUrlList = new ArrayList<String>();
        for(int i = 0 ; i < albums.length() ; i++){
            albumTitleList.add(albums.getJSONObject(i).getString("name"));
        }
        JSONArray imageUrls;
        JSONObject images;
        for(int i = 0 ; i < albums.length() ; i++){
            images = new JSONObject(albums.getJSONObject(i).toString());
            imageUrls = images.getJSONArray("image");
            for(int j = 0 ; j < imageUrls.length() ; j++){
                if(j % 3 == 0 && j != 0)
                    albumUrlList.add(imageUrls.getJSONObject(j).getString("#text"));
            }
        }
        result.add(albumTitleList);
        result.add(albumUrlList);

        return result;

    }
}
