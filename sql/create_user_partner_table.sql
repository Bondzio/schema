CREATE TABLE `olympicsc`.`activity_partner_request` (
  `idpartnerrequest` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `iduser` INTEGER UNSIGNED NOT NULL,
  `idactivity` INTEGER UNSIGNED NOT NULL,
  `partnernames` VARCHAR(90) NOT NULL,
  PRIMARY KEY (`idpartnerrequest`)
)
ENGINE = InnoDB;