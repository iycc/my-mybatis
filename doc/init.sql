CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nums` int(11) NOT NULL,
  `name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_numbs` (`nums`)
) ENGINE=InnoDB AUTO_INCREMENT=5250 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `gp`.`test` (`id`, `nums`, `name`) VALUES ('1', '1', '2');
INSERT INTO `gp`.`test` (`id`, `nums`, `name`) VALUES ('2', '12', 'name-test');
