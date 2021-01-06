-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.5.5-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para control_prestamos
CREATE DATABASE IF NOT EXISTS `control_prestamos` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `control_prestamos`;

-- Volcando estructura para tabla control_prestamos.equipos
CREATE TABLE IF NOT EXISTS `equipos` (
  `id_equipo` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) NOT NULL,
  `marca` varchar(50) NOT NULL,
  `modelo` varchar(50) NOT NULL,
  `color` varchar(50) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `propiedad` varchar(50) NOT NULL,
  PRIMARY KEY (`id_equipo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla control_prestamos.equipos: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `equipos` DISABLE KEYS */;
REPLACE INTO `equipos` (`id_equipo`, `descripcion`, `marca`, `modelo`, `color`, `cantidad`, `propiedad`) VALUES
	(1, 'Mouse', 'Genius', 'XP56884-1', 'negro', 3, 'Sistemas'),
	(2, 'Mouse', 'Laser', 'IOF-13513513', 'gris', 4, 'Sistemas'),
	(3, 'Teclado', 'Genius', 'A-564565', 'negro', 3, 'Sistemas'),
	(4, 'Monitor', 'LG', '564654', 'negro', 5, 'Sistemas'),
	(5, 'CPU', 'ASUS', 'JK-45656', 'negro', 6, 'Sistemas');
/*!40000 ALTER TABLE `equipos` ENABLE KEYS */;

-- Volcando estructura para vista control_prestamos.nombre_completo
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `nombre_completo` (
	`id_usuario` INT(11) NOT NULL,
	`nombre_usuario` VARCHAR(51) NOT NULL COLLATE 'latin1_swedish_ci'
) ENGINE=MyISAM;

-- Volcando estructura para tabla control_prestamos.prestamos
CREATE TABLE IF NOT EXISTS `prestamos` (
  `id_prestamos` int(11) NOT NULL AUTO_INCREMENT,
  `id_equipo` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `cantidad_equipos` int(11) NOT NULL,
  `fecha_salida` date NOT NULL,
  `fecha_entrega` date NOT NULL,
  PRIMARY KEY (`id_prestamos`),
  KEY `FK_prestamos_equipos` (`id_equipo`),
  KEY `FK_prestamos_usuarios` (`id_usuario`),
  CONSTRAINT `FK_prestamos_equipos` FOREIGN KEY (`id_equipo`) REFERENCES `equipos` (`id_equipo`),
  CONSTRAINT `FK_prestamos_usuarios` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla control_prestamos.prestamos: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `prestamos` DISABLE KEYS */;
REPLACE INTO `prestamos` (`id_prestamos`, `id_equipo`, `id_usuario`, `cantidad_equipos`, `fecha_salida`, `fecha_entrega`) VALUES
	(1, 3, 1, 1, '2020-12-31', '2021-01-15'),
	(2, 3, 1, 1, '2020-12-31', '2021-01-15'),
	(3, 5, 2, 1, '2020-12-31', '2021-02-10');
/*!40000 ALTER TABLE `prestamos` ENABLE KEYS */;

-- Volcando estructura para vista control_prestamos.reporte_prestamos
-- Creando tabla temporal para superar errores de dependencia de VIEW
CREATE TABLE `reporte_prestamos` (
	`cedula` VARCHAR(10) NOT NULL COLLATE 'latin1_swedish_ci',
	`nombre_usuario` VARCHAR(51) NOT NULL COLLATE 'latin1_swedish_ci',
	`descripcion` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
	`marca` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
	`modelo` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
	`color` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
	`cantidad_equipos` INT(11) NOT NULL,
	`propiedad` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
	`fecha_salida` DATE NOT NULL,
	`fecha_entrega` DATE NOT NULL
) ENGINE=MyISAM;

-- Volcando estructura para tabla control_prestamos.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `cedula` varchar(10) NOT NULL,
  `nombres` varchar(25) NOT NULL,
  `apellidos` varchar(25) NOT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla control_prestamos.usuarios: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
REPLACE INTO `usuarios` (`id_usuario`, `cedula`, `nombres`, `apellidos`, `telefono`, `email`, `direccion`) VALUES
	(1, '0254646484', 'Carlos Andres', 'Morales Meneses', '0954546878', 'carlos@gmail.com', 'Ibarra'),
	(2, '0404978231', 'Karlon Patricia', 'Egas Arévalo', '0921784632', 'karol-patricia@gmail.com', 'Bolivar'),
	(3, '0215798632', 'Karen Andrea', 'Olsen Potosi', '0956478213', 'karen-olsen@gmail.com', 'Ibarra'),
	(6, '0401544687', 'Edison ', 'Mendez Criollo', '0913235435', 'edison@gmail.com', 'Otavalo');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

-- Volcando estructura para vista control_prestamos.nombre_completo
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `nombre_completo`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `nombre_completo` AS SELECT usuarios.id_usuario,
CONCAT_WS(" ", nombres, apellidos) AS nombre_usuario
FROM usuarios ;

-- Volcando estructura para vista control_prestamos.reporte_prestamos
-- Eliminando tabla temporal y crear estructura final de VIEW
DROP TABLE IF EXISTS `reporte_prestamos`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `reporte_prestamos` AS SELECT usuarios.cedula, nombre_completo.nombre_usuario, 
equipos.descripcion, equipos.marca, equipos.modelo, equipos.color,
prestamos.cantidad_equipos, equipos.propiedad, prestamos.fecha_salida, prestamos.fecha_entrega
FROM usuarios
INNER JOIN nombre_completo ON usuarios.id_usuario = nombre_completo.id_usuario
INNER JOIN prestamos ON usuarios.id_usuario = prestamos.id_usuario
INNER JOIN equipos ON prestamos.id_equipo = equipos.id_equipo ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
