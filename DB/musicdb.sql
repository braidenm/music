-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema musicdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `musicdb` ;

-- -----------------------------------------------------
-- Schema musicdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `musicdb` DEFAULT CHARACTER SET utf8 ;
USE `musicdb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(500) NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `role` VARCHAR(45) NOT NULL DEFAULT 'standard',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NULL,
  `street_2` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `emplpyee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `emplpyee` ;

CREATE TABLE IF NOT EXISTS `emplpyee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `pay_rate` DECIMAL NULL,
  `user_id` INT NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `address_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_employee_to_user_idx` (`user_id` ASC),
  INDEX `fk_employee_to_address_idx` (`address_id` ASC),
  CONSTRAINT `fk_employee_to_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_to_address`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `client` ;

CREATE TABLE IF NOT EXISTS `client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `fiancee_fname` VARCHAR(100) NULL,
  `fiancee_lname` VARCHAR(100) NULL,
  `phone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NULL,
  `address_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_client_to_address_idx` (`address_id` ASC),
  CONSTRAINT `fk_client_to_address`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `package`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `package` ;

CREATE TABLE IF NOT EXISTS `package` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `length` INT NOT NULL,
  `price` INT NOT NULL,
  `lights` INT NULL,
  `extras` VARCHAR(500) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event` ;

CREATE TABLE IF NOT EXISTS `event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `category` VARCHAR(100) NULL,
  `paid_in_full` TINYINT NULL,
  `package_id` INT NULL,
  `amount_paid` INT NULL,
  `start_date` VARCHAR(45) NULL,
  `end_date` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_to_package_idx` (`package_id` ASC),
  CONSTRAINT `fk_event_to_package`
    FOREIGN KEY (`package_id`)
    REFERENCES `package` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `client_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `client_event` ;

CREATE TABLE IF NOT EXISTS `client_event` (
  `client_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  PRIMARY KEY (`client_id`, `event_id`),
  INDEX `fk_event_to_client_idx` (`event_id` ASC),
  CONSTRAINT `fk_client_to_event`
    FOREIGN KEY (`client_id`)
    REFERENCES `client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_to_client`
    FOREIGN KEY (`event_id`)
    REFERENCES `event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vendor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vendor` ;

CREATE TABLE IF NOT EXISTS `vendor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NULL,
  `last_name` VARCHAR(100) NULL,
  `category` VARCHAR(100) NULL,
  `phone` VARCHAR(45) NULL,
  `email` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `venue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `venue` ;

CREATE TABLE IF NOT EXISTS `venue` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `address_id` INT NULL,
  `owner_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_venue_to_address_idx` (`address_id` ASC),
  INDEX `fk_venue_to_vendor_owner_idx` (`owner_id` ASC),
  CONSTRAINT `fk_venue_to_address`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venue_to_vendor_owner`
    FOREIGN KEY (`owner_id`)
    REFERENCES `vendor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `note`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `note` ;

CREATE TABLE IF NOT EXISTS `note` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `note` TEXT NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `client_id` INT NULL,
  `event_id` INT NULL,
  `venue_id` INT NULL,
  `vendor_id` INT NULL,
  `employee_id` INT NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_note_to_client_idx` (`client_id` ASC),
  INDEX `fk_note_to_event_idx` (`event_id` ASC),
  INDEX `fk_note_to_venue_idx` (`venue_id` ASC),
  INDEX `fk_note_to_vendor_idx` (`vendor_id` ASC),
  INDEX `fk_note_to_employee_idx` (`employee_id` ASC),
  CONSTRAINT `fk_note_to_client`
    FOREIGN KEY (`client_id`)
    REFERENCES `client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_note_to_event`
    FOREIGN KEY (`event_id`)
    REFERENCES `event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_note_to_venue`
    FOREIGN KEY (`venue_id`)
    REFERENCES `venue` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_note_to_vendor`
    FOREIGN KEY (`vendor_id`)
    REFERENCES `vendor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_note_to_employee`
    FOREIGN KEY (`employee_id`)
    REFERENCES `emplpyee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `venue_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `venue_event` ;

CREATE TABLE IF NOT EXISTS `venue_event` (
  `venue_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  PRIMARY KEY (`venue_id`, `event_id`),
  INDEX `fk_event_to_venue_idx` (`event_id` ASC),
  CONSTRAINT `fk_venue_to_event`
    FOREIGN KEY (`venue_id`)
    REFERENCES `venue` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_to_venue`
    FOREIGN KEY (`event_id`)
    REFERENCES `event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vendor_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vendor_event` ;

CREATE TABLE IF NOT EXISTS `vendor_event` (
  `vendor_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  PRIMARY KEY (`vendor_id`, `event_id`),
  INDEX `fk_event_to_vendor_idx` (`event_id` ASC),
  CONSTRAINT `fk_vendor_to_event`
    FOREIGN KEY (`vendor_id`)
    REFERENCES `vendor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_to_vendor`
    FOREIGN KEY (`event_id`)
    REFERENCES `event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vendor_venue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vendor_venue` ;

CREATE TABLE IF NOT EXISTS `vendor_venue` (
  `vendor_id` INT NOT NULL,
  `venue_id` INT NOT NULL,
  PRIMARY KEY (`vendor_id`, `venue_id`),
  INDEX `fk_venue_to_vendor_idx` (`venue_id` ASC),
  CONSTRAINT `fk_vendor_to_venue`
    FOREIGN KEY (`vendor_id`)
    REFERENCES `vendor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venue_to_vendor`
    FOREIGN KEY (`venue_id`)
    REFERENCES `venue` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `employee_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `employee_event` ;

CREATE TABLE IF NOT EXISTS `employee_event` (
  `employee_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  PRIMARY KEY (`employee_id`, `event_id`),
  INDEX `fk_event_to_employeeevent_idx` (`event_id` ASC),
  CONSTRAINT `fk_employee_to_employeeevent`
    FOREIGN KEY (`employee_id`)
    REFERENCES `emplpyee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_to_employeeevent`
    FOREIGN KEY (`event_id`)
    REFERENCES `event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS dj@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'dj'@'localhost' IDENTIFIED BY 'music';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `musicdb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `enabled`, `role`) VALUES (1, 'admin', 'admin', 'admin', 'password', 1, 'admin');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `enabled`, `role`) VALUES (2, 'Braiden', 'Miller', 'bmiller', 'password', 1, 'admin');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `enabled`, `role`) VALUES (3, 'Test', 'Test', 'test', 'password', 1, 'standard');

COMMIT;


-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `musicdb`;
INSERT INTO `address` (`id`, `street`, `street_2`, `city`, `state`, `zip`) VALUES (1, 'P.O. Box 19929', NULL, 'Denver', 'CO', '80219');
INSERT INTO `address` (`id`, `street`, `street_2`, `city`, `state`, `zip`) VALUES (2, 'test', NULL, 'Denver', 'CO', '80222');
INSERT INTO `address` (`id`, `street`, `street_2`, `city`, `state`, `zip`) VALUES (3, 'Super Test', NULL, 'Denver', 'CO', '80222');
INSERT INTO `address` (`id`, `street`, `street_2`, `city`, `state`, `zip`) VALUES (4, '701 N High Street', NULL, 'Denver', 'CO', '80218');

COMMIT;


-- -----------------------------------------------------
-- Data for table `emplpyee`
-- -----------------------------------------------------
START TRANSACTION;
USE `musicdb`;
INSERT INTO `emplpyee` (`id`, `title`, `pay_rate`, `user_id`, `email`, `address_id`) VALUES (1, 'Dj', 100000, 2, 'braidenjamesmiller@gmail.com', 1);
INSERT INTO `emplpyee` (`id`, `title`, `pay_rate`, `user_id`, `email`, `address_id`) VALUES (2, 'Dj', 5, 3, 'test@test.test', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `client`
-- -----------------------------------------------------
START TRANSACTION;
USE `musicdb`;
INSERT INTO `client` (`id`, `first_name`, `last_name`, `fiancee_fname`, `fiancee_lname`, `phone`, `email`, `address_id`) VALUES (1, 'Joe', 'Test', 'Bob', 'Tester', '222-222-2222', 'supertest@test.test', 3);
INSERT INTO `client` (`id`, `first_name`, `last_name`, `fiancee_fname`, `fiancee_lname`, `phone`, `email`, `address_id`) VALUES (2, 'Stone', 'ER', 'four', 'twenty', '420-420-0420', 'rockymountain@high.io', 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `package`
-- -----------------------------------------------------
START TRANSACTION;
USE `musicdb`;
INSERT INTO `package` (`id`, `name`, `length`, `price`, `lights`, `extras`) VALUES (1, 'Standard', 7, 1200, 4, NULL);
INSERT INTO `package` (`id`, `name`, `length`, `price`, `lights`, `extras`) VALUES (2, 'Standard Plus Lights', 7, 1500, 12, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `event`
-- -----------------------------------------------------
START TRANSACTION;
USE `musicdb`;
INSERT INTO `event` (`id`, `name`, `category`, `paid_in_full`, `package_id`, `amount_paid`, `start_date`, `end_date`) VALUES (1, 'Joe and Bob\'s Wedding', 'Wedding', 0, 1, 900, '4-15-2019', '4-15-2019');
INSERT INTO `event` (`id`, `name`, `category`, `paid_in_full`, `package_id`, `amount_paid`, `start_date`, `end_date`) VALUES (2, 'Ston-er Gathering', 'Other', 1, 2, 1500, '4-20-2019', '4-20-2019');

COMMIT;


-- -----------------------------------------------------
-- Data for table `client_event`
-- -----------------------------------------------------
START TRANSACTION;
USE `musicdb`;
INSERT INTO `client_event` (`client_id`, `event_id`) VALUES (1, 1);
INSERT INTO `client_event` (`client_id`, `event_id`) VALUES (2, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `vendor`
-- -----------------------------------------------------
START TRANSACTION;
USE `musicdb`;
INSERT INTO `vendor` (`id`, `first_name`, `last_name`, `category`, `phone`, `email`) VALUES (1, 'Jerry', 'Wedding', 'Venue', '222-222-2222', 'jerry@wedding.com');
INSERT INTO `vendor` (`id`, `first_name`, `last_name`, `category`, `phone`, `email`) VALUES (2, 'Steve', 'Stoner', 'Venue', '420-420-0420', 'steve@stoner.com');
INSERT INTO `vendor` (`id`, `first_name`, `last_name`, `category`, `phone`, `email`) VALUES (3, 'Nancy', 'Photo', 'Photography', '111-111-1111', 'nancy@photo.com');
INSERT INTO `vendor` (`id`, `first_name`, `last_name`, `category`, `phone`, `email`) VALUES (4, 'Gary', 'Food', 'Catering', '444-444-4444', 'gary@food.com');
INSERT INTO `vendor` (`id`, `first_name`, `last_name`, `category`, `phone`, `email`) VALUES (5, 'Beth', 'Alcohol', 'Alcohol', '555-555-5555', 'beth@alcohol.com');
INSERT INTO `vendor` (`id`, `first_name`, `last_name`, `category`, `phone`, `email`) VALUES (6, 'Susan', 'Event', 'Event Cordinator', '777-777-7777', 'susan@event.com');
INSERT INTO `vendor` (`id`, `first_name`, `last_name`, `category`, `phone`, `email`) VALUES (7, 'Greg', 'Contact', 'Second Contact', '999-999-9999', 'greg@contact.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `venue`
-- -----------------------------------------------------
START TRANSACTION;
USE `musicdb`;
INSERT INTO `venue` (`id`, `name`, `address_id`, `owner_id`) VALUES (1, 'Wedding Place', 3, 1);
INSERT INTO `venue` (`id`, `name`, `address_id`, `owner_id`) VALUES (2, 'Event Place', 4, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `note`
-- -----------------------------------------------------
START TRANSACTION;
USE `musicdb`;
INSERT INTO `note` (`id`, `title`, `note`, `created`, `updated`, `client_id`, `event_id`, `venue_id`, `vendor_id`, `employee_id`, `active`) VALUES (1, 'test', 'testing client', DEFAULT, DEFAULT, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `note` (`id`, `title`, `note`, `created`, `updated`, `client_id`, `event_id`, `venue_id`, `vendor_id`, `employee_id`, `active`) VALUES (2, 'Test', 'testing event', DEFAULT, DEFAULT, NULL, 1, NULL, NULL, NULL, 1);
INSERT INTO `note` (`id`, `title`, `note`, `created`, `updated`, `client_id`, `event_id`, `venue_id`, `vendor_id`, `employee_id`, `active`) VALUES (3, 'Test', 'testing venue', DEFAULT, DEFAULT, NULL, NULL, 1, NULL, NULL, 1);
INSERT INTO `note` (`id`, `title`, `note`, `created`, `updated`, `client_id`, `event_id`, `venue_id`, `vendor_id`, `employee_id`, `active`) VALUES (4, 'Test', 'testing vendor', DEFAULT, DEFAULT, NULL, NULL, NULL, 1, NULL, 1);
INSERT INTO `note` (`id`, `title`, `note`, `created`, `updated`, `client_id`, `event_id`, `venue_id`, `vendor_id`, `employee_id`, `active`) VALUES (5, 'Test', 'testing employee', DEFAULT, DEFAULT, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `note` (`id`, `title`, `note`, `created`, `updated`, `client_id`, `event_id`, `venue_id`, `vendor_id`, `employee_id`, `active`) VALUES (6, 'Test2', 'testing client and venue', DEFAULT, DEFAULT, 2, NULL, 2, NULL, NULL, 1);
INSERT INTO `note` (`id`, `title`, `note`, `created`, `updated`, `client_id`, `event_id`, `venue_id`, `vendor_id`, `employee_id`, `active`) VALUES (7, 'Test2', 'testing employee and event', DEFAULT, DEFAULT, NULL, 2, NULL, NULL, 2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `venue_event`
-- -----------------------------------------------------
START TRANSACTION;
USE `musicdb`;
INSERT INTO `venue_event` (`venue_id`, `event_id`) VALUES (1, 1);
INSERT INTO `venue_event` (`venue_id`, `event_id`) VALUES (2, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `vendor_event`
-- -----------------------------------------------------
START TRANSACTION;
USE `musicdb`;
INSERT INTO `vendor_event` (`vendor_id`, `event_id`) VALUES (1, 1);
INSERT INTO `vendor_event` (`vendor_id`, `event_id`) VALUES (2, 2);
INSERT INTO `vendor_event` (`vendor_id`, `event_id`) VALUES (3, 1);
INSERT INTO `vendor_event` (`vendor_id`, `event_id`) VALUES (4, 1);
INSERT INTO `vendor_event` (`vendor_id`, `event_id`) VALUES (5, 1);
INSERT INTO `vendor_event` (`vendor_id`, `event_id`) VALUES (3, 2);
INSERT INTO `vendor_event` (`vendor_id`, `event_id`) VALUES (6, 2);
INSERT INTO `vendor_event` (`vendor_id`, `event_id`) VALUES (7, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `vendor_venue`
-- -----------------------------------------------------
START TRANSACTION;
USE `musicdb`;
INSERT INTO `vendor_venue` (`vendor_id`, `venue_id`) VALUES (3, 1);
INSERT INTO `vendor_venue` (`vendor_id`, `venue_id`) VALUES (4, 1);
INSERT INTO `vendor_venue` (`vendor_id`, `venue_id`) VALUES (5, 1);
INSERT INTO `vendor_venue` (`vendor_id`, `venue_id`) VALUES (3, 2);
INSERT INTO `vendor_venue` (`vendor_id`, `venue_id`) VALUES (6, 2);
INSERT INTO `vendor_venue` (`vendor_id`, `venue_id`) VALUES (7, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `employee_event`
-- -----------------------------------------------------
START TRANSACTION;
USE `musicdb`;
INSERT INTO `employee_event` (`employee_id`, `event_id`) VALUES (1, 1);
INSERT INTO `employee_event` (`employee_id`, `event_id`) VALUES (2, 1);
INSERT INTO `employee_event` (`employee_id`, `event_id`) VALUES (1, 2);
INSERT INTO `employee_event` (`employee_id`, `event_id`) VALUES (2, 2);

COMMIT;

