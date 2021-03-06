CREATE TABLE CALENDAR(
CALENDAR_ID INTEGER NOT NULL AUTO_INCREMENT ,
WORKING_DATE DATE,
DAY VARCHAR( 50 ) ,
WEEK INTEGER,
DESCRIPTION VARCHAR( 100 ) ,
USER_ID INTEGER,
PRIMARY KEY ( CALENDAR_ID )
);

CREATE TABLE HOTEL(
HOTEL_ID INTEGER NOT NULL AUTO_INCREMENT,
HOTEL_NAME VARCHAR(128),
DESCRIPTION VARCHAR(128),
PRIMARY KEY ( HOTEL_ID )
);

CREATE TABLE PLAN(
PLAN_ID INTEGER NOT NULL AUTO_INCREMENT,
USER_ID INTEGER,
HOTEL_ID INTEGER,
PRIMARY KEY ( PLAN_ID )
);

CREATE TABLE POSITIONS(
POSITION_ID INTEGER NOT NULL AUTO_INCREMENT,
DESCRIPTION VARCHAR(200),
PRIMARY KEY ( POSITION_ID )
);

CREATE TABLE USER(
USER_ID INTEGER NOT NULL AUTO_INCREMENT,
FIRSTNAME VARCHAR(100),
LASTNAME VARCHAR(100),
AGE INTEGER,
SEX VARCHAR(20),
USERNAME VARCHAR(100),
PASSWORD VARCHAR(200),
SALT varchar(128),
EMAIL VARCHAR(128),
DESCRIPTION VARCHAR(128),
PRIMARY KEY ( USER_ID ));

CREATE TABLE USERCALENDAR (
  USERCALENDAR_ID INTEGER NOT NULL AUTO_INCREMENT,
  CALENDAR_ID INTEGER, 
  USER_ID INTEGER,
  WORK_START VARCHAR(100),
  WORK_END VARCHAR(100),
  DESCRIPTION VARCHAR(200),
  PRIMARY KEY ( USERCALENDAR_ID )
);

CREATE TABLE USERROLE (
  USERROLE_ID INTEGER NOT NULL AUTO_INCREMENT,
  USER_ID INTEGER,
  ROLENAME VARCHAR(45) DEFAULT NULL,
  EMAIL VARCHAR(128) DEFAULT NULL,
  PRIMARY KEY ( USERROLE_ID )
);

CREATE TABLE EMPLOYEE(
EMPLOYEE_ID INTEGER NOT NULL AUTO_INCREMENT,
AGE INTEGER,
DESCRIPTION VARCHAR(255),
EMAIL VARCHAR(255),
FIRSTNAME VARCHAR(255),
LASTNAME VARCHAR(255),
SEX VARCHAR(255),
FULLNAME VARCHAR(255),
POSITION_ID INTEGER,
HOTEL_ID INTEGER,
PRIMARY KEY(EMPLOYEE_ID));