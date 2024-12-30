drop database if exists chess;
create database chess;
use chess;

DROP TABLE IF EXISTS `user`;
CREATE TABLE user (
	`username` varchar(60) NOT NULL,
    `password` varchar(60) NOT NULL,
    PRIMARY KEY (`username`)
);

INSERT INTO `user` (username,password) values ("admin","admin");
INSERT INTO `user` (username,password) values ("fischer","fischer");
INSERT INTO `user` (username,password) values ("capablanca","capablanca");
INSERT INTO `user` (username,password) values ("kasparov","kasparov");
INSERT INTO `user` (username,password) values ("carlsen","carlsen");
INSERT INTO `user` (username,password) values ("revuelta","revuelta");


DROP TABLE IF EXISTS `secure_user`;
CREATE TABLE secure_user (
    `username` varchar(60) NOT NULL,
    `password` varchar(60) NOT NULL,
    PRIMARY KEY (`username`)
);
INSERT INTO secure_user (username, password) VALUES ('sebastianrevuelta@gmail.com', SHA1('Catamarca1039'));
INSERT INTO secure_user (username, password) VALUES ('a', SHA1('a'));

-- create read user
-- CREATE USER 'chess'@'%' IDENTIFIED BY 'checkMate$_@76';
-- GRANT SELECT ON chess.* TO chess@'%';
