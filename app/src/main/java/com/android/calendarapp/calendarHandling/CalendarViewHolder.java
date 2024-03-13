package com.android.calendarapp.calendarHandling;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.calendarapp.R;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public final TextView dayOfMonth;
    private final OnItemListener onItemListener;
    public CalendarViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition(), (String) dayOfMonth.getText());
    }
}
