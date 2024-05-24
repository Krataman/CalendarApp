package com.android.calendarapp.calendarHandling;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.calendarapp.R;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public final TextView dayOfMonth;
    private final OnItemListener onItemListener;

    //region calendar view holder
    /**
     * ViewHolder for a calendar day cell in a RecyclerView.
     *
     * <p>This constructor initializes the ViewHolder for a calendar day cell, setting up the
     * TextView to display the day of the month and assigning the click listener.</p>
     *
     * @param itemView The view of the individual calendar day cell.
     * @param onItemListener The listener for item click events.
     */
    public CalendarViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener;

        itemView.setOnClickListener(this);
    }
    //endregion
    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition(), (String) dayOfMonth.getText());
    }
}
