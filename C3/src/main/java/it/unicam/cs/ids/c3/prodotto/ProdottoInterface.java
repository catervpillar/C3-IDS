package it.unicam.cs.ids.c3.prodotto;
import it.unicam.cs.ids.c3.utenti.commerciante.Commerciante;

public interface ProdottoInterface {
    /**
     * Metodo getter per l'attributo ID.
     *
     * @return l'attributo ID.
     */
    String getID();

    /**
     * Metodo setter per l'attributo ID.
     *
     * @param ID - Il nuovo attributo ID.
     */
    void setID(String ID);

    /**
     * Metodo getter per l'attributo nome.
     *
     * @return l'attributo nome.
     */
    String getNome();

    /**
     * Metodo setter per l'attributo nome.
     *
     * @param nome - Il nuovo attributo nome.
     */
    void setNome(String nome);

    /**
     * Metodo getter per l'attributo ID del {@link Commerciante}.
     *
     * @return l'attributo ID del {@link Commerciante}.
     */
    String getIDCommerciante();

    /**
     * Metodo setter per l'attributo ID del {@link Commerciante}.
     *
     * @param IDCommerciante - Il nuovo attributo ID del {@link Commerciante}.
     */
    void setIDCommerciante(String IDCommerciante);

    /**
     * Metodo getter per l'attributo prezzo.
     *
     * @return l'attributo prezzo.
     */
    double getPrezzo();

    /**
     * Metodo setter per l'attributo prezzo.
     *
     * @param prezzo - Il nuovo attributo prezzo.
     */
    void setPrezzo(double prezzo);

    /**
     * Metodo getter per l'attributo quantita.
     *
     * @return l'attributo quantita.
     */
    int getQuantita();

    /**
     * Metodo setter per l'attributo quantita.
     *
     * @param quantita - Il nuovo attributo quantita.
     */
    void setQuantita(int quantita);

    /**
     * Metodo getter per l'attributo URL dell'immagine.
     *
     * @return l'attributo URL dell'immagine.
     */
    String getURLImmagine();

    /**
     * Metodo setter per l'attributo URL dell'immagine.
     *
     * @param URLImmagine - Il nuovo attributo URL dell'immagine.
     */
    void setURLImmagine(String URLImmagine);
}
