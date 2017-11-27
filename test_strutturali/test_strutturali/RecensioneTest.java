package test_strutturali;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;


import gestionale.UtenteRegistrato;
import gestionale.Recensione;

public class RecensioneTest {
	
	private static UtenteRegistrato utente1 = null;
	private static UtenteRegistrato utente2 = null;
	private static Recensione recensione = null;

	@BeforeClass
	public static void setUp() {
		// 1 - Setup del test
		utente1 = new UtenteRegistrato("codiceFiscaleU1", "NomeU1", "CognomeU1", 
				"IndirizzoU1", new Date(), "340444456", 
				"email@U1.it", "passwordU1");
		utente2 = new UtenteRegistrato("codiceFiscale2", "NomeU2", "CognomeU2", 
				"IndirizzoU2", new Date(), "340567762", 
				"email@U2.it", "passwordU2");
		recensione = new Recensione(utente1, "RecensioneProva");
	}
	@Test
	public void testAutoreRecensione(){
		recensione.setAutore(utente2);
		assertEquals("L'autore cambia in utente2", utente2, recensione.getAutore());
	}

	@Test
	public void testTestoRecensione(){
		String testo = "Recensione";
		recensione.setTesto(testo);
		assertEquals("Testo della recensione modificato", testo,
				recensione.getTesto());
	}
}