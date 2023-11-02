-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: dbclients
-- ------------------------------------------------------
-- Server version	8.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clients_table`
--

DROP TABLE IF EXISTS `clients_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients_table` (
  `idclient` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID del cliente en la base de datos',
  `sharedkey` varchar(100) NOT NULL COMMENT 'Nombre unico del cliente',
  `nameclient` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Nombre Completo del cliente',
  `phone` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Telefono del cliente',
  `email` varchar(400) NOT NULL COMMENT 'Correo Electronico del cliente',
  `startdate` date DEFAULT NULL COMMENT 'Fecha inicial del cliente',
  `enddate` date DEFAULT NULL COMMENT 'Fecha final del cliente',
  `createdate` date NOT NULL COMMENT 'Fecha de creacion del cliente en BD',
  `updatedate` date DEFAULT NULL COMMENT 'Fecha de actualizacion del cliente en BD',
  `stateclient` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Estado del cliente',
  PRIMARY KEY (`idclient`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients_table`
--

LOCK TABLES `clients_table` WRITE;
/*!40000 ALTER TABLE `clients_table` DISABLE KEYS */;
INSERT INTO `clients_table` VALUES (1,'prueba','prueba nombre','3216548936','prueba@prueba.com','2023-11-02','2023-11-02','2023-11-02',NULL,'ACTIVE'),(3,'prueba2','prueba nombre upd','3216548940','prueba2@prueba.com','2023-11-02','2023-11-02','2023-11-02','2023-11-01','ACTIVE'),(4,'prueba3','prueba nombre 3','3216548939','prueba3@prueba.com','2023-11-02','2023-11-02','2023-11-01',NULL,'ACTIVE'),(5,'prueba4','prueba nombre create','3216548939','prueba4@prueba.com','2023-11-02','2023-11-02','2023-11-02',NULL,'ACTIVE');
/*!40000 ALTER TABLE `clients_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'dbclients'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-02 17:34:10
