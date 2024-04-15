package com.android.calendarapp.eventsHandling;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.calendarapp.R;

public class EventViewHolder extends RecyclerView.ViewHolder {
    public TextView eventName;
    public TextView location;
    public TextView description;
    public TextView time;
    public EventViewHolder(@NonNull View itemView) {
        super(itemView);
        eventName = itemView.findViewById(R.id.eventCellName);
        location = itemView.findViewById(R.id.eventCellLocation);
        description = itemView.findViewById(R.id.eventCellDescritpion);
        time = itemView.findViewById(R.id.eventCellTime);

    }
}
