package it.unicam.cs.ids.c3.model;

public class Courier extends User {
    private String businessName;
    private CourierStatus status;

    public Courier(int ID, String username, String password, String email, String telephone, String businessName) {
        super(ID, username, password, email, telephone);
        this.businessName = businessName;
        this.status = CourierStatus.NOT_AVAILABLE;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public CourierStatus getStatus() {
        return status;
    }

    public void setStatus(CourierStatus status) {
        this.status = status;
    }
}