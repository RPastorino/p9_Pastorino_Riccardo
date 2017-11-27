package test_strutturali;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import gestionale.Sbu;
import gestionale.Libro;
import gestionale.Biblioteca;
import gestionale.Prestito;
import gestionale.UtenteRegistrato;

public class PrestitoTest {
	private static Sbu sbu = null;
	private static Biblioteca biblioteca = null;
	private static Libro libro = null;
	private static UtenteRegistrato utente = null;
	private static Prestito prestito = null;

	@BeforeClass
	public static void setUp() {
		// 1 - Setup del test
		sbu = Sbu.getUniqueSbu("SBU");
		biblioteca = new Biblioteca("Biblioteca 1", "Via Biblioteca 1", sbu);
		sbu.getBiblioteche().add(biblioteca);
		libro = new Libro("TitoloLibro", "Autore", "GenereLibro", 
				"CollocazioneLibro", biblioteca, "ISBN-00-2452-32" , "CasaEditriceL", 578);
		utente = new UtenteRegistrato("codiceFiscaleU", "NomeU", "CognomeU", 
				"IndirizzoU", new Date(), "340893456", 
				"emai@U.it", "passwordU");
		prestito = new Prestito(libro, utente, new Date());
		sbu.registraUtente(utente);
		utente.getPrestiti().add(prestito);
		libro.setPrestito(prestito);
	}

	@Test
	public void testProlungaPrestito(){
		// Prolungamento possibile (rinnovi non terminati)
		assertTrue("rinnovo disponibile", prestito.estendiPrestito());
	}
	
	@Test
	public void testProlungaPrestitoNonDisponibile(){
		// Prolungamento non possibile (rinnovi terminati)
		while (prestito.getRinnovi()>0){
			prestito.estendiPrestito();
		}
		assertFalse("rinnovo non più disponibile", prestito.estendiPrestito());
	}

	@Test
	public void testGetDataInizio(){
		assertNotNull("Data di inizio not null", prestito.getDataInizio());
	}

	@Test
	public void testGetDataFine(){
		assertNotNull("Data di fine not null", prestito.getDataFine());
	}

	@Test
	public void testGetArticolo(){
		assertNotNull("Libro preso in prestito not null", prestito.getLibro());
	}

	@Test
	public void testGetRichiestoDa(){
		assertNotNull("L'utente richiedente not null", prestito.getRichiedente());
	}

}
