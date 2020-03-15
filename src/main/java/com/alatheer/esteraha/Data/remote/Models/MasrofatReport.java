package com.alatheer.esteraha.Data.remote.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MasrofatReport {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("masrofat_reports")
    @Expose
    private List<Masrofat> masrofat = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<Masrofat> getMasrofat() {
        return masrofat;
    }

    public void setMasrofat(List<Masrofat> masrofat) {
        this.masrofat = masrofat;
    }
}
