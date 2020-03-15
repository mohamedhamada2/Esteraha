package com.alatheer.esteraha.RestsManagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alatheer.esteraha.Data.remote.Models.RestModel;
import com.alatheer.esteraha.R;
import com.alatheer.esteraha.Reservation_Management.Fragments.AllRests.AllRestsAdapter;
import com.alatheer.esteraha.Reservation_Management.Resevation_Management_Home;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllRestsAdapter2 extends RecyclerView.Adapter<AllRestsAdapter2.AllRestsHolder> {
    Context context;
    List<RestModel> list;
    AllRestsActivity allRestsActivity;

    public AllRestsAdapter2(Context context, List<RestModel> list) {
        this.context = context;
        this.list = list;
        allRestsActivity = (AllRestsActivity) context;
    }

    @NonNull
    @Override
    public AllRestsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rest_item,parent,false);
        return new AllRestsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllRestsHolder holder, final int position) {
        holder.txt_num.setText(list.get(position).getRkm());
        holder.txt_name.setText(list.get(position).getName());
        holder.txt_address.setText(list.get(position).getAdress());
        holder.txt_price.setText(list.get(position).getPrice());
        holder.btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allRestsActivity.update_rest(list.get(position).getId(),list.get(position).getRkm(),list.get(position).getName(),
                        list.get(position).getAdress(),list.get(position).getPrice(),
                        list.get(position).getTimeLogin(),list.get(position).getTimeLogout(),
                        list.get(position).getDetails());
            }
        });
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allRestsActivity.delete_rest(list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class AllRestsHolder extends RecyclerView.ViewHolder{
        TextView txt_num,txt_name,txt_address,txt_price;
        Button btn_update,btn_delete;
        public AllRestsHolder(@NonNull View itemView) {
            super(itemView);
            txt_num = itemView.findViewById(R.id.txt_esteraha_no);
            txt_name = itemView.findViewById(R.id.txt_esteraha_name);
            txt_address = itemView.findViewById(R.id.txt_esteraha_address);
            txt_price = itemView.findViewById(R.id.txt_esteraha_price);
            btn_update = itemView.findViewById(R.id.btn_update);
            btn_delete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
