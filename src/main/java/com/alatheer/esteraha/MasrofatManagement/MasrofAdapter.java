package com.alatheer.esteraha.MasrofatManagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.alatheer.esteraha.Data.remote.Models.Masrof;
import com.alatheer.esteraha.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MasrofAdapter extends RecyclerView.Adapter<MasrofAdapter.MasrofHolder> {
    List<Masrof>masrofList;
    Context context;

    public MasrofAdapter(List<Masrof> masrofList, Context context) {
        this.masrofList = masrofList;
        this.context = context;
    }

    @NonNull
    @Override
    public MasrofHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.masrof_item,parent,false);
        return new MasrofHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MasrofHolder holder, int position) {
       holder.edt_name.setText(masrofList.get(position).getName());
       holder.edt_value.setText(masrofList.get(position).getMasrofatValue());
       String date = masrofList.get(position).getDateEsal();
       long dt = Long.parseLong(date);
       Date d = new Date((long) (dt*1000.05));
       DateFormat f = new SimpleDateFormat("yyyy-MM-dd ");
       holder.edt_date.setText(f.format(d));
       holder.edt_fea.setText(masrofList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return masrofList.size();
    }

    class MasrofHolder extends RecyclerView.ViewHolder{
        EditText edt_name,edt_date,edt_value,edt_fea;
        public MasrofHolder(@NonNull View itemView) {
            super(itemView);
            edt_name = itemView.findViewById(R.id.employee_name);
            edt_date = itemView.findViewById(R.id.date);
            edt_fea = itemView.findViewById(R.id.fea);
            edt_value = itemView.findViewById(R.id.edt_value);
        }
    }
}
