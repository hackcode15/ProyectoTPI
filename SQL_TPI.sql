CREATE SCHEMA bbdd_tpi;

USE bbdd_tpi;

CREATE TABLE usuario (
	id_usuario INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    correo VARCHAR(100),
    esAdmin BOOLEAN NOT NULL,
    PRIMARY KEY(id_usuario)
) ENGINE InnoDB;

CREATE TABLE cliente (
	id_cliente INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    dni VARCHAR(100),
    telefono VARCHAR(100),
    domicilio VARCHAR(100),
    id_usuario INT NOT NULL,
    PRIMARY KEY(id_cliente),
    CONSTRAINT fk_cliente FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario)
) ENGINE InnoDB;

CREATE TABLE administrador (
	id_administrador INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    apellido VARCHAR(45) NOT NULL,
    correo VARCHAR(100),
    id_usuario INT NOT NULL,
    PRIMARY KEY(id_administrador),
    CONSTRAINT fk_administrador FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario)
) ENGINE InnoDB;

CREATE TABLE vehiculo (
	id_vehiculo INT NOT NULL AUTO_INCREMENT,
    marca VARCHAR(45) NOT NULL,
    modelo VARCHAR(45) NOT NULL,
    anio INT NOT NULL,
    precio DOUBLE NOT NULL,
    estado VARCHAR(45) DEFAULT 'DISPONIBLE',
    PRIMARY KEY(id_vehiculo)
) ENGINE InnoDB;

CREATE TABLE compra (
	id_compra INT NOT NULL AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    id_vehiculo INT NOT NULL,
    monto_total DOUBLE NOT NULL,
    metodo_pago VARCHAR(45) NOT NULL,
    PRIMARY KEY(id_compra),
    CONSTRAINT fk_cliente_compra FOREIGN KEY(id_cliente) REFERENCES cliente(id_cliente),
    CONSTRAINT fk_vehiculo_compra FOREIGN KEY(id_vehiculo) REFERENCES vehiculo(id_vehiculo)
) ENGINE InnoDB;

INSERT INTO usuario (nombre, contrasena, correo, esAdmin) VALUES
('juan_perez', '789', 'juan.perez@gmail.com', TRUE),
('ana_garcia', '234', 'ana.garcia@hotmail.com', FALSE),
('carlos_rodriguez', '567', 'carlos.rodriguez@yahoo.com', FALSE),
('laura_martinez', '890', 'laura.martinez@outlook.com', TRUE),
('pedro_sanchez', '345', 'pedro.sanchez@gmail.com', FALSE),
('sofia_lopez', '678', 'sofia.lopez@empresa.com', FALSE);

-- Procedimientos almacenados para los usuarios
CREATE PROCEDURE agregar_usuario(n_nombre VARCHAR(45), n_contrasena VARCHAR(100), n_correo VARCHAR(100), n_esAdmin BOOLEAN)
INSERT INTO usuario(nombre, contrasena, correo, esAdmin)
VALUES (n_nombre, n_contrasena, n_correo, n_esAdmin);

-- en duda
CREATE PROCEDURE listar_usuario()
SELECT * FROM usuario;

CREATE PROCEDURE actualizar_usuario(n_id_usuario INT, n_contrasena VARCHAR(100), n_esAdmin BOOLEAN)
UPDATE usuario SET contrasena = n_contrasena, esAdmin = n_esAdmin WHERE id_usuario = n_id_usuario;








