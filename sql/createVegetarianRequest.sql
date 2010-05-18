DROP TABLE IF EXISTS `olympicsc`.`event_vegetarian_request`;
CREATE TABLE  `olympicsc`.`event_vegetarian_request` (
  `idvegetarianrequest` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `iduser` int(10) unsigned NOT NULL,
  `idevent` int(10) unsigned NOT NULL,
  `vegetarian` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`idvegetarianrequest`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=latin1;