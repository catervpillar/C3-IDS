package it.unicam.cs.ids.c3.model;

import it.unicam.cs.ids.c3.utilities.Controllore;

import java.util.Objects;

public abstract class Utente implements HasID {
    private String ID;
    private String username;
    private String password;
    private String email;
    private String telefono;

    public Utente(String username, String password, String email, String telefono) {
        IDGenerator.generateID(this);
        this.username = username;
        this.password = password;
        this.email = email;
        this.telefono = telefono;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        Controllore.getInstance().controllaID(ID);
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        Controllore.getInstance().controllaUsername(username);
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (Objects.isNull(password))
            throw new NullPointerException("La password dell'utente e' nulla");
        if (password.equals(""))
            throw new IllegalArgumentException("La password dell'utente non e' valida");
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        Controllore.getInstance().controllaEmail(email);
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        Controllore.getInstance().controllaTelefono(telefono);
        this.telefono = telefono;
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
