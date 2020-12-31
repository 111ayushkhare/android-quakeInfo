package com.example.quakeinfo;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.icu.text.UFormat;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Earthquake> mQuake;
    private LayoutInflater mInflater;

    private final String LOCATION_SEPARATOR = "of";

    RecyclerViewAdapter(Context context, List<Earthquake> quake) {
        mQuake = quake;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_earthquake, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Earthquake quake = mQuake.get(position);

        String primaryLoc = "Primary Location", offsetLoc = "Location Offset";
        String originalLoc = quake.getmQuakeLocation();

        if (originalLoc.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLoc.split(LOCATION_SEPARATOR);
            primaryLoc = parts[0] + LOCATION_SEPARATOR;
            offsetLoc = parts[1];
        }

        Date moment = new Date(quake.getmQuakeTime());
        String quakeDate = formatDate(moment);
        String quakeTime = formatTime(moment);

        holder.mag.setText(quake.getmQuakeMagnitude());
        holder.locOffset.setText(offsetLoc);
        holder.locPrimary.setText(primaryLoc);
        holder.date.setText(quakeDate);
        holder.date.setText(quakeTime);
    }

    @Override
    public int getItemCount() {
        return mQuake.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String formatDate(Date dateObj) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateFormat);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String formatTime(Date dateObj) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObj);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView locOffset;
        public TextView locPrimary;
        public TextView mag;
        public TextView time;
        public TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mag = itemView.findViewById(R.id.quakeMagnitude);
            locOffset = itemView.findViewById(R.id.locationOffset);
            locPrimary = itemView.findViewById(R.id.primaryLocation);
            date = itemView.findViewById(R.id.quakeDate);
            time = itemView.findViewById(R.id.quakeTime);

        }

        @Override
        public void onClick(View view) {
            Log.d("Click from View Holder", "Clicked");
        }
    }
}
