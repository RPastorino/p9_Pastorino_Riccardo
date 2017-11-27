package test_strutturali;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import gestionale.Sbu;
import gestionale.Biblioteca;
import gestionale.Bibliotecario;

public class BibliotecarioTest {
	private static Sbu sbu = null;
	private static Bibliotecario bibliotecario = null;
	private static Biblioteca biblioteca = null;
	
	@BeforeClass
	public static void setUp() {
		// 1 - Setup del test
		sbu = Sbu.getUniqueSbu("SBU");
		biblioteca = new Biblioteca("Biblioteca", "ViaBiblioteca", sbu);
		sbu.getBiblioteche().add(biblioteca);
		bibliotecario = new Bibliotecario("codiceFiscaleB1", "nomeB1", "cognomeB1", "indirizzoB1", 
				new Date(), "340609797", "email@Biblio1.it", 
				"passwordB1", biblioteca);
		sbu.registraUtente(bibliotecario);
	}
	
	@Test
	public void testSedeBibliotecario(){
		bibliotecario.setSedeBiblio(new Biblioteca("Biblioteca2", "ViaBiblioteca2",sbu));
		assertEquals("La biblioteca di riferimento deve essere la nuova",
				bibliotecario.getSedeBiblio().getId(), "Biblioteca2");	
	}
}
