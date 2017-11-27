package test_strutturali;

import static org.junit.Assert.*;  

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import gestionale.Biblioteca;
import gestionale.Bibliotecario;
import gestionale.ManagerSistema;
import gestionale.Sbu;
import gestionale.UtenteRegistrato;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)

public class SbuLoginTest {
	private static Sbu sbu = null;
	private static UtenteRegistrato u = null;
	private static Bibliotecario b = null;
	private static ManagerSistema m = null;
	
	@Parameters
	public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
            {"email@U.it","passwordU",true},
            {"email@B.it","passwordB",true},
            {"email@B.it","passwordsbagliata",false},
            {"email@M.it","passwordsnagliata",false},
            {"email@M.it","passwordM",true},
            {"email@U.it","passwordaltra",false},
            {"altraMail@U.it","passwordB",false},
            {"email@U.it","passwordM",false},
        });
	}
	
	@Parameter(0)
	public String email;
	
	@Parameter(1)
	public String password;
	
	@Parameter(2)
	public boolean controllo;
	
	@BeforeClass
	public static void setUp() {
		// 1 - Setup del test
		sbu = Sbu.getUniqueSbu("SBU");
		u = new UtenteRegistrato("codiceFiscaleU", "NomeU", "CognomeU", 
				"IndirizzoU", new Date(), "3401236781", 
				"email@U.it", "passwordU");
		b = new Bibliotecario("codiceFiscaleB", "NomeB", "CognomeB", 
				"Indirizzo2", new Date(), "3401236781", 
				"email@B.it", "passwordB", 
				new Biblioteca("Biblioteca","ViaB",sbu));
		m = new ManagerSistema("codiceFiscaleM", "NomeM", "CognomeM", 
				"IndirizzoM", new Date(), "3401236781", 
				"email@M.it", "passwordM", sbu);
		sbu.registraUtente(u);
		sbu.registraUtente(b);
		sbu.registraUtente(m);
	}

	@Test
	public void testLogin(){
		boolean risultato;
		if (sbu.login(email, password) == null) {
			risultato = false;
		} else {
			risultato = true;
		}
		assertEquals("Login effettuato correttamente", 
				risultato, controllo);	
	}
	
	@Test
	public void testLogin2(){
		boolean risultato;
		sbu.rimuoviManager();
		if (sbu.login(email, password) == null) {
			risultato = false;
		} else {
			risultato = true;
		}
		assertEquals("Login effettuato correttamente", 
				risultato, controllo);	
	}


	@After
	public void after() {
		sbu = Sbu.getUniqueSbu("SBU");
		sbu.registraUtente(u);
		sbu.registraUtente(b);
		sbu.registraUtente(m);
	}
}

