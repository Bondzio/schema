DROP TABLE IF EXISTS `olympicsc`.`event_childrenmenu_request`;
CREATE TABLE  `olympicsc`.`event_childrenmenu_request` (
  `idevent_childrenmenu_request` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `iduser` int(10) unsigned NOT NULL,
  `idevent` int(10) unsigned NOT NULL,
  `childrenmenus` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idevent_childrenmenu_request`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;