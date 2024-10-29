CREATE DATABASE bbddTPI;

USE bbddTPI;

/*
Monotributista
Responsable Inscripto
No Responsable Inscripto
Exento
*/

CREATE TABLE usuario (
	-- informacion comun
	id_usuario INT NOT NULL IDENTITY(1,1),
    nombre VARCHAR(45) NOT NULL,
	apellido VARCHAR(45),
    contrasena VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL,
	telefono VARCHAR(100),
    PRIMARY KEY(id_usuario)
);

CREATE TABLE cliente (
	-- campos adicionales especificos
	id_cliente INT NOT NULL IDENTITY(1,1),
    domicilio VARCHAR(100),
	tipoRegimenLaboral VARCHAR(100),
    id_usuario INT NOT NULL, -- referencia a usuario
    PRIMARY KEY(id_cliente), 
	-- relacion con la tabla usuario
    CONSTRAINT fk_cliente FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE administrador (
	-- campos adicionales especificos
	id_administrador INT NOT NULL IDENTITY(1,1),
	area_trabajo VARCHAR(100) NOT NULL,
	estado VARCHAR(100) DEFAULT 'ACTIVO',
    id_usuario INT NOT NULL, -- referencia a usuario
    PRIMARY KEY(id_administrador),
	-- relacion con la tabla usuario
    CONSTRAINT fk_administrador FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE vehiculo (
	id_vehiculo INT NOT NULL IDENTITY(1,1),
    marca VARCHAR(45) NOT NULL,
    modelo VARCHAR(45) NOT NULL,
    anio INT NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    estado VARCHAR(45) DEFAULT 'DISPONIBLE',
    PRIMARY KEY(id_vehiculo)
);

CREATE TABLE servicio (
	id_servicio INT NOT NULL IDENTITY(1,1),
	nombre VARCHAR(45) NOT NULL,
	costo FLOAT NOT NULL,
	estado VARCHAR(45) DEFAULT 'DISPONIBLE',
	PRIMARY KEY(id_servicio)
);

CREATE TABLE turno (
	id_turno INT NOT NULL IDENTITY(1,1),
	id_cliente INT NOT NULL,
	id_vehiculo INT NOT NULL,
	id_servicio INT NOT NULL,
	fecha DATE DEFAULT GETDATE() NOT NULL,
	costo_total DECIMAL(10,2) NOT NULL,
	estado BIT NOT NULL,
	PRIMARY KEY(id_turno),
	CONSTRAINT fk_turno_cliente FOREIGN KEY(id_cliente) REFERENCES cliente(id_cliente),
	CONSTRAINT fk_turno_vehiculo FOREIGN KEY(id_vehiculo) REFERENCES vehiculo(id_vehiculo),
	CONSTRAINT fk_turno_servicio FOREIGN KEY(id_servicio) REFERENCES servicio(id_servicio)
);

-- Sedders

-- Insertar en usuario
INSERT INTO usuario (nombre, apellido, contrasena, correo, telefono) VALUES
('Juan', 'Perez', 'password123', 'juan.perez@mail.com', '316261626'),
('Diego', 'Gomez', 'password246', 'diego.gomez@mail.com', '342233223'),
('Esteban', 'Aquino', 'password369', 'esteban.aquino@mail.com', '23442233');

-- Insertar en cliente
INSERT INTO cliente (domicilio, tipoRegimenLaboral, id_usuario) VALUES
('direccion1', 'monotributista', 1),
('direccion2', 'exento', 3);

-- Insertar un administrador
INSERT INTO administrador (area_trabajo, id_usuario) VALUES
('gerente', 2);

INSERT INTO vehiculo (marca, modelo, anio, precio, estado) VALUES
('Toyota', 'Corolla', 2020, 15000.00, 'DISPONIBLE'),
('Ford', 'Focus', 2018, 12000.00, 'DISPONIBLE'),
('Chevrolet', 'Cruze', 2019, 13000.00, 'DISPONIBLE');

INSERT INTO servicio (nombre, costo, estado) VALUES
('Cambio de aceite', 50.00, 'DISPONIBLE'),
('Alineación y balanceo', 100.00, 'DISPONIBLE'),
('Revisión general', 200.00, 'DISPONIBLE');


INSERT INTO turno (id_cliente, id_vehiculo, id_servicio, fecha, costo_total, estado) VALUES
(1, 1, 1, '2024-10-28', 50.00, 1),
(2, 2, 2, '2024-10-29', 100.00, 1),
(1, 3, 3, '2024-10-30', 200.00, 1);


-- Consultas
SELECT * FROM usuario;
SELECT * FROM cliente;
SELECT * FROM administrador;
SELECT * FROM vehiculo;
SELECT * FROM servicio;
SELECT * FROM turno;


/*
DROP TABLE IF EXISTS turno;
DROP TABLE IF EXISTS servicio;
DROP TABLE IF EXISTS vehiculo;
DROP TABLE IF EXISTS administrador;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS usuario;
*/


-- listar los clientes
SELECT c.id_cliente AS id_cliente, u.nombre AS nombre, u.telefono AS telefono, c.domicilio AS domicilio, c.tipoRegimenLaboral AS regimen
FROM cliente c
LEFT JOIN usuario u ON u.id_usuario = c.id_usuario
ORDER BY u.nombre;

-- listar los administradores
SELECT a.id_administrador AS id_admin, u.nombre AS nombre, u.correo AS correo, a.area_trabajo AS area, a.estado AS estado
FROM administrador a
LEFT JOIN usuario u ON a.id_usuario = u.id_usuario
ORDER BY u.nombre;

-- Listar todos los turnos
SELECT 
	c.id_cliente AS id_cliente,
	CONCAT(u.nombre, ' ', u.apellido) AS nombre_completo,
	s.nombre AS servicio,
	v.marca AS vehiculo,
	t.fecha AS fecha,
	t.costo_total AS costo,
	t.estado AS estado
FROM cliente c
LEFT JOIN usuario u ON c.id_usuario = u.id_usuario
LEFT JOIN turno t ON c.id_cliente = t.id_cliente
LEFT JOIN servicio s ON t.id_servicio = s.id_servicio
LEFT JOIN vehiculo v ON t.id_vehiculo = v.id_vehiculo
ORDER BY s.costo DESC;

-- Ver cuántos turnos tiene cada cliente:
SELECT 
    c.id_cliente AS id_cliente,
    CONCAT(u.nombre, ' ', u.apellido) AS nombre_completo,
    COUNT(t.id_turno) AS cantidad_turnos
FROM cliente c
LEFT JOIN usuario u ON c.id_usuario = u.id_usuario
LEFT JOIN turno t ON c.id_cliente = t.id_cliente
GROUP BY 
    c.id_cliente, 
    u.nombre,
    u.apellido
ORDER BY c.id_cliente;

-- ver los turnos de un cliente en especifico
SELECT 
	t.id_turno AS 'ID TURNO',
	CONCAT(u.nombre, ' ', u.apellido) AS 'NOMBRE COMPLETO',
	v.marca AS 'VEHICULO',
	s.nombre AS 'TIPO SERVICIO',
	t.fecha AS 'FECHA',
	t.costo_total AS 'COSTO',
	t.estado AS 'ESTADO'
FROM cliente c
LEFT JOIN usuario u ON c.id_usuario = u.id_usuario
LEFT JOIN turno t ON c.id_cliente = t.id_cliente
LEFT JOIN vehiculo v ON t.id_vehiculo = v.id_vehiculo
LEFT JOIN servicio s ON t.id_servicio = s.id_servicio
WHERE c.id_cliente = 1
ORDER BY t.costo_total DESC;


