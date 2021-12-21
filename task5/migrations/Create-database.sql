CREATE SCHEMA IF NOT EXISTS `task5` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS `task5`.`manufacturer` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NOT NULL COMMENT 'Наименование производителей.',
  CONSTRAINT pkId PRIMARY KEY (`id`),
  CONSTRAINT ukTitle UNIQUE KEY (`title`))
ENGINE = InnoDB
COMMENT = 'Это таблица производителей.';

CREATE TABLE IF NOT EXISTS `task5`.`category` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(30) NOT NULL COMMENT 'Наименование категорий товара.',
  CONSTRAINT pkId PRIMARY KEY (`id`),
  CONSTRAINT ukName UNIQUE KEY(`title`))
ENGINE = InnoDB
COMMENT = 'Это таблица категорий товара.';

CREATE TABLE IF NOT EXISTS `task5`.`product` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NOT NULL COMMENT 'Наименование товара.',
  `categoryId` INT UNSIGNED NOT NULL COMMENT 'ID категории товара.',
  `vendorCode` VARCHAR(30) COMMENT 'ID товара у производителя.',
  `manufacturerId` INT UNSIGNED NOT NULL COMMENT 'ID производителя.',
  `description` VARCHAR(100) COMMENT 'Описание товара.',
  CONSTRAINT pkId PRIMARY KEY (`id`),
  CONSTRAINT ukTitle_ManufacturerId UNIQUE KEY(`title`, `manufacturerId`),
  CONSTRAINT `fk_category`
  FOREIGN KEY (`categoryId`)
    REFERENCES `task5`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_manufacturer`
  FOREIGN KEY (`manufacturerId`)
    REFERENCES `task5`.`manufacturer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Это таблица товара.';

DROP DATABASE IF EXISTS task5;

