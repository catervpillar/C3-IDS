package it.unicam.cs.ids.c3.model;

public class PickupPoint extends User {
    private String businessName;
    private String address;

    public PickupPoint(int ID, String username, String password, String email, String telephone, String businessName, String address) {
        super(ID, username, password, email, telephone);
        this.businessName = businessName;
        this.address = address;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}