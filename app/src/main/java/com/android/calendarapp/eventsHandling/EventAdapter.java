package com.android.calendarapp.eventsHandling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.calendarapp.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {
    private List<Event> eventList;
    private Context context;

    public EventAdapter(List<Event> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());

        View view = inf.inflate(R.layout.event_cell, parent, false);
        ViewGroup.LayoutParams layParams = view.getLayoutParams();

        layParams.height = (int) (parent.getHeight() * 0.2);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.eventName.setText(event.getNameOfEvent());
        holder.location.setText(event.getLocationOfTheEvent());
        holder.description.setText(event.getDescriptionOfTheEvent());
        holder.time.setText(event.getTimeOfTheEvent());
        // Nastavte další prvky UI podle potřeby
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

}
