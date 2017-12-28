INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('Azienda');
INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('Studente');
INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('Segreteria');
INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('Presidente');
INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('TutorInterno');

INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('filomena@unisa.it', '123123123', 'Via del Presidente 1', '0815554689', 'img1', 'Presidente');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('stefano@unisa.it', '321321321', 'Via del Segretario', '0325568798', 'img2', 'Segreteria');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('alfredo@unisa.it', '312312312', 'Via del Presidente 2', '065987855', 'img3', 'Presidente');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('pippo@unisa.it', '312312312', 'Via del Tutor 2', '065987855', 'img3', 'TutorInterno');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('mario@studenti.it', '312312312', 'Via dello Studente 2', '065987855', 'img3', 'Studente');

INSERT INTO `libra`.`Segreteria` (`utenteEmail`, `giorniDiRicevimento`) VALUES ('stefano@unisa.it', '{\"LUN\":\"9.30-11.30\",\"MER\":\"9.30-11.30 14.30-18.30\"}');
INSERT INTO `libra`.`Presidente` (`utenteEmail`, `cognome`, `nome`, `dataDiNascita`, `giorniDiRicevimento`, `ufficio`, `linkSito`) VALUES ('filomena@unisa.it', 'Ferrucci', 'Filomena', '1992-08-15 00:00:00', '{\"MAR\":\"14.30-15.30\",\"VEN\":\"8.30-10.30 14.30-16.30\"}', 'M44', 'https://www.facebook.com/fferrucci');
INSERT INTO `libra`.`Presidente` (`utenteEmail`, `cognome`, `nome`, `dataDiNascita`, `giorniDiRicevimento`, `ufficio`, `linkSito`) VALUES ('alfredo@unisa.it', 'De Santis', 'Alfredo', '1992-08-15 00:00:00', '{\"MER\":\"11.00-13.00 15.00-18.00\",\"GIO\":\"8.30-10.30\"}', 'L37', 'https://www.facebook.com/adesantis');

INSERT INTO `libra`.`Utente` (`email`, `imgProfilo`, `indirizzo`, `password`, `telefono`, `ruolo`) VALUES ('azienda@prova.it', 'xxx', 'Via delle Aziende', '123123', '0892221113', 'Azienda');
INSERT INTO `libra`.`Azienda` (`utenteEmail`, `nome`, `partitaIVA`, `sede`) VALUES ('azienda@prova.it', 'Azienda', 'PartIva', 'Trivio');
INSERT INTO `libra`.`Tutoresterno` (`ambito`, `aziendaEmail`, `cognome`, `nome`, `telefono`, `indirizzo`, `dataDiNascita`) VALUES ('ambito', 'azienda@prova.it', 'Brazorf', 'Ajeje', '1112223331', 'via Roma, 1', '1992-08-15 00:00:00');
INSERT INTO `libra`.`Tutorinterno` (`utenteEmail`, `cognome`, `nome`, `dataDiNascita`, `linkSito`) VALUES ('pippo@unisa.it', 'Cattaneo', 'Pippo', '1900-01-01', 'www.unisa.it');
INSERT INTO `libra`.`Studente` (`utenteEmail`, `matricola`, `cognome`, `nome`, `dataDiNascita`) VALUES ('mario@studenti.it', '0215103555', 'Ruggiero', 'Mario', '1995-05-11');

INSERT INTO `libra`.`progettoformativo` (`stato`, `dataInizio`, `dataFine`, `ambito`, `note`, `documento`, `motivazioneRifiuto`, `periodoReport`, `studenteEmail`, `aziendaEmail`, `tutorInternoEmail`, `dataInvio`) VALUES (4,'2017-08-01', '2017-10-05','Biometria', '', 'doc.pdf','', 3, 'mario@studenti.it', 'azienda@prova.it', 'pippo@unisa.it', '2017-09-23'); 

INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('La durata del tirocinio è stata adeguata agli obiettivi formativi', 'Azienda');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('Gli obiettivi formativi previsti sono stati raggiunti', 'Azienda');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('La collaborazione con il tutor didattico è stata', 'Azienda');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('Il tirocinio formativo è utile all''Ente Ospitante', 'Azienda');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('Competenze informatiche possedute in ingresso', 'Azienda');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('Competenze acquisite al termine del tirocinio nella specifica disciplina', 'Azienda');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('Motivazione e interesse', 'Azienda');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('Soft skill(capacità di relazionarsi, comunicare, lavorare in team)', 'Azienda');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('Hanno fornito informazioni chiare ed esaustive', 'Azienda');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('Hanno fornito assistenza e disponibilità', 'Azienda');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('I servizi/informazioni forniti via Web sono esaustivi', 'Azienda');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('Note', 'Azienda');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('Le attività scelte sono state coerenti con le conoscenze possedute', 'Studente');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('Il tirocinio ha migliorato la formazione tecnica', 'Studente');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('Il tirocinio ha migliorato le soft skill', 'Studente');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('La durata del tirocinio è stata adeguata agli obiettivi del progetto', 'Studente');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('Valutazione complessiva dell''esperienza', 'Studente');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('Mansioni assegnate', 'Studente');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('Ambiente di lavoro', 'Studente');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('Competenze tecniche presenti', 'Studente');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('Logistica e supporto strumentale', 'Studente');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('Assistenza del tutor Ente ospitante', 'Studente');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('Le strutture universitarie addette ai Tirocini hanno fornito informazioni chiare ed esaustive', 'Studente');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('Le strutture universitatrie addette ai Tirocini hanno fornito assistenza e disponibilità', 'Studente');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('I servizi/informazioni fornite via Web sono esaustive', 'Studente');
INSERT INTO `libra`.`domanda`(`testo`, `tipo`) VALUES ('Note', 'Studente');

INSERT INTO `libra`.`hibernate_sequence`(`next_val`) VALUES(0);