USE carsurfing;

CREATE TABLE user
(
  ID INT NOT NULL AUTO_INCREMENT,
  FIRST_NAME VARCHAR(50) NOT NULL,
  MIDDLE_NAME VARCHAR(50),
  LAST_NAME VARCHAR(50) NOT NULL,
  DESCRIPTION VARCHAR(256) NOT NULL,
  EMAIL VARCHAR(50) NOT NULL,
  PHONE VARCHAR(10) NOT NULL,
  VERIFIED VARCHAR(10) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE ride
(
  ID INT  NOT NULL AUTO_INCREMENT,
  DRIVER_ID INT  NOT NULL, 
  FROM_LOCATION VARCHAR(20)  NOT NULL,
  TO_LOCATION VARCHAR(20)  NOT NULL,
  DATE_OF_DEPARTURE DATE  NOT NULL,
  ESTIMATED_DEPARTURE_TIME TIME,
  STOP_1 VARCHAR(20),
  STOP_2 VARCHAR(20),
  STOP_3 VARCHAR(20),
  SPACES_LEFT INT NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (DRIVER_ID) REFERENCES user(ID) ON DELETE CASCADE
);

CREATE TABLE bookings
(
  RIDE_ID INT  NOT NULL,
  PASSENGER_ID INT  NOT NULL,
  PRIMARY KEY (PASSENGER_ID, RIDE_ID),
  FOREIGN KEY (PASSENGER_ID) REFERENCES user(ID),
  FOREIGN KEY (RIDE_ID) REFERENCES ride(ID) ON DELETE CASCADE
);

CREATE TABLE reviews
(
  ID INT NOT NULL AUTO_INCREMENT,
  REVIEWER_ID INT  NOT NULL, 
  REVIEWED_ID INT  NOT NULL, 
  RIDE_ID INT  NOT NULL,
  COMMENT VARCHAR(256)  NOT NULL,
  RATING INT NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (REVIEWER_ID) REFERENCES user(ID),
  FOREIGN KEY (REVIEWED_ID) REFERENCES user(ID) ON DELETE CASCADE,
  FOREIGN KEY (RIDE_ID) REFERENCES ride(ID) 
);


insert into user values(0,'Sorcha','Jenny','Murtagh','','jennymurtagh@yahoo.com','0874123456','Yes');
insert into user values(0,'Robert','','Smith','funny person','robertS45@yahoo.com','089977546','Yes');
insert into user values(0,'John','Sean','Kennedy','pet owner','seankennedy777@gmail.com','087689800','Yes');
insert into user values(0,'Mary','Diana','Fernandez','','fernandezm@gmail.com','0874343563','Yes');
insert into user values('Mary','Diana','Fernandez','','fernandezm@gmail.com','0874343563','Yes');

insert into ride values(0, 1,'Galway','Dublin City Center',STR_TO_DATE('15-05-2022', '%d-%m-%Y'),TIME_FORMAT('07:15:00','%T'),'Athlone','Kinnegad', 'Maynooth','3');
insert into ride values(0, 2,'Cork','Dublin',STR_TO_DATE('22-10-2022', '%d-%m-%Y'),TIME_FORMAT('08:10:00','%T'),'','Gorey','','2');

insert into bookings values(1,4);
insert into bookings values(2,3);

insert into reviews values(0,4,1,1,'The journey was very pleasant with Mary.We laughed all the time, great music.100% recommend','10');
insert into reviews values(0,3,2,2,'Robert is a nice guy, good driver and right on time','10');