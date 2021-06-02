CREATE TABLE IF NOT EXISTS client (
id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
name varchar(50),
lastname varchar(50),
date_of_birth timestamp
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;