-- ============================================================
-- Universidad Politécnica de Francisco I. Madero
-- Carrera: Ingeniería en Sistemas Computacionales
-- Proyecto: ERP de Venta de Motos
-- Fecha: Junio 2026
-- Objetivo: Crear la base de datos, tablas y datos de prueba
-- Módulos: Ventas, Clientes, Finanzas, Inventario, Empleados
-- ============================================================

DROP DATABASE IF EXISTS erp_motos_db;
CREATE DATABASE erp_motos_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE erp_motos_db;

-- ============================================================
-- MÓDULO: EMPLEADOS
-- ============================================================

CREATE TABLE roles_empleado (
    id_rol_PK       INT AUTO_INCREMENT PRIMARY KEY,
    nombre          VARCHAR(60)  NOT NULL,
    descripcion     TEXT,
    fecha_creacion  DATE         NOT NULL DEFAULT (CURRENT_DATE)
);

CREATE TABLE empleados (
    id_empleado_PK  INT AUTO_INCREMENT PRIMARY KEY,
    id_rol_FK       INT          NOT NULL,
    nombre          VARCHAR(80)  NOT NULL,
    apellido_paterno VARCHAR(60) NOT NULL,
    apellido_materno VARCHAR(60),
    email           VARCHAR(120) NOT NULL UNIQUE,
    password_hash   VARCHAR(255) NOT NULL,
    telefono        VARCHAR(20),
    rfc             VARCHAR(20),
    direccion       TEXT,
    salario         DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    fecha_contrato  DATE         NOT NULL,
    activo          TINYINT(1)   NOT NULL DEFAULT 1,
    CONSTRAINT fk_emp_rol FOREIGN KEY (id_rol_FK) REFERENCES roles_empleado(id_rol_PK)
);

-- ============================================================
-- MÓDULO: CLIENTES
-- ============================================================

CREATE TABLE clientes (
    id_cliente_PK   INT AUTO_INCREMENT PRIMARY KEY,
    nombre          VARCHAR(80)  NOT NULL,
    apellido_paterno VARCHAR(60) NOT NULL,
    apellido_materno VARCHAR(60),
    email           VARCHAR(120) NOT NULL UNIQUE,
    telefono        VARCHAR(20),
    rfc             VARCHAR(20),
    fecha_nacimiento DATE,
    fecha_registro  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    activo          TINYINT(1)   NOT NULL DEFAULT 1
);

CREATE TABLE direcciones_cliente (
    id_direccion_PK INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente_FK   INT          NOT NULL,
    calle           VARCHAR(150) NOT NULL,
    numero_ext      VARCHAR(20),
    numero_int      VARCHAR(20),
    colonia         VARCHAR(100),
    municipio       VARCHAR(100),
    estado          VARCHAR(80)  NOT NULL,
    cp              VARCHAR(10)  NOT NULL,
    referencias     TEXT,
    es_principal    TINYINT(1)   NOT NULL DEFAULT 0,
    CONSTRAINT fk_dir_cliente FOREIGN KEY (id_cliente_FK) REFERENCES clientes(id_cliente_PK)
);

-- ============================================================
-- MÓDULO: INVENTARIO
-- ============================================================

CREATE TABLE marcas (
    id_marca_PK     INT AUTO_INCREMENT PRIMARY KEY,
    nombre          VARCHAR(80)  NOT NULL UNIQUE,
    pais_origen     VARCHAR(60),
    descripcion     TEXT
);

CREATE TABLE categorias_moto (
    id_categoria_PK INT AUTO_INCREMENT PRIMARY KEY,
    nombre          VARCHAR(80)  NOT NULL,
    descripcion     TEXT
);

CREATE TABLE motos (
    id_moto_PK      INT AUTO_INCREMENT PRIMARY KEY,
    id_marca_FK     INT          NOT NULL,
    id_categoria_FK INT          NOT NULL,
    modelo          VARCHAR(100) NOT NULL,
    anio            YEAR         NOT NULL,
    cilindraje      INT,
    color           VARCHAR(50),
    precio_costo    DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    precio_venta    DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    stock           INT          NOT NULL DEFAULT 0,
    num_serie       VARCHAR(80)  UNIQUE,
    imagen_url      VARCHAR(255),
    descripcion     TEXT,
    activo          TINYINT(1)   NOT NULL DEFAULT 1,
    fecha_alta      DATE         NOT NULL DEFAULT (CURRENT_DATE),
    CONSTRAINT fk_moto_marca     FOREIGN KEY (id_marca_FK)     REFERENCES marcas(id_marca_PK),
    CONSTRAINT fk_moto_categoria FOREIGN KEY (id_categoria_FK) REFERENCES categorias_moto(id_categoria_PK)
);

CREATE TABLE accesorios (
    id_accesorio_PK INT AUTO_INCREMENT PRIMARY KEY,
    nombre          VARCHAR(120) NOT NULL,
    descripcion     TEXT,
    precio_costo    DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    precio_venta    DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    stock           INT          NOT NULL DEFAULT 0,
    categoria       VARCHAR(60),
    imagen_url      VARCHAR(255),
    activo          TINYINT(1)   NOT NULL DEFAULT 1
);

CREATE TABLE movimientos_inventario (
    id_movimiento_PK  INT AUTO_INCREMENT PRIMARY KEY,
    tipo_producto     ENUM('moto','accesorio') NOT NULL,
    id_producto_ref   INT          NOT NULL,
    id_empleado_FK    INT          NOT NULL,
    tipo_movimiento   ENUM('entrada','salida','ajuste') NOT NULL,
    cantidad          INT          NOT NULL,
    motivo            TEXT,
    fecha_movimiento  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_mov_empleado FOREIGN KEY (id_empleado_FK) REFERENCES empleados(id_empleado_PK)
);

-- ============================================================
-- MÓDULO: VENTAS
-- ============================================================

CREATE TABLE ventas (
    id_venta_PK     INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente_FK   INT          NOT NULL,
    id_empleado_FK  INT          NOT NULL,
    folio           VARCHAR(30)  NOT NULL UNIQUE,
    subtotal        DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    descuento       DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    iva             DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    total           DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    metodo_pago     ENUM('contado','credito','transferencia','tarjeta') NOT NULL,
    estado          ENUM('pendiente','completada','cancelada') NOT NULL DEFAULT 'pendiente',
    observaciones   TEXT,
    fecha_venta     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_venta_cliente  FOREIGN KEY (id_cliente_FK)  REFERENCES clientes(id_cliente_PK),
    CONSTRAINT fk_venta_empleado FOREIGN KEY (id_empleado_FK) REFERENCES empleados(id_empleado_PK)
);

CREATE TABLE detalle_ventas (
    id_detalle_PK   INT AUTO_INCREMENT PRIMARY KEY,
    id_venta_FK     INT          NOT NULL,
    tipo_producto   ENUM('moto','accesorio') NOT NULL,
    id_producto_ref INT          NOT NULL,
    cantidad        INT          NOT NULL DEFAULT 1,
    precio_unitario DECIMAL(12,2) NOT NULL,
    descuento_item  DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    subtotal_item   DECIMAL(12,2) NOT NULL,
    CONSTRAINT fk_det_venta FOREIGN KEY (id_venta_FK) REFERENCES ventas(id_venta_PK)
);

CREATE TABLE cotizaciones (
    id_cotizacion_PK INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente_FK    INT          NOT NULL,
    id_empleado_FK   INT          NOT NULL,
    folio_cotizacion VARCHAR(30)  NOT NULL UNIQUE,
    total            DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    estado           ENUM('vigente','aceptada','vencida','rechazada') NOT NULL DEFAULT 'vigente',
    vigencia_dias    INT          NOT NULL DEFAULT 15,
    notas            TEXT,
    fecha_cotizacion DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_cot_cliente  FOREIGN KEY (id_cliente_FK)  REFERENCES clientes(id_cliente_PK),
    CONSTRAINT fk_cot_empleado FOREIGN KEY (id_empleado_FK) REFERENCES empleados(id_empleado_PK)
);

CREATE TABLE detalle_cotizaciones (
    id_det_cot_PK   INT AUTO_INCREMENT PRIMARY KEY,
    id_cotizacion_FK INT         NOT NULL,
    tipo_producto   ENUM('moto','accesorio') NOT NULL,
    id_producto_ref INT          NOT NULL,
    cantidad        INT          NOT NULL DEFAULT 1,
    precio_unitario DECIMAL(12,2) NOT NULL,
    subtotal_item   DECIMAL(12,2) NOT NULL,
    CONSTRAINT fk_det_cot FOREIGN KEY (id_cotizacion_FK) REFERENCES cotizaciones(id_cotizacion_PK)
);

-- ============================================================
-- MÓDULO: FINANZAS
-- ============================================================

CREATE TABLE cuentas_financieras (
    id_cuenta_PK    INT AUTO_INCREMENT PRIMARY KEY,
    nombre          VARCHAR(120) NOT NULL,
    tipo            ENUM('banco','caja','credito') NOT NULL,
    saldo           DECIMAL(14,2) NOT NULL DEFAULT 0.00,
    descripcion     TEXT,
    activo          TINYINT(1)   NOT NULL DEFAULT 1
);

CREATE TABLE ingresos (
    id_ingreso_PK   INT AUTO_INCREMENT PRIMARY KEY,
    id_cuenta_FK    INT          NOT NULL,
    id_venta_FK     INT,
    concepto        VARCHAR(200) NOT NULL,
    monto           DECIMAL(12,2) NOT NULL,
    tipo_ingreso    ENUM('venta','abono','otro') NOT NULL DEFAULT 'venta',
    comprobante     VARCHAR(255),
    fecha_ingreso   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_ing_cuenta FOREIGN KEY (id_cuenta_FK) REFERENCES cuentas_financieras(id_cuenta_PK),
    CONSTRAINT fk_ing_venta  FOREIGN KEY (id_venta_FK)  REFERENCES ventas(id_venta_PK)
);

CREATE TABLE egresos (
    id_egreso_PK    INT AUTO_INCREMENT PRIMARY KEY,
    id_cuenta_FK    INT          NOT NULL,
    id_empleado_FK  INT,
    concepto        VARCHAR(200) NOT NULL,
    monto           DECIMAL(12,2) NOT NULL,
    tipo_egreso     ENUM('nomina','compra','servicio','otro') NOT NULL DEFAULT 'otro',
    comprobante     VARCHAR(255),
    fecha_egreso    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_eg_cuenta   FOREIGN KEY (id_cuenta_FK)   REFERENCES cuentas_financieras(id_cuenta_PK),
    CONSTRAINT fk_eg_empleado FOREIGN KEY (id_empleado_FK) REFERENCES empleados(id_empleado_PK)
);

CREATE TABLE creditos_cliente (
    id_credito_PK   INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente_FK   INT          NOT NULL,
    id_venta_FK     INT          NOT NULL,
    id_empleado_FK  INT          NOT NULL,
    monto_total     DECIMAL(12,2) NOT NULL,
    monto_pagado    DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    saldo_pendiente DECIMAL(12,2) NOT NULL,
    tasa_interes    DECIMAL(5,2)  NOT NULL DEFAULT 0.00,
    num_pagos       INT          NOT NULL DEFAULT 1,
    periodicidad    ENUM('semanal','quincenal','mensual') NOT NULL DEFAULT 'mensual',
    estado          ENUM('activo','liquidado','vencido') NOT NULL DEFAULT 'activo',
    fecha_inicio    DATE         NOT NULL,
    fecha_limite    DATE         NOT NULL,
    CONSTRAINT fk_cred_cliente  FOREIGN KEY (id_cliente_FK)  REFERENCES clientes(id_cliente_PK),
    CONSTRAINT fk_cred_venta    FOREIGN KEY (id_venta_FK)    REFERENCES ventas(id_venta_PK),
    CONSTRAINT fk_cred_empleado FOREIGN KEY (id_empleado_FK) REFERENCES empleados(id_empleado_PK)
);

CREATE TABLE pagos_credito (
    id_pago_PK      INT AUTO_INCREMENT PRIMARY KEY,
    id_credito_FK   INT          NOT NULL,
    id_cuenta_FK    INT          NOT NULL,
    monto_pago      DECIMAL(12,2) NOT NULL,
    num_pago        INT          NOT NULL,
    observaciones   TEXT,
    fecha_pago      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_pago_credito FOREIGN KEY (id_credito_FK) REFERENCES creditos_cliente(id_credito_PK),
    CONSTRAINT fk_pago_cuenta  FOREIGN KEY (id_cuenta_FK)  REFERENCES cuentas_financieras(id_cuenta_PK)
);

-- ============================================================

-- ============================================================
-- ============================================================
-- DATOS DE PRUEBA
-- (Orden respetando dependencias de llaves foráneas)
-- ============================================================

-- ── Roles ─────────────────────────────────────────────────
INSERT INTO roles_empleado (nombre, descripcion) VALUES
('Administrador', 'Acceso total al sistema ERP'),
('Vendedor',      'Gestión de ventas, clientes y cotizaciones'),
('Contador',      'Acceso al módulo de finanzas'),
('Almacenista',   'Gestión de inventario y movimientos');

-- ── Empleados ─────────────────────────────────────────────
-- Contraseña de todos: admin123 / vendedor123

-- ── Empleados ─────────────────────────────────────────────
INSERT INTO empleados (id_rol_FK, nombre, apellido_paterno, apellido_materno, email, password_hash, telefono, rfc, direccion, salario, fecha_contrato) VALUES
(1, 'Carlos',    'Ramírez',    'López',     'admin@erpmotos.com',  'admin123',    '7711111111', 'RALC900101AA1', 'Av. Juárez 10, Pachuca, Hidalgo',       25000.00, '2024-01-10'),
(2, 'María',     'Hernández',  'Pérez',     'maria@erpmotos.com',  'vendedor123', '7712222222', 'HEPM920202BB2', 'Morelos 55, Pachuca, Hidalgo',          12000.00, '2024-03-15'),
(2, 'Luis',      'García',     'Santos',    'luis@erpmotos.com',   'vendedor123', '7713333333', 'GASL930303CC3', 'Reforma 90, Tulancingo, Hidalgo',       12000.00, '2024-04-20'),
(3, 'Patricia',  'Torres',     'Mendoza',   'patricia@erpmotos.com','vendedor123','7714444444', 'TOMP950404DD4', 'Hidalgo 22, Actopan, Hidalgo',          14500.00, '2024-05-01'),
(4, 'Roberto',   'Díaz',       'Fuentes',   'roberto@erpmotos.com', 'vendedor123','7715555555', 'DIFR880505EE5', 'Insurgentes 300, Tizayuca, Hidalgo',    11000.00, '2024-06-10');

-- ── Marcas ────────────────────────────────────────────────

-- ── Clientes ───────────────────────────────────────────────
INSERT INTO clientes (nombre, apellido_paterno, apellido_materno, email, telefono, rfc, fecha_nacimiento) VALUES
('Juan',      'Pérez',     'Martínez',  'juan.perez@gmail.com',    '7711000001', 'PEMJ900101AA1', '1990-01-15'),
('Ana',       'López',     'García',    'ana.lopez@gmail.com',     '7711000002', 'LOGA920220BB2', '1992-02-20'),
('Pedro',     'Sánchez',   'Ruiz',      'pedro.sanchez@gmail.com', '7711000003', 'SARP881103CC3', '1988-11-03'),
('Laura',     'Martínez',  'Hernández', 'laura.mtz@gmail.com',     '7711000004', 'MAHL950415DD4', '1995-04-15'),
('José',      'Gómez',     'Flores',    'jose.gomez@gmail.com',    '7711000005', 'GOFJ960530EE5', '1996-05-30'),
('Sofía',     'Reyes',     'Castro',    'sofia.reyes@gmail.com',   '7712000006', 'RECS980710FF6', '1998-07-10'),
('Miguel',    'Vargas',    'Ortiz',     'miguel.vargas@gmail.com', '7712000007', 'VAOM850825GG7', '1985-08-25'),
('Elena',     'Cruz',      'Jiménez',   'elena.cruz@gmail.com',    '7712000008', 'CRJE930312HH8', '1993-03-12'),
('Andrés',    'Morales',   'Vega',      'andres.morales@gmail.com','7713000009', 'MOVA910614II9', '1991-06-14'),
('Claudia',   'Núñez',     'Salinas',   'claudia.nunez@gmail.com', '7713000010', 'NUSC001118JJ0', '2000-11-18');

-- ── Direcciones de los 10 clientes ───────────────────────

-- ── Direcciones de los 10 clientes ────────────────────────
INSERT INTO direcciones_cliente (id_cliente_FK, calle, numero_ext, numero_int, colonia, municipio, estado, cp, referencias, es_principal) VALUES
(1,  'Av. Juárez',       '100', NULL, 'Centro',          'Pachuca de Soto',    'Hidalgo', '42000', 'Frente al palacio de gobierno', 1),
(2,  'Morelos',          '220', '3B', 'Centro',          'Tulancingo de Bravo','Hidalgo', '43600', 'Edificio Alameda, tercer piso',  1),
(3,  'Reforma',          '45',  NULL, 'San Javier',      'Pachuca de Soto',    'Hidalgo', '42086', 'Casa color verde con reja negra', 1),
(4,  'Hidalgo',          '12',  NULL, 'Centro',          'Actopan',            'Hidalgo', '42500', 'A una cuadra del mercado',        1),
(5,  'Insurgentes',      '89',  NULL, 'Centro',          'Tizayuca',           'Hidalgo', '43800', 'Frente a la farmacia del ahorro', 1),
(6,  'Benito Juárez',    '330', NULL, 'La Paz',          'Pachuca de Soto',    'Hidalgo', '42040', 'Casa blanca con portón azul',     1),
(7,  'Venustiano Carranza','15',NULL, 'El Arbolito',     'Mineral de la Reforma','Hidalgo','42180','Junto a la tienda OXXO',          1),
(8,  'Nicolás Bravo',    '78',  '2', 'Nuevo Tulancingo', 'Tulancingo de Bravo','Hidalgo', '43640', 'Depto. 2, planta alta',           1),
(9,  'Ignacio Allende',  '55',  NULL,'Centro',           'Apan',               'Hidalgo', '43900', 'Casa de dos pisos, color amarillo',1),
(10, 'Lázaro Cárdenas',  '200', NULL,'Lomas de Pachuca', 'Pachuca de Soto',    'Hidalgo', '42080', 'Privada Las Palmas, casa 12',      1);

-- ── Roles ─────────────────────────────────────────────────

-- ── Marcas ─────────────────────────────────────────────────
INSERT INTO marcas (nombre, pais_origen, descripcion) VALUES
('Honda',    'Japón',     'Líder mundial en motocicletas confiables y eficientes'),
('Yamaha',   'Japón',     'Innovación tecnológica y diseño deportivo'),
('Suzuki',   'Japón',     'Potencia y durabilidad para todo terreno'),
('Kawasaki', 'Japón',     'Motocicletas de alto rendimiento y potencia'),
('BMW',      'Alemania',  'Ingeniería de precisión y lujo en dos ruedas'),
('Italika',  'México',    'La marca mexicana más vendida, accesible y práctica');

-- ── Categorías ────────────────────────────────────────────

-- ── Categorías ─────────────────────────────────────────────
INSERT INTO categorias_moto (nombre, descripcion) VALUES
('Deportiva',        'Motos de alto rendimiento y velocidad, diseñadas para pista y carretera'),
('Trabajo',          'Motos de uso diario y carga, ideales para reparto y ciudad'),
('Scooter',          'Motos automáticas de ciudad con almacenamiento bajo el asiento'),
('Doble Propósito',  'Aptas para carretera y terracería, versátiles y robustas'),
('Touring',          'Diseñadas para viajes largos con comodidad y equipaje'),
('Custom / Chopper', 'Estilo personalizado tipo cruiser, para paseos y exhibición');

-- ── Motos (inventario) ────────────────────────────────────

-- ── Motos (inventario) ─────────────────────────────────────
INSERT INTO motos (id_marca_FK, id_categoria_FK, modelo, anio, cilindraje, color, precio_costo, precio_venta, stock, num_serie, descripcion) VALUES
(1, 2, 'Cargo 150',    2025, 150, 'Rojo',         25000.00,  32000.00,  8,  'HON-CAR-001', 'Moto de trabajo ideal para reparto, confiable y económica'),
(1, 3, 'PCX 150',      2025, 150, 'Blanco',       42000.00,  54000.00,  5,  'HON-PCX-001', 'Scooter premium con panel inteligente y bajo consumo'),
(2, 1, 'R15 V4',       2025, 155, 'Azul Racing',  68000.00,  82000.00,  4,  'YAM-R15-001', 'Moto deportiva 155cc con tecnología de MotoGP'),
(2, 4, 'Tenere 700',   2025, 689, 'Amarillo',    140000.00, 172000.00,  2,  'YAM-TEN-001', 'Adventure trail para todo tipo de camino'),
(3, 2, 'GN125',        2025, 125, 'Negro',        28000.00,  36000.00,  6,  'SUZ-GN1-001', 'Clásica y resistente, perfecta para ciudad y carretera'),
(3, 4, 'DR-Z400S',     2025, 400, 'Amarillo',     78000.00,  98000.00,  3,  'SUZ-DRZ-001', 'Enduro ligero y ágil para terracería y asfalto'),
(4, 1, 'Ninja 400',    2025, 400, 'Verde Kawasaki',108000.00,128000.00, 2,  'KAW-NIN-001', 'Deportiva de acceso con manejo preciso y motor bicilíndrico'),
(4, 6, 'Vulcan S 650', 2025, 649, 'Negro Mate',   98000.00, 120000.00,  2,  'KAW-VUL-001', 'Custom moderna con posición ergonómica ajustable'),
(5, 5, 'R 1250 GS',    2025,1254, 'Gris Titanio', 410000.00,490000.00,  1,  'BMW-GS1-001', 'La referencia mundial del turismo aventura'),
(6, 2, 'FT150',        2025, 150, 'Negro',        18000.00,  24000.00, 15,  'ITA-FT1-001', 'La más vendida de México, ideal para trabajo diario'),
(6, 3, 'Vento 150',    2025, 150, 'Gris',         16000.00,  22000.00, 10,  'ITA-VEN-001', 'Scooter económico y práctico para la ciudad'),
(1, 1, 'CBR 600RR',    2024, 600, 'Rojo y Negro', 195000.00,240000.00,  1,  'HON-CBR-001', 'Supersport de 600cc para entusiastas de la velocidad');

-- ── Accesorios ────────────────────────────────────────────

-- ── Accesorios ─────────────────────────────────────────────
INSERT INTO accesorios (nombre, descripcion, precio_costo, precio_venta, stock, categoria) VALUES
('Casco Integral LS2',       'Casco certificado DOT/ECE talla M-XL',                    1200.00,  1900.00, 30, 'Seguridad'),
('Casco Abierto HJC',        'Casco modular con visera solar integrada',                  900.00,  1400.00, 20, 'Seguridad'),
('Guantes de Cuero Moto',    'Guantes con protección en nudillos, talla S-XL',            320.00,   580.00, 40, 'Seguridad'),
('Guantes de Invierno',      'Guantes térmicos impermeables para clima frío',             380.00,   650.00, 25, 'Seguridad'),
('Chamarra Motociclista',    'Chamarra con protecciones en hombros y codos, CE nivel 1', 1800.00,  2900.00, 15, 'Ropa'),
('Impermeable 2 piezas',     'Traje impermeable pantalón y chaqueta, talla única',        480.00,   820.00, 25, 'Ropa'),
('Botas Moto Cortas',        'Botas de piel con tobillera reforzada',                     850.00,  1400.00, 18, 'Ropa'),
('Aceite Sintético 4T 1L',   'Aceite sintético 10W-40 para motos 4 tiempos',             180.00,   320.00, 60, 'Mantenimiento'),
('Kit de Frenos Delanteros',  'Pastillas y disco delantero universal 220mm',              450.00,   780.00, 20, 'Mantenimiento'),
('Candado Antirrobo Disco',  'Candado de disco con alarma de 110 dB',                    280.00,   490.00, 22, 'Seguridad'),
('Alarma para Moto',         'Alarma con sensor de movimiento y 2 controles',             620.00,  1050.00, 12, 'Seguridad'),
('Soporte Celular Moto',     'Soporte universal para manillar, rotación 360°',            120.00,   220.00, 35, 'Accesorios'),
('Maleta Lateral 30L',       'Maleta lateral rígida impermeable, 30 litros',             980.00,  1600.00, 10, 'Accesorios'),
('Filtro de Aire Deportivo', 'Filtro de alto flujo lavable y reutilizable',              350.00,   590.00, 16, 'Mantenimiento'),
('Batería 12V 9Ah',          'Batería AGM sellada para motos de 125-400cc',              650.00,  1100.00, 14, 'Mantenimiento');

-- ── Cuentas financieras ───────────────────────────────────

-- ── Cuentas financieras ────────────────────────────────────
INSERT INTO cuentas_financieras (nombre, tipo, saldo, descripcion) VALUES
('Caja General',   'caja',   85000.00,  'Efectivo en caja del negocio'),
('BBVA Negocios',  'banco',  320000.00, 'Cuenta bancaria principal para depósitos'),
('Santander PyME', 'banco',  145000.00, 'Cuenta bancaria secundaria para pagos de nómina');

-- ── Ventas ────────────────────────────────────────────────

-- ── Ventas ─────────────────────────────────────────────────
INSERT INTO ventas (id_cliente_FK, id_empleado_FK, folio, subtotal, descuento, iva, total, metodo_pago, estado, observaciones) VALUES
(1, 2, 'VTA-2026-001', 32000.00,    0.00,  5120.00,  37120.00, 'contado',       'completada', 'Pago en efectivo, entrega inmediata'),
(2, 2, 'VTA-2026-002', 82000.00, 2000.00, 12800.00,  92800.00, 'tarjeta',       'completada', 'Pago con tarjeta BBVA, 3 meses sin intereses'),
(3, 3, 'VTA-2026-003', 24000.00,    0.00,  3840.00,  27840.00, 'transferencia', 'completada', 'Transferencia recibida el mismo día'),
(4, 2, 'VTA-2026-004',128000.00, 3000.00, 20000.00, 145000.00, 'credito',       'pendiente',  'Crédito aprobado a 12 meses'),
(5, 3, 'VTA-2026-005', 54000.00,    0.00,  8640.00,  62640.00, 'contado',       'completada', 'Cliente frecuente, trato preferencial');

-- ── Detalle de ventas ─────────────────────────────────────

-- ── Detalle de ventas ──────────────────────────────────────
INSERT INTO detalle_ventas (id_venta_FK, tipo_producto, id_producto_ref, cantidad, precio_unitario, descuento_item, subtotal_item) VALUES
(1, 'moto',      1,  1,  32000.00,    0.00,  32000.00),   -- VTA-001: Cargo 150
(1, 'accesorio', 1,  1,   1900.00,    0.00,   1900.00),   -- VTA-001: Casco
(2, 'moto',      3,  1,  82000.00, 2000.00,  80000.00),   -- VTA-002: R15 V4
(2, 'accesorio', 5,  1,   2900.00,    0.00,   2900.00),   -- VTA-002: Chamarra
(3, 'moto',     10,  1,  24000.00,    0.00,  24000.00),   -- VTA-003: FT150
(4, 'moto',      7,  1, 128000.00, 3000.00, 125000.00),   -- VTA-004: Ninja 400
(5, 'moto',      2,  1,  54000.00,    0.00,  54000.00);

-- ── Cotizaciones ─────────────────────────────────────────

-- ── Cotizaciones ───────────────────────────────────────────
INSERT INTO cotizaciones (id_cliente_FK, id_empleado_FK, folio_cotizacion, total, estado, vigencia_dias, notas) VALUES
(1, 2, 'COT-2026-001', 172000.00, 'vigente',   15, 'Cliente interesado en Tenere 700, solicita financiamiento'),
(2, 3, 'COT-2026-002', 490000.00, 'vigente',   15, 'Cotización BMW GS, cliente evaluando opciones'),
(3, 2, 'COT-2026-003',  98000.00, 'aceptada',  15, 'Aceptada, pendiente de convertir en venta'),
(4, 3, 'COT-2026-004',  36000.00, 'vencida',   15, 'El cliente no regresó a confirmar'),
(5, 2, 'COT-2026-005', 120000.00, 'vigente',   30, 'Cotización Vulcan S, cliente interesado en custom');

-- ── Detalle de cotizaciones ───────────────────────────────

-- ── Detalle de cotizaciones ────────────────────────────────
INSERT INTO detalle_cotizaciones (id_cotizacion_FK, tipo_producto, id_producto_ref, cantidad, precio_unitario, subtotal_item) VALUES
(1, 'moto',      4, 1, 172000.00, 172000.00),  -- COT-001: Tenere 700
(2, 'moto',      9, 1, 490000.00, 490000.00),  -- COT-002: BMW R 1250 GS
(3, 'moto',      6, 1,  98000.00,  98000.00),  -- COT-003: DR-Z400S
(4, 'moto',      1, 1,  32000.00,  32000.00),  -- COT-004: Cargo 150
(4, 'accesorio', 1, 1,   1900.00,   1900.00),  -- COT-004: Casco
(5, 'moto',      8, 1, 120000.00, 120000.00);

-- ── Ingresos ─────────────────────────────────────────────

-- ── Ingresos ───────────────────────────────────────────────
INSERT INTO ingresos (id_cuenta_FK, id_venta_FK, concepto, monto, tipo_ingreso, comprobante) VALUES
(1, 1, 'Venta VTA-2026-001 — Honda Cargo 150',    37120.00, 'venta',  'REC-001'),
(2, 2, 'Venta VTA-2026-002 — Yamaha R15 V4',      92800.00, 'venta',  'REC-002'),
(2, 3, 'Venta VTA-2026-003 — Italika FT150',       27840.00, 'venta',  'REC-003'),
(1, 5, 'Venta VTA-2026-005 — Honda PCX 150',       62640.00, 'venta',  'REC-004'),
(1, NULL,'Abono mensual crédito cliente Ana López',  8000.00, 'abono',  'REC-005'),
(2, NULL,'Venta de accesorios variados mostrador',   4350.00, 'otro',   'REC-006');

-- ── Egresos ───────────────────────────────────────────────

-- ── Egresos ────────────────────────────────────────────────
INSERT INTO egresos (id_cuenta_FK, id_empleado_FK, concepto, monto, tipo_egreso, comprobante) VALUES
(2, 1, 'Nómina quincena 1 junio — 5 empleados',       35750.00, 'nomina',  'NOM-2026-11'),
(1, 4, 'Compra de accesorios para inventario',         18500.00, 'compra',  'FAC-PROV-045'),
(2, 1, 'Mantenimiento sistema de alarmas showroom',     3200.00, 'servicio','SRV-2026-07'),
(1, 3, 'Papelería y suministros de oficina',            1250.00, 'otro',    'TKT-2026-03'),
(2, 5, 'Compra de herramienta para taller',             6800.00, 'compra',  'FAC-TOOL-012'),
(2, 1, 'Nómina quincena 2 junio — 5 empleados',       35750.00, 'nomina',  'NOM-2026-12');

-- ── Crédito cliente (venta a crédito VTA-2026-004) ───────

-- ── Crédito cliente (venta a crédito VTA-2026-004) ─────────
INSERT INTO creditos_cliente (id_cliente_FK, id_venta_FK, id_empleado_FK, monto_total, monto_pagado, saldo_pendiente, tasa_interes, num_pagos, periodicidad, estado, fecha_inicio, fecha_limite) VALUES
(4, 4, 2, 145000.00, 24166.67, 120833.33, 12.00, 12, 'mensual', 'activo', '2026-06-01', '2027-06-01');

-- ── Pagos de crédito (3 pagos realizados) ────────────────

-- ── Pagos de crédito (3 pagos realizados) ──────────────────
INSERT INTO pagos_credito (id_credito_FK, id_cuenta_FK, monto_pago, num_pago, observaciones) VALUES
(1, 1, 8055.56, 1, 'Primer pago, entregado en caja'),
(1, 1, 8055.56, 2, 'Segundo pago mensual'),
(1, 2, 8055.55, 3, 'Tercer pago vía depósito bancario');

-- ── Movimientos de inventario (entradas y salidas) ────────

-- ── Movimientos de inventario (entradas y salidas) ──────────
INSERT INTO movimientos_inventario (tipo_producto, id_producto_ref, id_empleado_FK, tipo_movimiento, cantidad, motivo) VALUES
('moto',      1,  5, 'entrada',  10, 'Compra inicial al proveedor Honda México'),
('moto',      2,  5, 'entrada',   5, 'Compra inicial al proveedor Honda México'),
('moto',      3,  5, 'entrada',   5, 'Compra inicial Yamaha distribuidora'),
('moto',      4,  5, 'entrada',   3, 'Compra inicial Yamaha distribuidora'),
('moto',      5,  5, 'entrada',   8, 'Compra inicial Suzuki'),
('moto',      6,  5, 'entrada',   4, 'Compra inicial Suzuki'),
('moto',      7,  5, 'entrada',   3, 'Compra inicial Kawasaki'),
('moto',      8,  5, 'entrada',   3, 'Compra inicial Kawasaki'),
('moto',      9,  5, 'entrada',   1, 'Unidad única importada BMW'),
('moto',     10,  5, 'entrada',  18, 'Compra inicial Italika'),
('moto',     11,  5, 'entrada',  12, 'Compra inicial Italika'),
('moto',     12,  5, 'entrada',   2, 'Unidad de exhibición Honda'),
('moto',      1,  2, 'salida',    1, 'Venta VTA-2026-001'),
('moto',      3,  2, 'salida',    1, 'Venta VTA-2026-002'),
('moto',     10,  3, 'salida',    1, 'Venta VTA-2026-003'),
('moto',      7,  2, 'salida',    1, 'Venta VTA-2026-004'),
('moto',      2,  3, 'salida',    1, 'Venta VTA-2026-005'),
('moto',      1,  5, 'ajuste',    1, 'Ajuste por unidad dañada en transporte'),
('accesorio', 1,  5, 'entrada',  35, 'Compra inicial accesorios proveedor'),
('accesorio', 2,  5, 'entrada',  25, 'Compra inicial accesorios proveedor'),
('accesorio', 3,  5, 'entrada',  50, 'Compra inicial accesorios proveedor'),
('accesorio', 4,  5, 'entrada',  30, 'Compra inicial accesorios proveedor'),
('accesorio', 5,  5, 'entrada',  20, 'Compra inicial accesorios proveedor'),
('accesorio', 1,  2, 'salida',    1, 'Venta junto con VTA-2026-001'),
('accesorio', 5,  2, 'salida',    1, 'Venta junto con VTA-2026-002');

