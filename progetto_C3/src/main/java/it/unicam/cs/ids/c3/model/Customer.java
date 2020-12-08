package it.unicam.cs.ids.c3.model;

public class Customer extends User {
    private String name;
    private String surname;
    private String address;

    public Customer(int ID, String username, String password, String email, String telephone, String name, String surname, String address) {
        super(ID, username, password, email, telephone);
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
