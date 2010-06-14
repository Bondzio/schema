DROP TABLE IF EXISTS `olympicsc`.`customvaluetype`;
CREATE TABLE  `olympicsc`.`customvaluetype` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  `TYPE` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;