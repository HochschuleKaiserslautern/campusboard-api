DROP TABLE IF EXISTS `campusboard_personal`.`refresh_token`;
DROP TABLE IF EXISTS `campusboard_personal`.`client_secrect`;
DROP TABLE IF EXISTS `campusboard_personal`.`api_user`;
DROP TABLE IF EXISTS `campusboard_personal`.`known_client`;

CREATE TABLE `campusboard_personal`.`api_user` (
  `USERNAME` varchar(255) NOT NULL,
  `ANREDE` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `FAXNR` varchar(255) DEFAULT NULL,
  `FOTOURL` varchar(255) DEFAULT NULL,
  `HOMEPAGE` varchar(255) DEFAULT NULL,
  `MITARBEITER` tinyint(1) DEFAULT '0',
  `NACHNAME` varchar(255) DEFAULT NULL,
  `RAUM` varchar(255) DEFAULT NULL,
  `STANDORT` varchar(255) DEFAULT NULL,
  `STUDENT` tinyint(1) DEFAULT '0',
  `TELNR` varchar(255) DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `VORNAME` varchar(255) DEFAULT NULL,
  `USER_ID` varchar(255) DEFAULT NULL,
  `HASHED_PASSWORD` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `campusboard_personal`.`known_client` (
  `client_id` varchar(255) NOT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `campusboard_personal`.`client_secrect` (
  `client_id` varchar(255) NOT NULL,
  `client_secret` varchar(255) NOT NULL,
  `active` tinyint(1) DEFAULT '0',
  `issued_date_time` longblob,
  PRIMARY KEY (`client_id`,`client_secret`),
  CONSTRAINT `FK_client_secrect_client_id` FOREIGN KEY (`client_id`) REFERENCES `known_client` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `campusboard_personal`.`refresh_token` (
  `token_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` varchar(255) DEFAULT NULL,
  `issud_date_time` longblob,
  `revoked` tinyint(1) DEFAULT '0',
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`token_id`),
  KEY `FK_refresh_token_client_id` (`client_id`),
  CONSTRAINT `FK_refresh_token_client_id` FOREIGN KEY (`client_id`) REFERENCES `known_client` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

