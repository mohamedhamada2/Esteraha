package com.alatheer.esteraha.Data.remote.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EslatData {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("client_national_num")
    @Expose
    private String clientNationalNum;
    @SerializedName("estraha_id_fk")
    @Expose
    private String estrahaIdFk;
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
    @SerializedName("pay_method")
    @Expose
    private String payMethod;
    @SerializedName("tamin")
    @Expose
    private String tamin;
    @SerializedName("paid")
    @Expose
    private String paid;
    @SerializedName("remain")
    @Expose
    private String remain;
    @SerializedName("khasm")
    @Expose
    private String khasm;
    @SerializedName("discount_value")
    @Expose
    private String discountValue;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("rkm_esal")
    @Expose
    private String rkmEsal;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("approved")
    @Expose
    private String approved;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("type_reserve")
    @Expose
    private String typeReserve;
    @SerializedName("estrah_details")
    @Expose
    private EstrahDetails estrahDetails;
    @SerializedName("client_details")
    @Expose
    private ClientDetails clientDetails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientNationalNum() {
        return clientNationalNum;
    }

    public void setClientNationalNum(String clientNationalNum) {
        this.clientNationalNum = clientNationalNum;
    }

    public String getEstrahaIdFk() {
        return estrahaIdFk;
    }

    public void setEstrahaIdFk(String estrahaIdFk) {
        this.estrahaIdFk = estrahaIdFk;
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

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getTamin() {
        return tamin;
    }

    public void setTamin(String tamin) {
        this.tamin = tamin;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getRkmEsal() {
        return rkmEsal;
    }

    public void setRkmEsal(String rkmEsal) {
        this.rkmEsal = rkmEsal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeReserve() {
        return typeReserve;
    }

    public void setTypeReserve(String typeReserve) {
        this.typeReserve = typeReserve;
    }

    public EstrahDetails getEstrahDetails() {
        return estrahDetails;
    }

    public void setEstrahDetails(EstrahDetails estrahDetails) {
        this.estrahDetails = estrahDetails;
    }

    public ClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }
}
