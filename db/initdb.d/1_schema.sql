CREATE TABLE `gaudiy`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `create_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));
CREATE TABLE `gaudiy`.`point` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `point` INT NOT NULL,
  `invalid_time` DATETIME NULL,
  `create_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `INDEX` (`user_id` ASC, `create_time` ASC));
