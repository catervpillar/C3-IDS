package it.unicam.cs.ids.c3.services;

import it.unicam.cs.ids.c3.controller.ControllerCliente;
import it.unicam.cs.ids.c3.controller.ControllerCommerciante;
import it.unicam.cs.ids.c3.controller.ControllerCorriere;
import it.unicam.cs.ids.c3.controller.ControllerPuntoRitiro;
import it.unicam.cs.ids.c3.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Deserializer {
    private static Deserializer instance;

    private Deserializer() {
    }

    public static Deserializer getInstance() {
        if (Objects.isNull(instance))
            instance = new Deserializer();
        return instance;
    }

    public List<Prodotto> deserializzaProdotti(ResultSet resultSet) throws SQLException {
        List<Prodotto> listaProdotti = new ArrayList<>();

        while (resultSet.next()) {
            Prodotto prodotto = new Prodotto(resultSet.getString("ID"),
                    resultSet.getString("nome"),
                    resultSet.getDouble("prezzo"),
                    resultSet.getInt("quantita"),
                    resultSet.getString("commerciante_ID"),
                    resultSet.getString("URL_immagine"));
            listaProdotti.add(prodotto);
        }

        DBManager.getInstance().disconnect(resultSet);
        return listaProdotti;
    }

    public List<String> deserializzaIDProdotti(ResultSet resultSet) throws SQLException {
        List<String> listaIDProdotti = new ArrayList<>();

        while (resultSet.next()) {
            listaIDProdotti.add(resultSet.getString("prodottoInVendita_ID"));
        }

        DBManager.getInstance().disconnect(resultSet);
        return listaIDProdotti;
    }

    public Cliente deserializzaCliente(ResultSet resultSet) throws SQLException {
        Cliente cliente = new Cliente(resultSet.getString("ID"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("email"),
                resultSet.getString("nome"),
                resultSet.getString("cognome"));
        cliente.setTelefono(resultSet.getString("telefono"));
        cliente.setIndirizzo(resultSet.getString("indirizzo"));

        DBManager.getInstance().disconnect(resultSet);
        return cliente;
    }

    public List<Commerciante> deserializzaCommercianti(ResultSet resultSet) throws SQLException {
        List<Commerciante> listaCommercianti = new ArrayList<>();
        while (resultSet.next()) {
            Commerciante commerciante = new Commerciante(resultSet.getString("ID"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    resultSet.getString("ragioneSociale"));
            commerciante.setTelefono(resultSet.getString("telefono"));
            commerciante.setIndirizzo(resultSet.getString("indirizzo"));
            listaCommercianti.add(commerciante);
        }

        DBManager.getInstance().disconnect(resultSet);
        return listaCommercianti;
    }

    public List<Corriere> deserializzaCorrieri(ResultSet resultSet) throws SQLException {
        List<Corriere> listaCorriere = new ArrayList<>();
        while (resultSet.next()) {
            Corriere corriere = new Corriere(resultSet.getString("ID"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    resultSet.getString("ragioneSociale"));
            corriere.setTelefono(resultSet.getString("telefono"));
            corriere.setIndirizzo(resultSet.getString("indirizzo"));
            corriere.setStato(StatoCorriere.valueOf(resultSet.getString("stato")));
            listaCorriere.add(corriere);
        }

        DBManager.getInstance().disconnect(resultSet);
        return listaCorriere;
    }

    public List<PuntoRitiro> deserializzaPuntiRitiro(ResultSet resultSet) throws SQLException {
        List<PuntoRitiro> listaPuntiRitiro = new ArrayList<>();

        while (resultSet.next()) {
            PuntoRitiro puntoRitiro = new PuntoRitiro(resultSet.getString("ID"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    resultSet.getString("ragione_sociale"));
            puntoRitiro.setTelefono(resultSet.getString("telefono"));
            puntoRitiro.setIndirizzo(resultSet.getString("indirizzo"));
            listaPuntiRitiro.add(puntoRitiro);
        }

        DBManager.getInstance().disconnect(resultSet);
        return listaPuntiRitiro;
    }


    public List<? extends Promozione> deserializzaPromozioni(ResultSet resultSet) throws SQLException {
        List<Promozione> listaPromozioni = new ArrayList<>();

        while (resultSet.next()) {
            GregorianCalendar dataInizio = new GregorianCalendar();
            dataInizio.setTime(resultSet.getDate("data_inizio"));
            GregorianCalendar dataScadenza = new GregorianCalendar();
            dataScadenza.setTime(resultSet.getDate("data_scadenza"));

            Promozione promozione = new Promozione(resultSet.getString("ID"),
                    resultSet.getString("nome"),
                    resultSet.getString("commerciante_ID"),
                    resultSet.getString("descrizione"),
                    dataInizio,
                    dataScadenza);

            ResultSet resultSet1 = DBManager.getInstance().executeQuery("select * from promozione_has_prodotto where promozione_ID = \""
                    + promozione.getID() + "\"");
            while (resultSet1.next()) {
                promozione.getListaIDProdotti().add(resultSet1.getString("prodottoInVendita_ID"));
            }
            listaPromozioni.add(promozione);
        }

        DBManager.getInstance().disconnect(resultSet);
        return listaPromozioni;
    }

    public List<? extends Recensione> deserializzaRecensioni(ResultSet resultSet) throws SQLException {
        List<Recensione> listaRecensioni = new ArrayList<>();

        while (resultSet.next()) {
            Recensione recensione = new Recensione(resultSet.getString("ID"),
                    resultSet.getString("titolo"),
                    resultSet.getString("testo"),
                    resultSet.getString("cliente_ID"),
                    resultSet.getString("commerciante_ID"),
                    resultSet.getString("prodottoInVendita_ID"),
                    VotoRecensioni.valueOf(resultSet.getString("voto_recensione")));
            listaRecensioni.add(recensione);
        }

        DBManager.getInstance().disconnect(resultSet);
        return listaRecensioni;
    }

    public List<? extends Ritiro> deserializzaRitiri(ResultSet resultSet) throws SQLException {
        List<Ritiro> listaRitiri = new ArrayList<>();

        while (resultSet.next()) {
            GregorianCalendar dataPrenotazione = new GregorianCalendar();
            dataPrenotazione.setTime(resultSet.getDate("data_prenotazione"));
            GregorianCalendar dataConsegna = new GregorianCalendar();
            if (!Objects.isNull(resultSet.getDate("data_consegna")))
                dataConsegna.setTime(resultSet.getDate("data_consegna"));
            else
                dataConsegna = null;

            Ritiro ritiro = new Ritiro(resultSet.getString("ID"),
                    resultSet.getString("commerciante_ID"),
                    resultSet.getString("cliente_ID"),
                    resultSet.getString("corriere_ID"),
                    resultSet.getString("destinazione"),
                    TipoConsegna.valueOf(resultSet.getString("tipo_consegna")));
            ritiro.setCodiceRitiro(resultSet.getString("codice_ritiro"));
            ritiro.setRitirato(resultSet.getBoolean("ritirato"));
            ritiro.setStatoTracking(StatoTracking.valueOf(resultSet.getString("stato_tracking")));
            ritiro.setDataPrenotazione(dataPrenotazione);
            ritiro.setDataConsegna(dataConsegna);

            listaRitiri.add(ritiro);
        }

        DBManager.getInstance().disconnect(resultSet);
        return listaRitiri;
    }

    public String cercaUtente(String username, String password) throws SQLException {
        if (ControllerCliente.getInstance().loginCliente(username, password))
            return "cliente";
        else if (ControllerCommerciante.getInstance().loginCommerciante(username, password))
            return "commerciante";
        else if (ControllerCorriere.getInstance().loginCorriere(username, password))
            return "corriere";
        else if (ControllerPuntoRitiro.getInstance().loginPuntoRitiro(username, password))
            return "punto_ritiro";
        else throw new IllegalArgumentException("CREDENZIALI ERRATE");
    }
}
