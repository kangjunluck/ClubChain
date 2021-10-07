-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: j5b1021.p.ssafy.io    Database: ssafy_db
-- ------------------------------------------------------
-- Server version	8.0.26-0ubuntu0.20.04.3

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
-- Table structure for table `user_club_join`
--

DROP TABLE IF EXISTS `user_club_join`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_club_join` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `position` varchar(255) DEFAULT NULL,
  `clubid` bigint DEFAULT NULL,
  `userid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK25i7y6l5npyp9oh6phc62j72v` (`clubid`),
  KEY `FK49r5oesw6bu3gdond5m47lpxd` (`userid`)
) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_club_join`
--

LOCK TABLES `user_club_join` WRITE;
/*!40000 ALTER TABLE `user_club_join` DISABLE KEYS */;
INSERT INTO `user_club_join` VALUES (1,'관리자',1,1),(2,'관리자',2,3),(8,'관리자',8,5),(5,'관리자',5,3),(6,'관리자',6,4),(7,'관리자',7,2),(9,'관리자',9,1),(10,'관리자',10,2),(11,'관리자',11,4),(12,'관리자',12,5),(13,'관리자',13,3),(14,'정회원',9,5),(15,'정회원',9,3),(16,'정회원',8,4),(17,'정회원',1,5),(18,'정회원',9,4),(19,'정회원',9,2),(20,'정회원',7,3),(21,'정회원',9,7),(22,'정회원',8,2),(23,'관리자',14,7),(24,'정회원',11,7),(25,'정회원',2,2),(26,'정회원',13,7),(27,'정회원',6,5),(28,'정회원',6,2),(29,'정회원',6,7),(30,'정회원',13,2),(31,'정회원',13,4),(32,'정회원',8,3),(33,'정회원',8,7),(34,'정회원',7,1),(35,'정회원',7,4),(36,'정회원',2,14),(37,'정회원',8,14),(38,'정회원',2,7),(39,'정회원',14,5),(40,'정회원',8,1);
/*!40000 ALTER TABLE `user_club_join` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-08  0:46:29
