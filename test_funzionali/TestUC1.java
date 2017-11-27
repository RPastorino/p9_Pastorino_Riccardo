package test_funzionali;

import static org.junit.Assert.*; 

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import gestionale.Biblioteca;
import gestionale.Bibliotecario;
import gestionale.ManagerSistema;
import gestionale.Sbu;
import gestionale.UtenteRegistrato;
import gestionale.Utente;

public class TestUC1 {
	private static Sbu sbu = null;
	private static UtenteRegistrato utente = null;
	private static Bibliotecario bibliotecario = null;
	private static ManagerSistema manager = null;
	
	@BeforeClass
	public static void setUp() {
		// 1 - Setup del test
		sbu = Sbu.getUniqueSbu("SBU");
		utente = new UtenteRegistrato("codiceFiscaleU", "NomeU", "CognomeU", 
				"IndirizzoU", new Date(), "3401236781", 
				"email@U.it", "passwordU");
		bibliotecario = new Bibliotecario("codiceFiscaleB", "NomeB", "CognomeB", 
				"Indirizzo2", new Date(), "3401236781", 
				"email@B.it", "passwordB", 
				new Biblioteca("Biblioteca","ViaB",sbu));
		manager = new ManagerSistema("codiceFiscaleM", "NomeM", "CognomeM", 
				"IndirizzoM", new Date(), "3401236781", 
				"email@M.it", "passwordM", sbu);
		sbu.registraUtente(utente);
		sbu.registraUtente(bibliotecario);
		sbu.registraUtente(manager);
	}
	
	@Test
	public void testLoginUserNonCorretto(){
		assertNotNull("Utente istanziato", utente);
		// 2 - Settaggio inputs
		String user = "yyyyy";
		String password = "passwordU";
		// 3 - valore atteso ed esecuzione test
		Utente expectedloggato = sbu.login(user, password);
		// 4 - controllo risultato esecuione
		assertNull("Utente non deve essere loggato correttamente", expectedloggato);
	}
	
	@Test
	public void testLoginPasswordNonCorretto(){
		assertNotNull("Utente istanziato", utente);
		// 2 - Settaggio inputs
		String user = "email@U.it";
		String password = "yyyyyy";
		// 3 - valore atteso ed esecuzione test
		Utente expectedloggato = sbu.login(user, password);
		// 4 - controllo risultato esecuione
		assertNull("Utente non deve essere loggato correttamente", expectedloggato);
	}
	
	@Test
	public void testLoginCorrettoManagerSistema(){
		assertNotNull("ManagerSistema istanziato", manager);
		// 2 - Settaggio inputs
		String user = "email@M.it";
		String password = "passwordM";
		// 3 - valore atteso ed esecuzione test
		Utente expectedloggato = sbu.login(user, password);
		// 4 - controllo risultato esecuione
		assertNotNull("Manager deve essere loggato correttamente", expectedloggato);
	}

	
	@Test
	public void testLoginCorrettoUtenteRegistrato(){
		assertNotNull("UtenteRegistrato istanziato", utente);
		// 2 - Settaggio inputs
		String user = "email@U.it";
		String password = "passwordU";
		// 3 - valore atteso ed esecuzione test
		Utente expectedloggato = sbu.login(user, password);
		// 4 - controllo risultato esecuione
		assertNotNull("Utente deve essere loggato correttamente", expectedloggato);
	}
	
	@Test
	public void testLoginCorrettoBibliotecario(){
		assertNotNull("Bibliotecario istanziato", bibliotecario);
		// 2 - Settaggio inputs
		String user = "email@B.it";
		String password = "passwordB";
		// 3 - valore atteso ed esecuzione test
		Utente expectedloggato = sbu.login(user, password);
		// 4 - controllo risultato esecuione
		assertNotNull("Bibliotecario deve essere loggato correttamente", expectedloggato);
	}

}
