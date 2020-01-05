CREATE SCHEMA IF NOT EXISTS `task5` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS `task5`.`manufecturer` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NOT NULL COMMENT 'Наименование производителей.',
  CONSTRAINT pkId PRIMARY KEY (`id`),
  CONSTRAINT ukTitle UNIQUE KEY (`title`))
ENGINE = InnoDB
COMMENT = 'Это таблица производителей.';

CREATE TABLE IF NOT EXISTS `task5`.`category` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL COMMENT 'Наименование категорий товара.',
  CONSTRAINT pkId PRIMARY KEY (`id`),
  CONSTRAINT ukName UNIQUE KEY(`name`))
ENGINE = InnoDB
COMMENT = 'Это таблица категорий товара.';

CREATE TABLE IF NOT EXISTS `task5`.`product` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(30) NOT NULL COMMENT 'Наименование товара.',
  `categoryId` INT UNSIGNED NOT NULL COMMENT 'ID категории товара.',
  `productId` VARCHAR(30) COMMENT 'ID товара у производителя.',
  `manufecturerId` INT UNSIGNED NOT NULL COMMENT 'ID производителя.',
  `description` VARCHAR(100) COMMENT 'Описание товара.',
  CONSTRAINT pkId PRIMARY KEY (`id`),
  CONSTRAINT ukTitle_ManufecturerId UNIQUE KEY(`title`, `manufecturerId`),
  CONSTRAINT `fk_category`
  FOREIGN KEY (`categoryId`)
    REFERENCES `task5`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_manufecturer`
  FOREIGN KEY (`manufecturerId`)
    REFERENCES `task5`.`manufecturer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Это таблица товара.';

