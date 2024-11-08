-- ******************************* CREACION DE LA BASE DE DATOS *************************************
-- 1 ejecucion
-- CREATE DATABASE proyectoBD;

-- ******************************* USO DE LA BASE DE DATOS ********************************************
-- 2 ejecucion
USE proyectoBD;

-- ******************************** CREACIONES DE TABLAS******************************************************

-- 3 ejecucion
/*CREATE TABLE usuario (
	dni BIGINT NOT NULL,
    nombre VARCHAR(45) NOT NULL,
	apellido VARCHAR(45),
    contrasena VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL,
	telefono VARCHAR(100),
    PRIMARY KEY(dni)
);*/

-- 4 ejecucion
/*CREATE TABLE cliente (
	dni BIGINT NOT NULL,
	fechaIngreso DATETIME DEFAULT GETDATE(),
    domicilio VARCHAR(100),
	RegimenLaboral VARCHAR(100) NOT NULL,
    PRIMARY KEY(dni), 
    CONSTRAINT fk_cliente FOREIGN KEY(dni) REFERENCES usuario(dni) ON DELETE CASCADE
);*/

-- 5 ejecucion
/*CREATE TABLE mecanico (
	dni BIGINT NOT NULL,
	fechaIngreso DATETIME DEFAULT GETDATE(),
	sueldo DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY(dni),
    CONSTRAINT fk_administrador FOREIGN KEY(dni) REFERENCES usuario(dni) ON DELETE CASCADE
);*/

-- 6 ejecucion
/*CREATE TABLE vehiculo (
    id_vehiculo INT IDENTITY(1,1),
    marca VARCHAR(45) NOT NULL,
    modelo VARCHAR(45) NOT NULL,
    anio INT NOT NULL,
    dni_cliente BIGINT NOT NULL,
	PRIMARY KEY(id_vehiculo),
    CONSTRAINT fk_vehiculo_cliente FOREIGN KEY (dni_cliente) REFERENCES cliente(dni)
);*/

-- 7 ejecucion
/*CREATE TABLE servicio (
    id_servicio INT IDENTITY(1,1),
    nombre VARCHAR(45) NOT NULL,
    costo DECIMAL(10, 2) NOT NULL,
    estado VARCHAR(45) DEFAULT 'DISPONIBLE',
	PRIMARY KEY(id_servicio)
);*/

-- 8 ejecucion
/*CREATE TABLE turno (
    id_turno INT IDENTITY(1,1),
    fecha DATE DEFAULT GETDATE() NOT NULL,
    id_vehiculo INT NOT NULL,
    dni_cliente BIGINT NOT NULL,
    PRIMARY KEY(id_turno),
    CONSTRAINT fk_turno_vehiculo FOREIGN KEY (id_vehiculo) REFERENCES vehiculo(id_vehiculo),
    CONSTRAINT fk_turno_cliente FOREIGN KEY (dni_cliente) REFERENCES cliente(dni),
);*/

-- 9 ejecucion
/*CREATE TABLE turno_servicio (
    id_turno INT NOT NULL,
    id_servicio INT NOT NULL,
    PRIMARY KEY (id_turno, id_servicio),
    CONSTRAINT fk_turnoservicio_turno FOREIGN KEY (id_turno) REFERENCES turno(id_turno),
    CONSTRAINT fk_turnoservicio_servicio FOREIGN KEY (id_servicio) REFERENCES servicio(id_servicio)
);*/

-- Insertar en la tabla vehiculo
/*INSERT INTO vehiculo(marca, modelo, anio, dni_cliente)
VALUES
('Toyota', 'Hilux', 2024, 12345678),
('Toyota', 'Corolla', 2022, 12345678),
('Ford', 'Ranger', 2018, 31415926);*/

-- Insertar en la tabla servicio
/*INSERT INTO servicio(nombre, costo)
VALUES
('Cambio de Aceite', 15000),
('Servi Completo', 60000),
('Alineacion y Balanceo', 40000),
('Revision General', 25000);*/

-- Insertar en la tabla turno
/*INSERT INTO turno(id_vehiculo, dni_cliente)
VALUES
(1, 12345678),
(2, 12345678),
(3, 31415926);*/

-- Insertar en la tabla turno_servicio
/*INSERT INTO turno_servicio(id_turno, id_servicio)
VALUES
(1, 1),  -- Asocia el turno 1 con el servicio 1
(1, 3),  -- Asocia el turno 1 con el servicio 3
(2, 2),  -- Asocia el turno 2 con el servicio 2
(3, 4);  -- Asocia el turno 3 con el servicio 4*/

-- ver los vehiculos de los clientes
select c.dni, u.nombre, v.marca, v.modelo, v.anio from vehiculo v
left join cliente c on v.dni_cliente = c.dni
left join usuario u on c.dni = u.dni
where u.dni = 31415926;

-- Obtener Información Básica de Turnos
/*SELECT t.id_turno, t.fecha, v.marca, v.modelo, v.anio, u.nombre, u.apellido
FROM turno t
JOIN vehiculo v ON t.id_vehiculo = v.id_vehiculo
JOIN cliente c ON t.dni_cliente = c.dni
JOIN usuario u ON c.dni = u.dni;*/

-- Obtener Servicios Asociados a Cada Turno
/*SELECT t.id_turno, t.fecha, s.nombre AS servicio, s.costo
FROM turno t
JOIN turno_servicio ts ON t.id_turno = ts.id_turno
JOIN servicio s ON ts.id_servicio = s.id_servicio;*/

-- Obtener Información Completa de Turnos con Servicios
SELECT t.id_turno, t.fecha, v.marca, v.modelo, u.nombre, u.apellido, s.nombre AS servicio, s.costo
FROM turno t
JOIN turno_servicio ts ON t.id_turno = ts.id_turno
JOIN servicio s ON ts.id_servicio = s.id_servicio
JOIN vehiculo v ON t.id_vehiculo = v.id_vehiculo
JOIN cliente c ON t.dni_cliente = c.dni
JOIN usuario u ON c.dni = u.dni
where u.dni = 31415926;

-- Calcular el Costo Total de Servicios por Turno
/*SELECT t.id_turno, u.nombre, t.fecha, SUM(s.costo) AS costo_total
FROM turno t
JOIN cliente c on t.dni_cliente = c.dni
JOIN usuario u on c.dni = u.dni
JOIN turno_servicio ts ON t.id_turno = ts.id_turno
JOIN servicio s ON ts.id_servicio = s.id_servicio
GROUP BY t.id_turno, u.nombre, t.fecha;*/

-- prueba de eliminacion de clientes

-- aun no esta
/*CREATE PROCEDURE sp_EliminarCliente
    @dni_cliente BIGINT
AS
BEGIN
    -- Eliminar turnos asociados
    DELETE FROM turno_servicio
    WHERE id_turno IN (
        SELECT id_turno FROM turno
        WHERE dni_cliente = @dni_cliente
    );

    DELETE FROM turno
    WHERE dni_cliente = @dni_cliente;

    -- Eliminar vehículos asociados
    DELETE FROM vehiculo
    WHERE dni_cliente = @dni_cliente;

    -- Eliminar el cliente
    DELETE FROM cliente
    WHERE dni = @dni_cliente;
END;*/


-- Eliminar turnos asociados
    DELETE FROM turno_servicio
    WHERE id_turno IN (
        SELECT id_turno FROM turno
        WHERE dni_cliente = 31415926
    );

    DELETE FROM turno
    WHERE dni_cliente = 31415926;

    -- Eliminar vehículos asociados
    DELETE FROM vehiculo
    WHERE dni_cliente = 31415926;

	-- antes de eliminar el cliente le sacamos los roles posibles y le pongo vacio
	-- porque aun existira en tabla usuario
	UPDATE usuario SET rol = '' WHERE dni = 31415926;

    -- Eliminar el cliente
    DELETE FROM cliente
    WHERE dni = 31415926;



select * from usuario;
select * from cliente;



-- ************************************ SEDEERS ***********************************************

-- Seeder para la tabla Usuario
/*INSERT INTO usuario (dni, nombre, apellido, contrasena, correo, telefono)
VALUES
(12345678, 'Juan', 'Gomez', 'contraseña123', 'juan.gomez@ejemplo.com', '555-1234'),
(87654321, 'María', 'Fernández', 'abc456def', 'maria.fernandez@ejemplo.com', '555-5678'),
(46924236, 'Diego', 'Gomez', 'micontra123', 'diego.gomez@ejemplo.com', '555-9012'),
(24681012, 'Ana', 'Martínez', 'mno012pqr', 'ana.martinez@ejemplo.com', '555-3456'),
(46813579, 'Pedro', 'Sánchez', 'stu345vwx', 'pedro.sanchez@ejemplo.com', '555-7890'),
(97531246, 'Sofía', 'Díaz', 'yz456abc', 'sofia.diaz@ejemplo.com', '555-2468'),
(31415926, 'Miguel', 'Torres', 'def789ghi', 'miguel.torres@ejemplo.com', '555-6789'),
(65432109, 'Laura', 'Ramírez', 'jkl012mno', 'laura.ramirez@ejemplo.com', '555-0123'),
(98765432, 'David', 'Castillo', 'pqr345stu', 'david.castillo@ejemplo.com', '555-4567'),
(54321098, 'Alejandra', 'Herrera', 'vwx678yz', 'alejandra.herrera@ejemplo.com', '555-8901');*/

-- Seeder para la tabla Cliente
/*INSERT INTO cliente (dni, fechaIngreso, domicilio, RegimenLaboral)
VALUES
(12345678, GETDATE(), '123 Calle Principal', 'Exento'),
(87654321, GETDATE(), '456 Avenida del Robler', 'Responsable Inscripto'),
(24681012, GETDATE(), '789 Calle del Olmo', 'Monotributista'),
(46813579, GETDATE(), '321 Calle del Pino', 'Exento'),
(97531246, GETDATE(), '654 Bulevar del Maple', 'Monotributista');*/

-- Seeder para la tabla Mecanico
/*INSERT INTO mecanico (dni, fechaIngreso, sueldo)
VALUES
(46924236, GETDATE(), 50000.00),
(31415926, GETDATE(), 55000.00),
(65432109, GETDATE(), 60000.00);*/

-- ******************************************* MODIFICACION Y ACTUALIZACINO EN USUARIO ***************************************************************

-- Agrega la columna rol
-- ALTER TABLE usuario ADD rol VARCHAR(20) NOT NULL DEFAULT 'Cliente';

-- Actualizar roles de usuarios que son cliente
--UPDATE usuario SET rol = 'Cliente' WHERE dni IN (SELECT dni FROM cliente);

-- Actualizar roles de usuarios que son mecánicos
-- UPDATE usuario SET rol = 'Mecanico' WHERE dni IN (SELECT dni FROM mecanico);

-- agrego los clientes
/*INSERT INTO cliente(dni, fechaIngreso, domicilio, RegimenLaboral)
VALUES
(123456, GETDATE(), 'Direccion 1', 'Exento'),
(31415926, GETDATE(), 'Direccion 2', 'Monotributista'),
(46813579, GETDATE(), 'Direccion 3', 'Responsable Inscripto'),
(54321098, GETDATE(), 'Direccion 4', 'Responsable Inscripto'),
(98765432, GETDATE(), 'Direccion 5', 'Exento');*/


-- ******************************************** PROCEDIMIENTOS ALMACENADOS ********************************************************************
-- De la tabla MECANICO
-- esta
/*CREATE PROCEDURE agregar_mecanico
	@dni BIGINT,
	@nombre VARCHAR(100),
	@apellido VARCHAR(100),
    @contrasena VARCHAR(100),
    @correo VARCHAR(100),
	@telefono VARCHAR(100),
	@sueldo DECIMAL(10, 2)
AS
BEGIN
	INSERT INTO usuario(dni, nombre, apellido, contrasena, correo, telefono)
	VALUES(@dni, @nombre, @apellido, @contrasena, @correo, @telefono);
	INSERT INTO mecanico(dni, sueldo)
	VALUES(@dni, @sueldo);
END*/

-- esta
/*CREATE PROCEDURE listar_mecanicos
AS
BEGIN
	SELECT 
		u.dni,
		u.nombre,
		u.apellido,
		u.telefono,
		m.fechaIngreso,
		m.sueldo
	FROM mecanico m
	LEFT JOIN usuario u ON m.dni = u.dni
END*/

-- esta
/*CREATE PROCEDURE actualizar_mecanico
	@dni BIGINT,
	@contrasena VARCHAR(100),
	@telefono VARCHAR(100),
	@correo VARCHAR(100),
	@sueldo DECIMAL(10, 2)
AS
BEGIN
	UPDATE usuario SET contrasena = @contrasena, telefono = @telefono, correo = @correo WHERE dni = @dni;
	UPDATE mecanico SET sueldo = @sueldo WHERE dni = @dni;
END*/

-- esta
/*CREATE PROCEDURE buscar_mecanico
	@dni BIGINT
AS
BEGIN
	SELECT 
		u.dni,
		u.nombre,
		u.apellido,
		u.telefono,
		m.fechaIngreso,
		m.sueldo
	FROM mecanico m
	LEFT JOIN usuario u ON m.dni = u.dni
	WHERE m.dni = @dni
END*/

-- De la tabla CLIENTE
-- esta
/*CREATE PROCEDURE agregar_cliente
	@dni BIGINT,
	@nombre VARCHAR(100),
	@apellido VARCHAR(45),
    @contrasena VARCHAR(100),
    @correo VARCHAR(100),
	@telefono VARCHAR(100),
	@domicilio VARCHAR(100),
	@RegimenLaboral VARCHAR(100),
	@rol VARCHAR(20)
AS
BEGIN
	INSERT INTO usuario(dni, nombre, apellido, contrasena, correo, telefono, rol)
	VALUES(@dni, @nombre, @apellido, @contrasena, @correo, @telefono, @rol);
	INSERT INTO cliente(dni, domicilio, RegimenLaboral)
	VALUES(@dni, @domicilio, @RegimenLaboral);
END*/

-- esta (Del lado del mecanico)
/*CREATE PROCEDURE listar_clientes
AS
BEGIN
	select
		u.dni,
		u.nombre,
		u.apellido,
		u.contrasena,
		u.correo,
		u.telefono,
		c.fechaIngreso,
		c.domicilio,
		c.RegimenLaboral
	from cliente c
	left join usuario u on c.dni = u.dni
END*/

-- esta (Del lado del mecanico)
/*CREATE PROCEDURE buscar_cliente
	@dni BIGINT
AS
BEGIN
	SELECT
		u.dni,
		u.nombre,
		u.apellido,
		u.telefono,
		c.fechaIngreso,
		c.domicilio,
		c.RegimenLaboral AS 'regimen'
	FROM cliente c
	LEFT JOIN usuario u ON c.dni = u.dni
	WHERE c.dni = @dni
	ORDER BY u.nombre
END*/

-- esta (Del lado del cliente)
/*CREATE PROCEDURE ver_datos_del_cliente
	@dni BIGINT
AS
BEGIN
	SELECT
		u.dni,
		u.nombre,
		u.apellido,
		u.contrasena,
		u.correo,
		u.telefono,
		c.fechaIngreso,
		c.domicilio,
		c.RegimenLaboral AS 'regimen'
	FROM cliente c
	LEFT JOIN usuario u ON c.dni = u.dni
	WHERE c.dni = @dni
	ORDER BY u.nombre
END*/

-- esta (Del lado del cliente)
/*CREATE PROCEDURE actualizar_cliente
	@dni BIGINT,
	@contrasena VARCHAR(100),
	@telefono VARCHAR(100),
	@correo VARCHAR(100),
	@domicilio VARCHAR(100)
AS
BEGIN
	UPDATE usuario SET contrasena = @contrasena, telefono = @telefono, correo = @correo WHERE dni = @dni;
	UPDATE cliente SET domicilio = @domicilio WHERE dni = @dni;
END*/

-- De la tabla USUARIO
-- esta
/*CREATE PROCEDURE listar_usuarios
AS
BEGIN
	SELECT
		u.dni,
		u.nombre,
		u.apellido,
		u.contrasena,
		u.correo,
		u.telefono
	FROM usuario u
	LEFT JOIN cliente c ON u.dni = c.dni
	LEFT JOIN mecanico m ON u.dni = m.dni
	WHERE c.dni IS NULL AND m.dni IS NULL;
END*/

-- esta
/*CREATE PROCEDURE buscar_usuario
	@dni BIGINT
AS
BEGIN
	SELECT
		u.dni,
		u.nombre,
		u.apellido,
		u.contrasena,
		u.correo,
		u.telefono
	FROM usuario u
	LEFT JOIN cliente c ON u.dni = c.dni
	LEFT JOIN mecanico m ON u.dni = m.dni
	WHERE u.dni = @dni AND c.dni IS NULL AND m.dni IS NULL;
END*/

-- De la tabla VEHICULOS
-- esta -- usado
/*CREATE PROCEDURE agregar_vehiculo
	@marca VARCHAR(45),
	@modelo VARCHAR(45),
	@anio INT,
	@dni_cliente BIGINT
AS
BEGIN
	INSERT INTO vehiculo(marca, modelo, anio, dni_cliente)
	VALUES(@marca, @modelo, @anio, @dni_cliente)
END*/

-- esta -- usado
/*CREATE PROCEDURE actualizar_vehiculo
	@id_vehiculo INT,
	@marca VARCHAR(45),
	@modelo VARCHAR(45),
	@anio INT,
	@dni_cliente BIGINT
AS
BEGIN
	UPDATE vehiculo 
	SET 
	marca = @marca, 
	modelo = @modelo, 
	anio = @anio
	WHERE id_vehiculo = @id_vehiculo AND dni_cliente = @dni_cliente
END*/

-- esta -- usado
/*CREATE PROCEDURE buscar_vehiculo
	@id_vehiculo INT
AS
BEGIN
	SELECT 
		id_vehiculo,
		marca,
		modelo,
		anio,
		dni_cliente
	FROM vehiculo
	WHERE id_vehiculo = @id_vehiculo;
END*/

select * from vehiculo;

select * from vehiculo where id_vehiculo = 3 and dni_cliente = 12345678;

-- esta -- usado
/*CREATE PROCEDURE verificar_vehiculo_a_modificar
	@id_vehiculo INT,
	@dni_cliente BIGINT
AS
BEGIN
	SELECT 
		1
	FROM vehiculo
	WHERE id_vehiculo = @id_vehiculo AND dni_cliente = @dni_cliente;
END*/

-- esta -- usado
/*CREATE PROCEDURE listar_vehiculos_de_un_cliente
	@dni_cliente BIGINT
AS
BEGIN
	select
		v.id_vehiculo,
		v.marca,
		v.modelo,
		v.anio
	from vehiculo v
	join cliente c on v.dni_cliente = c.dni
	where c.dni = @dni_cliente;
END*/

select
		v.id_vehiculo,
		v.marca,
		v.modelo,
		v.anio
	from vehiculo v
	join cliente c on v.dni_cliente = c.dni
	where c.dni = 12345678;

	UPDATE vehiculo 
	SET 
	marca = 'Ford', 
	modelo = 'Ecosport', 
	anio = 2018
	WHERE id_vehiculo = 2 AND dni_cliente = 12345678

select * from usuario;


--	****************************************************** CONSULTAS ******************************************************************
-- listar todos los datos de las tablas
select * from usuario u left join mecanico m on u.dni = m.dni where rol = 'Mecanico' and sueldo >= 50000;
select * from cliente;

-- select * from vehiculo;

-- listar los mecanicos
select 
	u.dni,
	u.nombre,
	u.apellido,
	u.contrasena,
	u.correo,
	u.telefono,
	m.fechaIngreso,
	m.sueldo
from mecanico m
left join usuario u on m.dni = u.dni
order by m.sueldo desc;

-- listar los clientes
select
	u.dni,
	u.nombre,
	u.apellido,
	u.contrasena,
	u.correo,
	u.telefono,
	c.fechaIngreso,
	c.domicilio,
	c.RegimenLaboral
from cliente c
left join usuario u on c.dni = u.dni
order by u.nombre;

-- listar un cliente en especifico
SELECT u.dni, u.nombre, u.apellido, c.domicilio
FROM cliente c
LEFT JOIN usuario u ON c.dni = u.dni
WHERE c.dni = 234567890;

-- Listar todos los usuarios que no son ni clientes ni mecanicos
-- NO USUARIAMOS
/*SELECT
	u.dni,
	u.nombre,
	u.apellido,
	u.contrasena,
	u.correo,
	u.telefono,
	u.rol
FROM usuario u
LEFT JOIN cliente c ON u.dni = c.dni
LEFT JOIN mecanico m ON u.dni = m.dni
WHERE c.dni IS NULL AND m.dni IS NULL;*/

-- Buscar usuario que no es ni cliente ni mecanico
-- NO USARIAMOS
/*SELECT
	u.dni,
	u.nombre,
	u.apellido,
	u.contrasena,
	u.correo,
	u.telefono
FROM usuario u
LEFT JOIN cliente c ON u.dni = c.dni
LEFT JOIN mecanico m ON u.dni = m.dni
WHERE u.dni = 31415926 AND c.dni IS NULL AND m.dni IS NULL;*/

-- ******************************************** PRUEBA DE ELIMINACION DE DATOS **************************************************
--DELETE FROM cliente WHERE dni = 44745058;


-- ******************************************** ELIMINACION DE TABLAS y PROCEDIMIENTOS ****************************************************
/*DROP TABLE IF EXISTS administrador;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS usuario;*/

/*DROP PROCEDURE dbo.actualizar_administrador;
DROP PROCEDURE dbo.actualizar_cliente;
DROP PROCEDURE dbo.agregar_administrador;
DROP PROCEDURE dbo.agregar_cliente;
DROP PROCEDURE dbo.buscar_administrador;
DROP PROCEDURE dbo.buscar_cliente;
DROP PROCEDURE dbo.buscar_usuario;
DROP PROCEDURE dbo.listar_administradores;
DROP PROCEDURE dbo.listar_clientes;
DROP PROCEDURE dbo.listar_usuarios;*/



























-- UPDATE usuario SET contrasena = @n_contrasena, telefono = @n_telefono WHERE id_usuario = @n_id_usuario



/*
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
-- INSERT INTO administrador (area_trabajo, id_usuario) VALUES ('gerente', 2);

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
*/

-- *********************************************************** CREACION DE PROCEDIMIENTOS ALMACENADOS ***********************************************************

/*CREATE PROCEDURE agregar_cliente
	@domicilio VARCHAR(100),
	@tipoRegimenLaboral VARCHAR(100),
	@id_usuario INT
AS
BEGIN
	INSERT INTO cliente(domicilio, tipoRegimenLaboral, id_usuario)
	VALUES(@domicilio, @tipoRegimenLaboral, @id_usuario)	
END*/

/*CREATE PROCEDURE agregar_administrador
	@area_trabajo VARCHAR(100),
	@estado VARCHAR(100),
	@id_usuario INT
AS
BEGIN
	INSERT INTO administrador(area_trabajo, estado, id_usuario)
	VALUES(@area_trabajo, @estado, @id_usuario)
END*/

/*CREATE PROCEDURE actualizar_usuario_cliente
	@n_id_usuario INT,
	@n_contrasena VARCHAR(100),
	@n_telefono VARCHAR(100)
AS
BEGIN
	UPDATE usuario SET contrasena = @n_contrasena, telefono = @n_telefono WHERE id_usuario = @n_id_usuario
END*/


/*CREATE PROCEDURE listar_clientes
AS
BEGIN
	SELECT 
		u.nombre,
		u.apellido, 
		u.correo, 
		u.telefono,
		c.domicilio,
		c.tipoRegimenLaboral
	FROM cliente c
	LEFT JOIN usuario u ON c.id_usuario = u.id_usuario
	ORDER BY u.nombre;
END*/

/*CREATE PROCEDURE listar_administrador
AS
BEGIN
	-- Datos que queremos mostrar
	SELECT 
		u.nombre,
		u.apellido, 
		u.correo, 
		u.telefono,
		a.area_trabajo,
		a.estado
	FROM administrador a
	LEFT JOIN usuario u ON a.id_administrador = u.id_usuario
	ORDER BY a.estado;
END*/

/*CREATE PROCEDURE listar_turno
AS
BEGIN
    SELECT 
		-- datos de turno
        t.id_turno,
		t.fecha, 
		t.costo_total, 
		t.estado,
		-- datos de cliente
        c.id_cliente,
		-- datos de usuario
        u.nombre, 
		u.apellido,
		-- datos de vehiculo
        v.id_vehiculo, 
		v.marca, 
		v.modelo,
		-- datos de servicio
        s.id_servicio, 
		s.nombre AS nombre_servicio
    FROM turno t
    INNER JOIN cliente c ON t.id_cliente = c.id_cliente
    INNER JOIN usuario u ON c.id_usuario = u.id_usuario
    INNER JOIN vehiculo v ON t.id_vehiculo = v.id_vehiculo
    INNER JOIN servicio s ON t.id_servicio = s.id_servicio
    ORDER BY t.fecha DESC
END*/

/*CREATE PROCEDURE mostrar_turno_de_cliente
	@n_id_cliente INT
AS
BEGIN
	SELECT 
		c.id_cliente AS 'ID_CLIENTE',
		u.nombre AS 'NOMBRE',
		t.fecha AS 'FECHA_TURNO',
		v.marca AS 'MARCA',
		v.modelo AS 'MODELO',
		s.nombre AS 'SERVICIO_SOLICITADO',
		s.costo AS 'PRECIO'
	FROM turno t
	LEFT JOIN cliente c ON c.id_cliente = t.id_cliente
	LEFT JOIN usuario u ON c.id_usuario = u.id_usuario
	LEFT JOIN vehiculo v ON t.id_vehiculo = v.id_vehiculo
	LEFT JOIN servicio s ON t.id_servicio = s.id_servicio
	WHERE c.id_cliente =  @n_id_cliente
	ORDER BY s.costo DESC;
END*/

/*CREATE PROCEDURE buscar_cliente
	@n_id_cliente INT
AS
BEGIN
	SELECT 
		-- Datos que queremos extraer
		u.id_usuario,
		c.id_cliente,
		u.nombre,
		u.correo,
		c.domicilio,
		c.tipoRegimenLaboral
	FROM cliente c
	LEFT JOIN usuario u ON c.id_usuario = u.id_usuario
	WHERE c.id_cliente = @n_id_cliente
END*/



/*CREATE PROCEDURE eliminar_cliente
    @id_usuario INT
AS
BEGIN
    DELETE FROM cliente WHERE id_usuario = @id_usuario;
    DELETE FROM usuario WHERE id_usuario = @id_usuario;
END;*/


-- *********************************************************************************************************************************************************

-- CONSULTAS AVANZADAS
-- Listar todos los turnos
/*SELECT 
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
ORDER BY s.costo DESC;*/

-- Ver cuántos turnos tiene cada cliente:
/*SELECT 
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
ORDER BY c.id_cliente;*/

-- ver los turnos de un cliente en especifico
/*SELECT 
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
ORDER BY t.costo_total DESC;*/



-- listar turnos de un cliente en especifico
/*SELECT 
	c.id_cliente AS 'ID_CLIENTE',
	u.nombre AS 'NOMBRE',
	t.fecha AS 'FECHA_TURNO',
	CONCAT(v.marca, ' ', v.modelo) AS 'VEHICULO',
	s.nombre AS 'SERVICIO_SOLICITADO',
	s.costo AS 'PRECIO'
FROM turno t
LEFT JOIN cliente c ON c.id_cliente = t.id_cliente
LEFT JOIN usuario u ON c.id_usuario = u.id_usuario
LEFT JOIN vehiculo v ON t.id_vehiculo = v.id_vehiculo
LEFT JOIN servicio s ON t.id_servicio = s.id_servicio
WHERE c.id_cliente = 1
ORDER BY s.costo DESC;*/



/*SELECT u.id_usuario, u.nombre, u.apellido, u.correo, u.telefono
FROM usuario u
LEFT JOIN cliente c ON u.id_usuario = c.id_usuario
LEFT JOIN administrador a ON u.id_usuario = a.id_usuario
WHERE c.id_cliente IS NULL 
AND a.id_administrador IS NULL;*/

--select * from cliente;
--select * from usuario;

-- listar los clientes
/*SELECT u.id_usuario AS id_usuario, c.id_cliente AS id_cliente, u.nombre AS nombre, u.telefono AS telefono, c.domicilio AS domicilio, c.tipoRegimenLaboral AS regimen
FROM cliente c
LEFT JOIN usuario u ON u.id_usuario = c.id_usuario*/

-- listar los administradores
/*SELECT u.id_usuario AS id_usuario, a.id_administrador AS id_admin, u.nombre AS nombre, u.correo AS correo, a.area_trabajo AS area, a.estado AS estado
FROM administrador a
LEFT JOIN usuario u ON a.id_usuario = u.id_usuario*/

/*INSERT INTO usuario(nombre, apellido, contrasena, correo, telefono) 
VALUES('Valentina', 'Gomez', 'micontra2016', 'micorreo2016@gmail.com', '123456')
INSERT INTO administrador(area_trabajo, estado, id_usuario)
VALUES('Director', 'NO ACTIVO', 12)*/

/*SELECT * FROM usuario;
SELECT * FROM cliente;
SELECT * FROM administrador;
SELECT * FROM servicio;
SELECT * FROM vehiculo;
SELECT * FROM turno;*/

-- ELIMINACION DE TABLAS
/*DROP TABLE IF EXISTS turno;
DROP TABLE IF EXISTS servicio;
DROP TABLE IF EXISTS vehiculo;
DROP TABLE IF EXISTS administrador;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS usuario;*/

-- ELIMINACION DE PROCEDIMIENTOS ALMACENADOS
/*DROP PROCEDURE IF EXISTS dbo.actualizar_usuario_cliente;
DROP PROCEDURE IF EXISTS dbo.agregar_administrador;
DROP PROCEDURE IF EXISTS dbo.agregar_cliente;
DROP PROCEDURE IF EXISTS dbo.buscar_cliente;
DROP PROCEDURE IF EXISTS dbo.eliminar_cliente;
DROP PROCEDURE IF EXISTS dbo.listar_administrador;
DROP PROCEDURE IF EXISTS dbo.listar_clientes;
DROP PROCEDURE IF EXISTS dbo.listar_turno;
DROP PROCEDURE IF EXISTS dbo.mostrar_turno_de_cliente;*/

