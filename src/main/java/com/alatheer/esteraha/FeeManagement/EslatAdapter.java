package com.alatheer.esteraha.FeeManagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.alatheer.esteraha.Data.remote.Models.EslatData;
import com.alatheer.esteraha.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EslatAdapter extends RecyclerView.Adapter<EslatAdapter.EslatHolder> {
    Context context ;
    List<EslatData> eslatDataList;
    ReservationDetails reservationDetails;
    public EslatAdapter(Context context, List<EslatData> eslatDataList) {
        this.context = context;
        this.eslatDataList = eslatDataList;
        reservationDetails = (ReservationDetails) context;
    }

    @NonNull
    @Override
    public EslatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.esal_item,parent,false);
       return new EslatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EslatHolder holder, final int position) {
        holder.rest_num.setText(eslatDataList.get(position).getEstrahDetails().getRkm());
        holder.rest_name.setText(eslatDataList.get(position).getEstrahDetails().getName());
        holder.rest_price.setText(eslatDataList.get(position).getEstrahDetails().getPrice());
        holder.rest_address.setText(eslatDataList.get(position).getEstrahDetails().getAdress());
        holder.client_name.setText(eslatDataList.get(position).getClientDetails().getName());
        holder.client_national_num.setText(eslatDataList.get(position).getClientDetails().getNationalNum());
        holder.client_phone.setText(eslatDataList.get(position).getClientDetails().getMob());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reservationDetails.send(eslatDataList.get(position).getId(),eslatDataList.get(position).getValue()
                ,eslatDataList.get(position).getPaid(),eslatDataList.get(position).getRemain()
                        ,eslatDataList.get(position).getClientDetails().getNationalNum(),
                        eslatDataList.get(position).getEstrahaIdFk());
            }
        });

    }

    @Override
    public int getItemCount() {
        return eslatDataList.size();
    }

    class  EslatHolder extends RecyclerView.ViewHolder{
        EditText rest_num,rest_name,rest_price,rest_address,client_name,client_national_num,client_phone;
        RelativeLayout relativeLayout;
        public EslatHolder(@NonNull View itemView) {
            super(itemView);
            rest_num = itemView.findViewById(R.id.rest_num);
            rest_name = itemView.findViewById(R.id.rest_name);
            rest_price = itemView.findViewById(R.id.rest_price);
            rest_address = itemView.findViewById(R.id.rest_address);
            client_name = itemView.findViewById(R.id.client_name);
            client_national_num = itemView.findViewById(R.id.client_national_num);
            client_phone = itemView.findViewById(R.id.client_phone);
            relativeLayout = itemView.findViewById(R.id.relative_all);
        }
    }
}
