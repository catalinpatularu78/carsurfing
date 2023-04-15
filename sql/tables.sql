USE carsurfing;

CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `phone` bigint DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,NULL,'user@gmail.com','$2a$10$XQ0Yrg.Mh9dD.aiLayRjCexQiMQrsxCcCQYtiK9AJRp7AIWRqx8EG',NULL,2.3333333333333335,'user');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_USER'),(2,'ROLE_MODERATOR'),(3,'ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

CREATE TABLE ride
(
  ID INT  NOT NULL AUTO_INCREMENT,
  DRIVER_ID bigint  NOT NULL, 
  FROM_LOCATION VARCHAR(20)  NOT NULL,
  TO_LOCATION VARCHAR(20)  NOT NULL,
  DATE_OF_DEPARTURE DATE  NOT NULL,
  ESTIMATED_DEPARTURE_TIME TIME,
  STOP_1 VARCHAR(20),
  STOP_2 VARCHAR(20),
  STOP_3 VARCHAR(20),
  SPACES_LEFT INT NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (DRIVER_ID) REFERENCES users(ID)
);

CREATE TABLE bookings
(
  ID INT NOT NULL AUTO_INCREMENT,
  RIDE_ID INT  NOT NULL,
  PASSENGER_ID bigint  NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (PASSENGER_ID) REFERENCES users(ID),
  FOREIGN KEY (RIDE_ID) REFERENCES ride(ID)
);

CREATE TABLE `review` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `rating` int NOT NULL,
  `reviewed_id` int DEFAULT NULL,
  `reviewer_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,'Amazing ride',5,1,'Robert'),(2,'Amazing ride again',1,1,'RobertDown'),(3,'Amazing ride again and again',1,1,'RobertDownJunior');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;


