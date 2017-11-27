package gestionale;

import java.util.Date;

/** 
 * Classe ManagerSistema ( eredita da Utente )
 * 
 * @author Riccardo Pastorino
 * @version 1.0
 * 
 */
public class ManagerSistema extends Utente{
	/** SBU Gestito */
	private Sbu sbuGestito;
	
	/**
     * Costruttore di ManagerSistema, chiama il costruttore della
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
     * @param sbu sbu gestito
     */
    public ManagerSistema(String codiceFiscale, String nome, String cognome, String indirizzo, 
    		Date dataNascita, String telefono, String email, String password, Sbu sbu ){
		super(codiceFiscale, nome, cognome, indirizzo, dataNascita, 
				telefono, email, password);
		sbu.setManager(this);
		this.sbuGestito = sbu;
	}
    
    /**
     * Registra bibliotecario
     * 
     * @param bibliotecario bibliotecario da registrare
     */
    public void registraBibliotecario( Bibliotecario b ){
    	this.sbuGestito.registraUtente(b);
    }

    
}
