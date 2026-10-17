-- Acá esta la creacion de la base de datos con sus tablas
CREATE DATABASE soporte_tecnico;

USE soporte_tecnico;

CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL
);

CREATE TABLE categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE estado (
    id_estado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    modelo VARCHAR(100),
    descripcion VARCHAR(255),
    id_categoria INT NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE ticket (
    id_ticket INT AUTO_INCREMENT PRIMARY KEY,
    asunto VARCHAR(150) NOT NULL,
    descripcion TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    solucion TEXT,
    id_cliente INT NOT NULL,
    id_tecnico INT NULL,
    id_producto INT NOT NULL,
    id_estado INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_tecnico) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    FOREIGN KEY (id_estado) REFERENCES estado(id_estado)
);

CREATE TABLE respuesta (
    id_respuesta INT AUTO_INCREMENT PRIMARY KEY,
    contenido TEXT NOT NULL,
    fecha_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_ticket INT NOT NULL,
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_ticket) REFERENCES ticket(id_ticket),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);
-- insercion de los registros
INSERT INTO estado (nombre) VALUES
('Abierto'),
('En atención'),
('Resuelto'),
('Cerrado');

INSERT INTO usuario (nombre, email, contrasena, rol) VALUES
('Juan Pérez', 'juan@email.com', 'clave123', 'CLIENTE'),
('Laura Gómez', 'laura@cordobamicro.com', 'clave456', 'TECNICO'),
('Carlos Díaz', 'carlos@cordobamicro.com', 'clave789', 'ADMINISTRADOR');

INSERT INTO categoria (nombre, descripcion) VALUES
('Sensores', 'Sensores utilizados para medición y detección'),
('Microcontroladores', 'Microcontroladores y placas de desarrollo');

INSERT INTO producto (nombre, modelo, descripcion, id_categoria) VALUES
('Sensor de distancia', 'VL53L4CX', 'Sensor de medición de distancia', 1),
('Placa de desarrollo', 'STM32F4', 'Placa basada en microcontrolador STM32', 2);

INSERT INTO ticket
(asunto, descripcion, id_cliente, id_tecnico, id_producto, id_estado)
VALUES
('Lecturas incorrectas del sensor',
 'El sensor presenta valores de distancia inconsistentes.',
 1, 2, 1, 2),

('Problema de configuración',
 'No se logra configurar correctamente la placa de desarrollo.',
 1, NULL, 2, 1);
 
 INSERT INTO respuesta (contenido, id_ticket, id_usuario) VALUES
('¿Podría indicar qué configuración está utilizando actualmente?', 1, 2),
('Estoy utilizando la configuración predeterminada del sensor.', 1, 1);

-- Consultas SQL
-- Consulta de los tickets con cliente, producto y estado
SELECT
    t.id_ticket,
    t.asunto,
    u.nombre AS cliente,
    p.nombre AS producto,
    e.nombre AS estado,
    t.fecha_creacion
FROM ticket t
JOIN usuario u ON t.id_cliente = u.id_usuario
JOIN producto p ON t.id_producto = p.id_producto
JOIN estado e ON t.id_estado = e.id_estado;
-- Consulta de tickets pendientes de asignacion
SELECT
    id_ticket,
    asunto,
    fecha_creacion
FROM ticket
WHERE id_tecnico IS NULL;
-- Consulta de tickets asignados a tecnicos
SELECT
    t.id_ticket,
    t.asunto,
    u.nombre AS tecnico,
    e.nombre AS estado
FROM ticket t
JOIN usuario u ON t.id_tecnico = u.id_usuario
JOIN estado e ON t.id_estado = e.id_estado;
-- Consulta del historial de respuestas de un ticket
SELECT
    r.fecha_hora,
    u.nombre AS autor,
    u.rol,
    r.contenido
FROM respuesta r
JOIN usuario u ON r.id_usuario = u.id_usuario
WHERE r.id_ticket = 1
ORDER BY r.fecha_hora;
-- Consulta de tickets segun su estado
SELECT
    t.id_ticket,
    t.asunto,
    e.nombre AS estado
FROM ticket t
JOIN estado e ON t.id_estado = e.id_estado
WHERE e.nombre = 'Abierto';

-- Prueba de borrado de registros

INSERT INTO categoria (nombre, descripcion)
VALUES ('Temporal', 'Registro creado para prueba de borrado');

SELECT * FROM categoria
WHERE nombre = 'Temporal';

DELETE FROM categoria
WHERE nombre = 'Temporal';

SELECT * FROM categoria
WHERE nombre = 'Temporal';