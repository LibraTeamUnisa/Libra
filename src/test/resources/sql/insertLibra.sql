INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('Azienda');
INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('Studente');
INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('Segreteria');
INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('Presidente');
INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('TutorInterno');

INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('giovanni@unisa.it', '123123', 'Via del Presidente 1', '0815554689', '1.jpg', 'Presidente');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('stefano@unisa.it', '321321', 'Via del Segretario', '0325568798', '2.jpg', 'Segreteria');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('alfredo@unisa.it', '312312', 'Via del Presidente 2', '065987855', '3.jpg', 'Presidente');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('andrea@studenti.unisa.it', '312312', 'Via dello studente', '065987855', '3.jpg', 'Studente');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('filomena@unisa.it', '312312', 'Via del presidente', '065987855', '2.jpg', 'Presidente');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('contatti@oracle.com', '312312', 'Via azienda', '065987855', '1.jpg', 'Azienda');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('pippo@unisa.it', '312312', 'Via del tutor', '065987855', '2.jpg', 'TutorInterno');

INSERT INTO `libra`.`Segreteria` (`utenteEmail`, `giorniDiRicevimento`) VALUES ('stefano@unisa.it', '{\"LUN\":\"9.30-11.30\",\"MER\":\"9.30-11.30 14.30-18.30\"}');
INSERT INTO `libra`.`Presidente` (`utenteEmail`, `cognome`, `nome`, `giorniDiRicevimento`, `ufficio`, `linkSito`, `dataDiNascita`) VALUES ('giovanni@unisa.it', 'Buglione', 'Giovanni', '{\"MAR\":\"14.30-15.30\",\"VEN\":\"8.30-10.30 14.30-16.30\"}', 'M44', 'https://www.facebook.com/vannibuglione', '1992-08-15 00:00:00');
INSERT INTO `libra`.`Presidente` (`utenteEmail`, `cognome`, `nome`, `giorniDiRicevimento`, `ufficio`, , `linkSito`, `dataDiNascita`) VALUES ('alfredo@unisa.it', 'Russo', 'Alfredo', '{\"MER\":\"11.00-13.00 15.00-18.00\",\"GIO\":\"8.30-10.30\"}', 'L37', 'https://www.facebook.com/vannibuglione', '1992-08-12 00:00:00');

INSERT INTO `libra`.`Studente` (`utenteEmail`, `cognome`, `nome`, `dataDiNascita`, `matricola`) VALUES ('andrea@studenti.unisa.it', 'Bianchi', 'Andrea', '1996-05-15 00:00:00', '0512103443');
INSERT INTO `libra`.`Presidente` (`utenteEmail`, `cognome`, `nome`, `giorniDiRicevimento`, `ufficio`, `linkSito`, `dataDiNascita`) VALUES ('filomena@unisa.it', 'Ferrucci', 'Filomena', '{\"MAR\":\"14.30-15.30\",\"VEN\":\"8.30-10.30 14.30-16.30\"}', 'M34', 'http://docenti.unisa.it/001775/home', '1973-02-18 00:00:00');
INSERT INTO `libra`.`Azienda` (`utenteEmail`, `nome`, `sede`, `partitaIVA`) VALUES ('contatti@oracle.com', 'Oracle', 'Via azienda,Roma','30518598402');
INSERT INTO `libra`.`TutorInterno` (`utenteEmail`, `cognome`, `nome`, `linkSito`, `dataDiNascita`) VALUES ('pippo@unisa.it', 'Cattaneo', 'Giuseppe', 'http://docenti.unisa.it/001775/home', '1969-01-28 00:00:00');
