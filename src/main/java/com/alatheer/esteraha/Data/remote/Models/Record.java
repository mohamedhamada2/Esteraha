package com.alatheer.esteraha.Data.remote.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record {
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
    @SerializedName("estrah_details")
    @Expose
    private EstrahDetails estrahDetails;
    @SerializedName("client_details")
    @Expose
    private ClientDetails clientDetails;
    @SerializedName("all_esalt")
    @Expose
    private Object allEsalt;
    @SerializedName("remain_value")
    @Expose
    private Integer remainValue;

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

    public Object getAllEsalt() {
        return allEsalt;
    }

    public void setAllEsalt(Object allEsalt) {
        this.allEsalt = allEsalt;
    }

    public Integer getRemainValue() {
        return remainValue;
    }

    public void setRemainValue(Integer remainValue) {
        this.remainValue = remainValue;
    }
}
