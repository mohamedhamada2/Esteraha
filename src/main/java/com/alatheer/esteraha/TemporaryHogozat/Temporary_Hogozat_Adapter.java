package com.alatheer.esteraha.TemporaryHogozat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.esteraha.All_Hogozat.AllHogozat;
import com.alatheer.esteraha.Data.remote.Api;
import com.alatheer.esteraha.Data.remote.Models.OrdersModel;
import com.alatheer.esteraha.Data.remote.Models.Record;
import com.alatheer.esteraha.Data.remote.Models.Success;
import com.alatheer.esteraha.Orders.Orders;
import com.alatheer.esteraha.Orders.Orders_Adapter;
import com.alatheer.esteraha.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Temporary_Hogozat_Adapter extends RecyclerView.Adapter<Temporary_Hogozat_Adapter.Temporary_Hogozat_Holder> {
    List<Record> recordList;
    Context context;

    public Temporary_Hogozat_Adapter(List<Record> recordList, Context context) {
        this.recordList = recordList;
        this.context = context;
    }

    @NonNull
    @Override
    public Temporary_Hogozat_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.temporary_item,parent,false);
        return new Temporary_Hogozat_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Temporary_Hogozat_Holder holder, final int position) {
        holder.et_client_name.setText(recordList.get(position).getClientDetails().getName());
        holder.et_client_national_num.setText(recordList.get(position).getClientDetails().getNationalNum());
        holder.et_num_of_days.setText(recordList.get(position).getNumDays());
        String fromdate = recordList.get(position).getFromDate();
        String todate = recordList.get(position).getToDate();
        long dt = Long.parseLong(fromdate);
        Date d = new Date((long) (dt*1000.05));
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd ");
        holder.btn_start_date.setText(f.format(d));
        long dt2 = Long.parseLong(todate);
        Date d2 = new Date((long) (dt2*1000.05));
        DateFormat f1 = new SimpleDateFormat("yyyy-MM-dd ");
        holder.btn_end_date.setText(f1.format(d2));
        holder.et_phone.setText(recordList.get(position).getClientDetails().getMob());
        holder.et_total.setText(recordList.get(position).getValue());
        holder.et_paid.setText(recordList.get(position).getPaid());
        holder.et_remain.setText(recordList.get(position).getRemain());
        holder.et_discount_value.setText(recordList.get(position).getDiscountValue());
        holder.rest_num.setText(recordList.get(position).getEstrahDetails().getRkm());
        holder.rest_name.setText(recordList.get(position).getEstrahDetails().getName());
        holder.btn_confirm_hagz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Api.getService().change_temporry(recordList.get(position).getId()).enqueue(new Callback<Success>() {
                    @Override
                    public void onResponse(Call<Success> call, Response<Success> response) {
                        if(response.isSuccessful()){
                            if(response.body().getSuccess()==1){
                                Toast.makeText(context, "تم تاكيد الحجز بنجاح", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, AllHogozat.class);
                                intent.putExtra("flag",2);
                                context.startActivity(intent);
                                ((Activity)context).finish();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Success> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    class Temporary_Hogozat_Holder extends  RecyclerView.ViewHolder{
        EditText et_client_name,et_client_national_num,et_num_of_days,et_phone,et_total,et_paid,et_remain,et_discount_value;
        Button btn_start_date,btn_end_date,btn_confirm_hagz;
        TextView rest_name,rest_num;
        public Temporary_Hogozat_Holder(@NonNull View itemView) {
            super(itemView);
            et_client_name = itemView.findViewById(R.id.client_name);
            et_client_national_num = itemView.findViewById(R.id.client_national_num);
            et_num_of_days = itemView.findViewById(R.id.num_days);
            et_phone = itemView.findViewById(R.id.client_phone);
            et_total = itemView.findViewById(R.id.total_value);
            et_paid = itemView.findViewById(R.id.paid_value);
            et_remain = itemView.findViewById(R.id.remain_value);
            et_discount_value = itemView.findViewById(R.id.client_discount_Value);
            btn_start_date = itemView.findViewById(R.id.start_date);
            btn_end_date = itemView.findViewById(R.id.end_date);
            rest_name = itemView.findViewById(R.id.txt_esteraha_name);
            rest_num = itemView.findViewById(R.id.txt_esteraha_no);
            btn_confirm_hagz = itemView.findViewById(R.id.confirm_hagz);
        }
    }
}
