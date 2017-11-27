package gestionale;

/** 
 * Classe DocumentoDigitale ( eredita da Articolo )
 * 
 * @author Riccardo Pastorino
 * @version 1.0
 * 
 */
public class DocumentoDigitale extends Articolo{
	/** tipo di documento digitale (Articolo,Paper,rivista,..) */
    private String tipo;
    
    /** formato elettronico del documento digitale( pdf,epub,.. ) */
    private String formato;

    /**
     * Costruttore di DocumentoDigitale. Richiama il costruttore di
     * Articolo passandogli i parametri necessari
     *
     * @param titolo titolo documento digitale
     * @param autore autore documento digitale
     * @param genere genere documento digitale
     * @param tipo tipo di documento digitale (Articolo,Paper,rivista,..)
     * 
     */
	 public DocumentoDigitale(String titolo, String autore, String genere, 
			 String tipo, String formato, int pagine){
		super(titolo, autore, genere, pagine);
		this.setTipo(tipo);
	}

	/**
	 * Gets tipo documento digitale
	 * 
	 * @return tipo
	 */
	public String getTipo(){
		return tipo;
	}

	/**
	 * Sets tipo documento digitale
	 * 
	 * @param tipo tipo di documento digitale 
	 */
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
	
	/**
	 * Gets formato documento digitale
	 * 
	 * @return tipo
	 */
	public String getFormato(){
		return this.formato;
	}

	/**
	 * Sets tipo documento digitale
	 * 
	 * @param tipo tipo di documento digitale 
	 */
	public void setFormato(String formato){
		this.formato = formato;
	}


}
