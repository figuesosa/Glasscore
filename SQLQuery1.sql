/* ============================================================
   SISTEMA GLASSCORE
   Base de datos: db_glasscore_management
   Motor: SQL Server
   ============================================================ */

IF DB_ID('db_glasscore_management') IS NOT NULL
BEGIN
    ALTER DATABASE db_glasscore_management SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE db_glasscore_management;
END;
GO

CREATE DATABASE db_glasscore_management;
GO

USE db_glasscore_management;
GO

/*MÓDULO A: PERSONAL Y SEGURIDAD*/

CREATE TABLE roles (
    id_rol INT IDENTITY(1,1) PRIMARY KEY,
    nombre_rol VARCHAR(50) NOT NULL UNIQUE
);
GO

CREATE TABLE empleados (
    id_empleado INT IDENTITY(1,1) PRIMARY KEY,
    id_rol INT NOT NULL,
    identidad VARCHAR(20) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    salario_base DECIMAL(10,2) NOT NULL,
    estado VARCHAR(20) NOT NULL,

    CONSTRAINT FK_empleados_roles
        FOREIGN KEY (id_rol) REFERENCES roles(id_rol),

    CONSTRAINT CK_empleados_salario_base
        CHECK (salario_base >= 0),

    CONSTRAINT CK_empleados_estado
        CHECK (estado IN ('Activo', 'Inactivo'))
);
GO

CREATE TABLE usuarios (
    id_usuario INT IDENTITY(1,1) PRIMARY KEY,
    id_empleado INT NOT NULL UNIQUE,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,

    CONSTRAINT FK_usuarios_empleados
        FOREIGN KEY (id_empleado) REFERENCES empleados(id_empleado)
);
GO

/*MÓDULO B: INVENTARIO Y ACTIVOS LOGÍSTICOS*/

CREATE TABLE productos (
    id_producto INT IDENTITY(1,1) PRIMARY KEY,
    descripcion VARCHAR(150) NOT NULL,
    unidad_medida VARCHAR(20) NOT NULL,
    stock_actual DECIMAL(10,2) NOT NULL,
    precio_venta DECIMAL(10,2) NOT NULL,

    CONSTRAINT CK_productos_stock_actual
        CHECK (stock_actual >= 0),

    CONSTRAINT CK_productos_precio_venta
        CHECK (precio_venta >= 0)
);
GO

CREATE TABLE herramientas (
    id_herramienta INT IDENTITY(1,1) PRIMARY KEY,
    codigo_activo VARCHAR(50) NOT NULL UNIQUE,
    nombre_herramienta VARCHAR(100) NOT NULL,
    estado_conservacion VARCHAR(50) NOT NULL,
    disponibilidad VARCHAR(20) NOT NULL,

    CONSTRAINT CK_herramientas_estado_conservacion
        CHECK (estado_conservacion IN ('Excelente', 'Regular', 'En Mantenimiento')),

    CONSTRAINT CK_herramientas_disponibilidad
        CHECK (disponibilidad IN ('Disponible', 'Asignada'))
);
GO

CREATE TABLE asignacion_herramientas (
    id_asignacion INT IDENTITY(1,1) PRIMARY KEY,
    id_herramienta INT NOT NULL,
    id_empleado INT NOT NULL,
    fecha_salida DATETIME NOT NULL,
    fecha_devolucion_real DATETIME NULL,
    observaciones TEXT NULL,

    CONSTRAINT FK_asignacion_herramientas_herramientas
        FOREIGN KEY (id_herramienta) REFERENCES herramientas(id_herramienta),

    CONSTRAINT FK_asignacion_herramientas_empleados
        FOREIGN KEY (id_empleado) REFERENCES empleados(id_empleado)
);
GO

/*MÓDULO C: OPERACIONES Y FINANZAS*/

CREATE TABLE planillas (
    id_planilla INT IDENTITY(1,1) PRIMARY KEY,
    id_empleado INT NOT NULL,
    fecha_pago DATE NOT NULL,
    salario_base DECIMAL(10,2) NOT NULL,
    monto_horas_extras DECIMAL(10,2) NOT NULL,
    monto_viaticos DECIMAL(10,2) NOT NULL,
    total_neto DECIMAL(10,2) NOT NULL,

    CONSTRAINT FK_planillas_empleados
        FOREIGN KEY (id_empleado) REFERENCES empleados(id_empleado),

    CONSTRAINT CK_planillas_salario_base
        CHECK (salario_base >= 0),

    CONSTRAINT CK_planillas_horas_extras
        CHECK (monto_horas_extras >= 0),

    CONSTRAINT CK_planillas_viaticos
        CHECK (monto_viaticos >= 0),

    CONSTRAINT CK_planillas_total_neto
        CHECK (total_neto >= 0)
);
GO

/*DATOS INICIALES RECOMENDADOS*/

INSERT INTO roles (nombre_rol)
VALUES 
('Administrador'),
('Vendedor'),
('Instalador');
GO

INSERT INTO empleados 
(id_rol, identidad, nombre, apellido, telefono, correo, salario_base, estado)
VALUES
(1, '0801199900010', 'Carlos', 'Ramírez', '9999-1111', 'carlos.ramirez@glasscore.com', 18000.00, 'Activo'),
(2, '0801199800020', 'María', 'López', '9999-2222', 'maria.lopez@glasscore.com', 15000.00, 'Activo'),
(3, '0801200000030', 'José', 'Martínez', '9999-3333', 'jose.martinez@glasscore.com', 14000.00, 'Activo');
GO

INSERT INTO usuarios
(id_empleado, username, password_hash)
VALUES
(1, 'admin', 'admin123_hash'),
(2, 'vendedor', 'vendedor123_hash');
GO

INSERT INTO productos
(descripcion, unidad_medida, stock_actual, precio_venta)
VALUES
('Vidrio Claro 6mm', 'M2', 120.00, 950.00),
('Aluminio Comercial', 'ML', 300.00, 180.00),
('Herraje para Ventana', 'Pieza', 200.00, 75.00);
GO

INSERT INTO herramientas
(codigo_activo, nombre_herramienta, estado_conservacion, disponibilidad)
VALUES
('HER-001', 'Ventosa de Succión Triple', 'Excelente', 'Disponible'),
('HER-002', 'Cortador de Vidrio Profesional', 'Regular', 'Disponible'),
('HER-003', 'Taladro Industrial', 'Excelente', 'Disponible');
GO

/*VISTAS ÚTILES PARA JTABLE / INNER JOIN*/

CREATE VIEW vw_empleados_roles AS
SELECT 
    e.id_empleado,
    e.identidad,
    e.nombre,
    e.apellido,
    e.telefono,
    e.correo,
    e.salario_base,
    e.estado,
    r.nombre_rol
FROM empleados e
INNER JOIN roles r ON e.id_rol = r.id_rol;
GO

CREATE VIEW vw_usuarios_empleados AS
SELECT
    u.id_usuario,
    u.username,
    e.id_empleado,
    e.nombre,
    e.apellido,
    e.correo,
    r.nombre_rol
FROM usuarios u
INNER JOIN empleados e ON u.id_empleado = e.id_empleado
INNER JOIN roles r ON e.id_rol = r.id_rol;
GO

CREATE VIEW vw_herramientas_asignadas AS
SELECT
    ah.id_asignacion,
    h.id_herramienta,
    h.codigo_activo,
    h.nombre_herramienta,
    h.estado_conservacion,
    h.disponibilidad,
    e.id_empleado,
    e.nombre + ' ' + e.apellido AS empleado_asignado,
    ah.fecha_salida,
    ah.fecha_devolucion_real,
    ah.observaciones
FROM asignacion_herramientas ah
INNER JOIN herramientas h ON ah.id_herramienta = h.id_herramienta
INNER JOIN empleados e ON ah.id_empleado = e.id_empleado;
GO

CREATE VIEW vw_planillas_empleados AS
SELECT
    p.id_planilla,
    e.id_empleado,
    e.nombre + ' ' + e.apellido AS empleado,
    p.fecha_pago,
    p.salario_base,
    p.monto_horas_extras,
    p.monto_viaticos,
    p.total_neto
FROM planillas p
INNER JOIN empleados e ON p.id_empleado = e.id_empleado;
GO

/*PROCEDIMIENTOS OPCIONALES PARA ASIGNAR Y DEVOLVER HERRAMIENTAS*/

CREATE PROCEDURE sp_asignar_herramienta
    @id_herramienta INT,
    @id_empleado INT,
    @observaciones TEXT = NULL
AS
BEGIN
    SET NOCOUNT ON;

    IF NOT EXISTS (
        SELECT 1 FROM herramientas 
        WHERE id_herramienta = @id_herramienta 
        AND disponibilidad = 'Disponible'
    )
    BEGIN
        RAISERROR('La herramienta no existe o no está disponible.', 16, 1);
        RETURN;
    END;

    INSERT INTO asignacion_herramientas
    (id_herramienta, id_empleado, fecha_salida, fecha_devolucion_real, observaciones)
    VALUES
    (@id_herramienta, @id_empleado, GETDATE(), NULL, @observaciones);

    UPDATE herramientas
    SET disponibilidad = 'Asignada'
    WHERE id_herramienta = @id_herramienta;
END;
GO

CREATE PROCEDURE sp_devolver_herramienta
    @id_asignacion INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @id_herramienta INT;

    SELECT @id_herramienta = id_herramienta
    FROM asignacion_herramientas
    WHERE id_asignacion = @id_asignacion
    AND fecha_devolucion_real IS NULL;

    IF @id_herramienta IS NULL
    BEGIN
        RAISERROR('La asignación no existe o ya fue devuelta.', 16, 1);
        RETURN;
    END;

    UPDATE asignacion_herramientas
    SET fecha_devolucion_real = GETDATE()
    WHERE id_asignacion = @id_asignacion;

    UPDATE herramientas
    SET disponibilidad = 'Disponible'
    WHERE id_herramienta = @id_herramienta;
END;
GO

/* ============================================================
   CONSULTAS DE PRUEBA
   ============================================================ */

SELECT * FROM roles;
SELECT * FROM empleados;
SELECT * FROM usuarios;
SELECT * FROM productos;
SELECT * FROM herramientas;
SELECT * FROM vw_empleados_roles;
SELECT * FROM vw_usuarios_empleados;
SELECT * FROM vw_herramientas_asignadas;
SELECT * FROM vw_planillas_empleados;
GO

CREATE TABLE cotizaciones (
    id_cotizacion INT IDENTITY(1,1) PRIMARY KEY,
    fecha DATETIME DEFAULT GETDATE(),
    ancho DECIMAL(10,2),
    alto DECIMAL(10,2),
    metros_cuadrados DECIMAL(10,2),
    metros_lineales DECIMAL(10,2),
    costo_vidrio DECIMAL(10,2),
    costo_aluminio DECIMAL(10,2),
    total DECIMAL(10,2)
);


/*Usuario y contrasena*/

USE master;
GO

IF NOT EXISTS (SELECT 1 FROM sys.server_principals WHERE name = 'glasscore_user')
BEGIN
    CREATE LOGIN glasscore_user
    WITH PASSWORD = 'Glasscore123',
         CHECK_POLICY = OFF,
         CHECK_EXPIRATION = OFF;
END
ELSE
BEGIN
    ALTER LOGIN glasscore_user
    WITH PASSWORD = 'Glasscore123',
         CHECK_POLICY = OFF,
         CHECK_EXPIRATION = OFF;
    ALTER LOGIN glasscore_user ENABLE;
END;
GO

USE db_glasscore_management;
GO

IF NOT EXISTS (SELECT 1 FROM sys.database_principals WHERE name = 'glasscore_user')
BEGIN
    CREATE USER glasscore_user FOR LOGIN glasscore_user;
    ALTER ROLE db_owner ADD MEMBER glasscore_user;
END;
GO