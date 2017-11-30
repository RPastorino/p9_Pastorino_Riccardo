package gestionale;

import java.util.List; 
import java.util.ArrayList; 

/** 
 * Classe Biblioteca ( una o piu' biblioteche costituiscono l'sbu )
 * 
 * @author Riccardo Pastorino
 * @version 1.0
 * 
 */
public class Biblioteca {
	/** id della biblioteca */
    private String id;
    
    /** Indirizzo della biblioteca */
    private String indirizzo;
    
    /** Lista Articoli in catalogo libri */
    private List<Articolo> catalogo;
    
    /** Lista Articoli digitali in catalogo digitale */
    private List<Articolo> catalogoDigitale;
    
    /** Lista Recensione della biblioteca */
	private List<Recensione> recensioni;
	
	/** SBU di riferimento della biblioteca */
    private Sbu sbu;
    
	/**
     * Costruttore di Biblioteca
     *
     * @param nome nome della biblioteca
     * @param indirizzo indirizzo della biblioteca
     */
    public Biblioteca(String nome, String indirizzo, Sbu sbu){
		super();
		this.id = nome;
		this.indirizzo = indirizzo;
		catalogo = new ArrayList<Articolo>();
		catalogoDigitale = new ArrayList<Articolo>();
		recensioni = new ArrayList<Recensione>();
		this.sbu = sbu;
	}
    
    /**
     * Ricerca un articolo con una determinata stringa che puo' essere 
     * contenuta in uno dei campi
     *
     * @param txt stringa da ricercare
     * @return articoli
     */
    public List<Articolo> ricercaCatalogoUnificato( String txt ){
	List<Articolo> catalogoUnificato = new ArrayList<Articolo>();
    	catalogoUnificato.addAll(catalogo);
    	catalogoUnificato.addAll(catalogoDigitale);
    	List<Articolo> articoli = new ArrayList<Articolo>();
		for (Articolo a: catalogoUnificato) {
			if (a.checkCampiArticolo(txt)){
				articoli.add(a);
			}
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
    	for (Articolo a: catalogo) {
			if (a.checkCampiArticolo(txt)){
				libri.add(a);
			}
		}
    	return libri;
    }
    
    /**
     * InserisciLibro
     * 
     * @param libro libro da inserire nel catalogo
     */
    public void inserisciLibro( Libro libro ){
    	this.catalogo.add(libro);
    }
    
    /**
     * InserisciDocumentoDigitale
     * 
     * @param docDigitale documento da inserire nel catalogo digitale
     */
    public void inserisciDocDigitale( DocumentoDigitale docDigitale ){
    	this.catalogoDigitale.add(docDigitale);
    }

	/**
	 * Gets id biblioteca
	 * 
	 * @return id
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * Sets id biblioteca
	 * 
	 * @param id id della biblioteca
	 */
	public void setId(String id){
		this.id = id;
	}

	/**
	 * Gets indirizzo biblioteca
	 * 
	 * @return indirizzo
	 */
	public String getIndirizzo(){
		return this.indirizzo;
	}

	/**
	 * Sets indirizzo biblioteca
	 * 
	 * @param indirizzo indirizzo biblioteca
	 */
	public void setIndirizzo(String indirizzo){
		this.indirizzo = indirizzo;
	}

	/**
	 * Gets lista Articolo posseduti dalla biblioteca
	 * 
	 * @return possiede
	 */
	public List<Articolo> getCatalogo(){
		return this.catalogo;
	}

	/**
	 * Gets lista Recensione della biblioteca
	 * 
	 * @return recensioni
	 */
	public List<Recensione> getRecensioni(){
		return this.recensioni;
	}
	
	/**
	 * Gets sbu
	 * 
	 * @return recensioni
	 */
	public Sbu getSbu(){
		return this.sbu;
	}
}
