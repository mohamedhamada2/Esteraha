package com.alatheer.esteraha.Orders;

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
import android.widget.Toast;

import com.alatheer.esteraha.Authentication.LoginActivity;
import com.alatheer.esteraha.ClientsManagements.AllClients;
import com.alatheer.esteraha.ClientsManagements.Clients_Home;
import com.alatheer.esteraha.Data.local.MySharedPreference;
import com.alatheer.esteraha.Data.remote.Api;
import com.alatheer.esteraha.Data.remote.Models.OrdersModel;
import com.alatheer.esteraha.Data.remote.Models.Record;
import com.alatheer.esteraha.FeeManagement.FeeHome;
import com.alatheer.esteraha.MasrofatManagement.AddMasrof;
import com.alatheer.esteraha.R;
import com.alatheer.esteraha.ReportsManagement.Reports;
import com.alatheer.esteraha.ReportsManagement.ReportsManagements;
import com.alatheer.esteraha.Reservation_Management.Resevation_Management_Home;
import com.alatheer.esteraha.RestsManagement.RestsManagementHome;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class RefusedOrders extends AppCompatActivity {
    List<Record> recordList;
    Orders_Adapter orders_adapter;
    RecyclerView.LayoutManager manager;
    RecyclerView recyclerView_order;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menu;
    MySharedPreference mprefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refused_orders);
        recyclerView_order = findViewById(R.id.recycler_refused_order);
        menu = findViewById(R.id.img_menu);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        mprefs = MySharedPreference.getInstance();
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });
        handleSideMenu();
        Api.getService().get_all_orders("2").enqueue(new Callback<OrdersModel>() {
            @Override
            public void onResponse(Call<OrdersModel> call, Response<OrdersModel> response) {
                if(response.isSuccessful()){
                    if(response.body().getSucess()==1){
                        recordList = response.body().getRecords();
                        orders_adapter = new Orders_Adapter(recordList,RefusedOrders.this);
                        recyclerView_order.setAdapter(orders_adapter);
                        recyclerView_order.setHasFixedSize(true);
                        manager = new LinearLayoutManager(RefusedOrders.this);
                        recyclerView_order.setLayoutManager(manager);
                        Toast.makeText(RefusedOrders.this, "llll", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<OrdersModel> call, Throwable t) {

            }
        });
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
                startActivity(new Intent(RefusedOrders.this, Resevation_Management_Home.class));
            }
        });
        LinearLayout client_lyt = (LinearLayout)headerLayout.findViewById(R.id.clients_lyt);
        client_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RefusedOrders.this, Clients_Home.class));
            }
        });
        LinearLayout main_lyt = (LinearLayout)headerLayout.findViewById(R.id.main_lyt);
        main_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RefusedOrders.this, RestsManagementHome.class));
            }
        });
        LinearLayout reports_lyt = (LinearLayout)headerLayout.findViewById(R.id.reports_management_lyt);
        reports_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RefusedOrders.this, ReportsManagements.class));
            }
        });
        LinearLayout expenses_lyt = (LinearLayout)headerLayout.findViewById(R.id.expenses_lyt);
        expenses_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RefusedOrders.this, AddMasrof.class));
            }
        });
        LinearLayout fee_lyt = (LinearLayout)headerLayout.findViewById(R.id.fees_lyt);
        fee_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RefusedOrders.this, FeeHome.class));
            }
        });
        LinearLayout logout_lyt = (LinearLayout)headerLayout.findViewById(R.id.logout_lyt);
        logout_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mprefs.ClearData(RefusedOrders.this);
                startActivity(new Intent(RefusedOrders.this, LoginActivity.class));
            }
        });
    }

    public void BACK(View view) {
        onBackPressed();
    }
}
