package com.alatheer.esteraha.FeeManagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.alatheer.esteraha.Data.remote.Models.EslatData;
import com.alatheer.esteraha.Data.remote.Models.ViewMortaga3at;
import com.alatheer.esteraha.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Mortag3atAdapter extends RecyclerView.Adapter<Mortag3atAdapter.Mortaga3atHolder> {
    Context context ;
    List<ViewMortaga3at> mortaga3atList;
    public Mortag3atAdapter(Context context, List<ViewMortaga3at> mortaga3atList) {
        this.context = context;
        this.mortaga3atList = mortaga3atList;
        //reservationDetails = (ReservationDetails) context;
    }

    @NonNull
    @Override
    public Mortag3atAdapter.Mortaga3atHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mortag3_item,parent,false);
        return new Mortag3atAdapter.Mortaga3atHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Mortaga3atHolder holder, final int position) {
        holder.emp_name.setText(mortaga3atList.get(position).getName());
        holder.esal_num.setText(mortaga3atList.get(position).getRkmEsal());
        String date = mortaga3atList.get(position).getDateEsal();
        long dt = Long.parseLong(date);
        Date d = new Date((long) (dt*1000.05));
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd ");
        holder.esal_date.setText(f.format(d));
        holder.client_national_num.setText(mortaga3atList.get(position).getClientNationlNum());
        holder.total_value.setText(mortaga3atList.get(position).getTotalValue());
        holder.esal_value.setText(mortaga3atList.get(position).getPaid());
        holder.remain_value.setText(mortaga3atList.get(position).getRemain());
    }

   @Override
    public int getItemCount() {
        return mortaga3atList.size();
    }

    class  Mortaga3atHolder extends RecyclerView.ViewHolder{
        EditText emp_name,esal_num,esal_date,client_national_num,total_value,esal_value,remain_value;
        public Mortaga3atHolder(@NonNull View itemView) {
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
