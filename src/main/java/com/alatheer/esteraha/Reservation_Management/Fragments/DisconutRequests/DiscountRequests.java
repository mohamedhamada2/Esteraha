package com.alatheer.esteraha.Reservation_Management.Fragments.DisconutRequests;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.alatheer.esteraha.All_Hogozat.AllHogozat;
import com.alatheer.esteraha.DayHogozat.HogozatDay;
import com.alatheer.esteraha.Orders.AcceptedOrders;
import com.alatheer.esteraha.Orders.Orders;
import com.alatheer.esteraha.Orders.RefusedOrders;
import com.alatheer.esteraha.R;


public class DiscountRequests extends Fragment {
    ImageButton all_orders,accepted_orders,refused_order;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_discount_requests, container, false);
        init_view(view);
        return view;
    }
    private void init_view(View view) {
        all_orders = view.findViewById(R.id.btn_all_order);
        accepted_orders = view.findViewById(R.id.btn_accepted_order);
        refused_order = view.findViewById(R.id.refused_order);
        all_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Orders.class));
            }
        });
        accepted_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AcceptedOrders.class));
            }
        });
        refused_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RefusedOrders.class));
            }
        });

    }


}
