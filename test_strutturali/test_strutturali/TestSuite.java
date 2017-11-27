package test_strutturali;	

import org.junit.runner.RunWith;  
import org.junit.runners.Suite;

@Suite.SuiteClasses({SbuTest.class, ArticoloTest.class, SbuLoginTest.class,
	SbuRicercaUtenteTest.class, LibroTest.class, PrestitoTest.class, RecensioneTest.class,
	BibliotecaTest.class, UtenteRegistratoTest.class, AbbonamentoTest.class,
	UtenteTest.class, TipoAbbonamentoTest.class, BibliotecarioTest.class, 
	ManagerSistemaTest.class, DocumentoDigitaleTest.class})

@RunWith(Suite.class)
public class TestSuite {

}
