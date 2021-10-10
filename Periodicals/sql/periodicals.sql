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
    UNIQUE INDEX `u_roles_name` (`name` ASC));

-- -------------------------------------------------------
-- Table `periodicalsdb`.`users`
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS `periodicalsdb`.`users` (
    `user_id`   INT NOT NULL AUTO_INCREMENT,
    `username`  VARCHAR(64)  NOT NULL,
    `password`  VARCHAR(64)  NOT NULL,
    `firstname` VARCHAR(255) NOT NULL DEFAULT '',
    `lastname`  VARCHAR(255) NOT NULL DEFAULT '',
    `fullname`  VARCHAR(255) NOT NULL DEFAULT '',
    `email`     VARCHAR(255) NOT NULL,
    `status`    TINYINT NULL DEFAULT '1',
    `account`   DECIMAL(15, 2) UNSIGNED NULL DEFAULT NULL,
    `created`   TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `updated`   TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `u_users_username` (`username` ASC)
);

-- -------------------------------------------------------
-- Table `periodicalsdb`.`users_roles`
-- -------------------------------------------------------
CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  INDEX `i_user_id` (`user_id` ASC)
);

-- -------------------------------------------------------
-- Table `periodicalsdb`.`categories`
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS `periodicalsdb`.`categories` (
    `cat_id` INT NOT NULL AUTO_INCREMENT,
    `name`   VARCHAR(64) NOT NULL,
    PRIMARY KEY (`cat_id`),
    UNIQUE INDEX `u_categories_name` (`name` ASC)
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
    INDEX `i_periodicals_name` (`name` ASC)
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
    INDEX `u_subscriptions_user_id` (`user_id` ASC)
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
INSERT INTO users (user_id, username, password, email, fullname) VALUES
  (DEFAULT, 'user1',  'user1',  'gstream@gmail.com', 'Sami Keinänen'),
  (DEFAULT, 'magnum', 'magnum', 'sami@gmail.com',    'Sami Wolking'),
  (DEFAULT, 'kalma',  'kalma',  'kalma@gmail.com',   'Nick Gore'),
  (DEFAULT, 'kita',   'kita',   'kita@gmail.com',    'Sampsa Astala'),
  (DEFAULT, 'user',   '$2a$10$wSXV/z6SGuGsS1JwuDM7w.P8sZmzhwbaxQJFbySo8x0eHq0DliQgG', 'user@gmail.com', 'User name');

INSERT INTO users (user_id, username, password, email, fullname) VALUES
  (DEFAULT, 'otus',   'otus',   'otus@gmail.com',    'Tonmi Kristian Lillman'),
  (DEFAULT, 'oxx',    'oxx',    'oxx@gmail.com',     'Samer el Nahhal'),
  (DEFAULT, 'amen',   'amen',   'amen@gmail.com',    'Jussi Sydänmaa'),
  (DEFAULT, 'mana',   'mana',   'mana@gmail.com',    'Antto Nikolai Tuomainen');

INSERT INTO users (user_id, username, password, email, fullname) VALUES
  (DEFAULT, 'admin1', 'admin1', 'tomi@gmail.com',    'Tomi Petteri Putaansuu'),
  (DEFAULT, 'hella',  'hella',  'hella@gmail.com',   'Henna-Riikka Tuulia Broda'),
  (DEFAULT, 'awa',    'awa',    'awa@gmail.com',     'Leena Maria Peisa'),
  (DEFAULT, 'admin',  '$2a$10$LehXdZM4hFlDB6AASmdPZubIaV/XiaES5EBEsEPvn5nG37yh8bljq', 'admin@gmail.com', 'Admin name');

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
INSERT INTO categories (cat_id, name) VALUES (1, 'News'), (2, 'Medicine'), (3, 'IT'), (4, 'Defense');

-- --------------------------------------------------------------------------------------------
-- Periodicals
-- --------------------------------------------------------------------------------------------
INSERT INTO periodicals (periodical_id, name, description, cat_id, price) VALUES
  (DEFAULT, 'Guardian, October 2021',           'Pandora papers',                 1, 2),
  (DEFAULT, 'New York Times, September 2021',   'Oil prices rise',                1, 3),
  (DEFAULT, 'Wall Street Journal, August 2021', 'Cryptocurrencies fall in China', 1, 4),
  (DEFAULT, 'Lancet, July 2021',                'Race for vaccine',               2, 5),
  (DEFAULT, 'Janes, June 2021',                 'Iron dome',                      4, 6),
  (DEFAULT, 'Wired, May 2021',                  'New DDOS of unseen strength',    4, 7);

-- --------------------------------------------------------------------------------------------
-- Subscriptions
-- --------------------------------------------------------------------------------------------
INSERT INTO subscriptions (sub_id, user_id, periodical_id, startdate, enddate) VALUES
  (DEFAULT, 5, 1, '2021-09-04', '2021-09-04'),
  (DEFAULT, 5, 2, '2021-09-04', '2021-09-04'),
  (DEFAULT, 5, 3, '2021-09-04', '2021-09-04'),
  (DEFAULT, 5, 4, '2021-09-04', '2021-09-04'),
  (DEFAULT, 5, 6, '2020-09-04', '2021-09-05');
