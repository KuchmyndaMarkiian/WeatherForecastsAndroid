package com.dev.kuchmynda.weatherforecasts;

import Abstractions.MemoryStorage;
import Abstractions.ParsingFactory;
import Models.City;
import Models.CityModel;
import Services.CityParsingFactory;
import Services.ServerDownloader;
import StaticConstants.Files;
import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.wang.avi.AVLoadingIndicatorView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class LoadingActivity extends AppCompatActivity {
    TextView messageView;
    AVLoadingIndicatorView indicatorView;
    Button button;
    AutoCompleteTextView completeTextView;
    ArrayList<CityModel> cityModels=new ArrayList<>();
    Activity current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        messageView = (TextView) findViewById(R.id.loadingMessage);
        indicatorView = (AVLoadingIndicatorView) findViewById(R.id.indicator);
        completeTextView=(AutoCompleteTextView) findViewById(R.id.textCity);
        button=(Button) findViewById(R.id.searchButton);
        current=this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setEnabled(false);
                completeTextView.setEnabled(false);
                (new Searcher(current)).execute();
            }
        });
        indicatorView.smoothToShow();
        new Downloader(this).execute();
    }
    //region AsyncTasks
    class Searcher extends AsyncTask<Void,Void,Void>{
        Activity activity;
        Searcher(Activity activity) {
            this.activity = activity;
            messageView.setText("Searching...");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //todo new intent
            cityModels.clear();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                boolean isFound=false;
                CityModel cityModel=new CityModel();
                for (CityModel model:cityModels) {
                    if (model.getName().equalsIgnoreCase(completeTextView.getText().toString()))
                    {
                        isFound = true;
                        cityModel=model;
                        break;
                    }
                }
                if(isFound) {
                    File dir = activity.getExternalFilesDir(null);
                    if (!Files.IsExistsFile(dir, Files.myCity)) {
                        MemoryStorage<CityModel> storage=new MemoryStorage<>(dir,Files.myCity);
                        storage.writeData(cityModel);
                    }
                }
            } catch (final Exception e) {
                this.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
            return null;
        }
    }
    class Downloader extends AsyncTask<Void, Void, Void> {
        Activity activity;

        Downloader(Activity activity) {
            this.activity = activity;
            messageView.setText("Downloadind...");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            messageView.setText("Downloaded)");
            button.setEnabled(true);
            String[] strings=new String[cityModels.size()];
            int i=0;
            for (CityModel model:cityModels) {
                strings[i++]=model.getName().concat(",".concat(model.getCountryCode()));
            }
            ArrayAdapter adapter = new ArrayAdapter(this.activity, android.R.layout.simple_list_item_1, strings);
            completeTextView.setAdapter(adapter);
            completeTextView.setThreshold(1);
            completeTextView.setEnabled(true);
            indicatorView.smoothToHide();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                    cityModels=ServerDownloader.DownloadCities();
            } catch (final Exception e) {
                this.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
            return null;
        }
    }
    //endregion
}
