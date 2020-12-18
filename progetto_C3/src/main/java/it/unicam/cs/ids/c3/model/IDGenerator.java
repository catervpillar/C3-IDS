package it.unicam.cs.ids.c3.model;

import it.unicam.cs.ids.c3.utilities.AppList;

import java.util.List;

public final class IDGenerator {

    private int IDCliente  = 1;
    private int IDCommerciante = 1;
    private int IDPuntoRitiro = 1;
    private int IDCorriere = 1;
    private int IDProdotto = 1;
    private int IDPromozione = 1;
    private int IDTracking = 1;
    private int IDRitiro = 1;
    private int IDCategoriaProdotto = 1;
    private int IDCategoriaPromozione = 1;
    private static IDGenerator idGenerator;

    private IDGenerator(){ }

    public static IDGenerator getInstance(){
        if (idGenerator==null)
            idGenerator = new IDGenerator();
        return idGenerator;
    }

    public int generateIDCommerciante(){
        return IDCommerciante++;
    }

    public int generateIDPuntoRitiro(){
        return IDPuntoRitiro++;
    }

    public int generateIDCliente(){
        return IDCliente++;
    }

    public int generateIDProdotto(){
        return IDProdotto++;
    }

    public int generateIDCorriere(){
        return IDCorriere++;
    }

    public int generateIDPromozione(){
        return IDPromozione++;
    }

    public int generateIDTracking(){
        return IDTracking++;
    }

    public int generateIDRitiro(){
        return IDRitiro++;
    }

    public int generateIDCategoriaProdotto(){
        return IDCategoriaProdotto++;
    }

    public int generateIDCategoriaPromozione(){
        return IDCategoriaPromozione++;
    }

    public void resetAll(){
         IDCliente  = 1;
         IDCommerciante = 1;
         IDPuntoRitiro = 1;
         IDCorriere = 1;
         IDProdotto = 1;
         IDPromozione = 1;
         IDTracking = 1;
         IDRitiro = 1;
         IDCategoriaProdotto = 1;
         IDCategoriaPromozione = 1;
    }

    private int getFixedID(List<? extends HasID> lista){

        return lista.stream().mapToInt(HasID::getID).max().orElse(0) + 1;
    }

    public void fixAll(AppList appList){

        IDCliente  = getFixedID(appList.getClienti());
        IDCommerciante = getFixedID(appList.getCommercianti());
        IDPuntoRitiro = getFixedID(appList.getPuntiDiRitiro());
        IDCorriere = getFixedID(appList.getCorrieri());
        IDProdotto = getFixedID(appList.getProdotti());
        IDPromozione = getFixedID(appList.getPromozioni());
        IDTracking = getFixedID(appList.getTrackings());
        IDRitiro = getFixedID(appList.getRitiri());
        IDCategoriaProdotto = getFixedID(appList.getCategorieProdotti());
    }

}
