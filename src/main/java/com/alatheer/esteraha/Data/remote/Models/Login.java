package com.alatheer.esteraha.Data.remote.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_username")
    @Expose
    private String userUsername;
    @SerializedName("emp_code")
    @Expose
    private String empCode;
    @SerializedName("magles_mem_code")
    @Expose
    private String maglesMemCode;
    @SerializedName("user_pass")
    @Expose
    private String userPass;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("role_id_fk")
    @Expose
    private String roleIdFk;
    @SerializedName("system_structure_id_fk")
    @Expose
    private String systemStructureIdFk;
    @SerializedName("head_dep_id_fk")
    @Expose
    private String headDepIdFk;
    @SerializedName("dep_job_id_fk")
    @Expose
    private String depJobIdFk;
    @SerializedName("administration_id")
    @Expose
    private Object administrationId;
    @SerializedName("user_phone")
    @Expose
    private String userPhone;
    @SerializedName("user_id_number")
    @Expose
    private String userIdNumber;
    @SerializedName("user_photo")
    @Expose
    private String userPhoto;
    @SerializedName("last_login")
    @Expose
    private String lastLogin;
    @SerializedName("approved")
    @Expose
    private String approved;
    @SerializedName("is_logged")
    @Expose
    private String isLogged;
    @SerializedName("branch_id_fk")
    @Expose
    private Object branchIdFk;
    @SerializedName("success")
    @Expose
    private Integer success;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getMaglesMemCode() {
        return maglesMemCode;
    }

    public void setMaglesMemCode(String maglesMemCode) {
        this.maglesMemCode = maglesMemCode;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getRoleIdFk() {
        return roleIdFk;
    }

    public void setRoleIdFk(String roleIdFk) {
        this.roleIdFk = roleIdFk;
    }

    public String getSystemStructureIdFk() {
        return systemStructureIdFk;
    }

    public void setSystemStructureIdFk(String systemStructureIdFk) {
        this.systemStructureIdFk = systemStructureIdFk;
    }

    public String getHeadDepIdFk() {
        return headDepIdFk;
    }

    public void setHeadDepIdFk(String headDepIdFk) {
        this.headDepIdFk = headDepIdFk;
    }

    public String getDepJobIdFk() {
        return depJobIdFk;
    }

    public void setDepJobIdFk(String depJobIdFk) {
        this.depJobIdFk = depJobIdFk;
    }

    public Object getAdministrationId() {
        return administrationId;
    }

    public void setAdministrationId(Object administrationId) {
        this.administrationId = administrationId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserIdNumber() {
        return userIdNumber;
    }

    public void setUserIdNumber(String userIdNumber) {
        this.userIdNumber = userIdNumber;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getIsLogged() {
        return isLogged;
    }

    public void setIsLogged(String isLogged) {
        this.isLogged = isLogged;
    }

    public Object getBranchIdFk() {
        return branchIdFk;
    }

    public void setBranchIdFk(Object branchIdFk) {
        this.branchIdFk = branchIdFk;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
