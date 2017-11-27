package test_strutturali;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import gestionale.Prestito;
import gestionale.Sbu;
import gestionale.Biblioteca;
import gestionale.Libro;
import gestionale.UtenteRegistrato;

public class LibroTest {
	private static Libro libro = null;
	private static UtenteRegistrato u = null;
	private static Sbu sbu = null;
	private static Prestito prestito = null;
	private static Biblioteca biblioteca = null;
	
	
	@BeforeClass
	public static void setUp() {
		// 1 - Setup del test
		sbu = Sbu.getUniqueSbu("SBU");
		u = new UtenteRegistrato("codiceFiscaleU", "NomeU", "CognomeU", 
				"IndirizzoU", new Date(), "340893456", 
				"emai@U.it", "passwordU"); 
		biblioteca = new Biblioteca("Biblioteca", "ViaBteca", sbu);
		sbu.getBiblioteche().add(biblioteca);
		libro = new Libro("TitoloLibro", "Autore", "GenereLibro", 
				"CollocazioneLibro", biblioteca, "ISBN-00-2452-32" , "CasaEditriceL", 578);
		prestito = new Prestito(libro, u, new Date());
	}
	
	@Test
	public void testPrestito() {
		libro.setPrestito(prestito);
		assertEquals("Il prestito deve essere lo stesso", prestito, 
				libro.getPrestito());
	}
	
	@Test
	public void testPrenota() {
		libro.prenota(u);
		assertTrue("L'utente deve essere nella lista prenotazioni",
				libro.getPrenotato().contains(u));
	}
	
	@Test
	public void testGiaPrenotato(){
		libro.isPrenotato(u);
		assertTrue("L'utente non prenota piu volte",
				libro.isPrenotato(u));
	}

	@Test
	public void testRimuoviPrenotazione1(){
		libro.rimuoviPrenotazione(u);
		assertFalse("L'utente non deve essere nella lista",
				libro.getPrenotato().contains(u));
	}
	
	@Test
	public void testRimuoviPrenotazione2(){
		libro.rimuoviPrenotazione(u);
		assertFalse("L'utente non risulta prenotato", libro.isPrenotato(u));
	}
	
	@Test
	public void testCollocazione(){
		String settore = "Settore7A";
		libro.setSettore(settore);
		assertEquals("I settori devono essere uguali", settore, 
				libro.getSettore());
	}
	
	@Test
	public void testBiblio(){
		Biblioteca altraBiblio = new Biblioteca("altraBiblio", "ViaAltraBiblio", sbu);
		libro.setBiblio(altraBiblio);
		assertEquals("Le biblioteche devono essere uguali", altraBiblio, 
				libro.getBiblio());
	}	

	@Test
	public void testIsbn(){
		String nuovoIsbn = "ISBN-3210-453";
		libro.setIsbn(nuovoIsbn);
		assertEquals("ISBN deve essere il nuovo", nuovoIsbn, libro.getIsbn());
	}
	
	@Test
	public void testNuovaCasaEditrice(){
		String nuovaCasaEditrice = "nuovaCasaEditriceCasaEditrice";
		libro.setCasaEditrice(nuovaCasaEditrice);
		assertEquals("La casa editrice deve essere aggiornata", nuovaCasaEditrice,
				libro.getCasaEditrice());
	}

	@Test
	public void testPagine(){
		int pagine = 339;
		libro.setPagine(pagine);
		assertEquals("Il numero di pagine deve essere aggiornato", pagine,
				libro.getPagine());
	}

}
