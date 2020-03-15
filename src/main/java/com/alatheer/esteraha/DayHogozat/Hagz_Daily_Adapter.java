package com.alatheer.esteraha.DayHogozat;

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

import com.alatheer.esteraha.Book_Rest.BookRest;
import com.alatheer.esteraha.Data.remote.Api;
import com.alatheer.esteraha.Data.remote.Models.AddHagz;
import com.alatheer.esteraha.Data.remote.Models.Hogzat;
import com.alatheer.esteraha.R;
import com.alatheer.esteraha.Reservation_Management.Resevation_Management_Home;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Hagz_Daily_Adapter extends RecyclerView.Adapter<Hagz_Daily_Adapter.Hagz_Daily_Holder> {
    List<Hogzat>hogzatList;
    Context context;
    String reserve_type , pay_method ;
    public Hagz_Daily_Adapter(List<Hogzat> hogzatList, Context context) {
        this.hogzatList = hogzatList;
        this.context = context;
    }

    @NonNull
    @Override
    public Hagz_Daily_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hagz_item,parent,false);
        return new Hagz_Daily_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Hagz_Daily_Holder holder, final int position) {
        holder.et_client_name.setText(hogzatList.get(position).getClientname());
        holder.et_client_national_num.setText(hogzatList.get(position).getClientNationalNum());
        holder.et_num_of_days.setText(hogzatList.get(position).getNumDays());
        String fromdate = hogzatList.get(position).getFromDate();
        long dt = Long.parseLong(fromdate);
        final Date d = new Date((long) (dt * 1000.05));

        final DateFormat f = new SimpleDateFormat("yyyy-MM-dd ");
        holder.btn_start_date.setText(f.format(d));
        String todate = hogzatList.get(position).getToDate();
        long dt2 = Long.parseLong(todate);
        final Date d2 = new Date((long) (dt2 * 1000.05));
        final DateFormat f1 = new SimpleDateFormat("yyyy-MM-dd ");
        holder.btn_end_date.setText(f1.format(d2));
        holder.et_phone.setText(hogzatList.get(position).getMob());
        holder.et_total.setText(hogzatList.get(position).getValue());
        holder.et_paid.setText(hogzatList.get(position).getPaid());
        holder.et_remain.setText(hogzatList.get(position).getRemain());
        holder.et_discount_value.setText(hogzatList.get(position).getDiscountValue());
        holder.rest_num.setText(hogzatList.get(position).getRkm());
        holder.rest_name.setText(hogzatList.get(position).getEstrahatname());
        if(hogzatList.get(position).getTypeReserve().equals("1")){
            holder.reservation_type_.setText("مؤكد");
        }else if (hogzatList.get(position).getTypeReserve().equals("2")){
            holder.reservation_type_.setText("مؤقت");
        }

        holder.btn_update.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(context, BookRest.class);
               i.putExtra("reserved_id",hogzatList.get(position).getId());
               i.putExtra("price",hogzatList.get(position).getValue());
               i.putExtra("id",hogzatList.get(position).getEstrahaIdFk());
               i.putExtra("rest_name",hogzatList.get(position).getEstrahatname());
               i.putExtra("rest_num",hogzatList.get(position).getRkm());
               i.putExtra("rest_address",hogzatList.get(position).getAdress());
               i.putExtra("reserve_type",hogzatList.get(position).getTypeReserve());
               i.putExtra("pay_method",hogzatList.get(position).getPayMethod());
               i.putExtra("discount",hogzatList.get(position).getDiscountValue());
               i.putExtra("khasm",hogzatList.get(position).getKhasm());
               i.putExtra("from",f.format(d));
               i.putExtra("to",f1.format(d2));
               i.putExtra("client_name",hogzatList.get(position).getClientname());
               i.putExtra("national_num",hogzatList.get(position).getClientNationalNum());
               i.putExtra("mob",hogzatList.get(position).getMob());
               i.putExtra("num_days",hogzatList.get(position).getNumDays());
               i.putExtra("paid",hogzatList.get(position).getPaid());
               i.putExtra("tamen",hogzatList.get(position).getTamin());
               i.putExtra("remain",hogzatList.get(position).getRemain());
               i.putExtra("flag",2);
               context.startActivity(i);
               ((Activity)context).finish();
           }
       });
       holder.btn_delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Api.getService().delete_hagz(hogzatList.get(position).getId()).enqueue(new Callback<AddHagz>() {
                   @Override
                   public void onResponse(Call<AddHagz> call, Response<AddHagz> response) {
                       if(response.isSuccessful()){
                           if(response.body().getSuccess()==1){
                               Toast.makeText(context, "hagz delete successfully", Toast.LENGTH_SHORT).show();
                               ((Activity)context).finish();
                           }
                       }
                   }

                   @Override
                   public void onFailure(Call<AddHagz> call, Throwable t) {

                   }
               });
           }
       });
    }

    @Override
    public int getItemCount() {
        return hogzatList.size();
    }

    class Hagz_Daily_Holder extends RecyclerView.ViewHolder{
        EditText et_client_name,et_client_national_num,et_num_of_days,et_phone,et_total,et_paid,et_remain,et_discount_value,reservation_type_;
        Button btn_start_date,btn_end_date;
        Button btn_update,btn_delete;
        TextView rest_name,rest_num;
        public Hagz_Daily_Holder(@NonNull View itemView) {
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
            btn_update = itemView.findViewById(R.id.btn_update_hagz);
            btn_delete = itemView.findViewById(R.id.btn_delete_hagz);
            reservation_type_ = itemView.findViewById(R.id.client_reservation_type);
        }
    }
}
