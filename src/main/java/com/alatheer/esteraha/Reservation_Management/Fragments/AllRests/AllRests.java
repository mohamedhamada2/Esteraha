package com.alatheer.esteraha.Reservation_Management.Fragments.AllRests;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.alatheer.esteraha.Data.remote.Api;
import com.alatheer.esteraha.Data.remote.Models.RestModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.alatheer.esteraha.R;

import java.util.ArrayList;
import java.util.List;


public class AllRests extends Fragment {
     RecyclerView unbooked;
     RecyclerView.LayoutManager unbooked_manager;
     AllRestsAdapter allRests_adapter;
     List<RestModel> Rest_models_list;
     ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_un_booked, container, false);
        init_view(view);
        return view;
    }

    private void init_view(View view) {
        unbooked_manager = new LinearLayoutManager(getContext());
        unbooked = view.findViewById(R.id.unbooked_recycler);
        Rest_models_list = new ArrayList<>();
        progressBar = view.findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        get_online_rests();

    }
    private void get_online_rests() {
        Api.getService().get_Rests().enqueue(new Callback<List<RestModel>>() {
            @Override
            public void onResponse(Call<List<RestModel>> call, Response<List<RestModel>> response) {
                if(response.isSuccessful()){
                    Rest_models_list = response.body();
                    allRests_adapter = new AllRestsAdapter(getActivity(),Rest_models_list);
                    unbooked.setAdapter(allRests_adapter);
                    unbooked.setLayoutManager(unbooked_manager);
                    unbooked.setHasFixedSize(true);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<RestModel>> call, Throwable t) {

            }
        });
    }


}
