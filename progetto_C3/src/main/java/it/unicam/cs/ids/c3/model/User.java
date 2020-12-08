package it.unicam.cs.ids.c3.model;

public abstract class User {
    private int ID;
    private String username;
    private String password;
    private String email;
    private String telephone;

    public User(int ID, String username, String password, String email, String telephone) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
