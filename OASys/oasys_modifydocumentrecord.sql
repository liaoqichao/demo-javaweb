CREATE DATABASE  IF NOT EXISTS `oasys` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `oasys`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: oasys
-- ------------------------------------------------------
-- Server version	5.5.42

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

--
-- Table structure for table `modifydocumentrecord`
--

DROP TABLE IF EXISTS `modifydocumentrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modifydocumentrecord` (
  `modifyDocumentRecordNo` varchar(255) NOT NULL,
  `documentNo` varchar(255) NOT NULL,
  `documentHeader` varchar(255) NOT NULL,
  `modifyerCardNo` varchar(255) NOT NULL,
  `modifyTime` varchar(255) NOT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`modifyDocumentRecordNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modifydocumentrecord`
--

LOCK TABLES `modifydocumentrecord` WRITE;
/*!40000 ALTER TABLE `modifydocumentrecord` DISABLE KEYS */;
INSERT INTO `modifydocumentrecord` VALUES ('9C64E34AD826403ABA0348B8F04D85BE','21B17ACCDD3F441A8CC60BEA1B104436','笔记.txt','123456','2015-12-29','笔记，这里'),('A41EAB9995064CD298997CDC3F62970A','21B17ACCDD3F441A8CC60BEA1B104436','笔记.txt','123456','2015-12-29','笔记，这里'),('F5CC752F112F4EF2B27C44397639C95D','21B17ACCDD3F441A8CC60BEA1B104436','笔记.txt','123456','2015-12-29','笔记，这里');
/*!40000 ALTER TABLE `modifydocumentrecord` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-29 16:03:53
