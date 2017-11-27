package test_strutturali;

import static org.junit.Assert.*;

import gestionale.Abbonamento.TipoAbbonamento;

import org.junit.Test;

public class TipoAbbonamentoTest {

	@Test
	public void testGiornaliero() {
		assertEquals("giornaliero", TipoAbbonamento.giornaliero.toString());
	}
	
	@Test
	public void testMensile() {
		assertEquals("mensile", TipoAbbonamento.mensile.toString());
	}
	
	@Test
	public void testAnnuale() {
		assertEquals("annuale", TipoAbbonamento.annuale.toString());
	}

}
