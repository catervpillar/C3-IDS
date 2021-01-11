package it.unicam.cs.ids.c3.model;

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
        if (Objects.isNull(ID))
            throw new NullPointerException(ID_NULLO);

        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (Objects.isNull(username))
            throw new NullPointerException("L'username dell'utente e' nullo");
        if (username.equals(""))
            throw new IllegalArgumentException("L'username dell'utente non e' valido");
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
        if (Objects.isNull(email))
            throw new NullPointerException("La email dell'utente e' nulla");
        if (email.equals(""))
            throw new IllegalArgumentException("La email dell'utente non e' valida");
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (Objects.isNull(telefono))
            throw new NullPointerException("Il telefono dell'utente e' nullo");
        if (telefono.equals(""))
            throw new IllegalArgumentException("Il telefono dell'utente non e' valido");
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
