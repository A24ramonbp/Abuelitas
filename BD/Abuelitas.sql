/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19  Distrib 10.11.11-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: Abuelitas
-- ------------------------------------------------------
-- Server version	10.11.11-MariaDB-0+deb12u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Centro`
--

DROP TABLE IF EXISTS `Centro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Centro` (
  `code` varchar(10) NOT NULL,
  `name` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `address` varchar(40) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `ca` varchar(40) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Centro`
--

LOCK TABLES `Centro` WRITE;
/*!40000 ALTER TABLE `Centro` DISABLE KEYS */;
INSERT INTO `Centro` VALUES
('IO/123451','asdas','asdas@gasd.com','asdadsda','987987987','Canarias'),
('IO/123456','Centro','centro@gmail.com','Direccion','698698698','Aragón'),
('IO/987987','asdasasd','asdasd@mgail.com','asdasd','987987987','Aragón');
/*!40000 ALTER TABLE `Centro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cliente`
--

DROP TABLE IF EXISTS `Cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Cliente` (
  `dni` varchar(10) NOT NULL,
  `name` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `address` varchar(40) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `ca` varchar(40) NOT NULL,
  `cuidador` int(6) unsigned NOT NULL,
  `code` varchar(10) NOT NULL,
  `neurologo` int(6) unsigned DEFAULT NULL,
  `fisioterapeuta` int(6) unsigned DEFAULT NULL,
  PRIMARY KEY (`dni`),
  KEY `cuidador` (`cuidador`),
  KEY `code` (`code`),
  KEY `neurologo` (`neurologo`),
  KEY `fisioterapeuta` (`fisioterapeuta`),
  CONSTRAINT `Cliente_ibfk_1` FOREIGN KEY (`cuidador`) REFERENCES `UsuarioCuidador` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Cliente_ibfk_2` FOREIGN KEY (`code`) REFERENCES `Centro` (`code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Cliente_ibfk_3` FOREIGN KEY (`neurologo`) REFERENCES `UsuarioCuidador` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Cliente_ibfk_4` FOREIGN KEY (`fisioterapeuta`) REFERENCES `UsuarioCuidador` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cliente`
--

LOCK TABLES `Cliente` WRITE;
/*!40000 ALTER TABLE `Cliente` DISABLE KEYS */;
INSERT INTO `Cliente` VALUES
('20628698K','Begoña','Begoña@gmail.com','Direccion','698698698','Aragón',1,'IO/123456',NULL,3),
('20628698L','Julia','Julia@gmail.com','Direccion','698698698','Castilla-La Mancha',1,'IO/123456',2,3);
/*!40000 ALTER TABLE `Cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tareas`
--

DROP TABLE IF EXISTS `Tareas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Tareas` (
  `id` int(6) unsigned NOT NULL,
  `description` varchar(200) NOT NULL,
  `especialidad` varchar(30) NOT NULL,
  `code` varchar(10) NOT NULL,
  PRIMARY KEY (`id`,`code`),
  KEY `code` (`code`),
  CONSTRAINT `Tareas_ibfk_1` FOREIGN KEY (`code`) REFERENCES `Centro` (`code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tareas`
--

LOCK TABLES `Tareas` WRITE;
/*!40000 ALTER TABLE `Tareas` DISABLE KEYS */;
INSERT INTO `Tareas` VALUES
(4,'Pensar mas','Neurologo','IO/123456'),
(12,'asdad','Fisioterapeuta','IO/123456'),
(123,'Correr','Fisioterapeuta','IO/123456');
/*!40000 ALTER TABLE `Tareas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TareasAsignadas`
--

DROP TABLE IF EXISTS `TareasAsignadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `TareasAsignadas` (
  `dni` varchar(10) NOT NULL,
  `name` varchar(40) NOT NULL,
  `medico_id` int(6) unsigned NOT NULL,
  `especialidad` varchar(30) NOT NULL,
  `tarea_id` int(6) unsigned NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `code` varchar(10) NOT NULL,
  PRIMARY KEY (`dni`,`medico_id`,`tarea_id`),
  KEY `medico_id` (`medico_id`),
  KEY `tarea_id` (`tarea_id`,`code`),
  CONSTRAINT `TareasAsignadas_ibfk_1` FOREIGN KEY (`dni`) REFERENCES `Cliente` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `TareasAsignadas_ibfk_2` FOREIGN KEY (`medico_id`) REFERENCES `UsuarioCuidador` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `TareasAsignadas_ibfk_3` FOREIGN KEY (`tarea_id`, `code`) REFERENCES `Tareas` (`id`, `code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TareasAsignadas`
--

LOCK TABLES `TareasAsignadas` WRITE;
/*!40000 ALTER TABLE `TareasAsignadas` DISABLE KEYS */;
INSERT INTO `TareasAsignadas` VALUES
('20628698K','Begoña',3,'Fisioterapeuta',123,'Correr','IO/123456'),
('20628698L','Julia',2,'Neurologo',4,'Pensar mas','IO/123456');
/*!40000 ALTER TABLE `TareasAsignadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TareasPersonal`
--

DROP TABLE IF EXISTS `TareasPersonal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `TareasPersonal` (
  `id` int(6) unsigned NOT NULL,
  `dni` varchar(10) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dni` (`dni`),
  CONSTRAINT `TareasPersonal_ibfk_1` FOREIGN KEY (`dni`) REFERENCES `UsuarioPersonal` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TareasPersonal`
--

LOCK TABLES `TareasPersonal` WRITE;
/*!40000 ALTER TABLE `TareasPersonal` DISABLE KEYS */;
INSERT INTO `TareasPersonal` VALUES
(123,'20628698K','Correr');
/*!40000 ALTER TABLE `TareasPersonal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UsuarioAdmin`
--

DROP TABLE IF EXISTS `UsuarioAdmin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `UsuarioAdmin` (
  `dni` varchar(10) NOT NULL,
  `user` varchar(30) NOT NULL,
  `password` varchar(200) NOT NULL,
  `name` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `address` varchar(40) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `ca` varchar(40) NOT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UsuarioAdmin`
--

LOCK TABLES `UsuarioAdmin` WRITE;
/*!40000 ALTER TABLE `UsuarioAdmin` DISABLE KEYS */;
INSERT INTO `UsuarioAdmin` VALUES
('00000000A','Admin','$2a$10$E1UDi.MPzAh67eGBth/mbuo8NcdHsrMItecQMdNc3BvEJPq/vhmw.','Administrador','admin@ejemplo.com','Calle Falsa 123','600000000','CA1');
/*!40000 ALTER TABLE `UsuarioAdmin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UsuarioCuidador`
--

DROP TABLE IF EXISTS `UsuarioCuidador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `UsuarioCuidador` (
  `id` int(6) unsigned NOT NULL,
  `user` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `dni` varchar(10) NOT NULL,
  `name` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `address` varchar(40) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `ca` varchar(40) NOT NULL,
  `rol` varchar(30) NOT NULL,
  `code` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `code` (`code`),
  CONSTRAINT `UsuarioCuidador_ibfk_1` FOREIGN KEY (`code`) REFERENCES `Centro` (`code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UsuarioCuidador`
--

LOCK TABLES `UsuarioCuidador` WRITE;
/*!40000 ALTER TABLE `UsuarioCuidador` DISABLE KEYS */;
INSERT INTO `UsuarioCuidador` VALUES
(1,'Paula','$2a$10$E1UDi.MPzAh67eGBth/mbuo8NcdHsrMItecQMdNc3BvEJPq/vhmw.','20628698I','Paula','paula@gmail.com','Direccion','987987987','Canarias','Cuidador','IO/123456'),
(2,'Lucas','$2a$10$haphnnicRftt25/O6TxA6u/U58Ph5G9QBZXm9VPym3e2wmABImFuq','20628698L','Lucas','Lucas@gmail.com','Direccion','987987988','Canarias','Neurologo','IO/123456'),
(3,'Nico','$2a$10$lIp0byM5CyYoBIko1..zUufPMSBOSwUyGFsfmjscNndItb7LpcQfy','20628698X','Nico','Nico@gmail.com','Direccion','987987987','Canarias','Fisioterapeuta','IO/123456');
/*!40000 ALTER TABLE `UsuarioCuidador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UsuarioPersonal`
--

DROP TABLE IF EXISTS `UsuarioPersonal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `UsuarioPersonal` (
  `dni` varchar(10) NOT NULL,
  `user` varchar(30) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `name` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `address` varchar(40) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `ca` varchar(40) NOT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UsuarioPersonal`
--

LOCK TABLES `UsuarioPersonal` WRITE;
/*!40000 ALTER TABLE `UsuarioPersonal` DISABLE KEYS */;
INSERT INTO `UsuarioPersonal` VALUES
('20627676a','Lucass','$2a$10$TCy9gV3ACyrOaJSRVYPOMOOTtc0lWpDVb2z.QYdMeqphDEo/XVpcm','asdadad','asd@gmai.com','Direccion','098098098','Canarias'),
('20628698K','Pablo','$2a$10$1ZtjYMvvvFfB3phbWlcIsucLRZrefk13WXKcGRkCtU/MtcpkUiFia','Pablo','pablo@gmail.com','Direccion','123456789','Andalucía');
/*!40000 ALTER TABLE `UsuarioPersonal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UsuarioProfesional`
--

DROP TABLE IF EXISTS `UsuarioProfesional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `UsuarioProfesional` (
  `code` varchar(10) NOT NULL,
  `user` varchar(30) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `name` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `address` varchar(40) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `ca` varchar(40) NOT NULL,
  PRIMARY KEY (`code`),
  CONSTRAINT `UsuarioProfesional_ibfk_1` FOREIGN KEY (`code`) REFERENCES `Centro` (`code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UsuarioProfesional`
--

LOCK TABLES `UsuarioProfesional` WRITE;
/*!40000 ALTER TABLE `UsuarioProfesional` DISABLE KEYS */;
INSERT INTO `UsuarioProfesional` VALUES
('IO/123451','Lucas23','$2a$10$9U0T64nOBqVAhCWH.XxvLuV4sU1etQAChu.GfJv.d44amtuKcjmRi','asdas','asdas@gasd.com','asdadsda','987987987','Canarias'),
('IO/123456','Centro','$2a$10$eYFSGjIwywsMNdeqOM6ldek6h2LkmhS0nGpGvdVwZ7J/Es23eFRmS','Centro','centro@gmail.com','Direccion','698698698','Canarias'),
('IO/987987','ACASCASD','$2a$10$PpjPP6RDeZ5n0OVuTcYkMOLQ5azjtzLHyvO79ckAdIs6GCHKVeA1u','asdasasd','asdasd@mgail.com','asdasd','987987987','Aragón');
/*!40000 ALTER TABLE `UsuarioProfesional` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-08  6:05:12
