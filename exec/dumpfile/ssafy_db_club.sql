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
-- Table structure for table `club`
--

DROP TABLE IF EXISTS `club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `clubaccount` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile_thumbnail` varchar(255) DEFAULT NULL,
  `owner_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3t9ob93g0lgjn7yhk25f4415t` (`owner_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
INSERT INTO `club` VALUES (1,'0xFde487B7Ab16a04F4Dfb85BF93bCddeeb0E2c072','2021-10-07 00:16:42','뜨개질을 좋아하는 사람들의 모임입니다 *^^*','뜨개뜨개','1234','image/club/1-뜨개질 이미지.jpg',1),(2,'0x9567fBC0ebB893ACF942bE9113Aa6657fDa02e3b','2021-10-07 00:16:52','연극동아리','해왕성','1234','image/club/2-main2.jpg',3),(9,'0xc2e04f5e7e9d7ECC83D11f6A42478E17Db22C5fd','2021-10-07 00:24:49','요리보고 조리봐도 요리만 사랑하는 사람들의 모임','요리보고 조리보고','1111','image/club/9-요리.jpg',1),(5,'0x7feE06cC8204100D3ed6B2b8fe4FC80B34465d07','2021-10-07 00:20:08','등산동호회','한마음산악회','1234','image/club/5-산.jpg',3),(6,'0x5FDEd0CF71D75968C41Edf66cF5b3172f67FDE8B','2021-10-07 00:20:10','강아지 사랑 모임 2','강사모2','1234','image/club/6-IMG_2713.jpg',4),(7,'0xc3397De96eeb88A1a8a4514Eb9c8F058d1cd8c04','2021-10-07 00:20:41','알고리즘 동아리입니다.','아보카도','1234','image/club/7-아보카도.jpg',2),(8,'0x7d2341b0B60393701d8fb6e76B9Bc6e462266198','2021-10-07 00:24:19','고양이를 사랑하는 사람들의 모임','고사모','1234','image/club/8-cats.jpg',5),(10,'0xc1A37a9091D9C591170Ec42A2e3cce204Ad8910d','2021-10-07 00:25:08','피아노를 좋아하는 사람들의 모임입니다','피사모','1234','image/club/10-피아노.png',2),(11,'0x298Fb35bf58525759e2d4ad5AED87051b839F477','2021-10-07 00:27:00','어쿠스틱을 사랑하면 모이라','Hanburn 통통통','','image/club/11-guitar.jfif',4),(12,'0x998BcCE98Ed82fC3919BD203a1F81DeFB9ca0B37','2021-10-07 00:27:29','자원봉사 동아리입니다.','돕는 사람들','1234','image/club/12-봉사.png',5),(13,'0x88fc8339C22b83446fda7b421C856BB2219269f2','2021-10-07 00:28:23','스터디 동호회','메타스터디','1234','image/club/13-스터디.jpg',3),(14,'0x8D0d7Ff61649344dD2cE7386e744dF26d2cafB68','2021-10-07 00:30:42','싸피고 천문 동아리','루네이트','1234','image/club/14-은하수.jpg',7);
/*!40000 ALTER TABLE `club` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-08  0:46:28
