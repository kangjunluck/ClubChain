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
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `section` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `view` int NOT NULL,
  `clubid` bigint DEFAULT NULL,
  `userid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK46bredl8rk0xdqy60thqd1268` (`clubid`),
  KEY `FKbon6omt9qov9iduumkuj117xw` (`userid`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,'매일매일 자신의 댕댕이를 뽑내주세요!!','2021-10-07 00:30:38','분류','[공지] 강아지 사진을 마음껏 공유해주세요','2021-10-07 00:30:38',0,6,4),(2,'\n안녕하세요. 해왕성 회장 김기도입니다!!\n','2021-10-07 00:30:40','분류','극예술연구회 해왕성입니다.','2021-10-07 21:11:42',0,2,3),(3,'미모의 고양이를 만났습니다.','2021-10-07 00:34:13','분류','미묘','2021-10-07 00:34:13',0,8,5),(4,'길고양이, 집고양이 가리지 않고 많이많이 자랑해주세요!','2021-10-07 00:34:51','분류','모두 환영합니다~','2021-10-07 00:34:51',0,8,5),(5,'이름 : 서로\n나이 : 2살\n종 : 비숑프리제\n성별 : 왕자님\n취미 : 비숑타임, 우다다다\n특기 : 손, 발, 엎드려, 빵야, 다 됩니다','2021-10-07 00:34:54','분류','[소개] 서로를 소개합니다','2021-10-07 00:34:54',0,6,4),(6,'오늘 뜬 달 사진입니다!','2021-10-07 00:35:06','분류','오늘의 달','2021-10-07 00:35:06',0,14,7),(7,'고영님 발바닥 젤리 만지고싶다 ㅠㅠ','2021-10-07 00:35:16','분류','누운 고영','2021-10-07 00:35:16',0,8,5),(8,'안녕하세요. 한마음산악회 회장 김기도입니다.','2021-10-07 00:35:25','분류','첫 인사','2021-10-07 00:35:25',0,5,3),(9,'안녕하세요 메타스터디장 김기도입니다.','2021-10-07 00:35:47','분류','메타스터디','2021-10-07 00:35:47',0,13,3),(10,'주인님 : 집사야 츄르를 내놓거라!','2021-10-07 00:35:51','분류','우리집 주인님','2021-10-07 00:35:51',0,8,5),(11,'고양이는 야옹야옹','2021-10-07 00:36:12','분류','고영고영','2021-10-07 00:36:12',0,8,5),(12,'\n01092668459 연락바랍니다.','2021-10-07 02:45:04','분류','고양이 분양 받습니다','2021-10-07 02:45:04',0,8,3),(15,'안녕하세요!! 오늘 가입했습니다. 잘 부탁드려요','2021-10-07 17:31:37','분류','안녕하세요!','2021-10-07 17:31:37',0,6,7),(16,'고양이 분양 1일차인데, 꿀팁좀 알려주세욥','2021-10-07 17:37:25','분류','[소개] 초보 고양이 아빠입니다','2021-10-07 17:37:25',0,8,4),(30,'잘 부탁드립니다!','2021-10-07 22:21:06','분류','안녕하세요!','2021-10-07 22:21:06',0,8,1),(18,'서로 친구 누룽지입니다. 귀엽죠~','2021-10-07 17:45:17','분류','[소개] 서로 친구 누룽지입니다','2021-10-07 17:45:17',0,6,4),(31,'반갑습니다 문제 같이 풀어봐요!','2021-10-08 00:34:46','분류','안녕하세요~~알고리즘 동아리입니다','2021-10-08 00:34:46',0,7,2),(21,'추천해주세요!','2021-10-07 17:50:06','분류','오늘 어떤 음식을 먹을까요??','2021-10-07 17:50:06',0,9,1),(25,'총총총','2021-10-07 21:12:16','분류','사진 업로드해봅니다,,','2021-10-07 21:12:16',0,2,3);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
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
