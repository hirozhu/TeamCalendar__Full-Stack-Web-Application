DROP DATABASE if exists calendarUser;
CREATE DATABASE calendarUser;
USE calendarUser;

CREATE TABLE user (
email varchar(50) primary key not null,
fname varchar(50) not null,
lname varchar(50) not null,
image varchar(255) not null
);

CREATE TABLE event (
eventID varchar(100) primary key not null,
email varchar(50) not null,
title varchar(50) not null,
startDate varchar(50) not null,
endDate varchar(50) not null,
startTime varchar(50) not null,
endTime varchar(50) not null,
FOREIGN KEY fk1(email) REFERENCES user(email)
);

CREATE TABLE userFollow (
userFollowID int(11) primary key not null auto_increment,
email varchar(50) not null,
followingUserEmail varchar(50) not null
);
