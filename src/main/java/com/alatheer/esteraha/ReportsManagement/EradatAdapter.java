package com.alatheer.esteraha.ReportsManagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.alatheer.esteraha.Data.remote.Models.Records;
import com.alatheer.esteraha.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EradatAdapter extends RecyclerView.Adapter<EradatAdapter.EradatHolder> {
    List<Records>recordsList;
    Context context;

    public EradatAdapter(List<Records> recordsList, Context context) {
        this.recordsList = recordsList;
        this.context = context;
    }

    @NonNull
    @Override
    public EradatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.erads_item,parent,false);
        return new EradatHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EradatHolder holder, int position) {
        holder.client_name.setText(recordsList.get(position).getClientName());
        holder.client_national_num.setText(recordsList.get(position).getNationalNum());
        holder.hagz_details.setText(recordsList.get(position).getReservedId());
        if(recordsList.get(position).getPayMethod().equals("1")){
            holder.payment_method.setText("نقدي");
        }else if (recordsList.get(position).getPayMethod().equals("2")){
            holder.payment_method.setText("شبكة");
        }else if (recordsList.get(position).getPayMethod().equals("3")){
            holder.payment_method.setText("تحويل");
        }
        holder.rest_num.setText(recordsList.get(position).getEstrahaIdFk());
        holder.esal_value.setText(recordsList.get(position).getPaid());
        String date = recordsList.get(position).getDate();
        long dt = Long.parseLong(date);
        Date d = new Date((long) (dt*1000.05));
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        holder.esal_date.setText(f.format(d));
    }

    @Override
    public int getItemCount() {
        return recordsList.size();
    }

    class EradatHolder extends RecyclerView.ViewHolder{
        EditText client_name,client_national_num,rest_num,payment_method,hagz_details,esal_value,esal_date;
        public EradatHolder(@NonNull View itemView) {
            super(itemView);
            client_name = itemView.findViewById(R.id.client_name);
            client_national_num = itemView.findViewById(R.id.client_national_num);
            rest_num = itemView.findViewById(R.id.rest_num);
            payment_method = itemView.findViewById(R.id.payment_method);
            hagz_details = itemView.findViewById(R.id.hagz_details);
            esal_value = itemView.findViewById(R.id.esal_value);
            esal_date = itemView.findViewById(R.id.esal_date);

        }
    }
}
