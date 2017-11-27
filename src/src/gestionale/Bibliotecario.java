package gestionale;

import java.util.Date;

/** 
 * Classe Bibliotecario ( eredita da Utente )
 * 
 * @author Riccardo Pastorino
 * @version 1.0
 * 
 */
public class Bibliotecario extends Utente{
	/** Associazione tra Bibliotecario e Biblioteca */
    private Biblioteca sedeBiblio;
    
    /**
     * Costruttore di Bibliotecario, chiama il costruttore della
     * superclasse Utente
     * 
     * @param nome nome del bibliotecario
     * @param cognome cognome del bibliotecario
     * @param indirizzo indirizzo del bibliotecario.
     * @param dataNascita data di nascita del bibliotecario
     * @param codiceFiscale codice fiscale del bibliotecario
     * @param telefono numero di telefono del bibliotecario
     * @param email email del bibliotecario, usata per il login
     * @param password password del bibliotecario, usata per il login
     * @param biblioteca sede del bibliotecario
     */
    public Bibliotecario(String codiceFiscale, String nome, String cognome, String indirizzo, 
    		Date dataNascita, String telefono, String email, String password, Biblioteca biblioteca){
		super(codiceFiscale, nome, cognome, indirizzo, dataNascita, 
				telefono, email, password);
		this.setSedeBiblio(biblioteca);
	}
    
    

    
    /**
	 * Gets sede bibliotecario
	 * 
	 * @return sedeBiblio
	 */
	public Biblioteca getSedeBiblio(){
		return sedeBiblio;
	}

	/**
	 * Sets sede bibliotecario
	 * 
	 * @param sedeBiblio sede del bibliotecario
	 */
	public void setSedeBiblio(Biblioteca sedeBiblio){
		this.sedeBiblio = sedeBiblio;
	}
}
