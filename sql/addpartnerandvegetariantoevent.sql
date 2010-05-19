ALTER TABLE `olympicsc`.`event` ADD COLUMN `canrequestpartner` BOOLEAN DEFAULT 0 AFTER `logo_idlogo`,
 ADD COLUMN `canrequestvegetarian` BOOLEAN DEFAULT 0 AFTER `canrequestpartner`;
