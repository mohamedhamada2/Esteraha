package com.alatheer.esteraha.FeeManagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.alatheer.esteraha.Data.remote.Models.ViewEslat;
import com.alatheer.esteraha.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllEslatAdapter extends RecyclerView.Adapter<AllEslatAdapter.AllEslatHolder> {
    List<ViewEslat> viewEslats;
    Context context;

    public AllEslatAdapter(List<ViewEslat> viewEslats, Context context) {
        this.viewEslats = viewEslats;
        this.context = context;
    }

    @NonNull
    @Override
    public AllEslatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_esal_item,parent,false);
        return new  AllEslatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllEslatHolder holder, int position) {
        holder.emp_name.setText(viewEslats.get(position).getName());
        holder.esal_num.setText(viewEslats.get(position).getRkmEsal());
        String date = viewEslats.get(position).getDateEsal();
        long dt = Long.parseLong(date);
        Date d = new Date((long) (dt*1000.05));
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd ");
        holder.esal_date.setText(f.format(d));
        holder.client_national_num.setText(viewEslats.get(position).getClientNationlNum());
        holder.total_value.setText(viewEslats.get(position).getTotalValue());
        holder.esal_value.setText(viewEslats.get(position).getPaid());
        holder.remain_value.setText(viewEslats.get(position).getRemain());
    }

    @Override
    public int getItemCount() {
        return viewEslats.size();
    }

    class AllEslatHolder extends  RecyclerView.ViewHolder{
        EditText emp_name,esal_num,esal_date,client_national_num,total_value,esal_value,remain_value;
        public AllEslatHolder(@NonNull View itemView) {
            super(itemView);
            emp_name = itemView.findViewById(R.id.employee_name);
            esal_num = itemView.findViewById(R.id.esal_num);
            esal_date = itemView.findViewById(R.id.esal_date);
            client_national_num = itemView.findViewById(R.id.client_national_num);
            total_value = itemView.findViewById(R.id.total_value);
            esal_value = itemView.findViewById(R.id.esal_value);
            remain_value = itemView.findViewById(R.id.remain_value);
        }
    }
}
