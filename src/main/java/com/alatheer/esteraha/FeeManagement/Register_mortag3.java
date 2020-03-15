package com.alatheer.esteraha.FeeManagement;

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
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.esteraha.Authentication.LoginActivity;
import com.alatheer.esteraha.ClientsManagements.AllClients;
import com.alatheer.esteraha.ClientsManagements.Clients_Home;
import com.alatheer.esteraha.Data.local.MySharedPreference;
import com.alatheer.esteraha.Data.remote.Api;
import com.alatheer.esteraha.Data.remote.Models.AllRemains;
import com.alatheer.esteraha.Data.remote.Models.Esal;
import com.alatheer.esteraha.Data.remote.Models.EsalView;
import com.alatheer.esteraha.Data.remote.Models.Login;
import com.alatheer.esteraha.Data.remote.Models.Mortag3View;
import com.alatheer.esteraha.Data.remote.Models.RKM;
import com.alatheer.esteraha.Data.remote.Models.Success;
import com.alatheer.esteraha.Data.remote.Models.ViewEslat;
import com.alatheer.esteraha.Data.remote.Models.ViewMortaga3at;
import com.alatheer.esteraha.MasrofatManagement.AddMasrof;
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

public class Register_mortag3 extends AppCompatActivity {
    private ArrayAdapter<String> adapter_payment_type;
    private String m_payment_method = "", role_id_fk, publisher;
    Spinner spinner_payment;
    DatePickerDialog.OnDateSetListener date;
    String national_num;
    Calendar mycalender;
    private String[] payment_method_array;
    TextView txt_date;
    ImageView img_add;
    Esal esal;
    EditText et_national_num, et_total_value, et_paid_num, et_value_esal, et_remain_value, et_hagz_num;
    List<ViewEslat> esalatlist;
    List<ViewMortaga3at> mortaga3atList;
    android.app.AlertDialog dialog;
    View v;
    RecyclerView recyclerView, all_eslat;
    RecyclerView.LayoutManager layoutManager;
    EslatAdapter eslatAdapter;
    AllEslatAdapter allEslatAdapter;
    Mortag3atAdapter mortag3atAdapter;
    MySharedPreference mprefs;
    Login login;
    String id, value, remain, paid, esteraha_id_fk, date2, all_paid;
    int allrag;
    RelativeLayout relative_register_esal, relative_all_eslat;
    int flag, mortag3_num;
    ImageView menu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    String hagz_num,total,remain_value,esal_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_mortag3);
        spinner_payment = findViewById(R.id.spinner);
        txt_date = findViewById(R.id.txt_from_date);
        img_add = findViewById(R.id.img_add);
        et_national_num = findViewById(R.id.et_national_num);
        et_total_value = findViewById(R.id.et_total_value);
        all_eslat = findViewById(R.id.recycler_all_esals);
        et_paid_num = findViewById(R.id.et_paid_num);
        et_value_esal = findViewById(R.id.et_value_esal);
        et_remain_value = findViewById(R.id.et_remain_value);
        et_hagz_num = findViewById(R.id.et_hagz_num);
        relative_all_eslat = findViewById(R.id.relative_all_eslat);
        menu = findViewById(R.id.img_menu);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        handleSideMenu();
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });
        getDataIntent();
        get_mortag3_num();
        et_hagz_num.setText(id);
        et_total_value.setText(value);
        et_remain_value.setText(remain);
        et_paid_num.setText(paid);
        et_national_num.setText(national_num);
        et_value_esal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    if (!charSequence.equals("") && all_paid != null) {
                        int remain = Integer.parseInt(et_total_value.getText().toString()) -
                                (Integer.parseInt(paid) + (Integer.parseInt(all_paid) - allrag)) +
                                Integer.parseInt(charSequence.toString());
                        et_remain_value.setText(remain + "");
                        et_paid_num.setText((Integer.parseInt(paid) + (Integer.parseInt(all_paid) - allrag)) - Integer.parseInt(charSequence.toString()) + "");

                        // et_paid_num.setText(Integer.parseInt(all_paid)- Integer.parseInt(charSequence.toString()));
                    } else {
                        int remain = Integer.parseInt(et_total_value.getText().toString()) -
                                //(Integer.parseInt(paid))+
                                Integer.parseInt(charSequence.toString());
                        et_remain_value.setText(remain + "");
                        et_paid_num.setText(Integer.parseInt(all_paid) - Integer.parseInt(charSequence.toString()));
                    }
                } catch (Exception e) {
                    et_remain_value.setText(remain);
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        relative_register_esal = findViewById(R.id.relative_register_esal);
        esalatlist = new ArrayList<>();
        mprefs = MySharedPreference.getInstance();
        login = mprefs.Get_UserData(this);
        role_id_fk = login.getRoleIdFk();
        publisher = login.getUserId();

        dialog = new android.app.AlertDialog.Builder(this)
                .setCancelable(false)
                .create();
        mycalender = Calendar.getInstance();
        payment_method_array = getResources().getStringArray(R.array.payment_array);
        adapter_payment_type = new ArrayAdapter<>(this, R.layout.spinner_item2, payment_method_array);
        spinner_payment.setAdapter(adapter_payment_type);

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
        spinner_payment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    m_payment_method = "1";
                } else if (i == 1) {
                    m_payment_method = "2";


                } else if (i == 2) {
                    m_payment_method = "3";

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void handleSideMenu() {
        View headerLayout = navigationView.getHeaderView(0);
        LinearLayout reservation_lyt = (LinearLayout) headerLayout.findViewById(R.id.reservation_lyt);
        reservation_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Resevation_Management_Home.this,"الصادر",Toast.LENGTH_LONG).show();
                // Intent intent = getIntent();
                //finish();
                //startActivity(intent);
                startActivity(new Intent(Register_mortag3.this, Resevation_Management_Home.class));
            }
        });
        LinearLayout client_lyt = (LinearLayout) headerLayout.findViewById(R.id.clients_lyt);
        client_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register_mortag3.this, Clients_Home.class));
            }
        });
        LinearLayout main_lyt = (LinearLayout) headerLayout.findViewById(R.id.main_lyt);
        main_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register_mortag3.this, RestsManagementHome.class));
            }
        });
        LinearLayout reports_lyt = (LinearLayout) headerLayout.findViewById(R.id.reports_management_lyt);
        reports_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register_mortag3.this, ReportsManagements.class));
            }
        });
        LinearLayout expenses_lyt = (LinearLayout) headerLayout.findViewById(R.id.expenses_lyt);
        expenses_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register_mortag3.this, AddMasrof.class));
            }
        });
        LinearLayout fee_lyt = (LinearLayout) headerLayout.findViewById(R.id.fees_lyt);
        fee_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register_mortag3.this, FeeHome.class));
            }
        });
        LinearLayout logout_lyt = (LinearLayout) headerLayout.findViewById(R.id.logout_lyt);
        logout_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mprefs.ClearData(Register_mortag3.this);
                startActivity(new Intent(Register_mortag3.this, LoginActivity.class));
            }
        });
    }

    private void updateLabelStart() {
        String myFormat = "yyyy-MM-dd";//In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        txt_date.setText(sdf.format(mycalender.getTime()));
    }

    private void get_mortag3_num() {
        Api.getService().get_mortag3_num().enqueue(new Callback<RKM>() {
            @Override
            public void onResponse(Call<RKM> call, Response<RKM> response) {
                if (response.isSuccessful()) {
                    mortag3_num = response.body().getRkm();
                }
            }

            @Override
            public void onFailure(Call<RKM> call, Throwable t) {

            }
        });
    }

    public void getDataIntent() {
        flag = getIntent().getIntExtra("flag", 0);
        if (flag == 2) {
            id = getIntent().getStringExtra("id");
            //value = getIntent().getStringExtra("value");
            //remain = getIntent().getStringExtra("remain");
            //paid = getIntent().getStringExtra("paid");
            national_num = getIntent().getStringExtra("national_num");
            esteraha_id_fk = getIntent().getStringExtra("esteraha_id_fk");
            date2 = getIntent().getStringExtra("date");
            get_mortag3at();
        }
    }

    public void GetDate(View view) {
        new DatePickerDialog(Register_mortag3.this, date, mycalender
                .get(Calendar.YEAR), mycalender.get(Calendar.MONTH),
                mycalender.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void ADD(View view) {
        if (!TextUtils.isEmpty(et_national_num.getText().toString())) {
            Intent intent = new Intent(Register_mortag3.this, ReservationDetails.class);
            intent.putExtra("national_num", et_national_num.getText().toString());
            intent.putExtra("date", txt_date.getText().toString());
            intent.putExtra("flag", 2);
            startActivity(intent);
            finish();
        } else {
            et_national_num.setError("من فضلك ادخل رقم الهوية");
        }
    }

    public void save(View view) {
        Validation();
    }

    private void Validation() {
         date2 = txt_date.getText().toString();
         national_num = et_national_num.getText().toString();
         hagz_num = et_hagz_num.getText().toString();
         total = et_total_value.getText().toString();
         paid = et_paid_num.getText().toString();
         remain_value = et_remain_value.getText().toString();
         esal_value = et_value_esal.getText().toString();
        if (!date2.equals(getResources().getString(R.string.date)) && !TextUtils.isEmpty(national_num) && !TextUtils.isEmpty(hagz_num) &&
                !TextUtils.isEmpty(total) && !TextUtils.isEmpty(paid) && !TextUtils.isEmpty(remain_value) && !TextUtils.isEmpty(esal_value)) {
            AddMortag3(date2, national_num, hagz_num, total, paid, remain_value, esal_value, m_payment_method);
        }else {
            if(date2.equals(getResources().getString(R.string.date))){
                txt_date.setError("ادخل التاريخ من فضلك");
            }else{
                txt_date.setError(null);
            }
        }
    }

    private void AddMortag3(String date2, String national_num, String hagz_num, String total, String paid, String remain_value, String esal_value, String m_payment_method) {
        if (!paid.equals("0")) {
            Api.getService().add_mortag3(mortag3_num + "", hagz_num, national_num, esteraha_id_fk, total, m_payment_method
                    , paid, esal_value, remain_value, date2, publisher).enqueue(new Callback<Success>() {
                @Override
                public void onResponse(Call<Success> call, Response<Success> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getSuccess() == 1) {
                            finish();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Success> call, Throwable t) {

                }
            });
        } else {
            Toast.makeText(this, "لقد قمت باسترجاع المبلغ بالكامل", Toast.LENGTH_SHORT).show();
        }


    }

    public void get_mortag3at() {
        Api.getService().get_mortaga3at(id).enqueue(new Callback<AllRemains>() {
            @Override
            public void onResponse(Call<AllRemains> call, Response<AllRemains> response) {
                if (response.isSuccessful()) {
                    value = response.body().getRemains().getValue();
                    remain = response.body().getRemains().getRealremain() + "";
                    all_paid = response.body().getRemains().getAllpaid();
                    paid = response.body().getRemains().getPaid();
                    allrag = response.body().getRemains().getAllrag();
                    et_total_value.setText(value);
                    et_remain_value.setText(remain);
                    if (all_paid != null) {
                        et_paid_num.setText(Integer.parseInt(paid) + (Integer.parseInt(all_paid) - allrag) + "");
                    } else {
                        et_paid_num.setText(Integer.parseInt(paid) + "");
                    }

                }
            }

            @Override
            public void onFailure(Call<AllRemains> call, Throwable t) {

            }
        });
    }

    public void BACK(View view) {
        onBackPressed();
    }
    public void all_mortag3at(View view) {
        relative_register_esal.setVisibility(View.GONE);
        relative_all_eslat.setVisibility(View.VISIBLE);
        Api.getService().getMortag3at(role_id_fk, publisher).enqueue(new Callback<Mortag3View>() {
            @Override
            public void onResponse(Call<Mortag3View> call, Response<Mortag3View> response) {
                if (response.isSuccessful()) {

                    mortaga3atList = response.body().getViewMortaga3ats();
                    layoutManager = new LinearLayoutManager(Register_mortag3.this);
                    all_eslat.setLayoutManager(layoutManager);
                    mortag3atAdapter = new Mortag3atAdapter( Register_mortag3.this,mortaga3atList);
                    all_eslat.setAdapter(mortag3atAdapter);
                    all_eslat.setHasFixedSize(true);
                }
            }

            @Override
            public void onFailure(Call<Mortag3View> call, Throwable t) {

            }
        });
    }
}
