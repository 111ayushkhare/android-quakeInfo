package com.example.quakeinfo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mInitLayout;
    private Button mEarthquakesButton;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInitLayout = findViewById(R.id.initLayout);
        mEarthquakesButton = findViewById(R.id.earthquakeButton);
        mErrorMessageDisplay = findViewById(R.id.errorMessageView);
        mLoadingIndicator = findViewById(R.id.loadingIndicator);

        getLocationInfo();

    }

    private void makeEarthquakeQuery() {
        URL url = Network.buildUrl();
        new QuakeQuery().execute(url);
    }

    protected void showErrorMessage() {
        mErrorMessageDisplay.setText(getString(R.string.error));
    }

    @SuppressLint("StaticFieldLeak")
    public class QuakeQuery extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mInitLayout.setVisibility(View.GONE);
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            String jsonStringResponse = null;
            for (URL url : urls) {
                try {
                    jsonStringResponse = Network.getResponseFromHttpRequest(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return jsonStringResponse;
        }

        @Override
        protected void onPostExecute(String s) {
            mLoadingIndicator.setVisibility(View.GONE);
            Log.d("r", s);
            if (s != null && !s.equals("")) {
                Intent intent = new Intent(MainActivity.this, QuakeActivity.class);
                intent.putExtra("HTTP Response", s);
                startActivity(intent);
                mInitLayout.setVisibility(View.VISIBLE);
            } else {
                mErrorMessageDisplay.setVisibility(View.VISIBLE);
                showErrorMessage();
            }
        }
    }

    private void getLocationInfo() {
        mEarthquakesButton.setOnClickListener(view -> {
            makeEarthquakeQuery();
            //startActivity(new Intent(MainActivity.this, QuakeActivity.class));
        });
    }

}