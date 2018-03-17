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
-- Table structure for table `documenttable`
--

DROP TABLE IF EXISTS `documenttable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documenttable` (
  `documentNo` varchar(255) NOT NULL,
  `documentHeader` varchar(255) NOT NULL,
  `document` mediumblob NOT NULL,
  `draftsmanCardNo` varchar(255) NOT NULL,
  `issuerCardNo` varchar(255) DEFAULT NULL,
  `suffix` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`documentNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documenttable`
--

LOCK TABLES `documenttable` WRITE;
/*!40000 ALTER TABLE `documenttable` DISABLE KEYS */;
INSERT INTO `documenttable` VALUES ('0BAEBE9F08734E539B8B9CCCB5002AE2','需求规格说明书.doc','��\0sr\0java.io.File-�E\r��\0L\0patht\0Ljava/lang/String;xpt\0CE:\\OASys\\0BAEBE9F08734E539B8B9CCCB5002AE2_需求规格说明书.docw\0\\x','123456','123456','.doc'),('21B17ACCDD3F441A8CC60BEA1B104436','笔记.txt','��\0sr\0java.io.File-�E\r��\0L\0patht\0Ljava/lang/String;xpt\04E:\\OASys\\21B17ACCDD3F441A8CC60BEA1B104436_笔记.txtw\0\\x','123456','123456','.txt'),('E763B93B29C249FA92C033AAAC15FE1C','架构设计说明书.doc','��\0sr\0java.io.File-�E\r��\0L\0patht\0Ljava/lang/String;xpt\0CE:\\OASys\\E763B93B29C249FA92C033AAAC15FE1C_架构设计说明书.docw\0\\x','123456','123456','.doc');
/*!40000 ALTER TABLE `documenttable` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-29 16:03:56
