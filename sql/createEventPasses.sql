DROP TABLE IF EXISTS `olympicsc`.`event_numberofpasses_request`;
CREATE TABLE  `olympicsc`.`event_numberofpasses_request` (
  `idevent_numberofpasses_request` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `iduser` int(10) unsigned NOT NULL,
  `idevent` int(10) unsigned NOT NULL,
  `numberofpasses` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idevent_numberofpasses_request`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;