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
-- Table structure for table `user_has_activity`
--

CREATE TABLE IF NOT EXISTS `user_has_activity` (
  `user_iduser` int(11) NOT NULL,
  `activity_idactivity` int(11) NOT NULL,
  PRIMARY KEY (`user_iduser`,`activity_idactivity`),
  KEY `fk_user_has_activity_user1` (`user_iduser`),
  KEY `fk_user_has_activity_activity1` (`activity_idactivity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

--
-- Dumping data for table `user_has_activity`
--

INSERT INTO `user_has_activity` (`user_iduser`, `activity_idactivity`) VALUES
(5527, 1),
(5527, 7),
(5527, 8),
(5527, 19),
(5533, 7),
(5533, 18),
(5533, 19),
(5533, 23);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `user_has_activity`
--
ALTER TABLE `user_has_activity`
  ADD CONSTRAINT `fk_user_has_activity_activity1` FOREIGN KEY (`activity_idactivity`) REFERENCES `activity` (`idactivity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_user_has_activity_user1` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
