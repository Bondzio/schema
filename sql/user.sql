-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 27, 2010 at 05:01 
-- Server version: 5.1.41
-- PHP Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `olympicsc`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) COLLATE utf8_danish_ci DEFAULT NULL,
  `lastname` varchar(90) COLLATE utf8_danish_ci DEFAULT NULL,
  `shortname` varchar(45) COLLATE utf8_danish_ci NOT NULL,
  `employeeId` varchar(10) COLLATE utf8_danish_ci DEFAULT NULL,
  `mail` varchar(75) COLLATE utf8_danish_ci DEFAULT NULL,
  `department` varchar(45) COLLATE utf8_danish_ci DEFAULT NULL,
  `phone` varchar(11) COLLATE utf8_danish_ci DEFAULT NULL,
  `admin` tinyint(1) DEFAULT NULL,
  `shirtsize` varchar(4) COLLATE utf8_danish_ci DEFAULT NULL,
  `password` varchar(45) COLLATE utf8_danish_ci DEFAULT NULL,
  `personaleforening` tinyint(1) unsigned zerofill DEFAULT '0',
  `firstlogin` tinyint(1) NOT NULL DEFAULT '1',
  `partnerwish` varchar(45) COLLATE utf8_danish_ci DEFAULT NULL,
  PRIMARY KEY (`iduser`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci AUTO_INCREMENT=5534 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`iduser`, `firstname`, `lastname`, `shortname`, `employeeId`, `mail`, `department`, `phone`, `admin`, `shirtsize`, `password`, `personaleforening`, `firstlogin`, `partnerwish`) VALUES
(5527, 'Martin', 'Hylleberg', 'mah', '2020743', 'mhylleberg@csc.com', 'CCS', '61791394', 1, '42', 'mah', 1, 0, NULL),
(5528, 'Sune', 'Håkonsson', 'sxh', '2020152', 'shaakons@csc.com', 'OCW', 'NA', 0, 'XXXL', NULL, 1, 0, NULL),
(5531, NULL, NULL, 'rni', NULL, NULL, NULL, NULL, 1, '', NULL, 0, 1, NULL),
(5532, NULL, NULL, 'aha', NULL, NULL, NULL, NULL, 1, '', NULL, 0, 1, NULL),
(5533, 'Brit Pape', 'Jensen', 'bpj', NULL, 'bjensen7@csc.com', 'Finance', '29237137', 1, '14', NULL, 1, 0, NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
