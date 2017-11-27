package gestionale;

import java.util.Date;
import java.util.List;
import java.util.ArrayList; 

/** 
 * Classe UtenteRegistrato ( eredita da Utente )
 * 
 * @author Riccardo Pastorino
 * @version 1.0
 * 
 */
public class UtenteRegistrato extends Utente{
	/** Associazione tra UtenteRegistrato e Abbonamento */
	private Abbonamento abbonamento;
	
	/** Associazione tra UtenteRegistrato e Prestito */
    private List<Prestito> prestiti;
    
    /** Associazione tra UtenteRegistrato e Articolo */
    private List<Articolo> prenotazioni;
    
    /**
     * Costruttore di UtenteRegistrato. chiama il costruttore della 
     * superclasse Utente
     *
     * @param nome nome dell'utente
     * @param cognome cognome dell'utente
     * @param indirizzo indirizzo dell'utente.
     * @param dataNascita data di nascita dell'utente
     * @param codiceFiscale codice fiscale dell'utente
     * @param telefono numero di telefono dell'utente
     * @param email email dell'utente, usata per il login
     * @param password password dell'utente, usata per il login
     */
    public UtenteRegistrato(String codiceFiscale, String nome, String cognome, String indirizzo, 
    		Date dataNascita,  String telefono, 
    		String email, String password) {
		super(codiceFiscale, nome, cognome, indirizzo, dataNascita, 
				telefono, email, password);
		prestiti = new ArrayList<Prestito>();
		prenotazioni = new ArrayList<Articolo>();
	}
    
    /**
     * sottoscriviAbbonamento. 
     *
     * @param tipoAbb tipo abbonamento tra i 3 disponibili( giornaliero, mensile, annuale )
     */
    public boolean sottoscriviAbbonamento( Abbonamento.TipoAbbonamento tipoAbb ){
    	if( this.isAbbonato() ){
    		System.out.println("Abbonamento gia' sottoscritto");
    		return false;
    	}
    	else{
    		this.abbonamento = new Abbonamento(tipoAbb, this);
    		return true;
    	}
    }
    
    /**
     * isAbbonato, controlla se utente e' abbonato, ritorna true se l'abbonamento risulta valido
     * altrimenti elimina abbonamento scaduto e ritorna false
     * 
     * @return boolean
     *
     */
    public boolean isAbbonato(){
    	if( this.abbonamento != null ){
    		boolean valido = abbonamento.isValid();
    		if( valido ){
    			return valido;
    		}
    		else{
    			this.annullaAbbonamento();
    			System.out.println("Abbonamento scaduto rimosso");
    			return valido;
    		}
    	}
    	return false;
    }
    
    /**
     * annullaAbbonamento
     * 
     */
    public void annullaAbbonamento(){
    	this.abbonamento = null;
    }
    
    /**
     * effettua prenotazione. Ritorna false se
     * prenotazione  gia presente, altrimenti true
     *
     * @param libro libro da prenotare
     * @return boolean
     */
    public boolean prenotaLibro( Libro libro ){
    	if (prenotazioni.contains(libro)) {
    		return false;
    	}
    	prenotazioni.add(libro);
    	libro.prenota(this);
    	System.out.print("Articolo:" + libro.getTitolo() + " ");
    	System.out.println("prenotato a nome di: " + this.getNome() + " " + this.getCognome());
    	return true;
    }
    
    /**
     * Rimuove una prenotazione effettuata. Ritorna false se
     * articolo non e' presente in prenotazioni, altrimenti true
     *
     * @param libro libro prenotato da rimuovere
     * @return boolean
     */
    public boolean rimuoviPrenotazione ( Libro libro ){
    	if (prenotazioni.contains(libro)){
    		prenotazioni.remove(libro);
    		libro.rimuoviPrenotazione(this);
        	System.out.println("Prenotazione eliminata");
    		return true;
    	}
    	else
    		return false;
    }

	/**
	 * Gets lista prestiti
	 * 
	 * @return prestiti
	 */
	public List<Prestito> getPrestiti(){
		return this.prestiti;
	}

	/**
	 * Gets lista prenotazioni
	 * 
	 * @return prenotazione
	 */
	public List<Articolo> getPrenotazioni(){
		return this.prenotazioni;
	} 
	
	/**
	 * Gets Abbonamento
	 * 
	 * @return prestiti
	 */
	public Abbonamento getAbbonamento(){
		return this.abbonamento;
	}	
}

