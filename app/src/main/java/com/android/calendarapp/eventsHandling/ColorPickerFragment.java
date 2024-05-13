package com.android.calendarapp.eventsHandling;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.calendarapp.R;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorListener;

public class ColorPickerFragment extends DialogFragment {

    private ColorPickerView colorPickerView;
    private OnColorSelectedListener listener;

    //region Color interface
    public interface OnColorSelectedListener {
        void onColorSelected(int color);
    }
    //endregion
    //region setListener
    public void setColorPickerListener(OnColorSelectedListener listener) {
        this.listener = listener;
    }
    //endregion
    //region onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_color_picker, container, false);

        colorPickerView = view.findViewById(R.id.colorPickerView);

        Button returnButton = view.findViewById(R.id.colorPickerGoBackButton);
        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(int color, boolean fromUser) {
                returnButton.setBackgroundColor(color);
            }
        });
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int a = colorPickerView.getColor();
                    listener.onColorSelected(colorPickerView.getColor());

                }
                dismiss();
            }
        });
        return view;
    }
    //endregion
    //region onAttach
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EventClockFragment.OnTimeSelectedListener) {
            listener = (ColorPickerFragment.OnColorSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString() + "OnColorSelected not implemented!!!");
        }
    }
    //endregion
}