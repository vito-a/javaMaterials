-- -------------------------------------------------------
-- ------------ Database periodicalsdb -------------------
-- -------------------------------------------------------

-- DROP DATABASE `periodicalsdb`;
CREATE DATABASE IF NOT EXISTS `periodicalsdb` DEFAULT CHARACTER SET utf8;
USE `periodicalsdb` ;

-- DROP TABLE IF EXISTS subscriptions;
-- DROP TABLE IF EXISTS periodicals;
-- DROP TABLE IF EXISTS categories;
-- DROP TABLE IF EXISTS users;
-- DROP TABLE IF EXISTS roles;

-- -------------------------------------------------------
-- Table `periodicalsdb`.`roles`
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS `periodicalsdb`.`roles` (
    `role_id`   INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`role_id`),
    UNIQUE INDEX `u_roles_name` (`name`)
);

-- -------------------------------------------------------
-- Table `periodicalsdb`.`users`
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS `periodicalsdb`.`users` (
    `user_id`            INT NOT NULL AUTO_INCREMENT,
    `username`           VARCHAR(64)  NOT NULL,
    `password`           VARCHAR(64)  NOT NULL,
    `firstname`          VARCHAR(255) NOT NULL DEFAULT '',
    `lastname`           VARCHAR(255) NOT NULL DEFAULT '',
    `fullname`           VARCHAR(255) NULL DEFAULT '',
    `email`              VARCHAR(255) NOT NULL,
    `enabled`            TINYINT NULL DEFAULT 1,
    `account_non_locked` TINYINT NULL DEFAULT 1,
    `failed_attempt`     TINYINT NULL DEFAULT 0,
    `lock_time`          DATETIME NULL,
    `balance`            DOUBLE DEFAULT NULL,
    `created`            TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `updated`            TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `u_users_username` (`username`)
);

-- -------------------------------------------------------
-- Table `periodicalsdb`.`users_roles`
-- -------------------------------------------------------
CREATE TABLE `users_roles` (
    `user_id` int(11) NOT NULL,
    `role_id` int(11) NOT NULL,
    INDEX `i_user_id` (`user_id`)
);

-- -------------------------------------------------------
-- Table `periodicalsdb`.`categories`
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS `periodicalsdb`.`categories` (
    `cat_id` INT NOT NULL AUTO_INCREMENT,
    `name`   VARCHAR(64) NOT NULL,
    PRIMARY KEY (`cat_id`),
    UNIQUE INDEX `u_categories_name` (`name`)
);

-- -------------------------------------------------------
-- Table `periodicalsdb`.`periodicals`
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS `periodicalsdb`.`periodicals` (
    `periodical_id` INT NOT NULL AUTO_INCREMENT,
    `name`          VARCHAR(255) NOT NULL,
    `description`   VARCHAR(255) NULL DEFAULT NULL,
    `cat_id`        INT NULL DEFAULT NULL,
    `price`         DECIMAL(15,2) UNSIGNED NOT NULL,
    PRIMARY KEY (`periodical_id`),
    INDEX `i_periodicals_name` (`name`)
);

-- -------------------------------------------------------
-- Table `periodicalsdb`.`subscriptions`
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS `periodicalsdb`.`subscriptions` (
    `sub_id`         INT NOT NULL AUTO_INCREMENT,
    `user_id`        INT NOT NULL,
    `periodical_id`  INT NOT NULL,
    `startdate`     DATE NOT NULL,
    `enddate`       DATE NOT NULL,
    PRIMARY KEY (`sub_id`),
    INDEX `u_subscriptions_user_id` (`user_id`)
);

-- -------------------------------------------------------
-- Table `periodicalsdb`.`subscriptions`
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS `periodicalsdb`.`balance_transactions` (
    `trans_id`       INT NOT NULL AUTO_INCREMENT,
    `periodical_id`  INT NOT NULL,
    `amount`         DOUBLE NOT NULL,
    `startdate`      DATE NOT NULL,
    `enddate`        DATE NOT NULL,
    PRIMARY KEY (`trans_id`),
    INDEX `u_balance_transactions_id` (`trans_id`)
);

-- --------------------------------------------------------------------------------------------
-- ----------------------------------------- Data ---------------------------------------------
-- --------------------------------------------------------------------------------------------

-- --------------------------------------------------------------------------------------------
-- Roles
-- --------------------------------------------------------------------------------------------
INSERT INTO roles (role_id, name) VALUES(DEFAULT, 'USER');
INSERT INTO roles (role_id, name) VALUES(DEFAULT, 'EDITOR');
INSERT INTO roles (role_id, name) VALUES(DEFAULT, 'ADMIN');

-- --------------------------------------------------------------------------------------------
-- Users
-- --------------------------------------------------------------------------------------------
INSERT INTO users (user_id, username, password, email, firstname, lastname, balance) VALUES
  (DEFAULT, 'user1',  'user1',  'gstream@gmail.com', 'Sami', 'Keinänen', 1100),
  (DEFAULT, 'magnum', 'magnum', 'sami@gmail.com',    'Sami', 'Wolking', 1200),
  (DEFAULT, 'kalma',  'kalma',  'kalma@gmail.com',   'Nick', 'Gore', 1300),
  (DEFAULT, 'kita',   'kita',   'kita@gmail.com',    'Sampsa', 'Astala', 1400),
  (DEFAULT, 'user',   '$2a$10$wSXV/z6SGuGsS1JwuDM7w.P8sZmzhwbaxQJFbySo8x0eHq0DliQgG', 'user@gmail.com', 'User', 'name', 1500);

INSERT INTO users (user_id, username, password, email, firstname, lastname, balance) VALUES
  (DEFAULT, 'otus',   'otus',   'otus@gmail.com',    'Tonmi', 'Kristian Lillman', 1600),
  (DEFAULT, 'oxx',    'oxx',    'oxx@gmail.com',     'Samer', 'el Nahhal', 1700),
  (DEFAULT, 'amen',   'amen',   'amen@gmail.com',    'Jussi', 'Sydänmaa', 1800),
  (DEFAULT, 'mana',   'mana',   'mana@gmail.com',    'Antto', 'Nikolai Tuomainen', 1900);

INSERT INTO users (user_id, username, password, email, firstname, lastname, balance) VALUES
  (DEFAULT, 'admin1', 'admin1', 'tomi@gmail.com',    'Tomi', 'Petteri Putaansuu', 2000),
  (DEFAULT, 'hella',  'hella',  'hella@gmail.com',   'Henna-Riikka', 'Tuulia Broda', 2000),
  (DEFAULT, 'awa',    'awa',    'awa@gmail.com',     'Leena', 'Maria Peisa', 2100),
  (DEFAULT, 'admin',  '$2a$10$Ea90moCGB9hxRN7XV8r6v.YfxrfSOduTKIFGQlYoUINJQUp5krAja', 'admin@gmail.com', 'Admin', 'name', 2200);

-- --------------------------------------------------------------------------------------------
-- Users_Roles
-- --------------------------------------------------------------------------------------------
SET @userRoleId = (SELECT role_id FROM roles WHERE name = 'USER');
INSERT INTO users_roles (user_id, role_id) VALUES
  ((SELECT user_id FROM `users` WHERE username = 'user1'),  @userRoleId),
  ((SELECT user_id FROM `users` WHERE username = 'magnum'), @userRoleId),
  ((SELECT user_id FROM `users` WHERE username = 'kalma'),  @userRoleId),
  ((SELECT user_id FROM `users` WHERE username = 'kita'),   @userRoleId),
  ((SELECT user_id FROM `users` WHERE username = 'user'),   @userRoleId);

SET @editorRoleId = (SELECT role_id FROM roles WHERE name = 'EDITOR');
INSERT INTO users_roles (user_id, role_id) VALUES
  ((SELECT user_id FROM `users` WHERE username = 'otus'),   @editorRoleId),
  ((SELECT user_id FROM `users` WHERE username = 'oxx'),    @editorRoleId),
  ((SELECT user_id FROM `users` WHERE username = 'amen'),   @editorRoleId),
  ((SELECT user_id FROM `users` WHERE username = 'mana'),   @editorRoleId);

SET @adminRoleId = (SELECT role_id FROM roles WHERE name = 'ADMIN');
INSERT INTO users_roles (user_id, role_id) VALUES
  ((SELECT user_id FROM `users` WHERE username = 'admin1'), @adminRoleId),
  ((SELECT user_id FROM `users` WHERE username = 'hella'),  @adminRoleId),
  ((SELECT user_id FROM `users` WHERE username = 'awa'),    @adminRoleId),
  ((SELECT user_id FROM `users` WHERE username = 'admin'),  @adminRoleId);

-- --------------------------------------------------------------------------------------------
-- Categories
-- --------------------------------------------------------------------------------------------
INSERT INTO categories (cat_id, name) VALUES (1, 'News'), (2, 'Medicine'), (3, 'IT'), (4, 'Defence');

-- --------------------------------------------------------------------------------------------
-- Periodicals
-- --------------------------------------------------------------------------------------------
INSERT INTO periodicals (periodical_id, name, description, cat_id, price) VALUES
  (DEFAULT, 'Guardian', 'Latest news, sport, business, comment, analysis and reviews from the , the world\'s leading liberal voice', 1, 200),
  (DEFAULT, 'New York Times', 'Live news, investigations, opinion, photos and video by the journalists from more than 150 countries around the world.', 1, 300),
  (DEFAULT, 'Wall Street Journal', 'Breaking news and analysis from the U.S. and around the world at WSJ.com.', 1, 400),
  (DEFAULT, 'Lancet', 'Regional Health - Europe publishes a Series of eleven papers by experts from different areas of public health', 2, 500),
  (DEFAULT, 'Janes', 'The latest defence and security news - the trusted source for defence intelligence', 4, 600),
  (DEFAULT, 'Wired', 'A monthly American magazine, published in print and online editions, that focuses on how emerging technologies affect culture, the economy, and politics.', 4, 700);

-- --------------------------------------------------------------------------------------------
-- Subscriptions
-- --------------------------------------------------------------------------------------------

INSERT INTO subscriptions (sub_id, user_id, periodical_id, startdate, enddate) VALUES
  (DEFAULT, (SELECT user_id FROM `users` WHERE username = 'user1'),  (SELECT periodical_id FROM `periodicals` WHERE name = 'Guardian'),            '2021-09-04', '2022-09-04'),
  (DEFAULT, (SELECT user_id FROM `users` WHERE username = 'magnum'), (SELECT periodical_id FROM `periodicals` WHERE name = 'New York Times'),      '2021-09-04', '2022-09-04'),
  (DEFAULT, (SELECT user_id FROM `users` WHERE username = 'kalma'),  (SELECT periodical_id FROM `periodicals` WHERE name = 'Wall Street Journal'), '2021-09-04', '2022-09-04'),
  (DEFAULT, (SELECT user_id FROM `users` WHERE username = 'kita'),   (SELECT periodical_id FROM `periodicals` WHERE name = 'Lancet'),              '2021-09-04', '2022-09-04'),
  (DEFAULT, (SELECT user_id FROM `users` WHERE username = 'user'),   (SELECT periodical_id FROM `periodicals` WHERE name = 'Janes'),               '2021-09-04', '2022-09-04');

INSERT INTO subscriptions (sub_id, user_id, periodical_id, startdate, enddate) VALUES
  (DEFAULT, (SELECT user_id FROM `users` WHERE username = 'otus'),   (SELECT periodical_id FROM `periodicals` WHERE name = 'Guardian'), '2021-09-05', '2022-09-05'),
  (DEFAULT, (SELECT user_id FROM `users` WHERE username = 'oxx'),    (SELECT periodical_id FROM `periodicals` WHERE name = 'Lancet'),   '2021-09-05', '2022-09-05'),
  (DEFAULT, (SELECT user_id FROM `users` WHERE username = 'amen'),   (SELECT periodical_id FROM `periodicals` WHERE name = 'Janes'),    '2021-09-05', '2022-09-05'),
  (DEFAULT, (SELECT user_id FROM `users` WHERE username = 'mana'),   (SELECT periodical_id FROM `periodicals` WHERE name = 'Wired'),    '2021-09-05', '2022-09-05');

INSERT INTO subscriptions (sub_id, user_id, periodical_id, startdate, enddate) VALUES
  (DEFAULT, (SELECT user_id FROM `users` WHERE username = 'admin1'), (SELECT periodical_id FROM `periodicals` WHERE name = 'Guardian'), '2021-09-06', '2022-09-06'),
  (DEFAULT, (SELECT user_id FROM `users` WHERE username = 'hella'),  (SELECT periodical_id FROM `periodicals` WHERE name = 'Lancet'),   '2021-09-06', '2022-09-06'),
  (DEFAULT, (SELECT user_id FROM `users` WHERE username = 'awa'),    (SELECT periodical_id FROM `periodicals` WHERE name = 'Janes'),    '2021-09-06', '2022-09-06'),
  (DEFAULT, (SELECT user_id FROM `users` WHERE username = 'admin'),  (SELECT periodical_id FROM `periodicals` WHERE name = 'Wired'),    '2021-09-06', '2022-09-06');


-- -------------------------------------------------------
-- Table `periodicalsdb`.`studen`
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS `periodicalsdb`.`studen` (
    `idstuden`   INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `group` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`idstuden`),
    UNIQUE INDEX `u_roles_name` (`name`)
);

-- -------------------------------------------------------
-- Table `periodicalsdb`.`studen`
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS `periodicalsdb`.`teacher` (
    `idteacher`   INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`idteacher`)
);

-- -------------------------------------------------------
-- Table `periodicalsdb`.`studen_has_teacher`
-- -------------------------------------------------------
CREATE TABLE `periodicalsdb`.`studen_has_teacher` (
    `idstuden` int(11) NOT NULL,
    `idteacher` int(11) NOT NULL,
    INDEX `i_idteacher` (`idteacher`)
);
