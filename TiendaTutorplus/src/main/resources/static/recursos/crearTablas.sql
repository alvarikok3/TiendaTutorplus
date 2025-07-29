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
    especialidad VARCHAR(100) NOT NULL,
    contrasena VARCHAR(50)
);

CREATE TABLE estudiante (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido1 VARCHAR(50) NOT NULL,
    apellido2 VARCHAR(50) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL
);

CREATE TABLE administradores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido1 VARCHAR(50) NOT NULL,
    apellido2 VARCHAR(50) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    especialidad VARCHAR(100) NOT NULL,
    contrasena VARCHAR(50)
);

INSERT INTO tutor (nombre, apellido1, apellido2, correo, especialidad, contrasena)
VALUES 
('José', 'Heerera', 'Jiménez', 'jose.herrera@tutorplus.co.cr', 'Historia', 'JoseH@87945'),
('Andrés', 'Mora', 'Mora', 'andres.mora@tutorplus.co.cr', 'Matemáticas', 'AndresM$34651'),
('María', 'Fernández', 'Solano', 'maria.fernandez@tutorplus.co.cr', 'Química', 'MariaF#78946'),
('Laura', 'Ramírez', 'Soto', 'laura.ramirez@tutorplus.co.cr', 'Inglés', 'LauraR@84960'),
('Carlos', 'Pérez', 'Porras', 'carlos.perez@tutorplus.co.cr', 'Matemáticas', 'CarlosP$23654'),
('María', 'Gonzáles', 'Castillo', 'maria.gonzales@tutorplus.co.cr', 'Matemáticas', 'MariaG#12546');

INSERT INTO estudiante (nombre, apellido1, apellido2, correo, contrasena)
VALUES 
('Ana', 'Gómez', 'Hernández', 'ana.gomez@tutorplus.co.cr', 'AnaG#87622'),
('Pedro', 'Jiménez', 'Salas', 'pedro.jimenez@tutorplus.co.cr', 'PedroJ@35498'),
('María', 'Lopez', 'Ramírez', 'maria.lopez@tutorplus.co.cr', 'MariaL$64879');

INSERT INTO administradores (nombre, apellido1, apellido2, correo, especialidad, contrasena)
VALUES 
('Andy', 'Rodríguez', 'Rodríguez', 'admin@tutorplus.co.cr', 'Administrador', 'Admin*AN123'),
('Bernal', 'Herrera', 'Arias', 'adminbernal@tutorplus.co.cr', 'Administrador', 'Admin*BE456'),
('Alvaro', 'Monge', 'Guzman', 'adminalvaro@tutorplus.co.cr', 'Administrador', 'Admin*AL789');

CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO roles (nombre) VALUES 
('ROLE_TUTOR'), 
('ROLE_ESTUDIANTE'), 
('ROLE_ADMIN');

CREATE TABLE usuario_rol (
    correo VARCHAR(100) NOT NULL,
    rol_id INT NOT NULL,
    FOREIGN KEY (rol_id) REFERENCES roles(id),
    PRIMARY KEY (correo, rol_id)
);

-- Asignar rol a los TUTORES
INSERT INTO usuario_rol (correo, rol_id)
SELECT correo, (SELECT id FROM roles WHERE nombre = 'ROLE_TUTOR') FROM tutor;

-- Asignar rol a los ESTUDIANTES
INSERT INTO usuario_rol (correo, rol_id)
SELECT correo, (SELECT id FROM roles WHERE nombre = 'ROLE_ESTUDIANTE') FROM estudiante;

-- Asignar rol a los ADMINISTRADORES
INSERT INTO usuario_rol (correo, rol_id)
SELECT correo, (SELECT id FROM roles WHERE nombre = 'ROLE_ADMIN') FROM administradores;

USE tutorplus;
SELECT * FROM tutor;
SELECT * FROM estudiante;
SELECT * FROM administradores;
