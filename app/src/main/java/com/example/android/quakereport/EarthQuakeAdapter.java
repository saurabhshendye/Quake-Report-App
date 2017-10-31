package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Saurabh on 10/16/2017.
 */

public class EarthQuakeAdapter extends ArrayAdapter<EarthQuake> {

    public EarthQuakeAdapter(Context context, ArrayList<EarthQuake> earthQuake){
        super(context, 0, earthQuake);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        // Check if the existing view is being reused, otherwise inflate the view
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        EarthQuake earthQuake = getItem(position);



        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitudeView.setText(String.valueOf(earthQuake.getMagnitude()));

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        int magnitudeColor = getMagnitudeColor(earthQuake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        String location = earthQuake.getCity();
        String[] locationDetails = getParts(location);

        TextView primLocation = (TextView) listItemView.findViewById(R.id.primary_location);
        primLocation.setText(locationDetails[1]);

        TextView secLocation = (TextView) listItemView.findViewById(R.id.secondary_location);
        secLocation.setText(locationDetails[0]);

        Date dateObject = earthQuake.getTime();
        String formattedDate = formatDate(dateObject);

        TextView DateView = (TextView) listItemView.findViewById(R.id.Date);
        DateView.setText(formattedDate);

        String formattedTime = formatTime(dateObject);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        timeView.setText(formattedTime);

        return listItemView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime (Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String[] getParts(String location) {
        String[] parts = new String[2];
        for (int i = 0; i < location.length()-1; i++) {
            if (location.charAt(i) == 'o' && location.charAt(i+1) == 'f') {
                    parts[0] = location.substring(0,i+2);
                    parts[1] = location.substring(i+2,location.length());
                    return parts;
            }
        }

        parts[0] = "Near the";
        parts[1] = location;

        return parts;
    }

    private int getMagnitudeColor(String magnitude) {
        int mag = (int) Math.floor(Double.parseDouble(magnitude));
        int magnitudeColorResourceId;
        switch (mag) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}
