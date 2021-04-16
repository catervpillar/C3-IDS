package it.unicam.cs.ids.c3.utenti.corriere;

public interface CorriereInterface {
    String getRagioneSociale();

    void setRagioneSociale(String ragioneSociale);

    StatoCorriere getStato();

    void setStato(StatoCorriere stato);
}
