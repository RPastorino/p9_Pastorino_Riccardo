package gestionale;

import java.util.List;
import java.util.ArrayList;

/** 
 * Classe Sbu ( acronimo di sistema bibliotecario urbano )
 * 
 * @author Riccardo Pastorino
 * @version 1.0
 * 
 */
public class Sbu {
	/** Istanza della classe per realizzazione singleton */
	private static Sbu instanceSbu;
	
	/** id del sistema */
    private String identificativo;
    
    /** Lista di acquisti consigliati dagli utenti ( sia registrati che non registrati ) */
    private List<Articolo> listaConsigliati;
    
    /** Manager del sistema ( supposte essere unico ) 
     * 
     * Associazione tra Sbu e manager di sistema
     * 
     * */
    private Utente manager;
    
    /** Associazione tra Sbu e Recensione */
    private List<Recensione> recensioni;
    
    /** Associazione tra Sbu e Biblioteca */
    private List<Biblioteca> biblioteche;
    
    /** Associazione tra Sbu e utenti registrati */
    private List<Utente> utentiRegistrati;
    
    /** Associazione tra Sbu e bibliotecari */
    private List<Utente> bibliotecari;
    
    /**
     * Costruttore di Sbu, il costruttore e' settato su private per utilizzare il Singleton
     *
     * @param nome nome dell'SBU
     */
	private Sbu(String id){
		super();
		this.setId(id);
		this.listaConsigliati = new ArrayList<Articolo>();
		this.recensioni = new ArrayList<Recensione>();
		this.bibliotecari = new ArrayList<Utente>();
		this.utentiRegistrati = new ArrayList<Utente>();
		this.biblioteche = new ArrayList<Biblioteca>();
		this.manager = null;
		
	}
	
	/**
     * Metodo statico che restituisce l'unico oggetto Sbu istanziabile
     *
     * @param nome nome dell'SBU
     */
	public static Sbu getUniqueSbu( String id ){
		if( instanceSbu == null ){
			instanceSbu = new Sbu(id);
		}
		return instanceSbu;
	}
	
	/**
     * Login (controllo username e password). Ritorna la persona se email e
     * password corrispondono a quelle di una Persona contenuta nella
     * lista utenti, altrimenti null
     *
     * @param email email da controllare
     * @param password password da controllare
     * @return Persona
     */
    public Utente login( String email, String password ){
    	for(Utente u: utentiRegistrati){
    		if(u.getEmail().equals(email) && u.getPassword().equals(password)){
    			System.out.println("Login Utente effettuato");	
        		return u;
    		}
    	}
    	for(Utente b: bibliotecari){
    		if(b.getEmail().equals(email) && b.getPassword().equals(password)){
    			System.out.println("Login Bibliotecario effettuato");
    			return b;
    		}	
    	}
    	if(this.getManager() != null){
    		if(manager.getEmail().equals(email) && manager.getPassword().equals(password)){
    			System.out.println("Login Manager effettuato");
        		return manager;
    		}else{
    			System.out.println("Credenziali non valide");
    			return null;
    		}
    	}	
    	return null;
    }
    /**
     * Ricerca di un libro o di un documento digitale con una determinata stringa 
     * che puo' essere contenuta in uno dei campi, la ricerca viene effettuata solo
     * se l'utente e' bibliotecario o manager di sistema o se l'utente registrato risulta abbonato
     * per l'accesso a documenti digitali
     *
     * @param txt stringa da ricercare
     * @param utente utente che vuole effettuare la ricerca
     * @return articoli
     */
    public List<Articolo> ricercaCatalogoUnificato( Utente utente, String txt ){
    	List<Articolo> articoli = new ArrayList<Articolo>();
    	if( utente instanceof ManagerSistema){
    		for(Biblioteca b:biblioteche) {
    			for(Articolo a:b.ricercaCatalogoUnificato(txt)){
    				articoli.add(a);
    			} 			
    		}
    		return articoli;
    	}
    	if( utente instanceof Bibliotecario){
    		for(Biblioteca b:biblioteche) {
    			for(Articolo a:b.ricercaCatalogoUnificato(txt)){
    				articoli.add(a);
    			} 			
    		}
    		return articoli;
    	}
    	if( ((UtenteRegistrato) utente).isAbbonato() ){
    		System.out.println("Utente Registrato con abbonamento valido: accesso garantito");
    		for(Biblioteca b:biblioteche) {
    			for(Articolo a:b.ricercaCatalogoUnificato(txt)){
    				articoli.add(a);
    			} 			
    		}
    		return articoli;
    	}
    	return articoli;
    }
    
    /**
     * Ricerca di un libro da parte di un utente, funzione a cui possono accedere 
     * tutti gli utenti anche quelli non abbonati
     *
     * @param txt stringa da ricercare
     * @return libri
     */
    public List<Articolo> ricercaLibro( String txt ){
    	List<Articolo> libri = new ArrayList<Articolo>();
    	for(Biblioteca b:biblioteche) {
    		for(Articolo a:b.ricercaLibro(txt)){
    			libri.add(a);
    		} 				
    	}
    	return libri;
    }
    
    /**
     * Ricerca utente a partire da una stringa
     *
     * @param txt stringa di ricerca dell'utente
     * @return utentiTrovati
     */
    public List<Utente> ricercaUtente( String txt ){
    	List<Utente> utentiTrovati = new ArrayList<Utente>();
		for(Utente u: utentiRegistrati) {
			if(u.checkAnagrafica(txt)) {
				utentiTrovati.add(u);
			}
		}
		for(Utente u: utentiRegistrati) {
			if(u.checkAnagrafica(txt)) {
				utentiTrovati.add(u);
			}
		}
		return utentiTrovati;
    }
    
    /**
     * Registra nel sistema un nuovo utente. Ritorna false se
     * utente e' gia presente nel sistema, altrimenti true
     *
     * @param utente utente da registrare nel sistema
     * @return boolean
     */
    public boolean registraUtente( Utente utente ){
    	if( utente instanceof UtenteRegistrato){
    		for(Utente u : utentiRegistrati) {
        		if(u.getCodiceFiscale().equals(utente.getCodiceFiscale())
        				|| u.getEmail().equals(utente.getEmail())){
        			System.out.println("Utente gia' registrato nel sistema");
        			return false;
        		}
        	}
    		utentiRegistrati.add(utente);
    		System.out.println("Registrazione utente effettuata");
    		return true;
    	}
    	if( utente instanceof Bibliotecario ){
    		for(Utente b : bibliotecari) {
        		if(b.getCodiceFiscale().equals(utente.getCodiceFiscale()) 
        				|| b.getEmail().equals(utente.getEmail())){
        			System.out.println("Utente gia' registrato nel sistema");
        			return false;
        		}    
        	}
    		bibliotecari.add(utente);
    		System.out.println("Registrazione Bibliotecario effettuata");
    		return true;
    	}
    	if( this.getManager() == null ){
			this.setManager(utente);
    		System.out.println("Registrazione Manager effettuata");
    		return true;
		}else{
			System.out.println("Manager sistema gia' registrato e non modificabile");
    		return false;
		}	
    }
    
    /**
     * Rimuove registrazione utente esistente dal sistema. Ritorna false se
     * utente non risulta gia' registrato, altrimenti true
     *
     * @param utente utente da rimuovere
     * @return boolean
     */
    public boolean rimuoviUtente( Utente utente ){
    	if( utente instanceof UtenteRegistrato){
    		if( utentiRegistrati.contains(utente)){
    			utentiRegistrati.remove(utente);
        		return true;
    		}
    		return false;		
    	}
    	if( utente instanceof Bibliotecario ){
    		if( bibliotecari.contains(utente)){
    			bibliotecari.remove(utente);
        		return true;
    		}
    		return false;
    	}
    	if( getManager() != null ){
			rimuoviManager();
    		System.out.println("Manager rimosso");
    		return true;
		}else{
			System.out.println("Utente non presente in sbu");
    		return false;
		}	
    }
    
    /**
     * Rimuove registrazione Manager dal sistema
     *
     * @param bibliotecario registrazione bibliotecario da rimuovere
     * @return boolean
     */
    public void rimuoviManager(){
    	this.manager = null;
    }

	/**
	 * Gets id SBU
	 * 
	 * @return identificativo
	 */
	public String getId(){
		return this.identificativo;
	}

	/**
	 * Sets id SBU
	 * 
	 * @param identificativo id dell'SBU
	 */
	public void setId(String id){
		this.identificativo = id;
	}
	
	/**
	 * Gets Manager di Sistema
	 * 
	 * @return manager
	 */
	public Utente getManager(){
		return this.manager;
	}
	
	/**
	 * Sets Manager di Sistema
	 * 
	 * @param manager manager di sistema
	 */
	public void setManager(Utente manager){
		this.manager = manager;
	}

	/**
	 * Gets lista acquisti consigliati dagli utenti
	 * 
	 * @return listaConsigliati
	 */
	public List<Articolo> getListaConsigliati(){
		return listaConsigliati;
	}

	/**
	 * Gets lista Recensione
	 * 
	 * @return recensioni
	 */
	public List<Recensione> getRecensioni(){
		return recensioni;
	}
	
	/**
	 * Sets Biblioteca
	 * 
	 * @param biblioteca biblioteca da inserire
	 */
	public void setBiblioteca(Biblioteca biblioteca){
		this.biblioteche.add(biblioteca);
	}

	/**
	 * Gets lista Biblioteca
	 * 
	 * @return biblioteche
	 */
	public List<Biblioteca> getBiblioteche(){
		return biblioteche;
	}

	/**
	 * Gets lista Utenti Registrati
	 * 
	 * @return utentiRegistrati
	 */
	public List<Utente> getUtentiRegistrati(){
		return utentiRegistrati;
	}

	/**
	 * Gets lista bibliotecari
	 * 
	 * @return bibliotecari
	 */
	public List<Utente> getBibliotecari(){
		return bibliotecari;
	}
}
