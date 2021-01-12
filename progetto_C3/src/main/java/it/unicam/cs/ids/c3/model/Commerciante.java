package it.unicam.cs.ids.c3.model;

import it.unicam.cs.ids.c3.utilities.Controllore;

import javax.naming.ldap.Control;
import java.util.List;
import java.util.Objects;

public class Commerciante extends Utente {
    private String ragioneSociale;
    private String indirizzo;
    private List<CategoriaProdotto> listaCategorie;

    public Commerciante(String username, String password, String email, String telephone, String ragioneSociale, String indirizzo, List<CategoriaProdotto> listaCategorie) {
        super(username, password, email, telephone);
        this.ragioneSociale = ragioneSociale;
        this.indirizzo = indirizzo;
        this.listaCategorie = listaCategorie;
        this.indirizzo = indirizzo;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        Controllore.getInstance().controllaStringa(ragioneSociale);
        this.ragioneSociale = ragioneSociale;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        Controllore.getInstance().controllaIndirizzo(indirizzo);
        this.indirizzo = indirizzo;
    }

    public List<CategoriaProdotto> getListaCategorie() {
        return listaCategorie;
    }

    public void setListaCategorie(List<CategoriaProdotto> listaCategorie) {
        Controllore.getInstance().controllaLista(listaCategorie);
        this.listaCategorie = listaCategorie;
    }


}