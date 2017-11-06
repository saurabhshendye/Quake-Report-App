/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;


import android.content.Context;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.quakereport.QuakeUtils.makeHttpRequest;

public class EarthquakeActivity extends AppCompatActivity implements
         android.app.LoaderManager.LoaderCallbacks<List<EarthQuake>> {

    private TextView mEmptyStateTextView;

    private ProgressBar progressBar;

    private EarthQuakeAdapter adapter;

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    public static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);


        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();

        if (netInfo!=null && netInfo.isConnected()) {

            // Initializing the loader
            getLoaderManager().initLoader(0, null, this);

            // Find a reference to the {@link ListView} in the layout
            ListView earthquakeListView = (ListView) findViewById(R.id.list);

            mEmptyStateTextView = (TextView) findViewById(R.id.emptyView);
            earthquakeListView.setEmptyView(mEmptyStateTextView);

            // Create a new {@link ArrayAdapter} of earthquakes
            adapter = new EarthQuakeAdapter(
                    this, new ArrayList<EarthQuake>());

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            earthquakeListView.setAdapter(adapter);

//        QuakeAsyncTask task = new QuakeAsyncTask();
//        task.execute(USGS_REQUEST_URL);


            earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id ) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    String url = adapter.getItem(position).getUrl();
                    intent.setData(Uri.parse(url));
                    startActivity(intent);

                }
            });
        } else {
            progressBar = (ProgressBar) findViewById(R.id.spinner);
            progressBar.setVisibility(View.GONE);

            mEmptyStateTextView = (TextView) findViewById(R.id.emptyView);
            mEmptyStateTextView.setText(R.string.no_internet);

        }



    }

    @Override
    public android.content.Loader<List<EarthQuake>> onCreateLoader(int i, Bundle bundle) {
        return new EarthQuakeLoader(this);
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<EarthQuake>> loader, List<EarthQuake> earthQuakes) {
        // Set empty state text to display "No earthquakes found."
        mEmptyStateTextView.setText(R.string.no_earthquakes);

        progressBar = (ProgressBar) findViewById(R.id.spinner);
        progressBar.setVisibility(View.GONE);

        adapter.clear();

        if (earthQuakes!=null && !earthQuakes.isEmpty()){
            adapter.addAll(earthQuakes);
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<EarthQuake>> loader) {
        adapter.clear();
    }


//    class QuakeAsyncTask extends AsyncTask<String, Void, ArrayList<EarthQuake>> {
//
//        @Override
//        protected ArrayList<EarthQuake> doInBackground(String... urls) {
//
//            String JSONString = "";
//
//            try {
//                JSONString = makeHttpRequest(urls[0]);
//            } catch (IOException e) {
//
//            }
//
//            // Create a fake list of earthquake locations.
//            ArrayList<EarthQuake> earthquakes = QuakeUtils.extractEarthquakes(JSONString);
//            return earthquakes;
//
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<EarthQuake> earthQuakes) {
//            adapter.clear();
//
//            if (earthQuakes!=null && !earthQuakes.isEmpty()){
//                adapter.addAll(earthQuakes);
//            }
//        }
//
//        }
//
//        private void updateUi(ArrayList<EarthQuake> earthquakes) {
//
//        }
}
