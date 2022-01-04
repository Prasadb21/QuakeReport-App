package com.example.quakereport;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.view.ViewGroup;
import java.text.DecimalFormat;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.List;

public class EarthquakeAdaptor extends ArrayAdapter<EarthQuake> {
    private static final String LOCATION_SEPARATOR = "of";


    public EarthquakeAdaptor(@NonNull Context context, List<EarthQuake> earthQuakes) {
        super(context, 0, earthQuakes);
    }
    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(Double magnitude)
    {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);

    }

    private int getMagnitudeColor(double number)
    {
        int magnitudeResourceId;
        int magnitudeFloor = (int) Math.floor((number));
        switch (magnitudeFloor)
        {
            case 0:
            case 1:
                magnitudeResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeResourceId = R.color.magnitude10plus;
                break;

        }
        return ContextCompat.getColor(getContext() , magnitudeResourceId);
    }


    public View getView(int position, View counterView, ViewGroup parent) {
        View listItemView = counterView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }





        EarthQuake currentEarthquake = getItem(position);

        String orignalLocation = currentEarthquake.getLocation();
        String primaryLoaction;
        String OffsetLocation;

        if (orignalLocation.contains(LOCATION_SEPARATOR))
        {
            String[] parts = orignalLocation.split(LOCATION_SEPARATOR);
            OffsetLocation = parts[0] + LOCATION_SEPARATOR;
            primaryLoaction = parts[1];
        }
        else
        {
            OffsetLocation = getContext().getString(R.string.near_the);
            primaryLoaction = orignalLocation;

        }

        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        magnitudeView.setText(formattedMagnitude);
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        magnitudeCircle.setColor(magnitudeColor);


        TextView locationOffset = (TextView) listItemView.findViewById(R.id.location_offset);
        locationOffset.setText(OffsetLocation);


        TextView primaryLocation = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocation.setText(primaryLoaction);





        Date dateObject = new Date(currentEarthquake.getDate());

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        dateView.setText(formattedDate);


        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);



        return listItemView;



    }


}



