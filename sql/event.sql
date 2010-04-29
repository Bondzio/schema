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
-- Table structure for table `event`
--

CREATE TABLE IF NOT EXISTS `event` (
  `idevent` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_danish_ci NOT NULL,
  `description` longtext COLLATE utf8_danish_ci,
  `logo` varchar(255) COLLATE utf8_danish_ci DEFAULT NULL,
  `start` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `signstart` datetime DEFAULT NULL,
  `signend` datetime DEFAULT NULL,
  `location_idlocation` int(11) DEFAULT NULL,
  `unsignend` datetime DEFAULT NULL,
  `minimumusers` int(10) unsigned DEFAULT NULL,
  `memberprice` int(10) unsigned DEFAULT NULL COMMENT 'personaleforening',
  `notmemberprice` int(10) unsigned DEFAULT NULL COMMENT 'ikke personaly forening',
  `noshowprice` int(10) unsigned DEFAULT NULL COMMENT 'udeblivelse',
  `logo_idlogo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idevent`) USING BTREE,
  KEY `fk_event_location1` (`location_idlocation`),
  KEY `fk_event_logo1` (`logo_idlogo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci AUTO_INCREMENT=449 ;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`idevent`, `name`, `description`, `logo`, `start`, `end`, `enabled`, `signstart`, `signend`, `location_idlocation`, `unsignend`, `minimumusers`, `memberprice`, `notmemberprice`, `noshowprice`, `logo_idlogo`) VALUES
(439, 'Olympicsc', '21. august 2010\r\n\r\n\r\nEr du til fest, farver og fibersprængninger – så deltag i OlympiCSC!\r\n\r\n\r\nOlympiCSC afholdes lørdag den 21. august 2010 her på SC.\r\n\r\nDu skal blot tilmelde dig samt vælge, hvilke aktiviteter du ønsker, at deltage i.\r\n\r\nHusk det skal ske inden den 19. maj 2009 – dette er vigtigt for den videre planlægning.\r\n\r\nDeltagelse koster 100,- for medlemmer af Personaleforeningen. Beløbet vil blive trukket\r\nover lønnen – derfor skal personalenummer udfyldes.\r\n\r\nDeltagelse for ikke medlemmer koster 550,- som skal afregnes ved tilmelding.\r\n\r\n(Ved afbud/udeblivelse betales 400,-) \r\n\r\nI arrangementet indgår følgende:\r\n\r\n•	Sport og leg i rigelige mængder\r\n•	1 hold T-shirt.\r\n•	Morgenmad \r\n•	Frokost \r\n•	Festmiddag inkl. vin til maden.\r\n•	Øl / sodavand i løbet af dagen\r\n\r\nTilmeld dig nu og gør dit til, at vi får det lige så sjovt, som vi plejer.\r\n\r\nVi ses / OlympiCSC Komiteen - Personaleforeningen\r\n', NULL, '2010-08-21 08:45:00', '2010-08-21 23:59:00', 0, '2010-04-15 12:00:00', '2010-05-19 12:00:00', 103, '2010-07-30 12:00:00', NULL, 100, 550, 400, NULL);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `fk_event_location1` FOREIGN KEY (`location_idlocation`) REFERENCES `location` (`idlocation`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_event_logo1` FOREIGN KEY (`logo_idlogo`) REFERENCES `logo` (`idLogo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
