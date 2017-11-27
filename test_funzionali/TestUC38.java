package test_funzionali;

import static org.junit.Assert.*; 

import org.junit.After;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import gestionale.Abbonamento.TipoAbbonamento;
import gestionale.Biblioteca;
import gestionale.Articolo;
import gestionale.UtenteRegistrato;
import gestionale.Sbu;
import gestionale.DocumentoDigitale;

public class TestUC38 {
	private static Sbu sbu = null;
	private static UtenteRegistrato utente = null;
	private static Biblioteca biblioteca = null;
	private static DocumentoDigitale docDig = null;
	private static List<Articolo> listTrovati = null;
	
	@BeforeClass
	public static void setUp(){
		// 1 - Setup del test
		sbu = Sbu.getUniqueSbu("SBU");
		utente = new UtenteRegistrato("codiceFiscaleU", "NomeU", "CognomeU", 
				"IndirizzoU", new Date(), "340888456", 
				"email@U.it", "password");
		sbu.registraUtente(utente);
		biblioteca = new Biblioteca("Biblioteca", "viaBteca", sbu);
		sbu.setBiblioteca(biblioteca);
		docDig = new DocumentoDigitale("TitoloDg", "AutoreDg", "GenereDg", 
				"TipoDg", "FormatoDg" ,  627);
		biblioteca.inserisciDocDigitale(docDig);
		utente.sottoscriviAbbonamento(TipoAbbonamento.mensile);
	}
	
	@Test
	public void testAccessoCatalogoUnificatoCorrettoStringaCorretta(){
		assertNotNull("Utente istanziato", utente);
		assertNotNull("Utente con abbonamento valido", utente.getAbbonamento());
		// 2 - Settaggio input
		String dgCercato = "TitoloDg";
		// 3 - esecuzione test
		listTrovati = sbu.ricercaCatalogoUnificato(utente, dgCercato);
		DocumentoDigitale dg = (DocumentoDigitale)listTrovati.get(0);
		// 4 - controllo risultato esecuzione
		assertEquals("Lista contiene il documento digitale cercato", dg.getTitolo(), docDig.getTitolo());
	}
	
	@Test
	public void testAccessoCatalogoUnificatoCorrettoStringaNonCorretta(){
		assertNotNull("Utente istanziato", utente);
		assertNotNull("Utente con abbonamento valido", utente.getAbbonamento());
		// 2 - Settaggio input
		String dgCercato = "yyy";
		// 3 - esecuzione test
		listTrovati = sbu.ricercaCatalogoUnificato(utente, dgCercato);
		Boolean expectedRes = listTrovati.contains(docDig);
		// 4 - controllo risultato esecuzione
		assertFalse("Lista non contiene il documento digitale cercato", expectedRes);
	}
	
	@Test
	public void testAccessoCatalogoUnificatoNonCorrettoStringaCorretta(){
		assertNotNull("Utente istanziato", utente);
		utente.annullaAbbonamento();
		assertNull("Utente con abbonamento valido", utente.getAbbonamento());
		// 2 - Settaggio input
		String dgCercato = "Titol";
		// 3 - esecuzione test
		listTrovati = sbu.ricercaCatalogoUnificato(utente, dgCercato);
		// 4 - controllo risultato esecuzione
		assertTrue("Lista non contiene nulla", listTrovati.isEmpty());
	}
	
	@Test
	public void testAccessoCatalogoUnificatoNonCorrettoStringaNonCorretta(){
		assertNotNull("Utente istanziato", utente);
		utente.annullaAbbonamento();
		assertNull("Utente con abbonamento valido", utente.getAbbonamento());
		// 2 - Settaggio input
		String dgCercato = "yyy";
		// 3 - esecuzione test
		listTrovati = sbu.ricercaCatalogoUnificato(utente, dgCercato);
		// 4 - controllo risultato esecuzione
		assertTrue("Lista non contiene nulla", listTrovati.isEmpty());
	}
	
	@After
	public void after(){
		sbu = Sbu.getUniqueSbu("SBU");
		utente = new UtenteRegistrato("codiceFiscaleU", "NomeU", "CognomeU", 
				"IndirizzoU", new Date(), "340888456", 
				"email@U.it", "password");
		sbu.registraUtente(utente);
		biblioteca = new Biblioteca("Biblioteca", "viaBteca", sbu);
		sbu.setBiblioteca(biblioteca);
		docDig = new DocumentoDigitale("TitoloDg", "AutoreDg", "GenereDg", 
				"TipoDg", "FormatoDg" ,  627);
		biblioteca.inserisciDocDigitale(docDig);
		utente.sottoscriviAbbonamento(TipoAbbonamento.mensile);
	}
}