DROP TABLE IF EXISTS `olympicsc`.`event_partner_request`;
CREATE TABLE  `olympicsc`.`event_partner_request` (
  `idpartnerrequest` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `iduser` int(10) unsigned NOT NULL,
  `idevent` int(10) unsigned NOT NULL,
  `partnerrequest` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`idpartnerrequest`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;