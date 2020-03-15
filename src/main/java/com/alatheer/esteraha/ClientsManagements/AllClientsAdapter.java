package com.alatheer.esteraha.ClientsManagements;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alatheer.esteraha.Data.remote.Models.CheckClient;
import com.alatheer.esteraha.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllClientsAdapter extends RecyclerView.Adapter<AllClientsAdapter.AllClientHolder> {
    List<CheckClient> checkClients;
    Context context;
    AllClients allClients;
    AllClientsAdapter(List<CheckClient> checkClients,Context context){
        this.checkClients = checkClients;
        this.context = context;
        allClients = (AllClients) context;
    }
    @NonNull
    @Override
    public AllClientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.client_item,parent,false);
        return new AllClientHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllClientHolder holder, final int position) {
      holder.client_name.setText(checkClients.get(position).getName());
      holder.client_national_num.setText(checkClients.get(position).getNationalNum());
      holder.client_phone.setText(checkClients.get(position).getMob());
      if (checkClients.get(position).getReserved()== null ){
          holder.reserved.setText("لم يسبق له الحجز");
      }else if (checkClients.get(position).getReserved().equals("2")){
          holder.reserved.setText("لم يسبق له الحجز");
      }else if (checkClients.get(position).getReserved().equals("0")){
          holder.reserved.setText("لم يسبق له الحجز");
      }else if (checkClients.get(position).getReserved().equals("1")){
          holder.reserved.setText("سبق له الحجز");
      }
     ;
      holder.Edit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              allClients.updateclient(checkClients.get(position).getId(),checkClients.get(position).getName(),
                      checkClients.get(position).getMob(),checkClients.get(position).getNationalNum());
          }
      });
      holder.Delete.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              allClients.deleteclient(checkClients.get(position).getId());
          }
      });
    }

    @Override
    public int getItemCount() {
        return checkClients.size();
    }

    class AllClientHolder extends RecyclerView.ViewHolder{
        TextView client_name,client_national_num,client_phone,reserved;
        Button Edit,Delete;
        public AllClientHolder(@NonNull View itemView) {
            super(itemView);
            client_name = itemView.findViewById(R.id.client_name);
            client_national_num = itemView.findViewById(R.id.client_national_num);
            client_phone = itemView.findViewById(R.id.client_phone);
            reserved = itemView.findViewById(R.id.client_reserved);
            Edit = itemView.findViewById(R.id.edit_client);
            Delete = itemView.findViewById(R.id.delete_client);
        }
    }
}
