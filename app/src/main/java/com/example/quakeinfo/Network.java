package com.example.quakeinfo;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Network {

    private static final String EARTHQUAKE_SEARCH_QUERY = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2019-01-01&endtime=2021-01-02&minmagnitude=6";

    public static URL buildUrl() {
        Uri builtUri = Uri.parse(EARTHQUAKE_SEARCH_QUERY).buildUpon().build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.d("URL: ", url.toString());
        return url;
    }

    public static String getResponseFromHttpRequest(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            return parseInputStream(in);
        } finally {
            urlConnection.disconnect();
        }
    }

    private static String parseInputStream(InputStream is) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            return sb.toString();
        }
    }
}
