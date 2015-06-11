CREATE DATABASE  IF NOT EXISTS `mplayerdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mplayerdb`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win32 (x86)
--
-- Host: localhost    Database: db
-- ------------------------------------------------------
-- Server version	5.6.25

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
-- Table structure for table `media`
-- `Id` - идентификатор записи
-- `SongName` - название песни. вводится ручками
-- `FileName` - наименование файла песни
-- `SongData` - MD5 сумма от файла песни (если 2 раза пытаться загрузить один файл, то храниться будет один так как хэш совпадет)
-- `SongSize` - размер файла в Кб
-- `CreateDate` - дата создания записи
-- `ChangeDate` - дата изменения записи, обновляется триггером.
--

DROP TABLE IF EXISTS `media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `media` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `SongName` varchar(64) DEFAULT NULL,
  `FileName` varchar(64) NOT NULL,
  `SongData` blob,
  `SongSize` int(11) DEFAULT NULL,
  `CreateDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ChangeDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `mplayerdb`.`media_AFTER_UPDATE` BEFORE UPDATE ON `media` FOR EACH ROW
BEGIN
	set new.CHANGEDATE = CURRENT_TIMESTAMP;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `users`
-- `UserName` - имя пользователя.
-- `Password` - MD5 от пароля.
-- `Administrator` - флаг админа.
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `UserName` varchar(32) NOT NULL,
  `Password` varchar(256) NOT NULL,
  `Administrator` tinyint(1) NOT NULL DEFAULT '0',
  UNIQUE KEY `UserName` (`UserName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Два начальных пользователя, пароль "test" у обоих.
INSERT INTO USERS (UserName, Password, Administrator) values
('User', '74657374', '0'),
('Administrator', '74657374', '1');

-- --------------------------------- --
-- TEST DATA! COMMENT IT IF YOU WISH --
-- --------------------------------- --
insert into media (songname, filename, songdata, songsize)
values
("test1", "test1", null, 0),
("test2", "test2", null, 0),
("test3", "test3", null, 0),
("test4", "test4", null, 0),
("test5", "test5", null, 0),
("test6", "test6", null, 0),
("test7", "test7", null, 0),
("test8", "test8", null, 0),
("test9", "test9", null, 0),
("test10", "test10", null, 0),
("test11", "test11", null, 0),
("test12", "test12", null, 0),
("test13", "test13", null, 0),
("test14", "test14", null, 0),
("test15", "test15", null, 0),
("test16", "test16", null, 0),
("test17", "test17", null, 0),
("test18", "test18", null, 0),
("test19", "test19", null, 0),
("test20", "test20", null, 0),
("test21", "test21", null, 0),
("test22", "test22", null, 0),
("test23", "test23", null, 0),
("test24", "test24", null, 0),
("test25", "test25", null, 0),
("test26", "test26", null, 0),
("test27", "test27", null, 0),
("test28", "test28", null, 0),
("test29", "test29", null, 0),
("test30", "test30", null, 0),
("test31", "test31", null, 0),
("test32", "test32", null, 0),
("test33", "test33", null, 0),
("test34", "test34", null, 0),
("test35", "test35", null, 0),
("test36", "test36", null, 0),
("test37", "test37", null, 0),
("test38", "test38", null, 0),
("test39", "test39", null, 0),
("test40", "test40", null, 0),
("test41", "test41", null, 0),
("test42", "test42", null, 0),
("test43", "test43", null, 0),
("test44", "test44", null, 0),
("test45", "test45", null, 0),
("test46", "test46", null, 0),
("test47", "test47", null, 0),
("test48", "test48", null, 0),
("test49", "test49", null, 0),
("test50", "test50", null, 0),
("test51", "test51", null, 0),
("test52", "test52", null, 0),
("test53", "test53", null, 0),
("test54", "test54", null, 0),
("test55", "test55", null, 0),
("test56", "test56", null, 0),
("test57", "test57", null, 0),
("test58", "test58", null, 0),
("test59", "test59", null, 0),
("test60", "test60", null, 0),
("test61", "test61", null, 0),
("test62", "test62", null, 0),
("test63", "test63", null, 0),
("test64", "test64", null, 0),
("test65", "test65", null, 0),
("test66", "test66", null, 0),
("test67", "test67", null, 0),
("test68", "test68", null, 0),
("test69", "test69", null, 0),
("test70", "test70", null, 0),
("test71", "test71", null, 0),
("test72", "test72", null, 0),
("test73", "test73", null, 0),
("test74", "test74", null, 0),
("test75", "test75", null, 0),
("test76", "test76", null, 0),
("test77", "test77", null, 0),
("test78", "test78", null, 0),
("test79", "test79", null, 0),
("test80", "test80", null, 0),
("test81", "test81", null, 0),
("test82", "test82", null, 0),
("test83", "test83", null, 0),
("test84", "test84", null, 0),
("test85", "test85", null, 0),
("test86", "test86", null, 0),
("test87", "test87", null, 0),
("test88", "test88", null, 0),
("test89", "test89", null, 0),
("test90", "test90", null, 0),
("test91", "test91", null, 0),
("test92", "test92", null, 0),
("test93", "test93", null, 0),
("test94", "test94", null, 0),
("test95", "test95", null, 0),
("test96", "test96", null, 0),
("test97", "test97", null, 0),
("test98", "test98", null, 0),
("test99", "test99", null, 0),
("test100", "test100", null, 0);
-- --------------------------------- --
-- --------------------------------- --
-- --------------------------------- --

--
-- Dumping events for database 'db'
--

--
-- Dumping routines for database 'db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-11  9:51:47
