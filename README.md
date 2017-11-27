# GESTIONE BIBLIOTECA
Per l'esecuzione del progetto:
1 - scaricare la master directory;
2 - individuare la directory src all'interno di quanto scaricato;
3 - importare il contenuto di src in eclipse;
4 - Mandare in esecuzione la classe eseguibile Main;

Per l'esecuzione dei test Case:
1 - importare la cartella test_funzionali contenuta nella mastery;
2 - importare la cartella test_strutturali contenuta nella mastery;
3 - eseguire La TestSuite contenuta in test_funzionali per eseguire tutti i test funzionali;
4 - eseguire La TestSuite contenuta in test_strutturali per eseguire tutti i test strutturali;

NOTA BENE:
La percentuale di coverage è inferiore al 100% (ovviamente la classe main non è testata).
Nella classe Abbonamento è stato utilizzato un tipo enumerativo, definito come TipoAbbonamento,
dopo un pò di studio ho capito che il tipo Enum, estendendo java.lang.Enum, andava testato essendo questi che abbassava
ed era la presenza det tipo enumerativo che abbassava la percentuale di coverage della classe abbonamento.
Dopo un pò di ricerche ho creato un test apposito per il tipo enumerativo con copertura del 100% ma nonostante questo il solo tipo enumerativo risulta fermo ad una copertura dell 65% (percentuale invariata anche dopo la creazione del test ad hoc).
Nonostante il tentativo di cercare ulteriori informazioni in merito al testing di semplici tipi enum, la classe abbonamento risulta 
attualmente ancora non totalmente coperta.
Interessante notare che il tipo enum è incredibilmente semplice, consta di sole 3 costanti e non ha alcun altro componente e
tanto meno metodi interni, fatto che rende ancor più strano il mancato coverage totale a seguito del test.
