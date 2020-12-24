package com.example.quakeinfo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mInitLayout;
    private Button mEarthquakesButton;
    private Button mTsunamiButton;
    private Button mFloodsButton;
    private Button mLandslidesButton;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInitLayout = (LinearLayout) findViewById(R.id.initLayout);
        mEarthquakesButton = (Button) findViewById(R.id.earthquakeButton);
        mEarthquakesButton = (Button) findViewById(R.id.tsunamiButton);
        mEarthquakesButton = (Button) findViewById(R.id.floodButton);
        mEarthquakesButton = (Button) findViewById(R.id.landslidesButton);
        mErrorMessageDisplay = (TextView) findViewById(R.id.errorMessageView);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.loadingIndicator);

        getLocationInfo();

    }

    protected void makeEarthquakeQuery() {
        URL url = Network.buildUrl();
        new QuakeQuery().execute(url);
    }

    protected void showErrorMessage() {
        mErrorMessageDisplay.setText(getString(R.string.error));
    }

    public class QuakeQuery extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mInitLayout.setVisibility(View.INVISIBLE);
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String jsonStringResponse = null;
            try {
                jsonStringResponse = Network.getResponseFromHttpRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonStringResponse;
        }

        @Override
        protected void onPostExecute(String s) {
            mLoadingIndicator.setVisibility(View.GONE);
            if (s != null && !s.equals("")) {
                Intent intent = new Intent(MainActivity.this, EarthquakeActivity.class);
                intent.putExtra("response", s);
                startActivity(intent);
            } else {
                mErrorMessageDisplay.setVisibility(View.VISIBLE);
                showErrorMessage();
            }
        }
    }

    protected void getLocationInfo() {
        mEarthquakesButton.setOnClickListener(view -> {
            makeEarthquakeQuery();
        });
    }

}