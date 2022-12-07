CREATE DATABASE votaciones;

USE votaciones;

CREATE TABLE candidato (
  id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NOT NULL,
  cantVotos INT(11) NOT NULL,
  PRIMARY KEY (id));

DESCRIBE candidato;

SELECT * FROM candidato;
