package test_funzionali;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import gestionale.Biblioteca;
import gestionale.Libro;
import gestionale.Sbu;
import gestionale.UtenteRegistrato;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)

public class TestUC25 {
	private static Sbu sbu = null;
	private static Biblioteca biblioteca = null;
	private static Libro libro = null;
	private static UtenteRegistrato ut1 = null;
	private static UtenteRegistrato ut2 = null;
	
	@Parameters
	public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"recensioneCorretta", ut1, true},
            {"recensioneTroppoLunga12345678912345678912345678912345678", ut1, false},
            {"AltraRec1", ut1, true},
            {"recensioneCorretta2", ut2, true},
            {"recensioneTroppoLunga12345678912345678912345678912345678", ut2, false},
            {"AltraRec2", ut2, true}
        });
	}
	
	@BeforeClass
	public static void setUp() {
		// 1 - Setup del test
		sbu = Sbu.getUniqueSbu("SBU");
		biblioteca = new Biblioteca("Biblioteca", "viaBteca", sbu);
		sbu.setBiblioteca(biblioteca);
		libro = new Libro("TitoloLibro", "AutoreLibro", "GenereLibro", 
				"settoreLibro", biblioteca, "ISBN-0113-1100" , "CasaEditriceLibro", 477);
		biblioteca.inserisciLibro(libro);
		ut1 = new UtenteRegistrato("codiceFiscaleUt1", "NomeUt1", "CognomeUt1", 
				"IndirizzoUt1", new Date(), "340435677", 
				"email@Utente1.it", "passwordUt1");
		ut2 = new UtenteRegistrato("codiceFiscaleUt2", "NomeUt2", "CognomeUt2", 
				"IndirizzoUt2", new Date(), "340435677", 
				"email@Utente2.it", "passwordUt2");
	}
	
	@Parameter(0)
	public String Recensione;
	
	
	@Parameter(1)
	public UtenteRegistrato recensore;
	
	@Parameter(2)
	public boolean expectedResult;
	
	@Test
	public void test() {
		assertNotNull("libro istanziato", libro);
		assertNotNull("utente ut1 istanziato", ut1);
		assertNotNull("utente ut2 istanziato", ut2);
		boolean boolResult = libro.scriviRecensione(Recensione, recensore);
		// 5 - Check del risultato
		assertEquals(boolResult, expectedResult);
	}
}

