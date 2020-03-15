package com.alatheer.esteraha.Orders;

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
import com.alatheer.esteraha.DayHogozat.HogozatDay;
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

public class Orders_Adapter extends RecyclerView.Adapter<Orders_Adapter.Orders_Holder> {
    List<Record>recordList;
    Context context;

    public Orders_Adapter(List<Record> recordList, Context context) {
        this.recordList = recordList;
        this.context = context;
    }

    @NonNull
    @Override
    public Orders_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.order_item,parent,false);
       return new Orders_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Orders_Holder holder, final int position) {
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
        holder.btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Api.getService().accept_refuse("1",recordList.get(position).getId()).enqueue(new Callback<OrdersModel>() {
                    @Override
                    public void onResponse(Call<OrdersModel> call, Response<OrdersModel> response) {
                        if(response.isSuccessful()){
                            if(response.body().getSucess()==1){
                                Toast.makeText(context, "تم قبول الخصم بنجاح", Toast.LENGTH_SHORT).show();
                                context.startActivity(new Intent(context, Orders.class));
                                ((Activity)context).finish();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<OrdersModel> call, Throwable t) {

                    }
                });
            }
        });
        holder.btn_refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Api.getService().accept_refuse("2",recordList.get(position).getId()).enqueue(new Callback<OrdersModel>() {
                    @Override
                    public void onResponse(Call<OrdersModel> call, Response<OrdersModel> response) {
                        if(response.isSuccessful()){
                            if(response.body().getSucess()==1){
                                Toast.makeText(context, "تم رفض الخصم بنجاح", Toast.LENGTH_SHORT).show();
                                context.startActivity(new Intent(context, Orders.class));
                                ((Activity)context).finish();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<OrdersModel> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    class Orders_Holder extends RecyclerView.ViewHolder{
        EditText et_client_name,et_client_national_num,et_num_of_days,et_phone,et_total,et_paid,et_remain,et_discount_value;
        Button btn_start_date,btn_end_date,btn_accept,btn_refuse;
        TextView rest_name,rest_num;
        public Orders_Holder(@NonNull View itemView) {
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
            btn_accept = itemView.findViewById(R.id.btn_accept);
            btn_refuse = itemView.findViewById(R.id.btn_refuse);
            //btn_confirm_hagz = itemView.findViewById(R.id.confirm_hagz);
        }
    }
}
