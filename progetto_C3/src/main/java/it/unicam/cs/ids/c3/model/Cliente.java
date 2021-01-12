package it.unicam.cs.ids.c3.model;

import it.unicam.cs.ids.c3.utilities.Controllore;

import java.util.Objects;

public class Cliente extends Utente {
    private String nome;
    private String cognome;
    private String indirizzo;

    public Cliente(String username, String password, String email, String telefono, String nome, String cognome, String indirizzo) {
        super(username, password, email, telefono);
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        Controllore.getInstance().controllaNome(nome);
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        Controllore.getInstance().controllaNome(cognome);
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        Controllore.getInstance().controllaIndirizzo(indirizzo);
        this.indirizzo = indirizzo;
    }
}
