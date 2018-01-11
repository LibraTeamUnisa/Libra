-- MySQL dump 10.13  Distrib 5.7.16, for Win64 (x86_64)
--
-- Host: localhost    Database: libra
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

drop schema if exists libra;
create schema libra;
use libra;

--
-- Table structure for table `azienda`
--

DROP TABLE IF EXISTS `azienda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `azienda` (
  `utenteEmail` varchar(50) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `partitaIVA` varchar(15) NOT NULL,
  `sede` varchar(100) NOT NULL,
  PRIMARY KEY (`utenteEmail`),
  CONSTRAINT `emailAzienda` FOREIGN KEY (`utenteEmail`) REFERENCES `utente` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `azienda`
--

LOCK TABLES `azienda` WRITE;
/*!40000 ALTER TABLE `azienda` DISABLE KEYS */;
/*!40000 ALTER TABLE `azienda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `domanda`
--

DROP TABLE IF EXISTS `domanda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `domanda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `testo` varchar(500) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `domanda`
--

LOCK TABLES `domanda` WRITE;
/*!40000 ALTER TABLE `domanda` DISABLE KEYS */;
/*!40000 ALTER TABLE `domanda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `progettoFormativoID` int(11) NOT NULL,
  `domandaID` int(11) NOT NULL,
  `valutazione` varchar(256) NOT NULL,
  PRIMARY KEY (`progettoFormativoID`,`domandaID`),
  KEY `domandaID` (`domandaID`),
  CONSTRAINT `domandaID` FOREIGN KEY (`domandaID`) REFERENCES `domanda` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pfIDFeedback` FOREIGN KEY (`progettoFormativoID`) REFERENCES `progettoformativo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gruppo`
--

DROP TABLE IF EXISTS `gruppo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gruppo` (
  `ruolo` varchar(20) NOT NULL,
  PRIMARY KEY (`ruolo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gruppo`
--

LOCK TABLES `gruppo` WRITE;
/*!40000 ALTER TABLE `gruppo` DISABLE KEYS */;
/*!40000 ALTER TABLE `gruppo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifica`
--

DROP TABLE IF EXISTS `notifica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notifica` (
  `id` int(11) NOT NULL,
  `descrizione` varchar(500) NOT NULL,
  `dataRicezione` datetime NOT NULL,
  `visualizzata` bit(1) NOT NULL,
  `utenteEmail` varchar(50) NOT NULL,
  `progettoFormativoID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `utenteEmail` (`utenteEmail`),
  CONSTRAINT `IDProgettoFormativo` FOREIGN KEY (`id`) REFERENCES `progettoformativo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `utenteEmail` FOREIGN KEY (`utenteEmail`) REFERENCES `utente` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifica`
--

LOCK TABLES `notifica` WRITE;
/*!40000 ALTER TABLE `notifica` DISABLE KEYS */;
/*!40000 ALTER TABLE `notifica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permesso`
--

DROP TABLE IF EXISTS `permesso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permesso` (
  `tipo` varchar(20) NOT NULL,
  `abilitazione` bit(1) NOT NULL,
  PRIMARY KEY (`tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permesso`
--

LOCK TABLES `permesso` WRITE;
/*!40000 ALTER TABLE `permesso` DISABLE KEYS */;
/*!40000 ALTER TABLE `permesso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `possesso`
--

DROP TABLE IF EXISTS `possesso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `possesso` (
  `ruolo` varchar(20) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  PRIMARY KEY (`ruolo`,`tipo`),
  KEY `tipo` (`tipo`),
  CONSTRAINT `gruppo` FOREIGN KEY (`ruolo`) REFERENCES `gruppo` (`ruolo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tipo` FOREIGN KEY (`tipo`) REFERENCES `permesso` (`tipo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `possesso`
--

LOCK TABLES `possesso` WRITE;
/*!40000 ALTER TABLE `possesso` DISABLE KEYS */;
/*!40000 ALTER TABLE `possesso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preferenza`
--

DROP TABLE IF EXISTS `preferenza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `preferenza` (
  `studenteEmail` varchar(50) NOT NULL,
  `aziendaEmail` varchar(50) NOT NULL,
  PRIMARY KEY (`studenteEmail`,`aziendaEmail`),
  KEY `emailAziendaPreferenza` (`aziendaEmail`),
  CONSTRAINT `emailAziendaPreferenza` FOREIGN KEY (`aziendaEmail`) REFERENCES `azienda` (`utenteEmail`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `emailStudentePreferenza` FOREIGN KEY (`studenteEmail`) REFERENCES `studente` (`utenteEmail`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preferenza`
--

LOCK TABLES `preferenza` WRITE;
/*!40000 ALTER TABLE `preferenza` DISABLE KEYS */;
/*!40000 ALTER TABLE `preferenza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presidente`
--

DROP TABLE IF EXISTS `presidente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `presidente` (
  `utenteEmail` varchar(50) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `dataDiNascita` datetime NOT NULL,
  `giorniDiRicevimento` varchar(100) NOT NULL,
  `ufficio` varchar(50) NOT NULL,
  `linkSito` varchar(500) NOT NULL,
  PRIMARY KEY (`utenteEmail`),
  CONSTRAINT `emailPresidente` FOREIGN KEY (`utenteEmail`) REFERENCES `utente` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presidente`
--

LOCK TABLES `presidente` WRITE;
/*!40000 ALTER TABLE `presidente` DISABLE KEYS */;
/*!40000 ALTER TABLE `presidente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `progettoformativo`
--

DROP TABLE IF EXISTS `progettoformativo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `progettoformativo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stato` int(11) NOT NULL,
  `dataInizio` datetime DEFAULT NULL,
  `dataFine` datetime DEFAULT NULL,
  `ambito` varchar(50) NOT NULL,
  `note` varchar(50) DEFAULT NULL,
  `documento` varchar(500) NOT NULL,
  `motivazioneRifiuto` varchar(500) DEFAULT NULL,
  `periodoReport` int(11) NOT NULL,
  `studenteEmail` varchar(50) DEFAULT NULL,
  `aziendaEmail` varchar(50) DEFAULT NULL,
  `tutorInternoEmail` varchar(50) DEFAULT NULL,
  `dataInvio` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `emailStudentePF` (`studenteEmail`),
  KEY `emailAziendaPF` (`aziendaEmail`),
  KEY `emailTutorInternoPF` (`tutorInternoEmail`),
  CONSTRAINT `emailAziendaPF` FOREIGN KEY (`aziendaEmail`) REFERENCES `azienda` (`utenteEmail`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `emailStudentePF` FOREIGN KEY (`studenteEmail`) REFERENCES `studente` (`utenteEmail`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `emailTutorInternoPF` FOREIGN KEY (`tutorInternoEmail`) REFERENCES `tutorinterno` (`utenteEmail`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `progettoformativo`
--

LOCK TABLES `progettoformativo` WRITE;
/*!40000 ALTER TABLE `progettoformativo` DISABLE KEYS */;
/*!40000 ALTER TABLE `progettoformativo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `data` datetime NOT NULL,
  `testo` varchar(500) NOT NULL,
  `progettoFormativoID` int(11) NOT NULL,
  PRIMARY KEY (`data`,`progettoFormativoID`),
  KEY `pfIDReport` (`progettoFormativoID`),
  CONSTRAINT `pfIDReport` FOREIGN KEY (`progettoFormativoID`) REFERENCES `progettoformativo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `segreteria`
--

DROP TABLE IF EXISTS `segreteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `segreteria` (
  `utenteEmail` varchar(50) NOT NULL,
  `giorniDiRicevimento` varchar(256) NOT NULL,
  PRIMARY KEY (`utenteEmail`),
  CONSTRAINT `emailSegreteria` FOREIGN KEY (`utenteEmail`) REFERENCES `utente` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `segreteria`
--

LOCK TABLES `segreteria` WRITE;
/*!40000 ALTER TABLE `segreteria` DISABLE KEYS */;
/*!40000 ALTER TABLE `segreteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studente`
--

DROP TABLE IF EXISTS `studente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studente` (
  `utenteEmail` varchar(50) NOT NULL,
  `matricola` varchar(15) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `dataDiNascita` datetime NOT NULL,
  PRIMARY KEY (`utenteEmail`),
  CONSTRAINT `emailStudente` FOREIGN KEY (`utenteEmail`) REFERENCES `utente` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studente`
--

LOCK TABLES `studente` WRITE;
/*!40000 ALTER TABLE `studente` DISABLE KEYS */;
/*!40000 ALTER TABLE `studente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutoresterno`
--

DROP TABLE IF EXISTS `tutoresterno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tutoresterno` (
  `aziendaEmail` varchar(50) NOT NULL,
  `ambito` varchar(50) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `dataDiNascita` datetime NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `indirizzo` varchar(40) NOT NULL,
  PRIMARY KEY (`aziendaEmail`,`ambito`),
  CONSTRAINT `emailTutorEsterno` FOREIGN KEY (`aziendaEmail`) REFERENCES `azienda` (`utenteEmail`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutoresterno`
--

LOCK TABLES `tutoresterno` WRITE;
/*!40000 ALTER TABLE `tutoresterno` DISABLE KEYS */;
/*!40000 ALTER TABLE `tutoresterno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutorinterno`
--

DROP TABLE IF EXISTS `tutorinterno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tutorinterno` (
  `utenteEmail` varchar(50) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `dataDiNascita` datetime NOT NULL,
  `linkSito` varchar(500) NOT NULL,
  PRIMARY KEY (`utenteEmail`),
  CONSTRAINT `emailTutorInterno` FOREIGN KEY (`utenteEmail`) REFERENCES `utente` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutorinterno`
--

LOCK TABLES `tutorinterno` WRITE;
/*!40000 ALTER TABLE `tutorinterno` DISABLE KEYS */;
/*!40000 ALTER TABLE `tutorinterno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utente` (
  `email` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `indirizzo` varchar(100) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `imgProfilo` varchar(500) NOT NULL,
  `ruolo` varchar(20) NOT NULL,
  PRIMARY KEY (`email`),
  KEY `ruolo_idx` (`ruolo`),
  CONSTRAINT `ruolo` FOREIGN KEY (`ruolo`) REFERENCES `gruppo` (`ruolo`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-08 16:06:12
