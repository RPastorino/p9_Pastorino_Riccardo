package test_funzionali;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import gestionale.UtenteRegistrato;
import gestionale.Biblioteca;
import gestionale.Libro;
import gestionale.Sbu;

public class TestUC20 {
	private static Sbu sbu = null;
	private static UtenteRegistrato utente = null;
	private static Biblioteca biblioteca = null;
	private static Libro libro = null;
	
	@BeforeClass
	public static void setUp(){
		// 1 - Setup del test
		sbu = Sbu.getUniqueSbu("SBU");
		utente = new UtenteRegistrato("codiceFiscaleU", "NomeU", "CognomeU", 
				"IndirizzoU", new Date(), "340888456", 
				"email@U.it", "password");
		sbu.registraUtente(utente);
		biblioteca = new Biblioteca("Biblioteca", "ViaBiblioteca",
				sbu);
		sbu.setBiblioteca(biblioteca);
		libro = new Libro("TitoloArt", "AutoreArt", "GenereArt", 
				"SettoreArt", biblioteca, "ISBN-01205-1909" , "CasaEditriceArt", 630);
		biblioteca.inserisciLibro(libro);
	}
	
	@Test
	public void prenotazioneCorretta(){
		assertNotNull("Libro istanziato", libro);
		// 4 - esegui Test
		utente.prenotaLibro(libro);
		// 5 - Check del risultato
		assertTrue("Utente deve avere prenotazione ", utente.getPrenotazioni().contains(libro));
	}
	
	@Test
	public void prenotazioneNonCorretta(){
		assertNotNull("Libro istanziato", libro);
		// 4 - esegui Test
		utente.prenotaLibro(libro);
		// 5 - Check del risultato
		assertFalse("Utente deve avere prenotazione ", utente.prenotaLibro(libro));
	}
	
	@After
	public void after() {
		utente = new UtenteRegistrato("codiceFiscaleU", "NomeU", "CognomeU", 
				"IndirizzoU", new Date(), "340888456", 
				"email@U.it", "password");
		libro = new Libro("TitoloArt", "AutoreArt", "GenereArt", 
				"SettoreArt", biblioteca, "ISBN-01205-1909" , "CasaEditriceArt", 630);
	}

}
