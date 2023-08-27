package com.example.projekt_edp.fetchData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Lyrics {
    public Lyrics () {}

    public static String fetchLyrics(String name, String title) throws Exception{
        String url = "https://api.lyrics.ovh/v1/" + name + "/" + title;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        if(responseCode != 200)
            return "Not found";
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONObject myResponse = new JSONObject(response.toString());
        return myResponse.getString("lyrics");
    }
}