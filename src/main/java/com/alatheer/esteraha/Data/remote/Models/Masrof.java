package com.alatheer.esteraha.Data.remote.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Masrof {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("fe2a")
    @Expose
    private String fe2a;
    @SerializedName("masrofat_value")
    @Expose
    private String masrofatValue;
    @SerializedName("date_esal")
    @Expose
    private String dateEsal;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("name")
    @Expose
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFe2a() {
        return fe2a;
    }

    public void setFe2a(String fe2a) {
        this.fe2a = fe2a;
    }

    public String getMasrofatValue() {
        return masrofatValue;
    }

    public void setMasrofatValue(String masrofatValue) {
        this.masrofatValue = masrofatValue;
    }

    public String getDateEsal() {
        return dateEsal;
    }

    public void setDateEsal(String dateEsal) {
        this.dateEsal = dateEsal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

