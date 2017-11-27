package test_strutturali;

import static org.junit.Assert.*;   

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import gestionale.Abbonamento.TipoAbbonamento;
import gestionale.UtenteRegistrato;
import gestionale.Biblioteca;
import gestionale.Libro;
import gestionale.Sbu;

public class UtenteRegistratoTest {
	private static Sbu sbu = null;
	private static UtenteRegistrato utente = null;
	private static Biblioteca biblioteca= null;
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
	public void testSottoscrizioneAbbonamento1(){
		// Utente non abbonatosottoscrive abbonamento
		assertTrue("L'utente registrato riesce ad abbonarsi", 
				utente.sottoscriviAbbonamento(TipoAbbonamento.giornaliero));
	}
	
	@Test
	public void testSottoscrizioneAbbonamento2(){
		// Utente non abbonatosottoscrive abbonamento
		utente.sottoscriviAbbonamento(TipoAbbonamento.giornaliero);
		assertFalse("L'utente già abbonato non riesce ad abbonarsi nuovamente", 
				utente.sottoscriviAbbonamento(TipoAbbonamento.giornaliero));
	}
	
	@Test
	public void testSottoscrizioneAbbonamentoMensile(){
		// Utente non abbonatosottoscrive abbonamento
		utente.annullaAbbonamento();
		assertTrue("L'utente registrato riesce ad abbonarsi mensilmente", 
				utente.sottoscriviAbbonamento(TipoAbbonamento.mensile));
	}
	
	@Test
	public void testSottoscrizioneAbbonamentoAnnuale(){
		// Utente non abbonatosottoscrive abbonamento
		utente.annullaAbbonamento();
		assertTrue("L'utente registrato riesce ad abbonarsi mensilmente", 
				utente.sottoscriviAbbonamento(TipoAbbonamento.mensile));
	}
	
	@Test
	public void testSottoscrizioneConAbbonamentoScaduto(){
		// Utente non abbonatosottoscrive abbonamento
		utente.sottoscriviAbbonamento(TipoAbbonamento.mensile);
		Calendar data = Calendar.getInstance();
		data.setTime(new Date());
		data.add(Calendar.MONTH, -3);
		Date dataFineNuova = data.getTime();
		utente.getAbbonamento().setDataFine(dataFineNuova);
		assertTrue("L'utente con abbonamento scaduto riesce ad abbonarsi mensilmente", 
				utente.sottoscriviAbbonamento(TipoAbbonamento.mensile));
	}

	@Test
	public void testPrenotaLibro1(){
		// Articolo da prenotare
		utente.prenotaLibro(libro);
		assertTrue("La lista prenotazioni deve contenere l'articolo", 
				utente.getPrenotazioni().contains(libro));
	}
	
	@Test
	public void testPrenotaLibro2(){
		// Articolo prenotato più di una volta
		utente.prenotaLibro(libro);
		assertFalse("La prenotazione non deve andare nuovamente a buon fine", 
				utente.prenotaLibro(libro));
	}
	
	@Test
	public void testRimuoviPrenotazione(){
		utente.prenotaLibro(libro);
		utente.rimuoviPrenotazione(libro);
		assertFalse("La lista prenotazioni NON deve contenere l'articolo", 
				utente.getPrenotazioni().contains(libro));
	}
	
	@Test
	public void testRimuoviPrenotazione2(){
		utente.rimuoviPrenotazione(libro);
		assertFalse("La lista prenotazioni NON deve contenere l'articolo", 
				utente.getPrenotazioni().contains(libro));
	}
	
	@Test
	public void testGetAbbonamento(){
		utente.sottoscriviAbbonamento(TipoAbbonamento.annuale);
		assertNotNull("Abbonamento non deve essere null", 
				utente.getAbbonamento());
	}


	@Test
	public void testGetPrestiti(){
		assertNotNull("La lista prestiti non deve essere null", 
				utente.getPrestiti());
	}

	@Test
	public void testGetPrenotazioni(){
		assertNotNull("La lista prenotazioni non deve essere null", 
				utente.getPrenotazioni());
	}
	
	@After
	public void after(){
		utente = new UtenteRegistrato("codiceFiscaleU", "NomeU", "CognomeU", 
				"IndirizzoU", new Date(), "340888456", 
				"email@U.it", "password");
	}

}
