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
    /**
     * Initializes the fragment's UI components and sets up the color picker functionality.
     *
     * <p>This method inflates the layout for the fragment, initializes the color picker view,
     * and sets up the return button to change its background color based on the selected color.
     * It also sets a click listener on the return button to pass the selected color back to the
     * listener and dismiss the dialog.</p>
     *
     * <p>It performs the following steps:
     * <ul>
     *     <li>Inflates the fragment's layout.</li>
     *     <li>Finds and initializes the color picker view and the return button.</li>
     *     <li>Sets a color listener on the color picker view to update the return button's background color
     *         when a color is selected.</li>
     *     <li>Sets a click listener on the return button to notify the listener of the selected color and
     *         dismiss the dialog.</li>
     * </ul>
     * </p>
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return The View for the fragment's UI, or null.
     */
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
    /**
     * Attaches the fragment to its context and ensures the context implements the required listener interface.
     *
     * <p>This method is called when the fragment is first attached to its context. It checks if the context
     * implements the {@link ColorPickerFragment.OnColorSelectedListener} interface and assigns it to the
     * `listener` field. If the context does not implement the required interface, it throws a
     * {@link RuntimeException}.</p>
     *
     * @param context The context to which the fragment is being attached.
     * @throws RuntimeException if the context does not implement {@link ColorPickerFragment.OnColorSelectedListener}.
     */
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