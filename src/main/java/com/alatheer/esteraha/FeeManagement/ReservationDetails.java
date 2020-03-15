package com.alatheer.esteraha.FeeManagement;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alatheer.esteraha.Data.remote.Api;
import com.alatheer.esteraha.Data.remote.Models.Esal;
import com.alatheer.esteraha.Data.remote.Models.EslatData;
import com.alatheer.esteraha.R;

import java.util.List;

public class ReservationDetails extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    EslatAdapter eslatAdapter;
    List<EslatData> esalatlist;
    String national_num,date;
    Esal esal;
    int flag;
    String id,paid,remain,value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_details);
        recyclerView = findViewById(R.id.esalat_recycler);
        getDataIntent();

    }
    public void getDataIntent(){
        national_num = getIntent().getStringExtra("national_num");
        date = getIntent().getStringExtra("date");
        flag = getIntent().getIntExtra("flag",0);
        if(flag == 1){
            getOnlineData();
        }else if(flag == 2){
            getOnlineData2();
        }
    }

    private void getOnlineData2() {
        Api.getService().getMortag3ofUser(national_num).enqueue(new Callback<Esal>() {
            @Override
            public void onResponse(Call<Esal> call, Response<Esal> response) {
                if(response.isSuccessful()){
                    esal = response.body();
                    esalatlist = esal.getEslatData();
                    initRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<Esal> call, Throwable t) {

            }
        });
    }

    private void getOnlineData() {
        Api.getService().getEslatofUser(national_num).enqueue(new Callback<Esal>() {
            @Override
            public void onResponse(Call<Esal> call, Response<Esal> response) {
                if(response.isSuccessful()){
                    esal = response.body();
                    esalatlist = esal.getEslatData();
                    initRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<Esal> call, Throwable t) {

            }
        });
    }
    public void initRecyclerView(){
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        eslatAdapter = new EslatAdapter(this,esalatlist);
        recyclerView.setAdapter(eslatAdapter);
        recyclerView.setHasFixedSize(true);
    }



    public void send(String id, String value, String paid, String remain,String national_num,String esteraha_id_fk) {
        if(flag == 1){
            Intent i = new Intent(ReservationDetails.this,Register_esal.class);
            i.putExtra("id",id);
            i.putExtra("value",value);
            i.putExtra("paid",paid);
            i.putExtra("remain",remain);
            i.putExtra("national_num",national_num);
            i.putExtra("esteraha_id_fk",esteraha_id_fk);
            i.putExtra("flag",2);
            i.putExtra("date",date);
            startActivity(i);
            finish();
        }else if(flag == 2) {
            Intent i = new Intent(ReservationDetails.this,Register_mortag3.class);
            i.putExtra("id",id);
            i.putExtra("value",value);
            i.putExtra("paid",paid);
            i.putExtra("remain",remain);
            i.putExtra("national_num",national_num);
            i.putExtra("esteraha_id_fk",esteraha_id_fk);
            i.putExtra("flag",2);
            i.putExtra("date",date);
            startActivity(i);
            finish();
        }


    }

    public void BACK(View view) {
        onBackPressed();
    }
}
