package com.android.calendarapp.calendarHandling;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.calendarapp.R;

import java.util.ArrayList;

public class CalendarAdapter  extends RecyclerView.Adapter<CalendarViewHolder>{

    private final ArrayList<String> daysInMonth;
    private final OnItemListener itemListener;

    public CalendarAdapter(ArrayList<String> daysInMonth, OnItemListener itemListener) {
        this.daysInMonth = daysInMonth;
        this.itemListener = itemListener;
    }

    //region CalendarViewHolder

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());

        View view = inf.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layParams = view.getLayoutParams();

        layParams.height = (int) (parent.getHeight() * 0.166666666);

        return new CalendarViewHolder(view, itemListener);
    }
    //endregion

    //region onBindViewHolder
    // 'onBindViewHolder' je zodpovědná za aktualizaci obsahu jednoho prvku v 'RecyclerView' s daty z poskytnutého seznamu (daysInMonth)
    /**
     * Populates the item at the specified position in the RecyclerView with data from the
     * 'daysInMonth' list.
     *
     * @param holder The ViewHolder to be updated with the data.
     * @param position The position of the item within the RecyclerView's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        holder.dayOfMonth.setText(daysInMonth.get(position));
    }
    //endregion

    @Override
    public int getItemCount() {
        return daysInMonth.size();
    }
}
