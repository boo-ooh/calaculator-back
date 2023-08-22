CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(200) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ;

CREATE TABLE `calculator`.`operation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL,
  `cost` DOUBLE NULL,
  `display_name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `calculator`.`record` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `operation_id` INT NULL,
  `user_id` INT NULL,
  `amount` DOUBLE NULL,
  `user_balance` DOUBLE NULL,
  `operation_response` VARCHAR(45) NULL,
  `date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `id_idx` (`operation_id` ASC) VISIBLE,
  INDEX `id_idx1` (`user_id` ASC) VISIBLE,
  CONSTRAINT `operation_FK`
    FOREIGN KEY (`operation_id`)
    REFERENCES `calculator`.`operation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_FK`
    FOREIGN KEY (`user_id`)
    REFERENCES `calculator`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


INSERT INTO `calculator`.`operation` (`type`, `cost`, `display_name`) VALUES ('addition', '5', 'Add');
INSERT INTO `calculator`.`operation` (`type`, `cost`, `display_name`) VALUES ('subtraction', '5', 'Subtract');
INSERT INTO `calculator`.`operation` (`type`, `cost`, `display_name`) VALUES ('multiplication', '10', 'Multiply');
INSERT INTO `calculator`.`operation` (`type`, `cost`, `display_name`) VALUES ('division', '10','Divide');
INSERT INTO `calculator`.`operation` (`type`, `cost`, `display_name`) VALUES ('square_root', '15','Square Root');
INSERT INTO `calculator`.`operation` (`type`, `cost`, `display_name`) VALUES ('random_string', '20', 'Random String');

INSERT INTO `calculator`.`user` (`username`, `password`, `status`) VALUES ('Bruna', '$2a$10$AQmDF3inI1RZ0M/lfaA46eL0X8OdkDm1hkJT1bVeigvcLKQiUQ4EG', 'ACTIVE');
INSERT INTO `calculator`.`record` (`user_id`, `amount`, `user_balance`, `date`) VALUES ((select id from user where username='Bruna'), '1000', '1000', now());
