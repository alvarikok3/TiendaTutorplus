-- Crear nueva base de datos
CREATE DATABASE tutorplus;
USE tutorplus;

-- Crear usuario con contraseña segura
CREATE USER 'usuario_tutor'@'%' IDENTIFIED BY 'Clave_Tutor123.';

-- Dar permisos completos a ese usuario sobre tutorplus
GRANT ALL PRIVILEGES ON tutorplus.* TO 'usuario_tutor'@'%';
FLUSH PRIVILEGES;

-- Crear la tabla tutor
CREATE TABLE tutor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido1 VARCHAR(50) NOT NULL,
    apellido2 VARCHAR(50) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    especialidad VARCHAR(100) NOT NULL
);

USE tutorplus;
SELECT * FROM tutor;