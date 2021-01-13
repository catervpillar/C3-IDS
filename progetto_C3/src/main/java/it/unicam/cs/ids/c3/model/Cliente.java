package it.unicam.cs.ids.c3.model;


public class Cliente extends Utente {
    private String nome;
    private String cognome;

    public Cliente(String username, String password, String email, String nome, String cognome) {
        super(username, password, email);
        this.nome = nome;
        this.cognome = cognome;
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
}
