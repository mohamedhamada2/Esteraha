package com.alatheer.esteraha.Data.remote.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrdersModel {
    @SerializedName("records")
    @Expose
    private List<Record> records = null;
    @SerializedName("sucess")
    @Expose
    private Integer sucess;

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public Integer getSucess() {
        return sucess;
    }

    public void setSucess(Integer sucess) {
        this.sucess = sucess;
    }

}
