package test_strutturali;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import gestionale.Articolo;
import gestionale.Biblioteca;
import gestionale.Libro;
import gestionale.Recensione;
import gestionale.Sbu;
import gestionale.UtenteRegistrato;

public class ArticoloTest {
	private static Sbu sbu = null;
	private static Biblioteca biblioteca = null;
	private static Articolo articolo = null;
	private static UtenteRegistrato u1 = null;
	private static UtenteRegistrato u2 = null;
	private static Recensione r1 = null;
	private static Recensione r2 = null;

	@BeforeClass
	public static void setUp() {
		// 1 - Setup del test
		sbu = Sbu.getUniqueSbu("SBU");
		biblioteca = new Biblioteca("Biblioteca", "ViaBteca",sbu);
		sbu.getBiblioteche().add(biblioteca);
		articolo = new Libro("TitoloArticolo", "AutoreArticolo", "GenereArticolo", 
				"settoreArticolo", biblioteca, "ISBN_93939" , "CasaEditriceArt", 670);
		u1 = new UtenteRegistrato("codiceFiscaleU1", "NomeU1", "CognomeU1", 
				"IndirizzoU1", new Date(), "340893456", 
				"emai@U1.it", "passwordU1");
		u2 = new UtenteRegistrato("codiceFiscaleU2", "NomeU2", "CognomeU2", 
				"Indirizzo2", new Date(), "34017772", 
				"emai@U2.it", "passwordU2");
		sbu.getUtentiRegistrati().add(u1);
		r1 = new Recensione(u1,"Bello");
		
		r2 = new Recensione(u2, "Brutto");
	}
	
	@Test
	public void checkCampiArticoloTitolo(){
		// ricerca stringa contenuta nei dati dell'articolo
		String stringaPresente = "Titol";
		Boolean contains = articolo.checkCampiArticolo(stringaPresente);
		assertTrue("L'articolo contiene la stringa in uno dei campi", contains);
		
	}
	
	@Test
	public void checkCampiArticoloTitolo2(){
		// ricerca stringa non contenuta nei dati dell'articolo
		String stringaPresente = "zxc";
		Boolean contains = articolo.checkCampiArticolo(stringaPresente);
		assertFalse("L'articolo non contiene la stringa in uno dei campi", contains);
		
	}
	
	@Test
	public void checkCampiArticoloAutore(){
		// ricerca stringa contenuta nei dati dell'articolo
		String stringaPresente = "Aut";
		Boolean contains = articolo.checkCampiArticolo(stringaPresente);
		assertTrue("L'articolo contiene la stringa in uno dei campi", contains);
		
	}
	
	@Test
	public void checkCampiArticoloAutore2(){
		// ricerca stringa contenuta nei dati dell'articolo
		String stringaPresente = "xrtAut";
		Boolean contains = articolo.checkCampiArticolo(stringaPresente);
		assertFalse("L'articolo non contiene la stringa in uno dei campi", contains);
		
	}
	
	@Test
	public void checkCampiArticoloGenere(){
		// ricerca stringa contenuta nei dati dell'articolo
		String stringaPresente = "Gener";
		Boolean contains = articolo.checkCampiArticolo(stringaPresente);
		assertTrue("L'articolo contiene la stringa in uno dei campi", contains);
		
	}
	
	@Test
	public void checkCampiArticoloGenere2(){
		// ricerca stringa contenuta nei dati dell'articolo
		String stringaPresente = "yyyy";
		Boolean contains = articolo.checkCampiArticolo(stringaPresente);
		assertFalse("L'articolo non contiene la stringa in uno dei campi", contains);
		
	}
	
	@Test
	public void testScriviRecensione1(){
		// Nuova recensione scritta
		articolo.scriviRecensione("Molto bello", u1);
		assertTrue("recensione scritta correttamente",
				articolo.scriviRecensione("Molto bello", u1));
	}
	
	@Test
	public void testScriviRecensioneTroppoLunga(){
		// Recensione troppo lunga
		String testoLungo = "una recensione che supera i 50 caratteri non è consentita";
		articolo.scriviRecensione(testoLungo, u1);
		assertFalse("recensione non scritta",
				articolo.scriviRecensione(testoLungo, u1));
	}

	@Test
	public void testInserisciRecensione(){
		// Nessuna recensione presente
		articolo.inserisciRecensione(r1);
		assertTrue("l'articolo deve contenere la recensione",
				articolo.getRecensioni().contains(r1));
	}
	
	@Test
	public void testInserisciRecensioneAltroAutore(){
		// Nessuna recensione presente
		articolo.inserisciRecensione(r2);
		assertTrue("l'articolo deve contenere la recensione",
				articolo.getRecensioni().contains(r2));
	}
	
	@Test
	public void testInserisciRecensioneAutoriDiversi(){
		// Nessuna recensione presente
		articolo.inserisciRecensione(r1);
		articolo.inserisciRecensione(r2);
		assertTrue("l'articolo deve contenere la recensione",
				articolo.getRecensioni().contains(r1));
	}
	
	@Test
	public void testInserisciRiscrivoRecensione(){
		// Una recensione dello stesso autore gia' presente e' riscritta
		articolo.inserisciRecensione(r1);
		Recensione r3 = new Recensione(u1,"Ottimo libro");
		articolo.inserisciRecensione(r3);
		assertTrue("l'articolo deve contenere la nuova recensione",
				articolo.getRecensioni().contains(r3));
	}

	@Test
	public void testRimuoviRecensione1(){
		// Non sono presenti recensioni
		articolo.rimuoviRecensione(u1);
		assertFalse("L'articolo non contiene recensioni dell'utente",
				articolo.getRecensioni().contains(r1));
	}
	
	@Test
	public void testRimuoviRecensione2(){
		// Presente una recensione di u1, la rimuovo
		articolo.scriviRecensione("Bello", u1);
		articolo.rimuoviRecensione(u1);
		assertFalse("L'articolo non contiene recensioni dell'utente",
				articolo.getRecensioni().contains(r1));
	}

	@Test
	public void testRimuoviRecensione3(){
		// Presente una recensione di u1 ma rimuovo quelle di u2
		articolo.inserisciRecensione(r1);
		articolo.rimuoviRecensione(u2);
		assertTrue("L'articolo contiene recensioni dell'utente",
				articolo.getRecensioni().contains(r1));
	}
	
	@Test
	public void testTitolo(){
		String titolo = "NuovoTitolo";
		articolo.setTitolo(titolo);
		assertEquals("I titoli devono essere uguali", titolo, 
				articolo.getTitolo());
	}

	@Test
	public void testAutore() {
		String autore = "NuovoAutore";
		articolo.setAutore(autore);
		assertEquals("Gli autori devono essere uguali", autore, 
				articolo.getAutore());
	}

	@Test
	public void testGenere() {
		String genere = "NuovoGenere";
		articolo.setGenere(genere);
		assertEquals("I generi devono essere uguali", genere, 
				articolo.getGenere());
	}

	@Test
	public void testGetRecensioni() {
		assertNotNull("La lista non deve essere null", 
				articolo.getRecensioni());
	}

	
	@After
	public void after() {
		articolo = new Libro("Titolo1", "Autore", "Genere", 
				"Collocazione", biblioteca, "ISBN_93939" , "CasaEditrice", 670);
	}
}
