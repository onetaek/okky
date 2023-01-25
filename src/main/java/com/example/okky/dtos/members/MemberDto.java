package com.example.okky.dtos.members;

import java.util.Date;
import java.util.Objects;

public class MemberDto {
    private String email;
    private String password;
    private String name;
    private String nickName;
    private String telecom;
    private String contactCountryValue;
    private String contact;
    private boolean policyEmailSend;
    private Date createdAt;
    private String statusValue;
    private boolean isAdmin;


    public MemberDto build() {
        return new MemberDto();
    }
    public MemberDto() {
    }

    public MemberDto(String email, String password, String name, String nickName, String telecom, String contactCountryValue, String contact, boolean policyEmailSend, Date createdAt, String statusValue, boolean isAdmin) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
        this.telecom = telecom;
        this.contactCountryValue = contactCountryValue;
        this.contact = contact;
        this.policyEmailSend = policyEmailSend;
        this.createdAt = createdAt;
        this.statusValue = statusValue;
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDto memberDto = (MemberDto) o;
        return Objects.equals(email, memberDto.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTelecom() {
        return telecom;
    }

    public void setTelecom(String telecom) {
        this.telecom = telecom;
    }

    public String getContactCountryValue() {
        return contactCountryValue;
    }

    public void setcontactCountryValue(String contactCountryValue) {
        this.contactCountryValue = contactCountryValue;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public boolean isPolicyEmailSend() {
        return policyEmailSend;
    }

    public void setPolicyEmailSend(boolean policyEmailSend) {
        this.policyEmailSend = policyEmailSend;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", telecom='" + telecom + '\'' +
                ", contactCountryValue='" + contactCountryValue + '\'' +
                ", contact='" + contact + '\'' +
                ", policyEmailSend=" + policyEmailSend +
                ", createdAt=" + createdAt +
                ", statusValue='" + statusValue + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
