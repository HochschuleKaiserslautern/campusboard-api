DROP TABLE IF EXISTS `dish`;
DROP TABLE IF EXISTS `canteen`;

CREATE TABLE `canteen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

CREATE TABLE `dish` (
  `id` int(11) NOT NULL,
  `date` varchar(45) NOT NULL,
  `day` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `rating` tinyint(1) NOT NULL DEFAULT '0',
  `canteen_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dish_canteen_id_idx` (`canteen_id`),
  CONSTRAINT `FK_dish_canteen_id` FOREIGN KEY (`canteen_id`) REFERENCES `canteen` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `campusboard_personal`.`canteen` (`id`, `name`) VALUES ('1', 'Canteen_One');
INSERT INTO `campusboard_personal`.`canteen` (`id`, `name`) VALUES ('2', 'Canteen_Two');

INSERT INTO `campusboard_personal`.`dish` (`id`, `date`, `day`, `description`, `rating`, `canteen_id`) VALUES ('1', '2020-01-01', '5', 'Noodles', '5', '1');
INSERT INTO `campusboard_personal`.`dish` (`id`, `date`, `day`, `description`, `rating`, `canteen_id`) VALUES ('2', '2020-02-01', '4', 'Rice', '4', '1');
INSERT INTO `campusboard_personal`.`dish` (`id`, `date`, `day`, `description`, `rating`, `canteen_id`) VALUES ('3', '2020-03-01', '3', 'Potato', '3', '1');
INSERT INTO `campusboard_personal`.`dish` (`id`, `date`, `day`, `description`, `rating`, `canteen_id`) VALUES ('4', '2020-01-01', '5', 'Noodles', '5', '2');
INSERT INTO `campusboard_personal`.`dish` (`id`, `date`, `day`, `description`, `rating`, `canteen_id`) VALUES ('5', '2020-02-01', '4', 'Rice', '4', '2');
INSERT INTO `campusboard_personal`.`dish` (`id`, `date`, `day`, `description`, `rating`, `canteen_id`) VALUES ('6', '2020-03-01', '3', 'Potato', '3', '2');
