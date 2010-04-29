-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 27, 2010 at 05:03 
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
-- Table structure for table `user_has_event`
--

CREATE TABLE IF NOT EXISTS `user_has_event` (
  `user_iduser` int(11) NOT NULL,
  `event_idevent` int(11) NOT NULL,
  PRIMARY KEY (`user_iduser`,`event_idevent`),
  KEY `fk_user_has_event_user` (`user_iduser`),
  KEY `fk_user_has_event_event1` (`event_idevent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

--
-- Dumping data for table `user_has_event`
--

INSERT INTO `user_has_event` (`user_iduser`, `event_idevent`) VALUES
(5527, 439),
(5528, 439),
(5533, 439);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `user_has_event`
--
ALTER TABLE `user_has_event`
  ADD CONSTRAINT `fk_user_has_event_event1` FOREIGN KEY (`event_idevent`) REFERENCES `event` (`idevent`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_user_has_event_user` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
