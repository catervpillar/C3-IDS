package it.unicam.cs.ids.c3.utenti.puntoRitiro;

public interface PuntoRitiroInterface {
    String getID();

    void setID(String ID);

    String getUsername();

    void setUsername(String username);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    String getTelefono();

    void setTelefono(String telefono);

    String getIndirizzo();

    void setIndirizzo(String indirizzo);

    String getRagioneSociale();

    void setRagioneSociale(String ragioneSociale);
}
