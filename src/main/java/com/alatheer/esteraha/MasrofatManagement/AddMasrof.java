package com.alatheer.esteraha.MasrofatManagement;

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
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.esteraha.Authentication.LoginActivity;
import com.alatheer.esteraha.Book_Rest.BookRest;
import com.alatheer.esteraha.ClientsManagements.AllClients;
import com.alatheer.esteraha.ClientsManagements.Clients_Home;
import com.alatheer.esteraha.ClientsManagements.Registeration;
import com.alatheer.esteraha.Data.local.MySharedPreference;
import com.alatheer.esteraha.Data.remote.Api;
import com.alatheer.esteraha.Data.remote.Models.Feat;
import com.alatheer.esteraha.Data.remote.Models.Login;
import com.alatheer.esteraha.Data.remote.Models.Masrof;
import com.alatheer.esteraha.Data.remote.Models.Nationality;
import com.alatheer.esteraha.Data.remote.Models.Success;
import com.alatheer.esteraha.FeeManagement.FeeHome;
import com.alatheer.esteraha.R;
import com.alatheer.esteraha.ReportsManagement.Reports;
import com.alatheer.esteraha.ReportsManagement.ReportsManagements;
import com.alatheer.esteraha.Reservation_Management.Resevation_Management_Home;
import com.alatheer.esteraha.RestsManagement.RestsManagementHome;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddMasrof extends AppCompatActivity {
    private ArrayAdapter<String> adapter_fea;
    private String m_fea="",m_date,publisher,role_id_fk;
    private List<Feat> featModelList;
    private List<String> featList;
    ImageView img_date,menu;
    TextView txt_date;
    EditText edt_value;
    Spinner spinner_fea;
    DatePickerDialog.OnDateSetListener date;
    String value;
    Calendar mycalender;
    RecyclerView masrofat;
    RecyclerView.LayoutManager layoutManager;
    MasrofAdapter masrofAdapter;
    List<Masrof>masrofList;
    MySharedPreference mprefs;
    Login login;
    //ProgressBar progressBar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_masrof);
        img_date = findViewById(R.id.img_from_date);
        edt_value = findViewById(R.id.edt_value);
        spinner_fea = findViewById(R.id.spinner);
        txt_date = findViewById(R.id.txt_from_date);
        masrofat = findViewById(R.id.masrof_recycler);
        //progressBar = findViewById(R.id.progress);
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
        featList = new ArrayList<>();
        masrofList = new ArrayList<>();
        mycalender = Calendar.getInstance();
        mprefs = MySharedPreference.getInstance();
        login = mprefs.Get_UserData(this);
        publisher = login.getUserId();
        role_id_fk = login.getRoleIdFk();
        getallMasrofat();
        getfea();
        spinner_fea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0)
                {
                    m_fea="";
                }else
                {
                    int pos = position-1;
                    m_fea = featModelList.get(pos).getId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                mycalender.set(Calendar.YEAR, year);
                mycalender.set(Calendar.MONTH, monthOfYear);
                mycalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart();

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
                startActivity(new Intent(AddMasrof.this, Resevation_Management_Home.class));
            }
        });
        LinearLayout client_lyt = (LinearLayout)headerLayout.findViewById(R.id.clients_lyt);
        client_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddMasrof.this, Clients_Home.class));
            }
        });
        LinearLayout main_lyt = (LinearLayout)headerLayout.findViewById(R.id.main_lyt);
        main_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddMasrof.this, RestsManagementHome.class));
            }
        });
        LinearLayout reports_lyt = (LinearLayout)headerLayout.findViewById(R.id.reports_management_lyt);
        reports_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddMasrof.this, ReportsManagements.class));
            }
        });
        LinearLayout expenses_lyt = (LinearLayout)headerLayout.findViewById(R.id.expenses_lyt);
        expenses_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddMasrof.this, AddMasrof.class));
            }
        });
        LinearLayout fee_lyt = (LinearLayout)headerLayout.findViewById(R.id.fees_lyt);
        fee_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddMasrof.this, FeeHome.class));
            }
        });
        LinearLayout logout_lyt = (LinearLayout)headerLayout.findViewById(R.id.logout_lyt);
        logout_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mprefs.ClearData(AddMasrof.this);
                startActivity(new Intent(AddMasrof.this, LoginActivity.class));
            }
        });
    }

    private void getallMasrofat() {
        Api.getService().get_masrofat(role_id_fk,publisher).enqueue(new Callback<List<Masrof>>() {
            @Override
            public void onResponse(Call<List<Masrof>> call, Response<List<Masrof>> response) {
                if(response.isSuccessful()){
                   masrofList = response.body();
                   //progressBar.setVisibility(View.INVISIBLE);
                   initrecycler();

                }
            }

            @Override
            public void onFailure(Call<List<Masrof>> call, Throwable t) {

            }
        });
    }

    private void initrecycler() {
        masrofAdapter = new MasrofAdapter(masrofList,this);
        masrofat.setAdapter(masrofAdapter);
        layoutManager = new LinearLayoutManager(this);
        masrofat.setLayoutManager(layoutManager);
        masrofat.setHasFixedSize(true);
    }

    private void updateLabelStart() {
        String myFormat = "yyyy-MM-dd";//In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        txt_date.setText(sdf.format(mycalender.getTime()));
    }

    private void getfea() {
        Api.getService().get_feat().enqueue(new Callback<List<Feat>>() {
            @Override
            public void onResponse(Call<List<Feat>> call, Response<List<Feat>> response) {
                if (response.isSuccessful())
                {
                    if (response.body().size()>0)
                    {
                        Log.e("sdfsdfsdfs",response.body().size()+"");
                        Log.e("ee",response.body().get(0).getTitle());
                        featModelList = response.body();
                        featList.clear();
                        featList.add("الفئة");
                        for (Feat featModel:featModelList)
                        {

                            featList.add(featModel.getTitle());
                        }
                        adapter_fea = new ArrayAdapter<String>(AddMasrof.this,R.layout.spinner_item2,featList);
                        spinner_fea.setAdapter(adapter_fea);

                    }else
                    {
                        Log.e("sdfsdfsdfs",response.body().size()+"");

                    }
                }else
                {
                    Log.e("sdfsdfsdfs","fsdfsdddddddddddddddd");

                }
            }

            @Override
            public void onFailure(Call<List<Feat>> call, Throwable t) {

            }
        });
    }

    public void GetDate(View view) {
        new DatePickerDialog(AddMasrof.this, date, mycalender
                .get(Calendar.YEAR), mycalender.get(Calendar.MONTH),
                mycalender.get(Calendar.DAY_OF_MONTH)).show();
    }
    public void txtGetDate(View view) {
        new DatePickerDialog(AddMasrof.this, date, mycalender
                .get(Calendar.YEAR), mycalender.get(Calendar.MONTH),
                mycalender.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void AddMasrof(View view) {
        validation();
    }

    private void validation() {
        m_date = txt_date.getText().toString();
        value = edt_value.getText().toString();
        if(!TextUtils.isEmpty(value)&& !m_fea.equals("الفئة")&&!m_date.equals(getResources().getString(R.string.date))){
            Add_Masrof(value,m_date,m_fea);
        }else {
            if(m_date.equals(getResources().getString(R.string.date))){
                txt_date.setError("ادخل التاريخ من فضلك");
            }else{
                txt_date.setError(null);
            }
        }
    }

    private void Add_Masrof(String value, String m_date, String m_fea) {
        Api.getService().add_masrof(value,m_date,m_fea,publisher).enqueue(new Callback<Success>() {
            @Override
            public void onResponse(Call<Success> call, Response<Success> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()==1){
                        Toast.makeText(AddMasrof.this, "تم اضافة المصروف بنجاح", Toast.LENGTH_SHORT).show();
                        getallMasrofat();
                    }
                }
            }

            @Override
            public void onFailure(Call<Success> call, Throwable t) {

            }
        });
    }

    public void BACK(View view) {
        onBackPressed();
    }
}
