package test_strutturali;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import gestionale.Utente;
import gestionale.Sbu;
import gestionale.UtenteRegistrato;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)

public class SbuRicercaUtenteTest {
	private static Sbu sbu = null;
	private static UtenteRegistrato u = null;
	
	@Parameters
	public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"NomeU",true},
            {"Banana",false}
        });
	}
	
	@Parameter(0)
	public String ricerca;
	
	@Parameter(1)
	public boolean controllo;
	
	@BeforeClass
	public static void setUp() {
		// 1 - Setup del test
		sbu = Sbu.getUniqueSbu("SBU");
		u = new UtenteRegistrato("codiceFiscaleU", "NomeU", "CognomeU", 
				"IndirizzoU", new Date(), "340596879", 
				"email@U.it", "password");
		sbu.registraUtente(u);
	}

	@Test
	public void testRicerca() {
		ArrayList<Utente> trovati = (ArrayList<Utente>) 
				sbu.ricercaUtente(ricerca);
		assertEquals(trovati.contains(u), controllo);
	}

	@After
	public void after() {
		sbu = Sbu.getUniqueSbu("SBU");
		sbu.registraUtente(u);
	}

}
