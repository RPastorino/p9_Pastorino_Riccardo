package test_strutturali;

import static org.junit.Assert.*;  

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import gestionale.Abbonamento.TipoAbbonamento;
import gestionale.Articolo;
import gestionale.ManagerSistema;
import gestionale.Sbu;
import gestionale.Libro;
import gestionale.DocumentoDigitale;
import gestionale.Bibliotecario;
import gestionale.Biblioteca;
import gestionale.UtenteRegistrato;

public class SbuTest {
	private static Sbu sbu = null;
	private static Libro libro = null;
	private static DocumentoDigitale docDigitale = null;
	private static ManagerSistema manager = null;
	private static Bibliotecario b1 = null;
	private static Bibliotecario b2 = null;
	private static Biblioteca biblioteca = null;
	private static UtenteRegistrato ut1 = null;
	private static UtenteRegistrato ut2 = null;
	private static UtenteRegistrato ut3 = null;
	private static ArrayList<Articolo> artTrovati = null;
	
	
	@BeforeClass
	public static void setUp(){
		// 1 - Setup del test
		sbu = Sbu.getUniqueSbu("SBU");
		biblioteca = new Biblioteca("Biblioteca", "viaBteca", sbu);
		sbu.setBiblioteca(biblioteca);
		manager = new ManagerSistema("codiceFiscaleM", "NomeM", "CognomeM", 
				"IndirizzoM", new Date(), "3401236781", 
				"email@M.it", "passwordM", sbu); 
		b1 = new Bibliotecario("codiceFiscaleB1", "nomeB1", "cognomeB1", "indirizzoB1", 
				new Date(), "340609797", "email@Biblio1.it", 
				"passwordB1", biblioteca);
		b2 = new Bibliotecario("codiceFiscaleB2", "nomeB2", "cognomeB2", "indirizzoB2", 
				new Date(), "340609797", "email@Biblio2.it", 
				"passwordB2", biblioteca);
		ut1 = new UtenteRegistrato("codiceFiscaleUt1", "NomeUt1", "CognomeUt1", 
				"IndirizzoUt1", new Date(), "340435677", 
				"email@Utente1.it", "passwordUt1");
		ut2 = new UtenteRegistrato("codiceFiscaleUt2", "NomeUt2", "CognomeUt2", 
				"IndirizzoUt2", new Date(), "340435677", 
				"email@Utente2.it", "passwordUt2");
		ut3 = new UtenteRegistrato("codiceFiscaleUt3", "NomeUt3", "CognomeUt3", 
				"IndirizzoUt3", new Date(), "340435677", 
				"email@Utente3.it", "passwordUt3");
		libro = new Libro("TitoloLibro", "AutoreLibro", "GenereLibro", 
				"settoreLibro", biblioteca, "ISBN-0113-1100" , "CasaEditriceLibro", 477);
		docDigitale = new DocumentoDigitale("TitoloDoc", "AutoreDoc", "GenereDoc", 
				"tipoDoc", "formatoDoc", 399);
	}
	
	@Test
	public void testRicercaArticolo(){
		// UtenteRegistrato senza abbonamento ricerca articolo
		sbu.registraUtente(ut1);
		artTrovati = new ArrayList<Articolo>(sbu.ricercaCatalogoUnificato(ut1, "xyyttt"));
		assertTrue("Articolo non cercato e lista vuota", artTrovati.isEmpty());
	}
	
	@Test
	public void testRicercaLibroNonPresente(){
		// UtenteRegistrato senza abbonamento ricerca articolo
		sbu.registraUtente(ut1);
		artTrovati = new ArrayList<Articolo>(sbu.ricercaLibro("xyyttt"));
		assertTrue("Articolo non cercato e lista vuota", artTrovati.isEmpty());
	}
	
	@Test
	public void testRicercaLibroPresente(){
		// UtenteRegistrato senza abbonamento ricerca articolo
		sbu.registraUtente(ut1);
		biblioteca.inserisciLibro(libro);
		artTrovati = new ArrayList<Articolo>(sbu.ricercaLibro("Titol"));
		assertFalse("Articolo non cercato e lista vuota", artTrovati.isEmpty());
	}
	
	@Test
	public void testRicercaArticoloUtAbbonato1(){
		// UtenteRegistrato senza abbonamento ricerca articolo
		sbu.registraUtente(ut1);
		ut1.sottoscriviAbbonamento(TipoAbbonamento.giornaliero);
		artTrovati = new ArrayList<Articolo>(sbu.ricercaCatalogoUnificato(ut1, "xyyttt"));
		assertTrue("Articolo non cercato e lista vuota", artTrovati.isEmpty());
	}
	
	@Test
	public void testRicercaArticoloUtAbbonato2(){
		// UtenteRegistrato senza abbonamento ricerca articolo
		sbu.registraUtente(ut1);
		biblioteca.inserisciLibro(libro);
		biblioteca.inserisciDocDigitale(docDigitale);
		ut1.sottoscriviAbbonamento(TipoAbbonamento.giornaliero);
		artTrovati = new ArrayList<Articolo>(sbu.ricercaCatalogoUnificato(ut1, "xyyttt"));
		assertTrue("Articolo non cercato e lista vuota", artTrovati.isEmpty());
	}
	
	@Test
	public void testRicercaArticoloUtAbbonato3(){
		// UtenteRegistrato senza abbonamento ricerca articolo
		sbu.registraUtente(ut1);
		biblioteca.inserisciLibro(libro);
		biblioteca.inserisciDocDigitale(docDigitale);
		ut1.sottoscriviAbbonamento(TipoAbbonamento.giornaliero);
		artTrovati = new ArrayList<Articolo>(sbu.ricercaCatalogoUnificato(ut1, "Aut"));
		assertTrue("uno o più articoli trovati", artTrovati.contains(libro));
	}
	
	@Test
	public void testRicercaArticoloBiblio1(){
		// Bibliotecario ricerca articolo con catalogo vuoto
		sbu.registraUtente(b1);
		artTrovati = new ArrayList<Articolo>(sbu.ricercaCatalogoUnificato(b1, "xyyttt"));
		assertTrue("Articolo non trovato, lista vuota", artTrovati.isEmpty());
	}
	
	@Test
	public void testRicercaArticoloBiblio2(){
		// Bibliotecario ricerca articolo con catalogo non vuoto 
		// ma stringa non contenuta
		sbu.registraUtente(b1);
		biblioteca.inserisciLibro(libro);
		biblioteca.inserisciDocDigitale(docDigitale);
		artTrovati = new ArrayList<Articolo>(sbu.ricercaCatalogoUnificato(b1, "xyyttt"));
		assertTrue("Articolo non presente", artTrovati.isEmpty());
	}
	@Test
	public void testRicercaArticoloBiblio3(){
		// Bibliotecario ricerca articolo con catalogo non vuoto
		// e stringa contenuta
		sbu.registraUtente(b1);
		biblioteca.inserisciLibro(libro);
		biblioteca.inserisciDocDigitale(docDigitale);
		artTrovati = new ArrayList<Articolo>(sbu.ricercaCatalogoUnificato(b1, "Titol"));
		assertTrue("uno o più articoli trovati", artTrovati.contains(libro));
	}
	
	@Test
	public void testRicercaArticoloManager1(){
		// Bibliotecario ricerca articolo con catalogo vuoto
		sbu.registraUtente(manager);
		artTrovati = new ArrayList<Articolo>(sbu.ricercaCatalogoUnificato(manager, "xyyttt"));
		assertTrue("Articolo non trovato, lista vuota", artTrovati.isEmpty());
	}
	
	@Test
	public void testRicercaArticoloManager2(){
		// Bibliotecario ricerca articolo con catalogo non vuoto 
		// ma stringa non contenuta
		sbu.registraUtente(manager);
		biblioteca.inserisciLibro(libro);
		biblioteca.inserisciDocDigitale(docDigitale);
		artTrovati = new ArrayList<Articolo>(sbu.ricercaCatalogoUnificato(manager, "xyyttt"));
		assertTrue("Articolo non presente", artTrovati.isEmpty());
	}
	@Test
	public void testRicercaArticoloManager3(){
		// Bibliotecario ricerca articolo con catalogo non vuoto
		// e stringa contenuta
		sbu.registraUtente(manager);
		biblioteca.inserisciLibro(libro);
		biblioteca.inserisciDocDigitale(docDigitale);
		artTrovati = new ArrayList<Articolo>(sbu.ricercaCatalogoUnificato(manager, "Titol"));
		assertTrue("uno o più articoli trovati", artTrovati.contains(libro));
	}

	@Test
	public void testSbu() {
		assertNotNull("sbu non deve essere null", sbu);
	}
	
	@Test
	public void testRegistraManager(){
		// L'utente non e' presente in sbu
		assertTrue("Manager rimosso da sistema", sbu.rimuoviUtente(manager));
		boolean condition = sbu.registraUtente(manager);
		assertTrue("Manager registrato correttamente", condition);
	}

	@Test
	public void testRegistraUtente(){
		// L'utente non e' presente in sbu
		sbu.rimuoviUtente(ut1);
		assertFalse("Ut1 non in sbu", sbu.getUtentiRegistrati().contains(ut1));
		boolean condition = sbu.registraUtente(ut1);
		assertTrue("L'utente deve essere inserito nel sistema", condition);
	}
	
	@Test
	public void testRegistraUtentePresente(){
		// L'utente gia' presente in sbu
		sbu.registraUtente(ut1);
		boolean condition = sbu.registraUtente(ut1);
		assertFalse("L'utente non deve essere inserito nel sistema",condition);
	}
	
	@Test
	public void testRegistraUtenteStessoCf(){
		// utente con codice fiscale uguale gia presente in sbu
		sbu.registraUtente(ut2);
		assertFalse("L'utente non deve essere inserito nell'sbu",
				sbu.registraUtente(new UtenteRegistrato("codiceFiscaleUt2", "xyz", "xyz", 
						"xyz", new Date(), "000", 
						"xyz@xyz.it", "xyz")));
	}
	@Test
	public void testRegistraBibliotecario(){
		// Bibliotecario non e' presente in sbu
		boolean condition = sbu.registraUtente(b1);
		assertTrue("L'utente deve essere inserito nell'sbu", condition);
	}
	
	@Test
	public void testRegistraBiblioStessoCf(){
		// Bibliotecario e' presente in sbu
		sbu.registraUtente(b2);
		assertFalse("Il bibliotecario non deve essere inserito nell'sbu",
						sbu.registraUtente(new Bibliotecario("codiceFiscaleB2", "aaaa", "aaaaa", 
								"aaaaa", new Date(), "0001111", 
								"email@yyyy.it", "xyzw", biblioteca)));
	}
	
	@Test
	public void testRegistraBibliotecarioStessaMail(){
		// Bibliotecario e' presente in sbu
		sbu.registraUtente(b2);
		assertFalse("Il bibliotecario non deve essere inserito nell'sbu",
						sbu.registraUtente(new Bibliotecario("aaaa", "aaaa", "aaaaa", 
								"aaaaa", new Date(), "0001111", 
								"email@Biblio2.it", "xyzw", biblioteca)));
	}
	
	@Test
	public void testRegistraUtenteStessaMail(){
		// utente con email uguale gia presente in sbu
		sbu.registraUtente(ut3); 
		assertFalse("L'utente non deve essere inserito nell'sbu ",
				sbu.registraUtente(new UtenteRegistrato("xxx", "xxx", "xxx", 
						"xxx", new Date(), "01010", 
						"email@Utente3.it", "passwordUt3")));
	}

	@Test
	public void testRimuoviUtente(){
		// L'utente e' presente in sbu
		sbu.registraUtente(ut1);
		boolean condition = sbu.rimuoviUtente(ut1);
		assertTrue("L'utente deve essere rimosso dall'sbu", condition);
	}
	
	@Test
	public void testRimuoviBiblio(){
		// L'utente e' presente in sbu
		sbu.registraUtente(b1);
		boolean condition = sbu.rimuoviUtente(b1);
		assertTrue("Il bibliotecario deve essere rimosso dall'sbu", condition);
	}
	
	@Test
	public void testRimuoviManager(){
		// Manager presente nel sistema
		sbu.rimuoviManager();
		assertNull("Manager deve essere rimosso nel sistema",
				sbu.getManager());
	}
	
	@Test
	public void testRimuoviUtenteNonPresente(){
		// L'utente non e' presente nel sistema 
		sbu.rimuoviUtente(ut1);
		assertFalse("L'utente NON deve essere rimosso nel sistema", sbu.rimuoviUtente(ut1));
	}
	
	@Test
	public void testRimuoviBiblioNonPresente(){
		// L'utente non e' presente nel sistema 
		sbu.rimuoviUtente(b1);
		assertFalse("L'utente NON deve essere rimosso nel sistema", sbu.rimuoviUtente(b1));
	}
	
	@Test
	public void testRimuoviManagerNonPresente(){
		// L'utente non e' presente nel sistema 
		sbu.rimuoviUtente(manager);
		assertFalse("L'utente NON deve essere rimosso nel sistema", sbu.rimuoviUtente(manager));
	}

	@Test
	public void testId(){
		String id = "nuovoIdSBU";
		sbu.setId(id);
		assertEquals("L'identificativo deve essere cambiato con il nuovo", id, 
				sbu.getId());
	}
	
	@Test
	public void testGetManager(){
		sbu.registraUtente(manager);
		assertNotNull("manager non deve esser null", 
				sbu.getManager());
	}
	
	@Test
	public void testGetListaConsigli(){
		assertNotNull("lista dei consigliati non deve esser null", 
				sbu.getListaConsigliati());
	}

	@Test
	public void testGetRecensioni(){
		assertNotNull("La lista delle recensioni non deve esser null",
				sbu.getRecensioni());
	}

	@Test
	public void testGetBiblioteche(){
		assertNotNull("La lista dele Biblioteche non deve esser null", 
				sbu.getBiblioteche());
	}
	
	@Test
	public void testGetBibliotecari(){
		assertNotNull("La lista degli utenti non deve esser null", 
				sbu.getBibliotecari());
	}

	@Test
	public void testGetUtentiRegistrati(){
		assertNotNull("La lista degli utenti non deve esser null", 
				sbu.getUtentiRegistrati());
	}
	
	@After
	public void after(){
		sbu = Sbu.getUniqueSbu("SBU");
	}
}
