package test_strutturali;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import gestionale.Utente;
import gestionale.UtenteRegistrato;

public class UtenteTest {
	private static Utente utente = null;
	
	@BeforeClass
	public static void setUp() {
		// 1 - Setup del test
		utente = new UtenteRegistrato("CodiceFiscaleU", "NomeU", "CognomeU", 
				"IndirizzoU", new Date(), "340888456", 
				"email@U.it", "password");
	}

	@Test
	public void testCheckAnagraficaNome(){
		String nomeCercato = "Nom";
		assertTrue("La persona deve essere quella cercata", 
				utente.checkAnagrafica(nomeCercato));
	}
	
	@Test
	public void testCheckAnagraficaCognome(){
		String cognomeCercato = "Cogn";
		assertTrue("La persona deve essere quella cercata", 
				utente.checkAnagrafica(cognomeCercato));
	}
	
	@Test
	public void testCheckAnagraficaCf(){
		String cfCercato = "CodiceFiscaleU";
		assertTrue("La persona deve essere quella cercata", 
				utente.checkAnagrafica(cfCercato));
	}
	
	@Test
	public void testCheckAnagraficaEmail(){
		String emailCercata = "email@U.it";
		assertTrue("La persona deve essere quella cercata", 
				utente.checkAnagrafica(emailCercata));
	}
	
	@Test
	public void testCheckAnagraficaNonPresente(){
		assertFalse("Utente non trovato", 
				utente.checkAnagrafica("xcxv"));
	}

	@Test
	public void testSetNome(){
		utente.setNome("nome");
		assertEquals("Il nome dell'utente deve essere cambiato", "nome",
				utente.getNome());
	}

	@Test
	public void testSetCognome(){
		utente.setCognome("Cognome");
		assertEquals("Il cognome dell'utente deve essere cambiato", 
				"Cognome", utente.getCognome());
	}

	@Test
	public void testSetIndirizzo(){
		utente.setIndirizzo("indirizzo");
		assertEquals("L'indirizzo dell'utente deve essere cambiato", 
				"indirizzo", utente.getIndirizzo());
	}

	@Test
	public void testDataNascita(){
		Date nuovaData = new Date();
		utente.setDataNascita(nuovaData);
		assertEquals("La data di nascita dell'utente deve essere cambiata", 
				nuovaData, utente.getDataNascita());
	}

	@Test
	public void testCodiceFiscale(){
		utente.setCodiceFiscale("Cfcambiato");
		assertEquals("Il codice fiscale dell'utente deve essere cambiato", 
				"Cfcambiato", utente.getCodiceFiscale());
	}

	@Test
	public void testSetTelefono(){
		utente.setTelefono("34567889");
		assertEquals("Il numero di telefono dell'utente deve essere cambiato",
				"34567889", utente.getTelefono());
	}

	@Test
	public void testSetEmail(){
		utente.setEmail("nuova@email.it");
		assertEquals("L'indirizzo email dell'utente deve essere cambiato", 
				"nuova@email.it", utente.getEmail());
	}

	@Test
	public void testGetPassword(){
		utente.setPassword("nuovaPassword");
		assertEquals("La password dell'utente deve essere aggiornato", 
				"nuovaPassword", utente.getPassword());
	}

	
	@After
	public void after() {
		utente = new UtenteRegistrato("CodiceFiscaleU", "NomeU", "CognomeU", 
				"IndirizzoU", new Date(), "340888456", 
				"email@U.it", "password");
	}

}
