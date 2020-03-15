package com.alatheer.esteraha.Reservation_Management.Fragments.AllReservations;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.alatheer.esteraha.TemporaryHogozat.TemporaryHogozat;
import com.alatheer.esteraha.R;
import com.alatheer.esteraha.All_Hogozat.AllHogozat;
import com.alatheer.esteraha.DayHogozat.HogozatDay;


public class AllReservation extends Fragment {
    ImageButton hagz_day,all_hagz,temporary_hagz;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booked, container, false);
        init_view(view);
        return view;
    }

    private void init_view(View view) {
        hagz_day = view.findViewById(R.id.btn_hagz_day);
        all_hagz = view.findViewById(R.id.btn_all_hagz);
        temporary_hagz = view.findViewById(R.id.btn_temporary_order);
        hagz_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),AllHogozat.class);
                i.putExtra("flag",1);
                startActivity(i);
            }
        });
        all_hagz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),AllHogozat.class);
                i.putExtra("flag",2);
                startActivity(i);
            }
        });
        temporary_hagz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TemporaryHogozat.class));
            }
        });

    }
}
