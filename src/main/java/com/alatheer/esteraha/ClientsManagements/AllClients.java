package com.alatheer.esteraha.ClientsManagements;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.alatheer.esteraha.Authentication.LoginActivity;
import com.alatheer.esteraha.Book_Rest.BookRest;
import com.alatheer.esteraha.Data.local.MySharedPreference;
import com.alatheer.esteraha.Data.remote.Api;
import com.alatheer.esteraha.Data.remote.Models.AddClient;
import com.alatheer.esteraha.Data.remote.Models.CheckClient;
import com.alatheer.esteraha.FeeManagement.FeeHome;
import com.alatheer.esteraha.MasrofatManagement.AddMasrof;
import com.alatheer.esteraha.R;
import com.alatheer.esteraha.ReportsManagement.Reports;
import com.alatheer.esteraha.ReportsManagement.ReportsManagements;
import com.alatheer.esteraha.Reservation_Management.Resevation_Management_Home;
import com.alatheer.esteraha.RestsManagement.RestsManagementHome;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class AllClients extends AppCompatActivity {
    RecyclerView rec_all_clients;
    AllClientsAdapter allClientsAdapter;
    List<CheckClient> checkClients;
    GridLayoutManager gridLayoutManager;
    ProgressBar progressBar ;
    Toolbar toolbar;
    EditText editsearch;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menu;
    MySharedPreference mprefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_clients);
        rec_all_clients = findViewById(R.id.rec_all_clients);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        toolbar = findViewById(R.id.toolbar);
        editsearch = findViewById(R.id.edit_search);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        menu = findViewById(R.id.img_menu);
        mprefs = MySharedPreference.getInstance();
        handleSideMenu();
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });
        checkClients = new ArrayList<>();
        loadallclientsonline();
       editsearch.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               Api.getService().Search_for_User(charSequence.toString()).enqueue(new Callback<List<CheckClient>>() {
                   @Override
                   public void onResponse(Call<List<CheckClient>> call, Response<List<CheckClient>> response) {
                       progressBar.setVisibility(View.GONE);
                       checkClients = response.body();
                       initrecyclerview();
                   }

                   @Override
                   public void onFailure(Call<List<CheckClient>> call, Throwable t) {

                   }
               });
           }

           @Override
           public void afterTextChanged(Editable editable) {

           }
       });
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
                startActivity(new Intent(AllClients.this, Resevation_Management_Home.class));
            }
        });
        LinearLayout client_lyt = (LinearLayout)headerLayout.findViewById(R.id.clients_lyt);
        client_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllClients.this, Clients_Home.class));
            }
        });
        LinearLayout main_lyt = (LinearLayout)headerLayout.findViewById(R.id.main_lyt);
        main_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllClients.this, RestsManagementHome.class));
            }
        });
        LinearLayout reports_lyt = (LinearLayout)headerLayout.findViewById(R.id.reports_management_lyt);
        reports_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllClients.this, ReportsManagements.class));
            }
        });
        LinearLayout expenses_lyt = (LinearLayout)headerLayout.findViewById(R.id.expenses_lyt);
        expenses_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllClients.this, AddMasrof.class));
            }
        });
        LinearLayout fee_lyt = (LinearLayout)headerLayout.findViewById(R.id.fees_lyt);
        fee_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllClients.this, FeeHome.class));
            }
        });
        LinearLayout logout_lyt = (LinearLayout)headerLayout.findViewById(R.id.logout_lyt);
        logout_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mprefs.ClearData(AllClients.this);
                startActivity(new Intent(AllClients.this, LoginActivity.class));
            }
        });
    }

    private void loadallclientsonline() {
        Api.getService().Get_Clients().enqueue(new Callback<List<CheckClient>>() {
            @Override
            public void onResponse(Call<List<CheckClient>> call, Response<List<CheckClient>> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    checkClients = response.body();
                    initrecyclerview();
                }
            }

            @Override
            public void onFailure(Call<List<CheckClient>> call, Throwable t) {

            }
        });
    }

    private void initrecyclerview() {
        allClientsAdapter = new AllClientsAdapter(checkClients,this);
        rec_all_clients.setAdapter(allClientsAdapter);
        gridLayoutManager = new GridLayoutManager(this,2);
        rec_all_clients.setLayoutManager(gridLayoutManager);
        rec_all_clients.setHasFixedSize(true);
    }

    public void updateclient(String id,String name,String phone,String national_num) {
       Intent i = new Intent(AllClients.this,Registeration.class);
       i.putExtra("flag",2);
       i.putExtra("id",id);
       i.putExtra("mob",phone);
       i.putExtra("name",name);
       i.putExtra("national_num",national_num);
       startActivity(i);
    }

    public void deleteclient(String id) {
        progressBar.setVisibility(View.VISIBLE);
        Api.getService().delete_client(id).enqueue(new Callback<AddClient>() {
         @Override
         public void onResponse(Call<AddClient> call, Response<AddClient> response) {
             if(response.isSuccessful()){
                 if(response.body().getSuccess() ==1){
                     progressBar.setVisibility(View.GONE);
                     loadallclientsonline();
                 }
             }
         }

         @Override
         public void onFailure(Call<AddClient> call, Throwable t) {

         }
     });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to Search");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void BACK(View view) {
        onBackPressed();
    }

}