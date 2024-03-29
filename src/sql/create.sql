CREATE DATABASE IF NOT EXISTS pwtest;
GRANT ALL PRIVILEGES ON pwtest.* TO 'pwuser'@'localhost' IDENTIFIED BY 'pwpassword';
USE pwtest;

CREATE TABLE book (
    isbn VARCHAR(20) PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    description TEXT,
    url VARCHAR(255),
    imgurl VARCHAR(255),
    price DECIMAL(6, 5),
    pages INT,
    edition INT,
    FULLTEXT KEY `search` (`name`,`description`)) ENGINE = InnoDB;

CREATE TABLE author (
    aid INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    surname VARCHAR(100)) ENGINE = InnoDB;
CREATE TABLE aubo (
    aid INT NOT NULL,
    isbn VARCHAR(20),
    PRIMARY KEY (aid, isbn),
    FOREIGN KEY (aid) REFERENCES author(aid) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (isbn) REFERENCES book(isbn) ON DELETE RESTRICT ON UPDATE CASCADE ) ENGINE = InnoDB;

CREATE TABLE category (
    cid INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(100)) ENGINE = InnoDB;
CREATE TABLE cabo (
    cid INT NOT NULL,
    isbn VARCHAR(20),
    PRIMARY KEY (cid, isbn),
    FOREIGN KEY (cid) REFERENCES category(cid) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (isbn) REFERENCES book(isbn) ON DELETE RESTRICT ON UPDATE CASCADE ) ENGINE = InnoDB;

CREATE TABLE editor (
    eid INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(100)) ENGINE = InnoDB;
CREATE TABLE edbo (
    eid INT NOT NULL,
    isbn VARCHAR(20),
    PRIMARY KEY (eid, isbn),
    FOREIGN KEY (eid) REFERENCES editor(eid) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (isbn) REFERENCES book(isbn) ON DELETE RESTRICT ON UPDATE CASCADE ) ENGINE = InnoDB;

CREATE TABLE user (
    email VARCHAR(255) PRIMARY KEY NOT NULL,
    name VARCHAR(100),
    surname VARCHAR(100),
    password VARCHAR(100)) ENGINE = InnoDB;

CREATE TABLE admin (
    email VARCHAR(255) PRIMARY KEY NOT NULL,
    password VARCHAR(100),
    display_name VARCHAR(100)) ENGINE = InnoDB;