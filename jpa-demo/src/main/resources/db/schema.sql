DROP TABLE IF EXISTS `image_cache`;
CREATE TABLE `image_cache` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image_id` varchar(255) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `tag` varchar(45) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `registry_address` varchar(45) DEFAULT NULL,
  `registry_name` varchar(45) DEFAULT NULL,
  `config` varchar(45) DEFAULT NULL,
  `refresh_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `registry_define`;
CREATE TABLE `registry_define` (
  `ID` int(32) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `HOST` varchar(255) DEFAULT NULL,
  `PORT` int(32) DEFAULT NULL,
  `USER` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `HARBOR_PRODUCT_NAME` varchar(255) DEFAULT NULL,
  `HARBOR_PRODUCT_ID` int(11) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `SCHEME` varchar(255) DEFAULT NULL,
  `HEALTH` int(255) DEFAULT '0',
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;