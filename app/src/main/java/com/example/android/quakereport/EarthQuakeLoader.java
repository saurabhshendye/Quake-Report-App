package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.quakereport.QuakeUtils.makeHttpRequest;

/**
 * Created by Saurabh on 11/3/2017.
 */

public class EarthQuakeLoader extends AsyncTaskLoader<List<EarthQuake>> {

    public static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    public EarthQuakeLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<EarthQuake> loadInBackground() {
        String JSONString = "";

        try {
            Thread.sleep(2000);
            JSONString = makeHttpRequest(USGS_REQUEST_URL);
        } catch (IOException e) {

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Create a list of earthquake locations.
        return QuakeUtils.extractEarthquakes(JSONString);
    }
}
