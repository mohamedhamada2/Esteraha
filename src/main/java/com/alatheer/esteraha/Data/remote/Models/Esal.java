package com.alatheer.esteraha.Data.remote.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Esal {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("eslat_data")
    @Expose
    private List<EslatData> eslatData = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<EslatData> getEslatData() {
        return eslatData;
    }

    public void setEslatData(List<EslatData> eslatData) {
        this.eslatData = eslatData;
    }
}
