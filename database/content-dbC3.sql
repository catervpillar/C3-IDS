-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: database_c3
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `ID` varchar(8) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `indirizzo` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('c0f1a525','Paolo','Pierfederici','paolino99','pwd','a Passo de Treia','paolo.pierfederici@gmail.com','3334445550');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commerciante`
--

DROP TABLE IF EXISTS `commerciante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commerciante` (
  `ID` varchar(8) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `indirizzo` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `ragioneSociale` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `idcommerciante_UNIQUE` (`ID`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `ragioneSociale_UNIQUE` (`ragioneSociale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commerciante`
--

LOCK TABLES `commerciante` WRITE;
/*!40000 ALTER TABLE `commerciante` DISABLE KEYS */;
INSERT INTO `commerciante` VALUES ('abcdefgh','leo','pwd',NULL,'leonardo@gmail.com',NULL,'leo srl'),('cd2cf1b6','tom','pwd','Viale Rossi, Paperopoli (MC)','tommaso@gmail.com','0715555925','Tommaso srl');
/*!40000 ALTER TABLE `commerciante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `corriere`
--

DROP TABLE IF EXISTS `corriere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `corriere` (
  `ID` varchar(8) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `indirizzo` varchar(45) DEFAULT NULL,
  `ragioneSociale` varchar(45) NOT NULL,
  `stato` enum('DISPONIBILE','NON_DISPONIBILE') NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ragione sociale_UNIQUE` (`ragioneSociale`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `corriere`
--

LOCK TABLES `corriere` WRITE;
/*!40000 ALTER TABLE `corriere` DISABLE KEYS */;
INSERT INTO `corriere` VALUES ('08f0cf37','poste_italiane','passwordposte',NULL,'poste.italiane@gmail.com',NULL,'Poste italiane S.p.A','NON_DISPONIBILE');
/*!40000 ALTER TABLE `corriere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodotto`
--

DROP TABLE IF EXISTS `prodotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prodotto` (
  `ID` varchar(8) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `prezzo` double NOT NULL,
  `quantita` int NOT NULL,
  `commerciante_ID` varchar(8) NOT NULL,
  `URL_immagine` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_prodottoInVendita_commerciante1_idx` (`commerciante_ID`),
  CONSTRAINT `fk_prodottoInVendita_commerciante1` FOREIGN KEY (`commerciante_ID`) REFERENCES `commerciante` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodotto`
--

LOCK TABLES `prodotto` WRITE;
/*!40000 ALTER TABLE `prodotto` DISABLE KEYS */;
INSERT INTO `prodotto` VALUES ('592c94eb','Big Mac',8,500,'cd2cf1b6','https://bit.ly/3b55hST'),('86740f38','Samsung S21 5G (128GB)',835,30,'cd2cf1b6','https://bit.ly/3qcaZqo'),('aaaaaaaa','Sony Playstation 5',499.99,2,'cd2cf1b6','https://bit.ly/37NHgxh'),('bbbbbbbb','X-Box Series X',499.99,12,'cd2cf1b6','https://bit.ly/3uyZe0l'),('dddddddd','APPLE Iphone 12',949,8,'cd2cf1b6','https://bit.ly/3dTIxH4'),('e56d7db3','pippo',33,3,'cd2cf1b6','');
/*!40000 ALTER TABLE `prodotto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promozione`
--

DROP TABLE IF EXISTS `promozione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promozione` (
  `ID` varchar(8) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `descrizione` varchar(500) NOT NULL,
  `data_inizio` date NOT NULL,
  `data_scadenza` date NOT NULL,
  `commerciante_ID` varchar(8) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_promozione_commerciante1_idx` (`commerciante_ID`),
  CONSTRAINT `fk_promozione_commerciante1` FOREIGN KEY (`commerciante_ID`) REFERENCES `commerciante` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promozione`
--

LOCK TABLES `promozione` WRITE;
/*!40000 ALTER TABLE `promozione` DISABLE KEYS */;
INSERT INTO `promozione` VALUES ('43f5e013','scontiissimi','aaaa','2021-03-16','2021-03-27','cd2cf1b6'),('84343f5e','promooo','saasaa','2021-04-08','2021-04-10','cd2cf1b6');
/*!40000 ALTER TABLE `promozione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promozione_has_prodotto`
--

DROP TABLE IF EXISTS `promozione_has_prodotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promozione_has_prodotto` (
  `promozione_ID` varchar(8) NOT NULL,
  `prodottoInVendita_ID` varchar(8) NOT NULL,
  PRIMARY KEY (`promozione_ID`,`prodottoInVendita_ID`),
  KEY `fk_promozione_has_prodottoInVendita_prodottoInVendita1_idx` (`prodottoInVendita_ID`),
  KEY `fk_promozione_has_prodottoInVendita_promozione1_idx` (`promozione_ID`),
  CONSTRAINT `fk_promozione_has_prodottoInVendita_prodottoInVendita1` FOREIGN KEY (`prodottoInVendita_ID`) REFERENCES `prodotto` (`ID`),
  CONSTRAINT `fk_promozione_has_prodottoInVendita_promozione1` FOREIGN KEY (`promozione_ID`) REFERENCES `promozione` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promozione_has_prodotto`
--

LOCK TABLES `promozione_has_prodotto` WRITE;
/*!40000 ALTER TABLE `promozione_has_prodotto` DISABLE KEYS */;
INSERT INTO `promozione_has_prodotto` VALUES ('43f5e013','592c94eb'),('84343f5e','592c94eb'),('43f5e013','86740f38'),('43f5e013','aaaaaaaa'),('43f5e013','bbbbbbbb'),('43f5e013','dddddddd'),('43f5e013','e56d7db3');
/*!40000 ALTER TABLE `promozione_has_prodotto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `punto_ritiro`
--

DROP TABLE IF EXISTS `punto_ritiro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `punto_ritiro` (
  `ID` varchar(8) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `indirizzo` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `ragione_sociale` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `ragione sociale_UNIQUE` (`ragione_sociale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `punto_ritiro`
--

LOCK TABLES `punto_ritiro` WRITE;
/*!40000 ALTER TABLE `punto_ritiro` DISABLE KEYS */;
INSERT INTO `punto_ritiro` VALUES ('b92be962','deposito_1','password-deposito','deposito1@gmail.com','Via Pippo','1234567890','Deposito n.1');
/*!40000 ALTER TABLE `punto_ritiro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recensione`
--

DROP TABLE IF EXISTS `recensione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recensione` (
  `ID` varchar(8) NOT NULL,
  `titolo` varchar(45) NOT NULL,
  `testo` varchar(45) DEFAULT NULL,
  `voto_recensione` enum('UNA_STELLA','DUE_STELLE','TRE_STELLE','QUATTRO_STELLE','CINQUE_STELLE') NOT NULL,
  `prodottoInVendita_ID` varchar(8) NOT NULL,
  `commerciante_ID` varchar(8) NOT NULL,
  `cliente_ID` varchar(8) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_recensione_prodottoInVendita1_idx` (`prodottoInVendita_ID`),
  KEY `fk_recensione_commerciante1_idx` (`commerciante_ID`),
  KEY `fk_recensione_cliente1_idx` (`cliente_ID`),
  CONSTRAINT `fk_recensione_cliente1` FOREIGN KEY (`cliente_ID`) REFERENCES `cliente` (`ID`),
  CONSTRAINT `fk_recensione_commerciante1` FOREIGN KEY (`commerciante_ID`) REFERENCES `commerciante` (`ID`),
  CONSTRAINT `fk_recensione_prodottoInVendita1` FOREIGN KEY (`prodottoInVendita_ID`) REFERENCES `prodotto` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recensione`
--

LOCK TABLES `recensione` WRITE;
/*!40000 ALTER TABLE `recensione` DISABLE KEYS */;
INSERT INTO `recensione` VALUES ('4caec5f1','niente di che','fa cacà','DUE_STELLE','bbbbbbbb','cd2cf1b6','c0f1a525'),('eea6d7cb','strafigaa','bella','CINQUE_STELLE','aaaaaaaa','cd2cf1b6','c0f1a525');
/*!40000 ALTER TABLE `recensione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ritiro`
--

DROP TABLE IF EXISTS `ritiro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ritiro` (
  `ID` varchar(8) NOT NULL,
  `destinazione` varchar(45) NOT NULL,
  `codice_ritiro` varchar(45) NOT NULL,
  `data_prenotazione` date NOT NULL,
  `data_consegna` date DEFAULT NULL,
  `ritirato` tinyint NOT NULL,
  `tipo_consegna` enum('CONSEGNA_A_DOMICILIO','CONSEGNA_PRESSO_PUNTO') NOT NULL,
  `commerciante_ID` varchar(8) NOT NULL,
  `cliente_ID` varchar(8) NOT NULL,
  `corriere_ID` varchar(8) NOT NULL,
  `stato_tracking` enum('IN_ELABORAZIONE','PRESO_IN_CARICO','IN_TRANSITO','IN_CONSEGNA','CONSEGNATO') NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `codice_ritiro_UNIQUE` (`codice_ritiro`),
  KEY `fk_ritiro_commerciante1_idx` (`commerciante_ID`),
  KEY `fk_ritiro_cliente1_idx` (`cliente_ID`),
  KEY `fk_ritiro_corriere1_idx` (`corriere_ID`),
  CONSTRAINT `fk_ritiro_cliente1` FOREIGN KEY (`cliente_ID`) REFERENCES `cliente` (`ID`),
  CONSTRAINT `fk_ritiro_commerciante1` FOREIGN KEY (`commerciante_ID`) REFERENCES `commerciante` (`ID`),
  CONSTRAINT `fk_ritiro_corriere1` FOREIGN KEY (`corriere_ID`) REFERENCES `corriere` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ritiro`
--

LOCK TABLES `ritiro` WRITE;
/*!40000 ALTER TABLE `ritiro` DISABLE KEYS */;
INSERT INTO `ritiro` VALUES ('00cb947a','Via Pippo','0S262Z','2021-03-04',NULL,0,'CONSEGNA_PRESSO_PUNTO','cd2cf1b6','c0f1a525','08f0cf37','IN_ELABORAZIONE'),('81187918','Via Pippo','RQACDA','2021-03-17',NULL,0,'CONSEGNA_PRESSO_PUNTO','cd2cf1b6','c0f1a525','08f0cf37','IN_ELABORAZIONE'),('dc523c11','Camerino','88P2YL','2021-03-10',NULL,0,'CONSEGNA_A_DOMICILIO','cd2cf1b6','c0f1a525','08f0cf37','IN_ELABORAZIONE');
/*!40000 ALTER TABLE `ritiro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ritiro_has_prodotto`
--

DROP TABLE IF EXISTS `ritiro_has_prodotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ritiro_has_prodotto` (
  `prodottoInVendita_ID` varchar(8) NOT NULL,
  `ritiro_ID` varchar(8) NOT NULL,
  PRIMARY KEY (`prodottoInVendita_ID`,`ritiro_ID`),
  KEY `fk_prodottoInVendita_has_ritiro_ritiro1_idx` (`ritiro_ID`),
  KEY `fk_prodottoInVendita_has_ritiro_prodottoInVendita1_idx` (`prodottoInVendita_ID`),
  CONSTRAINT `fk_prodottoInVendita_has_ritiro_prodottoInVendita1` FOREIGN KEY (`prodottoInVendita_ID`) REFERENCES `prodotto` (`ID`),
  CONSTRAINT `fk_prodottoInVendita_has_ritiro_ritiro1` FOREIGN KEY (`ritiro_ID`) REFERENCES `ritiro` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ritiro_has_prodotto`
--

LOCK TABLES `ritiro_has_prodotto` WRITE;
/*!40000 ALTER TABLE `ritiro_has_prodotto` DISABLE KEYS */;
INSERT INTO `ritiro_has_prodotto` VALUES ('aaaaaaaa','81187918'),('bbbbbbbb','81187918'),('dddddddd','81187918'),('dddddddd','dc523c11');
/*!40000 ALTER TABLE `ritiro_has_prodotto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-28 17:09:34
