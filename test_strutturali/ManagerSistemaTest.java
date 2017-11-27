package test_strutturali;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import gestionale.Biblioteca;
import gestionale.ManagerSistema;
import gestionale.Sbu;
import gestionale.Bibliotecario;

public class ManagerSistemaTest {
	private static Sbu sbu = null;
	private static Biblioteca biblio= null;
	private static ManagerSistema manager = null;
	private static Bibliotecario bibliotecario = null;

	@BeforeClass
	public static void setUp() {
		// 1 - Setup del test
		sbu = Sbu.getUniqueSbu("SBU");
		biblio = new Biblioteca("Biblioteca", "viaBteca", sbu);
		manager = new ManagerSistema("codiceFiscaleManager", "NomeManager", "CognomeManager", 
				"IndirizzoManager", new Date(), "34012899967", 
				"emil@Manager.it", "passwordM", sbu);
		bibliotecario = new Bibliotecario("codiceFiscaleB1", "nomeB1", "cognomeB1", "indirizzoB1", 
				new Date(), "340609797", "email@Biblio1.it", 
				"passwordB1", biblio);
	}
	
	@Test
	public void testRegistraBibliotecario() {
		manager.registraBibliotecario(bibliotecario);
		assertFalse("Bibliotecario registrato correttamente",
				sbu.getBibliotecari().isEmpty());
	}
	
	@Test
	public void testCreazione() {
		assertNotNull("Manager non null", sbu.getManager());
	}

}
