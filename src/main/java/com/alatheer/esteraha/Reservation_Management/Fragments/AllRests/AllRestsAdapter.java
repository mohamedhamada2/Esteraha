package com.alatheer.esteraha.Reservation_Management.Fragments.AllRests;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alatheer.esteraha.Book_Rest.BookRest;
import com.alatheer.esteraha.Data.remote.Models.RestModel;
import com.alatheer.esteraha.Reservation_Management.Resevation_Management_Home;
import com.alatheer.esteraha.R;
import com.alatheer.esteraha.RestsManagement.AllRestsActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllRestsAdapter extends RecyclerView.Adapter<AllRestsAdapter.UnBooked_Holder> {
    Context context;
    List<RestModel> list;
    Resevation_Management_Home resevationManagementHome;


    public AllRestsAdapter(Context context, List<RestModel> list) {
        this.context = context;
        this.list = list;
        resevationManagementHome = (Resevation_Management_Home) context;
    }

    @NonNull
    @Override
    public UnBooked_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.unbook_item,parent,false);
       return new UnBooked_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnBooked_Holder holder, final int position) {
        holder.txt_num.setText(list.get(position).getRkm());
        holder.txt_name.setText(list.get(position).getName());
        holder.txt_address.setText(list.get(position).getAdress());
        holder.txt_price.setText(list.get(position).getPrice());
        holder.booked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              resevationManagementHome.go_to_book_rest_activity(list.get(position).getId(),list.get(position).getPrice(),list.get(position).getName(),list.get(position).getRkm(),list.get(position).getAdress());
            }
        });
        holder.detailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = LayoutInflater.from(context).inflate(R.layout.details_dialog, null);
                final android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(context)
                        .setCancelable(false)
                        .create();
                ImageButton close = v.findViewById(R.id.close_img);
                EditText rest_no = v.findViewById(R.id.rest_num);
                EditText rest_name = v.findViewById(R.id.rest_name);
                EditText rest_address = v.findViewById(R.id.rest_address);
                EditText rest_price = v.findViewById(R.id.rest_price);
                rest_no.setText(list.get(position).getRkm());
                rest_name.setText(list.get(position).getName());
                rest_address.setText(list.get(position).getAdress());
                rest_price.setText(list.get(position).getPrice());
                close.setOnClickListener(new View.OnClickListener() {
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
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class  UnBooked_Holder extends RecyclerView.ViewHolder{
     TextView txt_num,txt_name,txt_address,txt_price;
     Button booked,detailed;
        public UnBooked_Holder(@NonNull View itemView) {
            super(itemView);
            txt_num = itemView.findViewById(R.id.txt_esteraha_no);
            txt_name = itemView.findViewById(R.id.txt_esteraha_name);
            txt_address = itemView.findViewById(R.id.txt_esteraha_address);
            txt_price = itemView.findViewById(R.id.txt_esteraha_price);
            booked = itemView.findViewById(R.id.btn_book);
            detailed = itemView.findViewById(R.id.btn_details);
        }
    }
}
