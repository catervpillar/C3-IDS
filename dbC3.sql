CREATE DATABASE IF NOT EXISTS databaseC3;
USE databaseC3;
/*CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL ON *.* TO 'admin'@'localhost';
SET GLOBAL time_zone = '-6:00';
*/
CREATE TABLE `cliente` (
`id` VARCHAR(8) NOT NULL UNIQUE,
`nome` VARCHAR(45) NOT NULL,
`cognome` VARCHAR(45) NOT NULL,
`indirizzo` VARCHAR(50), 
`email` VARCHAR(50) NOT NULL UNIQUE,
`telefono` VARCHAR(10),
`username` VARCHAR(10),
`password` VARCHAR(16),
PRIMARY KEY (`id`));

