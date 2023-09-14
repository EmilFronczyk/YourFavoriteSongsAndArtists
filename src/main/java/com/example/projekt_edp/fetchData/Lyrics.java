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
        //String url = "https://api.lyrics.ovh/v1/" + name + "/" + title;
        String url = "http://api.chartlyrics.com/apiv1.asmx/SearchLyricDirect?artist="+name+"&song="+title;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("respone code dla tekstu piosenki = " + responseCode);
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
        System.out.println("respone " + response);
        in.close();
        String myResponse = response.substring(response.indexOf("<Lyric>")+7);
        myResponse = myResponse.substring(0, myResponse.indexOf("</Lyric>"));
//        JSONObject myResponse = new JSONObject(response.toString());
//        return myResponse.getString("lyrics");
        System.out.println("myResponse = "+myResponse);
        return myResponse;
    }
}
