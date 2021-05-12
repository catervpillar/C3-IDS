package it.unicam.cs.ids.c3.utilities;

/**
 * Quest'interfaccia e' implementata da tutte le classi i cui oggetti sono identificati da un ID univoco.
 */
public interface HasID {
    /**
     * Metodo getter per l'attributo ID.
     *
     * @return l'ID dell'oggetto.
     */
    String getID();

    /**
     * Metodo setter per l'attributo ID.
     *
     * @param ID - Il nuovo ID.
     */
    void setID(String ID);
}
