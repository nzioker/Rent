package com.example.rent;

public class ModelOwnerPropertyList {

    private String Address, CompanyName,TotalTenants, ownerName;

    public ModelOwnerPropertyList(String address, String companyName, String totalTenants, String ownerName) {
        Address = address;
        CompanyName = companyName;
        TotalTenants = totalTenants;
        this.ownerName = ownerName;
    }

    public ModelOwnerPropertyList() {
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getTotalTenants() {
        return TotalTenants;
    }

    public void setTotalTenants(String totalTenants) {
        TotalTenants = totalTenants;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
