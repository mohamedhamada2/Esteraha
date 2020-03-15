package com.alatheer.esteraha.Data.remote.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllRemains {

    @SerializedName("remains")
    @Expose
    private Remains remains;

    public Remains getRemains() {
        return remains;
    }

    public void setRemains(Remains remains) {
        this.remains = remains;
    }
}
