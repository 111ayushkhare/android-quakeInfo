package com.example.quakeinfo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuakeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quake);

        mRecyclerView = findViewById(R.id.rv_earthquakes);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        String response = getIntent().getStringExtra("HTTP Response");
        //Log.d("intentStr", response);
        //String response = Network.SAMPLE_JSON_RESPONSE;

        ArrayList<Earthquake> quake = getJsonDecode(response);
        displayEarthquakes(quake);
    }

    protected void displayEarthquakes(ArrayList<Earthquake> quakeList) {
        RecyclerViewAdapter mRecyclerViewAdapter = new RecyclerViewAdapter(QuakeActivity.this, quakeList);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    protected ArrayList<Earthquake> getJsonDecode(String response) {
        ArrayList<Earthquake> responseList = new ArrayList<>();
        JSONObject baseJsonResponse;
        try {
            baseJsonResponse = new JSONObject(response);
            JSONArray earthquakeArray = baseJsonResponse.getJSONArray("features");

            for (int i = 0; i < earthquakeArray.length(); i++) {
                JSONObject currentEarthquake = earthquakeArray.getJSONObject(i);
                JSONObject properties = currentEarthquake.getJSONObject("properties");

                String title = properties.getString("title");
                String place = properties.getString("place");
                String url = properties.getString("url");
                String detail = properties.getString("detail");
                double magnitude = properties.getDouble("mag");
                long time = properties.getLong("time");

                responseList.add(new Earthquake(title, place, url, detail, magnitude, time));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return responseList;
        }
    }

}