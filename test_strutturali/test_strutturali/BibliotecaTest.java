package test_strutturali;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import gestionale.Articolo;
import gestionale.Biblioteca;
import gestionale.DocumentoDigitale;
import gestionale.Libro;
import gestionale.Sbu;

public class BibliotecaTest {
	private static Sbu sbu = null;
	private static Biblioteca biblioteca = null;
	private static Libro libro = null;
	private static DocumentoDigitale docDigitale = null;
	private static ArrayList<Articolo> artTrovati = null;
	
	@BeforeClass
	public static void setUp(){
		// 1 - Setup del test
		sbu = Sbu.getUniqueSbu("SBU");
		biblioteca = new Biblioteca("Biblioteca", "ViaXYZ",sbu);
		sbu.getBiblioteche().add(biblioteca);
		libro = new Libro("TitoloLibro", "AutoreLibro", "GenereLibro", 
				"settoreLibro", biblioteca, "ISBN-0113-1100" , "CasaEditriceLibro", 477);
		docDigitale = new DocumentoDigitale("TitoloDoc", "AutoreDoc", "GenereDoc", 
				"tipoDoc", "formatoDoc", 876);
		biblioteca.inserisciLibro(libro);
		biblioteca.inserisciDocDigitale(docDigitale);
	}
	
	@Test
	public void testRicercaLibroContenuto(){
		// La biblioteca contiene l'elemento cercato
		biblioteca.inserisciLibro(libro);
		String libroCercato = "Titol";
		artTrovati = new ArrayList<Articolo>(biblioteca.ricercaCatalogoUnificato(libroCercato));
		assertTrue("La lista deve contenere libro", artTrovati.contains(libro));
	}
	
	@Test
	public void testRicercaDocDigitaleContenuto(){
		// La biblioteca contiene l'elemento cercato
		biblioteca.inserisciDocDigitale(docDigitale);
		String docCercato = "Titol";
		artTrovati = new ArrayList<Articolo>(biblioteca.ricercaCatalogoUnificato(docCercato));
		assertTrue("La lista deve contenere libro", artTrovati.contains(docDigitale));
	}


	@Test
	public void testRicercaArticolo(){
		// La biblioteca non contiene elementi
		String libroCercato = "any";
		artTrovati = new ArrayList<Articolo>(biblioteca.ricercaCatalogoUnificato(libroCercato));		
		assertTrue("Ricerca non deve produrre risultati", artTrovati.isEmpty());
	}
	
	@Test
	public void testRicercaArticoloNontrovato(){
		// La biblioteca contiene elementi, ma non quello cercato
		String ricerca = "wrong";
		artTrovati = new ArrayList<Articolo>(biblioteca.ricercaCatalogoUnificato(ricerca)); 
		assertTrue("La lista deve essere vuota", artTrovati.isEmpty());
	}
	
	@Test
	public void testId(){
		String id = "newId";
		biblioteca.setId(id);
		assertEquals("Id deve essere uguale", id, biblioteca.getId());
	}

	@Test
	public void testIndirizzo(){
		String via = "ViaNew";
		biblioteca.setIndirizzo(via);
		assertEquals("L'indirizzo deve essere uguale", via, 
				biblioteca.getIndirizzo());
	}

	@Test
	public void testGetCatalogo(){
		assertNotNull("Il catalogo non deve esser null",biblioteca.getCatalogo());
	}

	@Test
	public void testGetRecensioni(){
		assertNotNull("La lista non deve esser null",biblioteca.getRecensioni());
	}
	
	@Test
	public void testGetSbu(){
		assertNotNull("L'Sbu non deve esser null",biblioteca.getSbu());
	}

	@After
	public void after(){
		biblioteca = new Biblioteca("Biblioteca", "ViaXYZ",sbu);
	}
}
