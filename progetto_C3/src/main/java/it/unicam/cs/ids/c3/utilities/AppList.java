package it.unicam.cs.ids.c3.utilities;

import it.unicam.cs.ids.c3.model.*;

import java.util.List;

public final class AppList {

    //stringa connessiona al db
    List<Prodotto> prodotti;
    List<Commerciante> commercianti;
    List<CategoriaProdotto> categorieProdotti;
    List<Cliente> clienti;
    List<PuntoRitiro> puntiDiRitiro;
    List<Corriere> corrieri;
    List<Ritiro> ritiri;
    List<Promozione> promozioni;
    List<Tracking> trackings;

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public List<Commerciante> getCommercianti() {
        return commercianti;
    }

    public void setCommercianti(List<Commerciante> commercianti) {
        this.commercianti = commercianti;
    }

    public List<CategoriaProdotto> getCategorieProdotti() {
        return categorieProdotti;
    }

    public void setCategorieProdotti(List<CategoriaProdotto> categorieProdotti) {
        this.categorieProdotti = categorieProdotti;
    }


    public List<Cliente> getClienti() {
        return clienti;
    }

    public void setClienti(List<Cliente> clienti) {
        this.clienti = clienti;
    }

    public List<PuntoRitiro> getPuntiDiRitiro() {
        return puntiDiRitiro;
    }

    public void setPuntiDiRitiro(List<PuntoRitiro> puntiDiRitiro) {
        this.puntiDiRitiro = puntiDiRitiro;
    }

    public List<Corriere> getCorrieri() {
        return corrieri;
    }

    public void setCorrieri(List<Corriere> corrieri) {
        this.corrieri = corrieri;
    }

    public List<Ritiro> getRitiri() {
        return ritiri;
    }

    public void setRitiri(List<Ritiro> ritiri) {
        this.ritiri = ritiri;
    }

    public List<Promozione> getPromozioni() {
        return promozioni;
    }

    public void setPromozioni(List<Promozione> promozioni) {
        this.promozioni = promozioni;
    }

    public List<Tracking> getTrackings() {
        return trackings;
    }

    public void setTrackings(List<Tracking> trackings) {
        this.trackings = trackings;
    }
}
