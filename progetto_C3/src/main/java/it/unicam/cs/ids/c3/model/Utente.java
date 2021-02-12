package it.unicam.cs.ids.c3.model;

import java.util.Objects;

public abstract class Utente implements HasID {
    private String ID;
    private String username;
    private String password;
    private String email;
    private String telefono;
    private String indirizzo;

    public Utente(String username, String password, String email) {
        IDGenerator.generateID(this);
        this.username = username;
        this.password = password;
        this.email = email;
        this.telefono = "";
        this.indirizzo = "";
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
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

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return ID.equals(utente.ID) && username.equals(utente.username) && email.equals(utente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, username, email);
    }
}