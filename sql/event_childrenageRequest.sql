DROP TABLE IF EXISTS `olympicsc`.`event_childrenage_request`;
CREATE TABLE  `olympicsc`.`event_childrenage_request` (
  `idchildrenagerequest` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `iduser` int(10) unsigned NOT NULL,
  `idevent` int(10) unsigned NOT NULL,
  `childrenage` varchar(90) NOT NULL,
  PRIMARY KEY (`idchildrenagerequest`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;