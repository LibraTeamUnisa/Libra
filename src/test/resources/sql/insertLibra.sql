INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('Azienda');
INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('Studente');
INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('Segreteria');
INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('Presidente');
INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('TutorInterno');

INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('filomena@unisa.it', '123123123', 'Via del Presidente 1', '0815554689', 'assets/images/users/1.jpg', 'Presidente');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('stefano@unisa.it', '321321321', 'Via del Segretario', '0325568798', 'img2', 'Segreteria');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('alfredo@unisa.it', '312312312', 'Via del Presidente 2', '065987855', 'img3', 'Presidente');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('pippo@unisa.it', '312312312', 'Via del Tutor 2', '065987855', 'img3', 'TutorInterno');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('mario@studenti.it', '312312312', 'Via dello Studente 2', '065987855', 'img3', 'Studente');
INSERT INTO `libra`.`utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('a.piccolella1@studenti.unisa.it', 'angelopiccolella', 'via G. Galilei 20, Capodrise', '3381798928', 'null', 'Studente');
INSERT INTO `libra`.`Utente` (`email`, `imgProfilo`, `indirizzo`, `password`, `telefono`, `ruolo`) VALUES ('azienda@prova.it', 'xxx', 'Via delle Aziende', '123123123', '0892221113', 'Azienda');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('michele@unisa.it', '312312312', 'Via dello Studente', '065987855', 'img3', 'Studente');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('android@google.com', '312312312', 'Via dell azienda', '065987855', 'img3', 'Azienda');

INSERT INTO `libra`.`Segreteria` (`utenteEmail`, `giorniDiRicevimento`) VALUES ('stefano@unisa.it', '{\"LUN\":\"9.30-11.30\",\"MER\":\"9.30-11.30 14.30-18.30\"}');
INSERT INTO `libra`.`Presidente` (`utenteEmail`, `cognome`, `nome`, `dataDiNascita`, `giorniDiRicevimento`, `ufficio`, `linkSito`) VALUES ('filomena@unisa.it', 'Ferrucci', 'Filomena', '1992-08-15 00:00:00', '{\"MAR\":\"14.30-15.30\",\"VEN\":\"8.30-10.30 14.30-16.30\"}', 'M44', 'https://www.facebook.com/fferrucci');
INSERT INTO `libra`.`Presidente` (`utenteEmail`, `cognome`, `nome`, `dataDiNascita`, `giorniDiRicevimento`, `ufficio`, `linkSito`) VALUES ('alfredo@unisa.it', 'De Santis', 'Alfredo', '1992-08-15 00:00:00', '{\"MER\":\"11.00-13.00 15.00-18.00\",\"GIO\":\"8.30-10.30\"}', 'L37', 'https://www.facebook.com/adesantis');
INSERT INTO `libra`.`Studente` (`utenteEmail`, `cognome`, `dataDiNascita`, `matricola`, `nome`) VALUES ('michele@unisa.it', 'Spano', '1996-06-09 00:00:00', '0512103861', 'Michele');
INSERT INTO `libra`.`Azienda` (`utenteEmail`, `nome`, `partitaIVA`, `sede`) VALUES ('android@google.com', 'Google', 'cdcxfvhyipb', 'Mountain View');
INSERT INTO `libra`.`TutorInterno` (`utenteEmail`, `cognome`, `dataDiNascita`, `linkSito`, `nome`) VALUES ('pippo@unisa.it', 'Cattaneo', '1992-08-15 00:00:00','https://www.facebook.com/pcattaneo', 'Giuseppe');
INSERT INTO libra.ProgettoFormativo (`aziendaEmail`,`studenteEmail`,`tutorInternoEmail`,`ambito`, stato,documento,`periodoReport`, `dataInvio`) VALUES ('android@google.com','michele@unisa.it', 'pippo@unisa.it', 'qualcosa','1','\\','0', '2017-11-27 00:00:00');
insert into hibernate_sequence(next_val) values(0);

INSERT INTO `libra`.`Azienda` (`utenteEmail`, `nome`, `partitaIVA`, `sede`) VALUES ('azienda@prova.it', 'Azienda', 'PartIva', 'Trivio');
INSERT INTO `libra`.`Tutoresterno` (`ambito`, `aziendaEmail`, `cognome`, `nome`, `telefono`, `indirizzo`, `dataDiNascita`) VALUES ('ambito', 'azienda@prova.it', 'Brazorf', 'Ajeje', '1112223331', 'via Roma, 1', '1992-08-15 00:00:00');
INSERT INTO `libra`.`Studente` (`utenteEmail`, `matricola`, `cognome`, `nome`, `dataDiNascita`) VALUES ('mario@studenti.it', '0215103555', 'Ruggiero', 'Mario', '1995-05-11');
INSERT INTO `libra`.`studente` (`utenteEmail`, `matricola`, `cognome`, `nome`, `dataDiNascita`) VALUES ('a.piccolella1@studenti.unisa.it', '0512103817', 'Piccolella', 'Angelo', '1996/07/09');

INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (1,'La durata del tirocinio è stata adeguata agli obiettivi formativi', 'Azienda');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (2,'Gli obiettivi formativi previsti sono stati raggiunti', 'Azienda');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (3,'La collaborazione con il tutor didattico è stata', 'Azienda');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (4,'Il tirocinio formativo è utile all''Ente Ospitante', 'Azienda');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (5,'Competenze informatiche possedute in ingresso', 'Azienda');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (6,'Competenze acquisite al termine del tirocinio nella specifica disciplina', 'Azienda');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (7,'Motivazione e interesse', 'Azienda');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (8,'Soft skill(capacità di relazionarsi, comunicare, lavorare in team)', 'Azienda');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (9,'Hanno fornito informazioni chiare ed esaustive', 'Azienda');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (10,'Hanno fornito assistenza e disponibilità', 'Azienda');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (11,'I servizi/informazioni forniti via Web sono esaustivi', 'Azienda');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (12,'Note', 'Azienda');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (13,'Le attività scelte sono state coerenti con le conoscenze possedute', 'Studente');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (14,'Il tirocinio ha migliorato la formazione tecnica', 'Studente');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (15,'Il tirocinio ha migliorato le soft skill', 'Studente');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (16,'La durata del tirocinio è stata adeguata agli obiettivi del progetto', 'Studente');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (17,'Valutazione complessiva dell''esperienza', 'Studente');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (18,'Mansioni assegnate', 'Studente');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (19,'Ambiente di lavoro', 'Studente');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (20,'Competenze tecniche presenti', 'Studente');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (21,'Logistica e supporto strumentale', 'Studente');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (22,'Assistenza del tutor Ente ospitante', 'Studente');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (23,'Le strutture universitarie addette ai Tirocini hanno fornito informazioni chiare ed esaustive', 'Studente');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (24,'Le strutture universitatrie addette ai Tirocini hanno fornito assistenza e disponibilità', 'Studente');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (25,'I servizi/informazioni fornite via Web sono esaustive', 'Studente');
INSERT INTO `libra`.`domanda`(`id`,`testo`, `tipo`) VALUES (26,'Note', 'Studente');

INSERT INTO `permesso` (`tipo`,`abilitazione`) VALUES ('anonimi',b'1');
INSERT INTO `permesso` (`tipo`,`abilitazione`) VALUES ('conFirma',b'1');
INSERT INTO `permesso` (`tipo`,`abilitazione`) VALUES ('noFeedback',b'1');
INSERT INTO `permesso` (`tipo`,`abilitazione`) VALUES ('ricevuti',b'1');

INSERT INTO `possesso` (`ruolo`,`tipo`) VALUES ('Azienda','conFirma');
INSERT INTO `possesso` (`ruolo`,`tipo`) VALUES ('Presidente','conFirma');
INSERT INTO `possesso` (`ruolo`,`tipo`) VALUES ('Segreteria','conFirma');
INSERT INTO `possesso` (`ruolo`,`tipo`) VALUES ('Studente','conFirma');
INSERT INTO `possesso` (`ruolo`,`tipo`) VALUES ('TutorInterno','conFirma');
INSERT INTO `possesso` (`ruolo`,`tipo`) VALUES ('Presidente','ricevuti');
INSERT INTO `possesso` (`ruolo`,`tipo`) VALUES ('Segreteria','ricevuti');
INSERT INTO `possesso` (`ruolo`,`tipo`) VALUES ('Studente','ricevuti');
INSERT INTO `possesso` (`ruolo`,`tipo`) VALUES ('TutorInterno','ricevuti');

INSERT INTO `libra`.`Utente` (`email`, `password`, `ruolo`) VALUES ('admin@apple.it', '123123123', 'Azienda');
INSERT INTO `libra`.`Utente` (`email`, `password`, `ruolo`) VALUES ('admin@google.it', '123123123', 'Azienda');
INSERT INTO `libra`.`Utente` (`email`, `password`, `ruolo`) VALUES ('admin@amazon.it', '123123123', 'Azienda');
INSERT INTO `libra`.`Utente` (`email`, `password`, `ruolo`) VALUES ('admin@microsoft.it', '123123123', 'Azienda');
INSERT INTO `libra`.`Utente` (`email`, `password`, `ruolo`) VALUES ('admin@hp.it', '123123123', 'Azienda');
INSERT INTO `libra`.`Utente` (`email`, `password`, `ruolo`) VALUES ('admin@asus.it', '123123123', 'Azienda');


INSERT INTO `libra`.`Azienda` (`utenteEmail`, `nome`) VALUES ('admin@google.it', 'Google');
INSERT INTO `libra`.`Azienda` (`utenteEmail`, `nome`) VALUES ('admin@amazon.it', 'Amazon');
INSERT INTO `libra`.`Azienda` (`utenteEmail`, `nome`) VALUES ('admin@apple.it', 'Apple');
INSERT INTO `libra`.`Azienda` (`utenteEmail`, `nome`) VALUES ('admin@hp.it', 'HP');
INSERT INTO `libra`.`Azienda` (`utenteEmail`, `nome`) VALUES ('admin@asus.it', 'Asus');
INSERT INTO `libra`.`Azienda` (`utenteEmail`, `nome`) VALUES ('admin@microsoft.it', 'Microsoft');

INSERT INTO `libra`.`utente` (`email`, `password`, `ruolo`) VALUES ('tutor@unisa.it', '123123123', 'TutorInterno');
INSERT INTO `libra`.`tutorinterno` (`utenteEmail`, `cognome`, `dataDiNascita`, `nome`) VALUES ('tutor@unisa.it', 'Interno', '1970-01-27 00:00:00', 'Tutor');

