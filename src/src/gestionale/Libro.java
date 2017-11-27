package gestionale;

import java.util.ArrayList;
import java.util.List;

/** 
 * Classe Libro ( eredita da Articolo )
 * 
 * @author Riccardo Pastorino
 * @version 1.0
 * 
 */
public class Libro extends Articolo{
	/** Collocazione all'interno della biblioteca del Libro */
    private String settore;
    
    /** Associazione tra Libro e Biblioteca di appartenenza */
    private Biblioteca biblio;
    
    /** 
     * Associazione tra Libro e UtenteRegistrato per
     * la gestione delle prenotazioni
     */
    private List<UtenteRegistrato> prenotazioni;
    
    /** 
     * Associazione fra Libro e Prestito per la gestione 
     * dei prestiti
     */
    private Prestito prestito;
    
    /** ISBN del libro */
    private String isbn;
    
    /** Casa editrice del libro */
    private String casaEditrice;
    
    
    /**
     * Costruttore di Libro. Richiama il costruttore di
     * Articolo passandogli i parametri necessari
     *
     * @param titolo titolo del libro
     * @param autore autore del libro
     * @param genere genere del libro
     * @param collocazione collocazione nella biblioteca del libro
     * @param biblio biblioteca di riferimento del libro
     * @param isbn ISBN del libro
     * @param casaEditrice casa editrice del libro
     * @param pagine pagine del libro
     * 
     */
	public Libro(String titolo, String autore, String genere, 
			String settore, Biblioteca biblio, String isbn,
			String casaEditrice, int pagine) {
		super(titolo, autore, genere, pagine);
		this.settore = settore;
		this.biblio = biblio;
		this.isbn = isbn;
		this.casaEditrice = casaEditrice;
		prenotazioni = new ArrayList<UtenteRegistrato>();
		
	}
    
    
    public boolean isPrenotato( Utente autore ){
    	if (this.prenotazioni.contains(autore)) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Prenotazione articolo
     * 
     * @param utente utente prenotante
     */
    public void prenota(UtenteRegistrato utente){
    	prenotazioni.add(utente);
    }
    
    /**
     * Rimuovi prenotazione articolo
     * 
     * @param utente utente che rimuove prenotazione
     */
    public void rimuoviPrenotazione(UtenteRegistrato utente){
    	prenotazioni.remove(utente);
    }
    
    /**
	 * Gets settore
	 *
	 * @return settore
	 */
	public String getSettore(){
		return settore;
	}
	
	/**
	 * Sets settore
	 *
	 * @param settore settore dell'articolo
	 */
	public void setSettore(String settore){
		this.settore = settore;
	}
	
	/**
	 * Gets biblioteca di riferimento
	 *
	 * @return biblio
	 */
	public Biblioteca getBiblio(){
		return biblio;
	}
	
	/**
	 * Sets biblioteca di riferimento
	 *
	 * @param biblio biblioteca di riferimento
	 */
	public void setBiblio(Biblioteca biblio){
		this.biblio = biblio;
	}
	
	/**
	 * Gets lista prenotazioni
	 *
	 * @return prenotato
	 */
	public List<UtenteRegistrato> getPrenotato(){
		return prenotazioni;
	}
	
	/**
	 * Gets prestito
	 *
	 * @return prestito
	 */
	public Prestito getPrestito(){
		return prestito;
	} 
	
	/**
	 * Sets prestito
	 *
	 * @param prestito oggetto prestito
	 */
	public void setPrestito(Prestito prestito){
		this.prestito = prestito;
	}   
   
    /**
	 * Gets isbn
	 *
	 * @return isbn
	 */
	public String getIsbn(){
		return this.isbn;
	}
	
	/**
	 * Sets isbn
	 *
	 * @param isbn isbn del libro
	 */
	public void setIsbn(String isbn){
		this.isbn = isbn;
	}
	
	/**
	 * Gets casa editrice
	 *
	 * @return casaEditrice
	 */
	public String getCasaEditrice(){
		return casaEditrice;
	}
	
	/**
	 * Sets casa editrice
	 *
	 * @param casaEditrice casa editrice del libro
	 */
	public void setCasaEditrice(String casaEditrice){
		this.casaEditrice = casaEditrice;
	}
}
