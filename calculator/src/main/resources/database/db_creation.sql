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
  
CREATE TABLE `record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `operation_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `user_balance` double DEFAULT NULL,
  `operation_response` varchar(45) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id_idx` (`operation_id`),
  KEY `id_idx1` (`user_id`),
  CONSTRAINT `operation_FK` FOREIGN KEY (`operation_id`) REFERENCES `operation` (`id`),
  CONSTRAINT `user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
)


INSERT INTO `calculator`.`operation` (`type`, `cost`, `display_name`) VALUES ('addition', '5', 'Add');
INSERT INTO `calculator`.`operation` (`type`, `cost`, `display_name`) VALUES ('subtraction', '5', 'Subtract');
INSERT INTO `calculator`.`operation` (`type`, `cost`, `display_name`) VALUES ('multiplication', '10', 'Multiply');
INSERT INTO `calculator`.`operation` (`type`, `cost`, `display_name`) VALUES ('division', '10','Divide');
INSERT INTO `calculator`.`operation` (`type`, `cost`, `display_name`) VALUES ('square_root', '15','Square Root');
INSERT INTO `calculator`.`operation` (`type`, `cost`, `display_name`) VALUES ('random_string', '20', 'Random String');

INSERT INTO `calculator`.`user` (`username`, `password`, `status`) VALUES ('Bruna', '$2a$10$AQmDF3inI1RZ0M/lfaA46eL0X8OdkDm1hkJT1bVeigvcLKQiUQ4EG', 'ACTIVE');
INSERT INTO `calculator`.`record` (`user_id`, `amount`, `user_balance`, `date`) VALUES ((select id from user where username='Bruna'), '1000', '1000', now());
