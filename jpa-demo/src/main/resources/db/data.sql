INSERT INTO `registry_define` (`ID`, `NAME`, `HOST`, `PORT`, `USER`, `PASSWORD`, `HARBOR_PRODUCT_NAME`, `HARBOR_PRODUCT_ID`, `TYPE`, `SCHEME`, `HEALTH`, `CREATE_TIME`, `UPDATE_TIME`)
VALUES
	(5, 'vos', '192.168.109.2', 6999, NULL, NULL, NULL, NULL, 'V2', 'http', NULL, '2020-12-16 07:44:12', '2020-12-28 07:42:38'),
	(6, 'vos', '192.168.109.2', 6999, NULL, NULL, NULL, NULL, 'V2', 'http', NULL, '2020-12-21 12:17:11', '2020-12-28 07:42:38');

INSERT INTO `image_cache` (`id`, `image_id`, `name`, `tag`, `size`, `registry_address`, `registry_name`, `config`, `refresh_time`)
VALUES
	(34, '383867b75fd22e6c8ca3ef2a1042339ec2d5b655365107547eac94e918309b91', 'mysql', '5.7.27', 124130524, 'http://192.168.109.2:6999', 'vos', NULL, '2020-12-08 16:09:50'),
	(35, 'f32a97de94e13d29835a19851acd6cbc7979d1d50f703725541e44bb89a1ce91', 'registry', 'latest', 9658846, 'http://192.168.109.2:5999', 'test', NULL, '2020-12-08 15:59:42'),
	(36, 'f32a97de94e13d29835a19851acd6cbc7979d1d50f703725541e44bb89a1ce91', 'registry', 'latest', 9658846, 'http://192.168.109.2:6999', 'vos', NULL, '2020-12-08 16:09:50'),
	(37, 'd23bdf5b1b1b1afce5f1d0fd33e7ed8afbc084b594b9ccf742a5b27080d8a4a8', 'java', '8', 247331743, 'http://192.168.109.2:6999', 'vos', NULL, '2020-12-08 16:09:50'),
	(38, '1babb1dde7e1fc7520ce56ce6d39843a074151bb192522b1988c65a067b15e96', 'redis_20201208v10', 'v10', 35180038, 'http://192.168.109.2:6999', 'vos', NULL, '2020-12-08 16:09:50'),
	(39, '1babb1dde7e1fc7520ce56ce6d39843a074151bb192522b1988c65a067b15e96', 'redis_20201208v11', 'v11', 35180038, 'http://192.168.109.2:6999', 'vos', NULL, '2020-12-08 16:09:50'),
	(40, '1babb1dde7e1fc7520ce56ce6d39843a074151bb192522b1988c65a067b15e96', 'redis_20201208v12', 'v12', 35180038, 'http://192.168.109.2:6999', 'vos', NULL, '2020-12-08 16:09:50'),
	(41, '1babb1dde7e1fc7520ce56ce6d39843a074151bb192522b1988c65a067b15e96', 'redis_20201208v3', 'v3', 35180038, 'http://192.168.109.2:6999', 'vos', NULL, '2020-12-08 16:09:50'),
	(42, '1babb1dde7e1fc7520ce56ce6d39843a074151bb192522b1988c65a067b15e96', 'redis_20201208v4', 'v4', 35180038, 'http://192.168.109.2:6999', 'vos', NULL, '2020-12-08 16:09:50');
COMMIT;