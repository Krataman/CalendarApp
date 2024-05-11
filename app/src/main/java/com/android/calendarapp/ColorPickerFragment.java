package com.android.calendarapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.calendarapp.eventsHandling.EventClockFragment;
import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;
import com.skydoves.colorpickerview.listeners.ColorListener;
import com.skydoves.colorpickerview.listeners.ColorPickerViewListener;

public class ColorPickerFragment extends DialogFragment {

    private ColorPickerView colorPickerView;
    private OnColorSelectedListener listener;

    public interface OnColorSelectedListener {
        void onColorSelected(int color);
    }

    public void setColorPickerListener(OnColorSelectedListener listener) {
        this.listener = listener;
    }
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EventClockFragment.OnTimeSelectedListener) {
            listener = (ColorPickerFragment.OnColorSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString() + "OnColorSelected not implemented!!!");
        }
    }
}