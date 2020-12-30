package it.unicam.cs.ids.c3.model;

import java.util.Objects;

public class Cliente extends Utente {
    private String nome;
    private String cognome;
    private String indirizzo;

    public Cliente(String username, String password, String email, String telephone, String name, String cognome, String indirizzo) {
        super(username, password, email, telephone);
        this.nome = name;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
}
