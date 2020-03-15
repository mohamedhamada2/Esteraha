package com.alatheer.esteraha.Data.remote.Models;

import com.alatheer.esteraha.Data.remote.Models.Hogzat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllReservationModel {
    @SerializedName("hogzat")
    @Expose
    private List<Hogzat> hogzat = null;
    @SerializedName("sucess")
    @Expose
    private Integer sucess;

    public List<Hogzat> getHogzat() {
        return hogzat;
    }

    public void setHogzat(List<Hogzat> hogzat) {
        this.hogzat = hogzat;
    }

    public Integer getSucess() {
        return sucess;
    }

    public void setSucess(Integer sucess) {
        this.sucess = sucess;
    }
}
