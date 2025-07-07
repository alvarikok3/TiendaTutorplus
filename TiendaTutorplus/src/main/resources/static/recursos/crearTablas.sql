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


USE tutorplus;
SELECT * FROM tutor;
SELECT * FROM estudiante;