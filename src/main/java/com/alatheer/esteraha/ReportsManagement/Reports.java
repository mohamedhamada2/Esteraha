package com.alatheer.esteraha.ReportsManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alatheer.esteraha.Authentication.LoginActivity;
import com.alatheer.esteraha.ClientsManagements.AllClients;
import com.alatheer.esteraha.ClientsManagements.Clients_Home;
import com.alatheer.esteraha.Data.local.MySharedPreference;
import com.alatheer.esteraha.Data.remote.Api;
import com.alatheer.esteraha.Data.remote.Models.Erad;
import com.alatheer.esteraha.Data.remote.Models.Login;
import com.alatheer.esteraha.Data.remote.Models.Masrofat;
import com.alatheer.esteraha.Data.remote.Models.MasrofatReport;
import com.alatheer.esteraha.Data.remote.Models.Records;
import com.alatheer.esteraha.FeeManagement.FeeHome;
import com.alatheer.esteraha.MasrofatManagement.AddMasrof;
import com.alatheer.esteraha.MasrofatManagement.MasrofAdapter;
import com.alatheer.esteraha.R;
import com.alatheer.esteraha.Reservation_Management.Resevation_Management_Home;
import com.alatheer.esteraha.RestsManagement.RestsManagementHome;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Reports extends AppCompatActivity {
    ImageView img_from,img_to,menu;
    Calendar myCalendar;
    TextView txt_from_date,txt_to_date,txt_reservation_day;
    DatePickerDialog.OnDateSetListener datestart,dateend;
    String from_date,to_date,role_id_fk,publisher;
    List<Records>recordsList;
    List<Masrofat> masrofatList;
    EradatAdapter eradatAdapter;
    MasrofatAdapter masrofatAdapter ;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView eradat_recycler;
    Erad erad;
    ProgressBar progressBar;
    int flag;
    MySharedPreference mprefs;
    Login login ;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get__reports);
        img_from = findViewById(R.id.img_from_date);
        img_to = findViewById(R.id.img_to_date);
        txt_from_date = findViewById(R.id.txt_from_date);
        txt_to_date = findViewById(R.id.txt_to_date);
        progressBar = findViewById(R.id.progress);
        txt_reservation_day = findViewById(R.id.txt_reservation_day);
        myCalendar = Calendar.getInstance();
        recordsList = new ArrayList<>();
        masrofatList = new ArrayList<>();
        eradat_recycler = findViewById(R.id.recycler_eradat);
        mprefs = MySharedPreference.getInstance();
        login = mprefs.Get_UserData(this);
        publisher = login.getUserId();
        role_id_fk = login.getRoleIdFk();
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
        getDataIntent();
        datestart = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart();

            }

        };
        dateend = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart2();

            }

        };
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
                startActivity(new Intent(Reports.this, Resevation_Management_Home.class));
            }
        });
        LinearLayout client_lyt = (LinearLayout)headerLayout.findViewById(R.id.clients_lyt);
        client_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Reports.this, Clients_Home.class));
            }
        });
        LinearLayout main_lyt = (LinearLayout)headerLayout.findViewById(R.id.main_lyt);
        main_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Reports.this, RestsManagementHome.class));
            }
        });
        LinearLayout reports_lyt = (LinearLayout)headerLayout.findViewById(R.id.reports_management_lyt);
        reports_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Reports.this, ReportsManagements.class));
            }
        });
        LinearLayout expenses_lyt = (LinearLayout)headerLayout.findViewById(R.id.expenses_lyt);
        expenses_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Reports.this, AddMasrof.class));
            }
        });
        LinearLayout fee_lyt = (LinearLayout)headerLayout.findViewById(R.id.fees_lyt);
        fee_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Reports.this, FeeHome.class));
            }
        });
        LinearLayout logout_lyt = (LinearLayout)headerLayout.findViewById(R.id.logout_lyt);
        logout_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mprefs.ClearData(Reports.this);
                startActivity(new Intent(Reports.this, LoginActivity.class));
            }
        });
    }

    private void getDataIntent() {
        flag = getIntent().getIntExtra("flag",0);
        if (flag == 1){
            txt_reservation_day.setText(getString(R.string.earns_reports));
        }else if(flag == 2){
            txt_reservation_day.setText(getString(R.string.expenses_reports));
        }

    }

    private void updateLabelStart() {
        String myFormat = "yyyy-MM-dd";//In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        txt_from_date.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateLabelStart2() {
        String myFormat = "yyyy-MM-dd";//In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        txt_to_date.setText(sdf.format(myCalendar.getTime()));
    }


    public void GetFromDate(View view) {
        new DatePickerDialog(Reports.this, datestart, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void GetToDate(View view) {
        new DatePickerDialog(Reports.this, dateend, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void search(View view) {
        validation();
    }

    private void validation() {
        from_date = txt_from_date.getText().toString();
        to_date = txt_to_date.getText().toString();
        progressBar.setVisibility(View.VISIBLE);
        if(!from_date.equals(getResources().getString(R.string.from_date))&&
                !to_date.equals(getResources().getString(R.string.to_date))){
            if(flag == 1){
                GetEradat(from_date,to_date);

            }else if(flag == 2) {
                GetMasrofat(from_date,to_date);
                txt_reservation_day.setText(getString(R.string.expenses_reports));
            }
        }else {
            if(from_date.equals(getResources().getString(R.string.from_date))){
                txt_from_date.setError("ادخل التاريخ ");
            }else {
                txt_from_date.setError(null);
            }
            if(to_date.equals(getResources().getString(R.string.to_date))){
                txt_to_date.setError("ادخل التاريخ ");
            }else {
                txt_to_date.setError(null);
            }
        }
    }

    private void GetEradat(String from_date, String to_date) {
        Api.getService().get_all_eradat(from_date,to_date,role_id_fk,publisher).enqueue(new Callback<Erad>() {
            @Override
            public void onResponse(Call<Erad> call, Response<Erad> response) {
                if(response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    erad = response.body();
                    recordsList = erad.getRecords();
                    initrecycler();
                }
            }

            @Override
            public void onFailure(Call<Erad> call, Throwable t) {

            }
        });

    }
    private void GetMasrofat(String from_date, String to_date) {
        Api.getService().get_masrofat_report(from_date,to_date,role_id_fk,publisher).enqueue(new Callback<MasrofatReport>() {
            @Override
            public void onResponse(Call<MasrofatReport> call, Response<MasrofatReport> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess() == 1){
                        progressBar.setVisibility(View.GONE);
                        masrofatList = response.body().getMasrofat();
                        initrecycler2();
                    }
                }

            }

            @Override
            public void onFailure(Call<MasrofatReport> call, Throwable t) {

            }
        });

    }
    public void initrecycler(){
        eradatAdapter = new EradatAdapter(recordsList,this);
        layoutManager = new LinearLayoutManager(this);
        eradat_recycler.setHasFixedSize(true);
        eradat_recycler.setLayoutManager(layoutManager);
        eradat_recycler.setAdapter(eradatAdapter);
    }
    public void initrecycler2(){
        masrofatAdapter = new MasrofatAdapter(masrofatList,this);
        layoutManager = new LinearLayoutManager(this);
        eradat_recycler.setHasFixedSize(true);
        eradat_recycler.setLayoutManager(layoutManager);
        eradat_recycler.setAdapter(masrofatAdapter);
    }

    public void BACK(View view) {
        onBackPressed();
    }

    public void GetToDate1(View view) {
        new DatePickerDialog(Reports.this, dateend, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void GetFromDate1(View view) {
        new DatePickerDialog(Reports.this, datestart, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
