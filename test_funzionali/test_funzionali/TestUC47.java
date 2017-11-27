package test_funzionali;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import gestionale.ManagerSistema;
import gestionale.Utente;
import gestionale.Sbu;
import gestionale.UtenteRegistrato;

public class TestUC47 {
	private static Sbu sbu = null;
	private static ManagerSistema m = null;
	private static UtenteRegistrato u = null;
	
	@BeforeClass
	public static void setUp() {
		// 1 - Setup del test
		sbu = Sbu.getUniqueSbu("SBU");
		m = new ManagerSistema("codiceFiscaleM", "NomeM", "CognomeM", 
				"IndirizzoM", new Date(), "340888456", 
				"email@M.it", "passwordM", sbu);
		u = new UtenteRegistrato("codiceFiscaleU", "NomeU", "CognomeU", 
				"IndirizzoU", new Date(), "340888456", 
				"email@U.it", "passwordU");
		sbu.registraUtente(m);
		sbu.registraUtente(u);
	}
	
	@Test
	public void ricercaUtentePerNome(){
		assertNotNull("Manager istanziato", m);
		assertNotNull("Utente istanziato", u);
		// 2 - Settaggio input
		String utenteCercato = "Nom";
		// 3 - esecuzione test
		ArrayList<Utente> utentiTrovati = (ArrayList<Utente>)sbu.ricercaUtente(utenteCercato);
		// 4 - controllo risultato esecuzione
		assertFalse("Lista utenti trovati non  vuota", utentiTrovati.isEmpty());
	}
	
	@Test
	public void ricercaUtentePerCognome(){
		assertNotNull("Manager istanziato", m);
		assertNotNull("Utente istanziato", u);
		// 2 - Settaggio input
		String utenteCercato = "Cogn";
		// 3 - esecuzione test
		ArrayList<Utente> utentiTrovati = (ArrayList<Utente>)sbu.ricercaUtente(utenteCercato);
		// 4 - controllo risultato esecuzione
		assertFalse("Lista utenti trovati non  vuota", utentiTrovati.isEmpty());
	}
	
	@Test
	public void ricercaUtentePerCf(){
		assertNotNull("Manager istanziato", m);
		assertNotNull("Utente istanziato", u);
		// 2 - Settaggio input
		String utenteCercato = "codiceFiscaleU";
		// 3 - esecuzione test
		ArrayList<Utente> utentiTrovati = (ArrayList<Utente>)sbu.ricercaUtente(utenteCercato);
		// 4 - controllo risultato esecuzione
		assertFalse("Lista utenti trovati non  vuota", utentiTrovati.isEmpty());
	}
	
	@Test
	public void ricercaUtentePerEmail(){
		assertNotNull("Manager istanziato", m);
		assertNotNull("Utente istanziato", u);
		// 2 - Settaggio input
		String utenteCercato = "email@U.it";
		// 3 - esecuzione test
		ArrayList<Utente> utentiTrovati = (ArrayList<Utente>)sbu.ricercaUtente(utenteCercato);
		// 4 - controllo risultato esecuzione
		assertFalse("Lista utenti trovati non  vuota", utentiTrovati.isEmpty());
	}
	
	@Test
	public void ricercaUtenteNonTrovato(){
		assertNotNull("Manager istanziato", m);
		assertNotNull("Utente istanziato", u);
		// 2 - Settaggio input
		String utenteCercato = "yyyyy";
		// 3 - esecuzione test
		ArrayList<Utente> utentiTrovati = (ArrayList<Utente>)sbu.ricercaUtente(utenteCercato);
		// 4 - controllo risultato esecuzione
		assertTrue("Lista utenti trovati vuota", utentiTrovati.isEmpty());
	}

}
