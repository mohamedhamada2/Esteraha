package com.alatheer.esteraha.Data.remote.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewEslat {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("rkm_esal")
    @Expose
    private String rkmEsal;
    @SerializedName("reserve_id_fk")
    @Expose
    private String reserveIdFk;
    @SerializedName("client_nationl_num")
    @Expose
    private String clientNationlNum;
    @SerializedName("estraha_id_fk")
    @Expose
    private String estrahaIdFk;
    @SerializedName("total_value")
    @Expose
    private String totalValue;
    @SerializedName("pay_method")
    @Expose
    private String payMethod;
    @SerializedName("paid")
    @Expose
    private String paid;
    @SerializedName("remain")
    @Expose
    private String remain;
    @SerializedName("date_esal")
    @Expose
    private String dateEsal;
    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("name")
    @Expose
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRkmEsal() {
        return rkmEsal;
    }

    public void setRkmEsal(String rkmEsal) {
        this.rkmEsal = rkmEsal;
    }

    public String getReserveIdFk() {
        return reserveIdFk;
    }

    public void setReserveIdFk(String reserveIdFk) {
        this.reserveIdFk = reserveIdFk;
    }

    public String getClientNationlNum() {
        return clientNationlNum;
    }

    public void setClientNationlNum(String clientNationlNum) {
        this.clientNationlNum = clientNationlNum;
    }

    public String getEstrahaIdFk() {
        return estrahaIdFk;
    }

    public void setEstrahaIdFk(String estrahaIdFk) {
        this.estrahaIdFk = estrahaIdFk;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getRemain() {
        return remain;
    }

    public void setRemain(String remain) {
        this.remain = remain;
    }

    public String getDateEsal() {
        return dateEsal;
    }

    public void setDateEsal(String dateEsal) {
        this.dateEsal = dateEsal;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
