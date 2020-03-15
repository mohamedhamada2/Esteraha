package com.alatheer.esteraha.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.alatheer.esteraha.Data.local.MySharedPreference;
import com.alatheer.esteraha.Data.remote.Models.Login;
import com.alatheer.esteraha.Home.HOME;
import com.alatheer.esteraha.Reservation_Management.Resevation_Management_Home;
import com.alatheer.esteraha.R;

public class Splash extends AppCompatActivity {
    MySharedPreference mprefs;
    Login login;
    int secondsDelayed = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mprefs = MySharedPreference.getInstance();
        login = mprefs.Get_UserData(this);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if(login != null){
                    startActivity(new Intent(Splash.this, HOME.class));
                    finish();
                }else {
                    startActivity(new Intent(Splash.this, LoginActivity.class));
                    finish();
                }

            }
        }, secondsDelayed * 3000);
    }
}
