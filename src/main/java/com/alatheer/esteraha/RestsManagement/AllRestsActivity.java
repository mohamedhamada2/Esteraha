package com.alatheer.esteraha.RestsManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alatheer.esteraha.Authentication.LoginActivity;
import com.alatheer.esteraha.ClientsManagements.AllClients;
import com.alatheer.esteraha.ClientsManagements.AllClientsAdapter;
import com.alatheer.esteraha.ClientsManagements.Clients_Home;
import com.alatheer.esteraha.Data.local.MySharedPreference;
import com.alatheer.esteraha.Data.remote.Api;
import com.alatheer.esteraha.Data.remote.Models.AddEstrahat;
import com.alatheer.esteraha.Data.remote.Models.RestModel;
import com.alatheer.esteraha.FeeManagement.FeeHome;
import com.alatheer.esteraha.MasrofatManagement.AddMasrof;
import com.alatheer.esteraha.R;
import com.alatheer.esteraha.ReportsManagement.Reports;
import com.alatheer.esteraha.ReportsManagement.ReportsManagements;
import com.alatheer.esteraha.Reservation_Management.Fragments.AllRests.AllRests;
import com.alatheer.esteraha.Reservation_Management.Fragments.AllRests.AllRestsAdapter;
import com.alatheer.esteraha.Reservation_Management.Resevation_Management_Home;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class AllRestsActivity extends AppCompatActivity {
    RecyclerView all_rests;
    RecyclerView.LayoutManager allrests_manager;
    AllRestsAdapter2 allRests_adapter2;
    List<RestModel> Rest_models_list;
    ProgressBar progressBar;
    ImageView menu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MySharedPreference mprefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_rests);
        allrests_manager = new LinearLayoutManager(AllRestsActivity.this);
        mprefs = MySharedPreference.getInstance();
        all_rests =findViewById(R.id.allrests_recycler);
        Rest_models_list = new ArrayList<>();
        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        menu = findViewById(R.id.img_menu);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });
        handleSideMenu();
        get_online_rests();
    }

    private void handleSideMenu() {
        View headerLayout = navigationView.getHeaderView(0);
        LinearLayout reservation_lyt = (LinearLayout)headerLayout.findViewById(R.id.reservation_lyt);
        reservation_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Resevation_Management_Home.this,"الصادر",Toast.LENGTH_LONG).show();
                // Intent intent = getIntent();
                //finish();
                //startActivity(intent);
                startActivity(new Intent(AllRestsActivity.this, Resevation_Management_Home.class));
            }
        });
        LinearLayout client_lyt = (LinearLayout)headerLayout.findViewById(R.id.clients_lyt);
        client_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllRestsActivity.this, Clients_Home.class));
            }
        });
        LinearLayout main_lyt = (LinearLayout)headerLayout.findViewById(R.id.main_lyt);
        main_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllRestsActivity.this, RestsManagementHome.class));
            }
        });
        LinearLayout reports_lyt = (LinearLayout)headerLayout.findViewById(R.id.reports_management_lyt);
        reports_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllRestsActivity.this, ReportsManagements.class));
            }
        });
        LinearLayout expenses_lyt = (LinearLayout)headerLayout.findViewById(R.id.expenses_lyt);
        expenses_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllRestsActivity.this, AddMasrof.class));
            }
        });
        LinearLayout fee_lyt = (LinearLayout)headerLayout.findViewById(R.id.fees_lyt);
        fee_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllRestsActivity.this, FeeHome.class));
            }
        });
        LinearLayout logout_lyt = (LinearLayout)headerLayout.findViewById(R.id.logout_lyt);
        logout_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mprefs.ClearData(AllRestsActivity.this);
                startActivity(new Intent(AllRestsActivity.this, LoginActivity.class));
            }
        });
    }

    private void initrecyclerview() {
        allRests_adapter2 = new AllRestsAdapter2(this,Rest_models_list);
        all_rests.setAdapter(allRests_adapter2);
        allrests_manager = new LinearLayoutManager(this);
        all_rests.setLayoutManager(allrests_manager);
        all_rests.setHasFixedSize(true);
    }

    private void get_online_rests() {
        Api.getService().get_Rests().enqueue(new Callback<List<RestModel>>() {
            @Override
            public void onResponse(Call<List<RestModel>> call, Response<List<RestModel>> response) {
                if(response.isSuccessful()){
                    Rest_models_list = response.body();
                    initrecyclerview();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<RestModel>> call, Throwable t) {

            }
        });
    }

    public void update_rest(String id, String rkm, String name, String adress, String price, String timeLogin, String timeLogout, String details) {
        Intent intent = new Intent(AllRestsActivity.this,AddRestActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("rkm",rkm);
        intent.putExtra("name",name);
        intent.putExtra("adress",adress);
        intent.putExtra("price",price);
        intent.putExtra("details",details);
        intent.putExtra("time_login",timeLogin);
        intent.putExtra("time_logout",timeLogout);
        intent.putExtra("flag",2);
        startActivity(intent);
    }

    public void delete_rest(String id) {
        progressBar.setVisibility(View.VISIBLE);
    Api.getService().deleterest(id).enqueue(new Callback<AddEstrahat>() {
        @Override
        public void onResponse(Call<AddEstrahat> call, Response<AddEstrahat> response) {
            if(response.isSuccessful()){
                if(response.body().getSuccess()==1){
                    get_online_rests();
                    Toast.makeText(AllRestsActivity.this, "تم ازالة الاستراحة بنجاح", Toast.LENGTH_SHORT).show();
                }
            }

        }

        @Override
        public void onFailure(Call<AddEstrahat> call, Throwable t) {

        }
    });
    }

    public void BACK(View view) {
        onBackPressed();
    }
}


