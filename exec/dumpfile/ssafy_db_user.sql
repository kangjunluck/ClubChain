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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `useraccount` varchar(255) DEFAULT NULL,
  `usernickname` varchar(255) DEFAULT NULL,
  `userthumbnail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'$2a$10$YKVYGyCGqhxpkvk1KKzIs.tNcsagaKGpEFwGqc9.1.hnVBAxNAxvy','ekql@naver.com','0x13e1f7dfbFA9A717727BcE03ddaff4C1Dc31467A','다비','image/user/ekql@naver.com_뜨개질.jpg'),(2,'$2a$10$hxxCmZ98.qVkDUuxO6wrM.9N5Z92hn.qZRHC1GC8XfVvuVjRizyiS','wym6764@gmail.com','0x6F3D6c6a3ddDDEbbc0726b0d2856c76e062ee363','왕영문','image/user/wym6764@gmail.com_사진.jpeg'),(3,'$2a$10$yH2tmYKWpCfm6glMyEmIUuSn.QxtbtnjP00qglJ7gIGbAt7m3f.oy','kido@naver.com','0x295b053A6D16e1379C774Ec7342fF4Efa6b6B582','kido','image/user/kido@naver.com_8.jpg'),(4,'$2a$10$7xlMtbcFvI5jQA.U4HYWoOxz84QwNAWGEZ7qbg6MHmDACgNJK6S1S','doglove@ssafy.com','0xf73801D38dB1BaF3a1e2cA0b1Ec551237fbb326b','강아지아빠','image/user/doglove@ssafy.com_IMG_2713.jpg'),(5,'$2a$10$NWlK00z2Ok3x1T1faSVFt.lVQ7WpVdj6z3uduSb4AkWA1RwaXc9IW','forest@forest.com','0xE32791Dfee02d1Dc28F77A7A7B77A1aa32f079C8','산다람쥐','image/user/forest@forest.com_플필2.jpg'),(6,'$2a$10$fzCxDcBpWhnALxeJF1Sl0.97zGuzr4zI3XYAE8Vl53VXWHag/bUHS','tkdugs@naver.com','0x5e79668b0eDb1472f5c13eE8cd1bA0d975fe6D1C','이상현','image/user/tkdugs@naver.com_라이언.png'),(7,'$2a$10$7Pro.3EHiKNXY1./hBnDd.yirkVcEKhFXe6sZX7Wj2l2.odCymPyu','dltkdgus@naver.com','0xaC05Af36Af1c938Bc2A1b4BE29929E56889b1627','이상현','image/user/dltkdgus@naver.com-펭.jpg'),(13,'$2a$10$YryrXdVnksRckWlU0HeJkO5FJr0Lhesmim8pJp0jZCMAe3uqcFbwe','cat@cat.com','0x4102068eE3D988ed7DDd75c9E44aa50472569543','야옹','image/user/cat@cat.com_고양이1.png'),(14,'$2a$10$ZoARgkbSpHNoK2uhxE.IJ.E644BliorQKw.C8dY7LogISqUAwCF..','club@gmail.com','0xA5E4CF26a82EAb44F4Cf1f5525A80E5865e595BF','Club','image/user/default.png'),(20,'$2a$10$PGcy3Ki5UqC7fbVPvH26fe5ppZnw/UEVB65j/hXfoLEZFUnikWpAe','cat@gmail.com','0x39cdf5e11daB33aA81cF68DF7f23F966bB217665','고양이는고영고영','image/user/default.png'),(19,'$2a$10$wGh5uvlTw9QLDdiAB1MpwOBh7neSFuhoFpErjFec1shbssH.GREKi','plz@plz.com','0xebCbA6f7479Eb4aa10bAa7583b9D7a61c8C9a7CD','plz','image/user/plz@plz.com_main2.jpg');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
