package com.ai.vos.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_user")
public class SysUser {
    @Id
    @Column(name = "USER_ID")
    private BigDecimal userId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LOGIN_NAME")
    private String loginName;

    @Column(name = "ORG_ID")
    private BigDecimal orgId;

    @Column(name = "MAIL")
    private String mail;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "EXPIRE_TIME")
    private Date expireTime;

    @Column(name = "LAST_MODIFY_PWD_TIME")
    private Date lastModifyPwdTime;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "LANGUAGE_CODE")
    private String languageCode;

    @Column(name = "EMP_ID")
    private BigDecimal empId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PINCODE_CODE")
    private String pincodeCode;

    @Column(name = "COUNTY_CODE")
    private BigDecimal countyCode;

    @Column(name = "PROV_CODE")
    private BigDecimal provCode;

    @Column(name = "REGION_CODE")
    private BigDecimal regionCode;

    @Column(name = "TENANT_ID")
    private BigDecimal tenantId;

    @Column(name = "IS_ADMIN")
    private BigDecimal isAdmin;

    /**
     * @return USER_ID
     */
    public BigDecimal getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    /**
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return LOGIN_NAME
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param loginName
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * @return ORG_ID
     */
    public BigDecimal getOrgId() {
        return orgId;
    }

    /**
     * @param orgId
     */
    public void setOrgId(BigDecimal orgId) {
        this.orgId = orgId;
    }

    /**
     * @return MAIL
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return PASSWORD
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return STATUS
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return PHONE
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return ADDRESS
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return EXPIRE_TIME
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * @param expireTime
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * @return LAST_MODIFY_PWD_TIME
     */
    public Date getLastModifyPwdTime() {
        return lastModifyPwdTime;
    }

    /**
     * @param lastModifyPwdTime
     */
    public void setLastModifyPwdTime(Date lastModifyPwdTime) {
        this.lastModifyPwdTime = lastModifyPwdTime;
    }

    /**
     * @return REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return LANGUAGE_CODE
     */
    public String getLanguageCode() {
        return languageCode;
    }

    /**
     * @param languageCode
     */
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    /**
     * @return EMP_ID
     */
    public BigDecimal getEmpId() {
        return empId;
    }

    /**
     * @param empId
     */
    public void setEmpId(BigDecimal empId) {
        this.empId = empId;
    }

    /**
     * @return FIRST_NAME
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return LAST_NAME
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return PINCODE_CODE
     */
    public String getPincodeCode() {
        return pincodeCode;
    }

    /**
     * @param pincodeCode
     */
    public void setPincodeCode(String pincodeCode) {
        this.pincodeCode = pincodeCode;
    }

    /**
     * @return COUNTY_CODE
     */
    public BigDecimal getCountyCode() {
        return countyCode;
    }

    /**
     * @param countyCode
     */
    public void setCountyCode(BigDecimal countyCode) {
        this.countyCode = countyCode;
    }

    /**
     * @return PROV_CODE
     */
    public BigDecimal getProvCode() {
        return provCode;
    }

    /**
     * @param provCode
     */
    public void setProvCode(BigDecimal provCode) {
        this.provCode = provCode;
    }

    /**
     * @return REGION_CODE
     */
    public BigDecimal getRegionCode() {
        return regionCode;
    }

    /**
     * @param regionCode
     */
    public void setRegionCode(BigDecimal regionCode) {
        this.regionCode = regionCode;
    }

    /**
     * @return TENANT_ID
     */
    public BigDecimal getTenantId() {
        return tenantId;
    }

    /**
     * @param tenantId
     */
    public void setTenantId(BigDecimal tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * @return IS_ADMIN
     */
    public BigDecimal getIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin
     */
    public void setIsAdmin(BigDecimal isAdmin) {
        this.isAdmin = isAdmin;
    }
}