package com.alatheer.esteraha.ReportsManagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.alatheer.esteraha.Data.remote.Models.Masrofat;
import com.alatheer.esteraha.Data.remote.Models.Records;
import com.alatheer.esteraha.MasrofatManagement.MasrofAdapter;
import com.alatheer.esteraha.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MasrofatAdapter extends RecyclerView.Adapter<MasrofatAdapter.MasrofatHolder> {
    List<Masrofat>recordsList;
    Context context;

    public MasrofatAdapter(List<Masrofat> recordsList, Context context) {
        this.recordsList = recordsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MasrofatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.masrof_item,parent,false);
        return new MasrofatAdapter.MasrofatHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MasrofatHolder holder, int position) {
        holder.edt_name.setText(recordsList.get(position).getName());
        holder.edt_value.setText(recordsList.get(position).getMasrofatValue());
        String date = recordsList.get(position).getDateEsal();
        long dt = Long.parseLong(date);
        Date d = new Date((long) (dt*1000.05));
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        holder.edt_date.setText(f.format(d));
        holder.edt_fea.setText(recordsList.get(position).getFesaName());

    }

    @Override
    public int getItemCount() {
        return recordsList.size();
    }

    class MasrofatHolder extends RecyclerView.ViewHolder{
        EditText edt_name,edt_date,edt_value,edt_fea;
        public MasrofatHolder(@NonNull View itemView) {
            super(itemView);
            edt_name = itemView.findViewById(R.id.employee_name);
            edt_date = itemView.findViewById(R.id.date);
            edt_fea = itemView.findViewById(R.id.fea);
            edt_value = itemView.findViewById(R.id.edt_value);
        }
    }
}
