package com.alatheer.esteraha.Data.remote.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EsalView {
    @SerializedName("view_eslats")
    @Expose
    private List<ViewEslat> viewEslats = null;

    public List<ViewEslat> getViewEslats() {
        return viewEslats;
    }

    public void setViewEslats(List<ViewEslat> viewEslats) {
        this.viewEslats = viewEslats;
    }
}
