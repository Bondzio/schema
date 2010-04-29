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
-- Table structure for table `activity`
--

CREATE TABLE IF NOT EXISTS `activity` (
  `idactivity` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_danish_ci DEFAULT NULL,
  `description` longtext COLLATE utf8_danish_ci,
  `start` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `minimumPlayers` int(11) DEFAULT NULL,
  `minimumTeams` int(11) DEFAULT NULL,
  `location_idlocation` int(11) DEFAULT NULL,
  `canrequestpartner` tinyint(1) NOT NULL DEFAULT '0',
  `responsible` varchar(90) COLLATE utf8_danish_ci DEFAULT NULL,
  `memberprice` int(10) unsigned DEFAULT NULL,
  `notmemberprice` int(10) unsigned DEFAULT NULL,
  `noshowprice` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`idactivity`) USING BTREE,
  KEY `fk_activity_location1` (`location_idlocation`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci AUTO_INCREMENT=25 ;

--
-- Dumping data for table `activity`
--

INSERT INTO `activity` (`idactivity`, `name`, `description`, `start`, `end`, `minimumPlayers`, `minimumTeams`, `location_idlocation`, `canrequestpartner`, `responsible`, `memberprice`, `notmemberprice`, `noshowprice`) VALUES
(1, 'Morgenmad', 'Hvis du har lyst til at spise morgenmad sammen med dine kollegaer, skal du tilmelde dig og huske at møde op. \r\nBEMÆRK: Dette er en betalbar aktivitet, hvor du bliver trukket ekstra, hvis du tilmelder dig og ikke dukker op!', '2010-08-21 08:45:00', '2010-08-21 09:30:00', 0, 0, 103, 0, 'BPJ', 50, 50, 100),
(2, 'Street basket', 'Gadespillet, hvor du kan kanøfle din chef', '2010-08-21 09:30:00', '2010-08-21 12:30:00', 5, 0, 105, 0, 'MAH', NULL, NULL, NULL),
(5, 'Morgenmad', 'Fælles morgenmad. \r\nHUSK dette er en betalbar aktivitet, hvor du vil blive bonget dobbelt, hvis du ikke dukker op!!!', '2010-08-21 08:45:00', '2010-08-21 09:30:00', 0, 0, 103, 0, 'BJP', 50, 50, 100),
(6, 'Morgenmad', 'Er du tilmeldt morgenmaden og udebliver koster dette kr. XX', '2010-08-21 08:45:00', '2010-08-21 09:30:00', 0, 0, 103, 0, 'Lars Dragsbæk', NULL, NULL, NULL),
(7, 'Morgenmad', 'Er du tilmeldt morgenmaden og udebliver koster dette kr. XX', '2010-08-21 08:45:00', '2010-08-21 09:30:00', 0, 0, 103, 0, 'Lars Dragsbæk', NULL, NULL, NULL),
(8, 'Airhockey', 'Der vil blive en herre og dame pulje', '2010-08-21 09:30:00', '2010-08-21 12:00:00', 0, 0, 104, 0, 'Margit Holk', NULL, NULL, NULL),
(9, 'Backgammon', '', '2010-08-21 13:00:00', '2010-08-21 15:00:00', 0, 0, 103, 0, 'Morten Poulsen', NULL, NULL, NULL),
(10, 'Lawn Volley', '', '2010-08-21 11:30:00', '2010-08-21 14:00:00', 0, 0, 105, 0, 'Mathias Aagaard', NULL, NULL, NULL),
(11, 'Bordfodbold', '', '2010-08-21 12:00:00', '2010-08-21 14:30:00', 2, 0, 104, 1, 'Steffen Arnholtz', NULL, NULL, NULL),
(12, 'Bordtennis', '', '2010-08-21 09:30:00', '2010-08-21 11:30:00', 2, 0, 104, 0, 'Thomas Skovfoged', NULL, NULL, NULL),
(13, 'Dart', '', '2010-08-21 09:30:00', '2010-08-21 11:00:00', 4, 0, 104, 1, 'Stig T. Kristensen', NULL, NULL, NULL),
(14, 'Fodbold', '', '2010-08-21 16:30:00', '2010-08-21 17:45:00', 10, 0, 105, 0, 'Lars Hougaard', NULL, NULL, NULL),
(15, 'Hockey', '', '2010-08-21 13:00:00', '2010-08-21 14:30:00', 0, 0, 108, 0, 'Casper Voldstedslund', NULL, NULL, NULL),
(16, 'Kroket', '', '2010-08-21 13:00:00', '2010-08-21 14:30:00', 4, 0, 106, 1, 'Erik K Petersen', NULL, NULL, NULL),
(17, 'Løb', 'Der er mulighed for at vælge mellem 5 og 10 km (2 runder) distancer. \r\nDer løbes uden ur og vinderen er den der løber tættest på den forudsagte tid.\r\n', '2010-08-21 10:00:00', '2010-08-21 11:00:00', 0, 0, 108, 0, 'Allan Petersen', NULL, NULL, NULL),
(18, 'Petanque', '', '2010-08-21 10:00:00', '2010-08-21 15:00:00', 0, 0, 107, 1, 'Irving Knus', NULL, NULL, NULL),
(19, 'Pool', '', '2010-08-21 11:00:00', '2010-08-21 13:00:00', 2, 0, 104, 0, 'Lene Christensen', NULL, NULL, NULL),
(20, 'Rundbold', '', '2010-08-21 15:00:00', '2010-08-21 16:30:00', 0, 0, 105, 0, 'Frede', NULL, NULL, NULL),
(21, 'Softtennis', '', '2010-08-21 11:00:00', '2010-08-21 15:00:00', 2, 0, 108, 0, 'Jørgen Elkjær', NULL, NULL, NULL),
(22, 'Tovtrækning', '', '2010-08-21 17:45:00', '2010-08-21 18:15:00', 0, 0, 107, 0, 'Lars Lilholt', NULL, NULL, NULL),
(23, 'Trivial Pursuit', '', '2010-08-21 11:00:00', '2010-08-21 14:00:00', 4, 0, 103, 1, 'Lise Rich', NULL, NULL, NULL),
(24, 'Vikinge spil', '', '2010-08-21 11:00:00', '2010-08-21 15:00:00', 0, 0, 106, 1, 'Casper G Christensen', NULL, NULL, NULL);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `activity`
--
ALTER TABLE `activity`
  ADD CONSTRAINT `fk_activity_location1` FOREIGN KEY (`location_idlocation`) REFERENCES `location` (`idlocation`) ON DELETE SET NULL ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
