package com.alatheer.esteraha.RestsManagement;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    public interface TimePickerListener{
        void onTimeset(TimePicker timePicker, int i, int i1);
    }
    TimePickerListener timePickerListner;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            timePickerListner = (TimePickerListener) context;
        }catch (Exception e){
          throw  new ClassCastException(getActivity().toString()+"must implement TimePickerListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
       return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) this,hour,minute, DateFormat.is24HourFormat(getContext()));
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
      timePickerListner.onTimeset(timePicker,i,i1);
    }
}
