-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 27, 2010 at 05:02 
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
-- Table structure for table `event_has_activity`
--

CREATE TABLE IF NOT EXISTS `event_has_activity` (
  `event_idevent` int(11) NOT NULL DEFAULT '0',
  `activitiy_idactivity` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`event_idevent`,`activitiy_idactivity`),
  KEY `FK_event_has_activity_2` (`activitiy_idactivity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

--
-- Dumping data for table `event_has_activity`
--

INSERT INTO `event_has_activity` (`event_idevent`, `activitiy_idactivity`) VALUES
(439, 7),
(439, 8),
(439, 9),
(439, 10),
(439, 11),
(439, 12),
(439, 13),
(439, 14),
(439, 15),
(439, 16),
(439, 17),
(439, 18),
(439, 19),
(439, 20),
(439, 21),
(439, 22),
(439, 23),
(439, 24);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `event_has_activity`
--
ALTER TABLE `event_has_activity`
  ADD CONSTRAINT `FK_event_has_activity_1` FOREIGN KEY (`event_idevent`) REFERENCES `event` (`idevent`),
  ADD CONSTRAINT `FK_event_has_activity_2` FOREIGN KEY (`activitiy_idactivity`) REFERENCES `activity` (`idactivity`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
