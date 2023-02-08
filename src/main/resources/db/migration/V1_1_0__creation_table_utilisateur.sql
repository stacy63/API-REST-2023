-- ------------------------------------------------------------------------------------
-- Version du serveur:           10.11.1-rc -MariaDB - mariadb.org binary distribution
-- SE du serveur:                Win64
-- ------------------------------------------------------------------------------------


-- -----------------------------------------------------------------------------
-- - Construction de la table des utilisateurs                               ---
-- -----------------------------------------------------------------------------
-- Contrainte UNIQUE pour garantir qu'aucune valeur en double n'est entrée dans des
-- colonnes spécifiques ne faisant pas partie d'une clé primaire.
-- In MySQL, LENGTH() returns the length of the string in bytes (not the number of characters).
-- This means that if a string contains multibyte characters, its length in bytes can be greater
-- than in characters. To return specifically the number of characters in a string in MySQL,
-- the CHAR_LENGTH() function is used.
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
                                        `id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                        `email` varchar(319) UNIQUE NOT NULL,
                                        `pseudo` varchar(50) UNIQUE NOT NULL,
                                        `password` char(64) NOT NULL,
                                        `salt` char(16) UNIQUE NOT NULL,
                                        `last_update` DATE NOT NULL,
                                        CONSTRAINT `CHK_EMAIL` CHECK (`email` LIKE '%_@__%.__%')
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;