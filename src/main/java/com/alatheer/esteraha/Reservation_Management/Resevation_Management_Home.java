package com.alatheer.esteraha.Reservation_Management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.esteraha.Authentication.LoginActivity;
import com.alatheer.esteraha.ClientsManagements.AllClients;
import com.alatheer.esteraha.ClientsManagements.Clients_Home;
import com.alatheer.esteraha.ClientsManagements.Registeration;
import com.alatheer.esteraha.Book_Rest.BookRest;
import com.alatheer.esteraha.Data.local.MySharedPreference;
import com.alatheer.esteraha.FeeManagement.FeeHome;
import com.alatheer.esteraha.MasrofatManagement.AddMasrof;
import com.alatheer.esteraha.ReportsManagement.ReportsManagements;
import com.alatheer.esteraha.Reservation_Management.Fragments.AllReservations.AllReservation;
import com.alatheer.esteraha.Reservation_Management.Fragments.AllRests.AllRests;
import com.alatheer.esteraha.Reservation_Management.Fragments.DisconutRequests.DiscountRequests;
import com.alatheer.esteraha.R;
import com.alatheer.esteraha.RestsManagement.RestsManagementHome;
import com.google.android.material.navigation.NavigationView;

public class Resevation_Management_Home extends AppCompatActivity  {
    Fragment selected_fragment;
    Button btn_allrests,btn_allreservation,btn_discount_request;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView rests;
    ImageView menu;
    MySharedPreference mprefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn_allrests = findViewById(R.id.btn_all_rests);
        btn_allreservation = findViewById(R.id.btn_all_reservation);
        btn_discount_request = findViewById(R.id.btn_discount_requests);
        toolbar = findViewById(R.id.toolbar);
        rests = findViewById(R.id.txt_rests);
        menu = findViewById(R.id.img_menu);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        mprefs = MySharedPreference.getInstance();
        handleSideMenu();
        selected_fragment = new AllRests();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selected_fragment).commit();
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });
    }

    public void all_rests(View view) {
        selected_fragment = new AllRests();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selected_fragment).commit();
        btn_allrests.setBackgroundResource(R.color.colorPrimary);
        btn_allrests.setTextColor(getResources().getColor(R.color.background));
        btn_allreservation.setBackgroundResource(R.color.background);
        btn_allreservation.setTextColor(getResources().getColor(R.color.colorPrimary));
        btn_discount_request.setBackgroundResource(R.color.background);
        btn_discount_request.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    public void all_reservation(View view) {
        selected_fragment = new AllReservation();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selected_fragment).commit();
        btn_allreservation.setBackgroundResource(R.color.colorPrimary);
        btn_allreservation.setTextColor(getResources().getColor(R.color.background));
        btn_allrests.setBackgroundResource(R.color.background);
        btn_allrests.setTextColor(getResources().getColor(R.color.colorPrimary));
        btn_discount_request.setBackgroundResource(R.color.background);
        btn_discount_request.setTextColor(getResources().getColor(R.color.colorPrimary));
    }
    public void discount_order(View view) {
        selected_fragment = new DiscountRequests();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selected_fragment).commit();
        btn_discount_request.setBackgroundResource(R.color.colorPrimary);
        btn_discount_request.setTextColor(getResources().getColor(R.color.background));
        btn_allrests.setBackgroundResource(R.color.background);
        btn_allrests.setTextColor(getResources().getColor(R.color.colorPrimary));
        btn_allreservation.setBackgroundResource(R.color.background);
        btn_allreservation.setTextColor(getResources().getColor(R.color.colorPrimary));
    }
    public void go_to_book_rest_activity(String id, String price, String name, String rkm, String address) {
        Intent i = new Intent(Resevation_Management_Home.this, BookRest.class);
        i.putExtra("id",id);
        i.putExtra("price",price);
        i.putExtra("rest_name",name);
        i.putExtra("rest_num",rkm);
        i.putExtra("rest_address",address);
        i.putExtra("flag",1);
        startActivity(i);
    }
    public void handleSideMenu() {
        View headerLayout = navigationView.getHeaderView(0);
        LinearLayout reservation_lyt = (LinearLayout)headerLayout.findViewById(R.id.reservation_lyt);
        reservation_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Resevation_Management_Home.this,"الصادر",Toast.LENGTH_LONG).show();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        LinearLayout client_lyt = (LinearLayout)headerLayout.findViewById(R.id.clients_lyt);
        client_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Resevation_Management_Home.this, Clients_Home.class));
            }
        });
        LinearLayout main_lyt = (LinearLayout)headerLayout.findViewById(R.id.main_lyt);
        main_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Resevation_Management_Home.this, RestsManagementHome.class));
            }
        });
        LinearLayout reports_lyt = (LinearLayout)headerLayout.findViewById(R.id.reports_management_lyt);
        reports_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Resevation_Management_Home.this, ReportsManagements.class));
            }
        });
        LinearLayout expenses_lyt = (LinearLayout)headerLayout.findViewById(R.id.expenses_lyt);
        expenses_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Resevation_Management_Home.this, AddMasrof.class));
            }
        });
        LinearLayout fee_lyt = (LinearLayout)headerLayout.findViewById(R.id.fees_lyt);
        fee_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Resevation_Management_Home.this, FeeHome.class));
            }
        });
        LinearLayout logout_lyt = (LinearLayout)headerLayout.findViewById(R.id.logout_lyt);
        logout_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mprefs.ClearData(Resevation_Management_Home.this);
                startActivity(new Intent(Resevation_Management_Home.this, LoginActivity.class));
            }
        });

    }

    public void BACK(View view) {
        onBackPressed();
    }
}
