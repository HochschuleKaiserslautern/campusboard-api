DROP TABLE IF EXISTS `timetable_entry`;
DROP TABLE IF EXISTS `docent`;
DROP TABLE IF EXISTS `room`;

CREATE TABLE `docent` (
  `DOCENT_ID` bigint(20) NOT NULL,
  `NAME_WITH_TITLE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DOCENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `room` (
  `ROOM_ID` bigint(20) NOT NULL,
  `ROOM_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ROOM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `timetable_entry` (
  `ID` bigint(20) NOT NULL,
  `DAY_OF_THE_WEEK` int(11) DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  `END_TIME` time DEFAULT NULL,
  `GROUP_NAME` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `NOTE` varchar(255) DEFAULT NULL,
  `ROTATION` int(11) DEFAULT NULL,
  `SHORT_NAME` varchar(255) DEFAULT NULL,
  `START_DATE` date DEFAULT NULL,
  `START_TIME` time DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `DOCENT_DOCENT_ID` bigint(20) DEFAULT NULL,
  `ROOM_ROOM_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_timetable_entry_DOCENT_DOCENT_ID` (`DOCENT_DOCENT_ID`),
  KEY `FK_timetable_entry_ROOM_ROOM_ID` (`ROOM_ROOM_ID`),
  CONSTRAINT `FK_timetable_entry_DOCENT_DOCENT_ID` FOREIGN KEY (`DOCENT_DOCENT_ID`) REFERENCES `docent` (`DOCENT_ID`),
  CONSTRAINT `FK_timetable_entry_ROOM_ROOM_ID` FOREIGN KEY (`ROOM_ROOM_ID`) REFERENCES `room` (`ROOM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;





INSERT INTO `docent` (`DOCENT_ID`, `NAME_WITH_TITLE`) 
VALUES ('1', 'Prof. Dr. Max Mustermann'),
('2', 'Dr. der Mathematik');


INSERT INTO `room` (`ROOM_ID`, `ROOM_NAME`) 
VALUES ('1', 'Raum A'),
('2', 'Raum B'),
('3', 'Raum C');

INSERT INTO `timetable_entry` (`ID`,`DAY_OF_THE_WEEK`, `END_DATE`, `END_TIME`, `GROUP_NAME`, `NAME`, `NOTE`, `ROTATION`, `SHORT_NAME`, `START_DATE`, `START_TIME`, `TYPE`, `DOCENT_DOCENT_ID`, `ROOM_ROOM_ID`) 
VALUES (1,'1', '2017-08-31', '13:15:00', '', 'Veranstalung A', '', '1', 'A', '2017-03-01-01', '11:30:00', 'Vorlesung', '1', '1'),
(2,'2', '2017-08-31', '13:15:00', '', 'Veranstalung A', '', '1', 'A', '2017-03-01-01', '11:30:00', 'Vorlesung', '1', '1'),
(3,'3', '2017-08-31', '13:15:00', '', 'Veranstalung A', '', '1', 'A', '2017-03-01-01', '11:30:00', 'Vorlesung', '1', '1'),
(4,'4', '2017-08-31', '13:15:00', '', 'Veranstalung A', '', '1', 'A', '2017-03-01-01', '11:30:00', 'Vorlesung', '1', '1'),
(5,'5', '2017-08-31', '13:15:00', '', 'Veranstalung A', '', '1', 'A', '2017-03-01-01', '11:30:00', 'Vorlesung', '1', '1');

