package com.alatheer.esteraha.RestsManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.alatheer.esteraha.Data.local.MySharedPreference;
import com.alatheer.esteraha.Data.remote.Api;
import com.alatheer.esteraha.Data.remote.Models.AddEstrahat;
import com.alatheer.esteraha.Data.remote.Models.Login;
import com.alatheer.esteraha.FeeManagement.ReservationDetails;
import com.alatheer.esteraha.R;
import com.alatheer.esteraha.Reservation_Management.Fragments.AllRests.AllRests;

import org.w3c.dom.Text;

public class AddRestActivity extends AppCompatActivity implements TimePickerFragment.TimePickerListener,TimePickerFragment2.TimePickerListener2 {
    EditText et_rest_rkm, et_rest_name, et_rest_address, et_rest_price, et_rest_details;
    Button time_in, time_out;
    String rest_rkm, rest_name, rest_address, rest_price, rest_details, m_hour_in, m_minute_in, m_minute_out, m_hour_out;
    String rkm,name,address,price,details,time_login,time_logout,id;
    MySharedPreference mprefs;
    ImageView img_back;
    Login user;
    String user_id;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rest);
        et_rest_name = findViewById(R.id.rest_name);
        et_rest_rkm = findViewById(R.id.rkm);
        et_rest_address = findViewById(R.id.rest_address);
        et_rest_details = findViewById(R.id.rest_details);
        et_rest_price = findViewById(R.id.rest_price);
        img_back = findViewById(R.id.img_back);
        mprefs = MySharedPreference.getInstance();
        user = mprefs.Get_UserData(this);
        user_id = user.getUserId();
        time_in = findViewById(R.id.time_in);
        time_out = findViewById(R.id.time_out);
        m_minute_in = "";
        m_hour_in = "";
        m_minute_out = "";
        m_hour_out = "";
        getDataIntent();
        if(flag != 1){
            SetData2();
        }
    }

    private void SetData2() {
        et_rest_rkm.setText(rkm);
        et_rest_name.setText(name);
        et_rest_price.setText(price);
        et_rest_details.setText(details);
        et_rest_address.setText(address);
        time_in.setText(time_login);
        time_out.setText(time_logout);
    }

    private void getDataIntent() {
        flag = getIntent().getIntExtra("flag",0);
        if(flag == 2){
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            rkm = getIntent().getStringExtra("rkm");
            address = getIntent().getStringExtra("adress");
            price = getIntent().getStringExtra("price");
            details = getIntent().getStringExtra("details");
            time_login = getIntent().getStringExtra("time_login");
            time_logout = getIntent().getStringExtra("time_logout");
            img_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   onBackPressed();
                }
            });
        }
    }

    public void InTime(View view) {
        DialogFragment dialogFragment = new TimePickerFragment();
        dialogFragment.setCancelable(false);
        dialogFragment.show(getSupportFragmentManager(), "time picker");
    }

    public void OutTime(View view) {
        DialogFragment dialogFragment2 = new TimePickerFragment2();
        dialogFragment2.setCancelable(false);
        dialogFragment2.show(getSupportFragmentManager(), "time picker2");
    }

    @Override
    public void onTimeset(TimePicker timePicker, int hour, int minute) {
        m_hour_in = hour + "";
        m_minute_in = minute + "";
        time_in.setText(m_hour_in+":"+m_minute_in);
    }

    @Override
    public void onTimeset2(TimePicker timePicker, int hour, int minute) {
        m_hour_out = hour + "";
        m_minute_out = minute + "";
        time_out.setText(m_hour_out+":"+m_minute_out);
    }

    public void AddRest(View view) {
        validation();
    }

    private void validation() {
        rest_name = et_rest_name.getText().toString();
        rest_rkm = et_rest_rkm.getText().toString();
        rest_address = et_rest_address.getText().toString();
        rest_details = et_rest_details.getText().toString();
        rest_price = et_rest_price.getText().toString();
        Log.v("hour_in", m_hour_in);
        Log.v("minute_in", m_minute_in);
        Log.v("hour_out", m_hour_out);
        Log.v("minute_out", m_minute_out);
        if (!TextUtils.isEmpty(rest_name) && !TextUtils.isEmpty(rest_rkm) && !TextUtils.isEmpty(rest_address)
                && !TextUtils.isEmpty(rest_details) && !TextUtils.isEmpty(rest_price) && !TextUtils.isEmpty(m_hour_in)
                && !TextUtils.isEmpty(m_minute_in) && !TextUtils.isEmpty(m_hour_out) && !TextUtils.isEmpty(m_minute_out)) {
            et_rest_rkm.setError(null);
            et_rest_name.setError(null);
            et_rest_address.setError(null);
            et_rest_price.setError(null);
            et_rest_details.setError(null);
            if(flag == 1){
                AddRest(rest_rkm,rest_name,rest_address,rest_price,rest_details,m_hour_in,m_minute_in,m_hour_out,m_minute_out);
            }else {
                UpdateRest(id,rest_rkm,rest_name,rest_address,rest_price,rest_details,m_hour_in,m_minute_in,m_hour_out,m_minute_out);
            }
           
            /*if(flag == 1){
                AddClient(client_id,client_name,m_res,m_nat,client_phone);
            }else {
                UpdateClient(client_id,client_name,m_res,m_nat,client_phone);
            }*/


        } else {
            if (TextUtils.isEmpty(rest_rkm)) {
                et_rest_rkm.setError("ادخل رقم الاستراحة");
            } else {
                et_rest_rkm.setError(null);
            }
            if (TextUtils.isEmpty(rest_name)) {
                et_rest_name.setError("ادخل اسم الاستراحة");
            } else {
                et_rest_name.setError(null);
            }
            if (TextUtils.isEmpty(rest_address)) {
                et_rest_address.setError("ادخل عنوان الاستراحة");
            } else {
                et_rest_address.setError(null);
            }
            if (TextUtils.isEmpty(rest_price)) {
                et_rest_price.setError("ادخل سعر الاستراحة");
            } else {
                et_rest_price.setError(null);
            }
            if (TextUtils.isEmpty(rest_details)) {
                et_rest_details.setError("ادخل عنوان الاستراحة");
            } else {
                et_rest_details.setError(null);
            }
            if (TextUtils.isEmpty(m_hour_in) && TextUtils.isEmpty(m_minute_in)) {
                Toast.makeText(this, "برجاء ادخال وقت الدخول", Toast.LENGTH_SHORT).show();
            } else {

            }
            if (TextUtils.isEmpty(m_hour_out) && TextUtils.isEmpty(m_minute_out)) {
                Toast.makeText(this, "برجاء ادخال وقت الخروج", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void UpdateRest(String id, String rest_rkm, String rest_name, String rest_address, String rest_price, String rest_details, String m_hour_in, String m_minute_in, String m_hour_out, String m_minute_out) {
        Api.getService().updaterest(id,rest_name,rest_address,rest_price,m_hour_in+":"+m_minute_in,m_hour_out+":"+m_minute_out,rest_details,"0","0",user_id,rest_rkm).enqueue(new Callback<AddEstrahat>() {
            @Override
            public void onResponse(Call<AddEstrahat> call, Response<AddEstrahat> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()==1){
                        Toast.makeText(AddRestActivity.this, "تم تعديل الاستراحة بنجاح", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddRestActivity.this, AllRestsActivity.class));
                    }
                }
            }

            @Override
            public void onFailure(Call<AddEstrahat> call, Throwable t) {

            }
        });
    }


    private void AddRest(String rest_rkm, String rest_name, String rest_address, String rest_price, String rest_details, String m_hour_in, String m_minute_in, String m_hour_out, String m_minute_out) {
        Api.getService().addrest(rest_name,rest_address,rest_price,m_hour_in+":"+m_minute_in,m_hour_out+":"+m_minute_out,rest_details,"0","0",user_id,rest_rkm).enqueue(new Callback<AddEstrahat>() {
            @Override
            public void onResponse(Call<AddEstrahat> call, Response<AddEstrahat> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()==1){
                        Toast.makeText(AddRestActivity.this, "تم اضافة الاستراحة بنجاح", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddRestActivity.this, AllRestsActivity.class));
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