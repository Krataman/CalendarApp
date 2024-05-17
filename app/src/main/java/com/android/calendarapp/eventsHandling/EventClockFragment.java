package com.android.calendarapp.eventsHandling;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import com.android.calendarapp.R;


public class EventClockFragment extends DialogFragment{

    private TimePicker timePicker;
    private OnTimeSelectedListener listener;

    public interface OnTimeSelectedListener {
        void onTimeSelected(int hourOfDay, int minute);
    }
    //region onCreateView nebo-li fragment_event_clock
    /**
     * Metoda ktera po kliknuti tlacitka hodin vytvori fragment ve kterem jsou hodiny a tlacitko pro zavreni fragmentu.
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_clock, container, false);

        timePicker = view.findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        Button returnButton = view.findViewById(R.id.clockFragmentGoBackButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Získání zvoleného času
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                if (listener != null) {
                    listener.onTimeSelected(hour, minute);
                }

                dismiss(); // Zavření fragmentu
            }
        });

        return view;
    }
    //endregion
    //region onAttach
    /**
     * Zavolan kdyz se fragment pripoji ke kontextu nejake aktivity, napr. MainActivity
     *
     * @param context The context to which the fragment is being attached.
     *
     * @throws RuntimeException if the context does not implement the required listener interface.
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTimeSelectedListener) {
            listener = (OnTimeSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString() + "OnTimeSelectedListener not implemented!!!");
        }
    }
    //endregion
}