package com.alatheer.esteraha.ClientsManagements;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.alatheer.esteraha.Data.local.MySharedPreference;
import com.alatheer.esteraha.Data.remote.Api;
import com.alatheer.esteraha.Data.remote.Models.AddClient;
import com.alatheer.esteraha.Data.remote.Models.Login;
import com.alatheer.esteraha.Data.remote.Models.Nationality;
import com.alatheer.esteraha.Home.HOME;
import com.alatheer.esteraha.R;

import java.util.ArrayList;
import java.util.List;

public class Registeration extends AppCompatActivity {
    private Spinner nat,res;
    EditText et_client_name,et_client_id,et_client_phone;
    String client_name,client_id,client_phone;
    private ArrayAdapter<String> adapter_nat,adapter_reserved;
    private String m_nat="",m_res="";
    private List<Nationality> nationalityModelList;
    private List<String> natonalityList;
    private String [] reserved_array;
    MySharedPreference mprefs;
    int flag;
    String id,name,phone,national_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        mprefs =MySharedPreference.getInstance();
        nat = findViewById(R.id.spinner_nationality);
        res = findViewById(R.id.spinner_reserved);
        et_client_name = findViewById(R.id.client_name);
        et_client_id = findViewById(R.id.client_id);
        et_client_phone = findViewById(R.id.client_phone);
        getDataIntent();
        if(flag != 1){
            SetData();
        }

        natonalityList = new ArrayList<>();
        reserved_array = getResources().getStringArray(R.array.reserved_array);
        adapter_reserved = new ArrayAdapter<>(this,R.layout.spinner_item,reserved_array);
        res.setAdapter(adapter_reserved);
        getNationality();
        nat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0)
                {
                    m_nat="";
                }else
                {
                    int pos = position-1;
                    m_nat = nationalityModelList.get(pos).getId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        res.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0)
                {
                    m_res="1";
                }else if (i == 1)
                {
                    m_res = "2";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getNationality() {
        Api.getService().getNationality().enqueue(new Callback<List<Nationality>>() {
            @Override
            public void onResponse(Call<List<Nationality>> call, Response<List<Nationality>> response) {
                if (response.isSuccessful())
                {
                    if (response.body().size()>0)
                    {
                        Log.e("sdfsdfsdfs",response.body().size()+"");
                        Log.e("ee",response.body().get(0).getTitle());
                        nationalityModelList = response.body();
                        natonalityList.clear();
                        natonalityList.add("الجنسية");
                        for (Nationality nationalityModel:nationalityModelList)
                        {

                            natonalityList.add(nationalityModel.getTitle());
                        }
                        adapter_nat = new ArrayAdapter<String>(Registeration.this,R.layout.spinner_item,natonalityList);
                        nat.setAdapter(adapter_nat);

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
            public void onFailure(Call<List<Nationality>> call, Throwable t) {

            }
        });
    }

    public void AddClient(View view) {
        validation();
    }

    private void SetData() {
        client_name = name;
        client_phone = phone;
        client_id = national_num;
        et_client_name.setText(client_name);
        et_client_phone.setText(client_phone);
        et_client_id.setText(client_id);

    }

    private void validation() {
       client_name = et_client_name.getText().toString();
       client_id = et_client_id.getText().toString();
       client_phone = et_client_phone.getText().toString();
      if(!TextUtils.isEmpty(client_name) && !TextUtils.isEmpty(client_id)&&!TextUtils.isEmpty(client_phone)
      && !TextUtils.isEmpty(m_nat) && !TextUtils.isEmpty(m_res) && client_id.length()==10 &&
      client_phone.length() == 11){
         et_client_name.setError(null);
         et_client_phone.setError(null);
         et_client_id.setError(null);
         if(flag == 1){
             AddClient(client_id,client_name,m_res,m_nat,client_phone);
         }else {
             UpdateClient(client_id,client_name,m_res,m_nat,client_phone);
         }

         
      }else {
          if (TextUtils.isEmpty(client_name)) {
              et_client_name.setError("ادخل اسم العميل");
          } else {
              et_client_name.setError(null);
          }
          if (TextUtils.isEmpty(client_id)) {
              et_client_id.setError("ادخل رقم الهوية");
          } else {
              et_client_id.setError(null);
          }
          if (TextUtils.isEmpty(client_phone)) {
              et_client_phone.setError("ادخل رقم الهاتف");
          } else {
              et_client_phone.setError(null);
          }
          if (TextUtils.isEmpty(m_nat)) {
              Toast.makeText(this, "برجاء ادخال الجنسية", Toast.LENGTH_SHORT).show();
          }
          if (TextUtils.isEmpty(m_res)) {
              Toast.makeText(this, "برجاء ادخال هل تم تسجيله من قبل ام لا ", Toast.LENGTH_SHORT).show();
          }
          if (client_id.length() < 10 || client_id.length() > 10) {
              et_client_id.setError("رقم الهوية لابد ان يكون 10 ارقام");
          } else {
              et_client_id.setError(null);
          }
          if (client_phone.length() < 10 || client_phone.length() > 10) {
              et_client_phone.setError("رقم الهاتف لابد ان يكون 10 ارقام");
          }else {
              et_client_phone.setError(null);
          }
      }
    }

    private void UpdateClient(String client_id, String client_name, String m_res, String m_nat, String client_phone) {
        Login login = mprefs.Get_UserData(Registeration.this);
        String user_id = login.getUserId();
        Api.getService().update_client(client_id,client_name,m_res,m_nat,client_phone,user_id,id).enqueue(new Callback<AddClient>() {
            @Override
            public void onResponse(Call<AddClient> call, Response<AddClient> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()==1){
                        Toast.makeText(Registeration.this, "تم تعديل بيانات العميل بنجاح", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Registeration.this,AllClients.class));
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddClient> call, Throwable t) {

            }
        });
    }

    private void AddClient(String client_id, String client_name, String m_res, String m_nat, String client_phone) {
        Login login = mprefs.Get_UserData(Registeration.this);
        String user_id = login.getUserId();
        Api.getService().add_client(client_id,client_name,m_res,m_nat,client_phone,user_id).enqueue(new Callback<AddClient>() {
            @Override
            public void onResponse(Call<AddClient> call, Response<AddClient> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()==1){
                        Toast.makeText(Registeration.this, "تم اضافة العميل بنجاح", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Registeration.this, HOME.class));
                    }
                }
            }

            @Override
            public void onFailure(Call<AddClient> call, Throwable t) {

            }
        });
    }
    public void getDataIntent(){
        flag = getIntent().getIntExtra("flag",0);
        if(flag == 2){
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            phone = getIntent().getStringExtra("mob");
            national_num = getIntent().getStringExtra("national_num");
        }
    }

    public void BACK(View view) {
        onBackPressed();
    }
}
