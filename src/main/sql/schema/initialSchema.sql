
-- NOTE: this file's contents are not really needed if you keep the JPA setting in the application.properties ddl-auto as 'update'

-- drop any tables already created
drop table if exists registry_server_node;


-- create the server node table
CREATE TABLE `registry_server_node` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `web_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7p583m1ibiycob2qilra9nrd0` (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
