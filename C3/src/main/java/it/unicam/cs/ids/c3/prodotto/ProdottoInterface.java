package it.unicam.cs.ids.c3.prodotto;

public interface ProdottoInterface {
     String getNome();

     void setNome(String nome);

     String getIDCommerciante();

     void setIDCommerciante(String IDCommerciante);

     double getPrezzo();

     void setPrezzo(double prezzo);

     int getQuantita();

     void setQuantita(int quantita);

     String getURLImmagine();

     void setURLImmagine(String URLImmagine);
}
