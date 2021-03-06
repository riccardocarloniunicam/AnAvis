<b>ANAVIS.</b>

<p>L'applicazione permette ad un utente con ruolo iniziale USER di iscriversi al servizio.
Inizialmente nella "Home" avrà solo la possibilità di vedere le informazioni fornite durante la registrazione, un messaggio di benvenuto che enuncia di compilare il modulo di "AnAvis" per essere approvato.
Una volta compilato il modulo nella home apparira un messaggio in cui dirà all'utente che è sotto revisione da parte delle sedi e deve attendere.
Ogni utente può mandare un solo modulo.

Nel pannello sedi intanto, si ha la possibilità di vedere tutti i moduli in attesa di approvazione in una tabella, dove per ogni riga si può accedere alle informazioni complete di un utente e infine la sede deciderà se renderlo donatore o respingere il modulo.
Nel caso di una declinazione, all'utente ricomparirà la possibilità di compilare ancora una volta il modulo.
Nel caso dell'approvazione un messaggio di successo apparirà nella home dicendo all'utente che al prossimo login sarà un donatore effettivo.


Al login successivo nella home, il vecchio user diventato donatore potrà:
- [x] Vedere le news pubblicate dalle sedi.
- [x] Effettuare una prenotazione.
- [x] Cancellare una prenotazione.
- [x] Visualizzare i suoi dati.
- [x] Visualizzare il suo storico analisi.


Nel caso in cui l'utente effettua una prenotazione, sceglierà il giorno e la sede, e verranno restituiti tutti gli orari disponibili per quel giorno.
Un utente può effettuare solamente una prenotazione alla volta.

Nel pannello delle sedi, nella home troveremo un count di:
- [x] Appuntamenti in sede
- [x] Appuntamenti eseguiti
- [x] Moduli in attesa di approvazione
- [x] Analisi da inviare.


Nel pannello delle sedi("/prenotazioni/sede/) avremo un menù che consente a essa di visualizzare gli appuntamenti nella propria sede.
Gli appuntamenti sono ordinati per giorno e ora e si hanno le seguenti possibilità:
- [x] Vedere le info complete dell'utente che ha prenotato.
- [x] Cancellare la prenotazione, inviando un messaggio all'utente che comparirà nella sua "Home".
- [x] Segnare l'appuntamento "Eseguito".





Se l'appuntamento viene eseguito, sarà spostato nella sezione "Appuntamenti eseguiti" e si alzerà il count delle analisi da inviare.

Nella sezione apposita si visualizzeranno tutti gli appuntamenti eseguiti ordinati per data e per "Risultato delle analisi" non inviato e si potra:

- [x] Cercare per nome.
- [x] Caricare Analisi delle prenotazione selezionata.

In ogni riga, si avrà un tasto "carica" dove si andrà in /inserisci-analisi dove la sede dovrà caricare un file.pdf e si può anche allegare una nota perl 'utente.

Una volta inviato ritornerà un messaggio di successo e la l'analisi risulterà come "inviata".

Nel frattempo, il donatore potrà andare nella sezione "Storico analisi" per visualizzare tutti i suoi risultati (file) che potrà scaricare e la relativa nota in allegato.</p>

<h3>Per il programma sono stati utilizzati:</h3><br>
- [x] Java
- [x] Javascript
- [x] Jquery
- [x] HTML5
- [x] CSS
<h3>Framework:</h3><br>
- [x] Springboot
- [x] SpringSecuirty
- [x] SpringWeb
- [x] MySqlConnector
- [x] JpaDatasource
- [x] Thymeleaf


Per lanciare il programma

```java
  cd AnAvis/target
java -jar filename-0.0.1-SNAPSHOT.jar
```


Il programma sarà disponibile all'indirizzo: http://localhost:8081/

