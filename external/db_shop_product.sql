DROP SCHEMA IF EXISTS `db_shop_product`;

CREATE SCHEMA `db_shop_product` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE `db_shop_product`.`product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  `country` VARCHAR(30) NOT NULL,
  `price` FLOAT NOT NULL DEFAULT 0.0,
  `amount` INT NOT NULL DEFAULT 0,
  `delivery_date` DATE NULL,
  `image` LONGBLOB NULL,
  PRIMARY KEY (`id`)
 )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;
