package gestionale;

import gestionale.Abbonamento.TipoAbbonamento;
import java.util.Date;
import java.util.List; 
import java.util.ArrayList;

/** 
 * Classe Main per la messa in esecuzione del sistema
 * 
 * @author Riccardo Pastorino
 * @version 1.0
 * 
 */
public class Main {

	/**
     * metodo main
     *
     * @param args argomenti
     */
	public static void main(String[] args) {
		System.out.println("Istanzio il sistema SBU");
		Sbu sbu = Sbu.getUniqueSbu("SBU");
		System.out.println("SBU creato");
		// ****************************************************		
		System.out.println("Creo e registro Manager del Sistema");
		sbu.registraUtente(new ManagerSistema("codiceFiscaleM", "NomeM", "CognomeM", 
				"IndirizzoM", new Date(), "34000000", "email@M.it", "password", sbu));
		// ****************************************************		
		System.out.println("creo nuova Biblioteca");
		Biblioteca bibliotecaProva = new Biblioteca("nomeBib", "Via Bib",
				sbu);
		sbu.getBiblioteche().add(bibliotecaProva);
		System.out.println("Biblioteca creata");
		// ****************************************************		
		System.out.println("Inserisco nuovo documento digitale nell biblioteca di prova");
		bibliotecaProva.inserisciDocDigitale(new DocumentoDigitale("TitoloDoc", "AutoreDoc", "GenereDoc", 
				"TipoDoc", "FormatoDoc", 567));
		System.out.println("Documento digitale inserito");
		// ****************************************************		
		System.out.println("Inserisco nuovo libro nell biblioteca di prova");
		bibliotecaProva.inserisciLibro(new Libro("TitoloLibro", "AutoreLibro", "GenereLibro", 
				"CollocazioneLib", bibliotecaProva, "ISBNLib", "CasaEditLib", 458 ));
		System.out.println("Libro inserito");
		// ****************************************************		
		System.out.println("Registro nuovo Bibliotecario ( UC40 )");
		sbu.registraUtente(new Bibliotecario("codiceFiscaleBiblio", "NomeBiblio", "CognomeBiblio", 
				"IndirizzoBiblio", new Date(), "340584574", 
				"email@Biblio.it", "passwordB", bibliotecaProva));
		// ****************************************************		
		System.out.println("Registro nuovo Utente");
		sbu.registraUtente(new UtenteRegistrato("codiceFiscaleUtente", "NomeUtente", "CognomeUtente", 
				"IndirizzoUtente", new Date(), "340584574", 
				"email@Utente.it", "passwordU"));
		// ****************************************************		
		System.out.println("Ricerco Libro di 'Titol' ( UC3 )");
		Utente utente = sbu.getBibliotecari().get(0);
		String inputDiRicerca = "Titol";
		List<Articolo> articoliTrovati = new ArrayList<Articolo>();
		articoliTrovati = sbu.ricercaCatalogoUnificato(utente, inputDiRicerca);
		for (Articolo a:articoliTrovati) {
			System.out.println("articolo" + (articoliTrovati.indexOf(a)+1) + ": " + a.getTitolo());
		}
		// ****************************************************		
		System.out.println("Login Bibliotecario ( UC1 )");
		String inputEmailB = "email@Biblio.it";
		String inputPasswordB = "passwordB";
		sbu.login(inputEmailB, inputPasswordB);
		// ****************************************************		
		System.out.println("Login Utente (UC1)");
		String inputEmailU = "email@Utente.it";
		String inputPasswordU = "passwordU";
		utente = sbu.login(inputEmailU, inputPasswordU);
		// ****************************************************
		System.out.println("Sottoscrizione abbonamento Giornaliero da parte di utente ( UC37 )");
		UtenteRegistrato utenteReg = ((UtenteRegistrato)sbu.getUtentiRegistrati().get(0));
		utenteReg.sottoscriviAbbonamento(TipoAbbonamento.giornaliero);
		Abbonamento abb = utenteReg.getAbbonamento();
		System.out.print("Abbonamento giornaliero valido da: " + abb.getDataInizio());
		System.out.println( " a: " + abb.getDataFine());
		// ****************************************************	
		System.out.println("Sottoscrizione abbonamento Annuale da parte di utente ( UC37 )");
		utenteReg.sottoscriviAbbonamento(TipoAbbonamento.annuale);
		System.out.println("( impossibilita' di sottoscrivere abbonamento in quanto l'utente "
		+ "ne ha precedentemente sottoscritto uno non ancora scaduto )" );
		// ****************************************************		
		System.out.println("Sottoscrizione abbonamento Annuale da parte di utente ( UC37 )");
		UtenteRegistrato utenteReg2 = new UtenteRegistrato("codiceFiscaleUtente", 
				"NomeUtenteReg2", "CognomeUtenteReg2", "IndirizzoUtenteReg", new Date(), "34955684574", 
				"email@UtenteReg2.it", "passwordUR2");
		sbu.registraUtente(utenteReg2);
		utenteReg2.sottoscriviAbbonamento(TipoAbbonamento.annuale);
		Abbonamento abbAnn = utenteReg2.getAbbonamento();
		System.out.print("Abbonamento annuale valido da: " + abbAnn.getDataInizio());
		System.out.println( " a: " + abbAnn.getDataFine());
		// ****************************************************		
		System.out.println("Accesso a documenti digitali per utente abbonato ( UC38 )");
		List<Articolo> docTrovati = new ArrayList<Articolo>();
		docTrovati = sbu.ricercaCatalogoUnificato(utenteReg2, "Titol");
		if( docTrovati == null )
			System.out.println("lista documenti digitali null");
		System.out.println("visualizzazione documento digitale trovati");
		for( Articolo doc: docTrovati){
			System.out.println("documento digitale" + (docTrovati.indexOf(doc)+1) + ": " + doc.getTitolo());
		}
		// ****************************************************		
		System.out.println("Ricerca utente 'Cognom' ( UC46 )");
		String inRicercaUtente = "Cognom";
		List<Utente> utentiTrovati = new ArrayList<Utente>();
		utentiTrovati = sbu.ricercaUtente(inRicercaUtente);
		for (Utente u:utentiTrovati) {
			System.out.println("Trovato " + u.getNome() + " " + u.getCognome());
		}
		// ****************************************************		
		System.out.println("Prenotazione Libro 'TitoloLibro' da 'NomeUtente' ( UC20 )");
		String inputRicercaLibro = "TitoloLibro";
		UtenteRegistrato prenotante = utenteReg;
		Libro libro = ((Libro)sbu.ricercaCatalogoUnificato(utenteReg,inputRicercaLibro).get(0));
		libro.prenota(prenotante);
		System.out.println("Libro prenotato");
		// ****************************************************
		System.out.println("Inserimento Recensione di 'NomeUtente' "
				+ "in 'TitoloLibro' ( UC25 ), tentativo con recensione troppo lunga");
		UtenteRegistrato recensorePocoAttento = prenotante;
		String recensioneTroppoLunga = "Ottimo Libro, acquisto consigliato per gli amanti del genere";
		sbu.ricercaCatalogoUnificato(utenteReg,inputRicercaLibro).get(0).scriviRecensione(recensioneTroppoLunga, recensorePocoAttento);
		// ****************************************************
		System.out.println("Inserimento Recensione di 'NomeUtente' "
				+ "in 'TitoloLibro' ( UC25 )");
		UtenteRegistrato recensore = utenteReg;
		String recensione = "Ottimo Libro";
		sbu.ricercaCatalogoUnificato(utenteReg,inputRicercaLibro).get(0).scriviRecensione(recensione, recensore);
		System.out.println("Recensione inserita");
		// ****************************************************
		System.out.println("Inserimento Recensione di 'NomeUtenteReg2' "
				+ "in 'TitoloLibro' ( UC25 )");
		UtenteRegistrato recensore2 = utenteReg2;
		String recensione2 = "Pessimo Libro, acquisto sconsigliato";
		sbu.ricercaCatalogoUnificato(utenteReg,inputRicercaLibro).get(0).scriviRecensione(recensione2, recensore2);
		System.out.println("Recensione inserita");
		System.out.println("Il libro 'TitoloLibro' ha ora 2 recensioni");
		// ****************************************************
		System.out.println("Visualizzazione Recensioni di 'TitoloLibro' ( UC26 )");
		System.out.println("Ricerco prima l'articolo nel catalogo unificato");
		for (Recensione recens: sbu.ricercaCatalogoUnificato(utenteReg2, "TitoloLibro").get(0).getRecensioni()) {
			System.out.println(recens.getAutore().getNome() + " " + 
					recens.getAutore().getCognome() + ": " + recens.getTesto());
		}
	}

}
