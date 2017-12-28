INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('Azienda');
INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('Studente');
INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('Segreteria');
INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('Presidente');
INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('TutorInterno');

INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('filomena@unisa.it', '123123123', 'Via del Presidente 1', '0815554689', 'img1', 'Presidente');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('stefano@unisa.it', '321321321', 'Via del Segretario', '0325568798', 'img2', 'Segreteria');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('alfredo@unisa.it', '312312312', 'Via del Presidente 2', '065987855', 'img3', 'Presidente');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('michele@unisa.it', '312312312', 'Via dello Studente', '065987855', 'img3', 'Studente');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('android@google.com', '312312312', 'Via dell azienda', '065987855', 'img3', 'Azienda');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('pippo@unisa.it', '312312312', 'Via del tutor', '065987855', 'img3', 'TutorInterno');


INSERT INTO `libra`.`Segreteria` (`utenteEmail`, `giorniDiRicevimento`) VALUES ('stefano@unisa.it', '{\"LUN\":\"9.30-11.30\",\"MER\":\"9.30-11.30 14.30-18.30\"}');
INSERT INTO `libra`.`Presidente` (`utenteEmail`, `cognome`, `nome`, `dataDiNascita`, `giorniDiRicevimento`, `ufficio`, `linkSito`) VALUES ('filomena@unisa.it', 'Ferrucci', 'Filomena', '1992-08-15 00:00:00', '{\"MAR\":\"14.30-15.30\",\"VEN\":\"8.30-10.30 14.30-16.30\"}', 'M44', 'https://www.facebook.com/fferrucci');
INSERT INTO `libra`.`Presidente` (`utenteEmail`, `cognome`, `nome`, `dataDiNascita`, `giorniDiRicevimento`, `ufficio`, `linkSito`) VALUES ('alfredo@unisa.it', 'De Santis', 'Alfredo', '1992-08-15 00:00:00', '{\"MER\":\"11.00-13.00 15.00-18.00\",\"GIO\":\"8.30-10.30\"}', 'L37', 'https://www.facebook.com/adesantis');
INSERT INTO `libra`.`Studente` (`utenteEmail`, `cognome`, `dataDiNascita`, `matricola`, `nome`) VALUES ('michele@unisa.it', 'Spano', '1996-06-09 00:00:00', '0512103861', 'Michele');
INSERT INTO `libra`.`Azienda` (`utenteEmail`, `nome`, `partitaIVA`, `sede`) VALUES ('android@google.com', 'Google', 'cdcxfvhyipb', 'Mountain View');
INSERT INTO `libra`.`TutorInterno` (`utenteEmail`, `cognome`, `dataDiNascita`, `linkSito`, `nome`) VALUES ('pippo@unisa.it', 'Cattaneo', '1992-08-15 00:00:00','https://www.facebook.com/pcattaneo', 'Giuseppe');
INSERT INTO libra.ProgettoFormativo (`aziendaEmail`,`studenteEmail`,`tutorInternoEmail`,`ambito`, stato,documento,`periodoReport`, `dataInvio`) VALUES ('android@google.com','michele@unisa.it', 'pippo@unisa.it', 'qualcosa','0','D:/Michele/Università/Project Libra/Robe Utili/Statement of Work.pdf','0', '2017-11-27 00:00:00');
insert into hibernate_sequence(next_val) values(0);

INSERT INTO libra.Utente (email, imgProfilo, indirizzo, password, telefono, ruolo) VALUES ('azienda@prova.it', 'xxx', 'Via delle Aziende', '123123', '0892221113', 'Azienda');
INSERT INTO libra.Azienda (utenteEmail, nome, partitaIVA, sede) VALUES ('azienda@prova.it', 'Azienda', 'PartIva', 'Trivio');
INSERT INTO libra.Tutoresterno (ambito, aziendaEmail, cognome, nome, telefono, indirizzo, dataDiNascita) VALUES ('ambito', 'azienda@prova.it', 'Brazorf', 'Ajeje', '1112223331', 'via Roma, 1', '1992-08-15 00:00:00');

