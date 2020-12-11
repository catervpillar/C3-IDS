package it.unicam.cs.ids.c3.model;

import java.util.Objects;

public abstract class Utente {
    private int ID;
    private String username;
    private String password;
    private String email;
    private String telefono;

    public Utente(int ID, String username, String password, String email, String telefono) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telefono = telefono;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utente)) return false;
        Utente utente = (Utente) o;
        return ID == utente.ID &&
                username.equals(utente.username) &&
                email.equals(utente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, username, email);
    }
}
