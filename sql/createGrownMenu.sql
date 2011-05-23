DROP TABLE IF EXISTS `olympicsc`.`event_grownmenu_request`;
CREATE TABLE  `olympicsc`.`event_grownmenu_request` (
  `idevent_grownmenu_request` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `iduser` int(10) unsigned NOT NULL,
  `idevent` int(10) unsigned NOT NULL,
  `grownmenus` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idevent_grownmenu_request`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;