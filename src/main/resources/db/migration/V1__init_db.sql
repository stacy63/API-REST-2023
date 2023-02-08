-- --------------------------------------------------------
-- Version du serveur:           10.11.1-rc -MariaDB - mariadb.org binary distribution
-- SE du serveur:                Win64
-- --------------------------------------------------------

-- ------------------------------------------------------------------------------
-- - Construction de la base de données                                     ---
-- ------------------------------------------------------------------------------
DROP DATABASE IF EXISTS `apidatabase`;
CREATE DATABASE IF NOT EXISTS `apidatabase`;
USE `apidatabase`;

-- -----------------------------------------------------------------------------
-- - Construction de la table des ingrédients                               ---
-- -----------------------------------------------------------------------------
-- (11) doesn't define the size of the integer. It's just number of digits to display in some tools
DROP TABLE IF EXISTS `ingredient`;
CREATE TABLE IF NOT EXISTS `ingredient` (
                                            `id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                            `name` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- -----------------------------------------------------------------------------
-- - Construction de la table des recettes                               ---
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS `recipe`;
CREATE TABLE IF NOT EXISTS `recipe` (
                                        `id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                        `name` varchar(20) NOT NULL,
                                        `preparation_time_hours` int(2),
                                        `preparation_time_minutes` int(2),
                                        `description` varchar(2000)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- -----------------------------------------------------------------------------
-- - Construction de la table des associations                               ---
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS `recipe_ingredient_association`;
CREATE TABLE IF NOT EXISTS `recipe_ingredient_association` (
                                                    `recipe_id` int(11) NOT NULL,
                                                    `ingredient_id` int(11) NOT NULL,
                                                    `quantity` int(4),
                                                    `unit_of_mesurement` varchar(5),
                                                    CONSTRAINT `PK_ASSOCIATION` PRIMARY KEY (`recipe_id`, `ingredient_id`),
                                                    CONSTRAINT `FK_RECIPE_ID` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`),
                                                    CONSTRAINT `FK_INGREDIENT_ID` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;