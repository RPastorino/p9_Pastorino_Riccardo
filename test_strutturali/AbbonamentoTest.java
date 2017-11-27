package test_strutturali;

import static org.junit.Assert.*;  

import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import gestionale.Abbonamento.TipoAbbonamento;
import gestionale.Sbu;
import gestionale.UtenteRegistrato;

public class AbbonamentoTest {
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
		utente.sottoscriviAbbonamento(TipoAbbonamento.annuale);
	}


	@Test
	public void testGetDataInizio() {
		assertNotNull("La data inizio non deve essere null", 
				utente.getAbbonamento().getDataInizio());
	}
	
	@Test
	public void testGetDataFine() {
		assertNotNull("La data fine non deve essere null", 
				utente.getAbbonamento().getDataFine());
	}
	
	@Test
	public void testGetPeriodoAbbonamento() {
		utente.annullaAbbonamento();
		utente.sottoscriviAbbonamento(TipoAbbonamento.giornaliero);
		assertEquals("Tipo abbonamento uguale a quello sottoscritto", TipoAbbonamento.giornaliero,
				utente.getAbbonamento().getPeriodoAbbonamento());
	}
	
	@Test
	public void testGetGiorniValenzaAbbonamento() {
		utente.annullaAbbonamento();
		utente.sottoscriviAbbonamento(TipoAbbonamento.giornaliero);
		assertEquals("Giorni valenza per il giornaliero uguale a 1", 1,
				utente.getAbbonamento().getGiorniValenza());
	}
	
	@Test
	public void testGetAbbonato() {
		assertNotNull("Abbonato non deve essere null", 
				utente.getAbbonamento().getAbbonato());
	}
	
	@Test
	public void testIsValidYes(){
		// testa validità abbonamento valido
		utente.getAbbonamento().isValid();
		assertTrue("La prenotazione non deve andare nuovamente a buon fine", 
				utente.getAbbonamento().isValid());
	}
	
	@Test
	public void testIsValidNo(){
		// testa validità abbonamento non valido
		Calendar data = Calendar.getInstance();
		data.setTime(new Date());
		data.add(Calendar.YEAR, -4);
		Date dataFine = data.getTime();
		utente.getAbbonamento().setDataFine(dataFine);
		utente.getAbbonamento().isValid();
		assertFalse("La prenotazione non deve andare nuovamente a buon fine", 
				utente.getAbbonamento().isValid());
	}

}
