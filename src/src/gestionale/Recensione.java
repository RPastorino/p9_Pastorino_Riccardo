package gestionale;

/** 
 * Classe Bibliotecario ( eredita da Utente )
 * 
 * @author Riccardo Pastorino
 * @version 1.0
 * 
 */
public class Recensione {
	/** Associazione fra Recensione e UtenteRegistrato */
    private UtenteRegistrato autore;
    
    /** testo della recensione */
    private String testo;
    
    /**
     * Costruttore di default di Recensione
     * 
	 */
	public Recensione() {
		super();
	}
    
    /**
     * Costruttore di Recensione
     * 
	 * @param testo testo della recensione
	 * @param autore autore della recensione
	 */
	public Recensione(UtenteRegistrato autore, String testo){
		super();
		this.autore = autore;
		this.testo = testo;
	}

	/**
	 * Gets testo della recensione
	 *
	 * @return testo
	 */
	public String getTesto(){
		return testo;
	}

	/**
	 * Sets testo della recensione
	 *
	 * @param testo testo della recensione
	 */
	public void setTesto(String testo){
		this.testo = testo;
	}

	/**
	 * Gets autore (UtenteRegistrato) della recensione
	 *
	 * @return autore
	 */
	public UtenteRegistrato getAutore(){
		return autore;
	}
	
	/**
	 * Sets autore della recensione
	 *
	 * @param autore autore della recensione
	 */
	public void setAutore(UtenteRegistrato autore){
		this.autore = autore;
	}

}
