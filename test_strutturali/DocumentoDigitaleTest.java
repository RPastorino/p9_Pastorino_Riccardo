package test_strutturali;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import gestionale.Biblioteca;
import gestionale.DocumentoDigitale;
import gestionale.Sbu;

public class DocumentoDigitaleTest {
	private static Sbu sbu = null;
	private static Biblioteca biblioteca = null;
	private static DocumentoDigitale dg = null;
	
	@BeforeClass
	public static void setUp() {
		// 1 - Setup del test
		sbu = Sbu.getUniqueSbu("SBU");
		biblioteca = new Biblioteca("Biblioteca", "ViaBiblioteca",sbu);
		sbu.getBiblioteche().add(biblioteca);
		dg = new DocumentoDigitale("TitoloDoc", "AutoreDoc", "GenereDoc", "TipoDoc", "FormatoDoc", 377);
	}
	
	@Test
	public void testSetGetFormato() {
		String formatoDoc = "pdf";
		dg.setFormato(formatoDoc);
		assertEquals("formato documento deve essere pdf", formatoDoc, 
				dg.getFormato());
	}
	
	@Test
	public void testSetGetTipo() {
		String tipoDoc = "Magazine";
		dg.setTipo(tipoDoc);
		assertEquals("tipo documento deve essere Magazine", tipoDoc, 
				dg.getTipo());
	}
	
}
