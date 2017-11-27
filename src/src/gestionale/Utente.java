package gestionale;

import java.util.Date;

/** 
 * Classe astratta Utente ( ereditata da UtenteRegistrato, Bibliotecario e ManagerSistema )
 * 
 * @author Riccardo Pastorino
 * @version 1.0
 * 
 */
public abstract class Utente {
	/** Codice Fiscale della persona */
    private String codiceFiscale;
    
    /** Nome della persona */
    private String nome;
    
    /** Cognome della persona */
    private String cognome;
    
    /** Indirizzo della persona */
    private String indirizzo;
    
    /** Data di nascita della persona */
    private Date dataNascita;
    
    /** Numero di telefono della persona */
    private String telefono;
    
    /** Email della persona, usata per il login */
    private String email;
    
    /** Password della persona, usata per il login */
    private String password;
    
    /**
	 * Costruttore Utente.
	 *
	 * @param codiceFiscale codice fiscale dell'utente 
	 * @param nome nome dell'utente
	 * @param cognome cognome dell'utente
	 * @param indirizzo indirizzo dell'utente
	 * @param dataNascita data di nascita dell'utente
	 * @param telefono numero di telefono dell'utente
	 * @param email indirizzo email dell'utente
	 * @param password password dell'utente
	 */
	public Utente(String codiceFiscale, String nome, String cognome, String indirizzo, 
			Date dataNascita, String telefono, 
			String email, String password) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.dataNascita = dataNascita;
		this.codiceFiscale = codiceFiscale;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
	} 
	
	 /**
     * Ricerca di una persona a partire da una stringa
     *
     * @param arg stringa di ricerca
     * @return boolean
     */
    public boolean checkAnagrafica( String txt ){
		if (nome.contains(txt) || cognome.contains(txt) || codiceFiscale.equals(txt) || email.equals(txt)){
			return true;
		}
		return false;
    }
    
    /**
	 * Gets codice fiscale
	 *
	 * @return codiceFiscale
	 */
	public String getCodiceFiscale(){
		return codiceFiscale;
	}

	/**
	 * Sets codice fiscale
	 *
	 * @param codiceFiscale codice fiscale della persona
	 */
	public void setCodiceFiscale(String codiceFiscale){
		this.codiceFiscale = codiceFiscale;
	}
    
	/**
	 * Gets nome
	 *
	 * @return nome
	 */
	public String getNome(){
		return nome;
	}

	/**
	 * Sets nome
	 *
	 * @param nome nome della persona
	 */
	public void setNome(String nome){
		this.nome = nome;
	}

	/**
	 * Gets cognome
	 *
	 * @return cognome
	 */
	public String getCognome(){
		return cognome;
	}

	/**
	 * Sets cognome
	 *
	 * @param cognome cognome della persona
	 */
	public void setCognome(String cognome){
		this.cognome = cognome;
	}

	/**
	 * Gets indirizzo
	 *
	 * @return indirizzo
	 */
	public String getIndirizzo(){
		return indirizzo;
	}

	/**
	 * Sets indirizzo
	 *
	 * @param indirizzo indirizzo della persona
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * Gets data di nascita
	 *
	 * @return dataNascita
	 */
	public Date getDataNascita(){
		return dataNascita;
	}

	/**
	 * Sets data di nascita
	 *
	 * @param dataNascita data di nascita della persona
	 */
	public void setDataNascita(Date dataNascita){
		this.dataNascita = dataNascita;
	}

	/**
	 * Gets numero di telefono
	 *
	 * @return telefono
	 */
	public String getTelefono(){
		return telefono;
	}

	/**
	 * Sets numero di telefono
	 *
	 * @param telefono numero di telefono della persona
	 */
	public void setTelefono(String telefono){
		this.telefono = telefono;
	}

	/**
	 * Gets email
	 *
	 * @return email
	 */
	public String getEmail(){
		return email;
	}

	/**
	 * Sets email
	 *
	 * @param email email della persona
	 */
	public void setEmail(String email){
		this.email = email;
	}

	/**
	 * Gets password
	 *
	 * @return password
	 */
	public String getPassword(){
		return password;
	}

	/**
	 * Sets password
	 *
	 * @param password password della persona
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
