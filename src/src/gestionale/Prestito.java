package gestionale;

import java.util.Calendar;
import java.util.Date;

/** 
 * Classe Prestito 
 * 
 * @author Riccardo Pastorino
 * @version 1.0
 * 
 */
public class Prestito {
	/** Associazione tra Prestito e Articolo */
    private Libro libro;
    
    /** Associazione tra Prestito e UtenteRegistrato */
    private UtenteRegistrato richiedente;
    
    /** Data di inizio del prestito */
    private Date dataInizio;
    
    /** Data di fine del prestito */
    private Date dataFine;
    
    /** Numero di rinnovi rimasti */
    private int rinnovi;
    
    /**
     * Costruttore di Prestito.
     * 
	 * @param dataInizio data di inizio del prestito
	 * @param dataFine data di fine del prestito
	 * @param libro libro preso in prestito
	 * @param richiestoDa UtenteRegistrato che prende prestito
	 */
	public Prestito(Libro libro, UtenteRegistrato richiedente, Date dataInizio){
		super();
		this.libro = libro;
		this.richiedente = richiedente;
		this.dataInizio = dataInizio;
		Calendar dataAttuale = Calendar.getInstance();
		dataAttuale.setTime(dataInizio);
		dataAttuale.add(Calendar.MONTH, 2); // Il prestito ha una durata di massimo 2 mesi
		this.dataFine = dataAttuale.getTime();
		this.rinnovi = 2;
		
	}

	/**
     * Prolungare il prestito. Se possibile, viene inserita la nuova data
     * di riconsegna, altrimenti viene ritornato falso.
     * 
     * @param dataRiconsegna data di riconsegna del libro
     * @return boolean
     */
    public boolean estendiPrestito( ){
    	// estensione prestito di massimo 2 mesi a partire dalla data di rinnovo
    	if (this.rinnovi > 0) {
    		Calendar dataAttuale = Calendar.getInstance();
    		dataAttuale.setTime(dataInizio);
    		dataAttuale.add(Calendar.MONTH, 2); 
    		this.dataFine = dataAttuale.getTime();
    		this.rinnovi--;
    		return true;
    	}
    	System.out.println("Il prestito non e' piu' estendibile");
    	return false;
    }

	/**
	 * Gets numero rinnovi rimanenti
	 * 
	 * @return rinnovi
	 */
	public int getRinnovi(){
		return rinnovi;
	}

	/**
	 * Gets data di inizio
	 * 
	 * @return dataInizio
	 */
	public Date getDataInizio(){
		return dataInizio;
	}

	/**
	 * Gets data di fine
	 * 
	 * @return dataFine
	 */
	public Date getDataFine(){
		return dataFine;
	}

	/**
	 * Gets articolo in prestito
	 * 
	 * @return articolo
	 */
	public Libro getLibro(){
		return libro;
	}

	/**
	 * Gets utente che ha il libro in prestito
	 * 
	 * @return richiedente
	 */
	public UtenteRegistrato getRichiedente(){
		return richiedente;
	}   

}
