-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: weatherobservation
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `weatherobservation`
--

DROP TABLE IF EXISTS `weatherobservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `weatherobservation` (
  `observation_id` int NOT NULL AUTO_INCREMENT,
  `station_id` varchar(50) NOT NULL,
  `observation_date` date NOT NULL,
  `observation_time` time NOT NULL,
  `temperature` decimal(5,2) NOT NULL,
  `humidity` decimal(5,2) NOT NULL,
  `wind_speed` decimal(5,2) NOT NULL,
  `wind_direction` varchar(20) NOT NULL,
  `precipitation` decimal(5,2) NOT NULL,
  `description` text,
  PRIMARY KEY (`observation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weatherobservation`
--

LOCK TABLES `weatherobservation` WRITE;
/*!40000 ALTER TABLE `weatherobservation` DISABLE KEYS */;
INSERT INTO `weatherobservation` VALUES (1,'station1','2026-01-01','10:00:00',24.50,60.00,10.00,'N',0.00,'Clear sky'),(2,'station1','2026-01-01','12:00:00',31.00,65.00,15.00,'E',0.00,'Partly cloudy'),(3,'station1','2026-01-01','18:00:00',28.00,70.00,20.00,'S',5.00,'Rainy'),(4,'station2','2026-01-02','12:00:00',24.50,75.00,25.00,'W',10.00,'Stormy'),(5,'station3','2026-01-03','14:00:00',32.00,60.00,20.00,'NE',0.00,'Clear sky');
/*!40000 ALTER TABLE `weatherobservation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-20 23:13:45
