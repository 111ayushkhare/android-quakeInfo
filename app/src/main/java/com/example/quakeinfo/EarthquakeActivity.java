package com.example.quakeinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    private ListView mQuakeListView;
    private ArrayList<Earthquake> mQuake;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        mRecyclerView = findViewById(R.id.rv_earthquakes);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mQuake = new ArrayList<Earthquake>();
//        mQuake.add(new Earthquake("Humayi", "Tokyo Japan", "url", "url detail", 6.9, 876534567));
//        mQuake.add(new Earthquake("Humayi", "Tokyo Japan", "url", "url detail", 6.9, 876534567));
//        mQuake.add(new Earthquake("Humayi", "Tokyo Japan", "url", "url detail", 6.9, 876534567));
//        mQuake.add(new Earthquake("Humayi", "Tokyo Japan", "url", "url detail", 6.9, 876534567));
//        mQuake.add(new Earthquake("Humayi", "Tokyo Japan", "url", "url detail", 6.9, 876534567));
//        mQuake.add(new Earthquake("Humayi", "Tokyo Japan", "url", "url detail", 6.9, 876534567));
//        mQuake.add(new Earthquake("Humayi", "Tokyo Japan", "url", "url detail", 6.9, 876534567));
//        mQuake.add(new Earthquake("Humayi", "Tokyo Japan", "url", "url detail", 6.9, 876534567));
//        mQuake.add(new Earthquake("Humayi", "Tokyo Japan", "url", "url detail", 6.9, 876534567));
//        mQuake.add(new Earthquake("Humayi", "Tokyo Japan", "url", "url detail", 6.9, 876534567));

//        mQuakeListView = findViewById(R.id.quakeList);

        String response = getIntent().getStringExtra(Intent.EXTRA_TEXT);

        JSONObject baseJsonResponse = null;
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

                mQuake.add(new Earthquake(title, place, url, detail, magnitude, time));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        displayEarthquakes();
    }

    protected void displayEarthquakes() {
        mRecyclerViewAdapter = new RecyclerViewAdapter(EarthquakeActivity.this, mQuake);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }


}