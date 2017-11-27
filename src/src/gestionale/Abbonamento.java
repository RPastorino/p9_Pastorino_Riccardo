package gestionale;

import java.util.Calendar;
import java.util.Date;

/** 
 * Classe Abbonamento
 * 
 * @author Riccardo Pastorino
 * @version 1.0
 * 
 */
public class Abbonamento {
	/** Tipo enumerativo per le 3 tipologie di abbonamento */
	public static enum TipoAbbonamento{ giornaliero, mensile, annuale};
	
	/** Tipologia di abbonamento scelta */
	private TipoAbbonamento periodoAbbonamento;
	
	/** giorni di valenza abbonamento*/
	private int giorniValenza;
	
	/** Data di inizio abbonamento */
	private Date dataInizio;
	
	/** Data di fine abbonamento */
    private Date dataFine;
    
    /** Associazione tra abbonamento e UtenteRegistrato */
    private UtenteRegistrato abbonato;
    
    /**
     * Costruttore di Abbonamento.
     * 
     * @param tipoAbbonamento tipologia dell'abbonamento
     * @param giorniValenza numero di giorni di valenza abbonamento
	 * @param dataInizio data di inizio del prestito
	 * @param dataFine data di fine del prestito
	 * @param abbonato UtenteRegistrato che sottoscrive abbonamento
	 */
    public Abbonamento( TipoAbbonamento tipoAbb, UtenteRegistrato utente ){
    	this.abbonato = utente;
    	if( tipoAbb.equals(TipoAbbonamento.giornaliero) ){
    		periodoAbbonamento = TipoAbbonamento.giornaliero;
    		giorniValenza = 1;
    		dataInizio = new Date();
    		Calendar data = Calendar.getInstance();
    		data.setTime(dataInizio);
    		data.add(Calendar.DATE, giorniValenza);
    		dataFine = data.getTime();
    		System.out.println("Abbonamento giornaliere sottoscritto");
    	}
    	if( tipoAbb.equals(TipoAbbonamento.mensile) ){
    		periodoAbbonamento = TipoAbbonamento.mensile;
    		giorniValenza = 30;
    		dataInizio = new Date();
    		Calendar data = Calendar.getInstance();
    		data.setTime(dataInizio);
    		data.add(Calendar.MONTH, 1);
    		dataFine = data.getTime();
    		System.out.println("Abbonamento mensile sottoscritto");
    	}
    	if( tipoAbb.equals(TipoAbbonamento.annuale) ){
    		periodoAbbonamento = TipoAbbonamento.annuale;
    		giorniValenza = 365;
    		dataInizio = new Date();
    		Calendar data = Calendar.getInstance();
    		data.setTime(dataInizio);
    		data.add(Calendar.YEAR, 1);
    		dataFine = data.getTime();
    		System.out.println("Abbonamento annuale sottoscritto");
    	}
    }
    
    /**
     * isValid
     *
     * @return boolean
     */
    public boolean isValid(){
    	Date dataOggi = new Date();
    	int comparazione = dataOggi.compareTo(dataFine);
    	if( comparazione > 0)
    		return false;
    	else
    		return true;  
    }
    
    /**
	 * Sets data di fine
	 * 
	 * @param dataFine data di fine abbonamento
	 */
    /* questo metodo e' stato aggiunto a fini di testing.
	 * La data di fine prestito e' settata automaticamente in base alla
	 * durante selezionata e questo metodo non dovrebbe essere chiamato 
	 * se non in fase di testing */
	public void setDataFine(Date dataFine){
		this.dataFine = dataFine;
	}
    
    /**
	 * Gets data di inizio
	 * 
	 * @return dataInizio
	 */
	public Date getDataInizio(){
		return this.dataInizio;
	}

	/**
	 * Gets data di fine 
	 * 
	 * @return dataFine
	 */
	public Date getDataFine(){
		return this.dataFine;
	}
    
	/**
	 * Gets periodo Abbonamento
	 * 
	 * @return periodoAbbonamento
	 */
	public TipoAbbonamento getPeriodoAbbonamento(){
		return this.periodoAbbonamento;
	}
	
	/**
	 * Gets giorni valenza
	 * 
	 * @return giorniValenza
	 */
	public int getGiorniValenza(){
		return giorniValenza;
	}
	
	/**
	 * Gets Abbonato
	 * 
	 * @return abbonato
	 */
	public Utente getAbbonato(){
		return this.abbonato;
	}
}
