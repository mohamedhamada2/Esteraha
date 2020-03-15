package com.alatheer.esteraha.Data.remote.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EstrahDetails {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("rkm")
    @Expose
    private String rkm;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("adress")
    @Expose
    private String adress;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("time_login")
    @Expose
    private String timeLogin;
    @SerializedName("time_logout")
    @Expose
    private String timeLogout;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("long_map")
    @Expose
    private Object longMap;
    @SerializedName("lat_map")
    @Expose
    private Object latMap;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("from_date")
    @Expose
    private String fromDate;
    @SerializedName("to_date")
    @Expose
    private String toDate;
    @SerializedName("reserve_id_fk")
    @Expose
    private String reserveIdFk;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRkm() {
        return rkm;
    }

    public void setRkm(String rkm) {
        this.rkm = rkm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTimeLogin() {
        return timeLogin;
    }

    public void setTimeLogin(String timeLogin) {
        this.timeLogin = timeLogin;
    }

    public String getTimeLogout() {
        return timeLogout;
    }

    public void setTimeLogout(String timeLogout) {
        this.timeLogout = timeLogout;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Object getLongMap() {
        return longMap;
    }

    public void setLongMap(Object longMap) {
        this.longMap = longMap;
    }

    public Object getLatMap() {
        return latMap;
    }

    public void setLatMap(Object latMap) {
        this.latMap = latMap;
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

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getReserveIdFk() {
        return reserveIdFk;
    }

    public void setReserveIdFk(String reserveIdFk) {
        this.reserveIdFk = reserveIdFk;
    }
}
