package com.alatheer.esteraha.DayHogozat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;

import com.alatheer.esteraha.Data.remote.Api;
import com.alatheer.esteraha.Data.remote.Models.AllReservationModel;
import com.alatheer.esteraha.Data.remote.Models.Hogzat;
import com.alatheer.esteraha.R;

import java.util.List;

public class HogozatDay extends AppCompatActivity {
    List<Hogzat> hogzatList;
    Hagz_Daily_Adapter hagz_daily_adapter;
    RecyclerView.LayoutManager manager;
    RecyclerView recycler_hogozat_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hogozat_day);
        recycler_hogozat_day = findViewById(R.id.recycler_hogozat_day);
        getDataOnline();
    }

    private void getDataOnline() {

    }
}