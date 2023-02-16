CREATE TABLE `gaudiy`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NULL,
  `datetime` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `INDEX` (`datetime` ASC));
CREATE TABLE `gaudiy`.`point` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL,
  `point` INT NULL,
  `datetime` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `INDEX` (`datetime` ASC));
