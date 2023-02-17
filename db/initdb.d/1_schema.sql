CREATE TABLE `gaudiy`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `coin` INT NOT NULL DEFAULT 0,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));
CREATE TABLE `gaudiy`.`coin_history` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `coin` INT NOT NULL,
  `remark` VARCHAR(255) NOT NULL,
  `create_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `INDEX` (`user_id` ASC, `create_time` ASC));
