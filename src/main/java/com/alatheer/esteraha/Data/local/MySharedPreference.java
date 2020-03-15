package com.alatheer.esteraha.Data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.alatheer.esteraha.Data.remote.Models.Login;
import com.google.gson.Gson;

public class MySharedPreference {
    Context context;
    SharedPreferences mPrefs;
    private static MySharedPreference instance=null;


    public MySharedPreference(Context context) {
        this.context = context;
    }

    public MySharedPreference() {

    }

    public static  MySharedPreference getInstance()
    {
        if (instance==null)
        {
            instance = new MySharedPreference();
        }
        return instance;
    }

    public void Create_Update_UserData(Context context, Login userModel)
    {
        mPrefs = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String userData = gson.toJson(userModel);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString("user_data",userData);
        editor.apply();
        Create_Update_Session(context, "login");

    }

    public void Create_Update_Session(Context context,String session)
    {
        mPrefs = context.getSharedPreferences("session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString("state",session);
        editor.apply();
    }


    public String getSession(Context context)
    {
        SharedPreferences preferences = context.getSharedPreferences("session",Context.MODE_PRIVATE);
        String session = preferences.getString("state", "logout");
        return session;
    }


    public Login Get_UserData(Context context){

        mPrefs = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        Gson gson=new Gson();
        String userData = mPrefs.getString("user_data", "");
        Login login=gson.fromJson(userData,Login.class);
        return login;


    }

    public void ClearData(Context context) {
        Login userModel = null;
        mPrefs = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String userData = gson.toJson(userModel);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString("user_data", userData);
        editor.apply();
        Create_Update_Session(context,"login");
    }

}
