package com.alatheer.esteraha.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alatheer.esteraha.Authentication.LoginActivity;
import com.alatheer.esteraha.ClientsManagements.AllClients;
import com.alatheer.esteraha.ClientsManagements.Clients_Home;
import com.alatheer.esteraha.Data.local.MySharedPreference;
import com.alatheer.esteraha.FeeManagement.FeeHome;
import com.alatheer.esteraha.MasrofatManagement.AddMasrof;
import com.alatheer.esteraha.Orders.AcceptedOrders;
import com.alatheer.esteraha.R;
import com.alatheer.esteraha.ReportsManagement.Reports;
import com.alatheer.esteraha.ReportsManagement.ReportsManagements;
import com.alatheer.esteraha.Reservation_Management.Resevation_Management_Home;
import com.alatheer.esteraha.RestsManagement.RestsManagementHome;
import com.google.android.material.navigation.NavigationView;

public class HOME extends AppCompatActivity {
    Button btn_reservation_management,btn_clients_management,btn_rests_management,btn_reports_management,
    btn_masrofat_management,btn_fee_management;
    ImageView menu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MySharedPreference mprefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home3);
        btn_reservation_management = findViewById(R.id.reservation_management);
        btn_clients_management = findViewById(R.id.client_management);
        btn_reports_management = findViewById(R.id.reports_management);
        btn_rests_management = findViewById(R.id.salaries_management);
        btn_masrofat_management = findViewById(R.id.masrofat_managent);
        btn_fee_management = findViewById(R.id.fee_managent);
        mprefs = MySharedPreference.getInstance();
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
        btn_reservation_management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HOME.this, Resevation_Management_Home.class));
            }
        });
        btn_clients_management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HOME.this, Clients_Home.class));
            }
        });
        btn_rests_management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HOME.this, RestsManagementHome.class));
            }
        });
        btn_reports_management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HOME.this, ReportsManagements.class));
            }
        });
        btn_masrofat_management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HOME.this, AddMasrof.class));
            }
        });
        btn_fee_management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HOME.this, FeeHome.class));
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
                startActivity(new Intent(HOME.this, Resevation_Management_Home.class));
            }
        });
        LinearLayout client_lyt = (LinearLayout)headerLayout.findViewById(R.id.clients_lyt);
        client_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HOME.this, Clients_Home.class));
            }
        });
        LinearLayout main_lyt = (LinearLayout)headerLayout.findViewById(R.id.main_lyt);
        main_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HOME.this, RestsManagementHome.class));
            }
        });
        LinearLayout reports_lyt = (LinearLayout)headerLayout.findViewById(R.id.reports_management_lyt);
        reports_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HOME.this, Reports.class));
            }
        });
        LinearLayout expenses_lyt = (LinearLayout)headerLayout.findViewById(R.id.expenses_lyt);
        expenses_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HOME.this, AddMasrof.class));
            }
        });
        LinearLayout fee_lyt = (LinearLayout)headerLayout.findViewById(R.id.fees_lyt);
        fee_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HOME.this, FeeHome.class));
            }
        });
        LinearLayout logout_lyt = (LinearLayout)headerLayout.findViewById(R.id.logout_lyt);
        logout_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mprefs.ClearData(HOME.this);
                startActivity(new Intent(HOME.this, LoginActivity.class));
            }
        });
    }
}
