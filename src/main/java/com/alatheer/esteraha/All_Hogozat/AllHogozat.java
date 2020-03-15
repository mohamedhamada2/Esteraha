package com.alatheer.esteraha.All_Hogozat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
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

import com.alatheer.esteraha.Authentication.LoginActivity;
import com.alatheer.esteraha.ClientsManagements.Clients_Home;
import com.alatheer.esteraha.Data.local.MySharedPreference;
import com.alatheer.esteraha.Data.remote.Api;
import com.alatheer.esteraha.Data.remote.Models.AllReservationModel;
import com.alatheer.esteraha.Data.remote.Models.Hogzat;
import com.alatheer.esteraha.Data.remote.Models.Login;
import com.alatheer.esteraha.DayHogozat.Hagz_Daily_Adapter;
import com.alatheer.esteraha.DayHogozat.HogozatDay;
import com.alatheer.esteraha.FeeManagement.FeeHome;
import com.alatheer.esteraha.MasrofatManagement.AddMasrof;
import com.alatheer.esteraha.R;
import com.alatheer.esteraha.ReportsManagement.Reports;
import com.alatheer.esteraha.ReportsManagement.ReportsManagements;
import com.alatheer.esteraha.Reservation_Management.Resevation_Management_Home;
import com.alatheer.esteraha.RestsManagement.RestsManagementHome;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class AllHogozat extends AppCompatActivity {
    List<Hogzat> hogzatList;
    Hagz_Daily_Adapter hagz_daily_adapter;
    RecyclerView.LayoutManager manager;
    RecyclerView recycler_hogozat_day;
    MySharedPreference mprefs;
    Login login;
    ImageView menu;
    String role_id_fk,publisher;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_hogozat);
        recycler_hogozat_day = findViewById(R.id.recycler_all_hogozat);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        menu = findViewById(R.id.img_menu);
        handleSideMenu();
        mprefs = MySharedPreference.getInstance();
        login = mprefs.Get_UserData(this);
        role_id_fk = login.getRoleIdFk();
        publisher = login.getUserId();
        getDataIntent();
        getDataOnline();
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });
    }

    public void getDataIntent() {
        flag = getIntent().getIntExtra("flag", 0);
    }

    private void getDataOnline() {
        if (flag == 2) {
            Api.getService().get_all_hagz(role_id_fk,publisher).enqueue(new Callback<AllReservationModel>() {
                @Override
                public void onResponse(Call<AllReservationModel> call, Response<AllReservationModel> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getSucess() == 1) {
                            hogzatList = response.body().getHogzat();
                            hagz_daily_adapter = new Hagz_Daily_Adapter(hogzatList, AllHogozat.this);
                            manager = new LinearLayoutManager(AllHogozat.this);
                            recycler_hogozat_day.setAdapter(hagz_daily_adapter);
                            recycler_hogozat_day.setLayoutManager(manager);
                            recycler_hogozat_day.setHasFixedSize(true);
                        }
                    }
                }

                @Override
                public void onFailure(Call<AllReservationModel> call, Throwable t) {

                }
            });
        } else if (flag == 1) {
            Api.getService().get_hagz_day(role_id_fk,publisher).enqueue(new Callback<AllReservationModel>() {
                @Override
                public void onResponse(Call<AllReservationModel> call, Response<AllReservationModel> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getSucess() == 1) {
                            hogzatList = response.body().getHogzat();
                            hagz_daily_adapter = new Hagz_Daily_Adapter(hogzatList, AllHogozat.this);
                            manager = new LinearLayoutManager(AllHogozat.this);
                            recycler_hogozat_day.setAdapter(hagz_daily_adapter);
                            recycler_hogozat_day.setLayoutManager(manager);
                            recycler_hogozat_day.setHasFixedSize(true);
                        }
                    }
                }

                @Override
                public void onFailure(Call<AllReservationModel> call, Throwable t) {

                }
            });
        }
    }
    public void handleSideMenu() {
        View headerLayout = navigationView.getHeaderView(0);
        LinearLayout reservation_lyt = (LinearLayout)headerLayout.findViewById(R.id.reservation_lyt);
        reservation_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Resevation_Management_Home.this,"الصادر",Toast.LENGTH_LONG).show();
               // Intent intent = getIntent();
                //finish();
                //startActivity(intent);
                startActivity(new Intent(AllHogozat.this, Resevation_Management_Home.class));
            }
        });
        LinearLayout client_lyt = (LinearLayout)headerLayout.findViewById(R.id.clients_lyt);
        client_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllHogozat.this, Clients_Home.class));
            }
        });
        LinearLayout main_lyt = (LinearLayout)headerLayout.findViewById(R.id.main_lyt);
        main_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllHogozat.this, RestsManagementHome.class));
            }
        });
        LinearLayout reports_lyt = (LinearLayout)headerLayout.findViewById(R.id.reports_management_lyt);
        reports_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllHogozat.this, ReportsManagements.class));
            }
        });
        LinearLayout expenses_lyt = (LinearLayout)headerLayout.findViewById(R.id.expenses_lyt);
        expenses_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllHogozat.this, AddMasrof.class));
            }
        });
        LinearLayout fee_lyt = (LinearLayout)headerLayout.findViewById(R.id.fees_lyt);
        fee_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllHogozat.this, FeeHome.class));
            }
        });
        LinearLayout logout_lyt = (LinearLayout)headerLayout.findViewById(R.id.logout_lyt);
        logout_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mprefs.ClearData(AllHogozat.this);
                startActivity(new Intent(AllHogozat.this, LoginActivity.class));
            }
        });
    }

    public void BACK(View view) {
        onBackPressed();
    }
}
