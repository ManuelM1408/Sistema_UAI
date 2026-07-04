-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-07-2026 a las 02:39:31
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_uai`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `table_cliente`
--

CREATE TABLE `table_cliente` (
  `idCliente` int(11) NOT NULL,
  `Nombre_Cliente` varchar(45) NOT NULL,
  `Apellido_Cliente` varchar(45) NOT NULL,
  `dni` varchar(8) DEFAULT NULL,
  `nombres` varchar(100) DEFAULT NULL,
  `apellido_paterno` varchar(50) DEFAULT NULL,
  `apellido_materno` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `table_cliente`
--

INSERT INTO `table_cliente` (`idCliente`, `Nombre_Cliente`, `Apellido_Cliente`, `dni`, `nombres`, `apellido_paterno`, `apellido_materno`) VALUES
(1, 'Isaias', 'Bravo', NULL, NULL, NULL, NULL),
(2, 'Claudio', 'Huancahuire', NULL, NULL, NULL, NULL),
(3, 'Elizabeth', 'Chipani', NULL, NULL, NULL, NULL),
(4, 'Manuel', 'Marcelo', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `table_detalle`
--

CREATE TABLE `table_detalle` (
  `id_detalle` int(11) NOT NULL,
  `cliente` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `vendedor` int(11) NOT NULL,
  `totals` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `table_detalle`
--

INSERT INTO `table_detalle` (`id_detalle`, `cliente`, `fecha`, `vendedor`, `totals`) VALUES
(17, 1, '2026-05-30', 2, 800.00),
(18, 2, '2026-05-30', 2, 100.00),
(19, 1, '2026-07-03', 1, 200.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `table_productos`
--

CREATE TABLE `table_productos` (
  `idProductos` int(11) NOT NULL,
  `nombreProductos` varchar(45) NOT NULL,
  `preciosProductos` int(11) NOT NULL,
  `stock` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `table_productos`
--

INSERT INTO `table_productos` (`idProductos`, `nombreProductos`, `preciosProductos`, `stock`) VALUES
(1, 'Impresora', 800, 40),
(2, 'Laptop', 3000, 80),
(3, 'Teclado', 40, 20),
(4, 'Mouse', 20, 90),
(6, 'USB', 20, 15),
(7, 'Pantalla', 400, 45);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `table_vendedor`
--

CREATE TABLE `table_vendedor` (
  `idVendedor` int(11) NOT NULL,
  `nombreVendedor` varchar(55) NOT NULL,
  `password` varchar(100) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `table_vendedor`
--

INSERT INTO `table_vendedor` (`idVendedor`, `nombreVendedor`, `password`) VALUES
(1, 'isaias', '1234'),
(2, 'root', '1234'),
(3, 'yimi', 'abcd');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `table_ventas`
--

CREATE TABLE `table_ventas` (
  `idVentas` int(11) NOT NULL,
  `id_detalle` int(11) NOT NULL,
  `Productos` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `importe` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `table_ventas`
--

INSERT INTO `table_ventas` (`idVentas`, `id_detalle`, `Productos`, `cantidad`, `importe`) VALUES
(32, 17, 1, 1, 800.00),
(33, 18, 6, 2, 40.00),
(34, 18, 4, 3, 60.00),
(35, 19, 4, 10, 200.00);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `table_cliente`
--
ALTER TABLE `table_cliente`
  ADD PRIMARY KEY (`idCliente`),
  ADD UNIQUE KEY `unique_cliente2` (`idCliente`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `table_detalle`
--
ALTER TABLE `table_detalle`
  ADD PRIMARY KEY (`id_detalle`),
  ADD UNIQUE KEY `unique_detalle` (`id_detalle`,`vendedor`,`cliente`),
  ADD KEY `cliente_1` (`cliente`),
  ADD KEY `vendedor_1` (`vendedor`);

--
-- Indices de la tabla `table_productos`
--
ALTER TABLE `table_productos`
  ADD PRIMARY KEY (`idProductos`),
  ADD UNIQUE KEY `unique_producto2` (`idProductos`);

--
-- Indices de la tabla `table_vendedor`
--
ALTER TABLE `table_vendedor`
  ADD PRIMARY KEY (`idVendedor`),
  ADD UNIQUE KEY `unique_vendedor2` (`idVendedor`);

--
-- Indices de la tabla `table_ventas`
--
ALTER TABLE `table_ventas`
  ADD PRIMARY KEY (`idVentas`),
  ADD UNIQUE KEY `unique_ventas2` (`idVentas`,`id_detalle`,`Productos`),
  ADD KEY `nrofacturas_1` (`id_detalle`),
  ADD KEY `productos_1` (`Productos`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `table_cliente`
--
ALTER TABLE `table_cliente`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `table_detalle`
--
ALTER TABLE `table_detalle`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `table_productos`
--
ALTER TABLE `table_productos`
  MODIFY `idProductos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `table_ventas`
--
ALTER TABLE `table_ventas`
  MODIFY `idVentas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `table_detalle`
--
ALTER TABLE `table_detalle`
  ADD CONSTRAINT `table_detalle_ibfk_1` FOREIGN KEY (`cliente`) REFERENCES `table_cliente` (`idCliente`),
  ADD CONSTRAINT `table_detalle_ibfk_2` FOREIGN KEY (`vendedor`) REFERENCES `table_vendedor` (`idVendedor`);

--
-- Filtros para la tabla `table_ventas`
--
ALTER TABLE `table_ventas`
  ADD CONSTRAINT `table_ventas_ibfk_1` FOREIGN KEY (`id_detalle`) REFERENCES `table_detalle` (`id_detalle`),
  ADD CONSTRAINT `table_ventas_ibfk_2` FOREIGN KEY (`Productos`) REFERENCES `table_productos` (`idProductos`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
