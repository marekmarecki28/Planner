CREATE TABLE USER(
USER_ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL,
FIRSTNAME VARCHAR(100),
LASTNAME VARCHAR(100),
AGE INTEGER,
SEX VARCHAR(20),
USERNAME VARCHAR(100),
PASSWORD VARCHAR(200),
SALT varchar(128),
EMAIL VARCHAR(128),
DESCRIPTION VARCHAR(128));
