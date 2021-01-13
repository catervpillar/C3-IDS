package it.unicam.cs.ids.c3.model;

import java.util.Objects;

public abstract class Utente implements HasID {
    private String ID;
    private String username;
    private String password;
    private String email;
    private String telefono;
    private Indirizzo indirizzo;

    public Utente(String username, String password, String email) {
        IDGenerator.generateID(this);
        this.username = username;
        this.password = password;
        this.email = email;
        this.telefono = "";
        this.indirizzo = new Indirizzo("", 0, "", "", "", "");
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

    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void creaIndirizzo(String via, int numeroCivico, String citta, String CAP, String provincia, String stato) {
        this.indirizzo = new Indirizzo(via, numeroCivico, citta, CAP, provincia, stato);
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


    private class Indirizzo {
        private String via;
        private int numeroCivico;
        private String citta;
        private String CAP;
        private String provincia;
        private String stato;

        public Indirizzo(String via, int numeroCivico, String citta, String CAP, String provincia, String stato) {
            this.via = via;
            this.numeroCivico = numeroCivico;
            this.citta = citta;
            this.CAP = CAP;
            this.provincia = provincia;
            this.stato = stato;
        }

        public String getVia() {
            return via;
        }

        public void setVia(String via) {
            this.via = via;
        }

        public int getNumeroCivico() {
            return numeroCivico;
        }

        public void setNumeroCivico(int numeroCivico) {
            this.numeroCivico = numeroCivico;
        }

        public String getCitta() {
            return citta;
        }

        public void setCitta(String citta) {
            this.citta = citta;
        }

        public String getCAP() {
            return CAP;
        }

        public void setCAP(String CAP) {
            this.CAP = CAP;
        }

        public String getProvincia() {
            return provincia;
        }

        public void setProvincia(String provincia) {
            this.provincia = provincia;
        }

        public String getStato() {
            return stato;
        }

        public void setStato(String stato) {
            this.stato = stato;
        }
    }
}