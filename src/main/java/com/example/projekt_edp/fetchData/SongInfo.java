package com.example.projekt_edp.fetchData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class SongInfo {
    public SongInfo() {
    }

    private static StringBuffer fetchTrackData(String name, String title) throws IOException {
        String url = "https://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=ab8c6024c18d8a16dffed9a8cfcc3476&artist=" + name + "&track=" + title + "&format=json";
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

    private static StringBuffer fetchSimilarData(String name, String title) throws IOException {
        String url = "https://ws.audioscrobbler.com/2.0/?method=track.getsimilar&api_key=ab8c6024c18d8a16dffed9a8cfcc3476&artist=" + name + "&track=" + title + "&format=json";
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

    public static String fetchAlbum(String name, String title) throws Exception{
        StringBuffer response = fetchTrackData(name, title);
        JSONObject res = new JSONObject(response.toString());
        JSONObject track = new JSONObject(res.getString("track"));
        JSONObject album;
        if(track.has("album")) {
            album = new JSONObject(track.getString("album"));
            return album.getString("title");
        } else {
            return "Album not found";
        }

    }

    public static String fetchSongInfo(String name, String title) throws Exception{
        StringBuffer response = fetchTrackData(name, title);
        //JSONObject res = new JSONObject(response.toString());
        String jsonString = response.toString();
        JSONObject res = new JSONObject(jsonString);
        //System.out.println("res json = " + res);
        //JSONObject track = new JSONObject(res.getString("track"));
        JSONObject track = res.getJSONObject("track");
        //System.out.println("track json = " + track);
        JSONObject wiki;
        if(track.has("wiki")) {
            //wiki = new JSONObject(track.getString("wiki"));
            //System.out.println("weszlo do if'a wiki");
            wiki = track.getJSONObject("wiki");
            return wiki.getString("summary");
        } else {
            return "Not found";
        }

    }

    public static List<List<String>> fetchSimilarSongs(String name, String title) throws Exception{
        StringBuffer response = fetchSimilarData(name, title);

        JSONObject res = new JSONObject(response.toString());
        //System.out.println("res json = " + res);
        //JSONObject track = new JSONObject(res.getString("similartracks"));
        JSONObject track = res.getJSONObject("similartracks");
        //System.out.println("track json = " + track);

        JSONArray songsArray = track.getJSONArray("track");
        //System.out.println("songsArray json = " + songsArray);
        List<List<String>> result = new ArrayList<>();
        if(songsArray.length() == 0) {
            return result;
        }
        List<String> songTitleList = new ArrayList<>();
        List<String> songArtistList = new ArrayList<>();
        for(int i = 0 ; i < songsArray.length() ; i++){
            songTitleList.add(songsArray.getJSONObject(i).getString("name"));
        }
        for(int i = 0 ; i < songsArray.length() ; i++){
            //songArtistList.add(songsArray.getJSONObject(i).getString("artist"));
            //System.out.println("songsArray.getJSONObject(i) = "+songsArray.getJSONObject(i));
            songArtistList.add(String.valueOf(songsArray.getJSONObject(i).getJSONObject("artist")));
        }
        JSONObject artistName;
        List<String> songArtistNameList = new ArrayList<>();
        for(int i = 0 ; i < songArtistList.size() ; i++) {
            artistName = new JSONObject(songArtistList.get(i));
            songArtistNameList.add(artistName.getString("name"));
        }

        result.add(songArtistNameList);
        result.add(songTitleList);
        return result;
    }
}
