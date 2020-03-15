package com.alatheer.esteraha.Data.remote.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Records {
    @SerializedName("national_num")
    @Expose
    private String nationalNum;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("estraha_id_fk")
    @Expose
    private String estrahaIdFk;
    @SerializedName("reserved_id")
    @Expose
    private String reservedId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("total_value")
    @Expose
    private String totalValue;
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
    @SerializedName("pay_method")
    @Expose
    private String payMethod;
    @SerializedName("rkm_esal")
    @Expose
    private String rkmEsal;
    @SerializedName("client_name")
    @Expose
    private String clientName;
    @SerializedName("from")
    @Expose
    private Integer from;
    @SerializedName("num_days")
    @Expose
    private String numDays;
    @SerializedName("from_date")
    @Expose
    private String fromDate;
    @SerializedName("to_date")
    @Expose
    private String toDate;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("khasm")
    @Expose
    private String khasm;
    @SerializedName("discount_value")
    @Expose
    private String discountValue;
    @SerializedName("tamin")
    @Expose
    private String tamin;

    public String getNationalNum() {
        return nationalNum;
    }

    public void setNationalNum(String nationalNum) {
        this.nationalNum = nationalNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstrahaIdFk() {
        return estrahaIdFk;
    }

    public void setEstrahaIdFk(String estrahaIdFk) {
        this.estrahaIdFk = estrahaIdFk;
    }

    public String getReservedId() {
        return reservedId;
    }

    public void setReservedId(String reservedId) {
        this.reservedId = reservedId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
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

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getRkmEsal() {
        return rkmEsal;
    }

    public void setRkmEsal(String rkmEsal) {
        this.rkmEsal = rkmEsal;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public String getNumDays() {
        return numDays;
    }

    public void setNumDays(String numDays) {
        this.numDays = numDays;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKhasm() {
        return khasm;
    }

    public void setKhasm(String khasm) {
        this.khasm = khasm;
    }

    public String getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(String discountValue) {
        this.discountValue = discountValue;
    }

    public String getTamin() {
        return tamin;
    }

    public void setTamin(String tamin) {
        this.tamin = tamin;
    }

}
