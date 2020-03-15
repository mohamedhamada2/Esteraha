package com.alatheer.esteraha.Data.remote.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientDetails {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("national_num")
    @Expose
    private String nationalNum;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("reserved")
    @Expose
    private Object reserved;
    @SerializedName("nationality")
    @Expose
    private Object nationality;
    @SerializedName("mob")
    @Expose
    private String mob;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("publisher")
    @Expose
    private String publisher;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNationalNum() {
        return nationalNum;
    }

    public void setNationalNum(String nationalNum) {
        this.nationalNum = nationalNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getReserved() {
        return reserved;
    }

    public void setReserved(Object reserved) {
        this.reserved = reserved;
    }

    public Object getNationality() {
        return nationality;
    }

    public void setNationality(Object nationality) {
        this.nationality = nationality;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
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

}
