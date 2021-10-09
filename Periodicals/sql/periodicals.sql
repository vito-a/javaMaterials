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
    `login`     VARCHAR(64)  NOT NULL,
    `password`  VARCHAR(64)  NOT NULL,
    `firstname` VARCHAR(255) NOT NULL DEFAULT '',
    `lastname`  VARCHAR(255) NOT NULL DEFAULT '',
    `fullname`  VARCHAR(255) NOT NULL DEFAULT '',
    `email`     VARCHAR(255) NOT NULL,
    `status`    TINYINT NULL DEFAULT '1',
    `role_id`   INT NOT NULL,
    `account`   DECIMAL(15, 2) UNSIGNED NULL DEFAULT NULL,
    `created`   TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `updated`   TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `u_users_login` (`login` ASC)
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
    `category_id`   INT NULL DEFAULT NULL,
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
INSERT INTO roles (role_id, name) VALUES(DEFAULT, 'admin');
INSERT INTO roles (role_id, name) VALUES(DEFAULT, 'user');

-- --------------------------------------------------------------------------------------------
-- Users
-- --------------------------------------------------------------------------------------------
SET @adminRoleId = (SELECT role_id FROM roles WHERE name = 'admin');
INSERT INTO users (user_id, login, password, email, fullname, role_id) VALUES
  (DEFAULT, 'admin', 'admin', 'tomi@gmail.com',  'Tomi Petteri Putaansuu',     @adminRoleId),
  (DEFAULT, 'amen',  'amen',  'amen@gmail.com',  'Jussi Sydänmaa',             @adminRoleId),
  (DEFAULT, 'mana',  'mana',  'mana@gmail.com',  'Antto Nikolai Tuomainen',    @adminRoleId),
  (DEFAULT, 'hella', 'hella', 'hella@gmail.com', 'Henna-Riikka Tuulia Broda',  @adminRoleId),
  (DEFAULT, 'awa',   'awa',   'awa@gmail.com',   'Leena Maria Peisa',          @adminRoleId);

SET @userRoleId = (SELECT role_id FROM roles WHERE name = 'user');
INSERT INTO users (user_id, login, password, email, fullname, role_id) VALUES
  (DEFAULT, 'user',   'user',   'gstream@gmail.com', 'Sami Keinänen',          @userRoleId),
  (DEFAULT, 'magnum', 'magnum', 'sami@gmail.com',    'Sami Wolking',           @userRoleId),
  (DEFAULT, 'kalma',  'kalma',  'kalma@gmail.com',   'Nick Gore',              @userRoleId),
  (DEFAULT, 'kita',   'kita',   'kita@gmail.com',    'Sampsa Astala',          @userRoleId),
  (DEFAULT, 'otus',   'otus',   'otus@gmail.com',    'Tonmi Kristian Lillman', @userRoleId),
  (DEFAULT, 'oxx',    'oxx',    'oxx@gmail.com',     'Samer el Nahhal',        @userRoleId);

-- --------------------------------------------------------------------------------------------
-- Categories
-- --------------------------------------------------------------------------------------------
INSERT INTO categories (cat_id, name) VALUES (1, 'News'), (2, 'Medicine'), (3, 'IT'), (4, 'Defense');

-- --------------------------------------------------------------------------------------------
-- Periodicals
-- --------------------------------------------------------------------------------------------
INSERT INTO periodicals (periodical_id, name, description, category_id, price) VALUES
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
