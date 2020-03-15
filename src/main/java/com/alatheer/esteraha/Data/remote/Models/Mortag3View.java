package com.alatheer.esteraha.Data.remote.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Mortag3View {
    @SerializedName("view_mortaga3ats")
    @Expose
    private List<ViewMortaga3at> viewMortaga3ats = null;

    public List<ViewMortaga3at> getViewMortaga3ats() {
        return viewMortaga3ats;
    }

    public void setViewMortaga3ats(List<ViewMortaga3at> viewMortaga3ats) {
        this.viewMortaga3ats = viewMortaga3ats;
    }
}
