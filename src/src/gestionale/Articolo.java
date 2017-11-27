package gestionale;

import java.util.List;
import java.util.ArrayList; 

/** 
 * Classe Articolo ( ereditata da Libro e documento digitale )
 * 
 * @author Riccardo Pastorino
 * @version 1.0
 * 
 */
public abstract class Articolo {
	 /** Titolo dell'articolo */
    private String titolo;
    
    /** Autore dell'articolo */
    private String autore;
    
    /** Genere dell'articolo */
    private String genere;
    
    /** Numero pagine del libro */
    private int pagine;
    
    /** Associazione tra Articolo e Recensione */
    private List<Recensione> recensioni;
        
    /**
     * Costruttore di Articolo
     *
     * @param titolo titolo dell'articolo
     * @param autore autore dell'articolo
     * @param genere genere dell'articolo
     */
    public Articolo(String titolo, String autore, String genere, int pagine){
		super();
		this.titolo = titolo;
		this.autore = autore;
		this.genere = genere;
		this.pagine = pagine;
		recensioni = new ArrayList<Recensione>();
	}
    
	/**
     * Ricerca articolo attraverso una stringa,ritorna true se il titolo, 
     * autore o genere contengono la stringa, false altrimenti
     *
     * @param arg stringa di ricerca dell'articolo
     * @return boolean 
     */
    public boolean checkCampiArticolo( String arg ){
		if ((this.titolo.contains(arg)) || (this.autore.contains(arg)) || (this.genere.contains(arg))){
			return true;
		}
		return false;
    }
   
    
    /**
     * scrittura e conseguente inserimento di una nuova Recensione da parte di un
     * Utente Registrato
     *
     * @param autore autore recensione
     * @param testo testo recensione
     * @return boolean
     */
    public boolean scriviRecensione( String testo, UtenteRegistrato autore){
    	if (testo.length() > 50){
    		System.out.println("Attenzione, Riprovare non superando i 50 caratteri.");
    		return false;
    	}	
    	Recensione r = new Recensione();
    	r.setAutore(autore);
    	r.setTesto(testo);
    	inserisciRecensione(r);
    	System.out.println("Recensione scritta");
    	return true;
    }
    
    /**
     * Inserimento di una recensione
     *
     * @param recensione recensione contiene la recensione e l'autore
     */
    public void inserisciRecensione( Recensione recensione ){
    	/* se l'autore ha gia' prodotto una recensione, quest'ultima viene sovrascritta */
    	List<Recensione> recensioniDaRimuovere = new ArrayList<Recensione>();
    	for(Recensione r: recensioni){
    	    if (r.getAutore().equals(recensione.getAutore())) {
    	        recensioniDaRimuovere.add(r);
    	    }
    	}
    	recensioni.removeAll(recensioniDaRimuovere);
    	recensioni.add(recensione);
    }
    
    /**
     * Rimozione di una recensione di un utente
     *
     * @param autore autore della recensione da rimuovere
     * @return boolean true se la rimozione e' avvenuta correttamente
     * altrimenti false
     */
    public boolean rimuoviRecensione( UtenteRegistrato autore ) {
    	for( Recensione r: recensioni ) {
    	    if (r.getAutore().equals(autore)) {
    	        return recensioni.remove(r);
    	    }
    	}
    	return false;
    }
    
    /**
	 * Gets titolo
	 *
	 * @return titolo
	 */
	public String getTitolo(){
		return titolo;
	}
	
	/**
	 * Sets titolo
	 *
	 * @param titolo titolo dell'articolo 
	 */
	public void setTitolo(String titolo){
		this.titolo = titolo;
	}
	
	/**
	 * Gets autore
	 *
	 * @return autore
	 */
	public String getAutore(){
		return autore;
	}
	
	/**
	 * Sets autore
	 *
	 * @param autore autore dell'articolo
	 */
	public void setAutore(String autore){
		this.autore = autore;
	}
	
	/**
	 * Gets genere
	 *
	 * @return String
	 */
	public String getGenere(){
		return genere;
	}
	
	/**
	 * Sets genere
	 *
	 * @param genere genere dell'articolo
	 */
	public void setGenere(String genere){
		this.genere = genere;
	}
	
	
	
	/**
	 * Gets lista recensioni
	 *
	 * @return recensioni
	 */
	public List<Recensione> getRecensioni(){
		return recensioni;
	}
	
	/**
	 * Gets numero di pagine
	 *
	 * @return pagine
	 */
	public int getPagine(){
		return pagine;
	}
	
	/**
	 * Sets numero pagine.
	 *
	 * @param pagine numero pagine
	 */
	public void setPagine(int pagine) {
		this.pagine = pagine;
	} 
}
