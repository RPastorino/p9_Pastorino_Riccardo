package test_funzionali;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import gestionale.Articolo;
import gestionale.Biblioteca;
import gestionale.Libro;
import gestionale.Sbu;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)

public class TestUC3 {
	private static Sbu sbu = null;
	private static Biblioteca biblioteca = null;
	private static Libro libro = null;
	
	@Parameters
	public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"Titol", true},
            {"yyyyy", false},
            {"Autor", true},
            {"yyyyy", false},
            {"yyyyy", false},
            {"Gener", true}
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
	}
	
	@Parameter(0)
	public String stringaRicerca;
	
	@Parameter(1)
	public boolean expectedResult;
	
	@Test
	public void test() {
		assertNotNull("libro istanziato", libro);
		ArrayList<Articolo> listaTrovati = new ArrayList<>(sbu.ricercaLibro(stringaRicerca));
		// 5 - Check del risultato
		assertEquals(listaTrovati.contains(libro), expectedResult);
	}
}

