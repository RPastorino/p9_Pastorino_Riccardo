package test_funzionali;

import static org.junit.Assert.*; 
import org.junit.After;

import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import gestionale.Abbonamento.TipoAbbonamento;
import gestionale.UtenteRegistrato;
import gestionale.Sbu;

public class TestUC37 {
	private static Sbu sbu = null;
	private static UtenteRegistrato utente = null;
	
	@BeforeClass
	public static void setUp(){
		// 1 - Setup del test
		sbu = Sbu.getUniqueSbu("SBU");
		utente = new UtenteRegistrato("codiceFiscaleU", "NomeU", "CognomeU", 
				"IndirizzoU", new Date(), "340888456", 
				"email@U.it", "password");
		sbu.registraUtente(utente);
	}
	
	@Test
	public void testSottoscrizioneAbbonamentoGiornaliero(){
		assertNotNull("Utente istanziato", utente);
		assertNull("Nessun abbonamento valido", utente.getAbbonamento());
		// 2 - Settaggio inputs
		TipoAbbonamento tipoAbb = TipoAbbonamento.giornaliero;
		// 3 - valore atteso ed esecuzione test
		Boolean expectedRes = utente.sottoscriviAbbonamento(tipoAbb);
		// 4 - controllo risultato esecuzione
		assertTrue("Utente sottoscrive abbonamento correttamente", expectedRes);
	}
	
	@Test
	public void testSottoscrizioneAbbonamentoMensile(){
		assertNotNull("Utente istanziato", utente);
		assertNull("Nessun abbonamento valido", utente.getAbbonamento());
		// 2 - Settaggio inputs
		TipoAbbonamento tipoAbb = TipoAbbonamento.mensile;
		// 3 - valore atteso ed esecuzione test
		Boolean expectedRes = utente.sottoscriviAbbonamento(tipoAbb);
		// 4 - controllo risultato esecuzione
		assertTrue("Utente sottoscrive abbonamento correttamente", expectedRes);
	}
	
	@Test
	public void testSottoscrizioneAbbonamentoAnnuale(){
		assertNotNull("Utente istanziato", utente);
		assertNull("Nessun abbonamento valido", utente.getAbbonamento());
		// 2 - Settaggio inputs
		TipoAbbonamento tipoAbb = TipoAbbonamento.annuale;
		// 3 - valore atteso ed esecuzione test
		Boolean expectedRes = utente.sottoscriviAbbonamento(tipoAbb);
		// 4 - controllo risultato esecuzione
		assertTrue("Utente sottoscrive abbonamento correttamente", expectedRes);
	}
	
	@Test
	public void testSottoscrizioneGiaAvvenuta(){
		assertNotNull("Utente istanziato", utente);
		utente.sottoscriviAbbonamento(TipoAbbonamento.mensile);
		assertNotNull("Abbonamento non nullo", utente.getAbbonamento());
		// 2 - Settaggio inputs
		TipoAbbonamento tipoAbb = TipoAbbonamento.mensile;
		// 3 - valore atteso ed esecuzione test
		Boolean expectedRes = utente.sottoscriviAbbonamento(tipoAbb);
		// 4 - controllo risultato esecuzione
		assertFalse("Utente non deve sottoscrivere nuovamente abbonamento", expectedRes);
	}
	
	@Test
	public void testSottoscrizioneGiaAvvenutaMaAbbonamentoScaduto(){
		// Utente puo' nuovamente abbonarsi in quanto il precedente risulta scaduto
		assertNotNull("Utente istanziato", utente);
		utente.sottoscriviAbbonamento(TipoAbbonamento.mensile);
		assertNotNull("Abbonamento non nullo", utente.getAbbonamento());
		Calendar data = Calendar.getInstance();
		data.setTime(new Date());
		data.add(Calendar.MONTH, -7);
		Date dataFine = data.getTime();
		utente.getAbbonamento().setDataFine(dataFine);
		// 2 - Settaggio inputs
		TipoAbbonamento tipoAbb = TipoAbbonamento.annuale;
		// 3 - valore atteso ed esecuzione test
		Boolean expectedRes = utente.sottoscriviAbbonamento(tipoAbb);
		// 4 - controllo risultato esecuzione
		assertTrue("Utente sottoscrive abbonamento correttamente", expectedRes);
	}

	@After
	public void after(){
		sbu = Sbu.getUniqueSbu("SBU");
		utente = new UtenteRegistrato("codiceFiscaleU", "NomeU", "CognomeU", 
				"IndirizzoU", new Date(), "340888456", 
				"email@U.it", "password");
		sbu.registraUtente(utente);
		utente.annullaAbbonamento();
	}
}