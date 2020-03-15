package com.alatheer.esteraha.Book_Rest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.esteraha.All_Hogozat.AllHogozat;
import com.alatheer.esteraha.Authentication.LoginActivity;
import com.alatheer.esteraha.ClientsManagements.Clients_Home;
import com.alatheer.esteraha.Common.Common;
import com.alatheer.esteraha.Data.local.MySharedPreference;
import com.alatheer.esteraha.Data.remote.Api;
import com.alatheer.esteraha.Data.remote.Models.AddHagz;
import com.alatheer.esteraha.Data.remote.Models.CheckClient;
import com.alatheer.esteraha.Data.remote.Models.CheckDate;
import com.alatheer.esteraha.Data.remote.Models.Login;
import com.alatheer.esteraha.Data.remote.Models.Success;
import com.alatheer.esteraha.FeeManagement.FeeHome;
import com.alatheer.esteraha.MasrofatManagement.AddMasrof;
import com.alatheer.esteraha.Orders.RefusedOrders;
import com.alatheer.esteraha.R;
import com.alatheer.esteraha.ReportsManagement.Reports;
import com.alatheer.esteraha.ReportsManagement.ReportsManagements;
import com.alatheer.esteraha.Reservation_Management.Resevation_Management_Home;
import com.alatheer.esteraha.RestsManagement.RestsManagementHome;
import com.google.android.material.navigation.NavigationView;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookRest extends AppCompatActivity {
    Button txt_start_date,txt_end_date,book;
    Calendar myCalendar;
    TextView txt_rest_num,txt_rest_name,txt_rest_address;
    EditText et_national_num,et_client_name,et_num_of_days,et_total_value,et_paid,et_remain,et_discount_value,et_phone,et_tamin;
    String  national_num,client_name,num_of_days,total_value,paid,remain,discount_value,start_date,enddate,phone,publisher,tamen;
    Spinner discount,payment,reservation;
    ImageView back;
    MySharedPreference mprefs;
    Login login;
    private ArrayAdapter<String> adapter_discount,adapter_reservation_type,adapter_payment_type;
    private String [] discount_array,payment_method_array,reservation_type_array;
    String m_discount,m_payment_method,m_reservation_type;
    DatePickerDialog.OnDateSetListener datestart;
    String id,price,rest_name,rest_num,rest_address,reserved_id;
    int flag;
    int price_int;
    ImageView menu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RelativeLayout discount_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_rest);
        getdataIntent();
        et_national_num = findViewById(R.id.client_national_num);
        et_client_name = findViewById(R.id.client_name);
        et_num_of_days = findViewById(R.id.num_days);
        et_phone = findViewById(R.id.client_phone);
        et_total_value = findViewById(R.id.total_value);
        et_paid = findViewById(R.id.paid_value);
        et_remain = findViewById(R.id.remain_value);
        discount_layout = findViewById(R.id.relative_dicount_value);
        et_discount_value = findViewById(R.id.client_discount_Value);
        discount = findViewById(R.id.client_discount);
        payment = findViewById(R.id.spinner_payment);
        reservation = findViewById(R.id.client_reserve_type);
        txt_start_date = findViewById(R.id.start_date);
        txt_end_date = findViewById(R.id.end_date);
        et_tamin = findViewById(R.id.tamen_Value);
        myCalendar = Calendar.getInstance();
        mprefs = MySharedPreference.getInstance();
        book = findViewById(R.id.btn_book);
        txt_rest_num = findViewById(R.id.rest_num);
        txt_rest_name = findViewById(R.id.rest_name);
        txt_rest_address = findViewById(R.id.rest_address);
        txt_rest_name.setText(rest_name);
        txt_rest_num.setText(rest_num);
        txt_rest_address.setText(rest_address);
        et_num_of_days.setText(num_of_days);
        et_client_name.setText(client_name);
        et_national_num.setText(national_num);
        et_tamin.setText(tamen);
        txt_start_date.setText(start_date);
        txt_end_date.setText(enddate);
        et_total_value.setText(price);
        et_remain.setText(remain);
        et_phone.setText(phone);
        et_paid.setText(paid);
        et_discount_value.setText("0");
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
        login = mprefs.Get_UserData(this);
        et_paid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    if(discount_layout.getVisibility() == View.VISIBLE){
                        et_remain.setText(Integer.parseInt(et_total_value.getText().toString()) - Integer.parseInt(et_paid.getText().toString()) - Integer.parseInt(et_discount_value.getText().toString())+"");
                    }else {
                        et_remain.setText(Integer.parseInt(et_total_value.getText().toString()) - Integer.parseInt(et_paid.getText().toString())+"");
                    }

                }catch (Exception e){
                    if(et_paid.getText().toString().equals("")&&et_discount_value.getText().toString().equals("")) {
                        et_remain.setText(Integer.parseInt(et_total_value.getText().toString()) + "");
                    }else if(et_paid.getText().toString().equals("")&&!et_discount_value.getText().toString().equals("")){
                        et_remain.setText(Integer.parseInt(et_total_value.getText().toString()) - Integer.parseInt(et_discount_value.getText().toString())+"");
                    }else if(!et_paid.getText().toString().equals("")&&et_discount_value.getText().toString().equals("")){
                        et_remain.setText(Integer.parseInt(et_total_value.getText().toString()) - Integer.parseInt(et_paid.getText().toString())+"");
                    }else if(!et_paid.getText().toString().equals("")&&!et_discount_value.getText().toString().equals("")){
                        et_remain.setText(Integer.parseInt(et_total_value.getText().toString())- Integer.parseInt(et_paid.getText().toString()) - Integer.parseInt(et_discount_value.getText().toString())+"");
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et_discount_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    if(discount_layout.getVisibility() == View.VISIBLE){
                        if(et_paid.isEnabled() == true){
                            et_remain.setText(Integer.parseInt(et_total_value.getText().toString()) - Integer.parseInt(et_paid.getText().toString()) - Integer.parseInt(et_discount_value.getText().toString())+"");
                        }else {
                            et_remain.setText(Integer.parseInt(et_total_value.getText().toString()) - Integer.parseInt(et_discount_value.getText().toString())+"");
                        }

                    }else {
                        et_remain.setText(Integer.parseInt(et_total_value.getText().toString()) - Integer.parseInt(et_paid.getText().toString())+"");
                    }

                }catch (Exception e){
                    if(et_paid.getText().toString().equals("")&&et_discount_value.getText().toString().equals("")) {
                        et_remain.setText(Integer.parseInt(et_total_value.getText().toString()) + "");
                    }else if(et_paid.getText().toString().equals("")&&!et_discount_value.getText().toString().equals("")){
                        et_remain.setText(Integer.parseInt(et_total_value.getText().toString()) - Integer.parseInt(et_discount_value.getText().toString())+"");
                    }else if(!et_paid.getText().toString().equals("")&&et_discount_value.getText().toString().equals("")){
                    et_remain.setText(Integer.parseInt(et_total_value.getText().toString()) - Integer.parseInt(et_paid.getText().toString())+"");
                    }else if(!et_paid.getText().toString().equals("")&&!et_discount_value.getText().toString().equals("")){
                        et_remain.setText(Integer.parseInt(et_total_value.getText().toString())- Integer.parseInt(et_paid.getText().toString()) - Integer.parseInt(et_discount_value.getText().toString())+"");
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et_num_of_days.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(et_num_of_days.getText().toString().equals("")){
                    total_value = "";
                    et_total_value.setText(total_value);
                }else {
                    int  total_price = price_int*Integer.parseInt(et_num_of_days.getText().toString());
                    total_value = total_price+"";
                    et_total_value.setText(total_value);
                    et_remain.setText(total_value);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        discount_array = getResources().getStringArray(R.array.discount_array);
        payment_method_array = getResources().getStringArray(R.array.payment_array);
        reservation_type_array = getResources().getStringArray(R.array.reservation_array);
        adapter_discount = new ArrayAdapter<>(this,R.layout.spinner_item,discount_array);
        discount.setAdapter(adapter_discount);
        discount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0)
                {
                    m_discount="2";
                    //et_discount_value.setText("");
                    discount_layout.setVisibility(View.GONE);
                    et_discount_value.setText("0");
                }else if (i == 1)
                {
                    m_discount = "1";
                    //et_discount_value.setEnabled(true);
                    discount_layout.setVisibility(View.VISIBLE);
                    //et_remain.setText(Integer.parseInt(et_total_value.getText().toString())- Integer.parseInt(et_paid.getText().toString()+"")) ;
                }/*else if (i == 2)
                {
                    m_discount = "2";

                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        adapter_reservation_type = new ArrayAdapter<>(this,R.layout.spinner_item,reservation_type_array);
        reservation.setAdapter(adapter_reservation_type);
        reservation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0)
                {
                    m_reservation_type= "1";
                    et_paid.setEnabled(true);
                    et_remain.setEnabled(true);
                }else if (i == 1)
                {
                    m_reservation_type = "2";
                    et_paid.setEnabled(false);
                    et_remain.setEnabled(false);
                    et_paid.setText("0");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        adapter_payment_type = new ArrayAdapter<>(this,R.layout.spinner_item,payment_method_array);
        payment.setAdapter(adapter_payment_type);
        payment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0)
                {
                    m_payment_method= "1";
                }else if (i == 1)
                {
                    m_payment_method = "2";


                }else if (i == 2)
                {
                    m_payment_method = "3";

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //search editor
        ///////////////////
        et_national_num.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    return true;
                }
                return false;
            }
        });
        ///////////////////
        //get start date
        ///////////////////
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
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == 1){
                    Validation();
                }else if(flag == 2){
                    Validation2();

                }

            }
        });
        if(flag == 2){
            if(m_reservation_type.equals("1")){
                reservation.setSelection(0);
            }else if(m_reservation_type.equals("2")) {
                reservation.setSelection(1);
            }
            if(m_payment_method.equals("1")){
                payment.setSelection(0);
            }else if(m_payment_method.equals("2")){
                payment.setSelection(1);
            }else if(m_payment_method.equals("3")){
                payment.setSelection(2);
            }
            if(m_discount.equals("2")){
                discount.setSelection(0);
                discount_layout.setVisibility(View.GONE);
            }else if(m_discount.equals("1")){
                discount.setSelection(1);
                discount_layout.setVisibility(View.VISIBLE);
                et_discount_value.setText(discount_value);
            }
        }

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
                startActivity(new Intent(BookRest.this, Resevation_Management_Home.class));
            }
        });
        LinearLayout client_lyt = (LinearLayout)headerLayout.findViewById(R.id.clients_lyt);
        client_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookRest.this, Clients_Home.class));
            }
        });
        LinearLayout main_lyt = (LinearLayout)headerLayout.findViewById(R.id.main_lyt);
        main_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookRest.this, RestsManagementHome.class));
            }
        });
        LinearLayout reports_lyt = (LinearLayout)headerLayout.findViewById(R.id.reports_management_lyt);
        reports_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookRest.this, ReportsManagements.class));
            }
        });
        LinearLayout expenses_lyt = (LinearLayout)headerLayout.findViewById(R.id.expenses_lyt);
        expenses_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookRest.this, AddMasrof.class));
            }
        });
        LinearLayout fee_lyt = (LinearLayout)headerLayout.findViewById(R.id.fees_lyt);
        fee_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookRest.this, FeeHome.class));
            }
        });
        LinearLayout logout_lyt = (LinearLayout)headerLayout.findViewById(R.id.logout_lyt);
        logout_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mprefs.ClearData(BookRest.this);
                startActivity(new Intent(BookRest.this, LoginActivity.class));
            }
        });
    }

    private void Validation2() {
        national_num = et_national_num.getText().toString();
        client_name = et_client_name.getText().toString();
        num_of_days = et_num_of_days.getText().toString();
        start_date = txt_start_date.getText().toString();
        enddate = txt_end_date.getText().toString();
        total_value = et_total_value.getText().toString();
        phone = et_phone.getText().toString();
        paid = et_paid.getText().toString();
        remain = et_remain.getText().toString();
        tamen = et_tamin.getText().toString();
        discount_value = et_discount_value.getText().toString();
        publisher = login.getUserId();

            //Add_Hagz(national_num,client_name,num_of_days,start_date,enddate,phone,total_value,paid,remain,discount_value,tamen);
            Update_Hagz(national_num,client_name,num_of_days,start_date,enddate,phone,total_value,paid,remain,discount_value,tamen);


        }


    private void performSearch() {
        Api.getService().check_user(et_national_num.getText().toString()).enqueue(new Callback<CheckClient>() {
            @Override
            public void onResponse(Call<CheckClient> call, Response<CheckClient> response) {
                if(response.isSuccessful()){
                    if(response.body().getSucess()==1){
                        et_client_name.setText(response.body().getName());
                        et_phone.setText(response.body().getMob());
                    }else if(response.body().getSucess()==0){
                        et_client_name.setText("");
                        et_phone.setText("");
                    }
                }
            }

            @Override
            public void onFailure(Call<CheckClient> call, Throwable t) {

            }
        });
    }

    private void Validation() {
        national_num = et_national_num.getText().toString();
        client_name = et_client_name.getText().toString();
        num_of_days = et_num_of_days.getText().toString();
        start_date = txt_start_date.getText().toString();
        enddate = txt_end_date.getText().toString();
        phone = et_phone.getText().toString();
        if(et_paid.isEnabled() == true){
            paid = et_paid.getText().toString();
        }else {
            paid = "0";
        }
        if(et_paid.getText().toString().equals("")) {
            paid = "0";
        }
        total_value = et_total_value.getText().toString();
        remain = et_remain.getText().toString();
        tamen = et_tamin.getText().toString();
        discount_value = et_discount_value.getText().toString();
        publisher = login.getUserId();
        if(!TextUtils.isEmpty(national_num)&& !TextUtils.isEmpty(client_name)&&
        !TextUtils.isEmpty(num_of_days)&&!TextUtils.isEmpty(start_date)&&!TextUtils.isEmpty(enddate)
        &&!TextUtils.isEmpty(phone)&& !TextUtils.isEmpty(total_value)&&
        !TextUtils.isEmpty(remain) && !TextUtils.isEmpty(tamen)){
                Add_Hagz(national_num,client_name,num_of_days,start_date,enddate,phone,total_value,paid,remain,discount_value,tamen);
                //Update_Hagz(national_num,client_name,num_of_days,start_date,enddate,phone,total_value,paid,remain,discount_value,tamen);


        }else{
            if(TextUtils.isEmpty(national_num)){
                et_national_num.setError("ادخل رقم الهوية");
            }else {
                et_national_num.setError(null);
            }if(TextUtils.isEmpty(client_name)){
                et_client_name.setError("ادخل اسم العميل");
            }else {
                et_client_name.setError(null);
            }if(TextUtils.isEmpty(start_date)){
            txt_start_date.setError("ادخل بداية الحجز");
            }else {
            txt_start_date.setError(null);
            }if(TextUtils.isEmpty(enddate)){
                txt_end_date.setError("ادخل نهاية الحجز");
            }else {
                txt_end_date.setError(null);
            }if(TextUtils.isEmpty(num_of_days)){
                et_num_of_days.setError("ادخل عدد ايام الحجز");
            }else {
                et_num_of_days.setError(null);
            }if(TextUtils.isEmpty(tamen)){
                et_tamin.setError("ادخل مبلغ التامين");
            }else {
                et_tamin.setError(null);
            }if(TextUtils.isEmpty(phone)){
                et_phone.setError("ادخل رقم الهاتف");
            }else {
                et_phone.setError(null);
            }
        }
    }

    private void Update_Hagz(String national_num, String client_name, String num_of_days, String start_date, String enddate, String phone, String total_value, String paid, String remain, String discount_value, String tamen) {
        Log.v("kkkkk","kkk");
        Api.getService().update_hagz(reserved_id,national_num,id,num_of_days,m_discount,total_value,discount_value,paid,remain,m_payment_method,m_reservation_type,tamen,start_date,enddate,client_name,phone,publisher).enqueue(new Callback<AddHagz>() {
            @Override
            public void onResponse(Call<AddHagz> call, Response<AddHagz> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()== 1){
                        Log.v("kkkk","kkkk");
                        Toast.makeText(BookRest.this, "تم تعديل الحجز بنجاح", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                }
            }

            @Override
            public void onFailure(Call<AddHagz> call, Throwable t) {

            }
        });
    }

    private void updateLabelStart() {
        String myFormat = "yyyy-MM-dd";//In which you need put here
        String myFormat2= "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        SimpleDateFormat sdf2 = new SimpleDateFormat(myFormat2, Locale.US);
        txt_start_date.setText(sdf.format(myCalendar.getTime()));
        String from =sdf.format(myCalendar.getTime());
        if(!TextUtils.isEmpty(et_num_of_days.getText().toString())){
            myCalendar.add(myCalendar.DATE,Integer.parseInt(et_num_of_days.getText().toString())-1);
            txt_end_date.setText(sdf2.format(myCalendar.getTime()));
            String to = sdf2.format(myCalendar.getTime());
            Log.v("from_date",sdf.format(myCalendar.getTime()));
            Log.v("to_date",sdf2.format(myCalendar.getTime()));
            Log.v("id",id);
            Api.getService().CheckDate(et_num_of_days.getText().toString(),id,national_num,from).enqueue(new Callback<CheckDate>() {
                @Override
                public void onResponse(Call<CheckDate> call, Response<CheckDate> response) {
                    if(response.isSuccessful()){
                        if(response.body().getCount()>0){
                            CreateAlertDialog2();
                            txt_start_date.setText("");
                            txt_end_date.setText("");
                        }
                    }
                }

                @Override
                public void onFailure(Call<CheckDate> call, Throwable t) {

                }
            });
        }else {
            et_num_of_days.setError("ادخل عدد الايام اولا ");
        }
    }

    private void CreateAlertDialog2() {
        View v = LayoutInflater.from(this).inflate(R.layout.check_date, null);
        final android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this)
                .setCancelable(false)
                .create();
        Button btn = v.findViewById(R.id.btn_ok);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setView(v);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        dialog.show();
    }

    public void GetDateStart(View view) {
        if (!TextUtils.isEmpty(et_num_of_days.getText().toString())) {
            new DatePickerDialog(BookRest.this, datestart, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }else {
            et_num_of_days.setError("ادخل عدد الايام اولا ");
        }
    }
    ///////////////////////////////
    private void getdataIntent() {
        flag = getIntent().getIntExtra("flag",0);
        if(flag == 1){
            id = getIntent().getStringExtra("id");
            price = getIntent().getStringExtra("price");
            rest_name = getIntent().getStringExtra("rest_name");
            rest_num = getIntent().getStringExtra("rest_num");
            rest_address = getIntent().getStringExtra("rest_address");
            price_int = Integer.parseInt(price);
        }else if(flag == 2){
            id = getIntent().getStringExtra("id");
            price = getIntent().getStringExtra("price");
            rest_name = getIntent().getStringExtra("rest_name");
            rest_num = getIntent().getStringExtra("rest_num");
            rest_address = getIntent().getStringExtra("rest_address");
            price_int = Integer.parseInt(price);
            m_reservation_type = getIntent().getStringExtra("reserve_type");
            m_payment_method = getIntent().getStringExtra("pay_method");
            m_discount = getIntent().getStringExtra("khasm");
            discount_value = getIntent().getStringExtra("discount");
            start_date = getIntent().getStringExtra("from");
            enddate = getIntent().getStringExtra("to");
            client_name = getIntent().getStringExtra("client_name");
            national_num = getIntent().getStringExtra("national_num");
            num_of_days = getIntent().getStringExtra("num_days");
            paid = getIntent().getStringExtra("paid");
            tamen = getIntent().getStringExtra("tamen");
            remain = getIntent().getStringExtra("remain");
            reserved_id = getIntent().getStringExtra("reserved_id");
            phone = getIntent().getStringExtra("mob");
        }
    }
    private void Add_Hagz(String national_num,String client_name, String num_of_days, String start_date, String end_date, String phone,
                          String total_value, String paid, String remain, String discount_value, String tamen) {

        Api.getService().add_hagz(national_num,id,num_of_days,start_date,end_date,total_value,paid,remain,discount_value,m_discount,client_name,phone,publisher,m_reservation_type,m_payment_method,tamen).enqueue(new Callback<AddHagz>() {
            @Override
            public void onResponse(Call<AddHagz> call, Response<AddHagz> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()== 1){
                        Log.v("end_date",enddate);
                        Toast.makeText(BookRest.this, "تم اضافة الحجز بنجاح", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                }
            }

            @Override
            public void onFailure(Call<AddHagz> call, Throwable t) {

            }
        });
    }
   public void CreateAlertDialog(){
       View v = LayoutInflater.from(BookRest.this).inflate(R.layout.book_rest_dialog, null);
       final android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(BookRest.this)
               .setCancelable(false)
               .create();
       Button btn_print = v.findViewById(R.id.btn_print_now);
       Button btn_cancel = v.findViewById(R.id.btn_cancel);
       btn_print.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Dexter.withActivity(BookRest.this).withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                       .withListener(new PermissionListener() {
                           @Override
                           public void onPermissionGranted(PermissionGrantedResponse response) {
                               create_pdf_file(Common.getAppPath(BookRest.this)+"test_pdf.pdf");
                           }

                           @Override
                           public void onPermissionDenied(PermissionDeniedResponse response) {

                           }

                           @Override
                           public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                           }
                       });
           }
       });
    }
    public void create_pdf_file(String path){
      if(new File(path).exists())
          new File(path).delete();
      try {
          Document document = new Document();
          PdfWriter.getInstance(document,new FileOutputStream(path));
          //open to write
          document.open();


          //setting
          document.setPageSize(PageSize.A4);
          document.addCreationDate();
          document.addAuthor("alatheer tech");
          document.addCreator("mohamed hamada");

      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (DocumentException e) {
          e.printStackTrace();
      }
    }

    public void BACK(View view) {
        finish();
    }
}

