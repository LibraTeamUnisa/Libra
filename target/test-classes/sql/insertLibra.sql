INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('Azienda');
INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('Studente');
INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('Segreteria');
INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('Presidente');
INSERT INTO `libra`.`Gruppo` (`ruolo`) VALUES ('TutorInterno');

INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('giovanni@unisa.it', '123123', 'Via del Presidente 1', '0815554689', 'img1', 'Presidente');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('stefano@unisa.it', '321321', 'Via del Segretario', '0325568798', 'img2', 'Segreteria');
INSERT INTO `libra`.`Utente` (`email`, `password`, `indirizzo`, `telefono`, `imgProfilo`, `ruolo`) VALUES ('alfredo@unisa.it', '312312', 'Via del Presidente 2', '065987855', 'img3', 'Presidente');

INSERT INTO `libra`.`Segreteria` (`utenteEmail`, `giorniDiRicevimento`) VALUES ('stefano@unisa.it', '{\"LUN\":\"9.30-11.30\",\"MER\":\"9.30-11.30 14.30-18.30\"}');
INSERT INTO `libra`.`Presidente` (`utenteEmail`, `cognome`, `nome`, `giorniDiRicevimento`, `ufficio`, `linkSito`) VALUES ('giovanni@unisa.it', 'Buglione', 'Giovanni', '{\"MAR\":\"14.30-15.30\",\"VEN\":\"8.30-10.30 14.30-16.30\"}', 'M44', 'https://www.facebook.com/vannibuglione');
INSERT INTO `libra`.`Presidente` (`utenteEmail`, `cognome`, `nome`, `giorniDiRicevimento`, `ufficio`) VALUES ('alfredo@unisa.it', 'Russo', 'Alfredo', '{\"MER\":\"11.00-13.00 15.00-18.00\",\"GIO\":\"8.30-10.30\"}', 'L37');
UPDATE `libra`.`Presidente` SET `dataDiNascita`='1992-08-15 00:00:00' WHERE `utenteEmail`='giovanni@unisa.it';
