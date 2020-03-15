package com.alatheer.esteraha.Authentication;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alatheer.esteraha.Data.local.MySharedPreference;
import com.alatheer.esteraha.Data.remote.Api;
import com.alatheer.esteraha.Data.remote.Models.Login;
import com.alatheer.esteraha.Home.HOME;
import com.alatheer.esteraha.Reservation_Management.Resevation_Management_Home;
import com.alatheer.esteraha.R;

public class LoginActivity extends AppCompatActivity {
    EditText edit_user_name,edit_password;
    ProgressBar progressBar;
    String name,password;
    MySharedPreference mprefs;
    Login login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mprefs = MySharedPreference.getInstance();
        edit_user_name = findViewById(R.id.edit_user_name);
        edit_password = findViewById(R.id.edit_password);
        progressBar = findViewById(R.id.progressbar);
    }

    public void AddUser(View view) {
        Validation();
    }

    private void Validation() {
        name = edit_user_name.getText().toString();
        password = edit_password.getText().toString();
        if (    !TextUtils.isEmpty(name) &&
                !TextUtils.isEmpty(password)) {

            edit_user_name.setError(null);
            edit_password.setError(null);

//            Login(userName, passWord);

//            Login(email, passWord);
            LoginWeb(name, password);




        } else {

            if (TextUtils.isEmpty(name)){
                edit_user_name.setError(getString(R.string.name_req));
            }else {
                edit_user_name.setError(null);
            }
            if (TextUtils.isEmpty(password)){
                edit_password.setError(getString(R.string.pass_req));
            }else {
                edit_password.setError(null);
            }


        }
    }

    private void LoginWeb(String name, final String password) {
        progressBar.setVisibility(View.VISIBLE);
        Api.getService().admin_login(name,password).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess() == 1){
                        progressBar.setVisibility(View.GONE);
                        login = response.body();
                        mprefs.Create_Update_UserData(LoginActivity.this,login);
                        startActivity(new Intent(LoginActivity.this, HOME.class));
                        finish();
                    }else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this,"الاسم او الباسورد غير صحيح",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
               Log.e("error",t.getMessage());
            }
        });
    }
}
